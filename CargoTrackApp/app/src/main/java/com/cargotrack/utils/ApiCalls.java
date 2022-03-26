package com.cargotrack.utils;

import com.cargotrack.models.ApiCallModel;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ApiCalls {
    public static OkHttpClient client = new OkHttpClient();

    public static ApiCallModel apiCallModel(Request request) throws IOException {
        Tools.NetPolicy();
        Response response = client.newCall(request).execute();
        ApiCallModel model = new ApiCallModel();
        model.setCode(response.code());
        model.setData(response.body().string());
        return model;
    }
}
