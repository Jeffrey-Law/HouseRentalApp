package com.example.login;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Date;

public class BookingActivity extends AppCompatActivity {
    CalendarView simpleCalendarView;
    Button bookbtn;
    int user_id;
    int house_id;
    Date choiceDate;

    AppDatabase appDatabase;
    HouseDao houseDao;
    BookingDao bookingDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        appDatabase = AppDatabaseSingleton.getInstance(this);
        houseDao = appDatabase.getHouseDao();
        bookingDao = appDatabase.getBookingDao();

        setContentView(R.layout.booking);
        simpleCalendarView = (CalendarView) findViewById(R.id.CalendarView); // get the reference of CalendarView
        simpleCalendarView.setFocusedMonthDateColor(Color.RED); // set the red color for the dates of  focused month
        simpleCalendarView.setUnfocusedMonthDateColor(Color.BLUE); // set the yellow color for the dates of an unfocused month
        simpleCalendarView.setSelectedWeekBackgroundColor(Color.RED); // red color for the selected week's background
        simpleCalendarView.setWeekSeparatorLineColor(Color.GREEN); // green color for the week separator line

        Calendar calendar = Calendar.getInstance();
        long todayInMillis = calendar.getTimeInMillis();

// Set the minimum date to today
        simpleCalendarView.setMinDate(todayInMillis);


        // perform setOnDateChangeListener event on CalendarView
        simpleCalendarView.setOnDateChangeListener(chooseDate);

        bookbtn = findViewById(R.id.bookingpbtn);
        bookbtn.setOnClickListener(booking);

        house_id = getIntent().getIntExtra("house_id", 0);

        user_id = getIntent().getIntExtra("user_id",0);

        if(user_id == 0 || house_id == 0){
            Log.d("Error: ", "fall to access user id/ house id");
        }else{
            Log.d("Booking Activity : ", String.valueOf(user_id) + String.valueOf(house_id));
        }
    }
    CalendarView.OnDateChangeListener chooseDate = new CalendarView.OnDateChangeListener() {
        @Override
        public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
            // display the selected date by using a toast
            choiceDate = new Date(year + '-' + month + '-' +dayOfMonth);
        }
    };

    View.OnClickListener booking = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Booking booking = new Booking(user_id,house_id, houseDao.getOwnerById(house_id),choiceDate);
            bookingDao.insert(booking);
            Toast.makeText(getApplicationContext(), "Booked successfully" , Toast.LENGTH_SHORT).show();

            Log.d("Booking Record", String.valueOf(bookingDao.getNoOfRecords()));
            finish();
        }
    };
}
