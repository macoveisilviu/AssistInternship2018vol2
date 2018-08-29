package com.assist.assistteachme.MyAccountDoc;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TextInputEditText;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.assist.assistteachme.LoginActivityDoc.LoginActivity;
import com.assist.assistteachme.MainViewDoc.MainViewActivity;
import com.assist.assistteachme.Network.RestClient;
import com.assist.assistteachme.R;
import com.assist.assistteachme.SignUpActivityDoc.SignUpResponseModel;
import com.assist.assistteachme.User;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.ArrayList;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountActivity extends AppCompatActivity implements RecyclerViewAdapterDoc.OnItemClickListener {
    DrawerLayout drawer;
    ImageButton btnMenu;
    EditText name, email;
    TextView accountNameNav;
    TextInputEditText password;
    String emailValidation, passValidation, nameValidation;
    Button ChooseButton, btnSaveChanges, btnCancelChanges, btnDeleteCourse, btnLogOut;
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

        populateDummyData();
        initVariables();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.bringToFront();

        buttonsOnClickImplement();

        recyclerViewInit();
        getUserDetails();


    }// onCreate


    public void clickButtonView(View view) {
        TextView coursesNav = (TextView) findViewById(R.id.coursesNav);
        coursesNav.setBackgroundColor(getResources().getColor(R.color.blueButton));
        drawer.closeDrawer(Gravity.RIGHT);
        Intent intent = new Intent(AccountActivity.this, MainViewActivity.class);
        startActivity(intent);


        Log.d("OK Button", "pressed");
    }

    public void clickAccountBtn(View view) {
        drawer.closeDrawer(Gravity.RIGHT);
        Intent intent = new Intent(AccountActivity.this, AccountActivity.class);
        startActivity(intent);


    }


    public void clickCloseMenuBtn(View view) {
        drawer.closeDrawer(Gravity.RIGHT);

    }

    private void getUserDetails() {
        RestClient.networkHandler().accountInfo(User.getInstance().getLoginResponseModel()
                .getToken()).enqueue(new Callback<SignUpResponseModel>() {
            @Override
            public void onResponse(Call<SignUpResponseModel> call, Response<SignUpResponseModel> response) {

                if (response.code() == 200) {
                    String fullname = response.body().getFirstName().concat(" ").concat(response.body().getLastName());
                    name.setHint(fullname);
                    email.setHint(response.body().getMail());
                    accountNameNav.setText(fullname);
                } else {
                    Toast.makeText(AccountActivity.this, "Server is not working!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SignUpResponseModel> call, Throwable t) {

            }
        });
    }

    private void recyclerViewInit() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(AccountActivity.this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new RecyclerViewAdapterDoc(list, this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void buttonsOnClickImplement() {
        btnSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validateChanges()) {
                   Toast.makeText(getApplicationContext(), "Your information will be saved in the future!!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    //  Toast.makeText(getApplicationContext(), "Invalid email address or password!",
                    // Toast.LENGTH_SHORT).show();
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

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.RIGHT);
            }

        });

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getApplicationContext().getSharedPreferences("MY_PREF_NAME", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.clear();
                editor.commit();

                Intent intent = new Intent(AccountActivity.this, LoginActivity.class);
                startActivity(intent);

            }

        });
    }


    private void initVariables() {

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        btnMenu = (ImageButton) findViewById(R.id.btnMenu);
        btnLogOut = (Button) findViewById(R.id.buttonLogout);
        btnDeleteCourse = (Button) findViewById(R.id.btnDeleteCourse);
        ChooseButton = (Button) findViewById(R.id.discoverBtn);
        SelectImage = (ImageView) findViewById(R.id.profilePhoto);
        btnSaveChanges = (Button) findViewById(R.id.btnSaveChange);
        btnCancelChanges = (Button) findViewById(R.id.btnCancelChange);
        accountNameNav=(TextView)findViewById(R.id.accountNameNav);
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
        Log.d("CursClick", courses.points + "");


    }

    @Override
    public void onCourseDeleteBtnPressed(final Courses courses, int position) {

        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(AccountActivity.this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(AccountActivity.this);
        }
        builder.setTitle("Delete course")
                .setMessage("Are you sure you want to delete this course?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        list.remove(courses);
                        refreshRecycler();
                        deletePoints(courses);

                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

        recyclerView.setAdapter(new RecyclerViewAdapterDoc(list, this));
    }

    private void deletePoints(Courses courses) {
        int deleteNoPoint = courses.getPoints();
        Log.d("deleteNoPoints", "" + deleteNoPoint);
        int finalpoints;
        TextView pointsNav = (TextView) findViewById(R.id.points);
        int totalPoints = Integer.parseInt(pointsNav.getText().toString());
        finalpoints = totalPoints - deleteNoPoint;
        pointsNav.setText(String.valueOf(finalpoints));


    }

    private void refreshRecycler() {
        recyclerView.setAdapter(new RecyclerViewAdapterDoc(list, this));
    }
}

