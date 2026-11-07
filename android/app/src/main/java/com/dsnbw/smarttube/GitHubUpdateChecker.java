package com.dsnbw.smarttube;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GitHubUpdateChecker {
    private static final String TAG = "SmartTubeUpdater";
    private static final String GITHUB_API_URL = "https://api.github.com/repos/dsnbw/ytube/releases/latest";
    private static final String CURRENT_VERSION = "32.72";

    public interface UpdateCallback {
        void onCheckFinished(boolean updateAvailable, String latestVersion, String downloadUrl);
    }

    public static void checkForUpdates(final Activity activity, final boolean showDialogIfLatest) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(GITHUB_API_URL);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestProperty("User-Agent", "SmartTubeAndroid/" + CURRENT_VERSION);
                    conn.setConnectTimeout(8000);
                    conn.setReadTimeout(8000);

                    if (conn.getResponseCode() == 200) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        StringBuilder sb = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            sb.append(line);
                        }
                        reader.close();

                        JSONObject json = new JSONObject(sb.toString());
                        final String tagName = json.optString("tag_name", "");
                        final String cleanRemote = tagName.replace("v", "").trim();
                        final String releaseNotes = json.optString("body", "");

                        String apkUrl = "";
                        JSONArray assets = json.optJSONArray("assets");
                        if (assets != null) {
                            for (int i = 0; i < assets.length(); i++) {
                                JSONObject asset = assets.getJSONObject(i);
                                String name = asset.optString("name", "");
                                if (name.endsWith(".apk") && name.contains("universal")) {
                                    apkUrl = asset.optString("browser_download_url", "");
                                    break;
                                }
                            }
                            // Fallback to any APK if universal not found
                            if (apkUrl.isEmpty() && assets.length() > 0) {
                                for (int i = 0; i < assets.length(); i++) {
                                    JSONObject asset = assets.getJSONObject(i);
                                    String name = asset.optString("name", "");
                                    if (name.endsWith(".apk")) {
                                        apkUrl = asset.optString("browser_download_url", "");
                                        break;
                                    }
                                }
                            }
                        }

                        final String finalDownloadUrl = apkUrl;
                        final boolean isNewer = compareVersions(cleanRemote, CURRENT_VERSION) > 0;

                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                if (activity == null || activity.isFinishing()) return;

                                if (isNewer && !finalDownloadUrl.isEmpty()) {
                                    showUpdateDialog(activity, tagName, releaseNotes, finalDownloadUrl);
                                } else if (showDialogIfLatest) {
                                    new AlertDialog.Builder(activity)
                                            .setTitle("SmartTube")
                                            .setMessage("Bạn đang sử dụng phiên bản mới nhất (" + CURRENT_VERSION + ").")
                                            .setPositiveButton("OK", null)
                                            .show();
                                }
                            }
                        });
                    }
                } catch (Exception e) {
                    Log.e(TAG, "Error checking update", e);
                }
            }
        }).start();
    }

    private static void showUpdateDialog(final Activity activity, final String version, String notes, final String downloadUrl) {
        new AlertDialog.Builder(activity)
                .setTitle("Cập nhật SmartTube (" + version + ")")
                .setMessage("Đã có phiên bản mới với nhiều cải tiến và sửa lỗi giao diện.\n\nBạn có muốn tải và cài đặt ngay không?")
                .setPositiveButton("Tải & Cài đặt", (dialog, which) -> {
                    startDownload(activity, downloadUrl, "SmartTube_" + version + ".apk");
                })
                .setNegativeButton("Để sau", null)
                .show();
    }

    public static void startDownload(Context context, String url, String fileName) {
        try {
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
            request.setTitle("Đang tải SmartTube...");
            request.setDescription("Phiên bản mới từ GitHub");
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);

            DownloadManager manager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
            if (manager != null) {
                manager.enqueue(request);
            }
        } catch (Exception e) {
            // Fallback to browser
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            context.startActivity(intent);
        }
    }

    private static int compareVersions(String v1, String v2) {
        String[] parts1 = v1.split("\\.");
        String[] parts2 = v2.split("\\.");
        int length = Math.max(parts1.length, parts2.length);
        for (int i = 0; i < length; i++) {
            int p1 = i < parts1.length ? Integer.parseInt(parts1[i].replaceAll("\\D+", "0")) : 0;
            int p2 = i < parts2.length ? Integer.parseInt(parts2[i].replaceAll("\\D+", "0")) : 0;
            if (p1 < p2) return -1;
            if (p1 > p2) return 1;
        }
        return 0;
    }
}
