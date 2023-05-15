package com.example.login;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login.Adapter.notificationAdapter;
import com.example.login.Adapter.notificationResponseAdapter;

public class NotificationActivity extends AppCompatActivity {

    Button  response, request;
    RecyclerView recyclerView;
    TextView no_request_found;
    AppDatabase appDatabase;
    BookingDao bookingDao;
    int user_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        response = findViewById(R.id.notification_response_btn);
        request = findViewById(R.id.notification_request_btn);

        response.setOnClickListener(responseDisplay);
        request.setOnClickListener(requestDisplay);

        recyclerView = findViewById(R.id.recyclerview);

        no_request_found = findViewById(R.id.no_request_tv);

        appDatabase = AppDatabaseSingleton.getInstance(this);
        bookingDao = appDatabase.getBookingDao();

        user_id = getIntent().getIntExtra("user_id",0);

        if(user_id == 0){
            Log.d("Error: ", "fall to access user id");
        }else{
            Log.d("Notification Activity :", String.valueOf(user_id));
        }
        displayRequest();
    }

    private void displayRequest(){
        if(bookingDao.getAllRequestFromBooker(user_id).size() != 0){
            no_request_found.setVisibility(View.INVISIBLE);
        }else{
            no_request_found.setText("No Request is found");
            no_request_found.setVisibility(View.VISIBLE);
        }
        recyclerView.setAdapter(new notificationResponseAdapter(getApplicationContext(),bookingDao.getAllRequestFromBooker(user_id)));
    }
    View.OnClickListener responseDisplay = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(bookingDao.getAllResponseFromOwner(user_id).size() != 0){
                no_request_found.setVisibility(View.INVISIBLE);
            }else{
                no_request_found.setText("No Response is found");
                no_request_found.setVisibility(View.VISIBLE);
            }
            recyclerView.setAdapter(new notificationAdapter(getApplicationContext(),bookingDao.getAllResponseFromOwner(user_id)));
        }
    };

    View.OnClickListener requestDisplay = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            displayRequest();
        }
    };


}
