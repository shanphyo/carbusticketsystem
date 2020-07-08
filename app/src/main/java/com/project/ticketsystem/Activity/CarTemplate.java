package com.project.ticketsystem.Activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.project.ticketsystem.Database.databaseTable.car;
import com.project.ticketsystem.Database.databaseTable.carType;
import com.project.ticketsystem.Database.mainDatabase.Database;
import com.project.ticketsystem.R;
import com.project.ticketsystem.Shared.util;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class CarTemplate extends AppCompatActivity implements
        View.OnClickListener {
    public Spinner spFrom, spTo, spCompany, spCarType;
    public Button ctSave;
    TextView txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;
    public Database db;
    public EditText ctPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_template);

        spFrom = findViewById(R.id.ctsp_from);
        spTo = findViewById(R.id.ctsp_to);
        spCompany = findViewById(R.id.ctsp_carName);
        ctPrice = findViewById(R.id.ctet_price);
        txtDate = findViewById(R.id.cttv_date);
        txtTime = findViewById(R.id.cttv_time);
        ctSave = findViewById(R.id.ctbtn_savecarway);

        db = Database.getINSTANCE(CarTemplate.this);
        addStringArrayOnListener();
        txtDate.setOnClickListener(this);
        txtTime.setOnClickListener(this);
        ctSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spFrom.getSelectedItem().toString().equals("Choose Destination")) {
                    Toast.makeText(CarTemplate.this, "Please fill all th information!", Toast.LENGTH_SHORT).show();
                } else if (spTo.getSelectedItem().toString().equals("Choose Destination")) {
                    Toast.makeText(CarTemplate.this, "Please fill all th information!", Toast.LENGTH_SHORT).show();
                } else if (ctPrice.getText().length() == 0) {
                    ctPrice.setError("Please fill all th information!");
                } else if (txtDate.getText().equals("Current Date!(m-d-YYYY)")) {
                    Toast.makeText(CarTemplate.this, "Please fill all th information!", Toast.LENGTH_SHORT).show();
                }else if (txtTime.getText().equals("Time")){
                    Toast.makeText(CarTemplate.this, "Please fill all th information!", Toast.LENGTH_SHORT).show();

                }else {

                    String companname = (spCompany.getSelectedItem().toString());
                    String arr[] = companname.split("-");
                    int carsyskey = db.carDao().getSyskey(arr[0], arr[1]);
                    carType ct = new carType();
                    ct.setCarSyskey(carsyskey);
                    ct.setDate(util.dataToString(txtDate.getText().toString()));
                    ct.setTime(util.twelveTo24(txtTime.getText().toString()));
                    ct.setPrice(ctPrice.getText().toString());
                    ct.setFromcity(spFrom.getSelectedItem().toString());
                    ct.setTocity(spTo.getSelectedItem().toString());
                    db.carTypeDao().addCarType(ct);

                    Intent intent = new Intent(CarTemplate.this,CarTemplateList.class);
                    startActivity(intent);
                    finish();

                }

            }
        });

        //
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.chooseway , R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spFrom.setAdapter(adapter);
        spTo.setAdapter(adapter);
        //

    }

    private void addStringArrayOnListener() {
        List<car> list = db.carDao().getCar();

        List<String> carlist = new LinkedList<>();

        for (int i = 0; i < list.size(); i++) {
            carlist.add(list.get(i).getName() + "-" + list.get(i).getType());

        }


        ArrayAdapter<String> ad = new ArrayAdapter<String>(this,R.layout.spinner_item , carlist);
        ad.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spCompany.setAdapter(ad);

    }

    private void addLisenerOnButton() {


    }

    @Override
    public void onClick(View v) {

        if (v == txtDate) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == txtTime) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            String time;
                            if (hourOfDay < 10) {
                                if (minute < 10) {
                                    time = "0" + hourOfDay + "0" + minute;
                                } else {
                                    time = "0" + hourOfDay + "" + minute;
                                }
                            } else {
                                if (minute < 10) {
                                    time = hourOfDay + "0" + minute;
                                } else {
                                    time = hourOfDay + "" + minute;
                                }
                            }

                            String time1 = util.twentyfourToTwelve(time);
                            txtTime.setText(time1);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }
}
