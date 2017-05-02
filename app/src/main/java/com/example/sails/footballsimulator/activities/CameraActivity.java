package com.example.sails.footballsimulator.activities;

import android.content.Intent;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.sails.footballsimulator.R;
import com.example.sails.footballsimulator.camera.CameraSurface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CameraActivity extends AppCompatActivity {

    Camera camera = null;
    Button btnMakePhoto;
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final String LOG_TAG = "debug";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        initCameraAndSurface();
        initComponents();
    }

    private void initComponents() {
        btnMakePhoto = (Button) findViewById(R.id.buttonMakePhoto);

        btnMakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                camera.takePicture(null, null, pictureCallback);
            }
        });
    }

    private void initCameraAndSurface() {
        if (null == camera) {
            camera = Camera.open();
        }

        try {
            CameraSurface cameraSurface = new CameraSurface(this, camera);
            FrameLayout frameLayout = (FrameLayout) findViewById(R.id.frame_camera_preview);
            frameLayout.removeAllViews();
            frameLayout.addView(cameraSurface);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private final Camera.PictureCallback pictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] bytes, Camera camera) {
            Log.d(LOG_TAG, "onpicturetaken method started");

            File pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);
            if (pictureFile == null) {
                Toast.makeText(getApplicationContext(), "Picture not taken", Toast.LENGTH_SHORT).show();
                Log.d(LOG_TAG, "Error creating media file, check storage permissions");
                return;
            }

            try {
                FileOutputStream fos = new FileOutputStream(pictureFile);
                fos.write(bytes);
                Toast.makeText(getApplicationContext(), "Picture taken", Toast.LENGTH_SHORT).show();
                fos.close();
            } catch (FileNotFoundException e) {
                Log.d(LOG_TAG, "File not found: " + e.getMessage());
            } catch (IOException e) {
                Log.d(LOG_TAG, "Error accessing file: " + e.getMessage());
            }

            Intent intent = new Intent();
            String photoUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE).toString();
            System.out.println(photoUri);
            intent.putExtra("photoUri", photoUri);
            setResult(RESULT_OK, intent);
            finish();

        }
    };

    /**
     * Create a file Uri for saving an image or video
     */
    private static Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /**
     * Create a File for saving an image or video
     */
    private static File getOutputMediaFile(int type) {
        Log.d(LOG_TAG, "getOutputMediaFile started");
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "FS_camera");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(LOG_TAG, "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_" + timeStamp + ".jpg");
            Log.d(LOG_TAG, "OK");
        } else {
            Log.d(LOG_TAG, "returned null");

            return null;
        }

        return mediaFile;
    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initCameraAndSurface();
    }

    private void releaseCamera() {
        if (null != camera) {
            camera.release();
            camera = null;
        }

    }


}
