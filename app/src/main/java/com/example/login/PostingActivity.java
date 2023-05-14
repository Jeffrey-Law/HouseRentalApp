package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import javax.net.ssl.HostnameVerifier;

public class PostingActivity extends AppCompatActivity implements
AdapterView.OnItemSelectedListener{

    String[] options = {"Please Select","Yes", "No"};
    String[] houseTypes = {"House", "Apartment & unit", "Townhouse", "Villa", "Others"};

    String[] publishTime = {"7 Days", "30 Days", "90 Days", "180 Days", "365 Days"};
    Spinner furnished, petConsidered, visibility, houseType, publishingTime;

    TextView price, address, numOfBedroom, numOfCarSpace, description;
    Button upLoadPhotoBtn = findViewById(R.id.uploadPhoto_btn);
    Button postingBtn = findViewById(R.id.posting_btn);

    AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posting);

        appDatabase = MainActivity.getAppDatabase();

        price = findViewById(R.id.price_tv);
        address = findViewById(R.id.address_tv);
        numOfBedroom = findViewById(R.id.bedroom_tv);
        numOfCarSpace = findViewById(R.id.carspace_tv);

        furnished = findViewById(R.id.furnished_spinner);
        petConsidered = findViewById(R.id.pet_spinner);
        visibility = findViewById(R.id.visibility_spinner);
        houseType = findViewById(R.id.housetype_spinner);
        publishingTime = findViewById(R.id.publishing_time_spinner);

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

        furnished.setAdapter(optionsAd);
        furnished.setOnItemSelectedListener(this);

        petConsidered.setAdapter(optionsAd);
        petConsidered.setOnItemSelectedListener(this);

        visibility.setAdapter(optionsAd);
        visibility.setOnItemSelectedListener(this);

        houseType.setAdapter(houseTypesAd);
        houseType.setOnItemSelectedListener(this);

        //upLoadPhotoBtn.setOnClickListener();
        postingBtn.setOnClickListener(posting);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private View.OnClickListener posting = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            String price_txt = price.getText().toString();
            String address_txt = address.getText().toString();
            String numOfBedroom_txt = numOfBedroom.getText().toString();
            String numOfCarSpace_txt = numOfBedroom.getText().toString();
            int furnished_sel = furnished.getSelectedItemPosition();
            int petConsidered_sel = petConsidered.getSelectedItemPosition();
            String houseType_sel = houseType.getSelectedItem().toString();

            Context context = getApplicationContext();
            if(price_txt.length()==0 || address_txt.length()==0 || numOfBedroom_txt.length()==0 ||
            numOfCarSpace_txt.length()==0 || furnished_sel == 0 || petConsidered_sel == 0){
                CharSequence text = "Please fill in all the necessary data";
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
            }else{
                House house = new House();
                CharSequence text = "Posted";
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
            }

        }
    };
}




//    private void loadImagesFromGallery() {
//
//        if (ActivityCompat.checkSelfPermission(MakeNotes.this,
//                Manifest.permission.READ_EXTERNAL_STORAGE) !=              PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(MakeNotes.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
//                    100);
//            return;
//        }
//
//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
//        intent.setType("image/*");
//        startActivityForResult(intent, 1);
//
//
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
//    {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 1 && resultCode == RESULT_OK)
//        {
//            imageFragmentContainer.setVisibility(View.VISIBLE);
//            bitmaps = new ArrayList<>();
//            imageSources = new ArrayList<>();
//            ClipData clipData = data.getClipData();
//            //clip data will be null if user select one item from gallery
//
//            if (clipData != null){
//                for (int i = 0; i < clipData.getItemCount(); i++)
//                {
//                    Uri imageUri = clipData.getItemAt(i).getUri();
//                    try{
//                        InputStream is = getContentResolver().openInputStream(imageUri);
//                        Bitmap bitmap = BitmapFactory.decodeStream(is);
//                        bitmaps.add(bitmap);
//                        String imageSource =  ImageBitmapString.BitMapToString(bitmap);
//                        imageSources.add(imageSource);
//                    }
//                    catch (FileNotFoundException e){e.printStackTrace();}
//
//                }
//            }
//            else {
//                Uri imageUri = data.getData();
//                try {
//                    InputStream is = getContentResolver().openInputStream(imageUri);
//                    Bitmap bitmap = BitmapFactory.decodeStream(is);
//                    bitmaps.add(bitmap);
//                    String imageSource =  ImageBitmapString.BitMapToString(bitmap);
//                    imageSources.add(imageSource);
//                }
//                catch (FileNotFoundException e){e.printStackTrace();}
//            }
//
//            RecyclerView imageRecycleView = findViewById(R.id.imageRecycleView);
//            imageRecycleView.setHasFixedSize(true);
//            imageRecycleView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
//            adapter = new ImageAdapter(bitmaps);
//            imageRecycleView.setAdapter(adapter);
//
//        }
//
//    }
