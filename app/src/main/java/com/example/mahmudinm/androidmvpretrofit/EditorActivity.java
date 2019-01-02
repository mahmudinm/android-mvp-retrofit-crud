package com.example.mahmudinm.androidmvpretrofit;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditorActivity extends AppCompatActivity {

    EditText et_nama, et_harga;
    ProgressDialog progressDialog;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        et_nama = (EditText) findViewById(R.id.nama);
        et_harga = (EditText) findViewById(R.id.harga);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                String nama = et_nama.getText().toString().trim();
                String harga = et_harga.getText().toString().trim();
                if (nama.isEmpty()) {
                    et_nama.setError("Please enter a nama ");
                } else if (harga.isEmpty()) {
                    et_harga.setError("Please enter a harga ");
                } else {
                    saveNote(nama, harga);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveNote(String nama, String harga) {
        progressDialog.show();
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Item> saveItem = apiInterface.saveItem(nama, harga);
        saveItem.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(@NonNull Call<Item> call, Response<Item> response) {
                progressDialog.dismiss();
                Toast.makeText(EditorActivity.this, response.body().getStatus(), Toast.LENGTH_SHORT)
                        .show();
                finish();
            }

            @Override
            public void onFailure(@NonNull Call<Item> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(EditorActivity.this,
                        t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
