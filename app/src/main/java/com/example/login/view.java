package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login.Adapter.viewAdapter;
import com.example.login.RecyclerViewInterface.ViewInterface;

public class view extends AppCompatActivity implements ViewInterface {
    private RecyclerView recyclerView_view;
    private int user_id;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view);

        recyclerView_view = findViewById(R.id.recyclerView_view);

        user_id = getIntent().getIntExtra("user_id",0);

        if(user_id == 0){
            Log.d("Error: ", "fall to access user id");
        }else{
            Log.d("View Activity : ", String.valueOf(user_id));
        }

        AppDatabase instance = AppDatabaseSingleton.getInstance(getApplicationContext());
        recyclerView_view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView_view.setAdapter(new viewAdapter(getApplicationContext(), instance.getHouseDao().getAllHouse(), this));
    }


    @Override
    public void onItemClickFavourite(int id) {
        Intent intent = new Intent(view.this, DetailActivity.class);
        intent.putExtra("house_id", id);
        intent.putExtra("user_id", user_id);
        startActivity(intent);
    }
}
