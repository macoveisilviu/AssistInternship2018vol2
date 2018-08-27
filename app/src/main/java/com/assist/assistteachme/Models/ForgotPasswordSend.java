package com.assist.assistteachme.Models;

public class ForgotPasswordSend {
    private String email;

    public ForgotPasswordSend() {
    }

    public ForgotPasswordSend(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
