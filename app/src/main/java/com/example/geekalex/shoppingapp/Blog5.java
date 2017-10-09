package com.example.geekalex.shoppingapp;

/**
 * Created by Geek Alex on 9/20/2017.
 */

public class Blog5 {
    private String image;
    private String commodity;
    private String description;
    private String quantity;
    private String price;
    private String name;
    public  Blog5(){

    }

    public Blog5(String image, String commodity, String description, String quantity,
                 String price, String name, String location) {
        this.image = image;
        this.commodity = commodity;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.name = name;
        this.location = location;
    }

    private String location;

    public String getCommodity() {
        return commodity;
    }

    public void setCommodity(String commodity) {
        this.commodity = commodity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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
}
