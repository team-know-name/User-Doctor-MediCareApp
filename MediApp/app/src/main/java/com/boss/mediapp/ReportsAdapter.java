package com.boss.mediapp;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReportsAdapter extends RecyclerView.Adapter<ReportsAdapter.ReportHolder> {
    ArrayList<String> arrayList;

    public ReportsAdapter(ArrayList<String> list) {
        this.arrayList = list;
    }

    @NonNull
    @Override
    public ReportsAdapter.ReportHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        final ReportHolder reportHolder = new ReportHolder(view);
        reportHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("TAG", reportHolder.textView.getText().toString());
                if (reportHolder.textView.getText().toString().equals("Lab Results")) {
                    Intent intent = new Intent(parent.getContext(), LabResultsActivity.class);
                    parent.getContext().startActivity(intent);
                }
            }
        });
        return reportHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReportsAdapter.ReportHolder holder, int position) {
        holder.textView.setText(arrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ReportHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ReportHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv);
        }
    }
}