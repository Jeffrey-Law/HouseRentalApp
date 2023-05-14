package com.example.login;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import jp.shts.android.storiesprogressview.StoriesProgressView;
// Reference: https://blog.devgenius.io/creating-instagram-story-view-in-android-b5e4dbb27185
public class homeStoryActivity extends AppCompatActivity implements StoriesProgressView.StoriesListener {

    private final int[] images = {R.drawable.ad1_1, R.drawable.ad1_2, R.drawable.ad1_3, R.drawable.ad2_1, R.drawable.ad2_2,R.drawable.ad2_3, R.drawable.ad3_1, R.drawable.ad3_2, R.drawable.ad3_3, R.drawable.ad4_1, R.drawable.ad4_2, R.drawable.ad4_3, R.drawable.ad5_1, R.drawable.ad5_2, R.drawable.ad5_3};
    private final String[] titles = {"On Sale!", "On Sale!", "On Sale!", "Close To MTR!", "Close To MTR!", "Close To MTR!", "Rural Recommendation!", "Rural Recommendation!", "Rural Recommendation!", "White Houses", "White Houses","White Houses","Nature","Nature","Nature",};
    private final String[] postTimes = {"Posted on 02/05/2023", "Posted on 02/05/2023", "Posted on 02/05/2023","Posted on 05/05/2023", "Posted on 05/05/2023", "Posted on 05/05/2023","Posted on 09/05/2023", "Posted on 09/05/2023", "Posted on 09/05/2023","Posted on 14/05/2023", "Posted on 14/05/2023", "Posted on 14/05/2023", "Posted on 15/05/2023", "Posted on 15/05/2023", "Posted on 15/05/2023"};
    private final String[] captions = {"Discount up to 50% off!", "Discount up to 50% off!", "Discount up to 50% off!", "2 minutes walk to MTR station!", "2 minutes walk to MTR station!", "2 minutes walk to MTR station!", "Stay away from crowded city!", "Stay away from crowded city!", "Stay away from crowded city!", "Wanna stay in a plain white house?", "Wanna stay in a plain white house?", "Wanna stay in a plain white house?", "Imagine living with nature and wild animals!", "Imagine living with nature and wild animals!", "Imagine living with nature and wild animals!"};

    private int[] adbgColor = new int[5];
    long pressTime = 0L;
    long limit = 500L;

    private StoriesProgressView storiesProgressView;
    private ImageView image;

    private TextView title_tv;
    private TextView postTime_tv;
    private TextView caption_tv;

    private int counter = 0;
    private int countBase = 0;
    private Intent i;

    private int setCounter(String adNum) {
        if (adNum.equals("ad_1"))
        {adbgColor[0] = R.color.light_grey;
            return 0;}

        if (adNum.equals("ad_2"))
        {adbgColor[1] = R.color.light_grey;
            return 3;}

        if (adNum.equals("ad_3"))
        {adbgColor[2] = R.color.light_grey;
            return 6;}

        if (adNum.equals("ad_4"))
        {adbgColor[3] = R.color.light_grey;
            return 9;}

        if (adNum.equals("ad_5"))
        {adbgColor[4] = R.color.light_grey;
            return 12;}

        return 0;
    }

    private final View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    pressTime = System.currentTimeMillis();
                    storiesProgressView.pause();
                    return false;
                case MotionEvent.ACTION_UP:
                    long now = System.currentTimeMillis();
                    storiesProgressView.resume();
                    return limit < now - pressTime;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_story);

        // Set counter to appropriate value based on ad number
        Bundle bundle = getIntent().getExtras();
        if(bundle != null)
        {
            int[] updatedAdbgColor = bundle.getIntArray("adbgColor");
            if(updatedAdbgColor != null)
                adbgColor = updatedAdbgColor;

            countBase = setCounter(bundle.getString("target"));
            counter = countBase;
        }

        storiesProgressView = (StoriesProgressView) findViewById(R.id.stories);
        storiesProgressView.setStoriesCount(3); // Fixed at 3 images for all stories
        storiesProgressView.setStoryDuration(3000L);
        storiesProgressView.setStoriesListener(this);
        storiesProgressView.startStories(0);

        image = (ImageView) findViewById(R.id.image);
        title_tv = findViewById(R.id.title_tv);
        postTime_tv = findViewById(R.id.postTime_tv);
        caption_tv = findViewById(R.id.caption_tv);

        // Using glide to load image to imageview
        glideImage(images[counter],titles[counter],postTimes[counter],captions[counter]);

        View previous = findViewById(R.id.previous);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // inside on click we are
                // reversing our progress view.
                storiesProgressView.reverse();
            }
        });
        previous.setOnTouchListener(onTouchListener);

        View next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // inside on click we are
                // skipping the story progress view.
                storiesProgressView.skip();
            }
        });
        next.setOnTouchListener(onTouchListener);
    }
    @Override
    public void onNext() {
        glideImage(images[++counter],titles[counter],postTimes[counter],captions[counter]);
    }

    @Override
    public void onPrev() {
        if ((counter - 1) < countBase) return;
        glideImage(images[--counter],titles[counter],postTimes[counter],captions[counter]);
    }

    @Override
    public void onComplete() {
        Intent i = new Intent(homeStoryActivity.this, HomeActivity.class);
        i.putExtra("adbgColor", adbgColor);
        startActivity(i);
        finish();
    }

    @Override
    protected void onDestroy() {
        storiesProgressView.destroy();
        super.onDestroy();
    }

    private void glideImage(int image, String title, String postTime, String caption)
    {
        Glide.with(this)
                .load(image)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        Toast.makeText(homeStoryActivity.this, "Failed to load image.", Toast.LENGTH_SHORT).show();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(this.image);

        title_tv.setText(title);
        postTime_tv.setText(postTime);
        caption_tv.setText(caption);
    }
}