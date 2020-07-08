package com.project.ticketsystem.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.project.ticketsystem.Database.databaseTable.Location;
import com.project.ticketsystem.Database.databaseTable.car;
import com.project.ticketsystem.Database.databaseTable.carType;
import com.project.ticketsystem.Database.mainDatabase.Database;
import com.project.ticketsystem.R;
import com.project.ticketsystem.Shared.util;

import java.util.ArrayList;
import java.util.List;

import ahmed.easyslider.EasySlider;
import ahmed.easyslider.SliderItem;

public class watchView extends AppCompatActivity {
    Database db;
    public TextView wprice,wdepaturetime,wduration,wplace,wdropplace,wservice,fromloc,toloc;
    String key,skey;
    List<carType>list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_view);
        wprice=findViewById(R.id.wprice);
        wdepaturetime=findViewById(R.id.wdtime);
        wduration=findViewById(R.id.wd);
        wplace=findViewById(R.id.wplace);
        wservice=findViewById(R.id.wservice);
        wdropplace=findViewById(R.id.wdroplace);
        fromloc=findViewById(R.id.tv_bordingplace);
        toloc=findViewById(R.id.tv_droppingplace);
        db= Database.getINSTANCE(watchView.this);
        setUpSlider();
        key=getIntent().getStringExtra("key");
        if(key.equalsIgnoreCase("watchview")){
            skey=getIntent().getStringExtra("skey");
           list=db.carTypeDao().getwatchcar(skey);
            wprice.setText("Price-"+list.get(0).getPrice()+"MMK");
            wdepaturetime.setText("Depature Time :"+ util.twentyfourToTwelve(list.get(0).getTime()));
            wduration.setText("Approximately 10 hours");
            fromloc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    List<car>carlist=db.carDao().getcarName(list.get(0).getCarSyskey());
                    List<Location>loc=db.locationDao().getspeciallocation(carlist.get(0).getName(),list.get(0).getFromcity());
                    seeLoc(loc.get(0).getLocationName(),loc.get(0).getLatitute(),loc.get(0).getLongitute());
                }
            });
            toloc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    List<car>carlist=db.carDao().getcarName(list.get(0).getCarSyskey());
                    List<Location>loc=db.locationDao().getspeciallocation(carlist.get(0).getName(),list.get(0).getTocity());
                    seeLoc(loc.get(0).getLocationName(),loc.get(0).getLatitute(),loc.get(0).getLongitute());
                }
            });
            if(list.get(0).getFromcity().equalsIgnoreCase("Yangon")){
                wplace.setText("Boarding Place : Aung Mingalar Bus Station");
            }else if(list.get(0).getFromcity().equalsIgnoreCase("Mandalay")){
                wplace.setText("Boarding Place : Kywesekan Bus Station");
            }else if(list.get(0).getFromcity().equalsIgnoreCase("Taunggyi")){
                wplace.setText("Boarding Place : Taunggyi Bus Station");
            }else if(list.get(0).getFromcity().equalsIgnoreCase("Bagan")){
                wplace.setText("Boarding Place : Bagan Bus Station");
            }else if(list.get(0).getFromcity().equalsIgnoreCase("Naypyitaw")){
                wplace.setText("Boarding Place :  Naypyitaw Bus Station");
            }
            if(list.get(0).getTocity().equalsIgnoreCase("Yangon")){
                wdropplace.setText("Dropping Place : Aung Mingalar Bus Station");
            }else if(list.get(0).getTocity().equalsIgnoreCase("Mandalay")){
                wdropplace.setText("Dropping Place : Kywesekan Bus Station");
            }else if(list.get(0).getTocity().equalsIgnoreCase("Taunggyi")){
                wdropplace.setText("Dropping Place : Taunggyi Bus Station");
            }else if(list.get(0).getTocity().equalsIgnoreCase("Bagan")){
                wdropplace.setText("Dropping Place : Bagan Bus Station");
            }else if(list.get(0).getTocity().equalsIgnoreCase("Naypyitaw")){
                wdropplace.setText("Dropping Place :  Naypyitaw Bus Station");
            }
            wservice.setText("Service :  Purified drinking water,  \n Snow Towel,Blanket,Medicine");
        }



    }
    public void setUpSlider() {
        EasySlider easySlider = findViewById(R.id.slider);

        List<SliderItem> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItem("page1", R.drawable.c6));
        sliderItems.add(new SliderItem("page2", R.drawable.c3));
        sliderItems.add(new SliderItem("page3", R.drawable.c4));
        sliderItems.add(new SliderItem("page4", R.drawable.c5));


        easySlider.setPages(sliderItems);
    }

    public void seeLoc(String locname,double lat,double lon){
        double latitute=lat;
        double longitude=lon;
        String label=locname;
        String uriBegin="geo:"+latitute+","+longitude;
        String query=latitute+","+longitude+"("+label+")";
        String encodeQuery= Uri.encode(query);
        String uriString=uriBegin+"?q="+encodeQuery+"&z=16";
        Uri uri=Uri.parse(uriString);
        Intent in=new Intent(android.content.Intent.ACTION_VIEW,uri);
        startActivity(in);
    }


}
