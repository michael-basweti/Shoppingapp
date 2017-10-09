package com.example.geekalex.shoppingapp;

/**
 * Created by Geek Alex on 9/20/2017.
 */

public class Blog2 {


    private String price;
    private String description;
    private String commodity;
    private String name;
    private String location;
    private String image;

    public Blog2(){

    }

    public Blog2(String price, String description, String commodity, String image, String name, String location) {
        this.price = price;
        this.description = description;
        this.commodity = commodity;
        this.image = image;
        this.name = name;
        this.location = location;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCommodity() {
        return commodity;
    }

    public void setCommodity(String commodity) {
        this.commodity = commodity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


}
