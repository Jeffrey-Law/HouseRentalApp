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
    AppDatabase appDatabase;
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

        appDatabase = AppDatabaseSingleton.getInstance(this);
        houseDao = appDatabase.getHouseDao();
        userDao = appDatabase.getUserDao();

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

