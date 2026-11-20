package com.google.android.apps.weather.launcher;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class WeatherLauncherActivity extends Activity {
    private static final String GSA_PACKAGE = "com.google.android.googlequicksearchbox";
    private static final String TRAMPOLINE_CLASS =
            "com.google.android.apps.gsa.velour.DynamicActivityTrampoline";
    private static final String WEATHER_URI = "dynact://velour/weather/ProxyActivity";
    private static final String FALLBACK_URI = "https://www.google.com/gaweather";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(WEATHER_URI));
            intent.setComponent(new ComponentName(GSA_PACKAGE, TRAMPOLINE_CLASS));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        } catch (Exception e) {
            try {
                Intent fallback = new Intent(Intent.ACTION_VIEW, Uri.parse(FALLBACK_URI));
                fallback.setPackage(GSA_PACKAGE);
                fallback.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(fallback);
            } catch (Exception ignored) {
            }
        }
        finish();
    }
}
