package com.assist.assistteachme;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anairda on 8/16/2018.
 */

public class MainViewActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    /*  RecyclerView mRecyclerView;*/
    List mCategory;
    AlbumCategory mFlowerData;
    ImageButton btnMenu, btnAbout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);


       /* mRecyclerView = findViewById(R.id.recyclerview);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(MainViewActivity.this, 2);
        mRecyclerView.setLayoutManager(mGridLayoutManager);*/

        mCategory = new ArrayList<>();
        mFlowerData = new AlbumCategory("Math", getString(R.string.description_flower_rose),
                R.drawable.rectangle1);
        mCategory.add(mFlowerData);
        mFlowerData = new AlbumCategory("Bio", getString(R.string.description_flower_carnation),
                R.drawable.rectangle2);


       /* AdapterAlbumCategory myAdapter = new  AdapterAlbumCategory(MainViewActivity.this, mCategory);
        mRecyclerView.setAdapter(myAdapter);*/


        btnMenu = (ImageButton) findViewById(R.id.btnMenu);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drawer.openDrawer(Gravity.RIGHT);
            }

        });


        btnAbout = (ImageButton) findViewById(R.id.about);
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainViewActivity.this, AccountActivity.class));
            }

        });


    }//onCreate


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(Gravity.RIGHT)) {
            drawer.closeDrawer(Gravity.RIGHT);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.courses) {
            startActivity(new Intent(MainViewActivity.this, SignupActivity.class));
        } else if (id == R.id.whatsNew) {

        } else if (id == R.id.about) {
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}

