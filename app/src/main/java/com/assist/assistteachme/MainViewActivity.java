package com.assist.assistteachme;

import android.annotation.SuppressLint;
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
import android.widget.ImageButton;
import android.widget.TextView;

import com.assist.assistteachme.CoursesActivityDoc.CoursesActivity;
import com.assist.assistteachme.Login.Reset.SignUp.SignupActivity;
import com.assist.assistteachme.MyAccount.AccountActivity;

import java.util.List;

/**
 * Created by anairda on 8/16/2018.
 */

public class MainViewActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    /*  RecyclerView mRecyclerView;*/
    List mCategory;

    ImageButton btnMenu,btnAbout, courses;




    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);


        btnMenu = (ImageButton) findViewById(R.id.btnMenu);
       courses = (ImageButton) findViewById(R.id.courses);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

      /*  NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);*/
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drawer.openDrawer(Gravity.RIGHT);
            }

        });

    btnAbout = (ImageButton) findViewById(R.id.aboutBTN);
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainViewActivity.this, AccountActivity.class));
            }

        });
       courses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainViewActivity.this, CoursesActivity.class));
            }

        });







        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerview=navigationView.getHeaderView(0);
       TextView accountName =(TextView)headerview.findViewById(R.id.accountName);

        accountName.setOnClickListener(new View.OnClickListener() {
            @Override    public void onClick(View v) {
                Intent intent = new Intent(MainViewActivity.this,AccountActivity.class);
                startActivity(intent);
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

        } else if (id == R.id.aboutBTN) {
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}

