package com.example.sails.footballsimulator.activities;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import com.example.sails.footballsimulator.R;
import com.example.sails.footballsimulator.adapters.ManagersAdapter;
import com.example.sails.footballsimulator.controllers.DataBaseController;
import com.example.sails.footballsimulator.entity.Manager;
import com.example.sails.footballsimulator.listeners.OnRecycleViewInteractionListener;

import java.util.List;

public class ManagersActivity extends AppCompatActivity implements OnRecycleViewInteractionListener{

    private static final String LOG_TAG = "debug";
    RecyclerView recyclerView;
    ManagersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managers);

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        init();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_search, menu);

        MenuItem searchItem = menu.findItem(R.id.menuSearchItem);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                ManagersActivity.this.adapter.getFilter().filter(newText);

                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void init() {

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewManagers);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        List<Manager> managers = DataBaseController.getManagersFromDB();

        adapter = new ManagersAdapter(this, managers, this);
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
