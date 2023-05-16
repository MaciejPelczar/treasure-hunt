package com.example.treasurehunt;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener{

    Random random = new Random();
    int randValue;
    int numberOfTries;
    TextView textHits;
    Boolean isTreasureFind = false;
    TextView textTreasureCollected;
    TextView textMoneyToEarn;
    TextView textMoneyToLose;
    int earn;
    int lose;
    int valueInt=0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        ImageView imageView1 = findViewById(R.id.imageView1);
        imageView1 .setOnClickListener(this);
        ImageView imageView2 = findViewById(R.id.imageView2);
        imageView2 .setOnClickListener(this);
        ImageView imageView3 = findViewById(R.id.imageView3);
        imageView3 .setOnClickListener(this);
        ImageView imageView4 = findViewById(R.id.imageView4);
        imageView4 .setOnClickListener(this);
        ImageView imageView5 = findViewById(R.id.imageView5);
        imageView5 .setOnClickListener(this);
        ImageView imageView6 = findViewById(R.id.imageView6);
        imageView6 .setOnClickListener(this);
        ImageView imageView7 = findViewById(R.id.imageView7);
        imageView7 .setOnClickListener(this);
        ImageView imageView8 = findViewById(R.id.imageView8);
        imageView8 .setOnClickListener(this);
        ImageView imageView9 = findViewById(R.id.imageView9);
        imageView9 .setOnClickListener(this);
        ImageView imageView10 = findViewById(R.id.imageView10);
        imageView10.setOnClickListener(this);
        ImageView imageView11 = findViewById(R.id.imageView11);
        imageView11.setOnClickListener(this);
        ImageView imageView12 = findViewById(R.id.imageView12);
        imageView12.setOnClickListener(this);
        ImageView imageView13 = findViewById(R.id.imageView13);
        imageView13.setOnClickListener(this);
        ImageView imageView14 = findViewById(R.id.imageView14);
        imageView14.setOnClickListener(this);
        ImageView imageView15 = findViewById(R.id.imageView15);
        imageView15.setOnClickListener(this);
        ImageView imageView16 = findViewById(R.id.imageView16);
        imageView16.setOnClickListener(this);
        ImageView imageView17 = findViewById(R.id.imageView17);
        imageView17.setOnClickListener(this);
        ImageView imageView18 = findViewById(R.id.imageView18);
        imageView18.setOnClickListener(this);
        ImageView imageView19 = findViewById(R.id.imageView19);
        imageView19.setOnClickListener(this);
        ImageView imageView20 = findViewById(R.id.imageView20);
        imageView20.setOnClickListener(this);
        ImageView imageView21 = findViewById(R.id.imageView21);
        imageView21.setOnClickListener(this);
        ImageView imageView22 = findViewById(R.id.imageView22);
        imageView22.setOnClickListener(this);
        ImageView imageView23 = findViewById(R.id.imageView23);
        imageView23.setOnClickListener(this);
        ImageView imageView24 = findViewById(R.id.imageView24);
        imageView24.setOnClickListener(this);
        ImageView imageView25 = findViewById(R.id.imageView25);
        imageView25.setOnClickListener(this);
        ImageView imageView26 = findViewById(R.id.imageView26);
        imageView26.setOnClickListener(this);
        ImageView imageView27 = findViewById(R.id.imageView27);
        imageView27.setOnClickListener(this);
        ImageView imageView28 = findViewById(R.id.imageView28);
        imageView28.setOnClickListener(this);
        ImageView imageView29 = findViewById(R.id.imageView29);
        imageView29.setOnClickListener(this);
        ImageView imageView30 = findViewById(R.id.imageView30);
        imageView30.setOnClickListener(this);
        ImageView imageView31 = findViewById(R.id.imageView31);
        imageView31.setOnClickListener(this);
        ImageView imageView32 = findViewById(R.id.imageView32);
        imageView32.setOnClickListener(this);
        ImageView imageView33 = findViewById(R.id.imageView33);
        imageView33.setOnClickListener(this);
        ImageView imageView34 = findViewById(R.id.imageView34);
        imageView34.setOnClickListener(this);
        ImageView imageView35 = findViewById(R.id.imageView35);
        imageView35.setOnClickListener(this);
        ImageView imageView36 = findViewById(R.id.imageView36);
        imageView36.setOnClickListener(this);

        Intent intent = getIntent();
        numberOfTries = intent.getIntExtra("numberOfTries",0);
        textHits = findViewById(R.id.textHits);
        textHits.setText(""+numberOfTries);
        randValue= random.nextInt(36);


        ImageView buttonEarnedMoney = findViewById(R.id.buttonEarnMoney);
        buttonEarnedMoney.setVisibility(View.INVISIBLE);
        buttonEarnedMoney.setOnClickListener(this);

        textTreasureCollected = findViewById(R.id.textTreasureCollect);
        textMoneyToEarn = findViewById(R.id.textToEarn);
        textMoneyToLose = findViewById(R.id.textToLose);

        lose = 0;
        earn = 3600;
        textMoneyToEarn.setText("3600");

        SharedPreferences prefMaxButton=getSharedPreferences("SETTINGS", Context.MODE_PRIVATE);
        valueInt=prefMaxButton.getInt("Treasure", 0);
        textTreasureCollected.setText(""+valueInt);
    }

    @Override
    public void onClick(View view) {
        int imageViewId = view.getId();
        ImageView image = findViewById(imageViewId);
        int colorWin = Color.parseColor("#FFFFFF");
        int color = Color.parseColor("#000000");
        Log.d("MainActivity2", String.format("You find the TREASURE!!"+imageViewId+" Random: "+randValue));
//        image.setColorFilter(colorWin);
        Intent intent = new Intent(MainActivity2.this, MainActivity.class );

        if (isTreasureFind){
            intent.putExtra("money", earn);
            startActivity(intent);

        }else {
            numberOfTries--;
            earn -= 100;
            lose += 100;

            if (numberOfTries >= 0) {
                textHits.setText("" + numberOfTries);
                textMoneyToEarn.setText("" + earn);
                textMoneyToLose.setText("" + lose);
                if (2131231200 + randValue == imageViewId) {
                    image.setColorFilter(colorWin);
                    isTreasureFind = true;
                    Toast.makeText(MainActivity2.this, "You find the TREASURE!!", Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity2.this, "You earnd: "+earn+"!!!", Toast.LENGTH_LONG).show();
                    findViewById(R.id.buttonEarnMoney).setVisibility(View.VISIBLE);


                } else {
                    if(numberOfTries == 0) {
                        findViewById(R.id.buttonEarnMoney).setVisibility(View.VISIBLE);
                    }
                    image.setColorFilter(color);
                }

            } else{
//                image.setColorFilter(color);
                Toast.makeText(MainActivity2.this, "You didn't find the TREASURE!!", Toast.LENGTH_SHORT).show();
                                Toast.makeText(MainActivity2.this, "You lose " + (lose-100) + "!!", Toast.LENGTH_SHORT).show();
//                int daneInt = Integer.parseInt(textMoneyToLose.getText().toString());
                intent.putExtra("money", ((lose-100)*(-1)));
                startActivity(intent);
            }
        }
    }
    @Override
    public void onBackPressed() {
    }
}