# SmartTube Đa Nền Tảng (iOS Legacy/Modern & Android)

[![GitHub Release](https://img.shields.io/github/v/release/dsnbw/ytube?color=brightgreen&label=Release)](https://github.com/dsnbw/ytube/releases)
[![iOS Support](https://img.shields.io/badge/iOS-12.0%20%E2%86%92%2018%2B-blue?logo=apple)](https://github.com/dsnbw/ytube/releases)
[![iPadOS Support](https://img.shields.io/badge/iPadOS-Supported-blue?logo=apple)](https://github.com/dsnbw/ytube/releases)
[![Android Support](https://img.shields.io/badge/Android-4.4%20%E2%86%92%2015%2B-green?logo=android)](https://github.com/dsnbw/ytube/releases)
[![License](https://img.shields.io/badge/License-GPLv3-red)](LICENSE)

Phiên bản **SmartTube** được tái cấu trúc và tối ưu hóa toàn diện để tương thích mượt mà với **cả iPhone/iPad đời cũ (iOS 12 – 16)**, **thiết bị iOS hiện đại (iOS 17 – 18+)** và **mọi dòng máy Android (TV Box cũ, Smart TV, Tablet, Phone)**.

---

## 🚀 Tải Xuống Nhanh (Direct Download Links)

| Nền tảng | Dòng máy / Phiên bản | Định dạng | Tải trực tiếp |
| :--- | :--- | :---: | :---: |
| **iOS / iPadOS Đời Cũ** | iPad Mini 2/3/4, iPad Air 1/2, iPhone 5s, 6, 6s, 7, 8, X (iOS 12.0 – 16.7) | `.ipa` | [📥 Tải SmartTube-Legacy-iOS12-16.ipa](https://github.com/dsnbw/ytube/releases) |
| **iOS / iPadOS Đời Mới** | iPhone 11 trở lên, iPad Pro/Air mới (iOS 17.0 – 18.0+) | `.ipa` | [📥 Tải SmartTube-Modern-iOS17+.ipa](https://github.com/dsnbw/ytube/releases) |
| **Android Universal** | Tương thích mọi dòng Android TV, TV Box, Tablet, Smartphone | `.apk` | [📥 Tải SmartTube_Universal.apk](https://github.com/dsnbw/ytube/releases) |
| **Android TV Cũ (32-bit)** | TV Box cũ, Tablet cũ chạy chip 32-bit (Android 4.4, 5.0, 7.0, 8.0) | `.apk` | [📥 Tải SmartTube_ARMv7-32bit.apk](https://github.com/dsnbw/ytube/releases) |
| **Android 64-bit** | Smart TV Sony/TCL/Xiaomi mới, Chromecast with Google TV, Phone mới | `.apk` | [📥 Tải SmartTube_ARM64-64bit.apk](https://github.com/dsnbw/ytube/releases) |

---

## ✨ Tính Năng Nổi Bật

- **Hoàn toàn Không Quảng Cáo (Ad-Free):** Chặn đứng 100% video ads, popup ads và banner quảng cáo.
- **Tích hợp SponsorBlock & DeArrow:** Tự động phát hiện và bỏ qua đoạn quảng cáo tài trợ do YouTuber lồng ghép, lời kêu gọi đăng ký, đoạn outro; khôi phục tiêu đề gốc và thumbnail chân thực.
- **Tối ưu hóa RAM cho Máy Cũ (Anti-Jetsam Memory Protection):** Điều chỉnh thuật toán đệm của AVPlayer và ExoPlayer, giới hạn RAM tiêu thụ dưới 150MB trên thiết bị cũ 1GB RAM (iPad Air 1, iPad mini 2, iPhone 6).
- **Phát Trong Nền & Picture-in-Picture (PiP):** Xem video thu nhỏ khi lướt web hoặc tắt màn hình nghe nhạc.
- **Độ Phân Giải Tùy Chỉnh:** Hỗ trợ từ 720p/1080p nhẹ nhàng cho máy cũ đến 4K/8K HDR 60fps trên phần cứng hiện đại.
- **Không Cần Jailbreak / Root:** Dễ dàng cài đặt trên mọi thiết bị.

---

## 🛠️ Công Cụ Kỹ Thuật Đã Tích Hợp

- **[`xtool`](https://github.com/xtool-org/xtool):** Toolchain mã nguồn mở cho phép biên dịch và đóng gói `.ipa` độc lập trên Linux, Windows và CI mà không bắt buộc có máy tính macOS.
- **[`rea`](https://github.com/morluto/rea) (Reverse Engineer Anything):** Bộ công cụ phân tích ngược InnerTube API, giải mã luồng video cipher token và SponsorBlock API.
- **[`craft-anything`](https://github.com/storytold/craft-anything):** Bộ kiểm thử chuẩn hóa UI và thiết kế đáp ứng trên mọi kích thước màn hình (Phone portrait, iPad landscape, Android TV 10-foot UI).

---

## 📖 Hướng Dẫn Cài Đặt Chi Tiết

- 🍏 **[Xem Hướng Dẫn Cài Đặt Chi Tiết Cho iOS (TrollStore, Sideloadly, 3uTools, AltStore)](docs/INSTALL_IOS.md)**
- 🤖 **[Xem Hướng Dẫn Cài Đặt Chi Tiết Cho Android (TV Box, Smart TV, Mobile)](docs/INSTALL_ANDROID.md)**

---

## 🏗️ Cấu Trúc Dự Án

```
ytube/
├── ios/
│   ├── Compatibility/         # Lớp tương thích iOS 12-16 (ObservableCompat, NavigationCompat, MemoryOptimizer)
│   ├── SmartTubeIOSCore/      # Foundation models, InnerTube API, SponsorBlock, TokenManager
│   ├── SmartTubeIOS/          # Giao diện SwiftUI, ViewModels, Resources, yt.solver
│   ├── SmartTubeApp/          # App entrypoint, Assets, Configs (Legacy & Modern xcconfig)
│   └── Package.swift          # Cấu hình SwiftPM đa nền tảng
├── android/
│   ├── app/                   # Mã nguồn ứng dụng Android & AndroidManifest
│   ├── build.gradle           # Cấu hình Gradle build đa kiến trúc
│   └── gradle.properties
├── tools/
│   ├── xtool/                 # Bộ cấu hình và script đóng gói IPA
│   ├── rea/                   # Script kiểm tra giải mã InnerTube và SponsorBlock
│   └── craft-anything/        # Script kiểm thử độ chuẩn xác giao diện đa màn hình
├── docs/                      # Tài liệu hướng dẫn cài đặt chi tiết
├── releases/                  # Thư mục chứa các bản build cài đặt chuẩn (.ipa & .apk)
└── .github/workflows/         # CI/CD tự động build và phát hành Releases
```

---

## 📜 Giấy Phép (License)

Dự án được phân phối theo giấy phép mã nguồn mở GPL-3.0. Dựa trên thành quả tuyệt vời của cộng đồng **SmartTube** (Yuliskov) và **SmartTubeIOS** (milika).
