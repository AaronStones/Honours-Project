package com.example.honoursproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.hardware.SensorEventListener;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import static android.view.View.VISIBLE;


public class TakeTest extends AppCompatActivity implements SensorEventListener{

    private SensorManager senSensorManager;
    private Sensor senAccelerometer;
    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 25;

    String doctorName;
    String email;

    FormVerfication formCheck = new FormVerfication(this);

    int count = 0;
    int SYS;
    int DYS;
    int Weight;
    int Temp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_test);

        doctorName = getIntent().getStringExtra("doctor");
        email = getIntent().getStringExtra("email");




        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer , SensorManager.SENSOR_DELAY_NORMAL);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 0);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor mySensor = sensorEvent.sensor;

        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            long curTime = System.currentTimeMillis();

            if ((curTime - lastUpdate) > 100) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                float speed = Math.abs(x + y + z - last_x - last_y - last_z)/ diffTime * 10000;

                if (speed > SHAKE_THRESHOLD) {
                    count++;
                }

                last_x = x;
                last_y = y;
                last_z = z;

            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    protected void onPause() {
        super.onPause();
        senSensorManager.unregisterListener(this);
    }

    protected void onResume() {
        super.onResume();
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }


    public void Begin(View view){
        showWeights();
    }

    void showWeights(){
        EditText editText = findViewById(R.id.editText11);
        editText.setVisibility(VISIBLE);

        Button button = findViewById(R.id.button13);
        button.setVisibility(VISIBLE);
    }

    public void Weight(View view){
        EditText editText = findViewById(R.id.editText12);
        editText.setVisibility(VISIBLE);

        Button button = findViewById(R.id.button14);
        button.setVisibility(VISIBLE);

        editText = findViewById(R.id.editText11);
        if (Boolean.toString(formCheck.intCheck(editText.getText().toString())).equals("false")){
            Weight = Integer.parseInt(editText.getText().toString());
        }
        else{
            formCheck.errorFormat();
        }
    }

    public void Temp(View view){
        EditText editText = findViewById(R.id.editText13);
        editText.setVisibility(VISIBLE);

        Button button = findViewById(R.id.button15);
        button.setVisibility(VISIBLE);

        editText = findViewById(R.id.editText12);
        if (Boolean.toString(formCheck.intCheck(editText.getText().toString())).equals("false")){

        Temp =  Integer.parseInt(editText.getText().toString());
        }
        else{
            formCheck.errorFormat();
        }
    }

    public void BP(View view) throws JSONException {
        EditText editText = findViewById(R.id.editText13);

        String bp = editText.getText().toString();
        if (Boolean.toString(formCheck.intCheck(editText.getText().toString())).equals("false")){

            SYS = Integer.parseInt(bp.substring(0, 3));
            DYS = Integer.parseInt(bp.substring(4));
        }
        else{
            formCheck.errorFormat();
        }
        HR();
    }

    public void HR() throws JSONException {
        Intent intent;
        intent = new Intent(getApplicationContext(), Measure.class);

        JSONObject obj=new JSONObject();
        obj.put("email", email);
        obj.put("doctor", doctorName);
        obj.put("weight", Weight);
        obj.put("count", count);
        obj.put("temperature", Temp);
        obj.put("sys", SYS);
        obj.put("dys", DYS);

        if (Boolean.toString(formCheck.simpleCheck(Integer.toString(Weight))).equals("true") ||
                Boolean.toString(formCheck.simpleCheck(Integer.toString(Temp))).equals("true") ||
                Boolean.toString(formCheck.simpleCheck(Integer.toString(SYS))).equals("true")||
                Boolean.toString(formCheck.simpleCheck(Integer.toString(DYS))).equals("true"))
        {
            formCheck.error();
        }
        else {
            intent.putExtra("email", obj.toString());
            startActivity(intent);
        }
    }


}
