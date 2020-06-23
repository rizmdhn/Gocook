package com.example.GoCookApp.REPO;

public class HomeREPO {

    int images;
    String titles, cat, cookt;

    public String getTitles() {
        return titles;
    }

    public void setTitles(String titles) {
        this.titles = titles;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getCookt() {
        return cookt;
    }

    public void setCookt(String cookt) {
        this.cookt = cookt;
    }

    public HomeREPO(int images, String titles, String cat, String cookt) {
        this.images = images;
        this.titles = titles;
        this.cat = cat;
        this.cookt = cookt;

    }

    public int getImages() {
        return images;
    }

    public void setImages(int images) {
        this.images = images;
    }
}
