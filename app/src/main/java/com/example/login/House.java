package com.example.login;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@Entity(tableName = "house_table")

// Changed post_id -> house_id, ower_id -> owner_id, no_bedroom -> bedroom_num, no_car_space -> car_space_num,available -> availability, no_rating deleted, char[] changed to String
public class House {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int house_id;

    @ColumnInfo(name = "owner_id")
//    @ForeignKey(User)
    @NonNull
    public int owner_id;

    @ColumnInfo(name = "price")
    @NonNull
    public int price;

    @ColumnInfo(name = "address")
    @NonNull
    public String address;

    @ColumnInfo(name = "latitude") // Get coordinate (latitude, longitude) from Google Map
    @NonNull
    public float latitude;

    @ColumnInfo(name = "longitude") // Get coordinate (latitude, longitude) from Google Map
    @NonNull
    public float longitude;

    @ColumnInfo(name = "bedroom_num")
    @NonNull
    public int bedroom_num;

    @ColumnInfo(name = "car_space_num")
    @NonNull
    public int car_space_num;

    @ColumnInfo(name = "furnished")
    @NonNull
    public boolean furnished;

    @ColumnInfo(name = "pet_considered")
    @NonNull
    public boolean pet_considered;

    @ColumnInfo(name = "house_type")
    @NonNull
    public String house_type;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "post_time")
    @NonNull
    public Date post_time;

    @ColumnInfo(name = "start_date")
    @NonNull
    public Date start_date;

    @ColumnInfo(name = "end_date")
    @NonNull
    public Date end_date;

    @ColumnInfo(name = "availability")
    @NonNull
    public boolean availability;

    @ColumnInfo(name = "rating")
    @NonNull
    public int rating;

    @ColumnInfo(name = "visibility")
    @NonNull
    public boolean visibility;

    @ColumnInfo(name = "houseimagepath")
    public String houseimagepath;

    public House() {
    }
}
