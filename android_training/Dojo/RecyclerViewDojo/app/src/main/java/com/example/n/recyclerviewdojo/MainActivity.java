package com.example.n.recyclerviewdojo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<DashboardItem> mDashboardItemList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private DashboardItemAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mAdapter = new DashboardItemAdapter(mDashboardItemList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
        prepareMovieData();
    }

    private void prepareMovieData() {

        DashboardItem dashboardItem;
        dashboardItem = new DashboardItem("Acceptance", 1, true);
        mDashboardItemList.add(dashboardItem);
        dashboardItem = new DashboardItem("English", 2, true);
        mDashboardItemList.add(dashboardItem);
        dashboardItem = new DashboardItem("Logic",3, true);
        mDashboardItemList.add(dashboardItem);
        dashboardItem = new DashboardItem("Introduction", 4, true);
        mDashboardItemList.add(dashboardItem);
        dashboardItem = new DashboardItem("Finish", 5, true);
        mDashboardItemList.add(dashboardItem);
        mAdapter.notifyDataSetChanged();
    }
}
