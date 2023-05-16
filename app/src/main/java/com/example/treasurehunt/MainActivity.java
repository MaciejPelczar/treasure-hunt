package com.example.treasurehunt;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Objects;
import java.util.Random;

public final class MainActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private float acceleration;
    private float currentAcceleration;
    private float lastAcceleration;

    TextView randNumber;
    TextView treasure;
    Button goToActivity2;
    Button reset;
    int valueInt=0;
    int valueIntSend=0;

    boolean isRandomize;
    int money;

    private final SensorEventListener sensorListener = (new SensorEventListener() {
        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            lastAcceleration = currentAcceleration;
            double temp = (x * x + y * y + z * z);
            currentAcceleration = (float)Math.sqrt(temp);
            float delta = currentAcceleration - lastAcceleration;
            acceleration = acceleration * 0.9F + delta;

            if (acceleration > (float)10 && !isRandomize ) {
                randNumber = findViewById(R.id.textRandNumber);
                Random random = new Random();
                randNumber.setText(String.valueOf(random.nextInt(36)+1));
                isRandomize = !isRandomize;
                goToActivity2 = findViewById(R.id.goToActivity2);
                goToActivity2.setVisibility(View.VISIBLE);
            }
        }
        public void onAccuracyChanged(Sensor sensor, int accuracy) {}
    });

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().add(R.id.frame_layout, new Fragment1()).commit();

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        Objects.requireNonNull(sensorManager)
                .registerListener(sensorListener, sensorManager
                        .getDefaultSensor(1), 3);

        acceleration = 10.0F;
        currentAcceleration = SensorManager.GRAVITY_EARTH;
        lastAcceleration = SensorManager.GRAVITY_EARTH;
        isRandomize = false;

        SharedPreferences prefMaxButton=getSharedPreferences("SETTINGS", Context.MODE_PRIVATE);
        valueInt=prefMaxButton.getInt("Treasure", 0);

        Intent intent = getIntent();
        money = intent.getIntExtra("money",0);
        valueInt = valueInt+money;

        treasure = findViewById(R.id.textTreasureCollect);
        treasure.setText(""+valueInt);

        SharedPreferences sharedPreferences=getSharedPreferences("SETTINGS", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("Treasure", valueInt);
        editor.commit();

        reset = findViewById(R.id.buttonReset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int reset = 0;
                editor.putInt("Treasure", reset);
                editor.commit();
                treasure.setText("0");
            }
        });
    }

    protected void onResume() {
        sensorManager.registerListener(sensorListener, sensorManager.getDefaultSensor(
                Sensor .TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        super.onResume();
    }

    protected void onPause() {
        sensorManager.unregisterListener(sensorListener);
        super.onPause();
    }

    public void actionFragment1(View v){
        replaceFragment(new Fragment1());
    }
    public void actionFragment2(View v){
        replaceFragment(new Fragment2());
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
    @Override
    public void onBackPressed() {
    }
}
