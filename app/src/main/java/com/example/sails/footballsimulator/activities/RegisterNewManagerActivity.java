package com.example.sails.footballsimulator.activities;

import android.app.Fragment;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageSwitcher;

import com.example.sails.footballsimulator.R;
import com.example.sails.footballsimulator.controllers.DataBaseController;
import com.example.sails.footballsimulator.fragments.TeamSelectionFragment;

public class RegisterNewManagerActivity extends AppCompatActivity implements TeamSelectionFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_register_new_manager);


        System.out.println(getIntent().getStringExtra("manager_name"));
        System.out.println(getIntent().getStringExtra("manager_name"));
        System.out.println(getIntent().getStringExtra("manager_name"));
        System.out.println(getIntent().getStringExtra("manager_name"));
        System.out.println(getIntent().getStringExtra("manager_name"));
        System.out.println(getIntent().getStringExtra("manager_name"));
        System.out.println(getIntent().getStringExtra("manager_name"));

        TeamSelectionFragment fragment = TeamSelectionFragment.newInstance(1,
                getIntent().getStringExtra("manager_name"));
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        ft.replace(R.id.frameSelectionTeam, fragment);
        ft.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
