package com.assist.assistteachme.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class RegisterRecive {

    @SerializedName("admin")
    @Expose
    private boolean admin;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("forgotPassword")
    @Expose
    private String forgotPassword;
    @SerializedName("updateAt")
    @Expose
    private Date updatedAt;
    @SerializedName("createdAt")
    @Expose
    private Date createdAt;

    public RegisterRecive(boolean admin, int id, String firstName, String lastName, String email, String password, String forgotPassword, Date updatedAt, Date createdAt) {
        this.admin = admin;
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.forgotPassword = forgotPassword;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    public boolean isAdmin() {
        return admin;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getForgotPassword() {
        return forgotPassword;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
