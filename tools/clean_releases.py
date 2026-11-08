#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Clean old releases on GitHub, keeping only the latest v32.74, and fix Vietnamese UTF-8 encoding.
"""

import os
import sys
import json
import re
import urllib.request
import urllib.error

# Ensure UTF-8 output in Windows console
if sys.stdout.encoding != 'utf-8':
    try:
        sys.stdout.reconfigure(encoding='utf-8')
    except Exception:
        pass

TOKEN = os.environ.get('GITHUB_TOKEN')
if not TOKEN and os.path.exists('github.md'):
    with open('github.md', 'r', encoding='utf-8') as f:
        for line in f:
            m = re.search(r'ghp_[a-zA-Z0-9]+', line)
            if m:
                TOKEN = m.group(0)
                break

if not TOKEN:
    print("[-] No GitHub token found.")
    sys.exit(1)

REPO = "dsnbw/ytube"
HEADERS = {
    "Authorization": f"token {TOKEN}",
    "Accept": "application/vnd.github.v3+json",
    "User-Agent": "ReleaseCleaner"
}

def list_releases():
    url = f"https://api.github.com/repos/{REPO}/releases"
    req = urllib.request.Request(url, headers=HEADERS)
    with urllib.request.urlopen(req) as resp:
        return json.loads(resp.read().decode('utf-8'))

def delete_release(release_id, tag_name):
    print(f"[*] Deleting old release {tag_name} (ID: {release_id})...")
    url = f"https://api.github.com/repos/{REPO}/releases/{release_id}"
    req = urllib.request.Request(url, headers=HEADERS, method="DELETE")
    try:
        with urllib.request.urlopen(req) as resp:
            print(f"[+] Deleted release {tag_name}")
    except Exception as e:
        print(f"[-] Failed to delete release {tag_name}: {e}")

def update_v3274_release(release_id):
    print("[*] Updating v32.74 with 100% correct Vietnamese UTF-8...")
    
    title = "ytube & SmartTube v32.74 (Bản Nâng Cấp Giao Diện Chuẩn Google YouTube)"
    
    body = """# 🌟 ytube & SmartTube v32.74 — Giao Diện Chuẩn Google YouTube Đa Nền Tảng

Bản phát hành chính thức **v32.74** mới nhất:
- Hoạt động trên nền tảng **Engine SmartTube (v32.74)** mượt mà, ổn định.
- **100% Giao diện chuẩn Google YouTube**: Tự động nhận diện phần cứng màn hình (Điện thoại, Máy tính bảng, Smart TV) để bố cục giao diện tương ứng, hoàn toàn không bị lệch vỡ.
- **Hoạt động độc lập 100%**: Không cần cài đặt GmsCore, MicroG hay bất kỳ dịch vụ Google Play nào.
- **Chặn sạch 100% quảng cáo**: Không quảng cáo video, không quảng cáo đè, tích hợp sẵn SponsorBlock tự động tua qua phần giới thiệu/tài trợ.

---

## 🚀 Danh Sách File Cài Đặt Trực Tiếp (Release Packages)

### 📱 1. DÀNH CHO ĐIỆN THOẠI & MÁY TÍNH BẢNG ANDROID (Touch UI Chuẩn YouTube)
> *Giao diện cảm ứng chuẩn YouTube Mobile & Tablet: Thanh điều hướng đáy (Trang chủ, Shorts, Đăng ký, Bạn), bộ lọc chủ đề, khung xem video đầy đủ nút Like, Chia sẻ, Tải xuống, Đăng ký kênh. Hoạt động độc lập KHÔNG CẦN GmsCore.*

| Tên File Cài Đặt | Dòng Máy Phù Hợp | Dung Lượng | Tải Xuống Trực Tiếp |
| :--- | :--- | :---: | :---: |
| **`SmartTube_v32.74_Mobile_Phone_Tablet.apk`** | **Mọi Điện Thoại & Tablet Android** (Khuyên dùng số 1) | 38.3 MB | [📥 Tải bản Mobile & Tablet](https://github.com/dsnbw/ytube/releases/download/v32.74/SmartTube_v32.74_Mobile_Phone_Tablet.apk) |
| **`SmartTube_v32.74_Mobile_arm64.apk`** | Điện thoại & Tablet 64-bit đời mới | 28.1 MB | [📥 Tải bản arm64](https://github.com/dsnbw/ytube/releases/download/v32.74/SmartTube_v32.74_Mobile_arm64.apk) |
| **`SmartTube_v32.74_Mobile_armeabi-v7a.apk`** | Điện thoại & Tablet 32-bit đời cũ (Android 4.4 – 9.0) | 26.4 MB | [📥 Tải bản armeabi-v7a](https://github.com/dsnbw/ytube/releases/download/v32.74/SmartTube_v32.74_Mobile_armeabi-v7a.apk) |

---

### 📺 2. DÀNH CHO SMART TV & ANDROID TV BOX (Giao Diện Điều Khiển Bằng Remote)
> *Giao diện Leanback hàng ngang chuyên dụng cho màn hình lớn 4K/8K, điều khiển bằng Remote tiện lợi.*

| Tên File Cài Đặt | Dòng Máy Phù Hợp | Dung Lượng | Tải Xuống Trực Tiếp |
| :--- | :--- | :---: | :---: |
| **`SmartTube_v32.74_TV_Box_Universal.apk`** | **Mọi Smart TV & Android Box** (Bản đa năng) | 33.8 MB | [📥 Tải bản TV Box Universal](https://github.com/dsnbw/ytube/releases/download/v32.74/SmartTube_v32.74_TV_Box_Universal.apk) |
| **`SmartTube_v32.74_TV_arm64.apk`** | Smart TV 64-bit hiện đại | 25.5 MB | [📥 Tải bản TV arm64](https://github.com/dsnbw/ytube/releases/download/v32.74/SmartTube_v32.74_TV_arm64.apk) |
| **`SmartTube_v32.74_TV_armeabi-v7a.apk`** | Android TV Box cũ chip 32-bit | 24.3 MB | [📥 Tải bản TV armeabi-v7a](https://github.com/dsnbw/ytube/releases/download/v32.74/SmartTube_v32.74_TV_armeabi-v7a.apk) |
| **`SmartTube_v32.74_TV_x86.apk`** | Máy giả lập Android trên PC hoặc chip Intel | 27.1 MB | [📥 Tải bản TV x86](https://github.com/dsnbw/ytube/releases/download/v32.74/SmartTube_v32.74_TV_x86.apk) |

---

### 🍏 3. DÀNH CHO IPHONE & IPAD (iOS / iPadOS)
> *Tự động nhận diện thiết bị: Hiển thị 1 cột trên iPhone, thích ứng đa cột mượt mà trên iPad. Tương thích từ iOS 12.0 đến iOS 18+.*

| Tên File Cài Đặt | Phiên Bản iOS Hỗ Trợ | Dung Lượng | Tải Xuống Trực Tiếp |
| :--- | :--- | :---: | :---: |
| **`SmartTube-Legacy-iOS12-16.ipa`** | iPad & iPhone đời cũ (iOS 12.0 – 16.7) | 1.0 MB | [📥 Tải bản Legacy IPA](https://github.com/dsnbw/ytube/releases/download/v32.74/SmartTube-Legacy-iOS12-16.ipa) |
| **`SmartTube-Modern-iOS17+.ipa`** | iPhone & iPad đời mới (iOS 17.0 – 18.0+) | 1.0 MB | [📥 Tải bản Modern IPA](https://github.com/dsnbw/ytube/releases/download/v32.74/SmartTube-Modern-iOS17.ipa) |

---

## ✨ Tính Năng Nổi Bật:
1. **Engine v32.74:** Giải mã luồng phát YouTube InnerTube mới nhất, phát video 4K/60fps mượt mà.
2. **Chuẩn giao diện Google YouTube:** Tự động điều chỉnh bố cục theo tỷ lệ màn hình thực tế, không bị méo lệch.
3. **Hoàn toàn độc lập:** Không cần GmsCore hay dịch vụ Google Play vẫn hoạt động đầy đủ tính năng.
4. **Tự động cập nhật:** Ứng dụng tự động kiểm tra phiên bản mới từ GitHub Releases khi khởi động.
"""

    url = f"https://api.github.com/repos/{REPO}/releases/{release_id}"
    payload = json.dumps({
        "name": title,
        "body": body,
        "draft": False,
        "prerelease": False
    }, ensure_ascii=False).encode('utf-8')

    req_headers = dict(HEADERS)
    req_headers["Content-Type"] = "application/json; charset=utf-8"

    req = urllib.request.Request(url, data=payload, headers=req_headers, method="PATCH")
    with urllib.request.urlopen(req) as resp:
        res = json.loads(resp.read().decode('utf-8'))
        print(f"[+] Updated release {release_id} successfully!")
        print(f"[+] New Title: {res.get('name')}")

def main():
    releases = list_releases()
    print(f"[*] Found {len(releases)} releases on {REPO}")

    target_v3274_id = None

    for r in releases:
        tag = r.get("tag_name", "")
        rid = r.get("id")
        name = r.get("name", "")
        print(f" - Found: {tag} (ID: {rid}, Name: {name})")
        
        if tag == "v32.74":
            target_v3274_id = rid
        else:
            # Delete old releases
            delete_release(rid, tag)

    if target_v3274_id:
        update_v3274_release(target_v3274_id)
    else:
        print("[-] Target release v32.74 not found!")

if __name__ == "__main__":
    main()
