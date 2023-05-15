package com.example.login;

import androidx.annotation.WorkerThread;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    @WorkerThread
    void insert(User user);

    @Delete
    void delete(User user);

    @Query("DELETE FROM user_table")
    void deleteAll();

    @Update
    void updateUser(User user);

    @Query("SELECT * FROM user_table WHERE user_id LIKE :uid LIMIT 1")
    User findById(int uid);

    @Query("SELECT * FROM user_table")
    List<User> getAll();

    @Query("SELECT user_id FROM user_table WHERE user_name LIKE :name LIMIT 1")
    int findIdByUserName(String name);

    @Query("SELECT * FROM user_table WHERE user_id = :id LIMIT 1")
    User getUserById(int id);

    @Query("SELECT user_name FROM user_table WHERE user_id = :id LIMIT 1")
    String getUserNameById(int id);

    @Query("SELECT contact FROM user_table WHERE user_id = :id LIMIT 1")
    String getContactById(int id);
}
