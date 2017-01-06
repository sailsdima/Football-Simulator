package com.example.sails.footballsimulator.entity;

import java.util.Calendar;

/**
 * Created by sails on 05.01.2017.
 */

public class Player {

    int id;
    String name, photoName;
    int number, teamId, positionId;
    int birthdayYear, height, weight;
    String country;
    int attackSkill, defenceSkill;
    String leadingFoot;

    public Player(int id, String name, String photoName, int number, int teamId, int positionId) {
        this.id = id;
        this.name = name;
        this.photoName = photoName;
        this.number = number;
        this.teamId = teamId;
        this.positionId = positionId;
    }

    public void setLeadingFoot(String leadingFoot) {
        this.leadingFoot = leadingFoot;
    }

    public void setDefenceSkill(int defenceSkill) {
        this.defenceSkill = defenceSkill;
    }

    public void setAttackSkill(int attackSkill) {
        this.attackSkill = attackSkill;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setBirthdayYear(int birthdayYear) {
        this.birthdayYear = birthdayYear;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhotoName() {
        return photoName;
    }

    public int getNumber() {
        return number;
    }

    public int getTeamId() {
        return teamId;
    }

    public int getPositionId() {
        return positionId;
    }

    public int getBirthdayYear() {
        return birthdayYear;
    }

    public int getAge(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR) - birthdayYear;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public String getCountry() {
        return country;
    }

    public int getAttackSkill() {
        return attackSkill;
    }

    public int getDefenceSkill() {
        return defenceSkill;
    }

    public String getLeadingFoot() {
        return leadingFoot;
    }
}
