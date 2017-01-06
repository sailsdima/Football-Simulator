package com.example.sails.footballsimulator.helpers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.sails.footballsimulator.entity.Manager;

/**
 * Created by sails on 05.01.2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    private final String LOG_TAG = "debug";

    // путь к базе данных вашего приложения
    private static String DB_PATH = "/data/data/com.example.sails.footballsimulator/databases/";
    private static String DB_NAME = "football";
    private SQLiteDatabase myDataBase;
    private final Context mContext;
    private boolean isOpened = false;

    /**
     * Конструктор
     * Принимает и сохраняет ссылку на переданный контекст для доступа к ресурсам приложения
     *
     * @param context
     */
    public DataBaseHelper(Context context) throws SQLException, IOException {
        super(context, DB_NAME, null, 2);
        this.mContext = context;
        createDataBase();
        openDataBase();
    }

    /**
     * Создает пустую базу данных и перезаписывает ее нашей собственной базой
     */
    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();

        if (dbExist) {
            //ничего не делать - база уже есть
        } else {
            //вызывая этот метод создаем пустую базу, позже она будет перезаписана
            this.getReadableDatabase();

            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    /**
     * Проверяет, существует ли уже эта база, чтобы не копировать каждый раз при запуске приложения
     *
     * @return true если существует, false если не существует
     */
    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;

        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
            //база еще не существует
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    /**
     * Копирует базу из папки assets заместо созданной локальной БД
     * Выполняется путем копирования потока байтов.
     */
    private void copyDataBase() throws IOException {
        //Открываем локальную БД как входящий поток
        InputStream myInput = mContext.getAssets().open(DB_NAME);

        //Путь ко вновь созданной БД
        String outFileName = DB_PATH + DB_NAME;

        //Открываем пустую базу данных как исходящий поток
        OutputStream myOutput = new FileOutputStream(outFileName);

        //перемещаем байты из входящего файла в исходящий
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        //закрываем потоки
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void openDataBase() throws SQLException {
        //открываем БД
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close() {
        if (myDataBase != null)
            myDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.d(LOG_TAG, "onUpgrade. old: " + oldVersion + " new: " + newVersion);

    }


    public ArrayList<Manager> getManagersFromDB() {
        Cursor cursor = myDataBase.rawQuery("SELECT * FROM managers ORDER BY name;", new String[]{});

        ArrayList<Manager> managers = new ArrayList<>();
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

    public Manager getManagerFromDBById(int id) {
        Cursor cursor = myDataBase.rawQuery("SELECT * FROM managers WHERE _id = " + id + " LIMIT 1;", new String[]{});

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
}


