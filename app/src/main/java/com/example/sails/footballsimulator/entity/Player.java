package com.example.sails.footballsimulator.entity;

import java.util.Calendar;

/**
 * Created by sails on 05.01.2017.
 */

public class Player {

    private int id;
    private String name, photoName, teamName = "undefined";
    private int number, teamId, positionId;
    private String position;
    private int birthdayYear, height, weight;
    private String country;
    private int attackSkill, defenceSkill;
    private String leadingFoot;

//    public Player(int id, String name, String photoName, int number, int teamId, int positionId) {
//        this.id = id;
//        this.name = name;
//        this.photoName = photoName;
//        this.number = number;
//        this.teamId = teamId;
//        this.positionId = positionId;
//    }


    public int getPositionId() {
        return positionId;
    }

    public Player(int id, String name, int number, int teamId, String photo, int positionId, String position, int birthdayYear, int height, int weight, String country, String leadingFoot, int attackSkill, int defenceSkill) {
        this.id = id;
        this.name = name;
        this.photoName = photo;
        this.number = number;
        this.teamId = teamId;
        this.positionId = positionId;
        this.position = position;
        this.birthdayYear = birthdayYear;
        this.height = height;
        this.weight = weight;
        this.country = country;
        this.attackSkill = attackSkill;
        this.defenceSkill = defenceSkill;
        this.leadingFoot = leadingFoot;

        checkAndFixDataIsNull();
    }

    public Player(int id, String name, int number, int teamId, String teamName, String photo, int positionId, String position, int birthdayYear, int height, int weight, String country, String leadingFoot, int attackSkill, int defenceSkill) {
        this.id = id;
        this.name = name;
        this.photoName = photo;
        this.number = number;
        this.teamId = teamId;
        this.teamName = teamName;
        this.positionId = positionId;
        this.position = position;
        this.birthdayYear = birthdayYear;
        this.height = height;
        this.weight = weight;
        this.country = country;
        this.attackSkill = attackSkill;
        this.defenceSkill = defenceSkill;
        this.leadingFoot = leadingFoot;

        checkAndFixDataIsNull();
    }

    private void checkAndFixDataIsNull(){
        if(null == name || name.length() == 0)
            name = "unknown";

        if(null == position || position.length() == 0)
            position = "UNK";

        if(null == country || country.length() == 0)
            country = "unknown";

        if(null == leadingFoot || leadingFoot.length() == 0)
            leadingFoot = "unknown";
    }

    @Override
    public String toString() {
        return "Player id = " + id + " name = " + name;
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

    public String getPosition() {
        return position;
    }

    public int getBirthdayYear() {
        return birthdayYear;
    }

    public int getAge() {
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

    public int getTotalSkill() {

        return (attackSkill > defenceSkill) ? attackSkill : defenceSkill;
    }

    public String getTeamName() {
        return teamName;
    }
}
