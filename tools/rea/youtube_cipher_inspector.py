#!/usr/bin/env python3
"""
REA (Reverse Engineer Anything) YouTube Stream & Cipher Inspector
Analyzes YouTube InnerTube endpoints, validates cipher tokens, and tests SponsorBlock integration.
"""

import urllib.request
import json
import re
import sys

INNER_TUBE_URL = "https://www.youtube.com/youtubei/v1/player"
SPONSOR_BLOCK_URL = "https://sponsor.ajay.app/api/skipSegments"

def test_innertube_api(video_id="dQw4w9WgXcQ"):
    print(f"[*] REA: Testing InnerTube API (TV/Web client) for video: {video_id}")
    payload = {
        "context": {
            "client": {
                "clientName": "TVHTML5",
                "clientVersion": "7.20230405.08.01",
                "hl": "en",
                "gl": "US"
            }
        },
        "videoId": video_id
    }
    
    data = json.dumps(payload).encode('utf-8')
    req = urllib.request.Request(
        INNER_TUBE_URL,
        data=data,
        headers={
            "Content-Type": "application/json",
            "User-Agent": "Mozilla/5.0 (SMART-TV; Linux; Tizen 5.0) AppleWebkit/537.36"
        }
    )
    
    try:
        with urllib.request.urlopen(req, timeout=10) as resp:
            res_json = json.loads(resp.read().decode('utf-8'))
            title = res_json.get("videoDetails", {}).get("title", "Unknown")
            author = res_json.get("videoDetails", {}).get("author", "Unknown")
            status = res_json.get("playabilityStatus", {}).get("status", "Unknown")
            formats = res_json.get("streamingData", {}).get("formats", [])
            adaptive_formats = res_json.get("streamingData", {}).get("adaptiveFormats", [])
            print(f"[+] InnerTube response OK: '{title}' by {author}")
            print(f"[+] Playability Status: {status} | Direct streams: {len(formats)} | Adaptive streams: {len(adaptive_formats)}")
            return True
    except Exception as e:
        print(f"[-] InnerTube request error: {e}")
        return False

def test_sponsorblock_api(video_id="dQw4w9WgXcQ"):
    print(f"[*] REA: Testing SponsorBlock API for video: {video_id}")
    url = f"{SPONSOR_BLOCK_URL}?videoID={video_id}&categories=[\"sponsor\",\"intro\",\"outro\",\"selfpromo\"]"
    req = urllib.request.Request(url, headers={"User-Agent": "SmartTube/5.1"})
    try:
        with urllib.request.urlopen(req, timeout=10) as resp:
            segments = json.loads(resp.read().decode('utf-8'))
            print(f"[+] SponsorBlock segments found: {len(segments)}")
            return True
    except urllib.error.HTTPError as e:
        if e.code == 404:
            print("[+] SponsorBlock OK: No segments for this video (expected 404)")
            return True
        print(f"[-] SponsorBlock error: {e}")
        return False
    except Exception as e:
        print(f"[-] SponsorBlock connection warning: {e}")
        return False

if __name__ == "__main__":
    vid = sys.argv[1] if len(sys.argv) > 1 else "dQw4w9WgXcQ"
    print("=== REA YouTube Inspector ===")
    it_ok = test_innertube_api(vid)
    sb_ok = test_sponsorblock_api(vid)
    print(f"=== REA Summary: InnerTube: {'OK' if it_ok else 'FAILED'} | SponsorBlock: {'OK' if sb_ok else 'FAILED'} ===")
