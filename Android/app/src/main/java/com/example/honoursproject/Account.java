package com.example.honoursproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


import org.json.JSONObject;


public class Account extends AppCompatActivity {

    JSONObject userJson;
    String doctorName;
    String json;
    String email;
    String Names;
    String Mobile;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        setupVariables();
        getResults();

        TextView Name = findViewById(R.id.textView7);
        Name.setText("Welcome Back : " + Names);
        TextView Doctor = findViewById(R.id.textView6);
        Doctor.setText("Doctor : Dr " + doctorName);

    }
    private Void setupVariables(){
        String userInfo = getIntent().getStringExtra("EXTRA_SESSION_ID");

        try {
            userJson = new JSONObject(userInfo);
            doctorName = userJson.getString("doctor");
            email = userJson.getString("email");
            json = userJson.getString("json");

        }
        catch(Exception e){
            Log.e("failed",userInfo);
        }

        try {
            JSONObject userJson = new JSONObject(json);

            Mobile = userJson.getString("Mobile");
            Names = userJson.getString("name");
        }

        catch (Exception e) {
            Log.d("fail",json);
            Log.d("fail","fail");
        }

        return null;
    }

    private void getResults(){
        String type = "getResults";
        String type2 = "retrieveResults";

        TestResultsWorker backgroundWorker = new TestResultsWorker(this);
        backgroundWorker.execute(type, type2, email);

    }


    public void viewResults(View view){

        Intent intent = new Intent(this, TestResults.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("email", email);
        intent.putExtra("doctor", doctorName);
        this.startActivity(intent);
    }

    public void changeInfo(View view){

        Intent intent = new Intent(this, UpdateInformation.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("email", email);
        intent.putExtra("json", json);
        intent.putExtra("doctor", doctorName);

        this.startActivity(intent);
    }

    public void takeTest(View view){

        Intent intent = new Intent(this, TakeTest.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("email", email);
        intent.putExtra("doctor", doctorName);
        intent.putExtra("value", 0);
        this.startActivity(intent);
    }

    public void doctorsNotes(View view){

        Intent intent = new Intent(this, DoctorsAdvice.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("email", email);
        intent.putExtra("doctor", doctorName);
        this.startActivity(intent);
    }

    public void Questions(View view){

        Intent intent = new Intent(this, speakDoctor.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("email", email);
        intent.putExtra("doctor", doctorName);
        this.startActivity(intent);
    }

}
