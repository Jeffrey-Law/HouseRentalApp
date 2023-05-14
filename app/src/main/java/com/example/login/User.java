package com.example.login;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int user_id;

    @ColumnInfo(name = "name")
    @NonNull
    public String name;

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

    public User(@NonNull String name, @NonNull String email, @NonNull String contact, @NonNull String passward, @NonNull boolean authenticated) {
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.passward = passward;
        this.establish_time = new Date();
        this.authenticated = authenticated;
    }

    @NonNull
    public String getName() {
        return name;
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
}

