package com.project.ticketsystem.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.ticketsystem.Adapter.CarTypelistCustomAdapter;
import com.project.ticketsystem.Adapter.RecyclerDeligate;
import com.project.ticketsystem.Database.databaseTable.car;
import com.project.ticketsystem.Database.mainDatabase.Database;
import com.project.ticketsystem.R;

import java.util.List;

public class cartypelist extends AppCompatActivity implements RecyclerDeligate {
   public Database db;
    public CarTypelistCustomAdapter adapter;
    RecyclerView rv;
    TextView backArrow;
    public TextView searcharrow,btnsearArrow;
    public EditText searchbox;
    public LinearLayout firstlayout,secondlayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartypelist);
        rv=findViewById(R.id.ct_rectangelcartypelist);

        backArrow = findViewById(R.id.back_arrow);
        searcharrow=findViewById(R.id.btn_searchcarway);
        searchbox=findViewById(R.id.et_carsearch);
        btnsearArrow=findViewById(R.id.search_arrow1);
        firstlayout=findViewById(R.id.cartypefirstlayout);
        secondlayout=findViewById(R.id.cartypesecondlayout);
        searcharrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstlayout.setVisibility(View.GONE);
                secondlayout.setVisibility(View.VISIBLE);
            }
        });
        btnsearArrow.setOnClickListener(new View.OnClickListener() {
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
                List<car> list=null;
                String t=searchbox.getText().toString();
                if(!t.equalsIgnoreCase("")) {
                    String a = "%" + t + "%";
                    list = db.carDao().getCarSearch(a);
                }else{
                    list=db.carDao().getCar();
                }
                adapter.AddAppendData(list);
            }

        });

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cartypelist.this, AdminPage.class);
                startActivity(intent);
                finish();
            }
        });

        db=Database.getINSTANCE(cartypelist.this);
        List<car> list=db.carDao().getCar();
        LinearLayoutManager manager=new LinearLayoutManager(this);
        rv.setLayoutManager(manager);
        rv.setHasFixedSize(true);


        adapter=new CarTypelistCustomAdapter(this,list,this);
        rv.setAdapter(adapter);
        findViewById(R.id.ctl_floatbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(cartypelist.this, cartype.class);
                finish();
                startActivity(in);
            }
        });
    }

    @Override
    public void onRecyclerClick(int position) {
        db= Database.getINSTANCE(cartypelist.this);
        db.carTypeDao().deletechildcar(position);
        db.carDao().deletecar(position);
        List<car> list=db.carDao().getCar();
        adapter.AddAppendData(list);
    }

    @Override
    public void onRecyclerClick1(int position, String type, int syskey, String name, String time, String price) {

    }

    @Override
    public void onRecyclerClick2(int position) {

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent(cartypelist.this, AdminPage.class);
            finish();
            startActivity(intent);
        }
        return true;
    }

    private void AlertBoxCustom() {
        AlertDialog.Builder builder = new AlertDialog.Builder(cartypelist.this);
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
