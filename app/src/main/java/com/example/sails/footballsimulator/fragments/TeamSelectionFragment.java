package com.example.sails.footballsimulator.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sails.footballsimulator.R;
import com.example.sails.footballsimulator.activities.MainMenuActivity;
import com.example.sails.footballsimulator.activities.PlayerActivity;
import com.example.sails.footballsimulator.adapters.SelectTeamAdapter;
import com.example.sails.footballsimulator.controllers.DataBaseController;
import com.example.sails.footballsimulator.entity.Manager;
import com.example.sails.footballsimulator.entity.Team;
import com.example.sails.footballsimulator.listeners.OnRecyclerViewSelectTeamInteractionListener;

import java.util.List;


public class TeamSelectionFragment extends Fragment implements OnRecyclerViewSelectTeamInteractionListener {


    private static int defaultTeamId;
    private static String managersName;
    private static final String ARG_DEFAULT_TEAM = "defaultTeamId";
    private static final String ARG_MANAGERS_NAME = "managersName";


    public TeamSelectionFragment() {

    }

    public static TeamSelectionFragment newInstance(int defaultTeamId, String managersName) {
        TeamSelectionFragment fragment = new TeamSelectionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_DEFAULT_TEAM, defaultTeamId);
        args.putString(ARG_MANAGERS_NAME, managersName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            defaultTeamId = getArguments().getInt(ARG_DEFAULT_TEAM);
            managersName = getArguments().getString(ARG_MANAGERS_NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_team_selection, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewChangingManagersInfo);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        List<Team> list = DataBaseController.getFullTeamsInfoFromDB();
        recyclerView.setAdapter(new SelectTeamAdapter(getContext(), managersName, list, this));

        return view;
    }


    @Override
    public void onPlayerClick(int id) {
        Intent intent = new Intent(getActivity().getApplication().getApplicationContext(), PlayerActivity.class);
        intent.putExtra("player_id", id);
        startActivity(intent);
    }

    @Override
    public void onConfirmButtonClick(Manager manager) {
        if (null != manager) {
            int newManagerId = DataBaseController.insertNewManagerIntoDB(manager);

            Intent intent = new Intent(getContext(), MainMenuActivity.class);
            intent.putExtra("manager_id", newManagerId);
            startActivity(intent);
        } else
            Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
    }


}
