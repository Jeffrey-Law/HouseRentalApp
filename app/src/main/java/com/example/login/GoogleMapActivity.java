package com.example.login;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class GoogleMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;
    AppDatabase appDatabase;
    HouseDao houseDao;

    List<House> houses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_googlemap);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_view);
        mapFragment.getMapAsync(this);

        appDatabase = AppDatabaseSingleton.getInstance(this);
        houseDao = appDatabase.getHouseDao();

        houses = houseDao.getAllHouse();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     *
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        for (House house : houses){
            LatLng house_position = new LatLng(house.getLatitude(), house.getLongitude());
            mMap.addMarker(new MarkerOptions()
                    .position(house_position)
                    .title(String.valueOf(house.getHouse_id())));

        }
        // Set the camera position to Hong Kong
        LatLng hongKong = new LatLng(22.3193, 114.1694);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hongKong, 10));

        googleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                View view = getLayoutInflater().inflate(R.layout.info_window, null);

                ImageView image = view.findViewById(R.id.info_window_image);
                TextView title = view.findViewById(R.id.info_window_title);

                House cur_house = houseDao.getHouseById(Integer.parseInt(marker.getTitle()));

                title.setText(cur_house.getTitle());
                Bitmap bitmap = cur_house.getHouse_image();
                if (bitmap != null) {
                    int width = bitmap.getWidth();
                    int height = bitmap.getHeight();
                    float scale = Math.min((float) MAX_WIDTH / width, (float) MAX_HEIGHT / height);
                    Matrix matrix = new Matrix();
                    matrix.postScale(scale, scale);
                    Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
                    image.setImageBitmap(resizedBitmap);
                }

                return view;
            }
        });

        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Log.d("Info Window:", marker.getTitle());
                Intent intent = new Intent(GoogleMapActivity.this, DetailActivity.class);
                intent.putExtra("house_id", marker.getTitle());
                startActivity(intent);
            }
        });

    }


}