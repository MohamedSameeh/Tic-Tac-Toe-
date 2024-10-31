package com.example.finalprojectforjavaandroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;

import androidx.preference.PreferenceManager;

public class Sound_effects {
    private static Sound_effects Instance;
    public MediaPlayer win,lose,click;
    boolean isClickSoundPlaying,isLoseSoundPlaying,isWinSoundPlaying;

    public static Sound_effects getInstance() {
        if(Instance==null){
            Instance=new Sound_effects();
        }
        return Instance;
    }
    public void Init(Context context){
        if(win==null){
            win=MediaPlayer.create(context,R.raw.win);
        }
        if(lose==null){
            lose=MediaPlayer.create(context,R.raw.u_lose_sound_effect);
        }
        if(click==null){
            click=MediaPlayer.create(context,R.raw.button_click);
        }
        LoadPrefernces(context);
    }
    public void LoadPrefernces(Context context){
        SharedPreferences sp= PreferenceManager.getDefaultSharedPreferences(context);
        isClickSoundPlaying=sp.getBoolean("isClickSoundPlaying",true);
        isLoseSoundPlaying=sp.getBoolean("isLoseSoundPlaying",true);
        isWinSoundPlaying=sp.getBoolean("isWinSoundPlaying",true);
    }
    public void StartWinSound(){
        if(win!=null&&!win.isPlaying()){
            win.start();
            isWinSoundPlaying=true;
        }
    }
    public void StartLoseSound(){
        if(lose!=null&&!lose.isPlaying()){
            lose.start();
            isLoseSoundPlaying=true;
        }
    }
    public void StartClickSound(){
        if(click!=null&&!click.isPlaying()){
            click.start();
            isClickSoundPlaying=true;
        }
    }
    public void StopWinSound(){
        if(win!=null&&win.isPlaying()){
            win.stop();
            isWinSoundPlaying=false;
        }
    }
    public void StopLoseSound(){
        if(lose!=null&&lose.isPlaying()){
            lose.stop();
            isLoseSoundPlaying=false;
        }
    }
    public void StopClickSound(){
        if(click!=null&&click.isPlaying()){
            click.stop();
            isClickSoundPlaying=false;
        }
    }
    public void getSoundEffectsState(boolean state,Context context){
        SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor=sp.edit();
        editor.putBoolean("isClickSoundPlaying",state);
        editor.apply();
        if(state)
        {
        StartClickSound();
        }else{
            StopClickSound();
        }
}
    public void getWinState(boolean state,Context context){
        SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor=sp.edit();
        editor.putBoolean("isWinSoundPlaying",state);
        editor.apply();
        if(state)
        {
            StartWinSound();
        }else{
            StopWinSound();
        }
    }public void getLoseState(boolean state,Context context){
        SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor=sp.edit();
        editor.putBoolean("isLoseSoundPlaying",state);
        editor.apply();
        if(state)
        {
            StartLoseSound();
        }else{
            StopLoseSound();
        }
    }
}
