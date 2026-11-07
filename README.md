# SmartTube Multiplatform (Legacy & Modern iOS + Android)

[![GitHub Release](https://img.shields.io/github/v/release/dsnbw/ytube?color=brightgreen&label=Release)](https://github.com/dsnbw/ytube/releases)
[![iOS Support](https://img.shields.io/badge/iOS-12.0%20%E2%86%92%2018%2B-blue?logo=apple)](https://github.com/dsnbw/ytube/releases)
[![iPadOS Support](https://img.shields.io/badge/iPadOS-Supported-blue?logo=apple)](https://github.com/dsnbw/ytube/releases)
[![Android Support](https://img.shields.io/badge/Android-4.4%20%E2%86%92%2015%2B-green?logo=android)](https://github.com/dsnbw/ytube/releases)
[![License](https://img.shields.io/badge/License-GPLv3-red)](LICENSE)

An advanced YouTube client re-architected and optimized for **legacy iPhone/iPad (iOS 12 – 16)**, **modern iOS (iOS 17 – 18+)**, and **all Android devices (legacy TV boxes, Smart TVs, tablets, phones)**.

*👉 [Đọc bản Tiếng Việt tại đây (Vietnamese README)](README_VI.md)*

---

## 🚀 Quick Download Links

| Platform | Target Devices / Versions | Format | Direct Download |
| :--- | :--- | :---: | :---: |
| **Legacy iOS / iPadOS** | iPad Mini 2/3/4, iPad Air 1/2, iPhone 5s, 6, 6s, 7, 8, X (iOS 12.0 – 16.7) | `.ipa` | [📥 Download SmartTube-Legacy-iOS12-16.ipa](https://github.com/dsnbw/ytube/releases) |
| **Modern iOS / iPadOS** | iPhone 11+, iPad Pro/Air (iOS 17.0 – 18.0+) | `.ipa` | [📥 Download SmartTube-Modern-iOS17+.ipa](https://github.com/dsnbw/ytube/releases) |
| **Android Universal** | All Android TVs, TV Boxes, Tablets, Smartphones | `.apk` | [📥 Download SmartTube_Universal.apk](https://github.com/dsnbw/ytube/releases) |
| **Android Legacy TV (32-bit)** | Older 32-bit TV Boxes & Tablets (Android 4.4, 5.0, 7.0, 8.0) | `.apk` | [📥 Download SmartTube_ARMv7-32bit.apk](https://github.com/dsnbw/ytube/releases) |
| **Android Modern (64-bit)** | Smart TVs (Sony, TCL, Xiaomi), Chromecast 4K, Shield TV | `.apk` | [📥 Download SmartTube_ARM64-64bit.apk](https://github.com/dsnbw/ytube/releases) |

---

## ✨ Features

- **Ad-Free:** 100% video ads, interstitials, and banner ads blocked.
- **SponsorBlock & DeArrow:** Automatically skips sponsored segments, intros, and reminders; restores clean titles and thumbnails.
- **Memory Pressure Protection:** Optimized AVPlayer buffer constraints preventing memory overflows on 1GB RAM devices (iPad Air 1, iPad mini 2, iPhone 6).
- **Background Playback & PiP:** Continuous audio playback with screen locked and Picture-in-Picture window.
- **Adaptive Video Resolution:** From lightweight 720p/1080p for older chips up to 4K/8K HDR 60fps on modern hardware.
- **No Jailbreak / Root Required:** Easy sideloading and APK installation.

---

## 🛠️ Tooling Integration

- **[`xtool`](https://github.com/xtool-org/xtool):** Cross-platform toolchain allowing iOS SwiftPM app building and IPA packaging without macOS.
- **[`rea`](https://github.com/morluto/rea) (Reverse Engineer Anything):** YouTube stream extraction, cipher token resolution, and SponsorBlock verification tools.
- **[`craft-anything`](https://github.com/storytold/craft-anything):** UI fidelity and multi-screen layout validation suite.

---

## 📖 Installation Guides

- 🍏 **[iOS Installation Guide (TrollStore, Sideloadly, 3uTools, AltStore)](docs/INSTALL_IOS.md)**
- 🤖 **[Android Installation Guide (Smart TV, TV Box, Mobile)](docs/INSTALL_ANDROID.md)**

---

## 🏗️ Repository Architecture

```
ytube/
├── ios/
│   ├── Compatibility/         # iOS 12-16 legacy backport layer (ObservableCompat, NavigationCompat, MemoryOptimizer)
│   ├── SmartTubeIOSCore/      # Foundation models, InnerTube API, SponsorBlock, TokenManager
│   ├── SmartTubeIOS/          # SwiftUI views, ViewModels, Resources, yt.solver
│   ├── SmartTubeApp/          # App entrypoint, Assets, Configs (Legacy & Modern xcconfig)
│   └── Package.swift          # Cross-platform SwiftPM definition
├── android/
│   ├── app/                   # Android app sources & AndroidManifest
│   ├── build.gradle           # Multi-ABI Gradle configuration
│   └── gradle.properties
├── tools/
│   ├── xtool/                 # IPA packaging configurations and scripts
│   ├── rea/                   # REA YouTube cipher and InnerTube inspector
│   └── craft-anything/        # Multi-target UI layout fidelity tester
├── docs/                      # Comprehensive setup & installation guides
├── releases/                  # Release packages (.ipa and .apk)
└── .github/workflows/         # Automated GitHub Actions CI/CD workflows
```

---

## 📜 License

Distributed under the GNU General Public License v3.0 (GPL-3.0). Based on the work of **SmartTube** (Yuliskov) and **SmartTubeIOS** (milika).
