// swift-tools-version:5.9
import PackageDescription

let package = Package(
    name: "SmartTubeIOS",
    defaultLocalization: "en",
    platforms: [
        .iOS(.v12),
        .macOS(.v12),
        .tvOS(.v12),
    ],
    products: [
        .library(name: "SmartTubeCompatibility", targets: ["SmartTubeCompatibility"]),
        .library(name: "SmartTubeIOSCore", targets: ["SmartTubeIOSCore"]),
        .library(name: "SmartTubeIOS", targets: ["SmartTubeIOS"]),
    ],
    dependencies: [],
    targets: [
        .target(
            name: "SmartTubeCompatibility",
            path: "Compatibility"
        ),
        .target(
            name: "SmartTubeIOSCore",
            dependencies: ["SmartTubeCompatibility"],
            path: "SmartTubeIOSCore"
        ),
        .target(
            name: "SmartTubeIOS",
            dependencies: ["SmartTubeIOSCore", "SmartTubeCompatibility"],
            path: "SmartTubeIOS",
            resources: [
                .process("Localizable.xcstrings"),
                .copy("Resources/yt.solver.lib.min.js"),
                .copy("Resources/yt.solver.core.min.js"),
            ]
        ),
    ]
)
