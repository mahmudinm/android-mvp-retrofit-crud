package com.example.mahmudinm.androidmvpretrofit.activity.main;

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

    }

}
