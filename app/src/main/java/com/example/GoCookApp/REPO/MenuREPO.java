 package com.example.GoCookApp.REPO;

public class MenuREPO {

    String Image;
    String titile;
    String price;

    public MenuREPO(String image, String titile, String price) {
        Image = image;
        this.titile = titile;
        this.price = price;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getTitile() {
        return titile;
    }

    public void setTitile(String titile) {
        this.titile = titile;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}