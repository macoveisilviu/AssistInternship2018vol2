package com.assist.assistteachme;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;

public class MainViewActivity extends AppCompatActivity {

    /*private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToogle;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);
       /* mDrawerLayout=findViewById(R.id.navigation_drawer);
        mToogle = new ActionBarDrawerToggle(this, mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToogle);
        mToogle.syncState();*/
        //if(getSupportActionBar()!=null)
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }



}
