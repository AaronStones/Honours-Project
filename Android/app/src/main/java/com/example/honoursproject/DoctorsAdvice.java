package com.example.honoursproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;


public class DoctorsAdvice extends AppCompatActivity {
    String doctorName;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_advice);
        setupVariable();
        getAdvice();
    }
    private void setupVariable(){
        email = getIntent().getStringExtra("email");
        doctorName = getIntent().getStringExtra("doctor");
    }

    private void getAdvice(){
        String type = "Advice";

        DoctorsAdviceWorker backgroundWorker = new DoctorsAdviceWorker(this);
        backgroundWorker.execute(type, email);

    }


}
