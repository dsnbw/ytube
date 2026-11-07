//
//  DeviceScreenMetrics.swift
//  SmartTube Layout Engine
//  Automatic screen resolution, aspect ratio & adaptive layout detection.
//

import SwiftUI

#if canImport(UIKit)
import UIKit
#endif

public enum DeviceIdiom {
    case phone
    case pad
    case tv
    case mac
}

public struct DeviceScreenMetrics {
    public static var idiom: DeviceIdiom {
        #if os(tvOS)
        return .tv
        #elseif os(macOS)
        return .mac
        #else
        switch UIDevice.current.userInterfaceIdiom {
        case .pad:
            return .pad
        case .tv:
            return .tv
        case .mac:
            return .mac
        default:
            return .phone
        }
        #endif
    }

    /// Automatically calculates optimal grid column count based on available width
    public static func adaptiveColumns(for width: CGFloat) -> Int {
        #if os(tvOS)
        return 4
        #elseif os(macOS)
        if width >= 1400 { return 5 }
        if width >= 1100 { return 4 }
        if width >= 750 { return 3 }
        return 2
        #else
        // Phone / Pad
        if width < 520 {
            // All iPhones in portrait, iPad 1/3 split view: 1 column full-width (pure YouTube native style)
            return 1
        } else if width < 840 {
            // iPhone landscape, iPad mini portrait, 1/2 split view: 2 columns
            return 2
        } else if width < 1240 {
            // iPad Air / Pro portrait and landscape: 3 columns
            return 3
        } else {
            // iPad Pro 12.9" landscape: 4 columns
            return 4
        }
        #endif
    }

    /// Responsive horizontal card padding
    public static func horizontalPadding(for width: CGFloat) -> CGFloat {
        if width < 520 {
            return 0 // Edge-to-edge thumbnails like native YouTube phone
        } else if width < 840 {
            return 12
        } else {
            return 16
        }
    }

    /// Responsive card spacing
    public static func cardSpacing(for width: CGFloat) -> CGFloat {
        if width < 520 {
            return 16
        } else {
            return 14
        }
    }
}

// Environment key for available layout width
private struct LayoutWidthKey: EnvironmentKey {
    static let defaultValue: CGFloat = 390
}

extension EnvironmentValues {
    public var layoutWidth: CGFloat {
        get { self[LayoutWidthKey.self] }
        set { self[LayoutWidthKey.self] = newValue }
    }
}
