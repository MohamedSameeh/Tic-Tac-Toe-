package com.example.finalprojectforjavaandroid;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class play_vs_computer extends AppCompatActivity {
    ImageButton reset_game_btn, reset_score;
    TextView your_score, bot_score, your_letter, bots_letter;
    String letter, bot_letter;
    Button btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9;
    Button[] buttons;
    boolean player1 = true;
    int playerScore = 0;
    int botScore = 0;
    Random random = new Random();
    Dialog dialog;
    Button cancel_btn, new_game_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_vs_computer);

        dialog = new Dialog(play_vs_computer.this);
        dialog.setContentView(R.layout.custom_dialog_box);
        cancel_btn = dialog.findViewById(R.id.cancel_btn);
        new_game_btn = dialog.findViewById(R.id.new_game_btn);

        cancel_btn.setOnClickListener(v -> {
            dialog.dismiss();
            Intent i=new Intent(play_vs_computer.this,MainActivity.class);
            startActivity(i);

        });
        new_game_btn.setOnClickListener(v -> {
            resetBoard();
            dialog.dismiss();
        });

        reset_game_btn = findViewById(R.id.reset_btn_1);
        reset_score = findViewById(R.id.reset_score_btn_easy_level);
        btn_1 = findViewById(R.id.btn_computer_hard_1);
        btn_2 = findViewById(R.id.btn_computer_hard_2);
        btn_3 = findViewById(R.id.btn_computer_hard_3);
        btn_4 = findViewById(R.id.btn_computer_hard_4);
        btn_5 = findViewById(R.id.btn_computer_hard_5);
        btn_6 = findViewById(R.id.btn_computer_hard_6);
        btn_7 = findViewById(R.id.btn_computer_hard_7);
        btn_8 = findViewById(R.id.btn_computer_hard_8);
        btn_9 = findViewById(R.id.btn_computer_hard_9);
        your_score = findViewById(R.id.your_score);
        bot_score = findViewById(R.id.bots_score);
        your_letter = findViewById(R.id.your_letter);
        bots_letter = findViewById(R.id.bots_letter);
        Intent intent = getIntent();
        letter = intent.getStringExtra("your_letter");
        bot_letter = intent.getStringExtra("bots_letter");
        your_letter.setText(letter);
        bots_letter.setText(bot_letter);

        reset_score.setOnClickListener(v -> resetScore());

        buttons = new Button[]{btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9};

        View.OnClickListener buttonClickListener = v -> {
            SharedPreferences preferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(this);
            boolean isClickSoundPlaying = preferences.getBoolean("isClickSoundPlaying", true);
            Sound_effects.getInstance().getSoundEffectsState(isClickSoundPlaying,this);
            Button clickedButton = (Button) v;
            if (clickedButton.getText().toString().isEmpty() && player1) {
                clickedButton.setText(letter);
                clickedButton.setTextColor(Color.parseColor("#E91E63"));
                player1 = false;
                checkGameStatus();
                if (!player1) {
                    botMove();
                    checkGameStatus();
                }
            }
        };
        for (Button button : buttons) {
            button.setOnClickListener(buttonClickListener);
        }
//        btn_1.setOnClickListener(v->{
//            SharedPreferences preferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(this);
//            boolean isClickSoundPlaying = preferences.getBoolean("isClickSoundPlaying", true);
//            Sound_effects.getInstance().getSoundEffectsState(isClickSoundPlaying,this);
//                if (btn_1.getText().toString().isEmpty() && player1) {
//                    btn_1.setText(letter);
//                    btn_1.setTextColor(Color.parseColor("#E91E63"));
//                player1 = false;
//                checkGameStatus();
//                if (!player1) {
//                    botMove();
//                    checkGameStatus();
//                }
//            }
//
//        });
//        btn_2.setOnClickListener(v->{
//                SharedPreferences preferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(this);
//                boolean isClickSoundPlaying = preferences.getBoolean("isClickSoundPlaying", true);
//                Sound_effects.getInstance().getSoundEffectsState(isClickSoundPlaying,this);
//                if (btn_2.getText().toString().isEmpty() && player1) {
//                    btn_2.setText(letter);
//                    btn_2.setTextColor(Color.parseColor("#E91E63"));
//                    player1 = false;
//                    checkGameStatus();
//                    if (!player1) {
//                        botMove();
//                        checkGameStatus();
//                    }
//                }
//
//        });
//        btn_3.setOnClickListener(v->{
//                SharedPreferences preferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(this);
//                boolean isClickSoundPlaying = preferences.getBoolean("isClickSoundPlaying", true);
//                Sound_effects.getInstance().getSoundEffectsState(isClickSoundPlaying,this);
//                if (btn_3.getText().toString().isEmpty() && player1) {
//                    btn_3.setText(letter);
//                    btn_3.setTextColor(Color.parseColor("#E91E63"));
//                    player1 = false;
//                    checkGameStatus();
//                    if (!player1) {
//                        botMove();
//                        checkGameStatus();
//                    }
//                }
//
//        });
//        btn_4.setOnClickListener(v->{
//                SharedPreferences preferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(this);
//                boolean isClickSoundPlaying = preferences.getBoolean("isClickSoundPlaying", true);
//                Sound_effects.getInstance().getSoundEffectsState(isClickSoundPlaying,this);
//                if (btn_4.getText().toString().isEmpty() && player1) {
//                    btn_4.setText(letter);
//                    btn_4.setTextColor(Color.parseColor("#E91E63"));
//                    player1 = false;
//                    checkGameStatus();
//                    if (!player1) {
//                        botMove();
//                        checkGameStatus();
//                    }
//                }
//
//        });
//        btn_5.setOnClickListener(v->{
//                SharedPreferences preferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(this);
//                boolean isClickSoundPlaying = preferences.getBoolean("isClickSoundPlaying", true);
//                Sound_effects.getInstance().getSoundEffectsState(isClickSoundPlaying,this);
//                if (btn_5.getText().toString().isEmpty() && player1) {
//                    btn_5.setText(letter);
//                    btn_5.setTextColor(Color.parseColor("#E91E63"));
//                    player1 = false;
//                    checkGameStatus();
//                    if (!player1) {
//                        botMove();
//                        checkGameStatus();
//                    }
//                }
//
//        });
//        btn_6.setOnClickListener(v->{
//                SharedPreferences preferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(this);
//                boolean isClickSoundPlaying = preferences.getBoolean("isClickSoundPlaying", true);
//                Sound_effects.getInstance().getSoundEffectsState(isClickSoundPlaying,this);
//                if (btn_6.getText().toString().isEmpty() && player1) {
//                    btn_6.setText(letter);
//                    btn_6.setTextColor(Color.parseColor("#E91E63"));
//                    player1 = false;
//                    checkGameStatus();
//                    if (!player1) {
//                        botMove();
//                        checkGameStatus();
//                    }
//                }
//
//        });
//        btn_7.setOnClickListener(v->{
//                SharedPreferences preferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(this);
//                boolean isClickSoundPlaying = preferences.getBoolean("isClickSoundPlaying", true);
//                Sound_effects.getInstance().getSoundEffectsState(isClickSoundPlaying,this);
//                if (btn_7.getText().toString().isEmpty() && player1) {
//                    btn_7.setText(letter);
//                    btn_7.setTextColor(Color.parseColor("#E91E63"));
//                    player1 = false;
//                    checkGameStatus();
//                    if (!player1) {
//                        botMove();
//                        checkGameStatus();
//                    }
//                }
//
//        });
//        btn_8.setOnClickListener(v->{
//                SharedPreferences preferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(this);
//                boolean isClickSoundPlaying = preferences.getBoolean("isClickSoundPlaying", true);
//                Sound_effects.getInstance().getSoundEffectsState(isClickSoundPlaying,this);
//                if (btn_8.getText().toString().isEmpty() && player1) {
//                    btn_8.setText(letter);
//                    btn_8.setTextColor(Color.parseColor("#E91E63"));
//                    player1 = false;
//                    checkGameStatus();
//                    if (!player1) {
//                        botMove();
//                        checkGameStatus();
//                    }
//                }
//
//        });
//        btn_9.setOnClickListener(v->{
//                SharedPreferences preferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(this);
//                boolean isClickSoundPlaying = preferences.getBoolean("isClickSoundPlaying", true);
//                Sound_effects.getInstance().getSoundEffectsState(isClickSoundPlaying,this);
//                if (btn_9.getText().toString().isEmpty() && player1) {
//                    btn_9.setText(letter);
//                    btn_9.setTextColor(Color.parseColor("#E91E63"));
//                    player1 = false;
//                    checkGameStatus();
//                    if (!player1) {
//                        botMove();
//                        checkGameStatus();
//                    }
//                }
//        });
        reset_game_btn.setOnClickListener(v -> resetBoard());
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
                playerScore++;
                your_score.setText(String.valueOf(playerScore));
                dialog.show();
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                boolean isWonSoundPlaying = preferences.getBoolean("isWinSoundPlaying", true);
                Sound_effects.getInstance().getWinState(isWonSoundPlaying,this);
            } else {
                botScore++;
                bot_score.setText(String.valueOf(botScore));
                dialog.show();
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
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
        for (Button button : buttons) {
            if (button.getText().toString().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private void botMove() {
        int index;
        do {
            index = random.nextInt(buttons.length);
        } while (!buttons[index].getText().toString().isEmpty());

        buttons[index].setText(bot_letter);
        buttons[index].setTextColor(Color.parseColor("#3949AB"));
        player1 = true;
    }

    private void resetBoard() {
        for (Button button : buttons) {
            button.setText("");
        }
        player1 = true;
    }
    private void resetScore() {
        playerScore = 0;
        botScore = 0;
        your_score.setText("");
        bot_score.setText("");
    }
}
