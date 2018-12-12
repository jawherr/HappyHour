package com.jawherkallell.happyhour.Remote;

import com.jawherkallell.happyhour.Json.model.Event;
import com.jawherkallell.happyhour.Json.model.MyPlaces;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface IMyAPI {
    @GET
    Call<List<Event>> getEvents(@Url String url);
    @GET
    Call<Event> getEventById(@Url String url);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://b934f907.ngrok.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}
