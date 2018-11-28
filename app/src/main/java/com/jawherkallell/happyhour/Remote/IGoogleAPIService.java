package com.jawherkallell.happyhour.Remote;

import com.jawherkallell.happyhour.Json.model.MyPlaces;
import com.jawherkallell.happyhour.Json.model.PlaceDetail;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface IGoogleAPIService {
    @GET
    Call<MyPlaces> getNearByPlaces(@Url String url);

    @GET
    Call<MyPlaces> getPlace(@Url String url);

    @GET
    Call<PlaceDetail> getDetailPlace(@Url String url);
}
