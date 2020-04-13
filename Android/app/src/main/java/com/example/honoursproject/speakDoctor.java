package com.example.honoursproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.json.JSONObject;

public class speakDoctor extends AppCompatActivity {
    String doctorName;
    String email;

    FormVerfication formVerfication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speak_doctor);

        doctorName = getIntent().getStringExtra("doctor");
        email = getIntent().getStringExtra("email");

        getMessages();
    }

    private void getMessages(){
        String type = "getMessages";

        messagesWorker backgroundWorker = new messagesWorker(this);
        backgroundWorker.execute(type, email, doctorName);
    }

    public void sendMessage(View view){
        String type = "sendMessage";
        EditText editText = findViewById(R.id.editText4);
        String Message = editText.getText().toString();

        if (formVerfication.simpleCheck(Message).toString().equals("true")){
            formVerfication.error();
        }
        else {
            messagesWorker backgroundWorker = new messagesWorker(this);
            backgroundWorker.execute(type, email, Message);

            getMessages();
        }
    }
}
