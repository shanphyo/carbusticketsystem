package com.project.ticketsystem.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.project.ticketsystem.R;

public class infoCustomer extends AppCompatActivity {
    public String iname,iemail,iphone,inrc,iseatno,key,tt;
    public TextView sname,semail,sphone,snrc,sseatno;
    public TextView backbtn;
    String b="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_customer);
        sname=findViewById(R.id.admin_name);
        semail=findViewById(R.id.admin_email);
        sphone=findViewById(R.id.admin_phone);
        snrc=findViewById(R.id.admin_nrc);
        sseatno=findViewById(R.id.admin_seatno);
        key=getIntent().getStringExtra("key");
        backbtn=findViewById(R.id.back_arrowcustomerinfo);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent in = new Intent(infoCustomer.this, Adminsuper.class);
                    in.putExtra("key","admincartemplatelist");
                    in.putExtra("s1",b);
                    finish();
                    startActivity(in);
                }catch (Exception e){
                    System.out.println("The exception is="+e.getMessage());
                }
            }
        });
        if(key.equalsIgnoreCase("info")){
            sname.setText(getIntent().getStringExtra("name"));
            semail.setText(getIntent().getStringExtra("email"));
            sphone.setText(getIntent().getStringExtra("phone"));
            snrc.setText(getIntent().getStringExtra("nrc"));
            sseatno.setText(getIntent().getStringExtra("seatno"));
            b=getIntent().getStringExtra("sskey");
            tt=getIntent().getStringExtra("tt");
        }

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){

        if(keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent=null;
            if(tt.equalsIgnoreCase("3")){
                intent = new Intent(infoCustomer.this, AdminFirstClass.class);
            }else {
                 intent = new Intent(infoCustomer.this, Adminsuper.class);
            }
            intent.putExtra("key","admincartemplatelist");
            intent.putExtra("s1",b);
            finish();
            startActivity(intent);
        }
        return true;
    }
}
