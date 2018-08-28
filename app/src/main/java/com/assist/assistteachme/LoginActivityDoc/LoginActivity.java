package com.assist.assistteachme.LoginActivityDoc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.assist.assistteachme.MainViewDoc.MainViewActivity;
import com.assist.assistteachme.Network.RestClient;
import com.assist.assistteachme.R;
import com.assist.assistteachme.ResetPassActivityDoc.ResetPassActivity;
import com.assist.assistteachme.SignUpActivityDoc.SignupActivity;
import com.assist.assistteachme.User;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    public Button login_btn, signUp_btn;
    public TextView reset_text;
    public EditText email;
    TextInputEditText password;
    String emailValidation, passValidation;
    Pattern specialCharPatten, UpperCasePatten, lowerCasePatten, digitCasePatten;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        autoLogin();
        initVariables();
        buttonsClick();



    }// onCreate

    private void autoLogin() {
        SharedPreferences prefs = this.getSharedPreferences("MY_PREF_NAME", Context.MODE_PRIVATE);

        String loginID = prefs.getString("LOGIN_EMAIL", "");
        String loginPWD = prefs.getString("LOGIN_PWD", "");
        if (loginID.length() > 0 && loginPWD.length() > 0) {
            login(loginID, loginPWD);
        } else {
            Log.d("OK", "Preference not saved");
        }
    }


    private void login(String emailValidation, String passValidation) {

        LoginUserModel userModel = new LoginUserModel(emailValidation, passValidation);

        RestClient.networkHandler().login(userModel).enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                Log.d("LOGIN RESPONSE", response.message());


                if (response.isSuccessful() == true) {

                    User.getInstance().setLoginResponseModel(response.body());

                    sharedPreferences();
                    startActivity(new Intent(LoginActivity.this, MainViewActivity.class));
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid email or password!", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Check internet connection!", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void sharedPreferences() {
        SharedPreferences prefs = this.getSharedPreferences("MY_PREF_NAME", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("LOGIN_EMAIL", emailValidation);
        editor.putString("LOGIN_PWD", passValidation);
        editor.commit();

    }

    private void initVariables() {
        login_btn = (Button) findViewById(R.id.buttonLog);
        signUp_btn = (Button) findViewById(R.id.signUp_btn);
        reset_text = (TextView) findViewById(R.id.resetTxt);
        email = (EditText) findViewById(R.id.logEmail);
        password = (TextInputEditText) findViewById(R.id.logPasss);


        specialCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        UpperCasePatten = Pattern.compile("[A-Z ]");
        lowerCasePatten = Pattern.compile("[a-z ]");
        digitCasePatten = Pattern.compile("[0-9 ]");

    }

    private void buttonsClick() {

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (validateLogin()) {
                    login(emailValidation, passValidation);
                }

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
        emailValidation = email.getText().toString().trim();
        passValidation = password.getText().toString().trim();

        if (TextUtils.isEmpty(emailValidation) && TextUtils.isEmpty(passValidation)) {

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
        if (passValidation.length() > 20) {
            password.setError("Password should contain maximum 20 characters!");
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


