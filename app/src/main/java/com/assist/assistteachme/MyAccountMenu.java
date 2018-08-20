package com.assist.assistteachme;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class MyAccountMenu extends AppCompatActivity {

    Button logOutButton;
    ImageView imageView;
    Button uploadButton;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account_menu);

        logOutButton = findViewById(R.id.activity_my_account_button);
        imageView = findViewById(R.id.activity_my_account_photo);
        uploadButton = findViewById(R.id.upload_button);


        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyAccountMenu.this, LoginScreenActivity.class));
            }
        });


        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            imageUri = data.getData();
            Log.i("FileDebug", "Path: " + imageUri.getPath() +
                    " size: " + new File(imageUri.getPath()).length() +
                    " status: " + MaxSizeImage(imageUri.getPath()));

            //File file = new File( imageUri.getPath());
            //file.length();
            //Log.i("File", file.length()+"");
            imageView.setImageURI(imageUri);
        }


    }

    public boolean MaxSizeImage(String imagePath) {
        boolean temp = false;
        File file = new File(imagePath);
        long length = file.length();

        if (length < 22500000) // 15 mb
            temp = true;

        return temp;
    }


}







