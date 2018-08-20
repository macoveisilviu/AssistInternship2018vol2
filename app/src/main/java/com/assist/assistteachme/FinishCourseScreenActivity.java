package com.assist.assistteachme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FinishCourseScreenActivity extends AppCompatActivity {

    Button gotocourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_course_screen);
        gotocourses= findViewById(R.id.goto_button);


        gotocourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FinishCourseScreenActivity.this, MainViewActivity.class));

            }
        });
    }

}





