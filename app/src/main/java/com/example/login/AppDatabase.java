package com.example.login;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {User.class, House.class}, version = 1,exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao getUserDao();
    public abstract HouseDao getHouseDao();


//    public static final String DATABASE_NAME = "HouseDb";
//
//    public static AppDatabase instance;
//
//    public static AppDatabase getInstance(Context context)
//    {
//        if(instance == null)
//        {
//            instance = Room.databaseBuilder(context,AppDatabase.class,DATABASE_NAME)
//                    .allowMainThreadQueries().build();
//        }
//        return instance;
//    }
}

