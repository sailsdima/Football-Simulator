package com.example.sails.footballsimulator.activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sails.footballsimulator.R;
import com.example.sails.footballsimulator.controllers.DataBaseController;
import com.example.sails.footballsimulator.entity.Manager;
import com.squareup.picasso.Picasso;

public class ManagerInfoActivity extends AppCompatActivity {

    Manager manager;
    int managerId;
    ImageView imageViewManagerPhoto;
    TabLayout tabs;
    ViewPager viewPager;
    private final int REQUEST_CODE_CAMERA = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        init();
    }

    private void init() {

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkCameraExists()) {
                    Intent intent = new Intent(getApplicationContext(), CameraActivity.class);
                    startActivityForResult(intent, REQUEST_CODE_CAMERA);
                } else {
                    Toast.makeText(getApplicationContext(), "Cant find a camera", Toast.LENGTH_SHORT).show();
                }
            }
        });

        int managerId = getIntent().getIntExtra("manager_id", 1);
        this.managerId = managerId;
        manager = DataBaseController.getManagerFromDBById(managerId);

        imageViewManagerPhoto = (ImageView) findViewById(R.id.imageViewManagersPhoto);

        tabs = (TabLayout) findViewById(R.id.tabLayoutManagerInfo);
        viewPager = (ViewPager) findViewById(R.id.viewPagerManagerInfo);

        updateUI();
    }

    private void updateUI() {
        setTitle(manager.getName());

        Picasso.with(getApplicationContext()).load(manager.getPhotoUri()).
                placeholder(R.drawable.no_photo).
                fit().
                into(imageViewManagerPhoto);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_CAMERA) {
                String photoUri = data.getStringExtra("photoUri");

                DataBaseController.updateManagersNewPhotoUri(manager.getId(), photoUri);
                manager.setPhotoUri(photoUri);
                updateUI();
            }
        }
    }

    private boolean checkCameraExists() {
        return getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }
}
