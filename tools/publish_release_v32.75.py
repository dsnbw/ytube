#!/usr/bin/env python3
"""
Create GitHub Release v32.75, clean old releases, and upload all APK and IPA installer assets.
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
TAG = 'v32.75'
NAME = 'ytube & SmartTube v32.75 (Bản Nâng Cấp Tương Thích & Giao Diện Chuẩn Google)'

BODY = '''## 🌟 ytube & SmartTube v32.75 — Bản Nâng Cấp Giao Diện Chuẩn Google YouTube Đa Nền Tảng

Bản phát hành chính thức **v32.75** tối ưu hóa toàn diện hiệu năng, sửa lỗi xác thực tài khoản Google, hỗ trợ đồng thời mọi thế hệ thiết bị iPhone, iPad, Điện thoại Android, Tablet và Smart TV.

---

### 📱 1. DÀNH CHO ĐIỆN THOẠI & MÁY TÍNH BẢNG ANDROID (Touch UI Chuẩn YouTube Gốc)
> *Giao diện cảm ứng 100% chuẩn YouTube Mobile & Tablet: Thanh điều hướng đáy (Trang chủ, Shorts, Đăng ký, Bạn), bộ lọc chủ đề, khung xem video đầy đủ nút Like, Chia sẻ, Tải xuống, Đăng ký kênh. Hoạt động độc lập KHÔNG CẦN GmsCore.*

- **[SmartTube_v32.75_Mobile_Phone_Tablet.apk](https://github.com/dsnbw/ytube/releases/download/v32.75/SmartTube_v32.75_Mobile_Phone_Tablet.apk)** *(38.32 MB)*: Bản khuyên dùng số 1 cho mọi dòng điện thoại & máy tính bảng Android (Samsung, Xiaomi, Oppo, Vivo, Realme, Google Pixel...).
- **[SmartTube_v32.75_Mobile_arm64.apk](https://github.com/dsnbw/ytube/releases/download/v32.75/SmartTube_v32.75_Mobile_arm64.apk)** *(28.09 MB)*: Bản tối ưu dung lượng cho máy 64-bit đời mới.
- **[SmartTube_v32.75_Mobile_armeabi-v7a.apk](https://github.com/dsnbw/ytube/releases/download/v32.75/SmartTube_v32.75_Mobile_armeabi-v7a.apk)** *(26.39 MB)*: Bản tương thích cho điện thoại & máy tính bảng 32-bit đời cũ (Android 4.4 – 9.0).

---

### 📺 2. DÀNH CHO SMART TV & ANDROID TV BOX (Giao Diện Điều Khiển Remote D-Pad)
> *Giao diện Leanback hàng ngang chuyên dụng cho màn hình lớn 4K/8K, điều khiển bằng Remote tiện lợi.*

- **[SmartTube_v32.75_TV_Box_Universal.apk](https://github.com/dsnbw/ytube/releases/download/v32.75/SmartTube_v32.75_TV_Box_Universal.apk)** *(33.75 MB)*: Bản phổ thông cho mọi Smart TV (Sony, TCL, Xiaomi, Casper...) và TV Box (Mi Box, Chromecast with Google TV, Fire TV, Tanix, Eweat...).
- **[SmartTube_v32.75_TV_arm64.apk](https://github.com/dsnbw/ytube/releases/download/v32.75/SmartTube_v32.75_TV_arm64.apk)** *(25.49 MB)*: Bản cho Smart TV 64-bit hiện đại.
- **[SmartTube_v32.75_TV_armeabi-v7a.apk](https://github.com/dsnbw/ytube/releases/download/v32.75/SmartTube_v32.75_TV_armeabi-v7a.apk)** *(24.34 MB)*: Bản cho TV Box chip 32-bit đời cũ.
- **[SmartTube_v32.75_TV_x86.apk](https://github.com/dsnbw/ytube/releases/download/v32.75/SmartTube_v32.75_TV_x86.apk)** *(27.08 MB)*: Dành cho máy giả lập Android trên PC hoặc chip Intel.

---

### 🍏 3. DÀNH CHO IPHONE & IPAD (iOS / iPadOS)
> *Đã kiểm tra và chạy thực tế trên iPhone 6s Plus (iOS 15.8.8) và các dòng iPhone/iPad hiện đại. Hỗ trợ cài trực tiếp qua TrollStore hoặc Sideloadly không giới hạn.*

- **[SmartTube-Legacy-iOS12-16.ipa](https://github.com/dsnbw/ytube/releases/download/v32.75/SmartTube-Legacy-iOS12-16.ipa)** *(113.16 MB)*: Tối ưu cho iPad và iPhone (iOS 12.0 đến 16.7), đã xử lý tương thích nhị phân ARM64, chống văng bộ nhớ Jetsam.
- **[SmartTube-Modern-iOS17+.ipa](https://github.com/dsnbw/ytube/releases/download/v32.75/SmartTube-Modern-iOS17+.ipa)** *(113.16 MB)*: Dành cho iPhone và iPad đời mới (iOS 17.0 – iOS 18.0+).

---

### ✨ Hướng Dẫn Đăng Nhập Tài Khoản Google Không Bị Báo Lỗi:
1. **Trên iPhone / iPad:**
   - **Cách 1 (Khuyên dùng - Cài qua TrollStore):** Cài đặt file IPA trực tiếp trong TrollStore. TrollStore giữ nguyên mã định danh Bundle ID gốc `com.google.ios.youtube`, giúp Google nhận diện là ứng dụng chính chủ và cho phép đăng nhập tức thì không gặp lỗi.
   - **Cách 2 (Khi cài qua Sideloadly):** Mở app YouTube -> vào **Cài đặt (Settings)** -> chọn **uYouEnhanced** -> cuộn xuống phần **Miscellaneous** -> Bật công tắc **`YouTube Sign-in Patch`** (hoặc `Fix Google Sign-in`) -> Vuốt tắt app YouTube trong đa nhiệm rồi mở lại -> Bấm Đăng nhập tài khoản Google bình thường -> Sau khi đăng nhập xong, tắt lại công tắc về OFF để đảm bảo ứng dụng chạy mượt mà nhất.
2. **Trên Smart TV & Android Box:**
   - Vào mục Tài khoản -> Thêm tài khoản -> Mở trình duyệt trên máy tính/điện thoại truy cập link `youtube.com/activate` và nhập mã trên màn hình TV để liên kết tài khoản 100% an toàn.
'''

def delete_old_releases():
    headers = {
        "Authorization": f"token {TOKEN}",
        "Accept": "application/vnd.github.v3+json",
        "User-Agent": "SmartTubeReleaseUploader"
    }
    list_url = f"https://api.github.com/repos/{REPO}/releases"
    req = urllib.request.Request(list_url, headers=headers)
    try:
        with urllib.request.urlopen(req) as resp:
            releases = json.loads(resp.read().decode('utf-8'))
            for r in releases:
                if r['tag_name'] != TAG:
                    r_id = r['id']
                    tag_name = r['tag_name']
                    print(f"[*] Deleting old release {tag_name} (ID: {r_id})...")
                    del_url = f"https://api.github.com/repos/{REPO}/releases/{r_id}"
                    del_req = urllib.request.Request(del_url, headers=headers, method="DELETE")
                    urllib.request.urlopen(del_req)
                    print(f"[+] Deleted release {tag_name}")
    except Exception as e:
        print(f"[-] Note on cleaning old releases: {e}")

def main():
    print(f"[*] Cleaning older releases so only {TAG} remains...")
    delete_old_releases()

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
