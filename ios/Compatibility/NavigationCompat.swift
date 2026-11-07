//
//  NavigationCompat.swift
//  SmartTube Compatibility Layer
//  Unified navigation container for iOS 12.0 - 18.0+
//

import SwiftUI

public struct CompatNavigationStack<Content: View>: View {
    private let content: Content

    public init(@ViewBuilder content: () -> Content) {
        self.content = content()
    }

    public var body: some View {
        if #available(iOS 16.0, tvOS 16.0, macOS 13.0, *) {
            NavigationStack {
                content
            }
        } else {
            NavigationView {
                content
            }
            .navigationViewStyle(StackNavigationViewStyle())
        }
    }
}
