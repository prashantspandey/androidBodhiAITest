package com.example.prashant.bodhiai.Infrastructure;

import android.app.Application;

public class BodhiApplication extends Application{

    private Auth auth;

    @Override
    public void onCreate() {
        super.onCreate();
        auth = new Auth(this);
    }

    public Auth getAuth() {
        return auth;
    }
}
