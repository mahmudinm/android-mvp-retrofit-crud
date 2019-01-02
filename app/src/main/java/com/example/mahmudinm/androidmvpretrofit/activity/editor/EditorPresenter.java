package com.example.mahmudinm.androidmvpretrofit.activity.editor;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.mahmudinm.androidmvpretrofit.api.ApiClient;
import com.example.mahmudinm.androidmvpretrofit.api.ApiInterface;
import com.example.mahmudinm.androidmvpretrofit.model.Item;

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

    void saveNote(String nama, String harga) {
        view.showProgress();
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Item> saveItem = apiInterface.saveItem(nama, harga);
        saveItem.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(@NonNull Call<Item> call, Response<Item> response) {
                view.hideProgress();
                if (response.body().getStatus().equals("success")) {
                    view.onAddSuccess(response.body().getStatus());
                } else {
                    view.onAddError(response.body().getStatus());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Item> call, Throwable t) {
                view.hideProgress();
                view.onAddError(t.getLocalizedMessage());
            }
        });

    }
}
