//
//  AppUpdateBannerView.swift
//  SmartTube Auto-Updater Banner
//  Displays an in-app banner prompting the user to update when a new version is detected.
//

import SwiftUI

public struct AppUpdateBannerView: View {
    @ObservedObject private var updater = AppUpdaterService.shared
    @State private var isDismissed = false

    public init() {}

    public var body: some View {
        if updater.updateAvailable && !isDismissed {
            VStack(spacing: 8) {
                HStack(alignment: .center, spacing: 12) {
                    ZStack {
                        Circle()
                            .fill(Color.red.opacity(0.15))
                            .frame(width: 36, height: 36)
                        Image(systemName: "arrow.down.circle.fill")
                            .font(.system(size: 20))
                            .foregroundColor(.red)
                    }

                    VStack(alignment: .leading, spacing: 2) {
                        Text("Đã có bản cập nhật SmartTube mới (\(updater.latestVersion))")
                            .font(.system(size: 14, weight: .semibold))
                            .foregroundColor(.primary)
                            .lineLimit(1)
                        Text("Bấm để cập nhật ngay (TrollStore / Sideload)")
                            .font(.system(size: 12))
                            .foregroundColor(.secondary)
                            .lineLimit(1)
                    }

                    Spacer()

                    // Update Button
                    Button {
                        updater.performUpdate()
                    } label: {
                        Text("Cập nhật")
                            .font(.system(size: 13, weight: .bold))
                            .foregroundColor(.white)
                            .padding(.horizontal, 12)
                            .padding(.vertical, 6)
                            .background(Color.red)
                            .cornerRadius(16)
                    }
                    .buttonStyle(.plain)

                    // Close Button
                    Button {
                        withAnimation {
                            isDismissed = true
                        }
                    } label: {
                        Image(systemName: "xmark")
                            .font(.system(size: 12, weight: .semibold))
                            .foregroundColor(.secondary)
                            .padding(6)
                    }
                    .buttonStyle(.plain)
                }
                .padding(.horizontal, 14)
                .padding(.vertical, 10)
                .background(
                    RoundedRectangle(cornerRadius: 12)
                        .fill(Color(UIColor.secondarySystemBackground))
                        .shadow(color: Color.black.opacity(0.1), radius: 6, x: 0, y: 2)
                )
            }
            .padding(.horizontal, 12)
            .padding(.top, 4)
            .transition(.move(edge: .top).combined(with: .opacity))
        }
    }
}
