package com.example.honoursproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class TestResults extends AppCompatActivity {

    String email;
    String docName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_results);

        docName = getIntent().getStringExtra("doctor");
        email = getIntent().getStringExtra("email");

        getResults();
    }

    private void getResults(){
        String type = "getResults";
        String type2 = "getResults";

        TestResultsWorker backgroundWorker = new TestResultsWorker(this);
        backgroundWorker.execute(type, type2, email);
    }
}
