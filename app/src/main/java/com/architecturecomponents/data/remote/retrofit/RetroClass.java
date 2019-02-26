package com.architecturecomponents.data.remote.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClass {

    private static Retrofit getRetrofitInstance(String url) {
        Gson gson = new GsonBuilder().create();
        return new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static APIService getAPIService(String url) {
        return getRetrofitInstance(url).create(APIService.class);
    }

}
