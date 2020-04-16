package com.example.honoursproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void Login(View view){ //get the login details and send them to a back ground worker
        email= findViewById(R.id.editText);
        password= findViewById(R.id.editText2);
        String Password = password.getText().toString();
        String Email = email.getText().toString();
        String type = "login";
        AccountVerification backgroundWorker = new AccountVerification(this);
        backgroundWorker.execute(type, Email, Password);
    }

    public void SignUp(View view){ //redirect to the signup page
        Intent intent = new Intent(this, SignUp.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);
    }
}
