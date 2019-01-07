package com.example.mahmudinm.androidmvpretrofit.activity.editor;

import android.support.annotation.NonNull;

import com.example.mahmudinm.androidmvpretrofit.api.ApiClient;
import com.example.mahmudinm.androidmvpretrofit.api.ApiInterface;
import com.example.mahmudinm.androidmvpretrofit.api.response.ItemResponse;
import com.example.mahmudinm.androidmvpretrofit.model.Item;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mahmudinm on 02/01/2019.
 */

public class EditorPresenter {

    private EditorView view;
    private ApiInterface apiInterface;
    private CompositeDisposable disposable;

    public EditorPresenter(EditorView view) {
        this.view = view;
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        disposable = new CompositeDisposable();
    }

    void saveItem(String nama, String harga) {
        view.showProgress();
//        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
//        Call<ItemResponse> saveItem = apiInterface.saveItem(nama, harga);
//        saveItem.enqueue(new Callback<ItemResponse>() {
//            @Override
//            public void onResponse(@NonNull Call<ItemResponse> call, @NonNull Response<ItemResponse>
//                    response) {
//                view.hideProgress();
//                if (response.body().getStatus().equals("success")) {
//                    view.onRequestSuccess(response.body().getStatus());
//                } else {
//                    view.onRequestError(response.body().getStatus());
//                }
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<ItemResponse> call, @NonNull Throwable t) {
//                view.hideProgress();
//                view.onRequestError(t.getLocalizedMessage());
//            }
//        });
        disposable.add(
                apiInterface.saveItem(nama, harga)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<ItemResponse>() {
                    @Override
                    public void onNext(ItemResponse itemResponse) {
                        view.hideProgress();
                        if (itemResponse.getStatus().equals("success")) {
                            view.onRequestSuccess(itemResponse.getStatus());
                        } else {
                            view.onRequestError(itemResponse.getStatus());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hideProgress();

                    }

                    @Override
                    public void onComplete() {
                        view.hideProgress();

                    }
                })
        );

    }

    void updateItem(String id, String nama, String harga) {
        view.showProgress();
//        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
//        Call<ItemResponse> updateItem = apiInterface.updateItem(id, nama, harga);
//        updateItem.enqueue(new Callback<ItemResponse>() {
//            @Override
//            public void onResponse(@NonNull Call<ItemResponse> call,@NonNull
//                        Response<ItemResponse> response) {
//                view.hideProgress();
//                if (response.isSuccessful() && response.body() != null) {
//                    String status = response.body().getStatus();
//                    if (status.equals("success")) {
//                        view.onRequestSuccess(status);
//                    } else {
//                        view.onRequestError(status);
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<ItemResponse> call,@NonNull Throwable t) {
//                view.hideProgress();
//                view.onRequestError(t.getLocalizedMessage());
//            }
//        });
        disposable.add(
          apiInterface.updateItem(id, nama, harga)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver(){
                    @Override
                    public void onComplete() {
                        view.hideProgress();
                        view.onRequestSuccess("Berhasil update");
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        view.hideProgress();
                        view.onRequestSuccess("Gagal update");

                    }
                })
        );

    }

    void deleteItem(String id) {
        view.showProgress();
//        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
//        Call<ItemResponse> deleteItem = apiInterface.deleteItem(id);
//        deleteItem.enqueue(new Callback<ItemResponse>() {
//            @Override
//            public void onResponse(@NonNull Call<ItemResponse> call, @NonNull Response<ItemResponse> response) {
//                view.hideProgress();
//                if (response.isSuccessful() && response.body() != null) {
//                    String status = response.body().getStatus();
//                    if (status.equals("success")) {
//                        view.onRequestSuccess(response.body().getStatus());
//                    } else {
//                        view.onRequestError(response.body().getStatus());
//                    }
//
//                }
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<ItemResponse> call, @NonNull Throwable t) {
//                view.hideProgress();
//                view.onRequestError(t.getLocalizedMessage());
//
//            }
//        });
        disposable.add(
                apiInterface.deleteItem(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver(){
                    @Override
                    public void onComplete() {
                        view.hideProgress();
                        view.onRequestSuccess("Berhasil hapus data");
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        view.hideProgress();
                        view.onRequestSuccess("Gagal hapus data");

                    }
                })
        );
    }

}
