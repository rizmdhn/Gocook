package com.example.GoCookApp.REPO;

public class MenuREPO {

    int Image;
    String titile;
    String price;

    public MenuREPO(int image, String titile, String price) {
        Image = image;
        this.titile = titile;
        this.price = price;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
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
