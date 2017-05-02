package com.example.sails.footballsimulator.listeners;

import com.example.sails.footballsimulator.entity.Manager;

/**
 * Created by sails on 16.01.2017.
 */

public interface OnRecyclerViewSelectTeamInteractionListener {

    void onPlayerClick(int id);
    void onConfirmButtonClick(Manager manager);

}
