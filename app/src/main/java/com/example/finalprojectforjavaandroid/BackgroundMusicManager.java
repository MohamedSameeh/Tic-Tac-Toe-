package com.example.finalprojectforjavaandroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import androidx.preference.PreferenceManager;

public class BackgroundMusicManager {
    private static BackgroundMusicManager instance;
    private MediaPlayer backgroundMusicPlayer;
    private boolean isBackgroundMusicPlaying = false;

    public static BackgroundMusicManager getInstance() {
        if (instance == null) {
            instance = new BackgroundMusicManager();
        }
        return instance;
    }

    public void init(Context context) {
        if (backgroundMusicPlayer == null) {
            backgroundMusicPlayer = MediaPlayer.create(context, R.raw.game_background_music);
            backgroundMusicPlayer.setLooping(true);
        }
        loadPreferences(context);
    }

    private void loadPreferences(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        isBackgroundMusicPlaying = preferences.getBoolean("isBackgroundMusicPlaying", true);

        if (isBackgroundMusicPlaying) {
            startBackgroundMusic();
        }
    }

    public void startBackgroundMusic() {
        if (backgroundMusicPlayer != null && !backgroundMusicPlayer.isPlaying()) {
            backgroundMusicPlayer.start();
            isBackgroundMusicPlaying = true;
        }
    }

    public void stopBackgroundMusic() {
        if (backgroundMusicPlayer != null && backgroundMusicPlayer.isPlaying()) {
            backgroundMusicPlayer.pause();
            isBackgroundMusicPlaying = false;
        }
    }
    public void setBackgroundMusicState(boolean state, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isBackgroundMusicPlaying", state);
        editor.apply();

        if (state) {
            startBackgroundMusic();
        } else {
            stopBackgroundMusic();
        }
    }
}
