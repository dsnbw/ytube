#!/usr/bin/env python3
"""
SmartTube Release Downloader
Quickly downloads official installer packages (.ipa and .apk) from GitHub Releases.
"""

import os
import sys
import urllib.request
import json

REPO = "dsnbw/ytube"
API_URL = f"https://api.github.com/repos/{REPO}/releases/latest"

def download_assets():
    print(f"[*] Fetching latest release information from {REPO}...")
    req = urllib.request.Request(API_URL, headers={"User-Agent": "SmartTubeDownloader"})
    try:
        with urllib.request.urlopen(req) as resp:
            data = json.loads(resp.read().decode('utf-8'))
            tag = data.get("tag_name", "latest")
            print(f"[+] Found release: {tag}")
            assets = data.get("assets", [])
            if not assets:
                print("[-] No assets found in the release yet.")
                return

            for a in assets:
                name = a["name"]
                url = a["browser_download_url"]
                size_mb = a["size"] / (1024 * 1024)
                print(f"[*] Downloading {name} ({size_mb:.2f} MB)...")
                urllib.request.urlretrieve(url, name)
                print(f"[+] Downloaded: {name}")

    except Exception as e:
        print(f"[-] Error downloading releases: {e}")

if __name__ == "__main__":
    download_assets()
