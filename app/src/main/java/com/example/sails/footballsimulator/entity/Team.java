package com.example.sails.footballsimulator.entity;

import android.util.Log;

import com.example.sails.footballsimulator.App;
import com.example.sails.footballsimulator.R;

import java.util.List;

/**
 * Created by sails on 05.01.2017.
 */

public class Team {

    private int id;
    private String name;
    private String teamColor;
    private int leagueId;
    private int managerId;
    private String emblemName;
    private long money;
    private int attackSkill;
    private int halfBackSkill;
    private int defenceSkill;
    private List<Player> players;
    private String managerName;

    public Team(int id, String name, String teamColor, int leagueId, int managerId, String managerName, String emblemName, long money, int attackSkill, int halfBackSkill, int defenceSkill, List<Player> players) {
        this.id = id;
        this.name = name;
        this.teamColor = teamColor;
        this.leagueId = leagueId;
        this.managerId = managerId;
        this.managerName = managerName;
        this.emblemName = emblemName;
        this.money = money;
        this.attackSkill = attackSkill;
        this.halfBackSkill = halfBackSkill;
        this.defenceSkill = defenceSkill;
        this.players = players;
    }

    public float getRating() {
        try {
            int minTotalRating = 30;
            int maxTotalRating = 100;
            float totalStarsCount = 5;


            int totalTeamSkill = attackSkill + halfBackSkill + defenceSkill;
            float avgTeamSkill = totalTeamSkill / 3;

            if (avgTeamSkill >= 85)
                return 5.0f;
            else if (avgTeamSkill >= 82)
                return 4.5f;
            else if (avgTeamSkill >= 77)
                return 4.0f;
            else if (avgTeamSkill >= 70)
                return 3.5f;
            else if (avgTeamSkill >= 60)
                return 3.0f;
            else if (avgTeamSkill >= 50)
                return 2.0f;

            return 1.0f;
        } catch (NullPointerException e) {
            return 0;
        }
    }

    public int getEmblemResourse() {
        int res = 0;
        try {
            res = R.mipmap.class.getField(getEmblemName().substring(0, getEmblemName().indexOf("."))).getInt(App.getAppContext());
        } catch (IllegalAccessException e) {
            Log.e("debug", e.toString());
        } catch (NoSuchFieldException e) {
            res = R.mipmap.unknown_team;
        }

        return res;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTeamColor() {
        return teamColor;
    }


    public int getLeagueId() {
        return leagueId;
    }

    public int getManagerId() {
        return managerId;
    }

    public String getManagerName() {
        return managerName;
    }

    public String getEmblemName() {
        return emblemName;
    }

    public long getMoney() {
        return money;
    }


    public int getAttackSkill() {
        return attackSkill;
    }

    public int getHalfBackSkill() {
        return halfBackSkill;
    }

    public int getDefenceSkill() {
        return defenceSkill;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public void setAttackSkill(int attackSkill) {
        this.attackSkill = attackSkill;
    }

    public void setHalfBackSkill(int halfBackSkill) {
        this.halfBackSkill = halfBackSkill;
    }

    public void setDefenceSkill(int defenceSkill) {
        this.defenceSkill = defenceSkill;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", leagueId=" + leagueId +
                ", managerId=" + managerId +
                ", emblemName='" + emblemName + '\'' +
                ", money=" + money +
                ", attackSkill=" + attackSkill +
                ", halfBackSkill=" + halfBackSkill +
                ", defenceSkill=" + defenceSkill +
                '}';
    }
}
