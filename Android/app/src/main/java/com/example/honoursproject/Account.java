package com.example.honoursproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import org.json.JSONObject;

public class Account extends AppCompatActivity {

    JSONObject userJson;
    String doctorName;
    String json;
    String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        setupVariables();
        TextView Name = findViewById(R.id.textView7);
        Name.setText("Welcome Back : " + email);
        TextView Doctor = findViewById(R.id.textView6);
        Doctor.setText("Doctor : Dr " + doctorName);
    }
    private Void setupVariables(){
        try {
            String userInfo = getIntent().getStringExtra("EXTRA_SESSION_ID");
            userJson = new JSONObject(userInfo);
            doctorName = userJson.getString("doctor");
            email = userJson.getString(("email"));
            json = userJson.getString("json");


        }
        catch(Exception e){
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            this.startActivity(intent);
        }

        return null;
    }

    public void viewResults(View view){

        Intent intent = new Intent(this, TestResults.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("email", email);
        intent.putExtra("doctor", doctorName);
        this.startActivity(intent);
    }

    public void testing(View view){

        Intent intent = new Intent(this, thisIsaTest.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("email", email);
        intent.putExtra("json", json);
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
