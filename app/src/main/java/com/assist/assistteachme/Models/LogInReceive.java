package com.assist.assistteachme.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LogInReceive {
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("token")
    @Expose
    public String token;

    public LogInReceive() {
    }

    public LogInReceive(String message, String token) {
        this.message = message;
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
