package com.project.ticketsystem.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.ticketsystem.Database.Dao.carTypeDao;
import com.project.ticketsystem.R;

import java.util.List;

public class BusRoutesCustomAdapter extends RecyclerView.Adapter<BusRoutesCustomAdapter.ViewHolder> {
    public Context context;
    public List<carTypeDao.junctioncar> namelist;


    public BusRoutesCustomAdapter(Context context, List<carTypeDao.junctioncar> namelist) {
        this.context = context;
        this.namelist = namelist;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cattemplate_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.ctltemplatetype.setText(namelist.get(position).getName1()+"-"+namelist.get(position).getType1());
        holder.ctltemplatefromto.setText("From "+namelist.get(position).getFromcity()+" To "+namelist.get(position).getTocity());
       holder.ctltemplatetime.setText("Duraing Time "+namelist.get(position).getTime());
       holder.ctltemplatedate.setText("Duraing Date "+namelist.get(position).getDate());
       holder.ctltemplateprice.setText("Price "+namelist.get(position).getPrice());
        holder.ctltemplateprofile.setImageURI(Uri.parse(namelist.get(position).getImage()));
    }

    @Override
    public int getItemCount() {
        return namelist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView ctltemplatetype;
        public TextView ctltemplatefromto;
        public TextView ctltemplatetime;
        public TextView ctltemplatedate;
        public TextView ctltemplateprice;
        public ImageView ctltemplateprofile;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           ctltemplatetype=itemView.findViewById(R.id.ctemietm_type);
           ctltemplatefromto=itemView.findViewById(R.id.ctemietm_fromto);
           ctltemplatetime=itemView.findViewById(R.id.ctemietm_time);
           ctltemplatedate=itemView.findViewById(R.id.ctemietm_date);
            ctltemplateprice=itemView.findViewById(R.id.ctemietm_price);
            ctltemplateprofile=itemView.findViewById(R.id.temietm_carprofile);
        }
    }
}
