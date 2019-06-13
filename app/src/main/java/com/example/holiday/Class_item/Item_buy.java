package com.example.holiday.Class_item;

import java.io.Serializable;

public class Item_buy implements Serializable {
    private  int id;
    private int image;
    private int image_user;
    private String name_user;
    private String time;
    private String title;
    private Double price;
    private String phone;

    public Item_buy(int id, int image, int image_user, String name_user, String time, String title, Double price, String phone) {
        this.id = id;
        this.image = image;
        this.image_user = image_user;
        this.name_user = name_user;
        this.time = time;
        this.title = title;
        this.price = price;
        this.phone = phone;
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

    public String getPhone() {
        return phone;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
