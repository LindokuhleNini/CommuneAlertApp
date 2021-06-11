package com.example.communealertapp;

import android.app.Application;

import com.backendless.Backendless;

public class TestApplication extends Application {

    public static final String APPLICATION_ID = "9ED63623-511B-0F48-FF0C-75D0CADBEA00";
    public static final String API_KEY = "6D9E49EE-B4AB-4953-99FB-EE1C242D9F17";
    public static final String SERVER_URL = "https://api.backendless.com";


    @Override
    public void onCreate() {
        super.onCreate();

        Backendless.setUrl( SERVER_URL );
        Backendless.initApp( getApplicationContext(),
                APPLICATION_ID,
                API_KEY );

    }
}
