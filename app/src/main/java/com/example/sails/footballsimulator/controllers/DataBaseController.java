package com.example.sails.footballsimulator.controllers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sails.footballsimulator.entity.Manager;
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

            return new Manager(id, name, birthdayYear, country, photo, team, teamId);
        }
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
        return names;
    }
}
