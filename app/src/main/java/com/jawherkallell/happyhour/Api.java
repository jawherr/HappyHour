package com.jawherkallell.happyhour;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;



public interface Api {

    String BASE_URL = "http://72a1acfd.ngrok.io/MyRest/public/";

    @GET("restaurents")
    Call<List<Restaurant>> getRestaurant();
}