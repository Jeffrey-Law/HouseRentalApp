package com.example.login;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class SignUpActivity extends AppCompatActivity {
    AppDatabase appDatabase;
    UserDao userDao;

    TextView username, email, password, contact;

    ImageView userImage;

    byte[] imageData = null;
    Bitmap bitmap;

    Button uploadbtn, signupbtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        appDatabase = AppDatabaseSingleton.getInstance(this);
        userDao = appDatabase.getUserDao();

        username = findViewById(R.id.signupname);
        email = findViewById(R.id.email);
        contact = findViewById(R.id.contact);
        password = findViewById(R.id.signuppw);

        userImage = findViewById(R.id.userImage);

        uploadbtn = findViewById(R.id.uploadUserImage_btn);
        signupbtn = findViewById(R.id.register_btn);

        uploadbtn.setOnClickListener(uploadImage);
        signupbtn.setOnClickListener(signUp);
    }

    View.OnClickListener uploadImage = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            imagePickerLauncher.launch("image/*");
        }
    };

    private ActivityResultLauncher<String> imagePickerLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
        @Override
        public void onActivityResult(Uri result) {
            if (result != null) {
                InputStream input = null;
                try {
                    input = getContentResolver().openInputStream(result);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int bytesRead;
                while (true) {
                    try {
                        if (!((bytesRead = input.read(buffer)) != -1)) break;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    output.write(buffer, 0, bytesRead);
                }
                imageData = output.toByteArray();

                bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);

                userImage.setImageBitmap(bitmap);
                userImage.setVisibility(View.VISIBLE);
                uploadbtn.setVisibility(View.INVISIBLE);
            }
        }
    });

    View.OnClickListener signUp = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String user_name_txt = username.getText().toString();
            String email_txt = email.getText().toString();
            String contact_txt = contact.getText().toString();
            String password_txt = password.getText().toString();
            if(user_name_txt.length() != 0 && email_txt.length() != 0 && contact_txt.length() != 0 && password_txt.length() != 0){
                Log.d("return value: ", user_name_txt+email_txt+contact_txt+password_txt);
                User user = new User(user_name_txt, email_txt, contact_txt, password_txt, imageData);
                userDao.insert(user);
                Toast.makeText(SignUpActivity.this, "Sign Up successful", Toast.LENGTH_SHORT).show();
                finish();
            }else{
                Toast.makeText(SignUpActivity.this, "Please fill in all the necessary information", Toast.LENGTH_SHORT).show();
            }
        }
    };
}
