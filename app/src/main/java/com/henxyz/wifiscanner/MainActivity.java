package com.henxyz.wifiscanner;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    private WiFiScanner wifiScanner;
    private Flashlight flashlight;
    private BrightnessControl brightnessControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wifiScanner = new WiFiScanner(this);
        flashlight = new Flashlight(this);
        brightnessControl = new BrightnessControl(this);

        Button btnScan = findViewById(R.id.btnScan);
        Button btnFlash = findViewById(R.id.btnFlash);
        Button btnDisco = findViewById(R.id.btnDisco);
        Button btnBrightness = findViewById(R.id.btnBrightness);

        btnScan.setOnClickListener(v -> wifiScanner.scanWiFi());
        btnFlash.setOnClickListener(v -> flashlight.toggleFlashlight());
        btnDisco.setOnClickListener(v -> flashlight.startDiscoMode());
        btnBrightness.setOnClickListener(v -> brightnessControl.setBrightness(100));

        requestPermissions();
    }

    private void requestPermissions() {
        String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_SETTINGS};
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{permission}, 1);
            }
        }
    }
}