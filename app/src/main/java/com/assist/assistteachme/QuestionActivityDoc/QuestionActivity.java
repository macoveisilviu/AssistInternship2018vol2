package com.assist.assistteachme.QuestionActivityDoc;

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

import com.assist.assistteachme.MainViewDoc.MainViewActivity;
import com.assist.assistteachme.MyAccountDoc.AccountActivity;
import com.assist.assistteachme.R;

import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity implements RecyclerViewAdapterQuestion.OnItemClickListener {
    DrawerLayout drawer;
    ImageButton btnMenu;
    RecyclerView recyclerView;
    ArrayList<QuestionModel> list = new ArrayList<QuestionModel>();
    Button nextChapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        initVariables();
        buttonsOnClick();
        populateDummyData();
        recyclerViewInit();
        buttonsInit();


    }//onCReate

    public void clickButtonView(View view) {
        TextView coursesNav = (TextView) findViewById(R.id.coursesNav);
        coursesNav.setBackgroundColor(getResources().getColor(R.color.blueButton));
        drawer.closeDrawer(Gravity.RIGHT);
        Intent intent = new Intent(QuestionActivity.this, MainViewActivity.class);
        startActivity(intent);


        Log.d("OK Button", "pressed");
    }

    public void clickAccountBtn(View view) {
        drawer.closeDrawer(Gravity.RIGHT);
        Intent intent = new Intent(QuestionActivity.this, AccountActivity.class);
        startActivity(intent);


    }

    private void buttonsOnClick() {
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.RIGHT);
            }

        });
    }


    public void clickCloseMenuBtn(View view) {
        drawer.closeDrawer(Gravity.RIGHT);

    }

    private void initVariables() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.bringToFront();
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        btnMenu = (ImageButton) findViewById(R.id.btnMenu);

    }

    private void buttonsInit() {
        nextChapter = (Button) findViewById(R.id.nextBtn);
        nextChapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(QuestionActivity.this, QuestionFinishActivity.class));


            }
        });
    }

    private void recyclerViewInit() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(QuestionActivity.this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new RecyclerViewAdapterQuestion(list, this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void populateDummyData() {

        QuestionModel one = new QuestionModel("Where Can You Find Unique Myspace Layouts Nowadays?",
                "From Wetlands To Canals And Dams Amsterdam Is Alive",
                "From Wetlands To Canals And Dams Amsterdam Is Alive",
                "From Wetlands To Canals And Dams Amsterdam Is Alive");
        QuestionModel two = new QuestionModel("Where Can You Find Unique Myspace Layouts Nowadays?",
                "From Wetlands To Canals And Dams Amsterdam Is Alive",
                "From Wetlands To Canals And Dams Amsterdam Is Alive",
                "From Wetlands To Canals And Dams Amsterdam Is Alive");
        QuestionModel three = new QuestionModel("Where Can You Find Unique Myspace Layouts Nowadays?",
                "From Wetlands To Canals And Dams Amsterdam Is Alive",
                "From Wetlands To Canals And Dams Amsterdam Is Alive",
                "From Wetlands To Canals And Dams Amsterdam Is Alive");

        list.add(one);
        list.add(two);
        list.add(three);
    }

    @Override
    public void onQuestionrClick(QuestionModel question) {

    }
}
