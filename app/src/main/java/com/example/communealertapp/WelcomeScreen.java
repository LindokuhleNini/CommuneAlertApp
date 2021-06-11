package com.example.communealertapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
    }

    public void openLogin(View v){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    public void openRegister(View v){
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);
    }
}