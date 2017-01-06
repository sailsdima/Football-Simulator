package com.example.sails.footballsimulator.entity;

import java.util.Calendar;
import java.util.Comparator;

/**
 * Created by sails on 05.01.2017.
 */

public class Manager {

    int id;
    String name;
    int birthdayYear;
    String contry;
    String photoName;
    String team;
    String teamId;

    public Manager(int id, String name, int birthdayYear, String contry, String photoName, String team, String teamId) {
        this.id = id;
        this.name = name;
        this.birthdayYear = birthdayYear;
        this.contry = contry;
        this.photoName = photoName;
        this.team = team;
        this.teamId = teamId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBirthdayYear() {
        return birthdayYear;
    }

    public int getAge(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR) - birthdayYear;
    }

    public String getContry() {
        return contry;
    }

    public String getPhotoName() {
        return photoName;
    }

    public String getTeam() {
        return team;
    }

    public String getTeamId() {
        return teamId;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthdayYear=" + birthdayYear +
                ", contry='" + contry + '\'' +
                ", photoName='" + photoName + '\'' +
                ", team='" + team + '\'' +
                ", teamId='" + teamId + '\'' +
                '}';
    }

}
