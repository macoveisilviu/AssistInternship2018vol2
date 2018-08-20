package com.assist.assistteachme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginScreenActivity extends AppCompatActivity {

    Button loginButon ;
    Button createAccount;
    EditText emailEditText ;
    EditText passwordEditText ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        loginButon= findViewById(R.id.loginButton);
        createAccount= findViewById(R.id.createAccountButton);
        emailEditText =(EditText) findViewById(R.id.emailEditText);
        passwordEditText =(EditText) findViewById(R.id.passwordEditText);

    loginButon.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if(fieldsAreValid()) {
                startActivity(new Intent(LoginScreenActivity.this, MainViewActivity.class));
            }

        }
    });

    createAccount.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(LoginScreenActivity.this, RegisterActivity.class));

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
        if(!isValidEmail(emailEditText.getText().toString())){
            emailEditText.setError("invalid email");
            return false;
        }
        if(TextUtils.isEmpty(passwordEditText.getText().toString())){
            passwordEditText.setError("empty field");
            return false;
        }
        return true;
    }
}