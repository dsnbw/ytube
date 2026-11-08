#!/usr/bin/env python3
"""
Create GitHub Release v32.74 and upload all APK and IPA installer assets.
"""

import os
import sys
import json
import urllib.request
import urllib.error

TOKEN = os.environ.get('GITHUB_TOKEN')
if not TOKEN and os.path.exists('github.md'):
    with open('github.md', 'r') as f:
        for line in f:
            if 'ghp_' in line:
                import re
                m = re.search(r'ghp_[a-zA-Z0-9]+', line)
                if m:
                    TOKEN = m.group(0)
                    break
REPO = 'dsnbw/ytube'
TAG = 'v32.74'
NAME = 'ytube & SmartTube v32.74 (Bản Nâng Cấp Tương Thích & Giao Diện Gốc)'

BODY = '''## 🌟 ytube & SmartTube v32.74 — Bản Nâng Cấp Giao Diện Chuẩn Google YouTube Đa Nền Tảng

Bản phát hành chính thức **v32.74** tối ưu hóa hiệu năng, cập nhật giao diện chuẩn YouTube nguyên bản từ Google và sửa các lỗi hiển thị lệch vỡ trên mọi kích thước màn hình.

---

### 📱 1. DÀNH CHO ĐIỆN THOẠI & MÁY TÍNH BẢNG ANDROID (Touch UI Chuẩn YouTube Gốc)
> *Giao diện cảm ứng 100% chuẩn YouTube Mobile & Tablet: Thanh điều hướng đáy (Trang chủ, Shorts, Đăng ký, Bạn), bộ lọc chủ đề, khung xem video đầy đủ nút Like, Chia sẻ, Tải xuống, Đăng ký kênh. Hoạt động độc lập KHÔNG CẦN GmsCore.*

- **[SmartTube_v32.74_Mobile_Phone_Tablet.apk](https://github.com/dsnbw/ytube/releases/download/v32.74/SmartTube_v32.74_Mobile_Phone_Tablet.apk)** *(38.32 MB)*: Bản khuyên dùng số 1 cho mọi dòng điện thoại & máy tính bảng Android (Samsung, Xiaomi, Oppo, Vivo, Realme, Google Pixel...).
- **[SmartTube_v32.74_Mobile_arm64.apk](https://github.com/dsnbw/ytube/releases/download/v32.74/SmartTube_v32.74_Mobile_arm64.apk)** *(28.09 MB)*: Bản tối ưu dung lượng cho máy 64-bit đời mới.
- **[SmartTube_v32.74_Mobile_armeabi-v7a.apk](https://github.com/dsnbw/ytube/releases/download/v32.74/SmartTube_v32.74_Mobile_armeabi-v7a.apk)** *(26.39 MB)*: Bản tương thích cho điện thoại & máy tính bảng 32-bit đời cũ (Android 4.4 – 9.0).

---

### 📺 2. DÀNH CHO SMART TV & ANDROID TV BOX (Giao Diện Điều Khiển Remote D-Pad)
> *Giao diện Leanback hàng ngang chuyên dụng cho màn hình lớn 4K/8K, điều khiển bằng Remote tiện lợi.*

- **[SmartTube_v32.74_TV_Box_Universal.apk](https://github.com/dsnbw/ytube/releases/download/v32.74/SmartTube_v32.74_TV_Box_Universal.apk)** *(33.75 MB)*: Bản phổ thông cho mọi Smart TV (Sony, TCL, Xiaomi, Casper...) và TV Box (Mi Box, Chromecast with Google TV, Fire TV, Tanix, Eweat...).
- **[SmartTube_v32.74_TV_arm64.apk](https://github.com/dsnbw/ytube/releases/download/v32.74/SmartTube_v32.74_TV_arm64.apk)** *(25.49 MB)*: Bản cho Smart TV 64-bit hiện đại.
- **[SmartTube_v32.74_TV_armeabi-v7a.apk](https://github.com/dsnbw/ytube/releases/download/v32.74/SmartTube_v32.74_TV_armeabi-v7a.apk)** *(24.34 MB)*: Bản cho TV Box chip 32-bit đời cũ.
- **[SmartTube_v32.74_TV_x86.apk](https://github.com/dsnbw/ytube/releases/download/v32.74/SmartTube_v32.74_TV_x86.apk)** *(27.08 MB)*: Dành cho máy giả lập Android trên PC hoặc chip Intel.

---

### 🍏 3. DÀNH CHO IPHONE & IPAD (iOS / iPadOS)
> *Tự động nhận diện thiết bị: Hiển thị 1 cột trên iPhone, thích ứng đa cột mượt mà trên iPad. Tương thích từ iOS 12.0 đến iOS 18+.*

- **[SmartTube-Legacy-iOS12-16.ipa](https://github.com/dsnbw/ytube/releases/download/v32.74/SmartTube-Legacy-iOS12-16.ipa)** *(1.00 MB)*: Tối ưu cho iPad và iPhone đời cũ (iOS 12.0 đến 16.7), hạn chế tràn RAM cho máy 1GB - 2GB RAM.
- **[SmartTube-Modern-iOS17+.ipa](https://github.com/dsnbw/ytube/releases/download/v32.74/SmartTube-Modern-iOS17.ipa)** *(0.99 MB)*: Dành cho iPhone và iPad đời mới (iOS 17.0 – iOS 18.0+).

---

### ✨ Tính Năng Nổi Bật Trên v32.74:
1. **Nâng cấp phiên bản v32.74:** Đồng bộ hóa mã phiên bản trên toàn hệ thống build và cơ chế tự động cập nhật GitHub Releases.
2. **Giao diện chuẩn Google YouTube:** Tự động nhận diện kích thước màn hình theo thời gian thực để hiển thị giao diện phù hợp nhất, chống lệch vỡ trên mọi độ phân giải.
3. **Hoạt động độc lập 100%:** Không cần GmsCore, MicroG hay dịch vụ Google Play vẫn xem video mượt mà 4K/60fps.
4. **Chặn sạch 100% quảng cáo & SponsorBlock:** Tự động loại bỏ mọi quảng cáo và tua nhanh đoạn tài trợ.
'''

def main():
    print(f"[*] Creating release {TAG} on {REPO}...")
    create_url = f"https://api.github.com/repos/{REPO}/releases"
    headers = {
        "Authorization": f"token {TOKEN}",
        "Accept": "application/vnd.github.v3+json",
        "User-Agent": "SmartTubeReleaseUploader"
    }
    
    # Check if release exists
    try:
        req = urllib.request.Request(f"https://api.github.com/repos/{REPO}/releases/tags/{TAG}", headers=headers)
        with urllib.request.urlopen(req) as resp:
            existing = json.loads(resp.read().decode('utf-8'))
            release_id = existing['id']
            upload_url = existing['upload_url'].split('{')[0]
            print(f"[+] Release {TAG} already exists (ID: {release_id})")
    except urllib.error.HTTPError as e:
        if e.code == 404:
            payload = json.dumps({
                "tag_name": TAG,
                "target_commitish": "main",
                "name": NAME,
                "body": BODY,
                "draft": False,
                "prerelease": False
            }).encode('utf-8')
            req = urllib.request.Request(create_url, data=payload, headers=headers, method="POST")
            with urllib.request.urlopen(req) as resp:
                created = json.loads(resp.read().decode('utf-8'))
                release_id = created['id']
                upload_url = created['upload_url'].split('{')[0]
                print(f"[+] Release {TAG} created successfully (ID: {release_id})")
        else:
            raise

    # Upload files from e:\ytube\releases
    rel_dir = r"e:\ytube\releases"
    files = [f for f in os.listdir(rel_dir) if f.endswith('.apk') or f.endswith('.ipa')]
    print(f"[*] Found {len(files)} installer assets to upload: {files}")

    for filename in sorted(files):
        filepath = os.path.join(rel_dir, filename)
        filesize = os.path.getsize(filepath)
        size_mb = filesize / (1024 * 1024)
        print(f"[*] Uploading {filename} ({size_mb:.2f} MB)...")
        
        # Determine content type
        content_type = "application/vnd.android.package-archive" if filename.endswith('.apk') else "application/octet-stream"
        
        target_upload_url = f"{upload_url}?name={filename}"
        with open(filepath, 'rb') as f:
            data = f.read()

        up_headers = {
            "Authorization": f"token {TOKEN}",
            "Content-Type": content_type,
            "User-Agent": "SmartTubeReleaseUploader",
            "Content-Length": str(len(data))
        }

        try:
            up_req = urllib.request.Request(target_upload_url, data=data, headers=up_headers, method="POST")
            with urllib.request.urlopen(up_req) as resp:
                result = json.loads(resp.read().decode('utf-8'))
                print(f"[+] Uploaded successfully: {result['name']} (ID: {result['id']})")
        except urllib.error.HTTPError as e:
            err_body = e.read().decode('utf-8')
            if "already_exists" in err_body:
                print(f"[!] Asset {filename} already exists on release.")
            else:
                print(f"[-] Failed uploading {filename}: {e.code} - {err_body}")

    print(f"\n[+] All release assets for {TAG} processed successfully!")

if __name__ == "__main__":
    main()
