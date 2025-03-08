package com.henxyz.wifiscanner;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import androidx.core.app.ActivityCompat;
import java.util.List;

public class WiFiScanner {
    private WifiManager wifiManager;
    private Context context;

    public WiFiScanner(Context context) {
        this.context = context;
        wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
    }

    public void scanWiFi() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            System.out.println("Izin lokasi diperlukan untuk scan WiFi!");
            return;
        }

        wifiManager.startScan();
        List<ScanResult> results = wifiManager.getScanResults();
        for (ScanResult result : results) {
            System.out.println("WiFi: " + result.SSID + " - Signal: " + result.level + "dBm");
        }
    }
}