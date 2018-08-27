package com.assist.assistteachme.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForgotPasswordRecive {
    @SerializedName("message")
    @Expose
    private String message;

    public ForgotPasswordRecive(String message) {
        this.message = message;
    }

    public ForgotPasswordRecive() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
