package com.assist.assistteachme;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.assist.assistteachme.Models.LogInReceive;
import com.assist.assistteachme.Models.LogInSend;
import com.assist.assistteachme.Network.RestClient;

import java.util.regex.Pattern;

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
    private SharedPreferences mPrefs;
    private static final String PREFS_NAME = "PrefsFile";



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
        mPrefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        getPreferencesDataAndAutologin();


        loginButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (fieldsAreValid()) {
                    LogInAccountToAPI(emailEditText.getText().toString(), passwordEditText.getText().toString(),false);

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


        emailEditText.getText().clear();
        passwordEditText.getText().clear();


    }


    private void getPreferencesDataAndAutologin() {
        String email = "";
        String password = "";

        email= PreferenceManager.getDefaultSharedPreferences(this).getString("pref_email", "");
        password=PreferenceManager.getDefaultSharedPreferences(this).getString("pref_password", "");

        if (!email.isEmpty() && !password.isEmpty()) {
            LogInAccountToAPI(email, password, true);
        }

    }


    public boolean isValidEmail(String emailt) {
        if (emailt == null)
            return false;

        return android.util.Patterns.EMAIL_ADDRESS.matcher(emailt).matches();
    }

    public boolean checkForNumbers(String a) {
        if (Pattern.matches("[a-zA-Z]+", a) == true) {
            return true;
        } else
            return false;
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

        if (!checkPassword(passwordEditText.getText().toString()) && checkForNumbers(passwordEditText.getText().toString())) {
            passwordEditText.setError("Password must contain Upper character and number");
            return false;
        }





        return true;
    }

    public boolean checkPassword(String a) {
        if (Pattern.matches("[A-Z]", a) == true) {
            return true;
        } else
            return false;
    }


    /*public void showPosts() {
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
    }*/


    public void LogInAccountToAPI(final String email, final String password, final Boolean isAutologin) {

        LogInSend logInSend = new LogInSend();
        logInSend.setEmail(email);
        logInSend.setPassword(password);
        RestClient.networkHandler().getLogInReceive(logInSend).enqueue(new Callback<LogInReceive>() {
            @Override
            public void onResponse(Call<LogInReceive> call, Response<LogInReceive> response) {
                int statuscode = response.code();
                if (response.isSuccessful()) {
                    if (!isAutologin) {
                        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("pref_email", email).apply();
                        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("pref_password", password).apply();

                    }
                    startActivity(new Intent(LoginScreenActivity.this, DrawerTestActivity.class));

                }
                else{
                    Toast.makeText(getApplicationContext(),"This account is not exists or password is wrong",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LogInReceive> call, Throwable t) {

            }
        });
    }


}