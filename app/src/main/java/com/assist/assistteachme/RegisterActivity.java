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
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private CheckBox checkbox;
    private Button registerbutton;
    private boolean isCheckBoxChecked = false;
    private Context context;
    EditText firstName;
    EditText lastName;
    EditText emaiL;
    EditText passworD;

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

        if (TextUtils.isEmpty(lastName.getText().toString())) {
            lastName.setError("empty field");
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

        if (passworD.length() < 8 || passworD.length() > 100) {
            passworD.setError("MINIMUM 8 characters and MAXIMUM 100 characters!");
            return false;
        }



        return true;
    }


    }



