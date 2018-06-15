package com.example.hoang.project_demo_3.entity;

import com.google.gson.annotations.SerializedName;

public class Product {
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    private String model;
    private String manufacturer;
    @SerializedName("price")
    private int price;
    @SerializedName("thumbnail")
    private String thumbnail;

    public Product() {
    }

    public Product(String title, String description, int price, String thumbnail) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}