# ytube & SmartTube Multiplatform (v32.75)

[![GitHub Release](https://img.shields.io/github/v/release/dsnbw/ytube?color=brightgreen&label=Release)](https://github.com/dsnbw/ytube/releases)
[![iOS Support](https://img.shields.io/badge/iOS-12.0%20%E2%86%92%2018%2B-blue?logo=apple)](https://github.com/dsnbw/ytube/releases)
[![iPadOS Support](https://img.shields.io/badge/iPadOS-Supported-blue?logo=apple)](https://github.com/dsnbw/ytube/releases)
[![Android Support](https://img.shields.io/badge/Android-4.4%20%E2%86%92%2015%2B-green?logo=android)](https://github.com/dsnbw/ytube/releases)
[![License](https://img.shields.io/badge/License-GPLv3-red)](LICENSE)

High-performance, ad-free YouTube multiplatform client powered by the **SmartTube engine (v32.75)** with an **Authentic Google YouTube Interface** tailored dynamically for Smartphones, Tablets, Smart TVs, and iOS devices.

*No GmsCore or Google Play Services required — 100% standalone and privacy-friendly.*

*👉 [Đọc bản Tiếng Việt tại đây (Vietnamese README)](README_VI.md)*

---

## 🚀 Quick Download Links (Latest Release v32.75)

### 📱 1. FOR ANDROID SMARTPHONES & TABLETS (Touch UI)
*Authentic YouTube Mobile & Tablet interface: Bottom navigation bar (Home, Shorts, Subscriptions, You), category chips, single-column full-width video cards, channel subscribe button, and horizontal action pills. Works standalone without GmsCore.*

| Package | Architecture / Target | Size | Direct Download |
| :--- | :--- | :---: | :---: |
| **`SmartTube_v32.75_Mobile_Phone_Tablet.apk`** | **All Android Phones & Tablets** (Recommended) | 38.3 MB | [📥 Download](https://github.com/dsnbw/ytube/releases/download/v32.75/SmartTube_v32.75_Mobile_Phone_Tablet.apk) |
| **`SmartTube_v32.75_Mobile_arm64.apk`** | Modern 64-bit Phones & Tablets | 28.1 MB | [📥 Download](https://github.com/dsnbw/ytube/releases/download/v32.75/SmartTube_v32.75_Mobile_arm64.apk) |
| **`SmartTube_v32.75_Mobile_armeabi-v7a.apk`** | Legacy 32-bit Phones & Tablets (Android 4.4 – 9.0) | 26.4 MB | [📥 Download](https://github.com/dsnbw/ytube/releases/download/v32.75/SmartTube_v32.75_Mobile_armeabi-v7a.apk) |

---

### 📺 2. FOR SMART TV & ANDROID TV BOX (Remote D-Pad UI)
*Dedicated 10-foot Leanback interface with horizontal shelves, fully optimized for remote controls and 4K/8K displays.*

| Package | Architecture / Target | Size | Direct Download |
| :--- | :--- | :---: | :---: |
| **`SmartTube_v32.75_TV_Box_Universal.apk`** | **All Smart TVs & Android Boxes** (Universal) | 33.8 MB | [📥 Download](https://github.com/dsnbw/ytube/releases/download/v32.75/SmartTube_v32.75_TV_Box_Universal.apk) |
| **`SmartTube_v32.75_TV_arm64.apk`** | Modern 64-bit Smart TVs | 25.5 MB | [📥 Download](https://github.com/dsnbw/ytube/releases/download/v32.75/SmartTube_v32.75_TV_arm64.apk) |
| **`SmartTube_v32.75_TV_armeabi-v7a.apk`** | Older 32-bit TV Boxes | 24.3 MB | [📥 Download](https://github.com/dsnbw/ytube/releases/download/v32.75/SmartTube_v32.75_TV_armeabi-v7a.apk) |
| **`SmartTube_v32.75_TV_x86.apk`** | PC Emulators & Intel x86 devices | 27.1 MB | [📥 Download](https://github.com/dsnbw/ytube/releases/download/v32.75/SmartTube_v32.75_TV_x86.apk) |

---

### 🍏 3. FOR IPHONE & IPAD (iOS / iPadOS)
*Auto-adapts between 1-column layout on iPhone and adaptive grid on iPad. Low-RAM memory optimization avoids Jetsam crashes on older iOS devices.*

| Package | Target iOS Version | Size | Direct Download |
| :--- | :--- | :---: | :---: |
| **`SmartTube-Legacy-iOS12-16.ipa`** | Legacy iPhone & iPad (iOS 12.0 – 16.7) | 113.2 MB | [📥 Download](https://github.com/dsnbw/ytube/releases/download/v32.75/SmartTube-Legacy-iOS12-16.ipa) |
| **`SmartTube-Modern-iOS17+.ipa`** | Modern iPhone & iPad (iOS 17.0 – 18.0+) | 113.2 MB | [📥 Download](https://github.com/dsnbw/ytube/releases/download/v32.75/SmartTube-Modern-iOS17+.ipa) |

---

## 🌟 Key Features
- **Authentic Google YouTube Interface:** Dynamically detects device screen dimensions and renders the authentic YouTube mobile, tablet, or TV layout.
- **Zero Ads & SponsorBlock:** Blocks video pre-roll/mid-roll ads, banners, and automatically skips sponsored segments.
- **100% Standalone:** Runs without requiring Google Play Services or GmsCore.
- **Background & PiP Playback:** Continue listening with screen turned off or in picture-in-picture mode.
- **Auto-Updater:** Automatically checks for new releases on GitHub and notifies you when an update is available.

---

## 🛠️ Project Structure
- `android/`: Android source code (Mobile Touch UI + TV Leanback UI).
- `ios/`: iOS client source code (SwiftUI / UIKit).
- `releases/`: Compiled installer packages (.apk and .ipa) ready to install.
- `tools/`: Build scripts, release deployment automations.

---

## 📜 License
Distributed under the GNU General Public License v3.0 (GPLv3).
