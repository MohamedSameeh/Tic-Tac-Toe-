package com.example.finalprojectforjavaandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

public class SplashScreen extends AppCompatActivity {
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        img = findViewById(R.id.img_view);

        img.postDelayed(() -> {
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(SplashScreen.this);
            boolean isLoggedIn = sp.getBoolean("Logged_in", false);
            Intent intent;
            if (isLoggedIn) {
                intent = new Intent(SplashScreen.this, MainActivity.class);
            } else {
                intent = new Intent(SplashScreen.this, logIn.class);
            }
            startActivity(intent);
            finish();
        }, 2000);
    }
}
