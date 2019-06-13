package com.example.holiday.Class_item;

import java.io.Serializable;

public class Item_horizontal implements Serializable {
    private  int id;
    private int image;
    private int image_user;
    private String name_user;
    private String time;
    private String title;
    private Double price,original_price;
    private String phone;


    public Item_horizontal(int id, int image, int image_user, String name_user, String time, String title, Double price, Double original_price, String phone) {
        this.id = id;
        this.image = image;
        this.image_user = image_user;
        this.name_user = name_user;
        this.time = time;
        this.title = title;
        this.price = price;
        this.original_price = original_price;
        this.phone = phone;
    }
    public String getPhone() {
        return phone;
    }
    public int getId() {
        return id;
    }

    public int getImage() {
        return image;
    }

    public int getImage_user() {
        return image_user;
    }

    public String getName_user() {
        return name_user;
    }

    public String getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    public Double getPrice() {
        return price;
    }

    public Double getOriginal_price() {
        return original_price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setImage_user(int image_user) {
        this.image_user = image_user;
    }

    public void setName_user(String name_user) {
        this.name_user = name_user;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setOriginal_price(Double original_price) {
        this.original_price = original_price;
    }
}
