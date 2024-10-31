package com.example.finalprojectforjavaandroid;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.lang.reflect.Array;
import java.util.HexFormat;
import java.util.Objects;

public class play_vs_friend extends AppCompatActivity {
    ImageButton reset_game_btn,reset_score;
    MediaPlayer mp,win;
    TextView your_score, friend_score, your_letter, bots_letter,your_name,friend_name;
    String letter, bot_letter,friend,you;
    Button btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9,new_game_btn,cancel_btn;
    boolean player1 = true;
    int player1Score = 0;
    int player2Score = 0;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_vs_friend);

        your_name=findViewById(R.id.your_name_tv);
        friend_name=findViewById(R.id.friend_name_tv);
        mp=MediaPlayer.create(this,R.raw.button_click);
        win=MediaPlayer.create(this,R.raw.win);


        reset_game_btn=findViewById(R.id.reset_btn_friend);
        reset_score=findViewById(R.id.reset_score_btn_friend);
        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);
        btn_5 = findViewById(R.id.btn_5);
        btn_6 = findViewById(R.id.btn_6);
        btn_7 = findViewById(R.id.btn_7);
        btn_8 = findViewById(R.id.btn_8);
        btn_9 = findViewById(R.id.btn_9);
        your_score = findViewById(R.id.your_score);
        friend_score = findViewById(R.id.bots_score);
        your_letter = findViewById(R.id.your_letter);
        bots_letter = findViewById(R.id.bots_letter);
        Intent intent = getIntent();
        letter = intent.getStringExtra("your_letter");
        bot_letter = intent.getStringExtra("bots_letter");
        you=intent.getStringExtra("your_name");
        friend=intent.getStringExtra("friend_name");
        your_letter.setText(letter);
        bots_letter.setText(bot_letter);
        your_name.setText(you);
        friend_name.setText(friend);
        dialog = new Dialog(play_vs_friend.this);
        dialog.setContentView(R.layout.custom_dialog_box);
        cancel_btn = dialog.findViewById(R.id.cancel_btn);
        new_game_btn = dialog.findViewById(R.id.new_game_btn);
        cancel_btn.setOnClickListener(v -> {
            dialog.dismiss();
            Intent i=new Intent(play_vs_friend.this,MainActivity.class);
            startActivity(i);
        });
        new_game_btn.setOnClickListener(v -> {
            resetBoard();
            dialog.dismiss();});
       Button[] buttons = new Button[]{btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9};

        View.OnClickListener buttonClickListener = v -> {
            SharedPreferences preferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(this);
            boolean isClickSoundPlaying = preferences.getBoolean("isClickSoundPlaying", true);
            Sound_effects.getInstance().getSoundEffectsState(isClickSoundPlaying,this);
            Button clickedButton = (Button) v;
            if (clickedButton.getText().toString().isEmpty()) {
                if (player1) {
                    clickedButton.setText(letter);
                    clickedButton.setTextColor(Color.parseColor("#E91E63"));
                } else {
                    clickedButton.setText(bot_letter);
                    clickedButton.setTextColor(Color.parseColor("#3949AB"));
                }
                player1 = !player1;
                checkGameStatus();
            }
        };
        for (Button button : buttons) {
            button.setOnClickListener(buttonClickListener);
        }

//        btn_1.setOnClickListener(v->{
//            SharedPreferences preferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(this);
//            boolean isClickSoundPlaying = preferences.getBoolean("isClickSoundPlaying", true);
//            Sound_effects.getInstance().getSoundEffectsState(isClickSoundPlaying,this);
//            Button clickedButton = (Button) v;
//            if (clickedButton.getText().toString().isEmpty()) {
//                if (player1) {
//                    clickedButton.setText(letter);
//                    clickedButton.setTextColor(Color.parseColor("#E91E63"));
//                } else {
//                    clickedButton.setText(bot_letter);
//                    clickedButton.setTextColor(Color.parseColor("#3949AB"));
//                }
//                player1 = !player1;
//                checkGameStatus();
//            }
//        });
//        btn_2.setOnClickListener(v->{
//            SharedPreferences preferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(this);
//            boolean isClickSoundPlaying = preferences.getBoolean("isClickSoundPlaying", true);
//            Sound_effects.getInstance().getSoundEffectsState(isClickSoundPlaying,this);
//            Button clickedButton = (Button) v;
//            if (clickedButton.getText().toString().isEmpty()) {
//                if (player1) {
//                    clickedButton.setText(letter);
//                    clickedButton.setTextColor(Color.parseColor("#E91E63"));
//                } else {
//                    clickedButton.setText(bot_letter);
//                    clickedButton.setTextColor(Color.parseColor("#3949AB"));
//                }
//                player1 = !player1;
//                checkGameStatus();
//            }
//        });        btn_3.setOnClickListener(v->{
//            SharedPreferences preferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(this);
//            boolean isClickSoundPlaying = preferences.getBoolean("isClickSoundPlaying", true);
//            Sound_effects.getInstance().getSoundEffectsState(isClickSoundPlaying,this);
//            Button clickedButton = (Button) v;
//            if (clickedButton.getText().toString().isEmpty()) {
//                if (player1) {
//                    clickedButton.setText(letter);
//                    clickedButton.setTextColor(Color.parseColor("#E91E63"));
//                } else {
//                    clickedButton.setText(bot_letter);
//                    clickedButton.setTextColor(Color.parseColor("#3949AB"));
//                }
//                player1 = !player1;
//                checkGameStatus();
//            }
//        });        btn_4.setOnClickListener(v->{
//            SharedPreferences preferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(this);
//            boolean isClickSoundPlaying = preferences.getBoolean("isClickSoundPlaying", true);
//            Sound_effects.getInstance().getSoundEffectsState(isClickSoundPlaying,this);
//            Button clickedButton = (Button) v;
//            if (clickedButton.getText().toString().isEmpty()) {
//                if (player1) {
//                    clickedButton.setText(letter);
//                    clickedButton.setTextColor(Color.parseColor("#E91E63"));
//                } else {
//                    clickedButton.setText(bot_letter);
//                    clickedButton.setTextColor(Color.parseColor("#3949AB"));
//                }
//                player1 = !player1;
//                checkGameStatus();
//            }
//        });        btn_5.setOnClickListener(v->{
//            SharedPreferences preferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(this);
//            boolean isClickSoundPlaying = preferences.getBoolean("isClickSoundPlaying", true);
//            Sound_effects.getInstance().getSoundEffectsState(isClickSoundPlaying,this);
//            Button clickedButton = (Button) v;
//            if (clickedButton.getText().toString().isEmpty()) {
//                if (player1) {
//                    clickedButton.setText(letter);
//                    clickedButton.setTextColor(Color.parseColor("#E91E63"));
//                } else {
//                    clickedButton.setText(bot_letter);
//                    clickedButton.setTextColor(Color.parseColor("#3949AB"));
//                }
//                player1 = !player1;
//                checkGameStatus();
//            }
//        });        btn_6.setOnClickListener(v->{
//            SharedPreferences preferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(this);
//            boolean isClickSoundPlaying = preferences.getBoolean("isClickSoundPlaying", true);
//            Sound_effects.getInstance().getSoundEffectsState(isClickSoundPlaying,this);
//            Button clickedButton = (Button) v;
//            if (clickedButton.getText().toString().isEmpty()) {
//                if (player1) {
//                    clickedButton.setText(letter);
//                    clickedButton.setTextColor(Color.parseColor("#E91E63"));
//                } else {
//                    clickedButton.setText(bot_letter);
//                    clickedButton.setTextColor(Color.parseColor("#3949AB"));
//                }
//                player1 = !player1;
//                checkGameStatus();
//            }
//        });        btn_7.setOnClickListener(v->{
//            SharedPreferences preferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(this);
//            boolean isClickSoundPlaying = preferences.getBoolean("isClickSoundPlaying", true);
//            Sound_effects.getInstance().getSoundEffectsState(isClickSoundPlaying,this);
//            Button clickedButton = (Button) v;
//            if (clickedButton.getText().toString().isEmpty()) {
//                if (player1) {
//                    clickedButton.setText(letter);
//                    clickedButton.setTextColor(Color.parseColor("#E91E63"));
//                } else {
//                    clickedButton.setText(bot_letter);
//                    clickedButton.setTextColor(Color.parseColor("#3949AB"));
//                }
//                player1 = !player1;
//                checkGameStatus();
//            }
//        });        btn_8.setOnClickListener(v->{
//            SharedPreferences preferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(this);
//            boolean isClickSoundPlaying = preferences.getBoolean("isClickSoundPlaying", true);
//            Sound_effects.getInstance().getSoundEffectsState(isClickSoundPlaying,this);
//            Button clickedButton = (Button) v;
//            if (clickedButton.getText().toString().isEmpty()) {
//                if (player1) {
//                    clickedButton.setText(letter);
//                    clickedButton.setTextColor(Color.parseColor("#E91E63"));
//                } else {
//                    clickedButton.setText(bot_letter);
//                    clickedButton.setTextColor(Color.parseColor("#3949AB"));
//                }
//                player1 = !player1;
//                checkGameStatus();
//            }
//        });        btn_9.setOnClickListener(v->{
//            SharedPreferences preferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(this);
//            boolean isClickSoundPlaying = preferences.getBoolean("isClickSoundPlaying", true);
//            Sound_effects.getInstance().getSoundEffectsState(isClickSoundPlaying,this);
//            Button clickedButton = (Button) v;
//            if (clickedButton.getText().toString().isEmpty()) {
//                if (player1) {
//                    clickedButton.setText(letter);
//                    clickedButton.setTextColor(Color.parseColor("#E91E63"));
//                } else {
//                    clickedButton.setText(bot_letter);
//                    clickedButton.setTextColor(Color.parseColor("#3949AB"));
//                }
//                player1 = !player1;
//                checkGameStatus();
//            }
//        });
        reset_game_btn.setOnClickListener(v->{resetBoard();});
        reset_score.setOnClickListener(v->{resetScore();});
    }

    private void checkGameStatus() {
        String btn_1_value = btn_1.getText().toString();
        String btn_2_value = btn_2.getText().toString();
        String btn_3_value = btn_3.getText().toString();
        String btn_4_value = btn_4.getText().toString();
        String btn_5_value = btn_5.getText().toString();
        String btn_6_value = btn_6.getText().toString();
        String btn_7_value = btn_7.getText().toString();
        String btn_8_value = btn_8.getText().toString();
        String btn_9_value = btn_9.getText().toString();

        if (isWinner(btn_1_value, btn_2_value, btn_3_value) ||
                isWinner(btn_4_value, btn_5_value, btn_6_value) ||
                isWinner(btn_7_value, btn_8_value, btn_9_value) ||
                isWinner(btn_1_value, btn_4_value, btn_7_value) ||
                isWinner(btn_2_value, btn_5_value, btn_8_value) ||
                isWinner(btn_3_value, btn_6_value, btn_9_value) ||
                isWinner(btn_1_value, btn_5_value, btn_9_value) ||
                isWinner(btn_3_value, btn_5_value, btn_7_value)) {

            if (!player1) {
                player1Score++;
                your_score.setText(String.valueOf(player1Score));
                dialog.show();
                SharedPreferences preferences =PreferenceManager.getDefaultSharedPreferences(this);
                boolean isWinSoundPlaying = preferences.getBoolean("isWinSoundPlaying", true);
                Sound_effects.getInstance().getWinState(isWinSoundPlaying,this);
            } else {
                player2Score++;
                friend_score.setText(String.valueOf(player2Score));
                dialog.show();
                SharedPreferences preferences =PreferenceManager.getDefaultSharedPreferences(this);
                boolean isLoseSoundPlaying = preferences.getBoolean("isLoseSoundPlaying", true);
                Sound_effects.getInstance().getLoseState(isLoseSoundPlaying,this);
            }
            resetBoard();
        } else if (isBoardFull()) {
            resetBoard();
        }
    }

    private boolean isWinner(String a, String b, String c) {
        return !a.isEmpty() && a.equals(b) && b.equals(c);
    }

    private boolean isBoardFull() {
        return !btn_1.getText().toString().isEmpty() &&
                !btn_2.getText().toString().isEmpty() &&
                !btn_3.getText().toString().isEmpty() &&
                !btn_4.getText().toString().isEmpty() &&
                !btn_5.getText().toString().isEmpty() &&
                !btn_6.getText().toString().isEmpty() &&
                !btn_7.getText().toString().isEmpty() &&
                !btn_8.getText().toString().isEmpty() &&
                !btn_9.getText().toString().isEmpty();
    }

    private void resetBoard() {
        btn_1.setText("");
        btn_2.setText("");
        btn_3.setText("");
        btn_4.setText("");
        btn_5.setText("");
        btn_6.setText("");
        btn_7.setText("");
        btn_8.setText("");
        btn_9.setText("");
        player1 = true;
    }
    private void resetScore(){
        player1Score=0;
        player2Score=0;
        your_score.setText("");
        friend_score.setText("");
    }
}
