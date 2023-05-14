package com.example.login;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.view.LayoutInflater;

public class MainActivity extends AppCompatActivity {
    static AppDatabase appDatabase;
    UserDao userDao;
    HouseDao houseDao;
    Button buttonInsert, buttonUpdate, buttonDelete, buttonClear;
    TextView textView;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    ImageView googlebtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database_testing);

        //Database related
        appDatabase = Room.databaseBuilder(this, AppDatabase.class, "user_database")
                .allowMainThreadQueries().build();
        userDao = appDatabase.getUserDao();
        houseDao = appDatabase.getHouseDao();

// Close the database when done

        setContentView(R.layout.database_testing);

        buttonInsert = findViewById(R.id.buttonInsert);
        textView = findViewById(R.id.textView);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User("Joe", "joe@email.com", "12345678", "123", false);
                userDao.insert(user);
                updateView();
            }

        });

    }

    public static AppDatabase getAppDatabase() {
        return appDatabase;
    }

    public void updateView() {
        List<User> list = userDao.getAll();
        String text = "";
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            text += user.getName() + ":" + user.getEstablish_time() + "\n";
        }
        textView.setText(text);
    }
}
//        buttonClear = layout.findViewById(R.id.buttonClear);
//        buttonDelete = layout.findViewById(R.id.buttonDelete);
//        buttonUpdate = layout.findViewById(R.id.buttonUpdate);

        //Login related
//        TextView username =(TextView) findViewById(R.id.username);
//        TextView password =(TextView) findViewById(R.id.password);
//
//        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginbtn);
//        MaterialButton signupbtn = (MaterialButton) findViewById(R.id.signupbtn);
//
//        // login button
//        loginbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
//                    //correct
//                    Toast.makeText(MainActivity.this,"Login successful",Toast.LENGTH_SHORT).show();
//                }else
//                    //incorrect
//                    Toast.makeText(MainActivity.this,"Login failed",Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        // change to register page
//        signupbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent registerIntent = new Intent(MainActivity.this,register.class);
//                startActivity(registerIntent);
//            }
//        });
//
//
//        // google sign in
//        googlebtn = findViewById(R.id.googlebtn);
//        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
//        gsc = GoogleSignIn.getClient(this,gso);
//
//        googlebtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                signIn();
//            }
//        });
//
//
//    }
//
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
//

