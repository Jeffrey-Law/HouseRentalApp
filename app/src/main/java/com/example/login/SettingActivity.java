package com.example.login;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SettingActivity extends AppCompatActivity {

    // Declare final variable for user ID
    int userId;

    // Declare other views and variables
    private ImageView userImage;
    private TextView username, email, contact;
    private Button signout;
    private AppDatabase appDatabase;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        // Get the user ID from the intent
        userId = getIntent().getIntExtra("user_id", 0);

        // Initialize views and variables
        userImage = findViewById(R.id.profile_user_image);
        username = findViewById(R.id.profile_user_name);
        email = findViewById(R.id.profile_email);
        contact = findViewById(R.id.profile_contact);
        signout = findViewById(R.id.setting_signout_btn);
        appDatabase = AppDatabaseSingleton.getInstance(this);
        userDao = appDatabase.getUserDao();

        if (userId == 0) {
            Log.d("Error: ", "failed to access user ID");
        } else {
            Log.d("Setting Activity : ", String.valueOf(userId));
            Bitmap returnValue = userDao.getUserById(userId).getUser_image();
            if(returnValue!= null){
                userImage.setImageBitmap(returnValue);
            }
            username.setText(String.valueOf(userDao.getUserNameById(userId)));
            email.setText(userDao.getEmailById(userId));
            contact.setText(String.valueOf(userDao.getContactById(userId)));
        }

        // Set the signout button click listener
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the LoginActivity and clear the back stack
                Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        // Populate the user information

    }
}