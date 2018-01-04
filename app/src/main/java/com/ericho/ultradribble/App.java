package com.ericho.ultradribble;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.facebook.stetho.Stetho;

import io.fabric.sdk.android.Fabric;
import io.realm.Realm;

public class App extends Application {

    private static AppConfig sAppConfig;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        sAppConfig = new AppConfig(this);
        Stetho.initializeWithDefaults(this);
        Realm.init(this);
    }



    public static AppConfig getAppConfig() {
        return sAppConfig;
    }

}
