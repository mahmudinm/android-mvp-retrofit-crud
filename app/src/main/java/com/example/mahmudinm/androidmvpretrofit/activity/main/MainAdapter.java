package com.example.mahmudinm.androidmvpretrofit.activity.main;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mahmudinm.androidmvpretrofit.R;
import com.example.mahmudinm.androidmvpretrofit.model.Item;

import java.util.List;

/**
 * Created by Mahmudinm on 03/01/2019.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private Context context;
    private List<Item> items;
    private ItemClickListener itemClickListener;

    public MainAdapter(Context context, List<Item> items, ItemClickListener itemClickListener) {
        this.context = context;
        this.items = items;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = items.get(position);
        holder.tv_id.setText(item.getId());
        holder.tv_nama.setText(item.getNama());
        holder.tv_harga.setText(item.getHarga());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_id, tv_nama, tv_harga;
        CardView card_item;
        ItemClickListener itemClickListener;

        public ViewHolder(View itemView, ItemClickListener itemClickListener) {
            super(itemView);
            this.itemClickListener = itemClickListener;

            tv_id = (TextView) itemView.findViewById(R.id.id);
            tv_nama = (TextView) itemView.findViewById(R.id.nama);
            tv_harga = (TextView) itemView.findViewById(R.id.harga);
            card_item = (CardView) itemView.findViewById(R.id.card_item);

            card_item.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(v, getAdapterPosition());
        }
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}
