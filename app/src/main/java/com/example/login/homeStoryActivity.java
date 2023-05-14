package com.example.login;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
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

public class homeStoryActivity extends AppCompatActivity implements StoriesProgressView.StoriesListener {

    private final int[] images = {R.drawable.ad1_1, R.drawable.ad1_2, R.drawable.ad1_3, R.drawable.ad2_1, R.drawable.ad2_2,R.drawable.ad2_3, R.drawable.ad3_1, R.drawable.ad3_2, R.drawable.ad3_3};
    private final String[] titles = {"On Sale!", "On Sale!", "On Sale!", "Close To MTR!", "Close To MTR!", "Close To MTR!", "Rural Recommendation!", "Rural Recommendation!", "Rural Recommendation!"};
    private final String[] postTimes = {"Posted on 11/05/2023", "Posted on 11/05/2023", "Posted on 11/05/2023","Posted on 14/05/2023", "Posted on 14/05/2023", "Posted on 14/05/2023", "Posted on 15/05/2023", "Posted on 15/05/2023", "Posted on 15/05/2023"};
    private final String[] captions = {"Discount up to 50% off!", "Discount up to 50% off!", "Discount up to 50% off!", "2 minutes walk to MTR station!", "2 minutes walk to MTR station!", "2 minutes walk to MTR station!", "Stay away from crowded city!", "Stay away from crowded city!", "Stay away from crowded city!"};

    long pressTime = 0L;
    long limit = 500L;

    private StoriesProgressView storiesProgressView;
    private ImageView image;

    private TextView title_tv;
    private TextView postTime_tv;
    private TextView caption_tv;

    private int counter = 0;
    private int countBase = 0;

    private int setCounter(String adNum)
    {
        if(adNum == "ad_1")
            return 0;

        if(adNum == "ad_2")
            return 3;

        if(adNum == "ad_3")
            return 6;

        return 0;
    }

    private final View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:

                    // on action down when we press our screen
                    // the story will pause for specific time.
                    pressTime = System.currentTimeMillis();

                    // on below line we are pausing our indicator.
                    storiesProgressView.pause();
                    return false;
                case MotionEvent.ACTION_UP:

                    // in action up case when user do not touches
                    // screen this method will skip to next image.
                    long now = System.currentTimeMillis();

                    // on below line we are resuming our progress bar for status.
                    storiesProgressView.resume();

                    // on below line we are returning if the limit < now - presstime
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
        Bundle adNum = getIntent().getExtras();
        if(adNum != null)
            countBase = setCounter(adNum.getString("target"));

        counter = countBase;

        storiesProgressView = (StoriesProgressView) findViewById(R.id.stories);

        // on below line we are setting the total count for our stories.
        storiesProgressView.setStoriesCount(images.length);

        // on below line we are setting story duration for each story.
        storiesProgressView.setStoryDuration(3000L);

        // on below line we are calling a method for set
        // on story listener and passing context to it.
        storiesProgressView.setStoriesListener(this);

        // below line is use to start stories progress bar.
        storiesProgressView.startStories(counter);

        // initializing our image view.
        image = (ImageView) findViewById(R.id.image);

        title_tv = findViewById(R.id.title_tv);
        postTime_tv = findViewById(R.id.postTime_tv);
        caption_tv = findViewById(R.id.caption_tv);

        // on below line we are setting image to our image view.
        glideImage(images[counter],titles[counter],postTimes[counter],captions[counter]);

        // below is the view for going to the previous story.
        // initializing our previous view.
        View previous = findViewById(R.id.previous);

        // adding on click listener for our reverse view.
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // inside on click we are
                // reversing our progress view.
                storiesProgressView.reverse();
            }
        });

        // on below line we are calling a set on touch
        // listener method to move towards previous image.
        previous.setOnTouchListener(onTouchListener);

        // on below line we are initializing
        // view to skip a specific story.
        View next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // inside on click we are
                // skipping the story progress view.
                storiesProgressView.skip();
            }
        });
        // on below line we are calling a set on touch
        // listener method to move to next story.
        next.setOnTouchListener(onTouchListener);
    }
    @Override
    public void onNext() {
        // this method is called when we move
        // to next progress view of story.
        glideImage(images[++counter],titles[counter],postTimes[counter],captions[counter]);
    }

    @Override
    public void onPrev() {

        // this method id called when we move to previous story.
        // on below line we are decreasing our counter
        if ((counter - 1) < countBase) return;
        glideImage(images[--counter],titles[counter],postTimes[counter],captions[counter]);

        // on below line we are setting image to image view
    }

    @Override
    public void onComplete() {
        // when the stories are completed this method is called.
        // in this method we are moving back to initial main activity.
        Intent i = new Intent(homeStoryActivity.this, HomeActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    protected void onDestroy() {
        // in on destroy method we are destroying
        // our stories progress view.
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