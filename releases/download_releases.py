#!/usr/bin/env python3
"""
SmartTube & Official YouTube Release Downloader
Downloads official installer packages (.ipa and .apk) directly from GitHub Releases.
"""

import os
import sys
import urllib.request
import json

REPO = "dsnbw/ytube"
API_URL = f"https://api.github.com/repos/{REPO}/releases/latest"

def download_assets():
    print(f"[*] Đang lấy danh sách các bản cài đặt từ {REPO}...")
    req = urllib.request.Request(API_URL, headers={"User-Agent": "SmartTubeDownloader"})
    try:
        with urllib.request.urlopen(req) as resp:
            data = json.loads(resp.read().decode('utf-8'))
            tag = data.get("tag_name", "latest")
            print(f"[+] Tìm thấy phiên bản phát hành: {tag}")
            assets = data.get("assets", [])
            if not assets:
                print("[-] Chưa tìm thấy file cài đặt nào trong bản phát hành.")
                return

            for a in assets:
                name = a["name"]
                url = a["browser_download_url"]
                size_mb = a["size"] / (1024 * 1024)
                print(f"[*] Đang tải {name} ({size_mb:.2f} MB)...")
                urllib.request.urlretrieve(url, name)
                print(f"[+] Đã tải xong: {name}")

    except Exception as e:
        print(f"[-] Lỗi khi tải bản cài đặt: {e}")

if __name__ == "__main__":
    download_assets()
