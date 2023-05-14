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

    @Query("DELETE FROM user")
    void deleteAll();

    @Update
    void updateUser(User user);

    @Query("SELECT * FROM user WHERE user_id LIKE :uid LIMIT 1")
    User findById(int uid);

    @Query("SELECT * FROM user")
    List<User> getAll();
}
