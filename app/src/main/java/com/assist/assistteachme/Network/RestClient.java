package com.assist.assistteachme.Network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by anairda on 8/20/2018.
 */

 public class RestClient {
    private static API REST_CLIENT;

    private static String BASE_URL = "http://192.168.151.36:8000/api/";

    public static API networkHandler() {
        return REST_CLIENT;
    }

    static {
        setupRestClient();
    }

    private static void setupRestClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.networkInterceptors().add(httpLoggingInterceptor);
        OkHttpClient okHttpClient = builder.build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        REST_CLIENT = retrofit.create(API.class);
    }
}
