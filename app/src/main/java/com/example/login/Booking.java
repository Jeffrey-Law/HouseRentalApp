package com.example.login;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "booking_table")
public class Booking {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int booking_id;

    @ColumnInfo(name="booker_id")
//    @ForeignKey(entity = User.class,
//            parentColumns = "user_id",
//            childColumns = "booker_id",
//            onDelete = ForeignKey.CASCADE,
//            onUpdate = ForeignKey.CASCADE)
    public int booker_id;

    @ColumnInfo(name = "house_id")
    @NonNull
    public int house_id;

    @ColumnInfo(name = "house_owner_id")
    @NonNull
    public int house_owner_id;

    @ColumnInfo(name = "date")
    @NonNull
    public Date date;



    @ColumnInfo(name = "status")
    @NonNull
    public int status;

    public Booking(int booker_id, int house_id, int house_owner_id, @NonNull Date date) {
        this.booker_id = booker_id;
        this.house_id = house_id;
        this.house_owner_id = house_owner_id;
        this.date = date;
        this.status = 0;
    }

    public int getBooking_id() {
        return booking_id;
    }

    public int getBooker_id() {
        return booker_id;
    }

    public int getHouse_id() {
        return house_id;
    }

    public int getHouse_owner_id() {
        return house_owner_id;
    }

    @NonNull
    public Date getDate() {
        return date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
