package com.example.login;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "user_table")
public class User {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int user_id;

    @ColumnInfo(name = "user_name")
    @NonNull
    public String user_name;

    @ColumnInfo(name = "email")
    @NonNull
    public String email;

    @ColumnInfo(name = "contact")
    @NonNull
    public String contact;

    @ColumnInfo(name = "passward")
    @NonNull
    public String passward;

    @ColumnInfo(name = "establish_time")
    @NonNull
    public Date establish_time;

    @ColumnInfo(name = "authenticated")
    @NonNull
    public boolean authenticated;
    @ColumnInfo(name = "user_image")
    public byte[] user_image;

    public User(@NonNull String user_name, @NonNull String email, @NonNull String contact, @NonNull String passward, byte[] user_image) {
        this.user_name = user_name;
        this.email = email;
        this.contact = contact;
        this.passward = passward;
        this.establish_time = new Date();
        this.authenticated = false;
        this.user_image = user_image;
    }

    @NonNull
    public String getUserName() {
        return user_name;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    @NonNull
    public String getContact() {
        return contact;
    }

    @NonNull
    public String getPassward() {
        return passward;
    }

    public String getEstablish_time() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(establish_time);
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public Bitmap getUser_image() {
        return BitmapFactory.decodeByteArray(user_image, 0, user_image.length);
    }
}

