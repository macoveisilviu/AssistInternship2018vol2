package com.assist.assistteachme;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
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
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.assist.assistteachme.Adapters.ChapterQuestionsAdapter;
import com.assist.assistteachme.Adapters.CourseButtonAdapter;
import com.assist.assistteachme.Adapters.RecycleViewAdapterS;
import com.assist.assistteachme.Models.CategoriesRecive;
import com.assist.assistteachme.Models.ChapterQuestion;
import com.assist.assistteachme.Models.CourseButton;
import com.assist.assistteachme.Network.RestClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DrawerTestActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Button backButon;
    Button openButton;
    TextView coursesTextView;
    NavigationView nav;
    TextView whatsNewTextView;
    TextView aboutTextView;
    TextView nameTextView;
    Context context;
    TextView courseName;
    Button discoverMoreButton;
    boolean buttonIsPressed=false;
    boolean lessthan4=false;
    int counter=0;

    private RecyclerView recyclerView;
    private CourseButtonAdapter adapter;
    private ArrayList<CourseButton> courseButtonArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_test);
        nav = findViewById(R.id.nav_view);
        discoverMoreButton = findViewById(R.id.discoverMoreButton);

        recyclerView = findViewById(R.id.recycleViewCourse);
        recyclerView.setHasFixedSize(true);
        courseButtonArrayList = new ArrayList<>();
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(DrawerTestActivity.this, 2);
        recyclerView.setLayoutManager(mGridLayoutManager);
        courseName = findViewById(R.id.courseNameTextView);
        CategoriesToApi(false);

        adapter = new CourseButtonAdapter(courseButtonArrayList, this);
        recyclerView.setAdapter(adapter);


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
                startActivity(new Intent(DrawerTestActivity.this, DrawerTestActivity.class));
            }
        });
        whatsNewTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Nothing new", Toast.LENGTH_SHORT).show();
            }
        });
        aboutTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Developed by Sofian And Costel", Toast.LENGTH_SHORT).show();
            }
        });
        nameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DrawerTestActivity.this, MyAccountMenuDrawer.class));
            }
        });

        discoverMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //courseButtonArrayList.clear();
                counter++;
                if(counter==1)
                CategoriesToApi(true);
                recyclerView.setAdapter(adapter);
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
        getMenuInflater().inflate(R.menu.drawer_test, menu);
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

       /* if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void populateWithDummyData(int i, int max) {
        Drawable a;
        for (i = 0; i < max; i++) {
            if (i % 3 == 0)
                a = getResources().getDrawable(R.drawable.gradient_astrology_round_corners);
            else if (i % 3 == 1)
                a = getResources().getDrawable(R.drawable.gradient_finnance_round_corners);
            else
                a = getResources().getDrawable(R.drawable.gradient_else_round_corners);
            CourseButton c = new CourseButton("Category " + i, a);
            courseButtonArrayList.add(c);
        }
    }

    public void CategoriesToApi(final boolean greaterThan4) {
        RestClient.networkHandler().getCategories().enqueue(new Callback<ArrayList<CategoriesRecive>>() {
            @Override
            public void onResponse(Call<ArrayList<CategoriesRecive>> call, Response<ArrayList<CategoriesRecive>> response) {
                ArrayList<CategoriesRecive> newCategoriesRecive = response.body();
                Drawable a;

                if (response.isSuccessful()) {
                    if (newCategoriesRecive.size() >4) {
                        if(greaterThan4==false) {
                            for (int i = 0; i < 4; i++) {
                                if (i % 3 == 0)
                                    a = getResources().getDrawable(R.drawable.gradient_astrology_round_corners);
                                else if (i % 3 == 1)
                                    a = getResources().getDrawable(R.drawable.gradient_finnance_round_corners);
                                else
                                    a = getResources().getDrawable(R.drawable.gradient_else_round_corners);
                                CourseButton c = new CourseButton(newCategoriesRecive.get(i).getName(), a);
                                courseButtonArrayList.add(c);
                            }
                        }
                        else {
                            for (int i = 4; i < newCategoriesRecive.size(); i++) {
                                if (i % 3 == 0)
                                    a = getResources().getDrawable(R.drawable.gradient_astrology_round_corners);
                                else if (i % 3 == 1)
                                    a = getResources().getDrawable(R.drawable.gradient_finnance_round_corners);
                                else
                                    a = getResources().getDrawable(R.drawable.gradient_else_round_corners);
                                CourseButton c = new CourseButton(newCategoriesRecive.get(i).getName(), a);
                                courseButtonArrayList.add(c);
                            }
                        }
                    }
                    else{
                        for (int i = 0; i < newCategoriesRecive.size(); i++) {
                            if (i % 3 == 0)
                                a = getResources().getDrawable(R.drawable.gradient_astrology_round_corners);
                            else if (i % 3 == 1)
                                a = getResources().getDrawable(R.drawable.gradient_finnance_round_corners);
                            else
                                a = getResources().getDrawable(R.drawable.gradient_else_round_corners);
                            CourseButton c = new CourseButton(newCategoriesRecive.get(i).getName(), a);
                            courseButtonArrayList.add(c);
                        }
                    }

                }
            }

            @Override
            public void onFailure(Call<ArrayList<CategoriesRecive>> call, Throwable t) {

            }
        });
    }
}
