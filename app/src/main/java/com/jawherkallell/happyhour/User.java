package com.jawherkallell.happyhour;

import com.google.gson.annotations.SerializedName;

class User {
    @SerializedName("response")
    private String Response;

    @SerializedName("name")
    private String Name;

    public User() {
    }

    public String getResponse() {
        return Response;
    }

    public String getName() {
        return Name;
    }
}
