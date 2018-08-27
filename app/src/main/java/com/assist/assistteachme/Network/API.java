package com.assist.assistteachme.Network;

import com.assist.assistteachme.Models.LogInReceive;
import com.assist.assistteachme.Models.LogInSend;
import com.assist.assistteachme.Models.Post;

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


}
