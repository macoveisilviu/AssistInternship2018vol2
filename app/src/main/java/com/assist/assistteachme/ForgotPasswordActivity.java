package com.assist.assistteachme;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.assist.assistteachme.Models.ForgotPasswordRecive;
import com.assist.assistteachme.Models.ForgotPasswordSend;
import com.assist.assistteachme.Network.RestClient;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity extends AppCompatActivity {

   private EditText emailEditText;
   private EditText passwordEditText;
   private EditText confirmedPasswordEditText;
   private Button ressetButton;
   private Context context;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
         emailEditText = findViewById(R.id.emailEditText);
         passwordEditText = findViewById(R.id.passwordEditText);
         confirmedPasswordEditText = findViewById(R.id.confirmedPasswordEditText);
         ressetButton = findViewById(R.id.ressetButton);
         context = getApplicationContext();

         ressetButton.setOnClickListener(new View.OnClickListener(){

             @Override
             public void onClick(View v) {
                 if(fieldsAreValid()){
                     forgotPasswordToApi();
                 }

             }
         });
    }
    public boolean isValidEmail(String emailt) {
        if (emailt == null)
            return false;

        return android.util.Patterns.EMAIL_ADDRESS.matcher(emailt).matches();
    }
    public boolean checkPassword(String a){
        if (Pattern.matches("[A-Z]+[0-9]", a) == true) {
            return true;
        }
        else
            return false;
    }

    public boolean fieldsAreValid(){
        if(TextUtils.isEmpty(emailEditText.getText().toString())){
            emailEditText.setError("empty field");
            return false;
        }
        if(isValidEmail(emailEditText.getText().toString())==false){
            emailEditText.setError("invalid email");
            return false;
        }
        /*if(TextUtils.isEmpty(passwordEditText.getText().toString())){
            passwordEditText.setError("empty field");
            return false;
        }
        if(checkPassword(passwordEditText.getText().toString())){
            passwordEditText.setError("Password must contain Upper character and number");
            return false;
        }
        if(TextUtils.isEmpty(confirmedPasswordEditText.getText().toString())){
            confirmedPasswordEditText.setError("empty field");
            return false;
        }
        if(checkPassword(confirmedPasswordEditText.getText().toString())){
            confirmedPasswordEditText.setError("Password must contain Upper character and number");
            return false;
        }
        if(!confirmedPasswordEditText.getText().toString().equals(passwordEditText.getText().toString())){
            passwordEditText.setError("passwords don't match");
            confirmedPasswordEditText.setError("passwords don't match");
            return false;

        }
        if (passwordEditText.length() < 8) {
            passwordEditText.setError("MINIMUM 8 characters!");
            return false;
        }
        if (confirmedPasswordEditText.length() < 8) {
            confirmedPasswordEditText.setError("MINIMUM 8 characters!");
            return false;
        }

        }*/

        return true;
    }

    public void forgotPasswordToApi(){
        ForgotPasswordSend forgotPasswordSend = new ForgotPasswordSend();
        forgotPasswordSend.setEmail(emailEditText.getText().toString());
        RestClient.networkHandler().getPasswordChange(forgotPasswordSend).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.w("RESPONSE", response.isSuccessful()+" "+response.code());
                if (response.isSuccessful()) {
                    String message = response.body();
                    startActivity(new Intent(ForgotPasswordActivity.this, LoginScreenActivity.class));
                    Toast.makeText(context,  message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
}

