package com.project.ticketsystem.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.ticketsystem.Adapter.CarTemplatelistCustomAdapter;
import com.project.ticketsystem.Adapter.RecyclerDeligate;
import com.project.ticketsystem.Database.Dao.carTypeDao;
import com.project.ticketsystem.Database.mainDatabase.Database;
import com.project.ticketsystem.R;
import com.project.ticketsystem.Shared.util;

import java.util.List;

public class BusList extends AppCompatActivity implements RecyclerDeligate {
    public String ctype,cpassenger,cform,cto,cperson,key,date;
    public Database db;
    RecyclerView rv;
    public CarTemplatelistCustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_list);

        TextView backArrow = findViewById(R.id.back_arrow2);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BusList.this,BusRoutes.class);
                intent.putExtra("key","bn");
                startActivity(intent);
                finish();
            }
        });

        db= Database.getINSTANCE(BusList.this);
        rv=findViewById(R.id.br_rv);
        key=getIntent().getStringExtra("key");
        if(key.equalsIgnoreCase("buslist")) {
            ctype = (getIntent().getStringExtra("brtype"));
            cpassenger=getIntent().getStringExtra("brpassenger");
            cform=getIntent().getStringExtra("brfrom");
            cto=getIntent().getStringExtra("brto");
            cperson=getIntent().getStringExtra("brperson");
            date=getIntent().getStringExtra("brdate");

        }

        String ccdate= util.currentdate();
        String cctime= util.currenttime();
       List<carTypeDao.junctioncar> list=db.carTypeDao().getSearchCar(ccdate,cctime,date,cform,cto);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        rv.setLayoutManager(manager);
        rv.setHasFixedSize(true);
        adapter=new CarTemplatelistCustomAdapter(this,list,this);
        rv.setAdapter(adapter);

    }

    @Override
    public void onRecyclerClick(int position) {
        Intent in=new Intent(BusList.this, watchView.class);
        in.putExtra("key","watchview");
        in.putExtra("skey",position+"");
        startActivity(in);
    }

    @Override
    public void onRecyclerClick1(int position, String type,int syskey,String name,String time,String price) {
        Intent in;

        if(type.equalsIgnoreCase("(2+2)")) {
             in = new Intent(BusList.this, SuperClassActivity.class);

        }else{
             in = new Intent(BusList.this, FirstClassActivity.class);
        }
        String a=syskey+"";
        in.putExtra("key","buslist");
        in.putExtra("s1",a);
        in.putExtra("passenger",cpassenger);
        in.putExtra("person",cperson);
        in.putExtra("carname",name);
        in.putExtra("from",cform);
        in.putExtra("to",cto);
        in.putExtra("date",date);
        in.putExtra("time",time);
        in.putExtra("price",price);
        startActivity(in);
    }

    @Override
    public void onRecyclerClick2(int position) {

    }
}
