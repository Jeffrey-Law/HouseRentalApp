package com.example.login;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Debug;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;

import javax.net.ssl.HostnameVerifier;

public class PostingActivity extends AppCompatActivity implements
AdapterView.OnItemSelectedListener{
    String[] districts = {"Central and Western", "Eastern", "Southern", "Wan Chai", "Sham Shui Po",
            "Kowloon City", "Kwun Tong", "Wong Tai Sin", "Yau Tsim Mong", "Islands", "Kwai Tsing",
            "North", "Sai Kung", "Sha Tin", "Tai Po", "Tsuen Wan", "Tuen Mun", "Yuen Long"};
    String[] options = {"Please Select","Yes", "No"};
    String[] houseTypes = {"House", "Apartment & unit", "Townhouse", "Villa", "Others"};
    String[] publishTime = {"7 Days", "30 Days", "90 Days", "180 Days", "365 Days"};
    Spinner district, furnished, petConsidered, visibility, houseType, publishingTime;

    TextView title, price, address, numOfBedroom, numOfCarSpace, description;
    Button upLoadPhotoBtn;
    Button postingBtn;

    ImageView houseImage;

    AppDatabase appDatabase;
    UserDao userDao;
    HouseDao houseDao;

    byte[] imageData = null;
    ByteArrayOutputStream stream;
    Bitmap bitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posting);

        appDatabase = AppDatabaseSingleton.getInstance(this);
        houseDao = appDatabase.getHouseDao();
        userDao = appDatabase.getUserDao();

        postingBtn = findViewById(R.id.posting_btn);
        upLoadPhotoBtn= findViewById(R.id.uploadPhoto_btn);

        houseImage = findViewById(R.id.houseImage_iv);

        title = findViewById(R.id.title_et);
        price = findViewById(R.id.price_et);
        address = findViewById(R.id.address_et);
        numOfBedroom = findViewById(R.id.bedroom_et);
        numOfCarSpace = findViewById(R.id.carspace_et);

        description = findViewById(R.id.desc_et);

        furnished = findViewById(R.id.furnished_spinner);
        petConsidered = findViewById(R.id.pet_spinner);
        visibility = findViewById(R.id.visibility_spinner);
        houseType = findViewById(R.id.housetype_spinner);
        publishingTime = findViewById(R.id.publishing_time_spinner);
        district = findViewById(R.id.district_spinner);

        ArrayAdapter<String> districtsAd  = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, districts);
        districtsAd.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);


        ArrayAdapter<String> optionsAd  = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, options);
        optionsAd.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> houseTypesAd  = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, houseTypes);
        houseTypesAd.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> publishTimeAd  = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, publishTime);
        publishTimeAd.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        district.setAdapter(districtsAd);
        district.setOnItemSelectedListener(this);

        furnished.setAdapter(optionsAd);
        furnished.setOnItemSelectedListener(this);

        petConsidered.setAdapter(optionsAd);
        petConsidered.setOnItemSelectedListener(this);

        visibility.setAdapter(optionsAd);
        visibility.setOnItemSelectedListener(this);

        houseType.setAdapter(houseTypesAd);
        houseType.setOnItemSelectedListener(this);

        publishingTime.setAdapter(publishTimeAd);
        publishingTime.setOnItemSelectedListener(this);

        upLoadPhotoBtn.setOnClickListener(uploadPhoto);
        postingBtn.setOnClickListener(posting);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private boolean convertOptionToBoolean(String option){
        if(option.equals("Yes"))
            return true;
        else
            return false;
    }

    private int convertPublishTime(int position){
        switch (position){
            case 0: return 7;

            case 1: return 30;

            case 2: return 90;

            case 3: return 180;

            case 4: return 365;
        }
        return 0;
    }

    private View.OnClickListener posting = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            String title_txt = title.getText().toString();
            String price_txt = price.getText().toString();
            String address_txt = address.getText().toString();
            String district_txt = district.getSelectedItem().toString();
            String numOfBedroom_txt = numOfBedroom.getText().toString();
            String numOfCarSpace_txt = numOfCarSpace.getText().toString();
            String furnished_sel = furnished.getSelectedItem().toString();
            String petConsidered_sel = petConsidered.getSelectedItem().toString();
            String houseType_sel = houseType.getSelectedItem().toString();
            String visibility_sel = visibility.getSelectedItem().toString();
            String description_txt = description.getText().toString();
          if(title_txt.length()== 0 || price_txt.length()==0 || address_txt.length()==0 || numOfBedroom_txt.length()==0 ||
            numOfCarSpace_txt.length()==0 || furnished_sel.length() > 4 || petConsidered_sel.length() > 4 || visibility_sel.length() > 4){
                            Toast.makeText(PostingActivity.this,"Please fill in all the necessary information", Toast.LENGTH_SHORT).show();
            }else{
              double latitude = 0,longitude = 0;
              Geocoder geocoder = new Geocoder(PostingActivity.this, Locale.getDefault());
              try {
                  List<Address> addresses = geocoder.getFromLocationName(address_txt, 1);
                  if (addresses != null && !addresses.isEmpty()) {
                      Address address = addresses.get(0);
                      latitude = address.getLatitude();
                      longitude = address.getLongitude();
                      // Do something with the latitude and longitude, such as display them in a TextView or use them to update a map
                  }
              } catch (IOException e) {
                  e.printStackTrace();
              }
                Toast.makeText(PostingActivity.this,"Posted", Toast.LENGTH_SHORT).show();

                if(imageData == null){
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.default_house_image);
                    stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    imageData = stream.toByteArray();
                }

                House house = new House(123, title_txt, Integer.parseInt(price_txt),address_txt,district_txt,latitude,longitude,
                        Integer.parseInt(numOfBedroom_txt), Integer.parseInt(numOfCarSpace_txt),
                        convertOptionToBoolean(furnished_sel),convertOptionToBoolean(petConsidered_sel),
                        houseType_sel, description_txt,  convertOptionToBoolean(visibility_sel), convertPublishTime(publishingTime.getSelectedItemPosition()),imageData);
                houseDao.insert(house);
            }

        }
    };

    private View.OnClickListener uploadPhoto = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            imagePickerLauncher.launch("image/*");
        }
    };

    private ActivityResultLauncher<String> imagePickerLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
        @Override
        public void onActivityResult(Uri result) {
            if (result != null) {
                InputStream input = null;
                try {
                    input = getContentResolver().openInputStream(result);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int bytesRead;
                while (true) {
                    try {
                        if (!((bytesRead = input.read(buffer)) != -1)) break;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    output.write(buffer, 0, bytesRead);
                }
                imageData = output.toByteArray();

                bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);

                houseImage.setImageBitmap(bitmap);
                houseImage.setVisibility(View.VISIBLE);
                upLoadPhotoBtn.setVisibility(View.INVISIBLE);
            }
        }
    });
}
