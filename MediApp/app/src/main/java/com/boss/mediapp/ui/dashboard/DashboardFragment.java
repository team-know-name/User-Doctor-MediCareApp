package com.boss.mediapp.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.boss.mediapp.R;
import com.boss.mediapp.ReportsAdapter;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {
    ArrayList<String> list;
    private DashboardViewModel dashboardViewModel;
    ReportsAdapter reportsAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = new ArrayList<>();
        list.add("Allergies");
        list.add("Lab Results");
        list.add("Medications");
        list.add("Immunizations");
        list.add("History");
        list.add("Problems");
        list.add("Others");
        list.add("Dental");
        list.add("Ophthalmology");
        list.add("Radiology");
        list.add("Devices");
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.rv);
        reportsAdapter = new ReportsAdapter(list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(reportsAdapter);
        return root;
    }
}