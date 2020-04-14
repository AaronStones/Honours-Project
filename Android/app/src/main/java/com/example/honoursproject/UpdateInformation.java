package com.example.honoursproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UpdateInformation extends AppCompatActivity {
    String doctorName;
    String email;
    String json;
    String Name;
    String Mobile;
    Boolean Parkinsons;
    Boolean Other;
    Boolean Dementia;

    FormVerfication formCheck = new FormVerfication(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_information);

        setupFields();
    }


    private Void setupFields(){


        doctorName = getIntent().getStringExtra("doctor");
        email = getIntent().getStringExtra("email");
        json = getIntent().getStringExtra("json");

        EditText doctor = findViewById(R.id.editText6);
        doctor.setText(doctorName);

        try {
            JSONObject userJson = new JSONObject(json);

            Mobile = userJson.getString("Mobile");
            Name = userJson.getString("name");
            Parkinsons = userJson.getBoolean("Parkinsons");
            Dementia = userJson.getBoolean("Dementia");
            Other = userJson.getBoolean("Other");

            EditText Phone = findViewById(R.id.editText9);
            Phone.setText(Mobile);

            EditText patientName = findViewById(R.id.editText3);
            patientName.setText(Name);
        }

        catch (Exception e) {
            Log.d("fail",json);
            Log.d("fail","fail");
        }




        return null;
    }

    public void nameChange(View v ){

        try {
            EditText editText = findViewById(R.id.editText3);

            Name = editText.getText().toString();
            if (Boolean.toString(formCheck.simpleCheck(Mobile)).equals("true")){
                formCheck.errorFormat();
            }
            else {
                JSONObject jsonObject = new JSONObject(json);
                jsonObject.remove("name");
                jsonObject.put("name", Name);
                json = jsonObject.toString();
            }
        }
        catch (JSONException e){
            formCheck.error();
            e.printStackTrace();
        }
        String file = json;

        String type = "Json";
        if (Boolean.toString(formCheck.simpleCheck(Mobile)).equals("true")){
            formCheck.error();
        }
        else {
            UpdateInformationWorker backgroundWorker = new UpdateInformationWorker(this);
            backgroundWorker.execute(type, file, email, "nameChange");
        }
    }
    public void doctorChange(View v ){
        String type = "Doctor";
        EditText editText = findViewById(R.id.editText6);
        String doctorName = editText.getText().toString();
        if (Boolean.toString(formCheck.mobileCheck(Mobile)).equals("true")){
            formCheck.errorFormat();
        }
        else {
            if (Boolean.toString(formCheck.simpleCheck(doctorName)).equals("true")) {
                formCheck.error();
            } else {
                UpdateInformationWorker backgroundWorker = new UpdateInformationWorker(this);
                backgroundWorker.execute(type, doctorName, email, "doctorChange");
            }
        }
    }

    public void mobileChange(View v ){
        try {
            EditText editText = findViewById(R.id.editText9);
            Mobile = editText.getText().toString();

            if (Boolean.toString(formCheck.intCheck(Mobile)).equals("true")){
                formCheck.errorFormat();
            }
            else {

                JSONObject jsonObject = new JSONObject(json);
                jsonObject.remove("Mobile");
                jsonObject.put("Mobile", Mobile);
                json = jsonObject.toString();
            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        Log.d("json",json);
        String file = json;
        String type = "Json";

        if (Boolean.toString(formCheck.simpleCheck(Mobile)).equals("true")){
            formCheck.error();
        }
        else {
            UpdateInformationWorker backgroundWorker = new UpdateInformationWorker(this);

            backgroundWorker.execute(type, file, email, "mobileChange");
        }

    }
}
