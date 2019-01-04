package com.example.mahmudinm.androidmvpretrofit.activity.main;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.mahmudinm.androidmvpretrofit.R;
import com.example.mahmudinm.androidmvpretrofit.activity.editor.EditorActivity;
import com.example.mahmudinm.androidmvpretrofit.model.Item;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView{

    FloatingActionButton fab;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefresh;
    MainPresenter presenter;
    MainAdapter adapter;
    MainAdapter.ItemClickListener itemClickListener;

    List<Item> item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, EditorActivity.class), 12);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        presenter = new MainPresenter(this);
        presenter.getData();

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getData();
            }
        });

        itemClickListener = new MainAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Item itemData = item.get(position);
                String id = itemData.getId();
                String nama = itemData.getNama();
                String harga = itemData.getHarga();

                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("nama", nama);
                intent.putExtra("harga", harga);
                startActivityForResult(intent, 11);
            }
        };

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 11 && resultCode == RESULT_OK) {
            presenter.getData();
        } else if (requestCode == 12 && resultCode == RESULT_OK) {
            presenter.getData();
        }
    }

    @Override
    public void showLoading() {
        swipeRefresh.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefresh.setRefreshing(false);

    }

    @Override
    public void onGetResult(List<Item> items) {
        adapter = new MainAdapter(this, items, itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        item = items;
    }

    @Override
    public void onErrorLoading(String status) {
        Toast.makeText(this, status, Toast.LENGTH_SHORT).show();
    }
}
