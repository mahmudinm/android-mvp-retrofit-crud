package com.example.mahmudinm.androidmvpretrofit.activity.main;

import android.util.Log;

import com.example.mahmudinm.androidmvpretrofit.api.ApiClient;
import com.example.mahmudinm.androidmvpretrofit.api.ApiInterface;
import com.example.mahmudinm.androidmvpretrofit.api.response.ItemResponse;
import com.example.mahmudinm.androidmvpretrofit.model.Item;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mahmudinm on 03/01/2019.
 */

public class MainPresenter {

    private MainView view;
    List<Item> items = new ArrayList<>();

    public MainPresenter(MainView view) {
        this.view = view;
    }

    public void getData() {
        view.showLoading();

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ItemResponse> getItem = apiInterface.getItem();
        getItem.enqueue(new Callback<ItemResponse>() {
            @Override
            public void onResponse(Call<ItemResponse> call, Response<ItemResponse> response) {
                view.hideLoading();
//                if (response.isSuccessful() && response.body() != null) {
//                items = response.body().getItems();
                view.onGetResult(response.body().getItems());
                view.testData(response.body().getStatus());
                Log.d("response", "onDataResponse: " + response.body().getItems());
                Log.d("status message", "onResponse: " + response.body().getStatus());
//                }
            }

            @Override
            public void onFailure(Call<ItemResponse> call, Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });

    }

}
