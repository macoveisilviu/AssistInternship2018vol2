package com.assist.assistteachme;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.assist.assistteachme.Models.Post;
import com.assist.assistteachme.Network.RestClient;

import java.util.List;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private CheckBox checkbox;
    private Button registerbutton;
    private boolean isCheckBoxChecked = false;
    private Context context;
    EditText firstName;
    EditText lastName;
    EditText emaiL;
    EditText passworD;
    TextView agreeTextView;

    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        checkbox = findViewById(R.id.EditCheckbox);
        registerbutton = findViewById(R.id.registerActivity_editButton);
        context = getApplicationContext();
        firstName = findViewById(R.id.registerActivity_editFirstName);
        lastName = findViewById(R.id.registerActivity_editLastName);
        emaiL = findViewById(R.id.registerActivity_editEmailAdress);
        passworD = findViewById(R.id.registerActivity_editPassword);
        agreeTextView = findViewById(R.id.agreeTextView);
        ButtonAction();

    }

    public void OnCheckBoxClicked(View v) {
        Log.w("RegisterActivity", "checked!!!!!!");
    }

    public void ButtonAction() {
        registerbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //OnCheckBoxClicked();

                if (FieldsAreValid() && checkbox.isChecked())

                {
                    startActivity(new Intent(RegisterActivity.this, LoginScreenActivity.class));

                } else {
                    Toast.makeText(context, "Invalid", Toast.LENGTH_LONG).show();
                }

            }
        });

    }


    public boolean isValidEmail(String emailt) {
        if (emailt == null)
            return false;
        return Patterns.EMAIL_ADDRESS.matcher(emailt).matches();
    }

    public boolean FieldsAreValid() {


        if (TextUtils.isEmpty(firstName.getText().toString())) {
            firstName.setError("empty field");
            return false;
        }
        if (firstName.getText().toString().contains(" ")) {
            firstName.setError("spaces are not allowed");
            return false;
        }

        if (checkForNumbers(firstName.getText().toString())==false) {
            firstName.setError("field must contain only letters");
            return false;
        }
        if (lastName.getText().toString().contains(" ")) {
            lastName.setError("spaces are not allowed");
            return false;
        }
        if (TextUtils.isEmpty(lastName.getText().toString())) {
            lastName.setError("empty field");
            return false;
        }

        if (checkForNumbers(lastName.getText().toString())==false) {
            lastName.setError("field must contain only letters");
            return false;
        }

        if (TextUtils.isEmpty(emaiL.getText().toString())) {
            emaiL.setError("empty field");
            return false;
        }

        if (!isValidEmail(emaiL.getText().toString())) {

            emaiL.setError("invalid email");
            return false;
        }

        if (TextUtils.isEmpty(passworD.getText().toString())) {
            passworD.setError(("empty field"));
            return false;
        }

        if (passworD.length() < 8) {
            passworD.setError("MINIMUM 8 characters!");
            return false;
        }

        if(checkbox.isChecked()==false){
            agreeTextView.setError("Check!");
            return false;
        }


        return true;
    }

    public boolean checkForNumbers(String a){
        if (Pattern.matches("[a-zA-Z]+", a) == true) {
            return true;
        }
        else
            return false;
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



