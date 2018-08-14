package com.assist.assistteachme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

/**
 * Created by anairda on 8/14/2018.
 */

public class ResetPassActivity extends AppCompatActivity {
    public Button resetPass_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_pass);


        resetPass_btn = (Button) findViewById(R.id.buttonReset);

    }
}
