package com.example.finalprojectforjavaandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.widget.EditText;

public class Choose_letter extends AppCompatActivity {
    ImageButton x, o;
    String you, friend;
    EditText your_name, friend_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_letter);
        x = findViewById(R.id.x);
        o = findViewById(R.id.o);
        your_name = findViewById(R.id.your_name_et);
        friend_name = findViewById(R.id.friend_name_et);

        x.setOnClickListener(v -> {
            handleLetterSelection("X", "O");
        });

        o.setOnClickListener(v -> {
            handleLetterSelection("O", "X");
        });
    }

    private void handleLetterSelection(String playerLetter, String botLetter) {
        Sound_effects.getInstance().Init(this);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isClickSoundPlaying = preferences.getBoolean("isClickSoundPlaying", true);
        Sound_effects.getInstance().getSoundEffectsState(isClickSoundPlaying,this);

        you = your_name.getText().toString();
        friend = friend_name.getText().toString();
        if (validateNames()) {
            Intent i = new Intent(Choose_letter.this, play_vs_friend.class);
            i.putExtra("your_name", you);
            i.putExtra("friend_name", friend);
            i.putExtra("your_letter", playerLetter);
            i.putExtra("bots_letter", botLetter);
            startActivity(i);
        }
    }

    private boolean validateNames() {
        if (friend.isEmpty() && you.isEmpty()) {
            Toast.makeText(getBaseContext(), R.string.twoError, Toast.LENGTH_SHORT).show();
            return false;
        } else if (friend.isEmpty()) {
            Toast.makeText(getBaseContext(), R.string.friend1Error, Toast.LENGTH_SHORT).show();
            return false;
        } else if (you.isEmpty()) {
            Toast.makeText(getBaseContext(), R.string.player2Error, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
