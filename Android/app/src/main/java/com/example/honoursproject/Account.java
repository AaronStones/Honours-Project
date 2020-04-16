//Project: Honours Project 2020
//Author: Aaron Stones
//Date: 10/04/2020
//Purpose: to show the benefits of collecting lots of data about a patient
//using different devices and tests

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
    String Mobile; //global variables



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        String newAccount;
        try

        {

            newAccount = getIntent().getStringExtra("newAccount"); //check if a new account has been created
        }
        catch(Exception e){
            newAccount = "";
        }

        setupVariables();

        if (newAccount.equals("true")){

        }
        else {
            getResults();//new account hasn't been created fetch existing results
        }
        TextView Name = findViewById(R.id.textView7);
        Name.setText("Welcome Back : " + Names);
        TextView Doctor = findViewById(R.id.textView6);
        Doctor.setText("Doctor : Dr " + doctorName); //display the user's data

    }
    private Void setupVariables(){ //decode intents passed by previous functions
        String userInfo = getIntent().getStringExtra("EXTRA_SESSION_ID");

        try {
            userJson = new JSONObject(userInfo);
            doctorName = userJson.getString("doctor");
            email = userJson.getString("email");
            json = userJson.getString("json"); //get the details from those intents

        }
        catch(Exception e){
            Log.e("failed",userInfo);
        }

        try {
            JSONObject userJson = new JSONObject(json);

            Mobile = userJson.getString("Mobile");
            Names = userJson.getString("name"); //get name and mobile number
        }

        catch (Exception e) {
            Log.d("fail",json);
            Log.d("fail","fail"); //failed log why
        }

        return null;
    }

    private void getResults(){ //get results through a background worker
        String type = "getResults";
        String type2 = "retrieveResults";

        TestResultsWorker backgroundWorker = new TestResultsWorker(this);
        backgroundWorker.execute(type, type2, email); //pass the type of request this is and the email

    }


    public void viewResults(View view){ //redirect to the view results activity

        Intent intent = new Intent(this, TestResults.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("email", email);
        intent.putExtra("doctor", doctorName);
        this.startActivity(intent);
    }

    public void changeInfo(View view){ //redirect to the view change information activity

        Intent intent = new Intent(this, UpdateInformation.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("email", email);
        intent.putExtra("json", json);
        intent.putExtra("doctor", doctorName);

        this.startActivity(intent);
    }

    public void takeTest(View view){ //redirect to the Take test activity

        Intent intent = new Intent(this, TakeTest.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("email", email);
        intent.putExtra("doctor", doctorName);
        intent.putExtra("value", 0);
        this.startActivity(intent);
    }

    public void doctorsNotes(View view){ //redirect to the doctor's notes activity

        Intent intent = new Intent(this, DoctorsAdvice.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("email", email);
        intent.putExtra("doctor", doctorName);
        this.startActivity(intent);
    }

    public void Questions(View view){//redirect to the consult a doctor activity

        Intent intent = new Intent(this, speakDoctor.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("email", email);
        intent.putExtra("doctor", doctorName);
        this.startActivity(intent);
    }

}
