package com.example.mahmudinm.androidmvpretrofit.api;

import com.example.mahmudinm.androidmvpretrofit.api.response.ItemResponse;

import io.reactivex.Observable;

/**
 * Created by Mahmudinm on 07/01/2019.
 */

public interface ItemRepository {

    Observable<ItemResponse> getItem();
}
