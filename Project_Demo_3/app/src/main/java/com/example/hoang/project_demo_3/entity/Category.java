package com.example.hoang.project_demo_3.entity;

public class Category {
    private String category_Title;
    private Product product;

    public String getCategory_Title() {
        return category_Title;
    }

    public void setCategory_Title(String category_Title) {
        this.category_Title = category_Title;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
