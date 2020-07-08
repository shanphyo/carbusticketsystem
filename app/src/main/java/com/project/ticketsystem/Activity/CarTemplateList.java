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

import com.project.ticketsystem.Adapter.CarTemplatelistCustomAdapter1;
import com.project.ticketsystem.Adapter.RecyclerDeligate;
import com.project.ticketsystem.Database.Dao.carTypeDao;
import com.project.ticketsystem.Database.mainDatabase.Database;
import com.project.ticketsystem.R;

import java.util.List;

public class CarTemplateList extends AppCompatActivity implements RecyclerDeligate {
    public Database db;
    public CarTemplatelistCustomAdapter1 adapter;
    RecyclerView rv;
    TextView backArrow;
    public TextView btnsearch,searcharrow;
    public EditText searchbox;
    public LinearLayout firstlayout,secondlayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_template_list);

        backArrow = findViewById(R.id.back_arrow);
        btnsearch=findViewById(R.id.btn_searchcarinfo);
        searcharrow=findViewById(R.id.search_arrow);
        searchbox=findViewById(R.id.et_search);
        firstlayout=findViewById(R.id.firstlayout);
        secondlayout=findViewById(R.id.secondlayout);
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
                List<carTypeDao.junctioncar> list=null;
                String t=searchbox.getText().toString();
                if(!t.equalsIgnoreCase("")) {
                    String a = "%" + t + "%";
                    list = db.carTypeDao().getSearchData(a);
                }else{
                    list=db.carTypeDao().getJunctioncar();
                }
                adapter.AddAppendData(list);
            }

        });

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CarTemplateList.this, AdminPage.class);
                startActivity(intent);
                finish();
            }
        });

        db=Database.getINSTANCE(CarTemplateList.this);
        rv=findViewById(R.id.ctemp_rv);
        List<carTypeDao.junctioncar> list=db.carTypeDao().getJunctioncar();
        LinearLayoutManager manager=new LinearLayoutManager(this);
        rv.setLayoutManager(manager);
        rv.setHasFixedSize(true);
        adapter=new CarTemplatelistCustomAdapter1(this,list,this);
        rv.setAdapter(adapter);
        findViewById(R.id.ctemlist_floatbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(CarTemplateList.this, CarTemplate.class);
                finish();
                startActivity(in);
            }
        });
    }

    @Override
    public void onRecyclerClick(int position) {
        db= Database.getINSTANCE(CarTemplateList.this);
        db.carTypeDao().deletecar(position);
        List<carTypeDao.junctioncar> list=db.carTypeDao().getJunctioncar();
        adapter.AddAppendData(list);
    }

    @Override
    public void onRecyclerClick1(int position, String type,int syskey,String name,String time,String price) {
        Intent in;

        if(type.equalsIgnoreCase("(2+2)")) {
            in = new Intent(CarTemplateList.this, Adminsuper.class);

        }else{
            in = new Intent(CarTemplateList.this, AdminFirstClass.class);
        }
        String a=syskey+"";
        in.putExtra("key","admincartemplatelist");
        in.putExtra("s1",a);
        startActivity(in);
    }

    @Override
    public void onRecyclerClick2(int position) {
        Intent in=new Intent(CarTemplateList.this, watchView.class);
        in.putExtra("key","watchview");
        in.putExtra("skey",position+"");
        startActivity(in);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent(CarTemplateList.this, AdminPage.class);
            finish();
            startActivity(intent);
        }
        return true;
    }

    private void AlertBoxCustom() {
        AlertDialog.Builder builder = new AlertDialog.Builder(CarTemplateList.this);
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
