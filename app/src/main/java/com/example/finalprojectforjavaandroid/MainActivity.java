package com.example.finalprojectforjavaandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

public class MainActivity extends AppCompatActivity {

    ImageButton play_with_friend, play_vs_computer, go_to_settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        BackgroundMusicManager.getInstance().init(this);

        play_with_friend = findViewById(R.id.main_btn_paly_with_friends);
        play_vs_computer = findViewById(R.id.main_btn_game_vs_computer);
        go_to_settings = findViewById(R.id.go_to_settings);

        play_with_friend.setOnClickListener(v -> {
            Sound_effects.getInstance().Init(this);
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            boolean isClickSoundPlaying = preferences.getBoolean("isClickSoundPlaying", true);
            Sound_effects.getInstance().getSoundEffectsState(isClickSoundPlaying,this);
            Intent intent = new Intent(MainActivity.this, Choose_letter.class);
            startActivity(intent);
        });

        play_vs_computer.setOnClickListener(v -> {
            Sound_effects.getInstance().Init(this);
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            boolean isClickSoundPlaying = preferences.getBoolean("isClickSoundPlaying", true);
            Sound_effects.getInstance().getSoundEffectsState(isClickSoundPlaying,this);
            Intent intent = new Intent(MainActivity.this, choose_letter_vs_computer.class);
            startActivity(intent);
        });

        go_to_settings.setOnClickListener(v -> {
            Sound_effects.getInstance().Init(this);
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            boolean isClickSoundPlaying = preferences.getBoolean("isClickSoundPlaying", true);
            Sound_effects.getInstance().getSoundEffectsState(isClickSoundPlaying,this);
            Intent intent = new Intent(MainActivity.this, Settings.class);
            startActivity(intent);
        });
    }
}

