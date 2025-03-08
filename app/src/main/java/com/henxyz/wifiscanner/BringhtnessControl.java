package com.henxyz.wifiscanner;

import android.content.Context;
import android.provider.Settings;

public class BrightnessControl {
    private Context context;

    public BrightnessControl(Context context) {
        this.context = context;
    }

    public void setBrightness(int level) {
        try {
            Settings.System.putInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, level);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}