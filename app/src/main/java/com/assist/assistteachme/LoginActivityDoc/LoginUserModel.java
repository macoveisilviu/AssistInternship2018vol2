package com.assist.assistteachme.LoginActivityDoc;

/**
 * Created by anairda on 8/22/2018.
 */

public class LoginUserModel {

    private String Mail;

    private String Password;

    public LoginUserModel(String mail, String password) {
        Mail = mail;
        Password = password;
    }


    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }






    }



