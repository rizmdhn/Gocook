package com.example.GoCookApp.REPO;

import java.util.ArrayList;

public class GeneralInfoREPO {
    private String title;
    private String image;
    private String categories;
    private String preptime;
    private String cooktime;
    private ArrayList ingredients, howtomake;
    public GeneralInfoREPO(){

    }

    public GeneralInfoREPO(String title, String image, String categories, String preptime, String cooktime, ArrayList ingredients, ArrayList howtomake, String servings) {
        this.title = title;
        this.image = image;
        this.categories = categories;
        this.preptime = preptime;
        this.cooktime = cooktime;
        this.ingredients = ingredients;
        this.howtomake = howtomake;
        this.servings = servings;
    }

    public String gettitle() {
        return title;
    }

    public void settitle(String title) {
        this.title = title;
    }

    public String getimage() {
        return image;
    }

    public void setimage(String image) {
        this.image = image;
    }

    public String getcategories() {
        return categories;
    }

    public void setcategories(String categories) {
        this.categories = categories;
    }

    public String getpreptime() {
        return preptime;
    }

    public void setpreptime(String preptime) {
        this.preptime = preptime;
    }

    public String getcooktime() {
        return cooktime;
    }

    public void setcooktime(String cooktime) {
        this.cooktime = cooktime;
    }

    public ArrayList getingredients() {
        return ingredients;
    }

    public void setingredients(ArrayList ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList gethowtomake() {
        return howtomake;
    }

    public void sethowtomake(ArrayList howtomake) {
        this.howtomake = howtomake;
    }

    public String getservings() {
        return servings;
    }

    public void setservings(String servings) {
        this.servings = servings;
    }

    private String servings;

}
