package com.example.sails.footballsimulator.entity;

/**
 * Created by sails on 05.01.2017.
 */

public class Team {

    int id;
    String name;
    int leagueId;
    int managerId;
    String emblemName;
    int money;

    public Team(int id, String name, int leagueId, int managerId, String emblemName, int money) {
        this.id = id;
        this.name = name;
        this.leagueId = leagueId;
        this.managerId = managerId;
        this.emblemName = emblemName;
        this.money = money;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getLeagueId() {
        return leagueId;
    }

    public int getManagerId() {
        return managerId;
    }

    public String getEmblemName() {
        return emblemName;
    }

    public int getMoney() {
        return money;
    }
}
