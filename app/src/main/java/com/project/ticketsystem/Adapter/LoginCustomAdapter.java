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

import com.project.ticketsystem.Database.databaseTable.user;
import com.project.ticketsystem.R;

import java.util.List;

public class LoginCustomAdapter extends RecyclerView.Adapter<LoginCustomAdapter.ViewHolder> {
    public Context context;
    public List<user> namelist;
    public RecyclerDeligate rd;
    public int pos=0;

    public LoginCustomAdapter(Context context, List<user> namelist, RecyclerDeligate rd) {
        this.context = context;
        this.namelist = namelist;
        this.rd = rd;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.login_item,parent,false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
       
        holder.llname.setText(namelist.get(position).getName());
        holder.llusername.setText(namelist.get(position).getEmail());
        if(namelist.get(position).getImg().equalsIgnoreCase("Noupload")){

        }else {
            holder.llprofile.setImageURI(Uri.parse(namelist.get(position).getImg()));
        }
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
        public TextView llname;
        public TextView llusername;


        public ImageView llprofile;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnCreateContextMenuListener(this);
            llname=itemView.findViewById(R.id.lltv_name);
            llusername=itemView.findViewById(R.id.lltv_username);
            llprofile=itemView.findViewById(R.id.lliv_profile);
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
    public void AddAppendData(List<user>l){
        namelist=l;

        notifyDataSetChanged();//refresh
    }




}
