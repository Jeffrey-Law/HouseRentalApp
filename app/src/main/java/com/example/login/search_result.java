package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class search_result extends AppCompatActivity {
    private RecyclerView recyclerView_search;
    private String district = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_of_search);

        Log.d("TAG", "Reached search_result ");
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            district = bundle.getString("district");
//            Log.d("Bundle String", district);
        } else {
            Log.d("Bundle", "EMPTY");
        }
    }
}
