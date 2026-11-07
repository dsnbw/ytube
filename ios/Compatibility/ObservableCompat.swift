//
//  ObservableCompat.swift
//  SmartTube Compatibility Layer
//  Supports iOS 12.0 - 18.0+
//

import Foundation
import SwiftUI

#if canImport(Observation)
import Observation
#endif
#if canImport(Combine)
import Combine
#endif

/// Unified marker protocol for state stores and view models across all iOS versions.
/// On iOS 17+, SwiftUI uses observation automatically with `@Observable`.
/// On iOS 12 - 16, models conform to `ObservableObject` so SwiftUI views can observe them safely.
#if canImport(Combine)
public protocol SmartTubeObservable: AnyObject, ObservableObject {}
#else
public protocol SmartTubeObservable: AnyObject {}
#endif

/// State synchronization helper for iOS 12-16 fallback
public final class StateSignal: ObservableObject {
    public static let shared = StateSignal()
    @Published public var changeCounter: Int = 0

    public func notify() {
        changeCounter &+= 1
    }
}
