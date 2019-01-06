package com.example.mahmudinm.androidmvpretrofit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mahmudinm on 02/01/2019.
 */

public class Item {

    @Expose
    @SerializedName("id") private String id;
    @Expose
    @SerializedName("nama") private String nama;
    @Expose
    @SerializedName("harga") private String harga;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

}
