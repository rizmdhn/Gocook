package com.example.GoCookApp.REPO;

public class RecipeREPO {
    String Image;
    String title, categories, cookt;

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getCookt() {
        return cookt;
    }

    public void setCookt(String cookt) {
        this.cookt = cookt;
    }
}
