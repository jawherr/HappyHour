package com.jawherkallell.happyhour;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class ApiClient {

    public static final String BASE_URL = "http://401dafaf.ngrok.io/loginapp/";
    public static Retrofit retrofit = null;

    public static Retrofit getApiClient()
    {
        if(retrofit==null)
        {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
