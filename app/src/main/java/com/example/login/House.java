package com.example.login;

import android.location.Address;
import android.location.Geocoder;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

    @ColumnInfo(name = "district")
    @NonNull
    public String district;

    @ColumnInfo(name = "latitude") // Get coordinate (latitude, longitude) from Google Map
    @NonNull
    public double latitude;

    @ColumnInfo(name = "longitude") // Get coordinate (latitude, longitude) from Google Map
    @NonNull
    public double longitude;

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

    @ColumnInfo(name = "post_day")
    @NonNull
    public int post_day;

    @ColumnInfo(name = "availability")
    @NonNull
    public boolean availability;

    @ColumnInfo(name = "rating")
    @NonNull
    public int rating;

    @ColumnInfo(name = "no_rating")
    @NonNull
    public int no_rating;

    @ColumnInfo(name = "visibility")
    @NonNull
    public boolean visibility;

    @ColumnInfo(name = "house_image")
    public byte[] house_image;

    public House(int owner_id, int price, @NonNull String address, @NonNull String district, double latitude, double longitude, int bedroom_num, int car_space_num, boolean furnished, boolean pet_considered, @NonNull String house_type, String description, boolean visibility, int post_day, byte[] house_image) {
        this.owner_id = owner_id;
        this.price = price;
        this.address = address;
        this.district = district;

        this.latitude = latitude;
        this.longitude = longitude;
        this.bedroom_num = bedroom_num;
        this.car_space_num = car_space_num;
        this.furnished = furnished;
        this.pet_considered = pet_considered;
        this.house_type = house_type;
        this.description = description;
        this.visibility = visibility;

        this.post_time = new Date();
        this.post_day = post_day;
        this.rating = 0;
        this.no_rating = 0;
        this.availability = true;
        this.house_image = house_image;
    }
}
