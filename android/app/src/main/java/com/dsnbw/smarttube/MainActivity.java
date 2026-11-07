package com.dsnbw.smarttube;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Dark theme background matching native YouTube Dark Mode
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setBackgroundColor(Color.parseColor("#0F0F0F"));
        root.setGravity(Gravity.CENTER);
        root.setPadding(40, 40, 40, 40);

        // YouTube Branding
        TextView title = new TextView(this);
        title.setText("SmartTube Multiplatform");
        title.setTextColor(Color.WHITE);
        title.setTextSize(26);
        title.setGravity(Gravity.CENTER);
        root.addView(title);

        TextView subtitle = new TextView(this);
        subtitle.setText("Giao diện nguyên bản YouTube • 100% Ad-Free • SponsorBlock • Auto-Update");
        subtitle.setTextColor(Color.parseColor("#AAAAAA"));
        subtitle.setTextSize(14);
        subtitle.setGravity(Gravity.CENTER);
        subtitle.setPadding(0, 16, 0, 32);
        root.addView(subtitle);

        // Check for updates button
        Button btnUpdate = new Button(this);
        btnUpdate.setText("Kiểm tra bản cập nhật mới (GitHub)");
        btnUpdate.setTextColor(Color.WHITE);
        btnUpdate.setBackgroundColor(Color.parseColor("#CC0000"));
        btnUpdate.setPadding(32, 16, 32, 16);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GitHubUpdateChecker.checkForUpdates(MainActivity.this, true);
            }
        });
        root.addView(btnUpdate);

        setContentView(root);

        // Automatically check for GitHub updates silently in the background on startup
        GitHubUpdateChecker.checkForUpdates(this, false);
    }
}
