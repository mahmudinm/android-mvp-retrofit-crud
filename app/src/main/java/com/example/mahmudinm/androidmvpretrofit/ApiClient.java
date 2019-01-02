package com.example.mahmudinm.androidmvpretrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mahmudinm on 02/01/2019.
 */

public class ApiClient {

    private static final String base = "http://192.168.100.13/android_codeigniter_crud/api/";
    private static Retrofit retrofit;

    private static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(base)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return  retrofit;
    }


}
