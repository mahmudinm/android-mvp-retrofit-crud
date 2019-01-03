package com.example.mahmudinm.androidmvpretrofit.api.response;

import com.example.mahmudinm.androidmvpretrofit.model.Item;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Mahmudinm on 03/01/2019.
 */

public class ItemResponse {

    @Expose
    @SerializedName("status") String status;

    @Expose
    @SerializedName("data") List<Item> items;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
