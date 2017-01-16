package com.example.sails.footballsimulator.controllers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sails.footballsimulator.entity.Manager;
import com.example.sails.footballsimulator.entity.Player;
import com.example.sails.footballsimulator.entity.Team;
import com.example.sails.footballsimulator.helpers.DataBaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sails on 07.01.2017.
 */

public class DataBaseController {

    public static List<Manager> getManagersFromDB() {
        SQLiteDatabase rDatabase = DataBaseHelper.getInstance().getReadableDatabase();

        Cursor cursor = rDatabase.rawQuery("SELECT * FROM managers ORDER BY name;", new String[]{});

        List<Manager> managers = new ArrayList<>();
        if (cursor.moveToFirst()) {

            int idCol = cursor.getColumnIndex("_id");
            int nameCol = cursor.getColumnIndex("name");
            int birthdayYearCol = cursor.getColumnIndex("birthday_year");
            int countryCol = cursor.getColumnIndex("contry");
            int photoCol = cursor.getColumnIndex("photo");
            int teamCol = cursor.getColumnIndex("team");
            int teamIdCol = cursor.getColumnIndex("team_id");

            do {
                int id = cursor.getInt(idCol);
                String name = cursor.getString(nameCol);
                int birthdayYear = cursor.getInt(birthdayYearCol);
                String country = cursor.getString(countryCol);
                String photo = cursor.getString(photoCol);
                String team = cursor.getString(teamCol);
                String teamId = cursor.getString(teamIdCol);

                Manager manager = new Manager(id, name, birthdayYear, country, photo, team, teamId);
                managers.add(manager);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return managers;
    }

    public static Manager getManagerFromDBById(int id) {

        SQLiteDatabase rDatabase = DataBaseHelper.getInstance().getReadableDatabase();

        Cursor cursor = rDatabase.rawQuery("SELECT * FROM managers WHERE _id = " + id + " LIMIT 1;", new String[]{});

        if (cursor.moveToFirst()) {
            int nameCol = cursor.getColumnIndex("name");
            int birthdayYearCol = cursor.getColumnIndex("birthday_year");
            int countryCol = cursor.getColumnIndex("contry");
            int photoCol = cursor.getColumnIndex("photo");
            int teamCol = cursor.getColumnIndex("team");
            int teamIdCol = cursor.getColumnIndex("team_id");

            String name = cursor.getString(nameCol);
            int birthdayYear = cursor.getInt(birthdayYearCol);
            String country = cursor.getString(countryCol);
            String photo = cursor.getString(photoCol);
            String team = cursor.getString(teamCol);
            String teamId = cursor.getString(teamIdCol);

            cursor.close();
            return new Manager(id, name, birthdayYear, country, photo, team, teamId);
        }
        cursor.close();
        return null;
    }

    public static List<String> getManagerNamesList(){

        List<String> names = new ArrayList<>();

        SQLiteDatabase rDatabase = DataBaseHelper.getInstance().getReadableDatabase();

        Cursor cursor = rDatabase.rawQuery("SELECT name FROM managers;", new String[]{});
        if (cursor.moveToFirst()) {
            int nameCol = cursor.getColumnIndex("name");

            do {
                String name = cursor.getString(nameCol);

                names.add(name);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return names;
    }

    public static List<Team> getFullTeamsInfoFromDB(){

        String query = "select * from FullTeamInfoView;";

        List<Team>teams = new ArrayList<>();
        SQLiteDatabase rDatabase = DataBaseHelper.getInstance().getReadableDatabase();
        Cursor cursor = rDatabase.rawQuery(query, new String[]{});

        if (cursor.moveToFirst()) {
            int idCol = cursor.getColumnIndex("_id");
            int nameCol = cursor.getColumnIndex("name");
            int leagueIdCol = cursor.getColumnIndex("league_id");
            int managerIdCol = cursor.getColumnIndex("manager_id");
            int managerNameCol = cursor.getColumnIndex("manager_name");
            int emblemCol = cursor.getColumnIndex("emblem");
            int moneyCol = cursor.getColumnIndex("money");
            int attackSkillCol = cursor.getColumnIndex("attackSkill");
            int halfBackSkillCol = cursor.getColumnIndex("halfBackSkill");
            int defSkillCol = cursor.getColumnIndex("defSkill");

            do {
                int id = cursor.getInt(idCol);
                String name = cursor.getString(nameCol);
                int leagueId = cursor.getInt(leagueIdCol);
                int managerId = cursor.getInt(managerIdCol);
                String managerName = cursor.getString(managerNameCol);
                String emblem = cursor.getString(emblemCol);
                long money = cursor.getLong(moneyCol);
                int attackSkill = cursor.getInt(attackSkillCol);
                int halfBackSkill = cursor.getInt(halfBackSkillCol);
                int defSkill = cursor.getInt(defSkillCol);

                Team team = new Team(id, name, leagueId, managerId, managerName, emblem, money, attackSkill, halfBackSkill, defSkill, getPlayersByTeamId(id));
                teams.add(team);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return teams;
    }

    public static List<Player> getPlayersByTeamId(int tId){
        List<Player> players = new ArrayList<>();

        SQLiteDatabase rDatabase = DataBaseHelper.getInstance().getReadableDatabase();
        System.out.println("TEAM ID: " + tId);
        Cursor cursor = rDatabase.rawQuery("SELECT players.*, player_position.name as posName" +
                " FROM players, player_position WHERE team_id = " + tId + " " +
                "and player_position._id = players.position_id ORDER BY position_id DESC;", new String[]{});

        if (cursor.moveToFirst()) {

            int idCol = cursor.getColumnIndex("_id");
            int nameCol = cursor.getColumnIndex("name");
            int numberCol = cursor.getColumnIndex("number");
            int teamIdCol = cursor.getColumnIndex("team_id");
            int photoCol = cursor.getColumnIndex("photo");
            int positionIdCol = cursor.getColumnIndex("position_id");
            int positionCol = cursor.getColumnIndex("posName");
            int birthdayYearCol = cursor.getColumnIndex("birthday_year");
            int heightCol = cursor.getColumnIndex("height");
            int weightCol = cursor.getColumnIndex("weight");
            int countryCol = cursor.getColumnIndex("country");
            int leadingFootCol = cursor.getColumnIndex("leading_foot");
            int attackSkillCol = cursor.getColumnIndex("attack_skill");
            int defenceSkillCol = cursor.getColumnIndex("defence_skill");

            do {
                int id = cursor.getInt(idCol);
                String name = cursor.getString(nameCol);
                int number = cursor.getInt(numberCol);
                int teamId = cursor.getInt(teamIdCol);
                String photo = cursor.getString(photoCol);
                int positionId = cursor.getInt(positionIdCol);
                String positionName = cursor.getString(positionCol);
                int birthdayYear = cursor.getInt(birthdayYearCol);
                int height = cursor.getInt(heightCol);
                int weight = cursor.getInt(weightCol);
                String country = cursor.getString(countryCol);
                String leadingFoot = cursor.getString(leadingFootCol);
                int attackSkill = cursor.getInt(attackSkillCol);
                int defenceSkill = cursor.getInt(defenceSkillCol);

                Player player = new Player(id, name, number, teamId, photo, positionId, positionName, birthdayYear,
                        height, weight, country, leadingFoot, attackSkill, defenceSkill);
                System.out.println(player.toString());
                players.add(player);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return players;
    }
}
