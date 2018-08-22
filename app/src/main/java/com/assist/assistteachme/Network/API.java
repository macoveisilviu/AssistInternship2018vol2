package com.assist.assistteachme.Network;

import com.assist.assistteachme.Models.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {

    @GET("/posts")
    Call<List<Post>> getPosts();


}
