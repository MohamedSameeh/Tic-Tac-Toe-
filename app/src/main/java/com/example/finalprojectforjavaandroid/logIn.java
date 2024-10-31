package com.example.finalprojectforjavaandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.Locale;

public class logIn extends AppCompatActivity {
    Button btn_login;
    EditText et_email, et_password;
    MydataBase myDataBase;
    TextView sign_up_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_log_in);


        myDataBase = new MydataBase(this);

        btn_login = findViewById(R.id.sign_in_btn_signin_activity);
        et_email = findViewById(R.id.et_email_login);
        et_password = findViewById(R.id.et_password_login);
        sign_up_tv = (TextView) findViewById(R.id.sign_up_tv);
        sign_up_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(logIn.this,signUp.class);
                startActivity(intent);
            }
        });
        btn_login.setOnClickListener(v -> {
            String email = et_email.getText().toString();
            String password = et_password.getText().toString();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(logIn.this, R.string.please_fill_in_both_fields, Toast.LENGTH_SHORT).show();
            } else {

                if (authenticateUser(email, password)) {
                    Toast.makeText(logIn.this, R.string.login_successful, Toast.LENGTH_SHORT).show();
                    // Save login status to SharedPreferences
                    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(logIn.this);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putBoolean("Logged_in", true);
                    editor.apply();

                    // Navigate to MainActivity
                    Intent i = new Intent(logIn.this, MainActivity.class);
                    startActivity(i);
                    finish(); // Close the login activity
                } else {
                    Toast.makeText(logIn.this, R.string.invalid_email_or_password, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean authenticateUser(String email, String password) {
        ArrayList<sign_up> users = myDataBase.getAllData();

        for (sign_up user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
    private void loadLocale() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String language = preferences.getString("SELECTED_LANGUAGE", "en");
        setLocale(language);
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.setLocale(locale);
        getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());
    }
}
