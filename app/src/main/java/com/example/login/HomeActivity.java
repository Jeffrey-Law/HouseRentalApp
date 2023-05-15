package com.example.login;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
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
import com.example.login.Adapter.districtAdapter;
import com.example.login.Adapter.hotHouseAdapter;
import com.example.login.RecyclerViewInterface.HotHouseInterface;
import com.google.android.material.appbar.AppBarLayout;

public class HomeActivity extends AppCompatActivity implements HotHouseInterface {
    private ImageButton ib_favourite;
    private Button btn_searchwithmap, btn_search;
    private CardView outer_ad_1, outer_ad_2, outer_ad_3, outer_ad_4, outer_ad_5;
    private ImageView ad_1_iv, ad_2_iv, ad_3_iv, ad_4_iv, ad_5_iv, iv_home, iv_manage, iv_post, iv_notification, iv_setting;
    private EditText et_search_box;
    private LinearLayout homeBtn, manageBtn, postBtn, notificationBtn, settingBtn, toolbar;
    private NestedScrollView scrollView;
    private RecyclerView recyclerView_hot, recyclerView_district;
    private int[] adbgColor = new int[5];
    private CoordinatorLayout coordinator;
    private AppBarLayout app_bar;

    private String[] districts = {"Central and Western", "Eastern", "Southern", "Wan Chai", "Sham Shui Po",
            "Kowloon City", "Kwun Tong", "Wong Tai Sin", "Yau Tsim Mong", "Islands", "Kwai Tsing",
            "North", "Sai Kung", "Sha Tin", "Tai Po", "Tsuen Wan", "Tuen Mun", "Yuen Long"};
    private int[] imageId = {R.drawable.centralandwestern, R.drawable.eastern, R.drawable.southern, R.drawable.wanchai, R.drawable.shamshuipo, R.drawable.kowlooncity, R.drawable.kwuntong, R.drawable.wongtaisin,R.drawable.yautsimmong, R.drawable.islands,R.drawable.kwaitsing, R.drawable.north, R.drawable.saikung, R.drawable.shatin, R.drawable.taipo, R.drawable.tsuenwan,R.drawable.tuenmun,R.drawable.yuenlong};

    private int user_id;
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
        recyclerView_district = findViewById(R.id.recyclerView_district);
        toolbar = findViewById(R.id.toolbar);
        iv_home = findViewById(R.id.iv_home);
        iv_manage = findViewById(R.id.iv_manage);
        iv_post = findViewById(R.id.iv_post);
        iv_notification = findViewById(R.id.iv_notification);
        iv_setting = findViewById(R.id.iv_setting);
        coordinator = findViewById(R.id.coordinator);
        app_bar = findViewById(R.id.app_bar);

        scrollView.setVisibility(View.VISIBLE);

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
        toolbar.setOnTouchListener(toolbar_listener);

        // Will be laggy if not using glide to load images
        glideImage(R.drawable.ad1_1, ad_1_iv);
        glideImage(R.drawable.ad2_1, ad_2_iv);
        glideImage(R.drawable.ad3_1, ad_3_iv);
        glideImage(R.drawable.ad4_1, ad_4_iv);
        glideImage(R.drawable.ad5_1, ad_5_iv);

        glideImage(R.drawable.home, iv_home);
        glideImage(R.drawable.user, iv_manage);
        glideImage(R.drawable.post, iv_post);
        glideImage(R.drawable.bell, iv_notification);
        glideImage(R.drawable.settings, iv_setting);

        Log.d("TAG", "REACHED BOTTOM OF ONCREATE()");

        user_id = getIntent().getIntExtra("user_id",0);

        if(user_id == 0){
            Log.d("Error: ", "fall to access user id");
        }else{
            Log.d("Home Activity : ", String.valueOf(user_id));
        }
    }
private View.OnTouchListener toolbar_listener = new View.OnTouchListener() {
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // Handle touch events for the toolbar
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // Handle toolbar touch down event
                if (scrollView != null) {
                    int width = scrollView.getWidth();
                    int height = scrollView.getHeight();
                    boolean hasNestedScrollingEnabled = scrollView.isNestedScrollingEnabled();

                    Log.d("SCROLLVIEW", "NestedScrollView properties:");
                    Log.d("SCROLLVIEW", "Width: " + width);
                    Log.d("SCROLLVIEW", "Height: " + height);
                    Log.d("SCROLLVIEW", "Nested scrolling enabled: " + hasNestedScrollingEnabled);
                    Log.d("SCROLLVIEW", "Visibility: " + scrollView.getVisibility());
                    Log.d("SCROLLVIEW", "x: " + scrollView.getX());
                    Log.d("SCROLLVIEW", "y: " + scrollView.getY()); // Goes Below app bar
                } else {
                    Log.e("SCROLLVIEW", "NestedScrollView is null");
                }
                break;
            case MotionEvent.ACTION_MOVE:
                // Handle toolbar touch move event
                break;
            case MotionEvent.ACTION_UP:
                // Handle toolbar touch up event
                break;
        }
        return true; // Return 'true' to consume the touch event
    }
};

    private View.OnClickListener ib_favourite_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(HomeActivity.this, favourite.class);
            intent.putExtra("user_id", user_id);
            startActivity(intent);
        }
    };

    private View.OnClickListener btn_searchwithmap_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(HomeActivity.this, GoogleMapActivity.class);
            intent.putExtra("user_id", user_id);
            startActivity(intent);
        }
    };

    private View.OnClickListener btn_search_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(HomeActivity.this, search_result.class);
            // TODO: Pass edittext content to search
            String text = et_search_box.getText().toString();
            intent.putExtra("keywords", text);
            intent.putExtra("user_id", user_id);
            et_search_box.setText("");
            startActivity(intent);
        }
    };

    private View.OnClickListener homeBtn_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            scrollView.smoothScrollTo(0,0);
            coordinator.scrollTo(0, 0);
            app_bar.setExpanded(true);
            scrollView.scrollTo(0, 0);
        }
    };

    private View.OnClickListener manageBtn_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(HomeActivity.this, PostingActivity.class); // TODO
            intent.putExtra("user_id", user_id);
            startActivity(intent);
        }
    };

    private View.OnClickListener postBtn_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(HomeActivity.this, PostingActivity.class);
            intent.putExtra("user_id", user_id);
            startActivity(intent);
        }
    };

    private View.OnClickListener notificationBtn_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(HomeActivity.this, NotificationActivity.class); // TODO
            intent.putExtra("user_id", user_id);
            startActivity(intent);
        }
    };

    private View.OnClickListener settingBtn_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(HomeActivity.this, BookingActivity.class); // TODO
            intent.putExtra("user_id", user_id);
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

        AppDatabase instance = AppDatabaseSingleton.getInstance(getApplicationContext());
        recyclerView_hot.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView_hot.setAdapter(new hotHouseAdapter(getApplicationContext(), instance.getHouseDao().sortHouseRatingDesc(), this));
        recyclerView_district.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView_district.setAdapter(new districtAdapter(getApplicationContext(), districts, imageId, this));

        if (scrollView != null) {
            int width = scrollView.getWidth();
            int height = scrollView.getHeight();
            boolean hasNestedScrollingEnabled = scrollView.isNestedScrollingEnabled();

            Log.d("SCROLLVIEW", "NestedScrollView properties:");
            Log.d("SCROLLVIEW", "Width: " + width);
            Log.d("SCROLLVIEW", "Height: " + height);
            Log.d("SCROLLVIEW", "Nested scrolling enabled: " + hasNestedScrollingEnabled);
            Log.d("SCROLLVIEW", "Visibility: " + scrollView.getVisibility());
            Log.d("SCROLLVIEW", "x: " + scrollView.getX());
            Log.d("SCROLLVIEW", "y: " + scrollView.getY());
        } else {
            Log.e("SCROLLVIEW", "NestedScrollView is null");
        }


        Log.d("TAG", "REACHED BOTTOM OF ONRESUME()");
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

    @Override
    public void onItemClick(int id) {
//        AppDatabase instance = AppDatabaseSingleton.getInstance(getApplicationContext());
//        House selectedHouse = instance.getHouseDao().getHouseById(position);

        Intent intent = new Intent(HomeActivity.this, DetailActivity.class);
        Log.d("TAG", "Reached onItemClick " + id);
        intent.putExtra("house_id", id);
        intent.putExtra("user_id", user_id);
        startActivity(intent);
    }

    @Override
    public void onItemClickDistrict(String district) {
        Intent intent = new Intent(HomeActivity.this, search_result.class);
        intent.putExtra("district", district);
        intent.putExtra("user_id", user_id);
        startActivity(intent);
    }
}

