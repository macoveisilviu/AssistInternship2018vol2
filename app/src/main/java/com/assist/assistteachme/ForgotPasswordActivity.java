package com.assist.assistteachme;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                     Toast.makeText(context,"Valid", Toast.LENGTH_LONG).show();
                 }

             }
         });
    }
    public boolean isValidEmail(String emailt) {
        if (emailt == null)
            return false;

        return android.util.Patterns.EMAIL_ADDRESS.matcher(emailt).matches();
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
        if(TextUtils.isEmpty(passwordEditText.getText().toString())){
            passwordEditText.setError("empty field");
            return false;
        }
        if(TextUtils.isEmpty(confirmedPasswordEditText.getText().toString())){
            confirmedPasswordEditText.setError("empty field");
            return false;
        }
        if(!confirmedPasswordEditText.getText().toString().equals(passwordEditText.getText().toString())){
            passwordEditText.setError("passwords don't match");
            confirmedPasswordEditText.setError("passwords don't match");
            return false;
        }
        return true;
    }
}

