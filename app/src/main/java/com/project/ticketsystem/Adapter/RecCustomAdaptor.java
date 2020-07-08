package com.project.ticketsystem.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.ticketsystem.R;

import java.util.ArrayList;

public class RecCustomAdaptor extends RecyclerView.Adapter<RecCustomAdaptor.ViewHolder> {

    ArrayList<String> namelist;
    ArrayList<Integer> imagelist;
    Context context;
    RecyclerDeligate deligate;

    public RecCustomAdaptor(ArrayList<String> namelist, ArrayList<Integer> imagelist, Context context, RecyclerDeligate deligate) {
        this.namelist = namelist;
        this.imagelist = imagelist;
        this.context = context;
        this.deligate = deligate;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.class_items,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.name.setText(namelist.get(position));
        holder.image.setImageResource(imagelist.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deligate.onRecyclerClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return namelist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tv_className);
            image = itemView.findViewById(R.id.iv_image);

        }
    }
}
