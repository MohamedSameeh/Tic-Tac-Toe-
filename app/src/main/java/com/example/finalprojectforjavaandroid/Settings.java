package com.example.finalprojectforjavaandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import java.util.Locale;

public class Settings extends AppCompatActivity {
    ImageButton eg_lang, spanish, english, germany, italy, france;
    ImageButton background_music_btn, click_sound_btn, log_out;
    boolean isClickSoundPlaying = true;
    boolean isBackgroundMusicPlaying = false;
    String selectedLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadLocale();
        setContentView(R.layout.activity_settings);
        background_music_btn = findViewById(R.id.btn_background_sound);
        click_sound_btn = findViewById(R.id.btn_click_sound);
        spanish = findViewById(R.id.spain_lang_btn);
        germany = findViewById(R.id.german_lang_btn);
        france = findViewById(R.id.france_lang_btn);
        italy = findViewById(R.id.italy_lang_btn);
        english = findViewById(R.id.usa_lang_btn);
        eg_lang = findViewById(R.id.eg_lang_btn);
        log_out = findViewById(R.id.logout_btn);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        isClickSoundPlaying = preferences.getBoolean("isClickSoundPlaying", true);
        isClickSoundPlaying = preferences.getBoolean("isWinSoundPlaying", true);
        isClickSoundPlaying = preferences.getBoolean("isLoseSoundPlaying", true);
        isBackgroundMusicPlaying = preferences.getBoolean("isBackgroundMusicPlaying", true);

        BackgroundMusicManager.getInstance().init(this);
        BackgroundMusicManager.getInstance().setBackgroundMusicState(isBackgroundMusicPlaying, this);

        updateSoundButtons();
        background_music_btn.setOnClickListener(v -> toggleBackgroundMusic());
        click_sound_btn.setOnClickListener(v -> toggleClickSound());
        log_out.setOnClickListener(v -> {
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("Logged_in", false);
            editor.apply();
            Intent intent = new Intent(Settings.this, logIn.class);
            startActivity(intent);
        });

        eg_lang.setOnClickListener(v -> setLanguage("ar"));
        english.setOnClickListener(v -> setLanguage("en"));
        france.setOnClickListener(v -> setLanguage("fr"));
        spanish.setOnClickListener(v -> setLanguage("es"));
        germany.setOnClickListener(v -> setLanguage("de"));
        italy.setOnClickListener(v -> setLanguage("it"));
    }

    private void toggleClickSound() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();

        if (isClickSoundPlaying) {
            click_sound_btn.setImageResource(R.drawable.off);
        } else {
            click_sound_btn.setImageResource(R.drawable.on);
        }

        isClickSoundPlaying = !isClickSoundPlaying;
        editor.putBoolean("isClickSoundPlaying", isClickSoundPlaying);
        editor.putBoolean("isWinSoundPlaying", isClickSoundPlaying);
        editor.putBoolean("isLoseSoundPlaying", isClickSoundPlaying);
        editor.apply();

        Sound_effects.getInstance().getSoundEffectsState(isClickSoundPlaying,this);
    }

    private void toggleBackgroundMusic() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();

        if (isBackgroundMusicPlaying) {
            background_music_btn.setImageResource(R.drawable.off);
        } else {
            background_music_btn.setImageResource(R.drawable.on);
        }

        isBackgroundMusicPlaying = !isBackgroundMusicPlaying;
        editor.putBoolean("isBackgroundMusicPlaying", isBackgroundMusicPlaying);
        editor.apply();

        BackgroundMusicManager.getInstance().setBackgroundMusicState(isBackgroundMusicPlaying, this);
    }

    private void updateSoundButtons() {
        if (isClickSoundPlaying) {
            click_sound_btn.setImageResource(R.drawable.on);
        } else {
            click_sound_btn.setImageResource(R.drawable.off);
        }

        if (isBackgroundMusicPlaying) {
            background_music_btn.setImageResource(R.drawable.on);
        } else {
            background_music_btn.setImageResource(R.drawable.off);
        }
    }

    private void setLanguage(String lang) {
        selectedLanguage = lang;
        setLocale(lang);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Settings.this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("SELECTED_LANGUAGE", selectedLanguage);
        editor.apply();
        recreate();
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Configuration configuration = new Configuration();
        configuration.setLocale(locale);
        getResources().updateConfiguration(configuration,new DisplayMetrics());
    }

    private void loadLocale() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String language = preferences.getString("SELECTED_LANGUAGE", "en"); // Default to English if not set
        setLocale(language);
    }
}
