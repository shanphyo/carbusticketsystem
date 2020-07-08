package com.project.ticketsystem.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.project.ticketsystem.Database.databaseTable.booking;
import com.project.ticketsystem.Database.mainDatabase.Database;
import com.project.ticketsystem.R;

import java.util.List;

public class AdminFirstClass extends AppCompatActivity implements View.OnClickListener {

    Button seat1, seat2, seat3, seat4, seat5, seat6, seat7, seat8, seat9, seat10, seat11, seat12;
    Button seat13, seat14, seat15, seat16, seat17, seat18, seat19, seat20;
    public Button btnbuyticket;
    public   String noseat="";
    String cc="";
    String key,passenger,person,name,from,to,date,time,price;
    Database db;
    String a;
    String rbooking="";
    public String rb[]=null;
    public String se[]=null;
    public int count=0;
    public int personcount;
    List<booking>lbooking;
    public TextView backarrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_first_class);
        backarrow=findViewById(R.id.back_arrowadminticket);
        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(AdminFirstClass.this,CarTemplateList.class);
                in.putExtra("key","backarrow");
                finish();
                startActivity(in);
            }
        });

        key=getIntent().getStringExtra("key");
        db=Database.getINSTANCE(AdminFirstClass.this);
        if(key.equalsIgnoreCase("admincartemplatelist")){
            a=getIntent().getStringExtra("s1");
            cc=a;


        }
        connectWithUi();

        btnClickprocess();
        lbooking=db.bookingDao().getRecordlist(a);
        String b="";
        String arr[]=null;
        for(int i=0;i<lbooking.size();i++){
            arr=lbooking.get(i).getSeatNo().split("/");
            for(int j=0;j<arr.length;j++){
                if(!arr[j].equalsIgnoreCase("")) {
                    checkbooking(arr[j]);
                }}
        }









    }
    private void checkbooking(String t){

        if(t.equalsIgnoreCase("A1")){
          //  seat1.setBackgroundColor(Color.parseColor("#F1EFEF"));
            seat1.setBackgroundResource(R.drawable.shape4);
            seat1.setText("B");



            rbooking=rbooking+"A1/";
        }else if(t.equalsIgnoreCase("A2")){
            seat2.setBackgroundResource(R.drawable.shape4);
            seat2.setText("B");
            rbooking=rbooking+"A2/";
        }else if(t.equalsIgnoreCase("A3")){
            seat3.setBackgroundResource(R.drawable.shape4);
            seat3.setText("B");
            rbooking=rbooking+"A3/";
        }else if(t.equalsIgnoreCase("A4")){
            seat4.setBackgroundResource(R.drawable.shape4);
            seat4.setText("B");
            rbooking=rbooking+"A4/";
        }
        else if(t.equalsIgnoreCase("B1")){
            seat5.setBackgroundResource(R.drawable.shape4);
            seat5.setText("B");
            rbooking=rbooking+"B1/";
        }else if(t.equalsIgnoreCase("B2")){
            seat6.setBackgroundResource(R.drawable.shape4);
            seat6.setText("B");
            rbooking=rbooking+"B2/";
        }
        else if(t.equalsIgnoreCase("B3")){
            seat7.setBackgroundResource(R.drawable.shape4);
            seat7.setText("B");
            rbooking=rbooking+"B3/";
        }else if(t.equalsIgnoreCase("B4")){
            seat8.setBackgroundResource(R.drawable.shape4);
            seat8.setText("B");
            rbooking=rbooking+"B4/";
        } else if(t.equalsIgnoreCase("C1")){
            seat9.setBackgroundResource(R.drawable.shape4);
            seat9.setText("B");
            rbooking=rbooking+"C1/";
        }else if(t.equalsIgnoreCase("C2")){
            seat10.setBackgroundResource(R.drawable.shape4);
            seat10.setText("B");
            rbooking=rbooking+"C2/";
        }
        else if(t.equalsIgnoreCase("C3")){
            seat11.setBackgroundResource(R.drawable.shape4);
            seat11.setText("B");
            rbooking=rbooking+"C3/";
        }else if(t.equalsIgnoreCase("C4")){
            seat12.setBackgroundResource(R.drawable.shape4);
            seat12.setText("B");
            rbooking=rbooking+"C4/";
        } else if(t.equalsIgnoreCase("D1")){
            seat13.setBackgroundResource(R.drawable.shape4);
            seat13.setText("B");
            rbooking=rbooking+"D1/";
        }else if(t.equalsIgnoreCase("D2")){
            seat14.setBackgroundResource(R.drawable.shape4);
            seat14.setText("B");
            rbooking=rbooking+"D2/";
        }
        else if(t.equalsIgnoreCase("D3")){
            seat15.setBackgroundResource(R.drawable.shape4);
            seat15.setText("B");
            rbooking=rbooking+"D3/";
        }else if(t.equalsIgnoreCase("D4")){
            seat16.setBackgroundResource(R.drawable.shape4);
            seat16.setText("B");
            rbooking=rbooking+"D4/";
        } else if(t.equalsIgnoreCase("E1")){
            seat17.setBackgroundResource(R.drawable.shape4);
            seat17.setText("B");
            rbooking=rbooking+"E1/";
        }else if(t.equalsIgnoreCase("E2")){
            seat18.setBackgroundResource(R.drawable.shape4);
            seat18.setText("B");
            rbooking=rbooking+"E2/";
        }
        else if(t.equalsIgnoreCase("E3")){
            seat19.setBackgroundResource(R.drawable.shape4);
            seat19.setText("B");
            rbooking=rbooking+"E3/";
        }else if(t.equalsIgnoreCase("E4")){
            seat20.setBackgroundResource(R.drawable.shape4);
            seat20.setText("B");
            rbooking=rbooking+"E4/";
        }

    }


    private void btnClickprocess() {
        seat1.setOnClickListener(this);
        seat2.setOnClickListener(this);
        seat3.setOnClickListener(this);
      //  seat4.setOnClickListener(this);
        seat5.setOnClickListener(this);
        seat6.setOnClickListener(this);
        seat7.setOnClickListener(this);
       // seat8.setOnClickListener(this);
        seat9.setOnClickListener(this);
        seat10.setOnClickListener(this);
        seat11.setOnClickListener(this);
       // seat12.setOnClickListener(this);
        seat13.setOnClickListener(this);
        seat14.setOnClickListener(this);
        seat15.setOnClickListener(this);
       // seat16.setOnClickListener(this);
        seat17.setOnClickListener(this);
        seat18.setOnClickListener(this);
        seat19.setOnClickListener(this);
       // seat20.setOnClickListener(this);

    }

    private void connectWithUi() {
        seat1 = findViewById(R.id.fcseat1);
        seat2 = findViewById(R.id.fcseat2);
        seat3 = findViewById(R.id.fcseat3);
       // seat4 = findViewById(R.id.aseat4);
        seat5 = findViewById(R.id.fcseat5);
        seat6 = findViewById(R.id.fcseat6);
        seat7 = findViewById(R.id.fcseat7);
        //seat8 = findViewById(R.id.aseat8);
        seat9 = findViewById(R.id.fcseat9);
        seat10 = findViewById(R.id.fcseat10);
        seat11 = findViewById(R.id.fcseat11);
       // seat12 = findViewById(R.id.fcseat12);
        seat13 = findViewById(R.id.fcseat13);
        seat14 = findViewById(R.id.fcseat14);
        seat15 = findViewById(R.id.fcseat15);
     //   seat16 = findViewById(R.id.aseat16);
        seat17 = findViewById(R.id.fcseat17);
        seat18 = findViewById(R.id.fcseat18);
        seat19 = findViewById(R.id.fcseat19);
     //   seat20 = findViewById(R.id.aseat20);

    }
    public void sendkey(String a){
        for(int i=0;i<lbooking.size();i++){
            if(lbooking.get(i).getSeatNo().contains(a)){
                Intent in=new Intent(AdminFirstClass.this,infoCustomer.class);
                in.putExtra("key","info");
                in.putExtra("name",lbooking.get(i).getName());
                in.putExtra("email",lbooking.get(i).getEmail());
                in.putExtra("nrc",lbooking.get(i).getNrcNo());
                in.putExtra("phone",lbooking.get(i).getPno());
                in.putExtra("seatno",lbooking.get(i).getSeatNo());
                in.putExtra("sskey",cc);
                in.putExtra("tt","3");
                startActivity(in);
                break;

            }

        }
    }

    @Override
    public void onClick(View view) {
        Intent in;
        if(view.getId() == R.id.fcseat1){
            sendkey("A1");

        }else if(view.getId() == R.id.fcseat2){
            sendkey("A2");

        }else if(view.getId() == R.id.fcseat3){
            sendkey("A3");
       /* }else if(view.getId() == R.id.aseat4){
            sendkey("A4");*/
        }else if(view.getId() == R.id.fcseat5){
            sendkey("B1");
        }else if(view.getId() == R.id.fcseat6){
            sendkey("B2");
        }else if(view.getId() == R.id.fcseat7){
            sendkey("B3");
       /* }else if(view.getId() == R.id.fcseat8){
            sendkey("B4");*/
        }else if(view.getId() == R.id.fcseat9){
            sendkey("C1");

        }else if(view.getId() == R.id.fcseat10){
            sendkey("C2");

        }else if(view.getId() == R.id.fcseat11){
            sendkey("C3");

   /*     }else if(view.getId() == R.id.aseat12){
            sendkey("C4");
*/
        }else if(view.getId() == R.id.fcseat13){
            sendkey("D1");

        }else if(view.getId() == R.id.fcseat14){
            sendkey("D2");

        }else if(view.getId() == R.id.fcseat15){
            sendkey("D3");

        /*}else if(view.getId() == R.id.aseat16){
            sendkey("D4");*/

        }else if(view.getId() == R.id.fcseat17){
            sendkey("E1");

        }else if(view.getId() == R.id.fcseat18){
            sendkey("E2");

        }else if(view.getId() == R.id.fcseat19){
            sendkey("E3");

      /*  }else if(view.getId() == R.id.aseat20){
            sendkey("E4");*/

        }





    }











}
