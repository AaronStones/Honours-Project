package com.example.honoursproject;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;


public class thisIsaTest extends AppCompatActivity {

    private static final int REQUEST_ENABLED = 0;
    private static final int ACTION_REQUEST_DISABLE = 0;
    private static final int REQUEST_DISCOVERABLE = 0;

    Button b_on;
    Button b_off;
    Button b_disc;
    Button b_list;

    ListView l;

    BluetoothAdapter bluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d("myTag", "point 1");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_this_isa_test);

        Log.d("myTag", "point 2");

        b_on = (Button) findViewById(R.id.db_on);
        b_off = (Button) findViewById(R.id.db_off);
        b_disc = (Button) findViewById(R.id.db_disc);
        b_list = (Button) findViewById(R.id.db_list);

        Log.d("myTag", "point 3");

        l = (ListView) findViewById(R.id.list);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        Log.d("myTag", "point 4");

        if (bluetoothAdapter == null){
            Toast.makeText(this,"bluetooth not enabled!", Toast.LENGTH_SHORT);

        }

        Log.d("myTag", "point 4");


        b_on.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(intent, REQUEST_ENABLED);
            }
        });

        b_off.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View view){
                bluetoothAdapter.disable();
            }
        });

        b_disc.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if (!bluetoothAdapter.isDiscovering()){
                    Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                    startActivityForResult(intent, REQUEST_DISCOVERABLE );

                }
            }
        });

        b_list.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();

                ArrayList<String> devices = new ArrayList<String>();

                for (BluetoothDevice bt : pairedDevices){
                    devices.add("Name : " + bt.getName() + "MAC Address : " + bt.getAddress());

                }

                ArrayAdapter arrayAdapter = new ArrayAdapter(thisIsaTest.this, android.R.layout.simple_list_item_1, devices);

                l.setAdapter(arrayAdapter);

            }
        });

    }
}
