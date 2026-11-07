//
//  AppUpdaterService.swift
//  SmartTube Auto-Updater
//  Checks GitHub Releases for new updates and handles 1-click updates (TrollStore, IPA, APK).
//

import Foundation
import SwiftUI

#if canImport(UIKit)
import UIKit
#endif

@MainActor
public final class AppUpdaterService: ObservableObject {
    public static let shared = AppUpdaterService()

    public static let currentVersion = "32.72"
    public static let currentBuild = "3272"
    public static let repoOwner = "dsnbw"
    public static let repoName = "ytube"

    @Published public var isChecking: Bool = false
    @Published public var updateAvailable: Bool = false
    @Published public var latestVersion: String = ""
    @Published public var releaseTitle: String = ""
    @Published public var releaseNotes: String = ""
    @Published public var downloadURL: URL? = nil
    @Published public var checkError: String? = nil
    @Published public var lastCheckTime: Date? = nil

    private init() {}

    /// Query GitHub Releases API for the latest release
    public func checkForUpdates(manual: Bool = false) async {
        isChecking = true
        checkError = nil
        
        let apiURL = URL(string: "https://api.github.com/repos/\(Self.repoOwner)/\(Self.repoName)/releases/latest")!
        var request = URLRequest(url: apiURL)
        request.setValue("SmartTubeApp/\(Self.currentVersion)", forHTTPHeaderField: "User-Agent")
        request.timeoutInterval = 10

        do {
            let (data, response) = try await URLSession.shared.data(for: request)
            guard let httpResponse = response as? HTTPURLResponse, httpResponse.statusCode == 200 else {
                if manual {
                    checkError = "Không thể kết nối đến máy chủ GitHub."
                }
                isChecking = false
                return
            }

            guard let json = try JSONSerialization.jsonObject(with: data) as? [String: Any],
                  let tagName = json["tag_name"] as? String else {
                isChecking = false
                return
            }

            self.latestVersion = tagName
            self.releaseTitle = (json["name"] as? String) ?? tagName
            self.releaseNotes = (json["body"] as? String) ?? ""
            self.lastCheckTime = Date()

            // Resolve suitable download asset
            if let assets = json["assets"] as? [[String: Any]] {
                #if os(iOS)
                let isLegacy = ProcessInfo.processInfo.operatingSystemVersion.majorVersion < 17
                let targetAssetPrefix = isLegacy ? "SmartTube-Legacy" : "SmartTube-Modern"
                
                for asset in assets {
                    if let name = asset["name"] as? String,
                       name.contains(targetAssetPrefix),
                       let downloadStr = asset["browser_download_url"] as? String,
                       let url = URL(string: downloadStr) {
                        self.downloadURL = url
                        break
                    }
                }
                #else
                for asset in assets {
                    if let name = asset["name"] as? String,
                       name.contains("universal"),
                       let downloadStr = asset["browser_download_url"] as? String,
                       let url = URL(string: downloadStr) {
                        self.downloadURL = url
                        break
                    }
                }
                #endif
            }

            // Compare version
            let cleanRemote = tagName.replacingOccurrences(of: "v", with: "").trimmingCharacters(in: .whitespaces)
            let cleanLocal = Self.currentVersion.trimmingCharacters(in: .whitespaces)

            if isNewerVersion(remote: cleanRemote, local: cleanLocal) {
                self.updateAvailable = true
            } else {
                self.updateAvailable = false
                if manual {
                    self.checkError = "Bạn đang sử dụng phiên bản mới nhất (\(Self.currentVersion))."
                }
            }

        } catch {
            if manual {
                self.checkError = "Lỗi kiểm tra cập nhật: \(error.localizedDescription)"
            }
        }

        isChecking = false
    }

    /// Perform 1-click update: tries TrollStore first, falls back to direct browser download
    public func performUpdate() {
        guard let url = downloadURL else { return }

        #if canImport(UIKit)
        // Check if TrollStore is installed to trigger silent 1-click update
        if let trollStoreURL = URL(string: "trollstore://install?url=\(url.absoluteString)"),
           UIApplication.shared.canOpenURL(trollStoreURL) {
            UIApplication.shared.open(trollStoreURL, options: [:], completionHandler: nil)
            return
        }

        // Direct browser / Safari download
        UIApplication.shared.open(url, options: [:], completionHandler: nil)
        #endif
    }

    private func isNewerVersion(remote: String, local: String) -> Bool {
        return remote.compare(local, options: .numeric) == .orderedDescending
    }
}
