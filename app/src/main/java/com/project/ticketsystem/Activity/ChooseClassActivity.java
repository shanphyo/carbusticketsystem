package com.project.ticketsystem.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import com.project.ticketsystem.Adapter.RecCustomAdaptor;
import com.project.ticketsystem.Adapter.RecyclerDeligate;
import com.project.ticketsystem.Database.databaseTable.user;
import com.project.ticketsystem.Database.mainDatabase.Database;
import com.project.ticketsystem.R;
import com.project.ticketsystem.Shared.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ChooseClassActivity extends AppCompatActivity implements RecyclerDeligate, NavigationView.OnNavigationItemSelectedListener,View.OnClickListener{

    RecyclerView rv;
    RecCustomAdaptor adaptor;
    ArrayList<String> classNameList;
    ArrayList<Integer> ImageList;
    ProgressDialog pd;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;
    //editprifile
    public TextView hfullname,husername,hedifprofile;
    public ImageView hprofile;
    //
    String key,username,fulname,skey,img;
    Database db;
    public Spinner ctype,cpassenger,cfrom,cto,cnumber;
    public TextView cdate;
    public Button btnsearch;
    private int mYear, mMonth, mDay;


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_class);


        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigation_view);
        toolbar = findViewById(R.id.toobar);
        View headerView = navigationView.getHeaderView(0);
        hfullname=headerView.findViewById(R.id.h_fullname);
        hprofile=headerView.findViewById(R.id.h_profile);

        husername=headerView.findViewById(R.id.h_email);
        hedifprofile=headerView.findViewById(R.id.h_editprofile);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.alltext));
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        key=getIntent().getStringExtra("key");
        if(key.equalsIgnoreCase("loginuser")){
            fulname=getIntent().getStringExtra("ename");
            username=getIntent().getStringExtra("eusername");
            skey=getIntent().getStringExtra("skey");
            img=getIntent().getStringExtra("img");
        }else if(key.equalsIgnoreCase("update")){
            skey=getIntent().getStringExtra("skey");
        }


        db=Database.getINSTANCE(ChooseClassActivity.this);
        List<user> list=db.userDao().getheaderProfile(skey);

        if(list.get(0).getImg().equalsIgnoreCase("Noupload")){

        }else{
            hprofile.setImageURI(Uri.parse(list.get(0).getImg()));
        }
        hfullname.setText(list.get(0).getName());
        husername.setText(list.get(0).getEmail());
        hedifprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in= new Intent(ChooseClassActivity.this,EditProfile.class);
                in.putExtra("key","editprofile");
                in.putExtra("epname",hfullname.getText().toString());
                in.putExtra("eusername",username);
                in.putExtra("img",img);
                in.putExtra("skey",skey);
                //finish();
                startActivity(in);
            }
        });

        //for busroutes
        ctype=findViewById(R.id.ccsp_type);
        cpassenger=findViewById(R.id.ccsp_passenger);
        cfrom=findViewById(R.id.ccsp_from);
        cto=findViewById(R.id.ccsp_To);
        cnumber=findViewById(R.id.ccsp_person);
        cdate=findViewById(R.id.cctv_date);
        btnsearch=findViewById(R.id.ccbtn_searchcarway);
        //
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.chooseway , R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        cfrom.setAdapter(adapter);
        cto.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.type , R.layout.spinner_item);
        adapter2.setDropDownViewResource(R.layout.spinner_dropdown_item);
        ctype.setAdapter(adapter2);

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.passenger , R.layout.spinner_item);
        adapter3.setDropDownViewResource(R.layout.spinner_dropdown_item);
        cpassenger.setAdapter(adapter3);

        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,
                R.array.person , R.layout.spinner_item);
        adapter4.setDropDownViewResource(R.layout.spinner_dropdown_item);
        cnumber.setAdapter(adapter4);

        //
        cdate.setOnClickListener(this);
        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ctype.getSelectedItem().toString().equals("Type")) {
                    Toast.makeText(ChooseClassActivity.this, "Please fill all the information", Toast.LENGTH_SHORT).show();
                } else if (cpassenger.getSelectedItem().toString().equals("Passenger")) {
                    Toast.makeText(ChooseClassActivity.this, "Please fill all the information", Toast.LENGTH_SHORT).show();
                } else if (cfrom.getSelectedItem().toString().equals("Choose Destination")) {
                    Toast.makeText(ChooseClassActivity.this, "Please fill all the information", Toast.LENGTH_SHORT).show();
                } else if (cto.getSelectedItem().toString().equals("Choose Destination")) {
                    Toast.makeText(ChooseClassActivity.this, "Please fill all the information", Toast.LENGTH_SHORT).show();
                } else if (cnumber.getSelectedItem().toString().equals("number")) {
                    Toast.makeText(ChooseClassActivity.this, "Please fill all the information", Toast.LENGTH_SHORT).show();
                } else if (cdate.getText().equals("Current Date!(m-d-YYYY)")) {
                    Toast.makeText(ChooseClassActivity.this, "Please fill all the information", Toast.LENGTH_SHORT).show();
                } else {
                    Intent in = new Intent(ChooseClassActivity.this, BusList.class);
                    in.putExtra("key", "buslist");
                    in.putExtra("brtype", ctype.getSelectedItem().toString());
                    in.putExtra("brpassenger", cpassenger.getSelectedItem().toString());
                    in.putExtra("brfrom", cfrom.getSelectedItem().toString());
                    in.putExtra("brto", cto.getSelectedItem().toString());
                    in.putExtra("brperson", cnumber.getSelectedItem().toString());
                    in.putExtra("brdate", util.dataToString(cdate.getText().toString()));
                    startActivity(in);
                }

            }
        });

        //





    }



    @Override
    public void onRecyclerClick(int position) {



    }

    @Override
    public void onRecyclerClick1(int position, String type, int syskey, String name, String time, String price) {

    }

    @Override
    public void onRecyclerClick2(int position) {

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {

            case R.id.home:

                Intent in=new Intent(ChooseClassActivity.this,HomePage.class);
                in.putExtra("key","chooseclass");
                in.putExtra("skey",skey);

                startActivity(in);

                break;
            case R.id.university:
                Intent intent2 = new Intent(ChooseClassActivity.this, About.class);
                intent2.putExtra("key","university");
                startActivity(intent2);
                break;
            case R.id.log_out:
                Intent inten4=new Intent(ChooseClassActivity.this,Login.class);

                finish();
                startActivity(inten4);

                break;

        }

        return false;
    }

    @Override
    public void onClick(View v) {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        cdate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
}
