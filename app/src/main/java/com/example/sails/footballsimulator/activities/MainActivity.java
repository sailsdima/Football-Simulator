package com.example.sails.footballsimulator.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.example.sails.footballsimulator.R;
import com.example.sails.footballsimulator.controllers.DataBaseController;
import com.example.sails.footballsimulator.entity.Manager;
import com.example.sails.footballsimulator.helpers.DataBaseHelper;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private final String LOG_TAG = "debug";

    private ImageView imageViewButtonOpenManagersList;
    private EditText editTextName;
    Button buttonContinue;
    private final int REQUEST_CODE_CHOOSE_MANAGER = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        imageViewButtonOpenManagersList = (ImageView) findViewById(R.id.imageViewButtonOpenManagersList);
        imageViewButtonOpenManagersList.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                imageViewButtonOpenManagersList.setSelected(event.getAction() == MotionEvent.ACTION_DOWN
                        || event.getAction() == MotionEvent.ACTION_MOVE);
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    startManagerSelectionActivity();
                }

                return false;
            }
        });

        editTextName = (EditText) findViewById(R.id.editTextName);
        buttonContinue = (Button) findViewById(R.id.buttonContinue);
        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonContinueClick();
            }
        });


    }

    private void buttonContinueClick() {

        if(editTextName.getText().toString().length() < 3) {
            Toast.makeText(getApplicationContext(), "Invalid name", Toast.LENGTH_SHORT).show();
            return;
        }

        String name = editTextName.getText().toString();

        if(DataBaseController.getManagerNamesList().contains(name)){
            // TODO add open game menu activity
        } else {

            openRegisterNewManagerActivity();

        }

    }

    private void openRegisterNewManagerActivity(){
        Intent intent = new Intent(this, RegisterNewManagerActivity.class);
        intent.putExtra("manager_name", editTextName.getText().toString());
        startActivity(intent);
    }

    private void startManagerSelectionActivity() {
        Intent intent = new Intent(getApplicationContext(), ManagersActivity.class);
        startActivityForResult(intent, REQUEST_CODE_CHOOSE_MANAGER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_CHOOSE_MANAGER && null != data){
            int id = data.getIntExtra("managerId", 1);
            setSelectedManagerInEditText(id);
        }
    }

    private void setSelectedManagerInEditText(int id){

        Manager manager = DataBaseController.getManagerFromDBById(id);
        editTextName.setText(manager.getName());
    }
}
