package com.example.communealertapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ReportCrime extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_crime);
    }

    public void openGroupScreen(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openCarTheftForm(View v){
        Intent intent = new Intent(this, CarVandalismForm.class);
        startActivity(intent);
    }

    public void openHouseBreakinForm(View v){
        Intent intent = new Intent(this, HouseBreakinForm.class);
        startActivity(intent);
    }

    public void openMissingPersonForm(View v){
        Intent intent = new Intent(this, MissingPersonForm.class);
        startActivity(intent);
    }

    public void openSuspiciousPersonForm(View v){
        Intent intent = new Intent(this, SuspiciousPersonForm.class);
        startActivity(intent);
    }

    public void openViolenceForm(View v){
        Intent intent = new Intent(this, ViolenceForm.class);
        startActivity(intent);
    }

    public void openTheftForm(View v){
        Intent intent = new Intent(this, TheftForm.class);
        startActivity(intent);
    }

    public void openReportCrime(View v){
        Intent intent = new Intent(this, ReportCrime.class);
        startActivity(intent);
    }

}