package com.example.mahmudinm.androidmvpretrofit.activity.main;

import com.example.mahmudinm.androidmvpretrofit.model.Item;

import java.util.List;

/**
 * Created by Mahmudinm on 03/01/2019.
 */

public interface MainView {
    void showLoading();
    void hideLoading();
    void onGetResult(List<Item> items);
    void onErrorLoading(String status);
}
