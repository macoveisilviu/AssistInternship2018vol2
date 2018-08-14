package com.assist.assistteachme;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignupActivity extends AppCompatActivity {
    public Button signUp_btn;
   public EditText firstNamee, lastNamee, email;
   public TextInputEditText password;
   public String emailValidation, passValidation, firstName, lastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);



        final EditText firstNamee = (EditText) findViewById(R.id.firstName);
        final EditText lastNamee = (EditText) findViewById(R.id.lastName);
        final EditText email = (EditText) findViewById(R.id.emailAddress);
        final TextInputEditText password=(TextInputEditText) findViewById(R.id.etPassworddd);
        final String emailValidation = email.getText().toString().trim();
        final String passValidation = password.getText().toString().trim();
        final String firstName = firstNamee.getText().toString().trim();
        final String lastName = lastNamee.getText().toString().trim();
        signUp_btn = (Button) findViewById(R.id.createAccountBtn);

        signUp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validateSignUp()) {
               // startActivity(new Intent(SignupActivity.this,LoginActivity.class));
                } else {
                    //Toast.makeText(getApplicationContext(), "Invalid email address or password!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private boolean validateSignUp() {

        if (TextUtils.isEmpty(emailValidation) && TextUtils.isEmpty(passValidation) &&TextUtils.isEmpty(firstName)  && TextUtils.isEmpty(lastName) ) {

            /*email.setBackgroundResource(R.drawable.shape_error);
            password.setBackgroundResource(R.drawable.shape_error);
*/
           /* firstNamee.setError("Enter first name!");
                    lastNamee.setError("Enter last name!");*/
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

        if (passValidation.length() < 8 /*&& !UpperCasePatten.matcher(passValidation).find() && !specialCharPatten.matcher(passValidation).find()
                && !lowerCasePatten.matcher(passValidation).find() && !digitCasePatten.matcher(passValidation).find()*/) {
            password.setError("Enter valid password!");
            return false;
        }

        if (!TextUtils.isEmpty(emailValidation) && !TextUtils.isEmpty(passValidation) && android.util.Patterns.EMAIL_ADDRESS.matcher(emailValidation).matches()
               /* && passValidation.length() >= 8 && UpperCasePatten.matcher(passValidation).find() && specialCharPatten.matcher(passValidation).find()
                && lowerCasePatten.matcher(passValidation).find() && digitCasePatten.matcher(passValidation).find()*/) {


            return true;
        }


        return true;


    }
}
