package com.example.prashant.bodhiai.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.prashant.bodhiai.Infrastructure.BodhiApplication;

public class BaseActivity extends AppCompatActivity{

    protected BodhiApplication application;

    @Override
    protected void onCreate(Bundle savedBundle){
        super.onCreate(savedBundle);

        application = (BodhiApplication) getApplication();
    }
}
