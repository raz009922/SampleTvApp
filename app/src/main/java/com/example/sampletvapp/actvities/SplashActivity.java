package com.example.sampletvapp.actvities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sampletvapp.R;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mainActivity();

            }
        };


        Handler handler = new Handler();
        handler.postDelayed(runnable, 2000);


    }

    public void mainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

