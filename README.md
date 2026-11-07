# SmartTube Multiplatform (Legacy & Modern iOS + Android)

[![GitHub Release](https://img.shields.io/github/v/release/dsnbw/ytube?color=brightgreen&label=Release)](https://github.com/dsnbw/ytube/releases)
[![iOS Support](https://img.shields.io/badge/iOS-12.0%20%E2%86%92%2018%2B-blue?logo=apple)](https://github.com/dsnbw/ytube/releases)
[![iPadOS Support](https://img.shields.io/badge/iPadOS-Supported-blue?logo=apple)](https://github.com/dsnbw/ytube/releases)
[![Android Support](https://img.shields.io/badge/Android-4.4%20%E2%86%92%2015%2B-green?logo=android)](https://github.com/dsnbw/ytube/releases)
[![License](https://img.shields.io/badge/License-GPLv3-red)](LICENSE)

An advanced YouTube client re-architected with an **authentic native YouTube UI**, automatic device & screen resolution detection, and built-in **GitHub Releases Auto-Updater**. Fully optimized for **legacy iPhone/iPad (iOS 12 – 16)**, **modern iOS (iOS 17 – 18+)**, and **all Android devices (legacy TV boxes, Smart TVs, tablets, phones)**.

*👉 [Đọc bản Tiếng Việt tại đây (Vietnamese README)](README_VI.md)*

---

## 🚀 Quick Download Links (Latest v32.72)

| Platform | Target Devices / Versions | Format | Direct Download |
| :--- | :--- | :---: | :---: |
| **Legacy iOS / iPadOS** | iPad Mini 2/3/4, iPad Air 1/2, iPhone 5s, 6, 6s, 7, 8, X (iOS 12.0 – 16.7) | `.ipa` | [📥 Download SmartTube-Legacy-iOS12-16.ipa](https://github.com/dsnbw/ytube/releases) |
| **Modern iOS / iPadOS** | iPhone 11+, iPad Pro/Air (iOS 17.0 – 18.0+) | `.ipa` | [📥 Download SmartTube-Modern-iOS17+.ipa](https://github.com/dsnbw/ytube/releases) |
| **Android Universal** | All Android TVs, TV Boxes, Tablets, Smartphones | `.apk` | [📥 Download SmartTube_Universal.apk](https://github.com/dsnbw/ytube/releases) |
| **Android Legacy TV (32-bit)** | Older 32-bit TV Boxes & Tablets (Android 4.4, 5.0, 7.0, 8.0) | `.apk` | [📥 Download SmartTube_ARMv7-32bit.apk](https://github.com/dsnbw/ytube/releases) |
| **Android Modern (64-bit)** | Smart TVs (Sony, TCL, Xiaomi), Chromecast 4K, Shield TV | `.apk` | [📥 Download SmartTube_ARM64-64bit.apk](https://github.com/dsnbw/ytube/releases) |

---

## 🎨 Authentic YouTube UI & Adaptive Layout Engine

1. **Native YouTube Design:**
   - **Header Bar:** Iconic red YouTube emblem, SmartTube branding, AirPlay/Cast button, Notification bell, Search button, and Account avatar.
   - **Topic Filter Pills:** Smooth scrolling horizontal category chips ("All", "Music", "Gaming", "News", "Live", "Podcasts", "Recently uploaded", "Watched").
   - **Redesigned Video Card:** 16:9 thumbnail with rounded corners, live/duration badges, red watch progress bar, circular channel avatar, 2-line title, metadata row, and 3-dots action menu (`...`).

2. **Automatic Screen Detection (`DeviceScreenMetrics`):**
   - **Phones (< 520pt):** 1-column full-width edge-to-edge layout matching the official YouTube mobile app. Zero squeezed text or tiny thumbnails.
   - **Tablets / Landscape (520pt – 840pt):** Balanced 2-column grid.
   - **iPad Air / iPad Pro (840pt – 1240pt):** Clean 3–4 column grid perfectly proportioned to the display size.
   - Supports orientation changes and iPad Split View seamlessly.

---

## 🔄 Built-in GitHub Auto-Updater

- **Automatic Startup Checks:** Periodically queries GitHub Releases in the background without affecting app performance.
- **In-App Update Banner:** Prompts users when a new release is available with version and changelog info.
- **1-Click TrollStore Installation:** Directly invokes `trollstore://install?url=...` for seamless permanent installation on supported iOS devices.
- **Android In-App Updater:** Downloads APKs directly and prompts package installation.
- **Manual Check in Settings:** Settings -> App Updates -> Check for updates anytime.

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

## 📜 License

Distributed under the GNU General Public License v3.0 (GPL-3.0). Based on the work of **SmartTube** (Yuliskov) and **SmartTubeIOS** (milika).
