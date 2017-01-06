package com.example.sails.footballsimulator;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.sails.footballsimulator.adapters.ManagersAdapter;
import com.example.sails.footballsimulator.entity.Manager;
import com.example.sails.footballsimulator.helpers.DataBaseHelper;
import com.example.sails.footballsimulator.listeners.OnRecycleViewInteractionListener;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ManagersActivity extends AppCompatActivity implements OnRecycleViewInteractionListener{

    private static final String LOG_TAG = "debug";
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managers);

        init();
    }

    private void init() {

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewManagers);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        DataBaseHelper myDbHelper = null;
        try {
            myDbHelper = new DataBaseHelper(this);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Manager> managers = myDbHelper.getManagersFromDB();

        ManagersAdapter adapter = new ManagersAdapter(this, managers, this);
        recyclerView.setAdapter(adapter);
    }



    @Override
    public void onItemClick(Object obj) {
        if (obj instanceof Manager) {
            Manager manager = (Manager)obj;

            Intent intent = new Intent();
            intent.putExtra("managerId", manager.getId());
            setResult(RESULT_OK, intent);
            finish();
        }
    }


}
