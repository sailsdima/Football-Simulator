package com.example.sails.footballsimulator.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sails.footballsimulator.R;

public class MainMenuActivity extends AppCompatActivity {

    Button btnOpenManagersPage;
    int manager_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        init();
        Toast.makeText(this, getIntent().getIntExtra("manager_id",10) + " ", Toast.LENGTH_SHORT).show();
    }

    private void init(){
        manager_id = getIntent().getIntExtra("manager_id",2);

        btnOpenManagersPage = (Button) findViewById(R.id.buttonOpenManagersPage);
        btnOpenManagersPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, ManagerInfoActivity.class);
                intent.putExtra("manager_id", manager_id);
                startActivity(intent);
            }
        });
    }
}
