package com.assist.assistteachme.CoursesActivityDoc;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.assist.assistteachme.ChapterActivityDoc.ChapterActivity;
import com.assist.assistteachme.MainViewDoc.MainViewActivity;
import com.assist.assistteachme.MyAccountDoc.AccountActivity;
import com.assist.assistteachme.R;

import java.util.ArrayList;

/**
 * Created by anairda on 8/20/2018.
 */

public class CoursesActivity extends AppCompatActivity implements RecyclerViewAdapterCourses.OnItemClickListener {

    DrawerLayout drawer;
    ImageButton btnMenu;
    RecyclerView recyclerView;

    ArrayList<AllCourses> list = new ArrayList<AllCourses>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.courses_activity);

        populateDummyData();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.bringToFront();
        recyclerViewInit();
        initVariables();
        buttonsOnClick();


    }//onCreate

    private void buttonsOnClick() {
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.RIGHT);
            }

        });

    }

    private void initVariables() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        btnMenu = (ImageButton) findViewById(R.id.btnMenu);

    }

    public void clickButtonView(View view) {
        TextView coursesNav = (TextView) findViewById(R.id.coursesNav);
        coursesNav.setBackgroundColor(getResources().getColor(R.color.blueButton));
        drawer.closeDrawer(Gravity.RIGHT);
        Intent intent = new Intent(CoursesActivity.this, MainViewActivity.class);
        startActivity(intent);


        Log.d("OK Button", "pressed");
    }

    public void clickAccountBtn(View view) {
        drawer.closeDrawer(Gravity.RIGHT);
        Intent intent = new Intent(CoursesActivity.this, AccountActivity.class);
        startActivity(intent);


    }


    public void clickCloseMenuBtn(View view) {
        drawer.closeDrawer(Gravity.RIGHT);

    }

    private void recyclerViewInit() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(CoursesActivity.this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new RecyclerViewAdapterCourses(list, this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void populateDummyData() {
        AllCourses onee = new AllCourses(R.drawable.copacei,
                "Internet Banner Advertising Most Reliable Forms Of Web Advertising",
                "In order to discuss the general function of the logo, we must " +
                        "firstly identify and define the environment where this will have to fulfill its function. ");
        AllCourses twoe = new AllCourses(R.drawable.copacei,
                "Internet Banner Advertising Most Reliable Forms Of Web Advertising",
                "In order to discuss the general function of the logo," +
                        " we must firstly identify and define the environment where this will have to fulfill its function. ");
        AllCourses three = new AllCourses(R.drawable.copacei,
                "Internet Banner Advertising Most Reliable Forms Of Web Advertising",
                "In order to discuss the general function of the logo, we must " +
                        "firstly identify and define the environment where this will have to fulfill its function. ");
        list.add(onee);
        list.add(twoe);
        list.add(three);
    }


    @Override
    public void onCourseClick(AllCourses courses) {
        //Log.d("CursClick", courses.points + "");
        startActivity(new Intent(CoursesActivity.this, ChapterActivity.class));
    }


}
