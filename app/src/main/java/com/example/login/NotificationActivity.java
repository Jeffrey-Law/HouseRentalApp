package com.example.login;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login.Adapter.notificationAdapter;

public class NotificationActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView no_request_found;
    AppDatabase appDatabase;
    BookingDao bookingDao;
    int user_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        recyclerView = findViewById(R.id.recyclerview);

        no_request_found = findViewById(R.id.no_request_tv);

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
        if(bookingDao.getAllRequestFromBooker(user_id).size() != 0){
            no_request_found.setVisibility(View.INVISIBLE);
        }else{
            no_request_found.setVisibility(View.VISIBLE);
        }
        recyclerView.setAdapter(new notificationAdapter(getApplicationContext(),bookingDao.getAllRequestFromBooker(user_id)));

    }
}
