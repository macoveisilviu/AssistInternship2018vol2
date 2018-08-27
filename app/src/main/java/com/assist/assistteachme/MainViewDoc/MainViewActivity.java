package com.assist.assistteachme.MainViewDoc;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.assist.assistteachme.CoursesActivityDoc.CoursesActivity;
import com.assist.assistteachme.MyAccountDoc.AccountActivity;
import com.assist.assistteachme.Network.RestClient;
import com.assist.assistteachme.R;
import com.assist.assistteachme.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by anairda on 8/16/2018.
 */

public class MainViewActivity extends AppCompatActivity implements RecyclerViewAdapterMainView.OnItemClickListener {
    DrawerLayout drawer;
    ImageButton btnMenu;
    Button discoverBtn;
    Toolbar toolbar;

    private RecyclerView.LayoutManager mLayoutManager;
    RecyclerView recyclerView;
    ArrayList<CategoryResponseModel> newCategoryList = new ArrayList<CategoryResponseModel>();
    ArrayList<CategoryResponseModel> categoryList = new ArrayList<CategoryResponseModel>();

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);

        initVariables();
        buttonsOnClick();
        populateDataApi();


        setSupportActionBar(toolbar);


    }//onCreate

    private void populateDataApi() {

        RestClient.networkHandler().getCategoryApi(User.getInstance().getLoginResponseModel()
                .getToken()).enqueue(new Callback<ArrayList<CategoryResponseModel>>() {
            @Override
            public void onResponse(Call<ArrayList<CategoryResponseModel>> call, Response<ArrayList<CategoryResponseModel>> response) {

                categoryList = response.body();
                if (categoryList.size() > 4) {
                    discoverBtn.setVisibility(View.VISIBLE);

                } else {
                    discoverBtn.setVisibility(View.INVISIBLE);
                }
                for (int i = 0; i < 4; i++) {
                    newCategoryList.add(categoryList.get(i));
                }

                recyclerViewInit(newCategoryList);
                Log.d("categoryResponse:", " " + categoryList.size());

            }

            @Override
            public void onFailure(Call<ArrayList<CategoryResponseModel>> call, Throwable t) {
                Log.d("categoryResponse:", " " + "error");

            }
        });


    }

    private void recyclerViewInit(ArrayList<CategoryResponseModel> categoryListFromAPI) {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainViewActivity.this));
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(getParent(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(new RecyclerViewAdapterMainView(categoryListFromAPI, this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    public void clickButtonView(View view) {
        TextView coursesNav = (TextView) findViewById(R.id.coursesNav);
        coursesNav.setBackgroundColor(getResources().getColor(R.color.blueButton));
        drawer.closeDrawer(Gravity.RIGHT);
        Intent intent = new Intent(MainViewActivity.this, MainViewActivity.class);
        startActivity(intent);


        Log.d("OK Button", "pressed");
    }

    public void clickAccountBtn(View view) {
        drawer.closeDrawer(Gravity.RIGHT);
        Intent intent = new Intent(MainViewActivity.this, AccountActivity.class);
        startActivity(intent);


    }


    public void clickCloseMenuBtn(View view) {
        drawer.closeDrawer(Gravity.RIGHT);

    }

    private void initVariables() {


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.bringToFront();
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        btnMenu = (ImageButton) findViewById(R.id.btnMenu);
        discoverBtn = (Button) findViewById(R.id.discoverBtn);
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
                int maxSize = newCategoryList.size();

                try {
                    for (int i = maxSize; i < maxSize + 4; i++) {
                        newCategoryList.add(categoryList.get(i));
                        recyclerViewInit(newCategoryList);
                    }
                } catch (Exception e) {
                    discoverBtn.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    @Override
    public void onCategoryClick(CategoryResponseModel category) {
        startActivity(new Intent(MainViewActivity.this, CoursesActivity.class));
    }


}
