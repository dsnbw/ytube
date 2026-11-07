package com.dsnbw.smarttube;

import android.app.UiModeManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;

public class DeviceFormFactorDetector {
    public enum FormFactor {
        PHONE,   // Authentic Google YouTube Mobile Touch UI (Bottom Tabs, 1-column feed)
        TABLET,  // Authentic Google YouTube Tablet UI (Navigation Rail / 2-3 column split)
        TV       // 10-foot Leanback Remote Control UI (Horizontal shelves)
    }

    public static FormFactor detect(Context context) {
        // 1. Detect TV (Leanback feature, Television UI mode, or missing touchscreen)
        UiModeManager uiModeManager = (UiModeManager) context.getSystemService(Context.UI_MODE_SERVICE);
        if (uiModeManager != null && uiModeManager.getCurrentModeType() == Configuration.UI_MODE_TYPE_TELEVISION) {
            return FormFactor.TV;
        }
        PackageManager pm = context.getPackageManager();
        if (pm.hasSystemFeature(PackageManager.FEATURE_LEANBACK)) {
            return FormFactor.TV;
        }
        if (!pm.hasSystemFeature(PackageManager.FEATURE_TOUCHSCREEN)) {
            return FormFactor.TV;
        }

        // 2. Detect Tablet (Screen width >= 600dp or Large/XLarge layout)
        Configuration config = context.getResources().getConfiguration();
        if (config.smallestScreenWidthDp >= 600 || 
            (config.screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE) {
            return FormFactor.TABLET;
        }

        // 3. Default to Phone (Standard mobile touch device)
        return FormFactor.PHONE;
    }
}
