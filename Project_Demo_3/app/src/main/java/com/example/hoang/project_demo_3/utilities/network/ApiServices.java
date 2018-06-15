package com.example.hoang.project_demo_3.utilities.network;

import com.example.hoang.project_demo_3.entity.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ApiServices {
    /**
     * Create interface + method GET list
     */
    @Headers({
            "Accept: application/json",
            "User-Agent: Mozilla/5.0"
    })
    @GET("/products/")
    Call<List<Product>> listProduct();

}
