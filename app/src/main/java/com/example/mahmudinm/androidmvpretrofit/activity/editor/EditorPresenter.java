package com.example.mahmudinm.androidmvpretrofit.activity.editor;

import android.support.annotation.NonNull;

import com.example.mahmudinm.androidmvpretrofit.api.ApiClient;
import com.example.mahmudinm.androidmvpretrofit.api.ApiInterface;
import com.example.mahmudinm.androidmvpretrofit.api.response.ItemResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mahmudinm on 02/01/2019.
 */

public class EditorPresenter {

    private EditorView view;

    public EditorPresenter(EditorView view) {
        this.view = view;
    }

    void saveItem(String nama, String harga) {
        view.showProgress();
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ItemResponse> saveItem = apiInterface.saveItem(nama, harga);
        saveItem.enqueue(new Callback<ItemResponse>() {
            @Override
            public void onResponse(@NonNull Call<ItemResponse> call, @NonNull Response<ItemResponse>
                    response) {
                view.hideProgress();
                if (response.body().getStatus().equals("success")) {
                    view.onRequestSuccess(response.body().getStatus());
                } else {
                    view.onRequestError(response.body().getStatus());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ItemResponse> call, @NonNull Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });

    }

    void updateItem(String id, String nama, String harga) {
        view.showProgress();
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ItemResponse> updateItem = apiInterface.updateItem(id, nama, harga);
        updateItem.enqueue(new Callback<ItemResponse>() {
            @Override
            public void onResponse(@NonNull Call<ItemResponse> call,@NonNull
                        Response<ItemResponse> response) {
                view.hideProgress();
                if (response.isSuccessful() && response.body() != null) {
                    String status = response.body().getStatus();
                    if (status.equals("success")) {
                        view.onRequestSuccess(status);
                    } else {
                        view.onRequestError(status);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ItemResponse> call,@NonNull Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });

    }
}
