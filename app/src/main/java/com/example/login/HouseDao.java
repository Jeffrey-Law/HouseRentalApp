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

    @Query("SELECT * FROM house_table WHERE house_id LIKE (:houseid) LIMIT 1")
    House findbyhouseid(int houseid);

    @Query("SELECT * FROM house_table WHERE (latitude LIKE (:lat) AND longitude LIKE (:lon))")
    List<House> findbycoordinate(float lat, float lon);

    @Query("SELECT * FROM house_table ORDER BY price DESC")
    List<House> sortHousePriceDesc();

    @Query("SELECT * FROM house_table ORDER BY price")
    List<House> sortHousePriceAsc();

    @Query("SELECT * FROM house_table ORDER BY rating DESC")
    List<House> sortHouseRatingDesc();

    @Query("SELECT * FROM house_table ORDER BY rating")
    List<House> sortHouseRatingAsc();

    @Query("SELECT house_image FROM house_table where house_id = :houseid")
    byte[] getImageByHouseId(int houseid);

    @Query("SELECT * FROM house_table WHERE house_id = :id LIMIT 1")
    House getHouseById(int id);

    @Query("SELECT owner_id FROM house_table WHERE house_id = :id LIMIT 1")
    int getOwnerById(int id);

    @Query("SELECT title FROM house_table WHERE house_id = :id LIMIT 1")
    String getTitleById(int id);

    @Query("SELECT * FROM house_table WHERE district = :district")
    List<House> findHouseByDistrict(String district);
}
