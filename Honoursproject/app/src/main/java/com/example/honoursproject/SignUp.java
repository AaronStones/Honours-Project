package com.example.honoursproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;


public class SignUp extends AppCompatActivity {
    private EditText email;
    private EditText email2;
    private EditText password;
    private EditText password2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void Login(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);
    }

    public void Signup(View view){
        email = findViewById(R.id.editText7);
        email2 = findViewById(R.id.editText);

        String Email = email.getText().toString();
        String Email2 = email2.getText().toString();

        password= findViewById(R.id.editText5);
        password2= findViewById(R.id.editText2);

        String Password = password.getText().toString();
        String Password2 = password2.getText().toString();

        if (Email.equals(Email2) && Password.equals(Password2)){
            String type = "signup";
            EditText doctor = findViewById(R.id.editText8);
            String Doctor = doctor.getText().toString();
            Boolean Park = ((CheckBox) findViewById(R.id.CheckBox1)).isChecked();
            Boolean Dem = ((CheckBox) findViewById(R.id.CheckBox2)).isChecked();
            Boolean Other = ((CheckBox) findViewById(R.id.CheckBox3)).isChecked();
            AccountVerification backgroundWorker = new AccountVerification(this);
            backgroundWorker.execute(type, Email, Password, Doctor, Boolean.toString(Park), Boolean.toString(Dem), Boolean.toString(Other));
        }
    }





}
