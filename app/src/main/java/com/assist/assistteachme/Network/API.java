package com.assist.assistteachme.Network;

import com.assist.assistteachme.ForgotPasswordActivity;
import com.assist.assistteachme.Models.ForgotPasswordRecive;
import com.assist.assistteachme.Models.ForgotPasswordSend;
import com.assist.assistteachme.Models.Post;
import com.assist.assistteachme.Models.RegisterRecive;
import com.assist.assistteachme.Models.RegisterSend;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API {

    /*@GET("/posts")
    Call<List<Post>> getPosts();*/

   @POST("api/register")
    Call<RegisterRecive> getRegisterUser(@Body RegisterSend registerSend);

   @POST("api/users/forgotPassword")
    Call<ForgotPasswordRecive> getPasswordChange(@Body ForgotPasswordSend forgotPasswordSend);

}
