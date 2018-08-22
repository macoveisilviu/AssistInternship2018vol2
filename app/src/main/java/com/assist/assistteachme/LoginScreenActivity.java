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
import android.widget.TextView;
import android.widget.Toast;

import com.assist.assistteachme.Models.Post;
import com.assist.assistteachme.Network.RestClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginScreenActivity extends AppCompatActivity {

    Button loginButon;
    Button createAccount;
    EditText emailEditText;
    EditText passwordEditText;
    TextView resetTextView;
    Context context;


    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        loginButon = findViewById(R.id.loginButton);
        createAccount = findViewById(R.id.createAccountButton);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        resetTextView = findViewById(R.id.resetTextView);

        loginButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (fieldsAreValid()) {
                    startActivity(new Intent(LoginScreenActivity.this, DrawerTestActivity.class));
                }

            }
        });

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginScreenActivity.this, RegisterActivity.class));

            }
        });

        resetTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginScreenActivity.this, ForgotPasswordActivity.class));

            }
        });

    }

    public boolean isValidEmail(String emailt) {
        if (emailt == null)
            return false;

        return android.util.Patterns.EMAIL_ADDRESS.matcher(emailt).matches();
    }

    public boolean fieldsAreValid() {
        if (TextUtils.isEmpty(emailEditText.getText().toString())) {
            emailEditText.setError("empty field");
            return false;
        }
        if (!isValidEmail(emailEditText.getText().toString())) {
            emailEditText.setError("invalid email");
            return false;
        }
        if (TextUtils.isEmpty(passwordEditText.getText().toString())) {
            passwordEditText.setError("empty field");
            return false;
        }
        return true;
    }

    public void showPosts() {
        RestClient.networkHandler().getPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    // display error message
                    return;
                }

                // do something with the result
                Log.w(TAG, "number of posts is " + response.body().size());
                String result = "";
                for (Post post : response.body()) {
                    result = result + "\nUser id:" + post.getUserId() + "\nID:" + post.getId() + " \nTitle:" + post.getTitle() + "\n" + post.getBody() + "\n\n";
                }
                //postText.setText(result);
                Toast.makeText(context, result ,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                // show message couldn't connect to server
            }
        });
    }


}