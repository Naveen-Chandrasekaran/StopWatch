package com.example.stopwatchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Chronometer chronometer;
    Button start,reset;
    boolean isRunning=false;
    // Initialize the elapsed time to 0
    long elapsedTime = 0;
    ImageView img;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chronometer = findViewById(R.id.chronometer);
        start = findViewById(R.id.startBtn);
        reset = findViewById(R.id.resetBtn);
        img=findViewById(R.id.img);



        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isRunning) {
                    // If the stopwatch is running, stop it
                    start.setText("Start");
                    start.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_start,0);
                    img.setBackgroundResource(R.drawable.stopwatchwall);
                    isRunning = false;
                    chronometer.stop();
                    // Store the elapsed time
                    elapsedTime = SystemClock.elapsedRealtime() - chronometer.getBase();
                } else {
                    // If the stopwatch is not running, start it
                    if (!isRunning) {
                        // Calculate the new base time by subtracting elapsed time
                        long newBase = SystemClock.elapsedRealtime() - elapsedTime;
                        chronometer.setBase(newBase);
                        start.setText("Stop");
                        start.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_stop_circle_24,0);
                        img.setBackgroundResource(R.drawable.sandclock1);
                        isRunning = true;
                        chronometer.start();
                    }
                }
            }
        });


        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Reset the Chronometer, set elapsed time to 0, and set isRunning to false
                chronometer.setBase(SystemClock.elapsedRealtime());
                elapsedTime = 0;
                isRunning = false;
            }
        });


    }


}