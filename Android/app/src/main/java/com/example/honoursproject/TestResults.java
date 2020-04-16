//Project: Honours Project 2020
//Author: Aaron Stones
//Date: 10/04/2020
//Purpose: to show the benefits of collecting lots of data about a patient
//using different devices and tests

package com.example.honoursproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class TestResults extends AppCompatActivity {

    String email;
    String docName; //global variables

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_results);

        docName = getIntent().getStringExtra("doctor");
        email = getIntent().getStringExtra("email");

        getResults(); //get results of the test and setup strings from previous activity
    }

    private void getResults(){
        String type = "getResults";
        String type2 = "getResults";

        TestResultsWorker backgroundWorker = new TestResultsWorker(this);
        backgroundWorker.execute(type, type2, email); //retrieve the results
    }
}
