package com.example.login;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BookingDao {
    @Insert
    void insert(Booking booking);

    @Insert
    void insertAll(Booking booking);

    @Delete
    void delete(Booking booking);

    @Update
    void updateBooking(Booking booking);

    @Query("SELECT * FROM booking_table WHERE booking_id = :id LIMIT 1")
    Booking getBookingById(int id);


    @Query("SELECT COUNT(*) FROM booking_table")
    int getNoOfRecords();
}
