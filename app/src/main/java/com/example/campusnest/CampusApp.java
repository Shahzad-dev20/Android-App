package com.example.campusnest;

import android.app.Application;

public class CampusApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppSettings.applySavedTheme(this);
        UserStore.init(this);
    }
}