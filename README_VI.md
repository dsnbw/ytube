# SmartTube Đa Nền Tảng (iOS Legacy/Modern & Android)

[![GitHub Release](https://img.shields.io/github/v/release/dsnbw/ytube?color=brightgreen&label=Release)](https://github.com/dsnbw/ytube/releases)
[![iOS Support](https://img.shields.io/badge/iOS-12.0%20%E2%86%92%2018%2B-blue?logo=apple)](https://github.com/dsnbw/ytube/releases)
[![iPadOS Support](https://img.shields.io/badge/iPadOS-Supported-blue?logo=apple)](https://github.com/dsnbw/ytube/releases)
[![Android Support](https://img.shields.io/badge/Android-4.4%20%E2%86%92%2015%2B-green?logo=android)](https://github.com/dsnbw/ytube/releases)
[![License](https://img.shields.io/badge/License-GPLv3-red)](LICENSE)

Phiên bản **SmartTube** được thiết kế lại giao diện **chuẩn theo nguyên bản YouTube**, tự động nhận diện phần cứng/màn hình để tối ưu bố cục và tích hợp tính năng **Tự động Cập nhật phiên bản mới trực tiếp từ GitHub Releases**. Hỗ trợ cả **iPhone/iPad đời cũ (iOS 12 – 16)**, **thiết bị iOS hiện đại (iOS 17 – 18+)** và **mọi dòng máy Android (TV Box cũ, Smart TV, Tablet, Phone)**.

---

## 🚀 Tải Xuống Phiên Bản Mới Nhất (Direct Download Links)

| Nền tảng | Dòng máy / Phiên bản | Định dạng | Tải trực tiếp |
| :--- | :--- | :---: | :---: |
| **iOS / iPadOS Đời Cũ** | iPad Mini 2/3/4, iPad Air 1/2, iPhone 5s, 6, 6s, 7, 8, X (iOS 12.0 – 16.7) | `.ipa` | [📥 Tải SmartTube-Legacy-iOS12-16.ipa](https://github.com/dsnbw/ytube/releases) |
| **iOS / iPadOS Đời Mới** | iPhone 11 trở lên, iPad Pro/Air mới (iOS 17.0 – 18.0+) | `.ipa` | [📥 Tải SmartTube-Modern-iOS17+.ipa](https://github.com/dsnbw/ytube/releases) |
| **Android Universal** | Tương thích mọi dòng Android TV, TV Box, Tablet, Smartphone | `.apk` | [📥 Tải SmartTube_Universal.apk](https://github.com/dsnbw/ytube/releases) |
| **Android TV Cũ (32-bit)** | TV Box cũ, Tablet cũ chạy chip 32-bit (Android 4.4, 5.0, 7.0, 8.0) | `.apk` | [📥 Tải SmartTube_ARMv7-32bit.apk](https://github.com/dsnbw/ytube/releases) |
| **Android 64-bit** | Smart TV Sony/TCL/Xiaomi mới, Chromecast with Google TV, Phone mới | `.apk` | [📥 Tải SmartTube_ARM64-64bit.apk](https://github.com/dsnbw/ytube/releases) |

---

## 🎨 Giao Diện Nguyên Bản YouTube & Tự Động Thích Ứng Màn Hình

1. **Giao Diện Chuẩn YouTube:**
   - **Thanh điều hướng trên (Top Bar):** Logo YouTube đỏ rực, tên thương hiệu SmartTube, nút Cast (AirPlay), Chuông thông báo, Kính lúp tìm kiếm và Avatar tài khoản.
   - **Thanh lọc chủ đề (Topic Filter Chips):** Danh sách chip bo góc ngang ("Tất cả", "Âm nhạc", "Trò chơi", "Tin tức", "Trực tiếp", "Mới tải lên", "Đã xem", v.v.).
   - **Thẻ Video (Video Card) thiết kế mới:** 
     - Thumbnail tỷ lệ chuẩn 16:9 bo góc mềm mại, hiển thị thời lượng video góc dưới phải, nhãn "TRỰC TIẾP" góc trên trái.
     - Thanh tiến trình xem màu đỏ nằm ngay mép dưới thumbnail.
     - Hàng thông tin chi tiết: Avatar kênh tròn 36pt, tiêu đề video đậm 2 dòng, tên kênh, số lượt xem và thời gian đăng tải.
     - Nút menu ngữ cảnh 3 chấm (`...`) để Lưu xem sau, Lưu danh sách phát, Tải về hoặc Ẩn video.

2. **Công Nghệ Nhận Diện Màn Hình Thông Minh (`DeviceScreenMetrics`):**
   - **Điện thoại (Màn hình < 520pt):** Tự động chuyển sang bố cục **1 cột full màn hình** (giống hệt ứng dụng YouTube trên iPhone/Android Phone), loại bỏ hoàn toàn hiện tượng vỡ chữ, đè chữ hay thumbnail bị ép nhỏ.
   - **Máy tính bảng & iPhone xoay ngang (520pt – 840pt):** Tự động dàn **2 cột** cân đối.
   - **iPad Air / iPad Pro / TV (840pt – 1240pt):** Tự động dàn **3 – 4 cột** tối ưu không gian hiển thị, không bị co kéo hay lệch khung hình.
   - Hỗ trợ hoàn hảo tính năng xoay màn hình và iPad Split View (chia đôi màn hình).

---

## 🔄 Tính Năng Tự Động Cập Nhật Trực Tiếp Từ GitHub (Auto-Updater)

- **Tự động kiểm tra trong nền khi mở ứng dụng:** Ứng dụng tự động kết nối API GitHub Releases để kiểm tra phiên bản mới nhất mà không gây trễ giao diện.
- **Banner thông báo cập nhật:** Khi phát hiện phiên bản mới, banner xuất hiện tinh tế ở đầu trang: "Đã có bản cập nhật mới (v32.72) - Cập nhật ngay".
- **Cập nhật 1 chạm cho iOS (TrollStore):** Hỗ trợ gọi trực tiếp URL scheme `trollstore://install?url=...` để TrollStore tự tải và cài đặt vĩnh viễn trong 1 nốt nhạc!
- **Cập nhật tự động cho Android:** Tự động tải file APK và gọi trình cài đặt hệ thống.
- **Kiểm tra thủ công trong Cài đặt:** Vào mục **Cài đặt** -> **Cập nhật ứng dụng** -> Bấm **"Kiểm tra cập nhật từ GitHub"** để kiểm tra bất kỳ lúc nào.

---

## 🛠️ Công Cụ Kỹ Thuật Đã Tích Hợp

- **[`xtool`](https://github.com/xtool-org/xtool):** Toolchain mã nguồn mở cho phép biên dịch và đóng gói `.ipa` độc lập trên Linux, Windows và CI mà không bắt buộc có máy tính macOS.
- **[`rea`](https://github.com/morluto/rea) (Reverse Engineer Anything):** Bộ công cụ phân tích ngược InnerTube API, giải mã luồng video cipher token và SponsorBlock API.
- **[`craft-anything`](https://github.com/storytold/craft-anything):** Bộ kiểm thử chuẩn hóa UI và thiết kế đáp ứng trên mọi kích thước màn hình.

---

## 📖 Hướng Dẫn Cài Đặt Chi Tiết

- 🍏 **[Xem Hướng Dẫn Cài Đặt Chi Tiết Cho iOS (TrollStore, Sideloadly, 3uTools, AltStore)](docs/INSTALL_IOS.md)**
- 🤖 **[Xem Hướng Dẫn Cài Đặt Chi Tiết Cho Android (TV Box, Smart TV, Mobile)](docs/INSTALL_ANDROID.md)**

---

## 📜 Giấy Phép (License)

Dự án được phân phối theo giấy phép mã nguồn mở GPL-3.0. Dựa trên thành quả tuyệt vời của cộng đồng **SmartTube** (Yuliskov) và **SmartTubeIOS** (milika).
