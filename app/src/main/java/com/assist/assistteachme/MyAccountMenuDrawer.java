package com.assist.assistteachme;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.assist.assistteachme.Adapters.ChapterQuestionsAdapter;
import com.assist.assistteachme.Adapters.MyAccounteMenuAdapter;
import com.assist.assistteachme.Models.ChapterQuestion;
import com.assist.assistteachme.Models.CourseButton;
import com.assist.assistteachme.Models.MyAccountMenuModel;
import com.assist.assistteachme.Models.User;

import java.io.File;
import java.util.ArrayList;

public class MyAccountMenuDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button backButon;
    Button openButton;
    NavigationView nav;
    TextView coursesTextView;
    TextView whatsNewTextView;
    TextView aboutTextView;
    TextView nameTextView;
    Context context;
    Button closeButton;

    Button logOutButton;
    ImageView imageView;
    Button uploadButton;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;

    private ArrayList<MyAccountMenuModel> listMyAccountMenu;

    //pentru recycleview
    private RecyclerView recycleView;
    private MyAccounteMenuAdapter myAccounteMenuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account_menu_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        closeButton = findViewById(R.id.my_account_menu_redclosebutton);

        //recycle view
        recycleView = findViewById(R.id.recycle_view_my_account_menu);
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        listMyAccountMenu = new ArrayList<>();

        populateWithDummyData();


        myAccounteMenuAdapter = new MyAccounteMenuAdapter( listMyAccountMenu,this);
        recycleView.setAdapter(myAccounteMenuAdapter);

        //recycle closed

        nav=findViewById(R.id.nav_view);
        backButon = findViewById(R.id.backButton);
        openButton = findViewById(R.id.openButton);
        whatsNewTextView = findViewById(R.id.whatsTextView);
        aboutTextView = findViewById(R.id.aboutTextView);
        nameTextView = findViewById(R.id.nameTextView);
        context = getApplicationContext();

        logOutButton = findViewById(R.id.activity_my_account_button);
        imageView = findViewById(R.id.activity_my_account_photo);
        uploadButton = findViewById(R.id.upload_button);

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        backButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.closeDrawer(GravityCompat.END);
            }
        });

        openButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.END);
            }
        });


        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().clear().apply();
                startActivity(new Intent(MyAccountMenuDrawer.this, LoginScreenActivity.class));
            }
        });
        coursesTextView = findViewById(R.id.coursesTextView);

        coursesTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyAccountMenuDrawer.this, DrawerTestActivity.class));
            }
        });
        whatsNewTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Nothing new" ,Toast.LENGTH_SHORT).show();
            }
        });
        aboutTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Developed by Sofian And Costel" ,Toast.LENGTH_SHORT).show();
            }
        });
        nameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyAccountMenuDrawer.this, MyAccountMenuDrawer.class));
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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_account_menu_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void populateWithDummyData(){
        Drawable a;
        for(int i=0;i<3;i++){
            if(i%3==0)
                a=getResources().getDrawable(R.drawable.gradient_astrology_round_corners);
            else if(i%3==1)
                a=getResources().getDrawable(R.drawable.gradient_finnance_round_corners);
            else
                a=getResources().getDrawable(R.drawable.gradient_else_round_corners);
            MyAccountMenuModel c= new MyAccountMenuModel("Text1 "+i,"Text2","Category",a);
            listMyAccountMenu.add(c);
        }
    }
}
