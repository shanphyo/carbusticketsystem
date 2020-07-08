package com.project.ticketsystem.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.project.ticketsystem.Database.databaseTable.booking;
import com.project.ticketsystem.Database.mainDatabase.Database;
import com.project.ticketsystem.R;
import com.project.ticketsystem.Shared.util;

public class customerinfo extends AppCompatActivity {
    public EditText fname, phno, email, nrc;
    public TextView expressname, duringdate, duringtime, nopassenger, toprice, seatno, tfrom, tto,backarrow;
    public Button bookingsave;
    String key, name, date, time, member, noseat, price, from, to, skey;
    Database db;
    TextView backArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerinfo);

        fname = findViewById(R.id.ciet_name);
        db = Database.getINSTANCE(customerinfo.this);
        phno = findViewById(R.id.ciet_phno);
        email = findViewById(R.id.ciet_email);
        nrc = findViewById(R.id.ciet_nrc);
        expressname = findViewById(R.id.citv_type);
        duringdate = findViewById(R.id.citv_date);
        duringtime = findViewById(R.id.citv_time);
        tfrom = findViewById(R.id.citv_from);
        tto = findViewById(R.id.citv_to);
        nopassenger = findViewById(R.id.citv_nopassenger);
        toprice = findViewById(R.id.citv_price);
        seatno = findViewById(R.id.citv_seatno);
        bookingsave = findViewById(R.id.cibtn_saveticket);

        backArrow = findViewById(R.id.back_arrow);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(customerinfo.this, BusRoutes.class);
                intent.putExtra("key","bbr");
                startActivity(intent);
                finish();
            }
        });

        bookingsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (fname.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill all the information!", Toast.LENGTH_SHORT).show();
                } else if (phno.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill all the information!", Toast.LENGTH_SHORT).show();
                } else if (email.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill all the information!", Toast.LENGTH_SHORT).show();
                } else if (nrc.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill all the information!", Toast.LENGTH_SHORT).show();
                } else {
                    saveDataProcess();
                }
            }
        });

        key = getIntent().getStringExtra("key");
        if (key.equalsIgnoreCase("superclass")) {
            name = getIntent().getStringExtra("name");
            date = getIntent().getStringExtra("date");
            time = getIntent().getStringExtra("time");
            member = getIntent().getStringExtra("member");
            noseat = getIntent().getStringExtra("seatno");
            price = getIntent().getStringExtra("price");
            from = getIntent().getStringExtra("from");
            to = getIntent().getStringExtra("to");
            skey = getIntent().getStringExtra("skey");
        }
        String p[] = null;
        double totalprice = 0.0;
        if (price.contains("M")) {
            p = price.split(" M");
            totalprice = (Integer.parseInt(p[0])) * (Integer.parseInt(member));
        } else {
            p = price.split("U");
            totalprice = (Integer.parseInt(p[0])) * (Integer.parseInt(member));
        }
        expressname.setText(name);
        tfrom.setText(from);
        tto.setText(to);
        duringdate.setText(util.stringToDate(date));
        duringtime.setText(util.twentyfourToTwelve(time));
        nopassenger.setText(member);
        toprice.setText(totalprice + "MMk");
        seatno.setText(noseat);

    }

    public void saveDataProcess() {
        booking b = new booking();

        b.setCarTypeSyskey(Integer.parseInt(skey));
        b.setName(fname.getText().toString());
        b.setPno(phno.getText().toString());
        b.setEmail(email.getText().toString());
        b.setNrcNo(nrc.getText().toString());

        b.setWay(name + " (" + from + "-" + to + ")");
        b.setDate(date);
        b.setTime(time);
        b.setNoOfPassenger(member);
        b.setSeatNo(noseat);

        db.bookingDao().addBoking(b);

        Intent in = new Intent(customerinfo.this, Qr.class);
        in.putExtra("key", "qr");
        in.putExtra("name", fname.getText().toString());
        in.putExtra("nrc", nrc.getText().toString());
        in.putExtra("expressname", name);
        in.putExtra("sdate", date);
        in.putExtra("stime", time);
        in.putExtra("seat", noseat);
        in.putExtra("from",from);
        in.putExtra("to",to);
        startActivity(in);
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            AlertBoxCustom();
        }

        return true;
    }

    private void AlertBoxCustom() {
        AlertDialog.Builder builder = new AlertDialog.Builder(customerinfo.this);
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
