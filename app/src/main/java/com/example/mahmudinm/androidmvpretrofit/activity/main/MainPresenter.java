package com.example.mahmudinm.androidmvpretrofit.activity.main;

import com.example.mahmudinm.androidmvpretrofit.api.ApiClient;
import com.example.mahmudinm.androidmvpretrofit.api.ApiInterface;
import com.example.mahmudinm.androidmvpretrofit.model.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mahmudinm on 03/01/2019.
 */

public class MainPresenter {

    private MainView view;

    public MainPresenter(MainView view) {
        this.view = view;
    }

    public void getData() {
        view.showLoading();

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Item> getItem = apiInterface.getItem();
        getItem.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {

            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {

            }
        });

    }

}
