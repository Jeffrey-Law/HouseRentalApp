package com.example.login;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface HouseDao {
    @Insert
    void insert(House house);

    @Insert
    void insertAll(House house);

    @Delete
    void delete(House house);

    @Update
    void updateHouse(House house);

    @Query("SELECT * FROM house_table")
    List<House> getAllHouse();

    @Query("SELECT * FROM house_table WHERE house_id LIKE (:houseid)")
    List<House> findbyhouseid(int houseid);

    @Query("SELECT * FROM house_table WHERE (latitude LIKE (:lat) AND longitude LIKE (:lon))")
    List<House> findbycoordinate(float lat, float lon);

}
