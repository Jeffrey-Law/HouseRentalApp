package com.example.login;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.login.Adapter.hotHouseAdapter;

public class HomeActivity extends AppCompatActivity {
    private ImageButton ib_favourite;
    private Button btn_searchwithmap, btn_search;
    private CardView outer_ad_1, outer_ad_2, outer_ad_3, outer_ad_4, outer_ad_5;
    private ImageView ad_1_iv, ad_2_iv, ad_3_iv, ad_4_iv, ad_5_iv;
    private EditText et_search_box;
    private LinearLayout homeBtn, manageBtn, postBtn, notificationBtn, settingBtn;
    private NestedScrollView scrollView;
    private RecyclerView recyclerView_hot;
    private int[] adbgColor = new int[5];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ib_favourite = findViewById(R.id.ib_favourite);
        btn_searchwithmap = findViewById(R.id.btn_searchwithmap);
        btn_search = findViewById(R.id.btn_search);
        outer_ad_1 = findViewById(R.id.outer_ad_1);
        outer_ad_2 = findViewById(R.id.outer_ad_2);
        outer_ad_3 = findViewById(R.id.outer_ad_3);
        outer_ad_4 = findViewById(R.id.outer_ad_4);
        outer_ad_5 = findViewById(R.id.outer_ad_5);
        ad_1_iv = findViewById(R.id.ad_1_iv);
        ad_2_iv = findViewById(R.id.ad_2_iv);
        ad_3_iv = findViewById(R.id.ad_3_iv);
        ad_4_iv = findViewById(R.id.ad_4_iv);
        ad_5_iv = findViewById(R.id.ad_5_iv);
        et_search_box = findViewById(R.id.et_search_box);
        homeBtn = findViewById(R.id.homeBtn);
        manageBtn = findViewById(R.id.manageBtn);
        postBtn = findViewById(R.id.postBtn);
        notificationBtn = findViewById(R.id.notificationBtn);
        settingBtn = findViewById(R.id.settingBtn);
        scrollView = findViewById(R.id.scrollView);
        recyclerView_hot = findViewById(R.id.recyclerview_hot);

        recyclerView_hot.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        AppDatabase instance = AppDatabaseSingleton.getInstance(getApplicationContext());
        recyclerView_hot.setAdapter(new hotHouseAdapter(getApplicationContext(), instance.getHouseDao().getAllHouse()));
//        Log.d("Size",  String.valueOf(instance.getHouseDao().getAllHouse()));

//                instance.getHouseDao().delete(instance.getHouseDao().findbyhouseid(5));

        // Get default adbgColor
        adbgColor[0] = outer_ad_1.getCardBackgroundColor().getDefaultColor();
        adbgColor[1] = outer_ad_2.getCardBackgroundColor().getDefaultColor();
        adbgColor[2] = outer_ad_3.getCardBackgroundColor().getDefaultColor();
        adbgColor[3] = outer_ad_4.getCardBackgroundColor().getDefaultColor();
        adbgColor[4] = outer_ad_5.getCardBackgroundColor().getDefaultColor();

        ib_favourite.setOnClickListener(ib_favourite_listener);
        btn_searchwithmap.setOnClickListener(btn_searchwithmap_listener);
        btn_search.setOnClickListener(btn_search_listener);
        ad_1_iv.setOnClickListener(ad_1_iv_listener);
        ad_2_iv.setOnClickListener(ad_2_iv_listener);
        ad_3_iv.setOnClickListener(ad_3_iv_listener);
        ad_4_iv.setOnClickListener(ad_4_iv_listener);
        ad_5_iv.setOnClickListener(ad_5_iv_listener);
        homeBtn.setOnClickListener(homeBtn_listener);
        manageBtn.setOnClickListener(manageBtn_listener);
        postBtn.setOnClickListener(postBtn_listener);
        notificationBtn.setOnClickListener(notificationBtn_listener);
        settingBtn.setOnClickListener(settingBtn_listener);

        // Will be laggy if not using glide to load images
        glideImage(R.drawable.ad1_1, ad_1_iv);
        glideImage(R.drawable.ad2_1, ad_2_iv);
        glideImage(R.drawable.ad3_1, ad_3_iv);
        glideImage(R.drawable.ad4_1, ad_4_iv);
        glideImage(R.drawable.ad5_1, ad_5_iv);
    }

    private View.OnClickListener ib_favourite_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(HomeActivity.this, favourite.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener btn_searchwithmap_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(HomeActivity.this, PostingActivity.class); // TODO
            startActivity(intent);
        }
    };

    private View.OnClickListener btn_search_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(HomeActivity.this, search_result.class);
            // TODO: Pass edittext content to search
            startActivity(intent);
        }
    };

    private View.OnClickListener homeBtn_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
//            startActivity(intent);
            scrollView.fullScroll(ScrollView.FOCUS_UP);
        }
    };

    private View.OnClickListener manageBtn_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(HomeActivity.this, PostingActivity.class); // TODO
            startActivity(intent);
        }
    };

    private View.OnClickListener postBtn_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(HomeActivity.this, PostingActivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener notificationBtn_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(HomeActivity.this, PostingActivity.class); // TODO
            startActivity(intent);
        }
    };

    private View.OnClickListener settingBtn_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(HomeActivity.this, booking.class); // TODO
            startActivity(intent);
        }
    };

    private View.OnClickListener ad_1_iv_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(HomeActivity.this, homeStoryActivity.class);
            intent.putExtra("target", "ad_1");
            intent.putExtra("adbgColor", adbgColor);
            startActivity(intent);
        }
    };

    private View.OnClickListener ad_2_iv_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(HomeActivity.this, homeStoryActivity.class);
            intent.putExtra("target", "ad_2");
            intent.putExtra("adbgColor", adbgColor);
            startActivity(intent);
        }
    };

    private View.OnClickListener ad_3_iv_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(HomeActivity.this, homeStoryActivity.class);
            intent.putExtra("target", "ad_3");
            intent.putExtra("adbgColor", adbgColor);
            startActivity(intent);
        }
    };

    private View.OnClickListener ad_4_iv_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(HomeActivity.this, homeStoryActivity.class);
            intent.putExtra("target", "ad_4");
            intent.putExtra("adbgColor", adbgColor);
            startActivity(intent);
        }
    };

    private View.OnClickListener ad_5_iv_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(HomeActivity.this, homeStoryActivity.class);
            intent.putExtra("target", "ad_5");
            intent.putExtra("adbgColor", adbgColor);
            startActivity(intent);
        }
    };

    @Override
    protected void onResume() {
        super.onResume();

        // Get adbgColor from homeStoryActivity
        Intent intent = getIntent();
        if (intent != null) {
            int[] updatedAdbgColor = intent.getIntArrayExtra("adbgColor");
            if (updatedAdbgColor != null) {
                adbgColor = updatedAdbgColor;
            }
        }
        // Set adbgColor
        outer_ad_1.setCardBackgroundColor(adbgColor[0]);
        outer_ad_2.setCardBackgroundColor(adbgColor[1]);
        outer_ad_3.setCardBackgroundColor(adbgColor[2]);
        outer_ad_4.setCardBackgroundColor(adbgColor[3]);
        outer_ad_5.setCardBackgroundColor(adbgColor[4]);

        recyclerView_hot.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        AppDatabase instance = AppDatabaseSingleton.getInstance(getApplicationContext());
        recyclerView_hot.setAdapter(new hotHouseAdapter(getApplicationContext(), instance.getHouseDao().getAllHouse()));
    }

    private void glideImage(int image, ImageView imageView) {
        Glide.with(this)
                .load(image)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        Toast.makeText(HomeActivity.this, "Failed to load image.", Toast.LENGTH_SHORT).show();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(imageView);
    }
}

