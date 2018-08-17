package com.assist.assistteachme;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

/**
 * Created by anairda on 8/14/2018.
 */

public class ResetPassActivity extends AppCompatActivity {
    public Button resetPass_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_pass);

        resetPass_btn = (Button) findViewById(R.id.buttonReset);
resetPass_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validateResetPass()) {
                    startActivity(new Intent(ResetPassActivity.this, LoginActivity.class));
                    Toast.makeText(getApplicationContext(), "Your password was successfully reseted!", Toast.LENGTH_SHORT).show();
                } else {
                    // Toast.makeText(getApplicationContext(), "Invalid email address or password!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private boolean validateResetPass() {
        final EditText email = (EditText) findViewById(R.id.resetEmail);
        final TextInputEditText password = (TextInputEditText) findViewById(R.id.resetPass1);
        final TextInputEditText passwordConfirm = (TextInputEditText) findViewById(R.id.resetPassConf1);


        Pattern specialCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Pattern UpperCasePatten = Pattern.compile("[A-Z ]");
        Pattern lowerCasePatten = Pattern.compile("[a-z ]");
        Pattern digitCasePatten = Pattern.compile("[0-9 ]");
        final String emailValidation = email.getText().toString().trim();
        final String passValidation = password.getText().toString().trim();
        final String passConfValidation = passwordConfirm.getText().toString().trim();

        if (TextUtils.isEmpty(emailValidation)
                && TextUtils.isEmpty(passValidation)
                && TextUtils.isEmpty(passConfValidation)) {

            /*email.setBackgroundResource(R.drawable.shape_btn_white);
            password.setBackgroundResource(R.drawable.shape_btn_white);
*/passwordConfirm.setError("Confirm email!");
            email.setError("Enter email!");
            password.setError("Enter password!");
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
        if (TextUtils.isEmpty(passValidation)) {
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


        if(!passConfValidation.equals(passValidation))
        {passwordConfirm.setError("Password doesn't match!");
            return false;}
        return true;
    }
}
