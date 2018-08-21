package com.assist.assistteachme.MyAccount;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.assist.assistteachme.R;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class AccountActivity extends AppCompatActivity implements RecyclerViewAdapterDoc.OnItemClickListener {

    public EditText name, email;
    public TextInputEditText password;
    public String emailValidation, passValidation, nameValidation;
    Button ChooseButton, btnSaveChanges, btnCancelChanges, btnDeleteCourse;
    ImageView SelectImage;
    private Uri mimage = null;

    Uri FilePathUri;
    int Image_Request_Code = 7;

    RecyclerView recyclerView;
    ArrayList<Courses> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_activity);

        //.setVisibility(View.GONE);
        //.setVisibility(View.VISIBLE);

        populateDummyData();
        initVariables();
        buttonsOnClickImplement();






        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(AccountActivity.this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new RecyclerViewAdapterDoc(list, this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());






    }// onCreate

    private void buttonsOnClickImplement() {
        btnSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validateChanges()) {
               /*     Toast.makeText(getApplicationContext(), "Your account was created!",
                            Toast.LENGTH_SHORT).show();*/
                } else {
                    //  Toast.makeText(getApplicationContext(), "Invalid email address or password!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnCancelChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText("");
                email.setText("");
                password.setText("");
            }
        });

        ChooseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Please Select Image"),
                        Image_Request_Code);
            }
        });
    }

    private void initVariables() {
       btnDeleteCourse=(Button)findViewById(R.id.btnDeleteCourse);
        ChooseButton = (Button) findViewById(R.id.chooseBtn);
        SelectImage = (ImageView) findViewById(R.id.profilePhoto);
        btnSaveChanges = (Button) findViewById(R.id.btnSaveChange);
        btnCancelChanges = (Button) findViewById(R.id.btnCancelChange);
        name = (EditText) findViewById(R.id.nameAccount);
        email = (EditText) findViewById(R.id.emailAccount);
        password = (TextInputEditText) findViewById(R.id.PassAccount);
        emailValidation = email.getText().toString().trim();
        passValidation = password.getText().toString().trim();
        nameValidation = name.getText().toString().trim();


    }

    private void populateDummyData() {

        Courses one = new Courses(R.drawable.profile,
                "Internet Banner Advertising Most Reliable Forms Of Web Advertising",
                "In order to discuss the general function of the logo, we must " +
                        "firstly identify and define the environment where this will have to fulfill its function. ",
                90);
        Courses two = new Courses(R.drawable.profile,
                "Internet Banner Advertising Most Reliable Forms Of Web Advertising",
                "In order to discuss the general function of the logo," +
                        " we must firstly identify and define the environment where this will have to fulfill its function. ",
                120);
        Courses three = new Courses(R.drawable.profile,
                "Internet Banner Advertising Most Reliable Forms Of Web Advertising",
                "In order to discuss the general function of the logo, we must " +
                        "firstly identify and define the environment where this will have to fulfill its function. ",
                80);
        list.add(one);
        list.add(two);
        list.add(three);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Image_Request_Code &&
                resultCode == RESULT_OK &&
                data != null && data.getData() != null) {


            FilePathUri = data.getData();
            CropImage.activity(FilePathUri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1, 1)
                    .start(this);
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                mimage = result.getUri();
                SelectImage.setImageURI(mimage);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

    // Creating Method to get the selected image file Extension from File Path URI.
    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();

        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();

        // Returning the file Extension.
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));

    }

    private boolean validateChanges() {
        Pattern specialCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Pattern UpperCasePatten = Pattern.compile("[A-Z ]");
        Pattern lowerCasePatten = Pattern.compile("[a-z ]");
        Pattern digitCasePatten = Pattern.compile("[0-9 ]");
        if (TextUtils.isEmpty(emailValidation)
                && TextUtils.isEmpty(passValidation)
                && TextUtils.isEmpty(nameValidation)
                ) {

            email.setError("Enter email!");
            password.setError("Enter password!");
            name.setError("Enter name!");


            return false;
        }
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(emailValidation).matches()) {
        } else {
            email.setError("Email is not valid");
            return false;
        }

        if (TextUtils.isEmpty(emailValidation)) {
            email.setError("Enter email address!");
            return false;
        }
        if (TextUtils.isEmpty(passValidation)) {
            password.setError("Enter password!");
            return false;
        }

        if (TextUtils.isEmpty(nameValidation)) {
            name.setError("Enter name!");
            return false;
        }

        if (digitCasePatten.matcher(nameValidation).find()) {
            name.setError("Name should contain only characters!");
            return false;
        }


        if (specialCharPatten.matcher(nameValidation).find()) {
            name.setError("Name should contain only characters!");
            return false;
        }


        if (passValidation.length() < 8) {
            password.setError("Password should contain at least 8 characters!");
            return false;
        }
        if (!specialCharPatten.matcher(passValidation).find()) {
            password.setError("Password must have at least one special character!");
            return false;
        }
        if (!UpperCasePatten.matcher(passValidation).find()) {
            password.setError("Password must have at least one uppercase character!");
            return false;
        }
        if (!lowerCasePatten.matcher(passValidation).find()) {
            password.setError("Password must have at least one lowercase character!");
            return false;
        }
        if (!digitCasePatten.matcher(passValidation).find()) {
            password.setError("Password must have at least one digit!");
            return false;
        }
        return true;
    }

    @Override
    public void onCourseClick(Courses courses) {
        Log.d("CursClick", courses.points+"");




  /*      btnDeleteCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             *//*   public void remove(Courses courses) {
                    int position = items.indexOf(item);
                    cou.remove(position);
                    notifyItemRemoved(position);
                }*//*

            }
        });*/

    }
}

