package com.example.honoursproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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

import static android.view.View.VISIBLE;


public class TakeTest extends AppCompatActivity implements SensorEventListener{

    private SensorManager senSensorManager;
    private Sensor senAccelerometer;
    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 400;

    int count = -5;
    int SYS;
    int DYS;
    int HR;
    int Weight;
    int Temp;

    String email = "stonesclan090@gmail.com";
    String doctorName = "Geoffrey Lund";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_test);

        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer , SensorManager.SENSOR_DELAY_NORMAL);
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
                    EditText editText = findViewById(R.id.editText4);
                    String thing = "Shake Detected " + Integer.toString(count);
                    editText.setText(thing);
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
        Button begin = findViewById(R.id.button12);
        begin.setVisibility(view.INVISIBLE);
        TextView textView = findViewById(R.id.textView16);
        textView.setText("please wait for the beep, then place the phone in the palm of your hand and wait until you hear the second beep");

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                ToneGenerator toneGen1 = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
                toneGen1.startTone(ToneGenerator.TONE_CDMA_PIP,200);

                final Handler handler2 = new Handler();
                handler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Do something after 5s = 5000ms

                        ToneGenerator toneGen1 = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
                        toneGen1.startTone(ToneGenerator.TONE_CDMA_PIP,200);


                    }
                }, 2000);
            }
        }, 5000);
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

        Weight =  Integer.parseInt(editText.getText().toString());

    }

    public void Temp(View view){
        EditText editText = findViewById(R.id.editText13);
        editText.setVisibility(VISIBLE);

        Button button = findViewById(R.id.button15);
        button.setVisibility(VISIBLE);

        editText = findViewById(R.id.editText12);

        Temp =  Integer.parseInt(editText.getText().toString());

    }

    public void BP(View view){
        EditText editText = findViewById(R.id.editText10);
        editText.setVisibility(VISIBLE);

        Button button = findViewById(R.id.button16);
        button.setVisibility(VISIBLE);

        editText = findViewById(R.id.editText13);

        String bp = editText.getText().toString();
        SYS = Integer.parseInt(bp.substring(0, 3));
        DYS = Integer.parseInt(bp.substring(4));
    }

    public void HR(View view){
        EditText editText = findViewById(R.id.editText10);
        HR = Integer.parseInt(editText.getText().toString());
        writeToDatabase();
    }

    public void writeToDatabase(){
        String counts = Integer.toString(count);
        String weights = Integer.toString(Weight);
        String temperature = Integer.toString(Temp);
        String sys = Integer.toString(SYS);
        String dys = Integer.toString(DYS);
        String hr = Integer.toString(HR);
        String type = "result";

        TakeTestWorker backgroundWorker = new TakeTestWorker(this);
        backgroundWorker.execute(type, email, doctorName, counts, hr, weights, temperature, sys, dys);
    }
}
