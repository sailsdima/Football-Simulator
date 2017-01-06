package com.example.sails.footballsimulator.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sails.footballsimulator.ManagersActivity;
import com.example.sails.footballsimulator.R;
import com.example.sails.footballsimulator.entity.Manager;
import com.example.sails.footballsimulator.helpers.DataBaseHelper;

import java.io.IOException;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {
    private final String LOG_TAG = "debug";

    private ImageView imageViewButtonOpenManagersList;
    private EditText editTextName;
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
    }


    private void startManagerSelectionActivity() {
        Intent intent = new Intent(getApplicationContext(), ManagersActivity.class);
        startActivityForResult(intent, REQUEST_CODE_CHOOSE_MANAGER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_CHOOSE_MANAGER){
            int id = data.getIntExtra("managerId", 1);
            setSelectedManagerInEditText(id);
        }
    }

    private void setSelectedManagerInEditText(int id){
        DataBaseHelper dbHelper = null;

        try {
            dbHelper = new DataBaseHelper(getApplicationContext());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Manager manager = dbHelper.getManagerFromDBById(id);
        editTextName.setText(manager.getName());
    }
}
