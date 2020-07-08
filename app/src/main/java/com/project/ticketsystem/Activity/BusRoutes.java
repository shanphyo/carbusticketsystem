package com.project.ticketsystem.Activity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.project.ticketsystem.R;
import com.project.ticketsystem.Shared.util;

import java.util.Calendar;

public class BusRoutes extends AppCompatActivity implements View.OnClickListener {
    public Spinner choosetype, passenger, chooseFrom, chooseTo, chooseperson;

    public TextView date;
    TextView backArrow;
    public Button search;
    private int mYear, mMonth, mDay;
    String key1,skey1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_routes);
        choosetype = findViewById(R.id.brsp_type);
        passenger = findViewById(R.id.brsp_passenger);
        chooseFrom = findViewById(R.id.brsp_from);
        chooseTo = findViewById(R.id.brsp_To);
        chooseperson = findViewById(R.id.brsp_person);
        date = findViewById(R.id.brtv_date);
        search = findViewById(R.id.brbtn_searchcarway);
        backArrow = findViewById(R.id.back_arrow1);
        key1=getIntent().getStringExtra("key");
        if(key1.equalsIgnoreCase("broute")){
            skey1=getIntent().getStringExtra("skey");
        }

    //
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.chooseway , R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        chooseFrom.setAdapter(adapter);
        chooseTo.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.type , R.layout.spinner_item);
        adapter2.setDropDownViewResource(R.layout.spinner_dropdown_item);
        choosetype.setAdapter(adapter2);

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.passenger , R.layout.spinner_item);
        adapter3.setDropDownViewResource(R.layout.spinner_dropdown_item);
        passenger.setAdapter(adapter3);

        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,
                R.array.person , R.layout.spinner_item);
        adapter4.setDropDownViewResource(R.layout.spinner_dropdown_item);
        chooseperson.setAdapter(adapter4);


        //

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BusRoutes.this, HomePage.class);
                intent.putExtra("key","chooseclass");
                intent.putExtra("skey",skey1);
                startActivity(intent);
                finish();
            }
        });

        date.setOnClickListener(this);


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (choosetype.getSelectedItem().toString().equals("Type")) {
                    Toast.makeText(BusRoutes.this, "Please fill all the information", Toast.LENGTH_SHORT).show();
                } else if (passenger.getSelectedItem().toString().equals("Passenger")) {
                    Toast.makeText(BusRoutes.this, "Please fill all the information", Toast.LENGTH_SHORT).show();
                } else if (chooseFrom.getSelectedItem().toString().equals("Choose Destination")) {
                    Toast.makeText(BusRoutes.this, "Please fill all the information", Toast.LENGTH_SHORT).show();
                } else if (chooseTo.getSelectedItem().toString().equals("Choose Destination")) {
                    Toast.makeText(BusRoutes.this, "Please fill all the information", Toast.LENGTH_SHORT).show();
                } else if (chooseperson.getSelectedItem().toString().equals("number")) {
                    Toast.makeText(BusRoutes.this, "Please fill all the information", Toast.LENGTH_SHORT).show();
                } else if (date.getText().equals("Current Date!(m-d-YYYY)")) {
                    Toast.makeText(BusRoutes.this, "Please fill all the information", Toast.LENGTH_SHORT).show();
                } else {
                    Intent in = new Intent(BusRoutes.this, BusList.class);
                    in.putExtra("key", "buslist");
                    in.putExtra("brtype", choosetype.getSelectedItem().toString());
                    in.putExtra("brpassenger", passenger.getSelectedItem().toString());
                    in.putExtra("brfrom", chooseFrom.getSelectedItem().toString());
                    in.putExtra("brto", chooseTo.getSelectedItem().toString());
                    in.putExtra("brperson", chooseperson.getSelectedItem().toString());
                    in.putExtra("brdate", util.dataToString(date.getText().toString()));
                    startActivity(in);
                    finish();
                }
            }
        });
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

                        date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            AlertBoxCustom();
        }

        return true;
    }

    private void AlertBoxCustom() {
        AlertDialog.Builder builder = new AlertDialog.Builder(BusRoutes.this);
        builder.setTitle("Alert!");
        builder.setMessage("Do you want to exit?");
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();

            }
        });

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
