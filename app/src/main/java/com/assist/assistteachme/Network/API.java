package com.assist.assistteachme.Network;


import com.assist.assistteachme.Models.CategoriesRecive;
import com.assist.assistteachme.Models.CoursesRecive;
import com.assist.assistteachme.Models.LogInReceive;
import com.assist.assistteachme.Models.LogInSend;

import com.assist.assistteachme.ForgotPasswordActivity;
import com.assist.assistteachme.Models.ForgotPasswordRecive;
import com.assist.assistteachme.Models.ForgotPasswordSend;

import com.assist.assistteachme.Models.Post;
import com.assist.assistteachme.Models.RegisterRecive;
import com.assist.assistteachme.Models.RegisterSend;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API {

    /*@GET("/posts")
    Call<List<Post>> getPosts();*/

    @POST("api/login")
    Call<LogInReceive> getLogInReceive(@Body LogInSend logInSend);


   @POST("api/register")
    Call<RegisterRecive> getRegisterUser(@Body RegisterSend registerSend);

   @POST("api/users/forgotPassword")
    Call<String> getPasswordChange(@Body ForgotPasswordSend forgotPasswordSend);

   @GET("api/categories")
    Call<ArrayList<CategoriesRecive>> getCategories();

   @GET("api/categories/courses")
    Call<ArrayList<CoursesRecive>> getCourses();
}
