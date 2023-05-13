package com.example.login;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {House.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract HouseDao houseDao();

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