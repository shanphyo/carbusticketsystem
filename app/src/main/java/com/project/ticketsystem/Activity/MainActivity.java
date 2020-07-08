package com.project.ticketsystem.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.chillibits.splashscreen.SplashScreenBuilder;
import com.project.ticketsystem.R;

import java.util.concurrent.Delayed;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        splash();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                intentProcess();
            }
        }, 5000);


    }

    private void intentProcess() {
        Intent intent = new Intent(MainActivity.this, Login.class);
        startActivity(intent);
        finish();
    }

    private void splash() {
        SplashScreenBuilder.Companion.getInstance(MainActivity.this)

                .setTitle("Car Ticket System")
                .setImage(R.raw.car)
                .setSubtitle("Majjhima Sukha ")
                .setVideo(R.raw.splash_animation)
                .enableSkipOnTap(false)
                .setTextFadeInDuration(3000)
                .show();
    }
}
