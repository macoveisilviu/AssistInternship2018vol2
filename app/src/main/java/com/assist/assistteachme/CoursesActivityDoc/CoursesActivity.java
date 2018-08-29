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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.assist.assistteachme.ChapterActivityDoc.ChapterActivity;
import com.assist.assistteachme.MainViewDoc.MainViewActivity;
import com.assist.assistteachme.MyAccountDoc.AccountActivity;
import com.assist.assistteachme.Network.RestClient;
import com.assist.assistteachme.R;
import com.assist.assistteachme.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by anairda on 8/20/2018.
 */

public class CoursesActivity extends AppCompatActivity implements RecyclerViewAdapterCourses.OnItemClickListener {
    EditText searchTxt;
    DrawerLayout drawer;
    ImageButton btnMenu;
    Button discoverBtn, searchBtn;
    RecyclerView recyclerView;


    ArrayList<CoursesResponseModel> coursesList = new ArrayList<CoursesResponseModel>();
    ArrayList<CoursesResponseModel> newCoursesList = new ArrayList<CoursesResponseModel>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.courses_activity);


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.bringToFront();

        initVariables();
        buttonsOnClick();
        populateDataApi();


    }//onCreate

    private void recyclerViewInit(ArrayList<CoursesResponseModel> coursesListFromAP) {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(CoursesActivity.this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new RecyclerViewAdapterCourses(coursesListFromAP, this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }


    private void initVariables() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        btnMenu = (ImageButton) findViewById(R.id.btnMenu);
        discoverBtn = (Button) findViewById(R.id.discoverBtn);
        searchBtn = (Button) findViewById(R.id.buttonSearch);
        searchTxt = (EditText) findViewById(R.id.searchTxt);
    }


    private void buttonsOnClick() {
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.RIGHT);
            }

        });

        discoverBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int maxSize = newCoursesList.size();

                try {
                    for (int i = maxSize; i < maxSize + 4; i++) {
                        newCoursesList.add(coursesList.get(i));
                        recyclerViewInit(newCoursesList);
                    }
                } catch (Exception e) {
                    discoverBtn.setVisibility(View.INVISIBLE);
                }
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String searchApi = searchTxt.getText().toString();
                SearchCoursesModel category = new SearchCoursesModel(searchApi);


                RestClient.networkHandler().searchCourses(User.getInstance().getLoginResponseModel().getToken(),
                        "application/json", category).
                        enqueue(new Callback<ArrayList<CoursesResponseModel>>() {

                            @Override
                            public void onResponse(Call<ArrayList<CoursesResponseModel>> call, Response<ArrayList<CoursesResponseModel>> response) {

                                ArrayList<CoursesResponseModel> searchListCourses = new ArrayList<CoursesResponseModel>();

                                if (response.code() == 200) {
                                    searchListCourses = response.body();

                                if (searchListCourses.size() > 3) {
                                    discoverBtn.setVisibility(View.VISIBLE);

                                } else {
                                    discoverBtn.setVisibility(View.INVISIBLE);
                                }

                                    recyclerViewInit(searchListCourses);
                                    Log.d("coursesResponse:", " " + coursesList);
                                }

                                else
                                {
                                    populateDataApi();
                                }
                            }

                            @Override
                            public void onFailure(Call<ArrayList<CoursesResponseModel>> call, Throwable t) {

                            }


                        });


            }
        });}


            private void populateDataApi() {
                Intent intent = CoursesActivity.this.getIntent();
                Integer categoryId = 0;

                categoryId = intent.getIntExtra("categoryId", 0);
                Log.d("categoryIdd", "" + categoryId);

                RestClient.networkHandler().getCoursesApi(User.getInstance().getLoginResponseModel().getToken(),
                        "application/json", categoryId).
                        enqueue(new Callback<ArrayList<CoursesResponseModel>>() {

                            @Override
                            public void onResponse(Call<ArrayList<CoursesResponseModel>> call, Response<ArrayList<CoursesResponseModel>> response) {

                                if (response.code() == 200) {
                                    coursesList = response.body();

                                    if(coursesList.size()==0)
                                    {
                                        Toast.makeText(CoursesActivity.this, "There are no courses at this category!", Toast.LENGTH_SHORT).show();
                                    }
                                    if (coursesList.size() > 3) {
                                        discoverBtn.setVisibility(View.VISIBLE);

                                    } else {
                                        discoverBtn.setVisibility(View.INVISIBLE);
                                    }
                                    for (int i = 0; i < 3; i++) {
                                        newCoursesList.add(coursesList.get(i));
                                    }
                                    recyclerViewInit(coursesList);
                                    Log.d("coursesResponse:", " " + coursesList);

                                }
                            }

                            @Override
                            public void onFailure(Call<ArrayList<CoursesResponseModel>> call, Throwable t) {

                            }
                        });

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


            @Override
            public void onCourseClick(CoursesResponseModel courses) {

                Intent detailItent = new Intent(CoursesActivity.this, ChapterActivity.class);

                detailItent.putExtra("coursesId",courses.getId());
                startActivity(detailItent);
                Log.d("coursesId", ""+courses.getId());

            }
        }
