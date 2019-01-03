package com.example.mahmudinm.androidmvpretrofit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Mahmudinm on 02/01/2019.
 */

public class Item {

    @Expose
    @SerializedName("status") private String status;
    @Expose
    @SerializedName("data") private List<Data> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }


    public class Data {
        @Expose
        @SerializedName("id") private int id;
        @Expose
        @SerializedName("nama") private String nama;
        @Expose
        @SerializedName("harga") private String harga;

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
    }

}
