package com.assist.assistteachme.ChapterActivityDoc;

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

import com.assist.assistteachme.MainViewDoc.MainViewActivity;
import com.assist.assistteachme.MyAccountDoc.AccountActivity;
import com.assist.assistteachme.QuestionActivityDoc.QuestionActivity;
import com.assist.assistteachme.R;

import java.util.ArrayList;

/**
 * Created by anairda on 8/21/2018.
 */

public class ChapterActivity extends AppCompatActivity implements RecyclerViewAdapterChapter.OnItemClickListener {
    RecyclerView recyclerView;
    DrawerLayout drawer;
    ImageButton btnMenu;
    ArrayList<ChapterModel> list = new ArrayList<ChapterModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chapter_activity);

        initVariables();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.bringToFront();
        populateDummyData();
        recyclerViewInit();
        buttonsOnClick();


    }

    private void buttonsOnClick() {
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.RIGHT);
            }

        });
    }

    public void clickButtonView(View view) {
        TextView coursesNav = (TextView) findViewById(R.id.coursesNav);
        coursesNav.setBackgroundColor(getResources().getColor(R.color.blueButton));
        drawer.closeDrawer(Gravity.RIGHT);
        Intent intent = new Intent(ChapterActivity.this, MainViewActivity.class);
        startActivity(intent);


        Log.d("OK Button", "pressed");
    }

    public void clickAccountBtn(View view) {
        drawer.closeDrawer(Gravity.RIGHT);
        Intent intent = new Intent(ChapterActivity.this, AccountActivity.class);
        startActivity(intent);


    }


    public void clickCloseMenuBtn(View view) {
        drawer.closeDrawer(Gravity.RIGHT);

    }

    private void initVariables() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        btnMenu = (ImageButton) findViewById(R.id.btnMenu);
    }

    private void recyclerViewInit() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(ChapterActivity.this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new RecyclerViewAdapterChapter(list, this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void populateDummyData() {
        ChapterModel one = new ChapterModel("CHAPTER I",
                "Welcome To Desiclassifieds Free Classifieds Free Ads",
                "It’s hard to say when in our lives each of us become aware of this thing called “astronomy”. " +
                        " But it is safe to say that at some point on our lives, each and every one of us has that moment when " +
                        "we are suddenly stunned when we come face to face with the enormity of the universe that we see in the" +
                        " night sky.");
        ChapterModel two = new ChapterModel("CHAPTER II",
                "Welcome To Desiclassifieds Free Classifieds Free Ads",
                "It’s hard to say when in our lives each of us become aware of this thing called “astronomy”. " +
                        " But it is safe to say that at some point on our lives, each and every one of us has that moment when " +
                        "we are suddenly stunned when we come face to face with the enormity of the universe that we see in the" +
                        " night sky.");
        ChapterModel thre = new ChapterModel("CHAPTER III",
                "Welcome To Desiclassifieds Free Classifieds Free Ads",
                "It’s hard to say when in our lives each of us become aware of this thing called “astronomy”. " +
                        " But it is safe to say that at some point on our lives, each and every one of us has that moment when " +
                        "we are suddenly stunned when we come face to face with the enormity of the universe that we see in the" +
                        " night sky.");
        list.add(one);
        list.add(two);
        list.add(thre);
    }


    @Override
    public void onChapterClick(ChapterModel chapterModel) {
        startActivity(new Intent(ChapterActivity.this, QuestionActivity.class));

    }
}
