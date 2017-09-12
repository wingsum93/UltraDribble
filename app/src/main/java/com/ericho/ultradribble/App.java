package com.ericho.ultradribble;

import android.app.Application;

public class App extends Application {

    private static AppConfig sAppConfig;

    @Override
    public void onCreate() {
        super.onCreate();
        sAppConfig = new AppConfig(this);

    }



    public static AppConfig getAppConfig() {
        return sAppConfig;
    }

}
