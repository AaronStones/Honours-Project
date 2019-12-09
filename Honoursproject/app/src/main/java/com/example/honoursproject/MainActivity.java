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
        String emailSignup;
        try

        {
            emailSignup = getIntent().getStringExtra("emailSignup");
        }
        catch(Exception e){
            emailSignup = "";
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText editText = (EditText)findViewById(R.id.editText);
        editText.setText(emailSignup);
    }
    public void Login(View view){
        email= findViewById(R.id.editText);
        password= findViewById(R.id.editText2);
        String Password = password.getText().toString();
        String Email = email.getText().toString();
        String type = "login";
        AccountVerification backgroundWorker = new AccountVerification(this);
        backgroundWorker.execute(type, Email, Password);
    }

    public void SignUp(View view){
        Intent intent = new Intent(this, SignUp.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);
    }
}
