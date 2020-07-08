package com.project.ticketsystem.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.project.ticketsystem.Database.databaseTable.Location;
import com.project.ticketsystem.Database.databaseTable.car;
import com.project.ticketsystem.Database.mainDatabase.Database;
import com.project.ticketsystem.R;

import java.util.LinkedList;
import java.util.List;

public class BusStation extends AppCompatActivity {
    public Spinner carname,province;
    public EditText locname,lat,lon;
    public Button saveloc;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_station);
        db = Database.getINSTANCE(BusStation.this);
        carname=findViewById(R.id.locsp_carName);
        locname=findViewById(R.id.locet_locname);
        lat=findViewById(R.id.locet_lat);
        lon=findViewById(R.id.locet_lon);
        province=findViewById(R.id.locsp_pro);

        saveloc=findViewById(R.id.locbtn_savelocation);
        addStringArrayOnListener();
        saveloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveLocation();
            }
        });

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.chooseway , R.layout.spinner_item);
        adapter2.setDropDownViewResource(R.layout.spinner_dropdown_item);
        province.setAdapter(adapter2);

    }
    public void saveLocation(){
        String companname = (carname.getSelectedItem().toString());
        Location l=new Location();
        double la=Double.parseDouble(lat.getText().toString());
        double lo=Double.parseDouble(lon.getText().toString());
        l.setCarname(companname);
        l.setLocationName(locname.getText().toString());
        l.setLatitute(la);
        l.setLongitute(lo);
        l.setProvince(province.getSelectedItem().toString());
        db.locationDao().addLocation(l);
        Intent i=new Intent(BusStation.this,BusLocationlist.class);
        finish();
        startActivity(i);

    }

    private void addStringArrayOnListener() {
        List<car> list = db.carDao().getCar();

        List<String> carlist = new LinkedList<>();

        for (int i = 0; i < list.size(); i++) {
            carlist.add(list.get(i).getName()) ;

        }


        ArrayAdapter<String> ad = new ArrayAdapter<String>(this, R.layout.spinner_item, carlist);
        ad.setDropDownViewResource(R.layout.spinner_dropdown_item);
        carname.setAdapter(ad);

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent ii=new Intent(BusStation.this,BusLocationlist.class);
            finish();
            startActivity(ii);
        }

        return true;
    }
}
