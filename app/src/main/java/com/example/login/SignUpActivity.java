package com.example.login;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {
    AppDatabase appDatabase;
    UserDao userDao;

    TextView username, email, password, re_password;

    ImageView userImage;

    Button uploadbtn, signupbtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        appDatabase = AppDatabaseSingleton.getInstance(this);
        userDao = appDatabase.getUserDao();

        username = findViewById(R.id.signupname)

    }
}
