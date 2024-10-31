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

import java.util.Locale;

public class signUp extends AppCompatActivity {
    Button signup;
    EditText username, email, password;
    String user_name, Email, Password;
    MydataBase myDataBase;
    TextView sign_in_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_sign_up);

        myDataBase = new MydataBase(this);

        signup = findViewById(R.id.signup_btn_signup_activity);
        username = findViewById(R.id.et_username);
        email = findViewById(R.id.et_email_signup);
        password = findViewById(R.id.et_password_signup);
        sign_in_tv = findViewById(R.id.sign_in_tv);

        sign_in_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(signUp.this, logIn.class);
                startActivity(i);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_name = username.getText().toString().trim();
                Email = email.getText().toString().trim();
                Password = password.getText().toString().trim();

                if (!user_name.isEmpty() && !Password.isEmpty() && !Email.isEmpty()) {
                    sign_up newUser = new sign_up(user_name, Email, Password);
                    long result = myDataBase.insertData(newUser);

                    if (result == -1) {
                        // Show failure message
                        Toast.makeText(signUp.this, "Sign Up Failed", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(signUp.this, R.string.sign_up_successfully, Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(signUp.this, logIn.class);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(signUp.this, R.string.fill_all_the_fields_please, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loadLocale() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String language = preferences.getString("SELECTED_LANGUAGE", "en"); // Default to English if not set
        setLocale(language);
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration(getResources().getConfiguration());
        configuration.setLocale(locale);
        createConfigurationContext(configuration);
    }
}
