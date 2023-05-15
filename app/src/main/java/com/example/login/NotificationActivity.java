package com.example.login;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login.Adapter.notificationAdapter;

public class NotificationActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    AppDatabase appDatabase;
    BookingDao bookingDao;
    int user_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        recyclerView = findViewById(R.id.recyclerview);

        appDatabase = AppDatabaseSingleton.getInstance(this);
        bookingDao = appDatabase.getBookingDao();

        user_id = getIntent().getIntExtra("user_id",0);

        if(user_id == 0){
            Log.d("Error: ", "fall to access user id");
        }else{
            Log.d("Google Map Activity : ", String.valueOf(user_id));
        }

        display();
    }

    private void display(){
        recyclerView.setAdapter(new notificationAdapter(getApplicationContext(),bookingDao.getAllRequestFromBooker(user_id)));
    }
}
