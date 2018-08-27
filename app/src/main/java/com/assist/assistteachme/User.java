package com.assist.assistteachme;

import com.assist.assistteachme.LoginActivityDoc.LoginResponseModel;

/**
 * Created by anairda on 8/23/2018.
 */

public class User {
    private static User INSTANCE;

    private LoginResponseModel loginResponseModel = new LoginResponseModel();

    public LoginResponseModel getLoginResponseModel() {
        return loginResponseModel;
    }

    public void setLoginResponseModel(LoginResponseModel loginResponseModel) {
        this.loginResponseModel = loginResponseModel;
    }

    private User() {
    }

    public static User getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new User();
        }

        return INSTANCE;
    }


}
