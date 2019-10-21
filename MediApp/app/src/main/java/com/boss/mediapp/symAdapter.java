package com.boss.mediapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class symAdapter extends RecyclerView.Adapter<symAdapter.symHolder> {
    ArrayList<String> arrayList;

    public symAdapter(ArrayList<String> list) {
        this.arrayList = list;
    }

    @NonNull
    @Override
    public symAdapter.symHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new symHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull symAdapter.symHolder holder, int position) {
        holder.textView.setText(arrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class symHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public symHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv);
        }
    }
}
