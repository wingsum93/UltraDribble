package com.ericho.ultradribble;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class App extends Application {

    private static AppConfig sAppConfig;

    @Override
    public void onCreate() {
        super.onCreate();
        sAppConfig = new AppConfig(this);
        Stetho.initializeWithDefaults(this);
    }



    public static AppConfig getAppConfig() {
        return sAppConfig;
    }

}
