package com.example.geekalex.shoppingapp;

/**
 * Created by Geek Alex on 9/21/2017.
 */

public class Blog12 {
    private String image,price,commodity,uid;

    public Blog12(){

    }

    public Blog12(String image, String price, String commodity, String uid) {
        this.image = image;
        this.price = price;
        this.commodity = commodity;
        this.uid = uid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCommodity() {
        return commodity;
    }

    public void setCommodity(String commodity) {
        this.commodity = commodity;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
