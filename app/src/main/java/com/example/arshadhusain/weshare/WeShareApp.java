package com.example.arshadhusain.weshare;

import android.app.Application;
import android.content.Context;

/**
 * Created by hasan on 2016-03-10.
 */
public class WeShareApp extends Application {
    transient public static EntryList app = null;
    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            appContext = getApplicationContext();

        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    /* create AppController */
    transient static public AppController controller = null;
    public static AppController getController() {
        if (controller == null) {
            controller = new AppController();
        }
        return controller;
    }

    /* taken from:
     * http://stackoverflow.com/questions/21994612/get-application-context-returns-null */
    public static Context getContext() {
        return appContext;
    }

    public static EntryList getApp() {
        if (app == null) {
            app = new EntryList();
        }
        return app;
    }
}
