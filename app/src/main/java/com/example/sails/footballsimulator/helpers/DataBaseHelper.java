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

import com.example.sails.footballsimulator.App;
import com.example.sails.footballsimulator.activities.MainActivity;
import com.example.sails.footballsimulator.entity.Manager;

/**
 * Created by sails on 05.01.2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    private static DataBaseHelper dbInstance;

    private static final String LOG_TAG = "debug";

    // путь к базе данных вашего приложения
    private static String DB_PATH = "/data/data/com.example.sails.footballsimulator/databases/";
    private static String DB_NAME = "football";
    private SQLiteDatabase myDataBase;
    private final Context mContext;

    public static synchronized DataBaseHelper getInstance() {
        if (null == dbInstance) {
            dbInstance = new DataBaseHelper(App.getAppContext());
        }
        return dbInstance;
    }

    private DataBaseHelper(Context context) {
        super(context, DB_NAME, null, 2);
        this.mContext = context;
        createDataBase();
        openDataBase();
    }

    private void createDataBase() {
        boolean dbExist = checkDataBase();
        if (!dbExist) {
            copyDataBase();
        }
    }

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

    private void copyDataBase() {
        //Открываем локальную БД как входящий поток
        InputStream myInput = null;
        try {
            myInput = mContext.getAssets().open(DB_NAME);

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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openDataBase() {
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

    }


}


