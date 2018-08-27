package com.assist.assistteachme.SignUpActivityDoc;

/**
 * Created by anairda on 8/23/2018.
 */

public class SignUpUserModel {
    String Mail;
    String Password;
    String FirstName;
    String LastName;

    public SignUpUserModel(String mail, String password, String firstName, String lastName) {
        Mail = mail;
        Password = password;
        FirstName = firstName;
        LastName = lastName;
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

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }
}
