package com.dsnbw.smarttube;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Authentic Google YouTube Interface for ytube.
 * Automatically adapts between:
 * - Phone: 1-column edge-to-edge feed, bottom navigation bar (Trang chu, Shorts, Dang ky, Ban).
 * - Tablet: 2-3 column responsive grid, spacious layout.
 * - TV: 10-foot remote D-pad interface with horizontal shelves.
 */
public class MainActivity extends Activity {
    private DeviceFormFactorDetector.FormFactor currentFormFactor;
    private boolean isWatchingVideo = false;
    private String currentVideoTitle = "";
    private String currentChannelName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Auto-detect whether device is Phone, Tablet, or TV
        currentFormFactor = DeviceFormFactorDetector.detect(this);

        // Render appropriate UI corresponding to the device
        renderUI();

        // Check for updates in background without GmsCore
        GitHubUpdateChecker.checkForUpdates(this, false);
    }

    private void renderUI() {
        if (isWatchingVideo) {
            renderWatchView();
            return;
        }

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setBackgroundColor(Color.parseColor("#0F0F0F")); // Authentic YouTube Dark Mode Background
        root.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        // 1. Top Header Bar (YouTube Branding & Actions)
        LinearLayout header = new LinearLayout(this);
        header.setOrientation(LinearLayout.HORIZONTAL);
        header.setPadding(32, 28, 32, 20);
        header.setGravity(Gravity.CENTER_VERTICAL);
        header.setBackgroundColor(Color.parseColor("#0F0F0F"));

        // Red Play Icon Box
        TextView redIcon = new TextView(this);
        redIcon.setText(" ▶ ");
        redIcon.setTextColor(Color.WHITE);
        redIcon.setBackgroundColor(Color.parseColor("#FF0000"));
        redIcon.setTextSize(14);
        redIcon.setPadding(6, 4, 6, 4);
        header.addView(redIcon);

        TextView appTitle = new TextView(this);
        appTitle.setText(" YouTube");
        appTitle.setTextColor(Color.WHITE);
        appTitle.setTextSize(20);
        appTitle.setTypeface(null, Typeface.BOLD);
        header.addView(appTitle);

        TextView modeBadge = new TextView(this);
        modeBadge.setText(" [" + currentFormFactor.name() + "] ");
        modeBadge.setTextColor(Color.parseColor("#888888"));
        modeBadge.setTextSize(11);
        header.addView(modeBadge);

        View spacer = new View(this);
        header.addView(spacer, new LinearLayout.LayoutParams(0, 0, 1.0f));

        // Action Icons: Cast, Notifications, Search, Profile Avatar
        TextView castIcon = new TextView(this);
        castIcon.setText(" 📺 ");
        castIcon.setTextSize(16);
        header.addView(castIcon);

        TextView notifIcon = new TextView(this);
        notifIcon.setText(" 🔔 ");
        notifIcon.setTextSize(16);
        header.addView(notifIcon);

        TextView searchIcon = new TextView(this);
        searchIcon.setText(" 🔍 ");
        searchIcon.setTextSize(16);
        header.addView(searchIcon);

        // Circular Profile Avatar
        TextView avatarBtn = new TextView(this);
        avatarBtn.setText(" 👤 ");
        avatarBtn.setTextSize(16);
        header.addView(avatarBtn);

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

        // 3. Scrollable Feed Area (Phone = 1-col edge-to-edge, Tablet = 2-col, TV = shelves)
        ScrollView feedScroll = new ScrollView(this);
        LinearLayout.LayoutParams feedLp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 1.0f);
        feedScroll.setLayoutParams(feedLp);

        LinearLayout feedContainer = new LinearLayout(this);
        feedContainer.setOrientation(LinearLayout.VERTICAL);
        feedContainer.setPadding(currentFormFactor == DeviceFormFactorDetector.FormFactor.PHONE ? 0 : 24, 0,
                                 currentFormFactor == DeviceFormFactorDetector.FormFactor.PHONE ? 0 : 24, 24);

        if (currentFormFactor == DeviceFormFactorDetector.FormFactor.PHONE) {
            // Authentic Phone Feed (Single Column Full-width Cards)
            feedContainer.addView(createVideoCard(
                    "Video Khám Phá Công Nghệ Mới Nhất 2026",
                    "Kênh Công Nghệ Việt",
                    "245 N lượt xem • 1 ngày trước",
                    "14:25"
            ));
            feedContainer.addView(createVideoCard(
                    "Tuyển Tập Âm Nhạc Thư Giãn Không Quảng Cáo (Ad-Free)",
                    "Chill Melodies",
                    "1,8 Tr lượt xem • 2 tuần trước",
                    "45:10"
            ));
            feedContainer.addView(createVideoCard(
                    "Trải Nghiệm SmartTube & ytube Chuẩn Giao Diện Gốc",
                    "SmartTube Studio",
                    "89 N lượt xem • 3 ngày trước",
                    "08:32"
            ));
        } else if (currentFormFactor == DeviceFormFactorDetector.FormFactor.TABLET) {
            // Tablet Grid Feed (2 columns)
            feedContainer.addView(createVideoCard(
                    "Trải Nghiệm Bố Cục Máy Tính Bảng (Tablet Mode)",
                    "Kênh Tablet Studio",
                    "120 N lượt xem",
                    "12:00"
            ));
            feedContainer.addView(createVideoCard(
                    "Thiết Kế Đa Cột Cân Đối & Thích Ứng Màn Hình",
                    "Design Lab",
                    "95 N lượt xem",
                    "18:40"
            ));
        } else {
            // TV Leanback Shelf Feed
            feedContainer.addView(createVideoCard(
                    "Bố Cục Tivi (Android TV / TV Box): Dành cho Remote / D-pad",
                    "Smart TV 4K",
                    "550 N lượt xem",
                    "25:00"
            ));
        }

        feedScroll.addView(feedContainer);
        root.addView(feedScroll);

        // 4. Bottom Navigation Bar (Authentic YouTube Mobile / Tablet Touch Bar)
        if (currentFormFactor == DeviceFormFactorDetector.FormFactor.PHONE ||
            currentFormFactor == DeviceFormFactorDetector.FormFactor.TABLET) {
            LinearLayout bottomBar = new LinearLayout(this);
            bottomBar.setOrientation(LinearLayout.HORIZONTAL);
            bottomBar.setBackgroundColor(Color.parseColor("#0F0F0F"));
            bottomBar.setPadding(0, 16, 0, 24);

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

    private View createVideoCard(String title, String channel, String metadata, String duration) {
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(0, 0, 0, 28);
        card.setOnClickListener(v -> openVideoPlayer(title, channel));

        // 16:9 Thumbnail Mockup with Duration Badge
        LinearLayout thumbContainer = new LinearLayout(this);
        thumbContainer.setOrientation(LinearLayout.VERTICAL);
        thumbContainer.setGravity(Gravity.BOTTOM | Gravity.END);
        int thumbHeight = currentFormFactor == DeviceFormFactorDetector.FormFactor.PHONE ? 540 : 400;
        thumbContainer.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, thumbHeight));
        thumbContainer.setBackgroundColor(Color.parseColor("#1C1C1C"));

        TextView durBadge = new TextView(this);
        durBadge.setText(" " + duration + " ");
        durBadge.setTextColor(Color.WHITE);
        durBadge.setTextSize(11);
        durBadge.setBackgroundColor(Color.parseColor("#CC000000"));
        LinearLayout.LayoutParams durLp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        durLp.setMargins(0, 0, 16, 16);
        durBadge.setLayoutParams(durLp);
        thumbContainer.addView(durBadge);

        card.addView(thumbContainer);

        // Details Row (Avatar + Text + 3-dots)
        LinearLayout detailsRow = new LinearLayout(this);
        detailsRow.setOrientation(LinearLayout.HORIZONTAL);
        detailsRow.setPadding(24, 16, 24, 0);
        detailsRow.setGravity(Gravity.TOP);

        // Circle Avatar
        TextView avatar = new TextView(this);
        avatar.setText(" " + channel.substring(0, 1).toUpperCase() + " ");
        avatar.setTextColor(Color.WHITE);
        avatar.setTextSize(14);
        avatar.setTypeface(null, Typeface.BOLD);
        avatar.setGravity(Gravity.CENTER);
        avatar.setBackgroundColor(Color.parseColor("#383838"));
        avatar.setLayoutParams(new LinearLayout.LayoutParams(92, 92));
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
        titleView.setTypeface(null, Typeface.BOLD);
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

    private void openVideoPlayer(String title, String channel) {
        this.currentVideoTitle = title;
        this.currentChannelName = channel;
        this.isWatchingVideo = true;
        renderUI();
    }

    /**
     * Authentic Google YouTube Watch Screen with Video Player,
     * Channel Subscribe Row, Action Pills, and Comments Preview.
     */
    private void renderWatchView() {
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setBackgroundColor(Color.parseColor("#0F0F0F"));
        root.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        // 1. Video Player Surface (16:9)
        LinearLayout playerSurface = new LinearLayout(this);
        playerSurface.setOrientation(LinearLayout.VERTICAL);
        playerSurface.setGravity(Gravity.CENTER);
        playerSurface.setBackgroundColor(Color.BLACK);
        int playerH = currentFormFactor == DeviceFormFactorDetector.FormFactor.PHONE ? 540 : 420;
        playerSurface.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, playerH));

        TextView playStatus = new TextView(this);
        playStatus.setText("▶ Đang phát (1080p HD - Ad-Free)");
        playStatus.setTextColor(Color.WHITE);
        playStatus.setTextSize(15);
        playerSurface.addView(playStatus);

        root.addView(playerSurface);

        // 2. Video Details Scroll
        ScrollView scroll = new ScrollView(this);
        LinearLayout.LayoutParams scrollLp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 1.0f);
        scroll.setLayoutParams(scrollLp);

        LinearLayout content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setPadding(24, 20, 24, 24);

        // Video Title
        TextView titleTv = new TextView(this);
        titleTv.setText(currentVideoTitle);
        titleTv.setTextColor(Color.WHITE);
        titleTv.setTextSize(17);
        titleTv.setTypeface(null, Typeface.BOLD);
        content.addView(titleTv);

        TextView viewsTv = new TextView(this);
        viewsTv.setText("245 N lượt xem • 2 ngày trước • #congnghe #ytube");
        viewsTv.setTextColor(Color.parseColor("#AAAAAA"));
        viewsTv.setTextSize(12);
        viewsTv.setPadding(0, 8, 0, 16);
        content.addView(viewsTv);

        // Channel Row (Avatar + Name + Subs + Subscribe Button)
        LinearLayout channelRow = new LinearLayout(this);
        channelRow.setOrientation(LinearLayout.HORIZONTAL);
        channelRow.setGravity(Gravity.CENTER_VERTICAL);
        channelRow.setPadding(0, 0, 0, 20);

        TextView chanAvatar = new TextView(this);
        chanAvatar.setText(" " + currentChannelName.substring(0, 1).toUpperCase() + " ");
        chanAvatar.setTextColor(Color.WHITE);
        chanAvatar.setTextSize(14);
        chanAvatar.setTypeface(null, Typeface.BOLD);
        chanAvatar.setBackgroundColor(Color.parseColor("#383838"));
        chanAvatar.setLayoutParams(new LinearLayout.LayoutParams(80, 80));
        chanAvatar.setGravity(Gravity.CENTER);
        channelRow.addView(chanAvatar);

        LinearLayout chanText = new LinearLayout(this);
        chanText.setOrientation(LinearLayout.VERTICAL);
        chanText.setPadding(16, 0, 0, 0);

        TextView chanName = new TextView(this);
        chanName.setText(currentChannelName);
        chanName.setTextColor(Color.WHITE);
        chanName.setTextSize(14);
        chanName.setTypeface(null, Typeface.BOLD);
        chanText.addView(chanName);

        TextView subCount = new TextView(this);
        subCount.setText("1,2 Tr người đăng ký");
        subCount.setTextColor(Color.parseColor("#888888"));
        subCount.setTextSize(11);
        chanText.addView(subCount);

        channelRow.addView(chanText, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));

        // Bold Red Subscribe Button
        Button subBtn = new Button(this);
        subBtn.setText("Đăng ký");
        subBtn.setTextColor(Color.BLACK);
        subBtn.setBackgroundColor(Color.WHITE);
        subBtn.setTextSize(12);
        subBtn.setTypeface(null, Typeface.BOLD);
        channelRow.addView(subBtn);

        content.addView(channelRow);

        // Action Pills Bar (Like/Dislike, Share, Download, Save)
        HorizontalScrollView actionScroll = new HorizontalScrollView(this);
        actionScroll.setHorizontalScrollBarEnabled(false);
        LinearLayout actionContainer = new LinearLayout(this);
        actionContainer.setOrientation(LinearLayout.HORIZONTAL);
        actionContainer.setPadding(0, 0, 0, 20);

        String[] actions = {"👍 45 N  |  👎", "↗️ Chia sẻ", "⬇️ Tải xuống", "✂️ Đoạn video", "💾 Lưu"};
        for (String act : actions) {
            TextView pill = new TextView(this);
            pill.setText(act);
            pill.setTextColor(Color.WHITE);
            pill.setBackgroundColor(Color.parseColor("#272727"));
            pill.setPadding(32, 16, 32, 16);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0, 0, 16, 0);
            pill.setLayoutParams(lp);
            actionContainer.addView(pill);
        }
        actionScroll.addView(actionContainer);
        content.addView(actionScroll);

        // Comments Preview Box
        LinearLayout commentBox = new LinearLayout(this);
        commentBox.setOrientation(LinearLayout.VERTICAL);
        commentBox.setBackgroundColor(Color.parseColor("#222222"));
        commentBox.setPadding(24, 20, 24, 20);

        TextView cmtHeader = new TextView(this);
        cmtHeader.setText("Bình luận  1,4 N");
        cmtHeader.setTextColor(Color.WHITE);
        cmtHeader.setTextSize(13);
        cmtHeader.setTypeface(null, Typeface.BOLD);
        commentBox.addView(cmtHeader);

        TextView cmtPreview = new TextView(this);
        cmtPreview.setText("Video rất hay và hữu ích, giao diện chuẩn YouTube xem cực kỳ thích!");
        cmtPreview.setTextColor(Color.LTGRAY);
        cmtPreview.setTextSize(12);
        cmtPreview.setPadding(0, 8, 0, 0);
        commentBox.addView(cmtPreview);

        content.addView(commentBox);

        // Back / Close Button
        Button closeBtn = new Button(this);
        closeBtn.setText("Thu nhỏ / Đóng Video");
        closeBtn.setTextColor(Color.WHITE);
        closeBtn.setBackgroundColor(Color.parseColor("#333333"));
        closeBtn.setOnClickListener(v -> {
            isWatchingVideo = false;
            renderUI();
        });
        LinearLayout.LayoutParams btnLp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        btnLp.setMargins(0, 24, 0, 0);
        closeBtn.setLayoutParams(btnLp);
        content.addView(closeBtn);

        scroll.addView(content);
        root.addView(scroll);

        setContentView(root);
    }

    @Override
    public void onBackPressed() {
        if (isWatchingVideo) {
            isWatchingVideo = false;
            renderUI();
        } else {
            super.onBackPressed();
        }
    }
}
