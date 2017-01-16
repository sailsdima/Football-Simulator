package com.example.sails.footballsimulator.fragments;

import android.content.Context;
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
import com.example.sails.footballsimulator.adapters.SelectTeamAdapter;
import com.example.sails.footballsimulator.controllers.DataBaseController;
import com.example.sails.footballsimulator.entity.Team;
import com.example.sails.footballsimulator.listeners.OnPlayerClickListener;

import java.util.List;


public class TeamSelectionFragment extends Fragment implements OnPlayerClickListener{


    private static int defaultTeamId;
    private static String managersName;
    private static final String ARG_DEFAULT_TEAM = "defaultTeamId";
    private static final String ARG_MANAGERS_NAME = "managersName";

    private OnFragmentInteractionListener mListener;

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

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onPlayerClick(int id) {
        Toast.makeText(getActivity(), id + " ", Toast.LENGTH_SHORT).show(); 
    }

    @Override
    public void onConfirmButtonClick() {

    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
