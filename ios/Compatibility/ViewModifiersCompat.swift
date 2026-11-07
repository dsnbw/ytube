//
//  ViewModifiersCompat.swift
//  SmartTube Compatibility Layer
//  Safe view modifiers for iOS 12.0 - 18.0+
//

import SwiftUI

private struct GeometryKey: PreferenceKey {
    static let defaultValue: CGSize = .zero
    static func reduce(value: inout CGSize, nextValue: () -> CGSize) {
        let next = nextValue()
        if next != .zero {
            value = next
        }
    }
}

public extension View {
    /// Backward-compatible geometry change observer for iOS 12-17
    @ViewBuilder
    func compatOnOrientationOrSizeChange(action: @escaping (CGSize) -> Void) -> some View {
        self.background(
            GeometryReader { proxy in
                Color.clear
                    .preference(key: GeometryKey.self, value: proxy.size)
            }
        )
        .onPreferenceChange(GeometryKey.self) { newSize in
            if newSize != .zero {
                action(newSize)
            }
        }
    }

    /// Safe scroll bounce behavior wrapper
    @ViewBuilder
    func compatScrollBounceBehavior() -> some View {
        if #available(iOS 16.4, tvOS 16.4, macOS 13.3, *) {
            self.scrollBounceBehavior(.basedOnSize)
        } else {
            self
        }
    }
}
