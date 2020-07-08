package com.project.ticketsystem.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.ticketsystem.Database.Dao.carTypeDao;
import com.project.ticketsystem.R;
import com.project.ticketsystem.Shared.util;

import java.util.List;

public class CarTemplatelistCustomAdapter1 extends RecyclerView.Adapter<CarTemplatelistCustomAdapter1.ViewHolder> {
    public Context context;
    public List<carTypeDao.junctioncar> namelist;
    public RecyclerDeligate rd;
    public int pos=0;


    public CarTemplatelistCustomAdapter1(Context context, List<carTypeDao.junctioncar> namelist, RecyclerDeligate rd) {
        this.context = context;
        this.namelist = namelist;
        this.rd = rd;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cattemplate_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.ctltemplatetype.setText(namelist.get(position).getName1()+"-"+namelist.get(position).getType1());
        holder.ctltemplatefromto.setText("From "+namelist.get(position).getFromcity()+" To "+namelist.get(position).getTocity());
       holder.ctltemplatetime.setText("Duraing Time "+ util.twentyfourToTwelve(namelist.get(position).getTime()));
       holder.ctltemplatedate.setText("Duraing Date "+util.stringToDate(namelist.get(position).getDate()));
       holder.ctltemplateprice.setText("Price "+namelist.get(position).getPrice());

       // holder.ctltemplateprofile.setImageURI(Uri.parse(namelist.get(position).getImage()));
        if(namelist.get(position).getImage().equalsIgnoreCase("nologo")){
            holder.ctltemplateprofile.setImageResource(R.drawable.logo);
        }else {
            holder.ctltemplateprofile.setImageURI(Uri.parse(namelist.get(position).getImage()));
        }
        holder.btnbuyticket.setText("View Info");
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                pos=position;
                return false;
            }
        });
        holder.btnbuyticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             rd.onRecyclerClick1(position,namelist.get(position).getType1(),namelist.get(position).getSyskey(),namelist.get(position).getName1(),namelist.get(position).getTime(),namelist.get(position).getPrice());
            }
        });
        holder.btnwatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rd.onRecyclerClick2(namelist.get(position).getSyskey());
            }
        });
    }

    @Override
    public int getItemCount() {
        return namelist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements  View.OnCreateContextMenuListener{
        public TextView ctltemplatetype;
        public TextView ctltemplatefromto;
        public TextView ctltemplatetime;
        public TextView ctltemplatedate;
        public TextView ctltemplateprice;
        public ImageView ctltemplateprofile;
        public Button btnwatch,btnbuyticket;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnCreateContextMenuListener(this);
           ctltemplatetype=itemView.findViewById(R.id.ctemietm_type);
           ctltemplatefromto=itemView.findViewById(R.id.ctemietm_fromto);
           ctltemplatetime=itemView.findViewById(R.id.ctemietm_time);
           ctltemplatedate=itemView.findViewById(R.id.ctemietm_date);
            ctltemplateprice=itemView.findViewById(R.id.ctemietm_price);
            ctltemplateprofile=itemView.findViewById(R.id.temietm_carprofile);
            btnbuyticket=itemView.findViewById(R.id.btn_buyticket);
            btnwatch=itemView.findViewById(R.id.btn_watch);
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
    public void AddAppendData(List<carTypeDao.junctioncar>list1){
        namelist=list1;

        notifyDataSetChanged();//refresh
    }
}
