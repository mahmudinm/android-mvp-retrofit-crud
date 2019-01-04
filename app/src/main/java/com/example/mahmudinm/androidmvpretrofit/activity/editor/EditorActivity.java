package com.example.mahmudinm.androidmvpretrofit.activity.editor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mahmudinm.androidmvpretrofit.R;

public class EditorActivity extends AppCompatActivity implements EditorView {

    EditText et_nama, et_harga;
    ProgressDialog progressDialog;

    EditorPresenter presenter;

    String id, nama, harga;

    Menu actionMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        et_nama = (EditText) findViewById(R.id.nama);
        et_harga = (EditText) findViewById(R.id.harga);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");

        presenter = new EditorPresenter(this);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        nama = intent.getStringExtra("nama");
        harga = intent.getStringExtra("harga");

        setDataFromIntentExtra();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_editor, menu);
        actionMenu = menu;

        if (id != null) {
            actionMenu.findItem(R.id.edit).setVisible(true);
            actionMenu.findItem(R.id.delete).setVisible(true);
            actionMenu.findItem(R.id.save).setVisible(false);
            actionMenu.findItem(R.id.update).setVisible(false);
        } else {
            actionMenu.findItem(R.id.edit).setVisible(false);
            actionMenu.findItem(R.id.delete).setVisible(false);
            actionMenu.findItem(R.id.save).setVisible(true);
            actionMenu.findItem(R.id.update).setVisible(false);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String nama = et_nama.getText().toString().trim();
        String harga = et_harga.getText().toString().trim();

        switch (item.getItemId()) {
            case R.id.save:
                if (nama.isEmpty()) {
                    et_nama.setError("Please enter a nama ");
                } else if (harga.isEmpty()) {
                    et_harga.setError("Please enter a harga ");
                } else {
                    presenter.saveItem(nama, harga);
                }
                return true;
            case R.id.edit:

                editMode();
                actionMenu.findItem(R.id.edit).setVisible(false);
                actionMenu.findItem(R.id.delete).setVisible(false);
                actionMenu.findItem(R.id.save).setVisible(false);
                actionMenu.findItem(R.id.update).setVisible(true);

                return true;
            case R.id.update:
                if (nama.isEmpty()) {
                    et_nama.setError("Please enter a nama ");
                } else if (harga.isEmpty()) {
                    et_harga.setError("Please enter a harga ");
                } else {
                    presenter.updateItem(id, nama, harga);
                }

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
    public void onRequestSuccess(String status) {
        Toast.makeText(this, status, Toast.LENGTH_SHORT).show();
        setResult(RESULT_OKgi);
        finish();
    }

    @Override
    public void onRequestError(String status) {
        Toast.makeText(this, status, Toast.LENGTH_SHORT).show();
    }

    private void setDataFromIntentExtra() {
        if (id != null) {
            et_nama.setText(nama);
            et_harga.setText(harga);
            getSupportActionBar().setTitle("Update item");
            readMode();
        } else {
            getSupportActionBar().setTitle("Add item");
            editMode();
        }
    }

    private void editMode() {
        et_nama.setFocusableInTouchMode(true);
        et_harga.setFocusableInTouchMode(true);
    }

    private void readMode() {
        et_nama.setFocusableInTouchMode(false);
        et_harga.setFocusableInTouchMode(false);
        et_nama.setFocusable(false);
        et_harga.setFocusable(false);
    }
}
