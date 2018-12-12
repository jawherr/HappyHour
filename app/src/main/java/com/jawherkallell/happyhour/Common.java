package com.jawherkallell.happyhour;


import com.jawherkallell.happyhour.Json.model.Results;
import com.jawherkallell.happyhour.Remote.IGoogleAPIService;
import com.jawherkallell.happyhour.Remote.RetrofitClient;

public class Common {

    private  static final String GOOGLE_API_URL = "https://maps.googleapis.com/";
    private  static  final  String MyAPI_URL="";
    public static Results currentResult;

    public static IGoogleAPIService getGoogleAPIService()
    {
        return RetrofitClient.getClient(GOOGLE_API_URL).create(IGoogleAPIService.class);
    }
}
