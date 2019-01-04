package com.example.mahmudinm.androidmvpretrofit.activity.editor;

/**
 * Created by Mahmudinm on 02/01/2019.
 */

public interface EditorView {

    void showProgress();
    void hideProgress();
    void onRequestSuccess(String status);
    void onRequestError(String status);

}
