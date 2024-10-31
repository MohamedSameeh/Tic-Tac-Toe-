package com.example.finalprojectforjavaandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

public class choose_letter_vs_computer extends AppCompatActivity {

    ImageButton x,o;
    String your_letter,bot_letter;
    MediaPlayer btn_click;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_letter_vs_computer);
        x=findViewById(R.id.easy_mode_select_letter1);
        o=findViewById(R.id.easy_mode_select_letter2);
        btn_click=MediaPlayer.create(this,R.raw.button_click);

        x.setOnClickListener(v-> {
            Sound_effects.getInstance().Init(this);
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            boolean isClickSoundPlaying = preferences.getBoolean("isClickSoundPlaying", true);
            Sound_effects.getInstance().getSoundEffectsState(isClickSoundPlaying,this);

            Intent i=new Intent(choose_letter_vs_computer.this, play_vs_computer.class);
                your_letter="X";
                bot_letter="O";
                i.putExtra("your_letter",your_letter);
                i.putExtra("bots_letter",bot_letter);

                startActivity(i);

        });
        o.setOnClickListener(v->{
            Sound_effects.getInstance().Init(this);
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            boolean isClickSoundPlaying = preferences.getBoolean("isClickSoundPlaying", true);
            Sound_effects.getInstance().getSoundEffectsState(isClickSoundPlaying,this);
                Intent i=new Intent(choose_letter_vs_computer.this, play_vs_computer.class);
                your_letter="O";
                bot_letter="X";
                i.putExtra("your_letter",your_letter);
                i.putExtra("bots_letter",bot_letter);
                startActivity(i);


        });

    }
}