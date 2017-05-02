package com.example.sails.footballsimulator.camera;

import android.content.Context;
import android.graphics.Rect;
import android.hardware.Camera;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.view.WindowManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.WINDOW_SERVICE;

/**
 * Created by sails on 02.02.2017.
 */

public class CameraSurface extends SurfaceView implements SurfaceHolder.Callback {

    Camera camera;
    SurfaceHolder surfaceHolder;
    String LOG_TAG = "debug";

    public CameraSurface(Context context, Camera camera) {
        super(context);

        this.camera = camera;
        this.surfaceHolder = getHolder();

        surfaceHolder.addCallback(this);
        surfaceHolder.setFormat(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Log.d(LOG_TAG, "sufraceCreated");
        try {

            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int width, int height) {

        Log.d(LOG_TAG, "surfaceChanged");

        if (surfaceHolder.getSurface() == null) {
            return;
        }

        try {
            camera.stopPreview();
        } catch (Exception e) {
        }


        Camera.Parameters parameters = camera.getParameters();


        Display display = ((WindowManager) getContext().getSystemService(WINDOW_SERVICE)).getDefaultDisplay();

        if (display.getRotation() == Surface.ROTATION_0) {
            parameters.setPreviewSize(height, width);
            camera.setDisplayOrientation(90);
        }

        if (display.getRotation() == Surface.ROTATION_90) {
            parameters.setPreviewSize(width, height);
        }

        if (display.getRotation() == Surface.ROTATION_180) {
            parameters.setPreviewSize(height, width);
        }

        if (display.getRotation() == Surface.ROTATION_270) {
            parameters.setPreviewSize(width, height);
            camera.setDisplayOrientation(180);
        }

        camera.setParameters(parameters);

        try {
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        Log.d(LOG_TAG, "surfaceDestroyed");
    }

}
