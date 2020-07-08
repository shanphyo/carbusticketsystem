package com.project.ticketsystem.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.ticketsystem.Adapter.LocationCustomAdapter;

import com.project.ticketsystem.Adapter.RecyclerDeligate;
import com.project.ticketsystem.Database.databaseTable.Location;
import com.project.ticketsystem.Database.databaseTable.user;
import com.project.ticketsystem.Database.mainDatabase.Database;
import com.project.ticketsystem.R;

import java.util.List;

public class BusLocationlist extends AppCompatActivity implements RecyclerDeligate {
    public Database db;
    public LocationCustomAdapter adapter;
    RecyclerView rv;
    TextView backArrow;
    public TextView btnsearch,searcharrow;
    public EditText searchbox;
    public LinearLayout firstlayout,secondlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_locationlist);
        db=Database.getINSTANCE(BusLocationlist.this);
        backArrow=findViewById(R.id.back_locarrow);
        btnsearch=findViewById(R.id.btn_searchlocinfo);
        searcharrow=findViewById(R.id.search_locarrow);
        searchbox=findViewById(R.id.etloc_search);
        firstlayout=findViewById(R.id.firstloclayout);
        secondlayout=findViewById(R.id.secondloclayout);
        rv=findViewById(R.id.loc_rv);
        List<Location> list=db.locationDao().getlocation();
        LinearLayoutManager manager=new LinearLayoutManager(this);
        rv.setLayoutManager(manager);
        rv.setHasFixedSize(true);
        adapter=new LocationCustomAdapter(this,list,this);
        rv.setAdapter(adapter);
        findViewById(R.id.loc_floatbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(BusLocationlist.this, BusStation.class);
                finish();
                startActivity(in);
            }
        });

        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstlayout.setVisibility(View.GONE);
                secondlayout.setVisibility(View.VISIBLE);
            }
        });
        searcharrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondlayout.setVisibility(View.GONE);
                firstlayout.setVisibility(View.VISIBLE);
            }
        });
        searchbox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                List<Location>list=null;
                String t=searchbox.getText().toString();
                if(!t.equalsIgnoreCase("")) {
                    String a = "%" + t + "%";
                    list = db.locationDao().getsearchlocation(a);
                }else{
                    list=db.locationDao().getlocation();
                }
                adapter.AddAppendData(list);
            }

        });
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BusLocationlist.this, AdminPage.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onRecyclerClick(int position) {
        db= Database.getINSTANCE(BusLocationlist.this);
        db.locationDao().deleteloc(position);
        List<Location>list=db.locationDao().getlocation();
        adapter.AddAppendData(list);

    }

    @Override
    public void onRecyclerClick1(int position, String type, int syskey, String name, String time, String price) {

    }

    @Override
    public void onRecyclerClick2(int position) {

    }
}
