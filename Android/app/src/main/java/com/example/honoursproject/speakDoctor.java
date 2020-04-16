//Project: Honours Project 2020
//Author: Aaron Stones
//Date: 10/04/2020
//Purpose: to show the benefits of collecting lots of data about a patient
//using different devices and tests

package com.example.honoursproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;

import org.json.JSONObject;

public class speakDoctor extends AppCompatActivity {
    String doctorName;
    String email;

    FormVerfication formCheck = new FormVerfication(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speak_doctor);

        doctorName = getIntent().getStringExtra("doctor"); //retrieve the doctor and the user's email from the previous activity
        email = getIntent().getStringExtra("email");

        getMessages();
    }

    private void getMessages(){
        String type = "getMessages";

        messagesWorker backgroundWorker = new messagesWorker(this);
        backgroundWorker.execute(type, email, doctorName); //start the background worker to fetch the previous messages


    }

    public void sendMessage(View view){ //send a message to server
        String type = "sendMessage";
        EditText editText = findViewById(R.id.editText4);
        String Message = editText.getText().toString();

        if (Boolean.toString(formCheck.simpleCheck(Message)).equals("true")){ //check the message sent passes security checks
            formCheck.error();
        }
        else {
            messagesWorker backgroundWorker = new messagesWorker(this);
            backgroundWorker.execute(type, email, Message); //message passes send to the background worker

            getMessages(); //update the message thread
        }
    }
}
