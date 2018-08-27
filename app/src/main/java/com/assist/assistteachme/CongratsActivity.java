package com.assist.assistteachme;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.assist.assistteachme.MainViewDoc.MainViewActivity;
import com.assist.assistteachme.MyAccountDoc.AccountActivity;

public class CongratsActivity extends AppCompatActivity {
    Button coursesBtn;
    DrawerLayout drawer;
    ImageButton btnMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congrats);

        initVariables();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.bringToFront();
        buttonsOnClick();


    }//onCreate

    private void buttonsOnClick() {
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.RIGHT);
            }

        });
        coursesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CongratsActivity.this, MainViewActivity.class));

            }
        });

    }

    private void initVariables() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        btnMenu = (ImageButton) findViewById(R.id.btnMenu);
        coursesBtn = (Button) findViewById(R.id.discoverBtn);
    }

    public void clickButtonView(View view) {
        TextView coursesNav = (TextView) findViewById(R.id.coursesNav);
        coursesNav.setBackgroundColor(getResources().getColor(R.color.blueButton));
        drawer.closeDrawer(Gravity.RIGHT);
        Intent intent = new Intent(CongratsActivity.this, MainViewActivity.class);
        startActivity(intent);


        Log.d("OK Button", "pressed");
    }

    public void clickAccountBtn(View view) {
        drawer.closeDrawer(Gravity.RIGHT);
        Intent intent = new Intent(CongratsActivity.this, AccountActivity.class);
        startActivity(intent);


    }


    public void clickCloseMenuBtn(View view) {
        drawer.closeDrawer(Gravity.RIGHT);

    }

}
