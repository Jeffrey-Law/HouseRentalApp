package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login.Adapter.resultAdapter;
import com.example.login.RecyclerViewInterface.SearchResultInterface;

public class search_result extends AppCompatActivity implements SearchResultInterface {
    private RecyclerView recyclerView_result;
    private String district = "";
    private String keywords = "";
    private Boolean searchDistrict = false;
    private int user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_of_search);

        Log.d("TAG", "Reached search_result ");

        Intent intent = getIntent();

        if (intent.hasExtra("district")) {
            district = intent.getStringExtra("district");
            Log.d("Intent String district", district);
            searchDistrict = true;
        } else {
            Log.d("Intent district", "EMPTY");
        }

        if (intent.hasExtra("keywords")) {
            keywords = intent.getStringExtra("keywords");
        } else {
            Log.d("Intent keywords", "EMPTY");
        }

        user_id = getIntent().getIntExtra("user_id",0);

        if(user_id == 0){
            Log.d("Error: ", "fall to access user id");
        }else{
            Log.d("Home Activity : ", String.valueOf(user_id));
        }

        recyclerView_result = findViewById(R.id.recyclerview_result);

        AppDatabase instance = AppDatabaseSingleton.getInstance(getApplicationContext());
        recyclerView_result.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        if(searchDistrict == true)
            recyclerView_result.setAdapter(new resultAdapter(getApplicationContext(), instance.getHouseDao().findHouseByDistrict(district), this));
        else
            recyclerView_result.setAdapter(new resultAdapter(getApplicationContext(), instance.getHouseDao().findbyhousetitle(keywords), this));
    }

    public void onItemClickSearchResult(int id) {
        Intent intent = new Intent(search_result.this, DetailActivity.class);
        intent.putExtra("house_id", id);
        intent.putExtra("user_id", user_id);
        startActivity(intent);
    }
}
