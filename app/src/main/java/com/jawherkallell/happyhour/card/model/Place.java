package com.jawherkallell.happyhour.card.model;


import com.jawherkallell.happyhour.Json.model.Photos;

public class Place {
    private  String id;
    private  String nom;
    private  String imgUrl;
    private String rating;
    Photos[] photos;

    public Place(String nom, String imgUrl, String rating) {
        this.nom = nom;
        this.imgUrl = imgUrl;
        this.rating = rating;
    }

    public Photos[] getPhotos() {
        return photos;
    }

    public void setPhotos(Photos[] photos) {
        this.photos = photos;
    }

    public String getNom() {
        return nom;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Place(String nom, String rating) {
        this.nom = nom;
        this.rating = rating;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
