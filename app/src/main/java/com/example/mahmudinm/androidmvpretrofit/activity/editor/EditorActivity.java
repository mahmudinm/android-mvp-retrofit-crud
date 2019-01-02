package com.example.mahmudinm.androidmvpretrofit.activity.editor;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mahmudinm.androidmvpretrofit.R;
import com.example.mahmudinm.androidmvpretrofit.api.ApiClient;
import com.example.mahmudinm.androidmvpretrofit.api.ApiInterface;
import com.example.mahmudinm.androidmvpretrofit.model.Item;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditorActivity extends AppCompatActivity implements EditorView {

    EditText et_nama, et_harga;
    ProgressDialog progressDialog;

    EditorPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        et_nama = (EditText) findViewById(R.id.nama);
        et_harga = (EditText) findViewById(R.id.harga);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");

        presenter = new EditorPresenter(this);

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
                    presenter.saveNote(nama, harga);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.hide();

    }

    @Override
    public void onAddSuccess(String status) {
        Toast.makeText(this, status, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onAddError(String status) {
        Toast.makeText(this, status, Toast.LENGTH_SHORT).show();
    }
}
