package com.dsnbw.smarttube;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.view.Gravity;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);
        tv.setText("SmartTube Multiplatform\nAd-Free, SponsorBlock, 4K Playback\nOptimized for Android TV, Box & Mobile");
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(22);
        setContentView(tv);
    }
}
