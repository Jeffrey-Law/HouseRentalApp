package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {
    private ImageButton ib_favourite;
    private Button btn_searchwithmap, btn_search;
    private CardView outer_ad_1, outer_ad_2;
    private ImageView ad_1_iv, ad_2_iv;
    private EditText et_search_box;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ib_favourite = findViewById(R.id.ib_favourite);
        btn_searchwithmap = findViewById(R.id.btn_searchwithmap);
        btn_search = findViewById(R.id.btn_search);
        outer_ad_1 = findViewById(R.id.outer_ad_1);
        outer_ad_2 = findViewById(R.id.outer_ad_2);
        ad_1_iv = findViewById(R.id.ad_1_iv);
        ad_2_iv = findViewById(R.id.ad_2_iv);
        et_search_box = findViewById(R.id.et_search_box);

        ib_favourite.setOnClickListener(ib_favourite_listener);
        btn_searchwithmap.setOnClickListener(btn_searchwithmap_listener);
        btn_search.setOnClickListener(btn_search_listener);
        ad_1_iv.setOnClickListener(ad_1_iv_listener);
        ad_2_iv.setOnClickListener(ad_2_iv_listener);
    }

    private View.OnClickListener ib_favourite_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(HomeActivity.this, PostingActivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener btn_searchwithmap_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(HomeActivity.this, PostingActivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener btn_search_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(HomeActivity.this, PostingActivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener ad_1_iv_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    private View.OnClickListener ad_2_iv_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
}