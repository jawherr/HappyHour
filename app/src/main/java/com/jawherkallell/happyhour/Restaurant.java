package com.jawherkallell.happyhour;

public class Restaurant {
    private int id;
    private String nom;
    private String adresse;
    private int id_rest;
    private String src;

    public Restaurant(int id, String nom, String adresse, int id_rest, String src) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.id_rest = id_rest;
        this.src = src;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getId_rest() {
        return id_rest;
    }

    public void setId_rest(int id_rest) {
        this.id_rest = id_rest;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
