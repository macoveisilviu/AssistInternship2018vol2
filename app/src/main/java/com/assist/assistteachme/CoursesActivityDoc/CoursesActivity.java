package com.assist.assistteachme.CoursesActivityDoc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.assist.assistteachme.ChapterActivityDoc.ChapterActivity;
import com.assist.assistteachme.R;

import java.util.ArrayList;

/**
 * Created by anairda on 8/20/2018.
 */

public class CoursesActivity extends AppCompatActivity implements RecyclerViewAdapterCourses.OnItemClickListener {


    RecyclerView recyclerView;

    ArrayList<AllCourses> list = new ArrayList<AllCourses>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.courses_activity);
        populateDummyData();


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(CoursesActivity.this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new RecyclerViewAdapterCourses(list, this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }//onClick

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
