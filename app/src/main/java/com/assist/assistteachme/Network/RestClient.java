package com.assist.assistteachme.Network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by silviu on 28.10.2016.
 */

public class RestClient {
    private static API REST_CLIENT;

    private static String BASE_URL = "https://jsonplaceholder.typicode.com";

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
