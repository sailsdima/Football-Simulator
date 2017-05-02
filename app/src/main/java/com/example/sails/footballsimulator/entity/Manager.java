package com.example.sails.footballsimulator.entity;

import java.util.Calendar;

/**
 * Created by sails on 05.01.2017.
 */

public class Manager {

    int id;
    String name;
    int birthdayYear;
    String country;
    String photoName;
    String team;
    int teamId;

    public Manager(int id, String name, int birthdayYear, String country, String photoName, String team, int teamId) {
        this.id = id;
        this.name = name;
        this.birthdayYear = birthdayYear;
        this.country = country;
        this.photoName = photoName;
        this.team = team;
        this.teamId = teamId;
    }
    public Manager(String name, int birthdayYear, String country, int teamId) {
        this.name = name;
        this.birthdayYear = birthdayYear;
        this.country = country;
        this.teamId = teamId;
    }

    public void setPhotoUri(String photoUri){
        this.photoName = photoUri;
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

    public String getCountry() {
        return country;
    }

    public String getPhotoName() {
        return photoName;
    }

    public String getPhotoUri() {
        if(photoName.matches("file://.*"))
            return photoName;
        return "http://stbarvinok.in.ua/android-apps/football-simulator/managers/" + photoName;
    }

    public String getTeam() {
        return team;
    }

    public int getTeamId() {
        return teamId;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthdayYear=" + birthdayYear +
                ", country='" + country + '\'' +
                ", photoName='" + photoName + '\'' +
                ", team='" + team + '\'' +
                ", teamId='" + teamId + '\'' +
                '}';
    }

}
