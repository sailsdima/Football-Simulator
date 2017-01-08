package com.example.sails.footballsimulator;

import android.app.Application;
import android.content.Context;

/**
 * Created by sails on 07.01.2017.
 */

public class App extends Application {

    static Context appContext;
//
//    public App(){
//        appContext = getApplicationContext();
//    }

    @Override
    public void onCreate() {
        super.onCreate();

        appContext = getApplicationContext();
    }

    public static Context getAppContext(){
        return appContext;
    }

}
