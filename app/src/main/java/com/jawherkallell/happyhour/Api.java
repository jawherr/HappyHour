package com.jawherkallell.happyhour;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;



public interface Api {

    String BASE_URL = "http://192.168.1.6:3636/MyRest/public/";

    @GET("restaurents")
    Call<List<Restaurant>> getRestaurants();
}