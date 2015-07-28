package com.example.trunghoang.materialviewpager.materialviewpager.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trunghoang.materialviewpager.R;

import java.util.List;

/**
 * Created by trunghoang on 7/28/15.
 */
public class TestRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    List<Object> contents;

    public TestRecyclerViewAdapter(List<Object> contents) {
        this.contents = contents;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ist_item_card, viewGroup, false);
        return new RecyclerView.ViewHolder(view) {
        };
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return contents.size();
    }
}
