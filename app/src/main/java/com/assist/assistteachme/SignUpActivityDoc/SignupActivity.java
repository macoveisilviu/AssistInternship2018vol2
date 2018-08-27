package com.assist.assistteachme.SignUpActivityDoc;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.assist.assistteachme.LoginActivityDoc.LoginActivity;
import com.assist.assistteachme.Network.RestClient;
import com.assist.assistteachme.R;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {
    public Button signUp_btn;
    public EditText firstNamee, lastNamee, email;
    public TextInputEditText password;
    public String emailValidation, passValidation, firstNameValidation, lastNameValidation;
    CheckBox checkBox;
    Pattern specialCharPatten, UpperCasePatten, lowerCasePatten, digitCasePatten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        initVariables();
        signUpBtnOnClick();


    }// onCreate

    private void signUpBtnOnClick() {
        signUp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validateSignUp()) {

                    signUp(emailValidation, passValidation, firstNameValidation, lastNameValidation);
                }

            }
        });
    }

    private void signUp(String emailValidation, String passValidation, String firstNameValidation, String lastNameValidation) {
        SignUpUserModel userModelSignUp = new SignUpUserModel(emailValidation, passValidation, firstNameValidation, lastNameValidation);

        RestClient.networkHandler().signUp(userModelSignUp).enqueue(new Callback<SignUpResponseModel>() {
            @Override
            public void onResponse(Call<SignUpResponseModel> call, Response<SignUpResponseModel> response) {
                Log.d("LOGIN RESPONSE", response.message());

                if (response.code() == 201) {
                    startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                    Toast.makeText(SignupActivity.this, "Account was created! Please login!", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<SignUpResponseModel> call, Throwable t) {
                Toast.makeText(SignupActivity.this, "Check internet connection!", Toast.LENGTH_SHORT).show();

            }


        });
    }

    private void initVariables() {
        signUp_btn = (Button) findViewById(R.id.createAccountBtn);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        firstNamee = (EditText) findViewById(R.id.firstNameS);
        lastNamee = (EditText) findViewById(R.id.nameAccount);
        email = (EditText) findViewById(R.id.emailAccount);
        password = (TextInputEditText) findViewById(R.id.etPasswordddS);
        signUp_btn = (Button) findViewById(R.id.createAccountBtn);
        specialCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        UpperCasePatten = Pattern.compile("[A-Z ]");
        lowerCasePatten = Pattern.compile("[a-z ]");
        digitCasePatten = Pattern.compile("[0-9 ]");
    }

    private boolean validateSignUp() {
        emailValidation = email.getText().toString().trim();
        passValidation = password.getText().toString().trim();
        firstNameValidation = firstNamee.getText().toString().trim();
        lastNameValidation = lastNamee.getText().toString().trim();

        if (TextUtils.isEmpty(emailValidation)
                && TextUtils.isEmpty(passValidation)
                && TextUtils.isEmpty(firstNameValidation)
                && TextUtils.isEmpty(lastNameValidation)) {

            email.setError("Enter email!");
            password.setError("Enter password!");
            firstNamee.setError("Enter first name!");
            lastNamee.setError("Enter last name!");


            return false;
        }
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(emailValidation).matches()) {
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

        if (TextUtils.isEmpty(firstNameValidation)) {
            firstNamee.setError("Enter first name!");
            return false;
        }
        if (TextUtils.isEmpty(lastNameValidation)) {
            lastNamee.setError("Enter last name!");
            return false;
        }
        if (digitCasePatten.matcher(firstNameValidation).find()) {
            firstNamee.setError("First name should contain only characters!");
            return false;
        }

        if (digitCasePatten.matcher(lastNameValidation).find()) {
            lastNamee.setError("Last name should contain only characters!");
            return false;
        }

        if (specialCharPatten.matcher(firstNameValidation).find()) {
            firstNamee.setError("First name should contain only characters!");
            return false;
        }

        if (specialCharPatten.matcher(lastNameValidation).find()) {
            lastNamee.setError("Last name should contain only characters!");
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

        if (!checkBox.isChecked()) {
            Toast.makeText(getApplicationContext(), "You have to agree with Terms & Conditions!",
                    Toast.LENGTH_SHORT).show();
            return false;
        }


        return true;


    }
}
