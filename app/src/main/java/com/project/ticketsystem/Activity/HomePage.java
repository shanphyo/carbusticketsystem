package com.project.ticketsystem.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.project.ticketsystem.Database.databaseTable.user;
import com.project.ticketsystem.Database.mainDatabase.Database;
import com.project.ticketsystem.R;

import java.util.List;

public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public ImageView firstimage, secondimage, thirdimage, fourthimage;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;
    ProgressDialog pd;
    public TextView hfullname,husername,hedifprofile;
    public ImageView hprofile;
    String skey,key;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        pd = new ProgressDialog(this);
        pd.setMessage("Please Wait...");

        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigation_view1);
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
        toggle.syncState();
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.alltext));
        navigationView.setNavigationItemSelectedListener(this);

        firstimage = findViewById(R.id.BusRoutes);
        firstimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, BusRoutes.class);
               intent.putExtra("key","broute");
               intent.putExtra("skey",skey);
                startActivity(intent);
                finish();
            }
        });
        secondimage = findViewById(R.id.terms);
        secondimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, TermsAndCondition.class);

                startActivity(intent);
            }
        });
        thirdimage = findViewById(R.id.FaQs);
        thirdimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, FaQs.class);

                startActivity(intent);
            }
        });
        fourthimage = findViewById(R.id.About);
        fourthimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, About.class);

                startActivity(intent);
            }
        });
        key=getIntent().getStringExtra("key");
        if(key.equalsIgnoreCase("chooseclass")){
            skey=getIntent().getStringExtra("skey");
            db= Database.getINSTANCE(HomePage.this);
            final List<user> list=db.userDao().getheaderProfile(skey);

            if(list.get(0).getImg().equalsIgnoreCase("Noupload")){

            }else{
                hprofile.setImageURI(Uri.parse(list.get(0).getImg()));
            }
            hfullname.setText(list.get(0).getName());
            husername.setText(list.get(0).getEmail());
            hedifprofile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in= new Intent(HomePage.this,EditProfile.class);
                    in.putExtra("key","editprofile");
                    in.putExtra("epname",list.get(0).getName());
                    in.putExtra("eusername",list.get(0).getEmail());
                    in.putExtra("img",list.get(0).getImg());
                    in.putExtra("skey",skey);
                    //finish();
                    startActivity(in);
                }
            });
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {

            case R.id.home:
                Intent intent = new Intent(HomePage.this, HomePage.class);
                intent.putExtra("key","chooseclass");
                intent.putExtra("skey",skey);
                startActivity(intent);
                break;
            case R.id.university:
                Intent intent2 = new Intent(HomePage.this, About.class);
                intent2.putExtra("key","university");
                startActivity(intent2);
                break;
            case R.id.log_out:
                Intent inten4=new Intent(HomePage.this,Login.class);

                finish();
                startActivity(inten4);

                break;

        }

        return false;
    }

    public void onClick(View view) {

        Intent pro = new Intent(getApplicationContext(), UserProfile.class);
        startActivity(pro);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            AlertBoxCustom();
        }

        return true;
    }

    private void AlertBoxCustom() {
        AlertDialog.Builder builder = new AlertDialog.Builder(HomePage.this);
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
