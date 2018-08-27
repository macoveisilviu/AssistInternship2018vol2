package com.assist.assistteachme.Network;

import com.assist.assistteachme.LoginActivityDoc.LoginResponseModel;
import com.assist.assistteachme.LoginActivityDoc.LoginUserModel;
import com.assist.assistteachme.MainViewDoc.CategoryResponseModel;
import com.assist.assistteachme.ResetPassActivityDoc.ResetResponseModel;
import com.assist.assistteachme.ResetPassActivityDoc.ResetUserModel;
import com.assist.assistteachme.SignUpActivityDoc.SignUpResponseModel;
import com.assist.assistteachme.SignUpActivityDoc.SignUpUserModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by anairda on 8/20/2018.
 */
public interface API {

/*    @GET("/login")
   Call<LoginUserModel> get();*/

    @POST("/api/login")
    Call<LoginResponseModel> login(@Body LoginUserModel userModel);

    @POST("/api/register")
    Call<SignUpResponseModel> signUp(@Body SignUpUserModel userModelSignUp);

    @POST("/api/reset")
    Call<ResetResponseModel> resetPass(@Body ResetUserModel userModelReset);

    @GET("/api/user/about")
    Call<SignUpResponseModel> accountInfo(
            @Header("token") String token);

    @GET("/api/categories")
    Call<ArrayList<CategoryResponseModel>> getCategoryApi(
            @Header("token") String token);


}