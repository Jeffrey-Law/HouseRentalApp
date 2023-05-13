package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.os.Bundle;

public class PostingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posting);


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
}