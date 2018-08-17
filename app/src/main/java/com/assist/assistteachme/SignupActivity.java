package com.assist.assistteachme;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {
    public Button signUp_btn;
    public EditText firstNamee, lastNamee, email;
    public TextInputEditText password;
    public String emailValidation, passValidation, firstName, lastName;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);


        signUp_btn = (Button) findViewById(R.id.createAccountBtn);

        signUp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validateSignUp()) {
                    startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                    Toast.makeText(getApplicationContext(), "Your account was created!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    //  Toast.makeText(getApplicationContext(), "Invalid email address or password!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }// onCreate

    private boolean validateSignUp() {
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        firstNamee = (EditText) findViewById(R.id.firstNameS);
        lastNamee = (EditText) findViewById(R.id.lastNameS);
        email = (EditText) findViewById(R.id.emailAddressS);
        password = (TextInputEditText) findViewById(R.id.etPasswordddS);
        emailValidation = email.getText().toString().trim();
        passValidation = password.getText().toString().trim();
        firstName = firstNamee.getText().toString().trim();
        lastName = lastNamee.getText().toString().trim();
        signUp_btn = (Button) findViewById(R.id.createAccountBtn);
        Pattern specialCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Pattern UpperCasePatten = Pattern.compile("[A-Z ]");
        Pattern lowerCasePatten = Pattern.compile("[a-z ]");
        Pattern digitCasePatten = Pattern.compile("[0-9 ]");
        if (TextUtils.isEmpty(emailValidation)
                && TextUtils.isEmpty(passValidation)
                && TextUtils.isEmpty(firstName)
                && TextUtils.isEmpty(lastName)) {
        /*email.setBackgroundResource(R.drawable.shape_btn_white);
            password.setBackgroundResource(R.drawable.shape_btn_white);*/


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

        if (TextUtils.isEmpty(firstName)) {
            firstNamee.setError("Enter first name!");
            return false;
        }
        if (TextUtils.isEmpty(lastName)) {
            lastNamee.setError("Enter last name!");
            return false;
        }
        if (digitCasePatten.matcher(firstName).find()) {
            firstNamee.setError("First name should contain only characters!");
            return false;
        }

        if (digitCasePatten.matcher(lastName).find()) {
            lastNamee.setError("Last name should contain only characters!");
            return false;
        }

        if (specialCharPatten.matcher(firstName).find()) {
            firstNamee.setError("First name should contain only characters!");
            return false;
        }

        if (specialCharPatten.matcher(lastName).find()) {
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
