package com.example.n.recyclerviewdojo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class DashboardItemAdapter extends RecyclerView.Adapter<DashboardItemAdapter.MyViewHolder> {
    private List<DashboardItem> mDashboardItemList;

    public DashboardItemAdapter(List<DashboardItem> dashboardItems) {
        this.mDashboardItemList = dashboardItems;
    }

    @Override
    public DashboardItemAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_recycler_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DashboardItemAdapter.MyViewHolder holder, int position) {
        DashboardItem dashboardItem = mDashboardItemList.get(position);
        holder.mTextViewInShape.setText(String.valueOf(dashboardItem.getmDashboardCount()));
        holder.mDescriptionTextView.setText(dashboardItem.getmDescription());
    }

    @Override
    public int getItemCount() {
        return mDashboardItemList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mTextViewInShape, mDescriptionTextView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewInShape = (TextView) itemView.findViewById(R.id.textViewInShape);
            mDescriptionTextView = (TextView) itemView.findViewById(R.id.dashboardDescriptionTextView);
        }
    }
}
