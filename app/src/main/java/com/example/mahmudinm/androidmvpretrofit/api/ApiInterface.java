package com.example.mahmudinm.androidmvpretrofit.api;

import com.example.mahmudinm.androidmvpretrofit.model.Item;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Mahmudinm on 02/01/2019.
 */

public interface ApiInterface {

    @FormUrlEncoded
    @POST("item")
    Call<Item> saveItem(@Field("nama") String nama,
                        @Field("harga") String harga);

}
