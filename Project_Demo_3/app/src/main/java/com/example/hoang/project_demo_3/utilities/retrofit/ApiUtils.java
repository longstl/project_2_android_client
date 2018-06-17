package com.example.hoang.project_demo_3.utilities.retrofit;

import com.example.hoang.project_demo_3.utilities.network.ApiServices;

public class ApiUtils {
    public static final String BASE_URL = "https://daokhanh-201004.appspot.com/_api/v1/";

    public static ApiServices getApiervice() {
        return RetrofitClient.getClient(BASE_URL).create(ApiServices.class);
    }
}
