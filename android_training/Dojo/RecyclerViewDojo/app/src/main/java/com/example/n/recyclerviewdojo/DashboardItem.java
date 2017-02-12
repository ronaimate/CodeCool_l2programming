package com.example.n.recyclerviewdojo;

/**
 * Created by Én on 2016. 07. 29..
 */
public class DashboardItem {

    private String mDescription;
    private int mDashboardCount;
    private boolean isEnabled;

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public int getmDashboardCount() {
        return mDashboardCount;
    }

    public void setmDashboardCount(int mDashboardCount) {
        this.mDashboardCount = mDashboardCount;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public DashboardItem(String mDescription, int mDashboardCount, boolean isEnabled) {
        this.mDescription = mDescription;
        this.mDashboardCount = mDashboardCount;
        this.isEnabled = isEnabled;
    }
}
