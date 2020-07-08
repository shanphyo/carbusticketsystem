package com.project.ticketsystem.Adapter;

public interface RecyclerDeligate {

    public void onRecyclerClick(int position);
    public void onRecyclerClick1(int position, String type, int syskey, String name, String time, String price);
    public void onRecyclerClick2(int position);
}
