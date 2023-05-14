package com.example.login;

import android.content.Intent;
import android.location.SettingInjectorService;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    Button loginbtn, signupbtn;
    TextView username, password;
    AppDatabase appDatabase;
    HouseDao houseDao;
    UserDao userDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        appDatabase = AppDatabaseSingleton.getInstance(this);
        houseDao = appDatabase.getHouseDao();
        userDao = appDatabase.getUserDao();

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        loginbtn = findViewById(R.id.register_btn);
        signupbtn = findViewById(R.id.signupbtn);

        //MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginbtn);
        //MaterialButton signupbtn = (MaterialButton) findViewById(R.id.signupbtn);

        // login button
        loginbtn.setOnClickListener(login);

        // change to register page
        signupbtn.setOnClickListener(signup);
    }


    View.OnClickListener login = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int uid = userDao.findIdByUserName(username.getText().toString());
//            Log.d("return value: ", String.valueOf(uid));
//            Log.d("return value: ", userDao.getUserById(uid).getPassward());
            if (uid != 0) {
                //correct
                if(userDao.getUserById(uid).getPassward().equals(password.getText().toString())){
                    Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                    Intent homeIntent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(homeIntent);
                }else{
                    Toast.makeText(LoginActivity.this, "The password is incorrect, please try again", Toast.LENGTH_SHORT).show();
                }
            } else
                //incorrect
                Toast.makeText(LoginActivity.this, "Invalid Username, please try again", Toast.LENGTH_SHORT).show();
        }
    };

    View.OnClickListener signup = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent registerIntent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(registerIntent);
        }
    };
}

//        // google sign in
//        View googlebtn = findViewById(R.id.googlebtn);
//        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
//        gsc = GoogleSignIn.getClient(this,gso);
//
//        googlebtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                signIn();
//            }
//        });

//    }

//    void signIn(){
//        Intent signInIntent = gsc.getSignInIntent();
//        startActivityForResult(signInIntent,1000);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == 1000){
//            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//
//            try {
//                task.getResult(ApiException.class);
//            } catch (ApiException e) {
//                Toast.makeText(getApplicationContext(),"something wrong",Toast.LENGTH_SHORT).show();
//            }
//        }
//
//    }

