package com.example.hoang.project_demo_3.entity;

import com.google.gson.annotations.SerializedName;

public class Account {
    @SerializedName("phone")
    private String phone;
    private String password;
    private String fullname;
    private String avatar;
    private String email;
    private String salt;
    private String status;
    private String created_At;
    private String updated_At;

    public Account() {
    }

    public Account(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }

    public Account(String phone, String password, String fullname, String email) {
        this.phone = phone;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated_At() {
        return created_At;
    }

    public void setCreated_At(String created_At) {
        this.created_At = created_At;
    }

    public String getUpdated_At() {
        return updated_At;
    }

    public void setUpdated_At(String updated_At) {
        this.updated_At = updated_At;
    }
}
