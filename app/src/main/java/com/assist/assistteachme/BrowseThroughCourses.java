package com.assist.assistteachme;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import android.widget.TextView;
import android.widget.Toast;

import com.assist.assistteachme.Adapters.RecycleViewAdapterS;
import com.assist.assistteachme.Models.CourseDetails;

import java.util.ArrayList;

public class BrowseThroughCourses extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button backButon;
    Button openButton;
    NavigationView nav;
    TextView coursesTextView;
    TextView whatsNewTextView;
    TextView aboutTextView;
    TextView nameTextView;
    Context context;

    //pentru recycle view
    private RecyclerView recyclerView;
    private RecycleViewAdapterS adapterS;

    private ArrayList<CourseDetails> listCourseDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_through_courses);

        //recycle view

        recyclerView = findViewById(R.id.recycler_v);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listCourseDetails= new ArrayList<>();

        populateWithDummyData();



        //recycler view closed

        adapterS = new RecycleViewAdapterS(listCourseDetails, this);
        recyclerView.setAdapter(adapterS);

        nav=findViewById(R.id.nav_view);

        backButon = findViewById(R.id.backButton);
        openButton = findViewById(R.id.openButton);
        whatsNewTextView = findViewById(R.id.whatsTextView);
        aboutTextView = findViewById(R.id.aboutTextView);
        nameTextView = findViewById(R.id.nameTextView);
        context = getApplicationContext();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

        coursesTextView = findViewById(R.id.coursesTextView);

        coursesTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BrowseThroughCourses.this, DrawerTestActivity.class));
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
                startActivity(new Intent(BrowseThroughCourses.this, MyAccountMenuDrawer.class));
            }
        });
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
        getMenuInflater().inflate(R.menu.browse_through_courses, menu);
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
        for(int i=0; i<6; i++){
            CourseDetails s= new CourseDetails("title"+i, "subtitle", "category");
            listCourseDetails.add(s);
        }
    }
}
