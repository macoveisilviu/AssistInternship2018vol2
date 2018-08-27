package com.assist.assistteachme.ResetPassActivityDoc;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.assist.assistteachme.MainViewDoc.MainViewActivity;
import com.assist.assistteachme.Network.RestClient;
import com.assist.assistteachme.R;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by anairda on 8/14/2018.
 */

public class ResetPassActivity extends AppCompatActivity {
    public Button resetPass_btn;
    Pattern specialCharPatten, UpperCasePatten, lowerCasePatten, digitCasePatten;
    String emailValidation, passValidation, passConfValidation;
    TextInputEditText password, passwordConfirm;
    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_pass);

        initVariables();
        resetPass_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validateResetPass()) {
                    resetPass(emailValidation);

                }

            }
        });
    }

    private void resetPass(String emailValidation) {
        ResetUserModel userModelReset = new ResetUserModel(emailValidation);

        RestClient.networkHandler().resetPass(userModelReset).enqueue(new Callback<ResetResponseModel>() {
            @Override
            public void onResponse(Call<ResetResponseModel> call, Response<ResetResponseModel> response) {
                Log.d("RESET RESPONSE", response.message());


                if (response.isSuccessful() == true) {
                    startActivity(new Intent(ResetPassActivity.this, MainViewActivity.class));
                    Toast.makeText(ResetPassActivity.this, "Check your email inbox to reset the password!", Toast.LENGTH_SHORT ).show();
                } else {
                    Toast.makeText(ResetPassActivity.this, "Email not found!", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<ResetResponseModel> call, Throwable t) {
                Toast.makeText(ResetPassActivity.this, "Check internet connection!", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void initVariables() {
        resetPass_btn = (Button) findViewById(R.id.buttonReset);
        email = (EditText) findViewById(R.id.resetEmail);
        password = (TextInputEditText) findViewById(R.id.resetPass1);
        passwordConfirm = (TextInputEditText) findViewById(R.id.resetPassConf1);


        specialCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        UpperCasePatten = Pattern.compile("[A-Z ]");
        lowerCasePatten = Pattern.compile("[a-z ]");
        digitCasePatten = Pattern.compile("[0-9 ]");


    }

    private boolean validateResetPass() {

        emailValidation = email.getText().toString().trim();
        passValidation = password.getText().toString().trim();
        passConfValidation = passwordConfirm.getText().toString().trim();

        if (TextUtils.isEmpty(emailValidation))
                /*&& TextUtils.isEmpty(passValidation)
                && TextUtils.isEmpty(passConfValidation))*/ {
           /* passwordConfirm.setError("Confirm email!");*/
            email.setError("Enter email!");
          /*  password.setError("Enter password!");*/
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
      /*  if (TextUtils.isEmpty(passValidation)) {
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
        if (TextUtils.isEmpty(passConfValidation)) {
            passwordConfirm.setError("Confirm password!");
            return false;
        }


        if (!passConfValidation.equals(passValidation)) {
            passwordConfirm.setError("Password doesn't match!");
            return false;
        }*/
        return true;
    }
}
