package com.example.mahmudinm.androidmvpretrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mahmudinm on 02/01/2019.
 */

public class Item {

    @Expose
    @SerializedName("id") private int id;
    @Expose
    @SerializedName("nama") private String nama;
    @Expose
    @SerializedName("harga") private String harga;
    @Expose
    @SerializedName("status") private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
