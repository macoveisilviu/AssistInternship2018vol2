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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.assist.assistteachme.MainViewDoc.MainViewActivity;
import com.assist.assistteachme.MyAccountDoc.AccountActivity;
import com.assist.assistteachme.Network.RestClient;
import com.assist.assistteachme.QuestionActivityDoc.QuestionActivity;
import com.assist.assistteachme.R;
import com.assist.assistteachme.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by anairda on 8/21/2018.
 */

public class ChapterActivity extends AppCompatActivity implements RecyclerViewAdapterChapter.OnItemClickListener {
    RecyclerView recyclerView;
    DrawerLayout drawer;
    ImageButton btnMenu;
    Button discoverBtn, btnStartChapter;
    ArrayList<ChapterResponseModel> chapterList = new ArrayList<ChapterResponseModel>();
    ArrayList<ChapterResponseModel> newChapterList = new ArrayList<ChapterResponseModel>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chapter_activity);

        initVariables();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.bringToFront();
        populateDataApi();
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
        discoverBtn = (Button) findViewById(R.id.discoverBtn);
        btnStartChapter = (Button) findViewById(R.id.btnStartChapter);
    }

    private void recyclerViewInit(ArrayList<ChapterResponseModel> chapterListFromAP) {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(ChapterActivity.this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new RecyclerViewAdapterChapter(chapterListFromAP, this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void populateDataApi() {
        Intent intent = ChapterActivity.this.getIntent();
        Integer coursesId = 0;

        coursesId = intent.getIntExtra("coursesId", 0);
        Log.d("coursesId", "" + coursesId);

        RestClient.networkHandler().getChapterApi(User.getInstance().getLoginResponseModel().getToken(), coursesId).
                enqueue(new Callback<ArrayList<ChapterResponseModel>>() {

                    @Override
                    public void onResponse(Call<ArrayList<ChapterResponseModel>> call,
                                           Response<ArrayList<ChapterResponseModel>> response) {

                        if (response.code() == 200) {
                            chapterList = response.body();
                            Log.d("chapterlist", "" + chapterList);
                            if (chapterList.size() > 3) {
                                discoverBtn.setVisibility(View.VISIBLE);

                            } else {
                                discoverBtn.setVisibility(View.INVISIBLE);
                            }
                            try {
                                if (chapterList.size() != 0)
                                    for (int i = 0; i < 3; i++) {
                                        newChapterList.add(chapterList.get(i));
                                    }
                                recyclerViewInit(newChapterList);
                            } catch (Exception e) {
                                Toast.makeText(ChapterActivity.this, "There aren't chapters for this course!", Toast.LENGTH_SHORT).show();

                            }

                            Log.d("coursesResponse:", " " + chapterList);

                        } else {
                            Toast.makeText(ChapterActivity.this, "There aren't chapters for this course!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<ChapterResponseModel>> call, Throwable t) {

                    }


                });

    }


    @Override
    public void onChapterClick(final ChapterResponseModel chapterModel) {

        Intent detailItent = new Intent(ChapterActivity.this, QuestionActivity.class);

        detailItent.putExtra("chapterId", chapterModel.getId());
        startActivity(detailItent);
        Log.d("chapterId", "" + chapterModel.getId());
    }


}

