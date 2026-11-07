//
//  PlayerMemoryOptimizer.swift
//  SmartTube Compatibility Layer
//  Memory pressure protection for legacy iOS devices (1GB - 2GB RAM)
//

import Foundation
import AVFoundation

#if canImport(UIKit)
import UIKit
#endif

public final class PlayerMemoryOptimizer {
    public static let shared = PlayerMemoryOptimizer()

    public let totalRAMGigabytes: Double
    public let isLegacyDevice: Bool

    private init() {
        let ramBytes = ProcessInfo.processInfo.physicalMemory
        let ramGB = Double(ramBytes) / (1024 * 1024 * 1024)
        self.totalRAMGigabytes = ramGB
        // Devices with <= 2.2GB RAM (iPhone 5s..8, iPad Air 1/2, iPad mini 2..4)
        self.isLegacyDevice = ramGB <= 2.2

        setupMemoryWarningObserver()
    }

    /// Optimal forward buffer duration in seconds to avoid Jetsam memory crashes
    public var recommendedForwardBufferDuration: TimeInterval {
        if isLegacyDevice {
            return 12.0 // Lean 12-second buffer for legacy devices
        } else {
            return 60.0 // Standard buffer for modern devices
        }
    }

    /// Maximum suggested video resolution for stable 60fps playback without memory spike
    public var recommendedMaxVideoResolution: CGSize {
        if totalRAMGigabytes <= 1.2 {
            // 1GB RAM devices (iPad Air 1, iPad mini 2/3, iPhone 6/5s) -> 720p HD max
            return CGSize(width: 1280, height: 720)
        } else if isLegacyDevice {
            // 2GB RAM devices (iPad Air 2, iPad mini 4, iPhone 6s/7/8) -> 1080p FHD max
            return CGSize(width: 1920, height: 1080)
        } else {
            // 3GB+ RAM devices -> 4K / 8K capable
            return CGSize(width: 3840, height: 2160)
        }
    }

    /// Configure AVPlayerItem with memory-safe buffer constraints
    public func applyMemoryConstraints(to playerItem: AVPlayerItem) {
        playerItem.preferredForwardBufferDuration = recommendedForwardBufferDuration

        if isLegacyDevice {
            playerItem.preferredPeakBitRate = 5_000_000 // 5 Mbps cap
            playerItem.preferredMaximumResolution = recommendedMaxVideoResolution
            playerItem.canUseNetworkResourcesForLiveStreamingWhilePaused = false
        }
    }

    private func setupMemoryWarningObserver() {
        #if canImport(UIKit)
        NotificationCenter.default.addObserver(
            forName: UIApplication.didReceiveMemoryWarningNotification,
            object: nil,
            queue: .main
        ) { _ in
            URLCache.shared.removeAllCachedResponses()
        }
        #endif
    }
}
