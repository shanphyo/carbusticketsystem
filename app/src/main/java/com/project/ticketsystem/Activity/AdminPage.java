package com.project.ticketsystem.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.project.ticketsystem.R;


public class AdminPage extends AppCompatActivity {
    public LinearLayout addCar, carInfo, thirdimage, fourthimage;
    TextView btn_log_out;
    ProgressDialog pd1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);
        carInfo = findViewById(R.id.btn_layout1);


        pd1 = new ProgressDialog(AdminPage.this);
        pd1.setMessage("Please Wait...");

        btn_log_out = findViewById(R.id.btn_log_out);

        btn_log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertBoxCustom1();
            }
        });

        carInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(AdminPage.this, CarTemplateList.class);
                startActivity(in);
            }
        });
        addCar = findViewById(R.id.btn_layout2);
        addCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(AdminPage.this, cartypelist.class);
                startActivity(in);
            }
        });

        thirdimage = findViewById(R.id.btn_layout3);
        thirdimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminPage.this, LoginList.class);
                startActivity(intent);
            }
        });
        fourthimage=findViewById(R.id.btn_layout4);
        fourthimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminPage.this, BusLocationlist.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            AlertBoxCustom();
        }

        return true;
    }

    private void AlertBoxCustom() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AdminPage.this);
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
    private void AlertBoxCustom1() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AdminPage.this);
        builder.setTitle("Alert!");
        builder.setMessage("Do you want to Logout?");
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();

            }
        });

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent in =new Intent(AdminPage.this,Login.class);
                finish();
                startActivity(in);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
