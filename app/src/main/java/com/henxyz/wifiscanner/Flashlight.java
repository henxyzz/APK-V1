package com.henxyz.wifiscanner;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Handler;

public class Flashlight {
    private CameraManager cameraManager;
    private String cameraId;
    private boolean isFlashOn = false;
    private Handler handler = new Handler();

    public Flashlight(Context context) {
        cameraManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
        try {
            cameraId = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    public void toggleFlashlight() {
        setFlashlight(!isFlashOn);
    }

    public void setFlashlight(boolean state) {
        try {
            cameraManager.setTorchMode(cameraId, state);
            isFlashOn = state;
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    public void startDiscoMode() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                setFlashlight(!isFlashOn);
                handler.postDelayed(this, 300);
            }
        });
    }
}