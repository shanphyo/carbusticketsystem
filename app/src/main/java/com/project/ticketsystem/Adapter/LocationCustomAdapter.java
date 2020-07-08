package com.project.ticketsystem.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.ticketsystem.Database.databaseTable.Location;
import com.project.ticketsystem.Database.databaseTable.user;
import com.project.ticketsystem.R;

import java.util.List;

public class LocationCustomAdapter extends RecyclerView.Adapter<LocationCustomAdapter.ViewHolder> {
    public Context context;
    public List<Location> namelist;
    public RecyclerDeligate rd;
    public int pos=0;

    public LocationCustomAdapter(Context context, List<Location> namelist, RecyclerDeligate rd) {
        this.context = context;
        this.namelist = namelist;
        this.rd = rd;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.location_item,parent,false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
       
        holder.companyname.setText(namelist.get(position).getCarname());
        holder.locationname.setText(namelist.get(position).getLocationName()+"-( "+namelist.get(position).getProvince()+" )");
        holder.latlong.setText(namelist.get(position).getLatitute()+","+namelist.get(position).getLongitute());
    holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            pos=position;
            return false;
        }
    });



    }

    @Override
    public int getItemCount() {
        return namelist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        public TextView companyname;
        public TextView locationname;
        public TextView latlong;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnCreateContextMenuListener(this);
            companyname=itemView.findViewById(R.id.loctv_info);
            locationname=itemView.findViewById(R.id.loctv_name);
            latlong=itemView.findViewById(R.id.loctv_latlong);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            MenuItem item1=menu.add("Delete");
            item1.setOnMenuItemClickListener(mOnMyActionClickListener);

        }
        private  final  MenuItem.OnMenuItemClickListener mOnMyActionClickListener=new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                rd.onRecyclerClick(namelist.get(pos).getSyskey());
                return false;
            }
        };
    }
    public void AddAppendData(List<Location>l){
        namelist=l;

        notifyDataSetChanged();//refresh
    }




}
