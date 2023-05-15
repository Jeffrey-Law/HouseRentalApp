package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    TextView title, address, price, bed_room, car_space, description;
    ImageView houseImage, pet, furnished;
    Button booknow;
    AppDatabase appDatabase;
    HouseDao houseDao;
    House house;
    int user_id;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        title = findViewById(R.id.titleTxt);
        address = findViewById(R.id.addressTxt);
        price = findViewById(R.id.priceTxt);
        bed_room = findViewById(R.id.bedroom_num);
        car_space = findViewById(R.id.car_space_num);
        description = findViewById(R.id.descriptionTxt);

        houseImage = findViewById(R.id.house_image_iv);
        pet = findViewById(R.id.pet_result);
        furnished = findViewById(R.id.furnished_result);

        booknow = findViewById(R.id.booknow_btn);
        booknow.setOnClickListener(booking);

        appDatabase = AppDatabaseSingleton.getInstance(this);
        houseDao = appDatabase.getHouseDao();

        String house_id_txt = getIntent().getStringExtra("house_id");

        user_id = getIntent().getIntExtra("user_id",0);

        if(user_id == 0){
            Log.d("Error: ", "fall to access user id");
        }else{
            Log.d("Detail Activity : ", String.valueOf(user_id));
        }

        if(house_id_txt.isEmpty()){
            Log.d("Error: ", "fall to access house id");
        }else{
            house = houseDao.getHouseById(Integer.parseInt(house_id_txt));
            Log.d("house Id : ", String.valueOf(house.getHouse_id()));

            title.setText(house.getTitle());
            address.setText(house.getAddress());
            price.setText("$" + house.getPrice());
            bed_room.setText(house.getBedroom_num());
            car_space.setText(house.getCar_space_num());
            description.setText(house.getDescription());

            houseImage.setImageBitmap(house.getHouse_image());
            if(house.isFurnished()){
                furnished.setImageResource(R.drawable.accept);
            }else
                furnished.setImageResource(R.drawable.decline);
            if(house.isPet_considered()){
                pet.setImageResource(R.drawable.accept);
            }else
                pet.setImageResource(R.drawable.decline);
        }
    }

    View.OnClickListener booking = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(DetailActivity.this, BookingActivity.class);
            intent.putExtra("house_id", house.getHouse_id());
            intent.putExtra("user_id", user_id);
            startActivity(intent);
        }
    };
}
