package com.example.holiday.Class_item;

import java.io.Serializable;

public class Item_Post implements Serializable {
    private int id,image,user_image,year;
    private String name,phone;
    private String post_type,type,product_type;
    private String title,condition;
    private String category;
    private String brand,rent_type;
    private String color,VIN_code,marchine_code;
    private Double cast,price;
    private String text,email,address;
    private String bedroom,bathroom,facing,size;

    public Item_Post(int id, int image,int user_image,int year ,String name, String phone, String post_type,String type,String product_type,
             String condition, String title, String category, String brand, String color, String VIN_code, String marchine_code,
             Double cast, Double price, String rent_type, String email, String address,String text,
             String bedroom,String bathroom,String facing,String size) {
        this.id = id;
        this.image = image;
        this.user_image = user_image;
        this.year = year;
        this.name = name;
        this.phone = phone;
        this.post_type = post_type;
        this.type = type;
        this.product_type = product_type;
        this.condition = condition;
        this.title = title;
        this.category = category;
        this.brand = brand;
        this.color = color;
        this.VIN_code = VIN_code;
        this.marchine_code = marchine_code;
        this.cast = cast;
        this.price = price;
        this.rent_type = rent_type;
        this.email = email;
        this.address = address;
        this.text = text;
        this.bedroom = bedroom;
        this.bathroom = bathroom;
        this.facing = facing;
        this.size = size;
    }

    public void setBedroom(String bedroom) {
        this.bedroom = bedroom;
    }

    public void setBathroom(String bathroom) {
        this.bathroom = bathroom;
    }

    public void setFacing(String facing) {
        this.facing = facing;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getBedroom() {
        return bedroom;
    }

    public String getBathroom() {
        return bathroom;
    }

    public String getFacing() {
        return facing;
    }

    public String getSize() {
        return size;
    }

    public String getType() {
        return type;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public int getImage() {
        return image;
    }
    public int getUser_image(){
        return user_image;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getPost_type() {
        return post_type;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getBrand() {
        return brand;
    }

    public String getColor() {
        return color;
    }

    public String getVIN_code() {
        return VIN_code;
    }

    public String getMarchine_code() {
        return marchine_code;
    }

    public Double getCast() {
        return cast;
    }

    public Double getPrice() {
        return price;
    }

    public String getRent_type() {
        return rent_type;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getText() {
        return text;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImage(int image) {
        this.image = image;
    }
    public void setUser_image(int user_image){
        this.user_image = user_image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPost_type(String post_type) {
        this.post_type = post_type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setVIN_code(String VIN_code) {
        this.VIN_code = VIN_code;
    }

    public void setMarchine_code(String marchine_code) {
        this.marchine_code = marchine_code;
    }

    public void setCast(Double cast) {
        this.cast = cast;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setRent_type(String rent_type) {
        this.rent_type = rent_type;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setText(String text) {
        this.text = text;
    }
}
