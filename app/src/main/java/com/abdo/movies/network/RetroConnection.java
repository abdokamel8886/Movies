package com.abdo.movies.network;

import com.abdo.movies.helpers.Const;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroConnection {

    private static Retrofit retrofit;

    private synchronized static Retrofit getRetrofit(){
        if (retrofit == null){

            retrofit = new Retrofit.Builder().baseUrl(Const.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        }
        return retrofit;
    }
    public static RetroServices getServices(){
        return getRetrofit().create(RetroServices.class);
    }
}