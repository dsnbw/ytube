package com.dsnbw.smarttube;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends Activity {
    private DeviceFormFactorDetector.FormFactor currentFormFactor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Auto-detect whether device is Phone, Tablet, or TV
        currentFormFactor = DeviceFormFactorDetector.detect(this);

        // Render appropriate UI corresponding to the device
        renderUI();

        // Check for updates in background
        GitHubUpdateChecker.checkForUpdates(this, false);
    }

    private void renderUI() {
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setBackgroundColor(Color.parseColor("#0F0F0F")); // Native YouTube Dark Theme
        root.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        // 1. Top Header Bar (YouTube Branding & Actions)
        LinearLayout header = new LinearLayout(this);
        header.setOrientation(LinearLayout.HORIZONTAL);
        header.setPadding(32, 24, 32, 24);
        header.setGravity(Gravity.CENTER_VERTICAL);
        header.setBackgroundColor(Color.parseColor("#0F0F0F"));

        // Red Play Icon Box
        TextView redIcon = new TextView(this);
        redIcon.setText(" ▶ ");
        redIcon.setTextColor(Color.WHITE);
        redIcon.setBackgroundColor(Color.parseColor("#FF0000"));
        redIcon.setTextSize(14);
        header.addView(redIcon);

        TextView appTitle = new TextView(this);
        appTitle.setText(" SmartTube");
        appTitle.setTextColor(Color.WHITE);
        appTitle.setTextSize(20);
        appTitle.setTypeface(null, android.graphics.Typeface.BOLD);
        header.addView(appTitle);

        TextView modeBadge = new TextView(this);
        modeBadge.setText(" [" + currentFormFactor.name() + " UI] ");
        modeBadge.setTextColor(Color.parseColor("#AAAAAA"));
        modeBadge.setTextSize(12);
        header.addView(modeBadge);

        View spacer = new View(this);
        header.addView(spacer, new LinearLayout.LayoutParams(0, 0, 1.0f));

        Button switchModeBtn = new Button(this);
        switchModeBtn.setText("Đổi Giao diện");
        switchModeBtn.setTextSize(11);
        switchModeBtn.setTextColor(Color.WHITE);
        switchModeBtn.setBackgroundColor(Color.parseColor("#272727"));
        switchModeBtn.setOnClickListener(v -> toggleFormFactor());
        header.addView(switchModeBtn);

        root.addView(header);

        // 2. Horizontal Topic Chips (Filter Pills)
        HorizontalScrollView chipScroll = new HorizontalScrollView(this);
        chipScroll.setHorizontalScrollBarEnabled(false);
        LinearLayout chipContainer = new LinearLayout(this);
        chipContainer.setOrientation(LinearLayout.HORIZONTAL);
        chipContainer.setPadding(24, 8, 24, 16);

        String[] topics = {"Tất cả", "Âm nhạc", "Trò chơi", "Tin tức", "Trực tiếp", "Podcasts", "Mới tải lên", "Đã xem"};
        for (int i = 0; i < topics.length; i++) {
            TextView chip = new TextView(this);
            chip.setText(topics[i]);
            chip.setTextColor(i == 0 ? Color.BLACK : Color.WHITE);
            chip.setBackgroundColor(i == 0 ? Color.WHITE : Color.parseColor("#272727"));
            chip.setPadding(28, 14, 28, 14);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0, 0, 16, 0);
            chip.setLayoutParams(lp);
            chipContainer.addView(chip);
        }
        chipScroll.addView(chipContainer);
        root.addView(chipScroll);

        // 3. Scrollable Feed Area (Phone = 1-col full width, Tablet = 2-col, TV = shelf rows)
        ScrollView feedScroll = new ScrollView(this);
        LinearLayout.LayoutParams feedLp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 1.0f);
        feedScroll.setLayoutParams(feedLp);

        LinearLayout feedContainer = new LinearLayout(this);
        feedContainer.setOrientation(LinearLayout.VERTICAL);
        feedContainer.setPadding(24, 0, 24, 24);

        if (currentFormFactor == DeviceFormFactorDetector.FormFactor.PHONE) {
            // Authentic Phone Feed (Single Column Full-width Cards)
            for (int i = 1; i <= 3; i++) {
                feedContainer.addView(createVideoCard(
                        "Video Trải Nghiệm Chuẩn YouTube Mobile #" + i,
                        "Kênh SmartTube Chính Thức",
                        "125 N lượt xem • 2 ngày trước"
                ));
            }
        } else if (currentFormFactor == DeviceFormFactorDetector.FormFactor.TABLET) {
            // Tablet Grid Feed (2 columns)
            TextView tabletNotice = new TextView(this);
            tabletNotice.setText("Bố Cục Máy Tính Bảng (Tablet): Đa cột cân đối, thanh điều hướng rộng");
            tabletNotice.setTextColor(Color.LTGRAY);
            tabletNotice.setPadding(0, 8, 0, 16);
            feedContainer.addView(tabletNotice);

            for (int i = 1; i <= 4; i++) {
                feedContainer.addView(createVideoCard("Video Tablet #" + i, "Kênh Sáng Tạo", "89 N lượt xem"));
            }
        } else {
            // TV Leanback Shelf Feed
            TextView tvNotice = new TextView(this);
            tvNotice.setText("Bố Cục Tivi (Android TV / TV Box): Dành cho điều khiển Remote / D-pad");
            tvNotice.setTextColor(Color.LTGRAY);
            tvNotice.setPadding(0, 8, 0, 16);
            feedContainer.addView(tvNotice);

            for (int i = 1; i <= 3; i++) {
                feedContainer.addView(createVideoCard("Video TV Box #" + i, "Kênh Truyền Hình 4K", "450 N lượt xem"));
            }
        }

        feedScroll.addView(feedContainer);
        root.addView(feedScroll);

        // 4. Bottom Navigation Bar (Authentic YouTube Mobile / Tablet Touch Bar)
        if (currentFormFactor == DeviceFormFactorDetector.FormFactor.PHONE ||
            currentFormFactor == DeviceFormFactorDetector.FormFactor.TABLET) {
            LinearLayout bottomBar = new LinearLayout(this);
            bottomBar.setOrientation(LinearLayout.HORIZONTAL);
            bottomBar.setBackgroundColor(Color.parseColor("#0F0F0F"));
            bottomBar.setPadding(0, 16, 0, 20);

            String[] navItems = {"🏠 Trang chủ", "⚡ Shorts", "📺 Đăng ký", "👤 Bạn"};
            for (String item : navItems) {
                TextView tab = new TextView(this);
                tab.setText(item);
                tab.setTextColor(Color.LTGRAY);
                tab.setTextSize(12);
                tab.setGravity(Gravity.CENTER);
                LinearLayout.LayoutParams tabLp = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);
                tab.setLayoutParams(tabLp);
                bottomBar.addView(tab);
            }
            root.addView(bottomBar);
        }

        setContentView(root);
    }

    private View createVideoCard(String title, String channel, String metadata) {
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(0, 0, 0, 36);

        // 16:9 Thumbnail Mockup
        TextView thumb = new TextView(this);
        thumb.setText("▶ 16:9 THUMBNAIL HD (1080p/4K)");
        thumb.setTextColor(Color.GRAY);
        thumb.setGravity(Gravity.CENTER);
        thumb.setBackgroundColor(Color.parseColor("#1F1F1F"));
        int thumbHeight = currentFormFactor == DeviceFormFactorDetector.FormFactor.PHONE ? 520 : 380;
        thumb.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, thumbHeight));
        card.addView(thumb);

        // Details Row (Avatar + Text + 3-dots)
        LinearLayout detailsRow = new LinearLayout(this);
        detailsRow.setOrientation(LinearLayout.HORIZONTAL);
        detailsRow.setPadding(0, 18, 0, 0);
        detailsRow.setGravity(Gravity.TOP);

        // Circle Avatar
        TextView avatar = new TextView(this);
        avatar.setText("ST");
        avatar.setTextColor(Color.WHITE);
        avatar.setGravity(Gravity.CENTER);
        avatar.setBackgroundColor(Color.parseColor("#333333"));
        avatar.setLayoutParams(new LinearLayout.LayoutParams(90, 90));
        detailsRow.addView(avatar);

        // Title and Metadata
        LinearLayout textCol = new LinearLayout(this);
        textCol.setOrientation(LinearLayout.VERTICAL);
        textCol.setPadding(24, 0, 12, 0);
        LinearLayout.LayoutParams textColLp = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);
        textCol.setLayoutParams(textColLp);

        TextView titleView = new TextView(this);
        titleView.setText(title);
        titleView.setTextColor(Color.WHITE);
        titleView.setTextSize(15);
        titleView.setTypeface(null, android.graphics.Typeface.BOLD);
        textCol.addView(titleView);

        TextView metaView = new TextView(this);
        metaView.setText(channel + " • " + metadata);
        metaView.setTextColor(Color.parseColor("#AAAAAA"));
        metaView.setTextSize(12);
        metaView.setPadding(0, 6, 0, 0);
        textCol.addView(metaView);

        detailsRow.addView(textCol);

        TextView dots = new TextView(this);
        dots.setText(" ⋮ ");
        dots.setTextColor(Color.GRAY);
        dots.setTextSize(18);
        detailsRow.addView(dots);

        card.addView(detailsRow);
        return card;
    }

    private void toggleFormFactor() {
        if (currentFormFactor == DeviceFormFactorDetector.FormFactor.PHONE) {
            currentFormFactor = DeviceFormFactorDetector.FormFactor.TABLET;
        } else if (currentFormFactor == DeviceFormFactorDetector.FormFactor.TABLET) {
            currentFormFactor = DeviceFormFactorDetector.FormFactor.TV;
        } else {
            currentFormFactor = DeviceFormFactorDetector.FormFactor.PHONE;
        }
        renderUI();
    }
}
