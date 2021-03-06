package com.example.mahmudinm.androidmvpretrofit.api;

import com.example.mahmudinm.androidmvpretrofit.api.response.ItemResponse;
import com.example.mahmudinm.androidmvpretrofit.model.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/**
 * Created by Mahmudinm on 02/01/2019.
 */

public interface ApiInterface {

    @GET("item")
    Call<ItemResponse> getItem();

    @FormUrlEncoded
    @POST("item")
    Call<ItemResponse> saveItem(@Field("nama") String nama,
                                @Field("harga") String harga);


    @FormUrlEncoded
    @PUT("item")
    Call<ItemResponse> updateItem(@Field("id") String id,
                                  @Field("nama") String nama,
                                  @Field("harga") String harga);

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "item", hasBody = true)
    Call<ItemResponse> deleteItem(@Field("id") String id);
}
