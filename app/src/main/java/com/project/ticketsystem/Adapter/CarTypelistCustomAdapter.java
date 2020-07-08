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

import com.project.ticketsystem.Database.databaseTable.car;
import com.project.ticketsystem.R;

import java.util.List;

public class CarTypelistCustomAdapter extends RecyclerView.Adapter<CarTypelistCustomAdapter.ViewHolder> {
    public Context context;
    public List<car> namelist;
    public RecyclerDeligate rd;
    public int pos=0;

    public CarTypelistCustomAdapter(Context context, List<car> namelist, RecyclerDeligate rd) {
        this.context = context;
        this.namelist = namelist;
        this.rd = rd;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cartype_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.ctlname.setText(namelist.get(position).getName());
        holder.ctlphone.setText(namelist.get(position).getPhNo());
        if(namelist.get(position).getType().equalsIgnoreCase("(2+2)")) {
            holder.ctltype.setText("Super Seat -"+namelist.get(position).getType());
        }else{
            holder.ctltype.setText("First Cass -"+namelist.get(position).getType());
        }
        if(namelist.get(position).getImage().equalsIgnoreCase("nologo")){
            holder.ctlimage.setImageResource(R.drawable.logo);
        }else {
            holder.ctlimage.setImageURI(Uri.parse(namelist.get(position).getImage()));
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

    public class ViewHolder extends RecyclerView.ViewHolder implements  View.OnCreateContextMenuListener{
        public TextView ctlname;
        public TextView ctlphone;
        public TextView ctltype;
        public ImageView ctlimage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnCreateContextMenuListener(this);
            ctlname=itemView.findViewById(R.id.ctltv_name);
            ctlphone=itemView.findViewById(R.id.ctltv_phno);
            ctltype=itemView.findViewById(R.id.ctltv_type);
            ctlimage=itemView.findViewById(R.id.ctliv_carprofile);
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
    public void AddAppendData(List<car>list1){
        namelist=list1;

        notifyDataSetChanged();//refresh

    }
}
