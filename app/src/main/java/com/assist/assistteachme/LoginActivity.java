package com.assist.assistteachme;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    public Button login_btn, signUp_btn;
    public TextView reset_text;
    public EditText password, email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //editText.getText().clear();

        login_btn = (Button) findViewById(R.id.buttonLog);
        signUp_btn = (Button) findViewById(R.id.signUp_btn);
        reset_text = (TextView) findViewById(R.id.resetTxt);

        buttonsClick();


    }// onCreate

    private void buttonsClick() {

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, MainViewActivity.class));
                /*if (validateLogin()) {
                    startActivity(new Intent(LoginActivity.this, MainViewActivity.class));
                } else {
                    // Toast.makeText(getApplicationContext(), "Invalid email address or password!", Toast.LENGTH_SHORT).show();
                }*/

            }
        });


        reset_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ResetPassActivity.class));
            }
        });


        signUp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });
    }

    private boolean validateLogin() {
        final EditText email = (EditText) findViewById(R.id.logEmail);
        final TextInputEditText password = (TextInputEditText) findViewById(R.id.logPasss);


        Pattern specialCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Pattern UpperCasePatten = Pattern.compile("[A-Z ]");
        Pattern lowerCasePatten = Pattern.compile("[a-z ]");
        Pattern digitCasePatten = Pattern.compile("[0-9 ]");
        final String emailValidation = email.getText().toString().trim();
        final String passValidation = password.getText().toString().trim();

        if (TextUtils.isEmpty(emailValidation) && TextUtils.isEmpty(passValidation)) {

            /*email.setBackgroundResource(R.drawable.shape_btn_white);
            password.setBackgroundResource(R.drawable.shape_btn_white);
*/
            email.setError("Enter email!");
            password.setError("Enter password!");
            return false;
        }
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(emailValidation).matches()
                ) {
        } else {
            email.setError("Email is not valid");
            return false;
        }

        if (TextUtils.isEmpty(emailValidation)) {
            email.setError("Enter email address!");
            return false;
        }
        if (TextUtils.isEmpty(passValidation)) {
            password.setError("Enter password!");
            return false;
        }

        if (passValidation.length() < 8) {
            password.setError("Password should contain at least 8 characters!");
            return false;
        }
        if (!specialCharPatten.matcher(passValidation).find()) {
            password.setError("Password must have at least one special character!");
            return false;
        }
        if (!UpperCasePatten.matcher(passValidation).find()) {
            password.setError("Password must have at least one uppercase character!");
            return false;
        }
        if (!lowerCasePatten.matcher(passValidation).find()) {
            password.setError("Password must have at least one lowercase character!");
            return false;
        }
        if (!digitCasePatten.matcher(passValidation).find()) {
            password.setError("Password must have at least one digit!");
            return false;
        }

        return true;
    }
}


