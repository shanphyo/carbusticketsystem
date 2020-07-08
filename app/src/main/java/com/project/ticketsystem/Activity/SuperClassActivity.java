package com.project.ticketsystem.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.project.ticketsystem.Database.databaseTable.booking;
import com.project.ticketsystem.Database.mainDatabase.Database;
import com.project.ticketsystem.R;

import java.util.List;

public class SuperClassActivity extends AppCompatActivity implements View.OnClickListener {

    Button seat1, seat2, seat3, seat4, seat5, seat6, seat7, seat8, seat9, seat10, seat11, seat12;
    Button seat13, seat14, seat15, seat16, seat17, seat18, seat19, seat20, seat21, seat22;
    public Button btnbuyticket;
    public   String noseat="";
    String key,passenger,person,name,from,to,date,time,price;
    Database db;
    String a;
    String rbooking="";
    public String rb[]=null;
    public String se[]=null;
    public int count=0;
    public int personcount;
    TextView backArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.super_class_activity);
        btnbuyticket=findViewById(R.id.buyticket);

        backArrow = findViewById(R.id.back_arrow);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SuperClassActivity.this,BusRoutes.class);
                intent.putExtra("key","sup");
                startActivity(intent);
                finish();
            }
        });

        key=getIntent().getStringExtra("key");
        db=Database.getINSTANCE(SuperClassActivity.this);
        if(key.equalsIgnoreCase("buslist")){
            a=getIntent().getStringExtra("s1");
            passenger=getIntent().getStringExtra("passenger");
            person=getIntent().getStringExtra("person");
            name=getIntent().getStringExtra("carname");
            to=getIntent().getStringExtra("to");
            date=getIntent().getStringExtra("date");
            from=getIntent().getStringExtra("from");
            price=getIntent().getStringExtra("price");
            time=getIntent().getStringExtra("time");
            personcount=Integer.parseInt(person);
        }
        connectWithUi();

        btnClickprocess();
        List<booking>lbooking=db.bookingDao().getRecordlist(a);
        String b="";
        String arr[]=null;
        for(int i=0;i<lbooking.size();i++){
            arr=lbooking.get(i).getSeatNo().split("/");
            for(int j=0;j<arr.length;j++){
            if(!arr[j].equalsIgnoreCase("")) {
                checkbooking(arr[j]);
            }}
        }



            btnbuyticket.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(SuperClassActivity.this,customerinfo.class);
                    intent.putExtra("key","superclass");
                    intent.putExtra("name",name);
                    intent.putExtra("date",date);
                    intent.putExtra("time",time);
                    intent.putExtra("member",person);
                    intent.putExtra("seatno",noseat);
                    intent.putExtra("price",price);
                    intent.putExtra("from",from);
                    intent.putExtra("to",to);
                    intent.putExtra("skey",a);
                    startActivity(intent);
                    finish();
                }
            });





    }
    private void checkbooking(String t){

        if(t.equalsIgnoreCase("A1")){
            seat1.setBackgroundResource(R.drawable.shape4);
            //seat1.setText("B");
            rbooking=rbooking+"A1/";
        }else if(t.equalsIgnoreCase("A2")){
            seat2.setBackgroundResource(R.drawable.shape4);
            //seat2.setText("B");
            rbooking=rbooking+"A2/";
        }else if(t.equalsIgnoreCase("A3")){
            seat3.setBackgroundResource(R.drawable.shape4);
            //seat3.setText("B");
            rbooking=rbooking+"A3/";
        }else if(t.equalsIgnoreCase("A4")){
            seat4.setBackgroundResource(R.drawable.shape4);
            //seat4.setText("B");
            rbooking=rbooking+"A4/";
        }
        else if(t.equalsIgnoreCase("B1")){
            seat5.setBackgroundResource(R.drawable.shape4);
            //seat5.setText("B");
            rbooking=rbooking+"B1/";
        }else if(t.equalsIgnoreCase("B2")){
            seat6.setBackgroundResource(R.drawable.shape4);
           // seat6.setText("B");
            rbooking=rbooking+"B2/";
        }
        else if(t.equalsIgnoreCase("B3")){
            seat7.setBackgroundResource(R.drawable.shape4);
            //seat7.setText("B");
            rbooking=rbooking+"B3/";
        }else if(t.equalsIgnoreCase("B4")){
            seat8.setBackgroundResource(R.drawable.shape4);
           // seat8.setText("B");
            rbooking=rbooking+"B4/";
        } else if(t.equalsIgnoreCase("C1")){
            seat9.setBackgroundResource(R.drawable.shape4);
            //seat9.setText("B");
            rbooking=rbooking+"C1/";
        }else if(t.equalsIgnoreCase("C2")){
            seat10.setBackgroundResource(R.drawable.shape4);
            //seat10.setText("B");
            rbooking=rbooking+"C2/";
        }
        else if(t.equalsIgnoreCase("C3")){
            seat11.setBackgroundResource(R.drawable.shape4);
            //seat11.setText("B");
            rbooking=rbooking+"C3/";
        }else if(t.equalsIgnoreCase("C4")){
            seat12.setBackgroundResource(R.drawable.shape4);
           // seat12.setText("B");
            rbooking=rbooking+"C4/";
        } else if(t.equalsIgnoreCase("D1")){
            seat13.setBackgroundResource(R.drawable.shape4);
            //seat13.setText("B");
            rbooking=rbooking+"D1/";
        }else if(t.equalsIgnoreCase("D2")){
            seat14.setBackgroundResource(R.drawable.shape4);
            //seat14.setText("B");
            rbooking=rbooking+"D2/";
        }
        else if(t.equalsIgnoreCase("D3")){
            seat15.setBackgroundResource(R.drawable.shape4);
            //seat15.setText("B");
            rbooking=rbooking+"D3/";
        }else if(t.equalsIgnoreCase("D4")){
            seat16.setBackgroundResource(R.drawable.shape4);
           // seat16.setText("B");
            rbooking=rbooking+"D4/";
        } else if(t.equalsIgnoreCase("E1")){
            seat17.setBackgroundResource(R.drawable.shape4);
            //seat17.setText("B");
            rbooking=rbooking+"E1/";
        }else if(t.equalsIgnoreCase("E2")){
            seat18.setBackgroundResource(R.drawable.shape4);
           // seat18.setText("B");
            rbooking=rbooking+"E2/";
        }
        else if(t.equalsIgnoreCase("E3")){
            seat19.setBackgroundResource(R.drawable.shape4);
            //seat19.setText("B");
            rbooking=rbooking+"E3/";
        }else if(t.equalsIgnoreCase("E4")){
            seat20.setBackgroundResource(R.drawable.shape4);
            //seat20.setText("B");
            rbooking=rbooking+"E4/";
        }

    }


    private void btnClickprocess() {
        seat1.setOnClickListener(this);
        seat2.setOnClickListener(this);
        seat3.setOnClickListener(this);
        seat4.setOnClickListener(this);
        seat5.setOnClickListener(this);
        seat6.setOnClickListener(this);
        seat7.setOnClickListener(this);
        seat8.setOnClickListener(this);
        seat9.setOnClickListener(this);
        seat10.setOnClickListener(this);
        seat11.setOnClickListener(this);
        seat12.setOnClickListener(this);
        seat13.setOnClickListener(this);
        seat14.setOnClickListener(this);
        seat15.setOnClickListener(this);
        seat16.setOnClickListener(this);
        seat17.setOnClickListener(this);
        seat18.setOnClickListener(this);
        seat19.setOnClickListener(this);
        seat20.setOnClickListener(this);

    }

    private void connectWithUi() {
        seat1 = findViewById(R.id.seat1);
        seat2 = findViewById(R.id.seat2);
        seat3 = findViewById(R.id.seat3);
        seat4 = findViewById(R.id.seat4);
        seat5 = findViewById(R.id.seat5);
        seat6 = findViewById(R.id.seat6);
        seat7 = findViewById(R.id.seat7);
        seat8 = findViewById(R.id.seat8);
        seat9 = findViewById(R.id.seat9);
        seat10 = findViewById(R.id.seat10);
        seat11 = findViewById(R.id.seat11);
        seat12 = findViewById(R.id.seat12);
        seat13 = findViewById(R.id.seat13);
        seat14 = findViewById(R.id.seat14);
        seat15 = findViewById(R.id.seat15);
        seat16 = findViewById(R.id.seat16);
        seat17 = findViewById(R.id.seat17);
        seat18 = findViewById(R.id.seat18);
        seat19 = findViewById(R.id.seat19);
        seat20 = findViewById(R.id.seat20);

    }

    @Override
    public void onClick(View view) {

        if (!rbooking.equalsIgnoreCase("") && (!rbooking.equalsIgnoreCase(null))) {
            rb = rbooking.split("/");
        }
        if (!noseat.equalsIgnoreCase("") && (!noseat.equalsIgnoreCase(null))) {
            se = noseat.split("/");
        }
        if (view.getId() == R.id.seat1) {
            Boolean check = false;
            Boolean nocheck = false;
            Button seat = seat1;
            if (!noseat.equalsIgnoreCase("")) {
                nocheck = checkselectseat(seat);
            }
            if (!rbooking.equalsIgnoreCase("") && nocheck.equals(false)) {
                check = checkregisterseat(seat);
            }

            if ((check.equals(false)) && (nocheck.equals(false))) {
                count = count + 1;
                if(count<=personcount) {

                    noseat = noseat + seat1.getText().toString() + "/";
                    seat1.setBackgroundResource(R.drawable.shape4);
                   // seat1.setText("B");
                    if(count==personcount){
                        btnbuyticket.setVisibility(View.VISIBLE);
                    }else{
                        btnbuyticket.setVisibility(View.GONE);
                    }
                }
            }
        } else if (view.getId() == R.id.seat2) {
            Boolean check = false;
            Boolean nocheck = false;
            Button seat = seat2;
            if (!noseat.equalsIgnoreCase("")) {
                nocheck = checkselectseat(seat);
            }
            if (!rbooking.equalsIgnoreCase("") && nocheck.equals(false)) {
                check = checkregisterseat(seat);
            }
            if ((check.equals(false)) && (nocheck.equals(false))) {
                count = count + 1;
                if(count<=personcount) {
                    noseat = noseat + seat2.getText().toString() + "/";
                    seat2.setBackgroundResource(R.drawable.shape4);
                   // seat2.setText("B");
                    if(count==personcount){
                        btnbuyticket.setVisibility(View.VISIBLE);
                    }else{
                        btnbuyticket.setVisibility(View.GONE);
                    }
                }
            }
        } else if (view.getId() == R.id.seat3) {
            Boolean check = false;
            Boolean nocheck = false;
            Button seat = seat3;
            if (!noseat.equalsIgnoreCase("")) {
                nocheck = checkselectseat(seat);
            }
            if (!rbooking.equalsIgnoreCase("") && nocheck.equals(false)) {
                check = checkregisterseat(seat);
            }

            if ((check.equals(false)) && (nocheck.equals(false))) {
                count = count + 1;
                if(count<=personcount) {
                    noseat = noseat + seat3.getText().toString() + "/";
                    seat3.setBackgroundResource(R.drawable.shape4);
                   // seat3.setText("B");
                    if(count==personcount){
                        btnbuyticket.setVisibility(View.VISIBLE);
                    }else{
                        btnbuyticket.setVisibility(View.GONE);
                    }
                }
            }
        } else if (view.getId() == R.id.seat4) {
            Boolean check = false;
            Boolean nocheck = false;
            Button seat = seat4;
            if (!noseat.equalsIgnoreCase("")) {
                nocheck = checkselectseat(seat);
            }
            if (!rbooking.equalsIgnoreCase("") && nocheck.equals(false)) {
                check = checkregisterseat(seat);
            }

            if ((check.equals(false)) && (nocheck.equals(false))) {
                count = count + 1;
                if(count<=personcount) {
                    noseat = noseat + seat4.getText().toString() + "/";
                    seat4.setBackgroundResource(R.drawable.shape4);
                   // seat4.setText("B");
                    if(count==personcount){
                        btnbuyticket.setVisibility(View.VISIBLE);
                    }else{
                        btnbuyticket.setVisibility(View.GONE);
                    }
                }
            }
        } else if (view.getId() == R.id.seat5) {
            Boolean check = false;
            Boolean nocheck = false;
            Button seat = seat5;
            if (!noseat.equalsIgnoreCase("")) {
                nocheck = checkselectseat(seat);
            }
            if (!rbooking.equalsIgnoreCase("") && nocheck.equals(false)) {
                check = checkregisterseat(seat);
            }

            if ((check.equals(false)) && (nocheck.equals(false))) {
                count = count + 1;
                if(count<=personcount) {
                    noseat = noseat + seat5.getText().toString() + "/";
                    seat5.setBackgroundResource(R.drawable.shape4);
                   // seat5.setText("B");
                    if(count==personcount){
                        btnbuyticket.setVisibility(View.VISIBLE);
                    }else{
                        btnbuyticket.setVisibility(View.GONE);
                    }
                }
            }
        } else if (view.getId() == R.id.seat6) {
            Boolean check = false;
            Boolean nocheck = false;
            Button seat = seat6;
            if (!noseat.equalsIgnoreCase("")) {
                nocheck = checkselectseat(seat);
            }
            if (!rbooking.equalsIgnoreCase("") && nocheck.equals(false)) {
                check = checkregisterseat(seat);
            }

            if ((check.equals(false)) && (nocheck.equals(false))) {
                count = count + 1;
                if(count<=personcount) {
                    noseat = noseat + seat6.getText().toString() + "/";
                    seat6.setBackgroundResource(R.drawable.shape4);
                    //seat6.setText("B");
                    if(count==personcount){
                        btnbuyticket.setVisibility(View.VISIBLE);
                    }else{
                        btnbuyticket.setVisibility(View.GONE);
                    }
                }
            }
        } else if (view.getId() == R.id.seat7) {
            Boolean check = false;
            Boolean nocheck = false;
            Button seat = seat7;
            if (!noseat.equalsIgnoreCase("")) {
                nocheck = checkselectseat(seat);
            }
            if (!rbooking.equalsIgnoreCase("") && nocheck.equals(false)) {
                check = checkregisterseat(seat);
            }

            if ((check.equals(false)) && (nocheck.equals(false))) {
                count = count + 1;
                if(count<=personcount) {
                    noseat = noseat + seat7.getText().toString() + "/";
                    seat7.setBackgroundResource(R.drawable.shape4);
                   // seat7.setText("B");
                    if(count==personcount){
                        btnbuyticket.setVisibility(View.VISIBLE);
                    }else{
                        btnbuyticket.setVisibility(View.GONE);
                    }
                }
            }
        } else if (view.getId() == R.id.seat8) {
            Boolean check = false;
            Boolean nocheck = false;
            Button seat = seat8;
            if (!noseat.equalsIgnoreCase("")) {
                nocheck = checkselectseat(seat);
            }
            if (!rbooking.equalsIgnoreCase("") && nocheck.equals(false)) {
                check = checkregisterseat(seat);
            }

            if ((check.equals(false)) && (nocheck.equals(false))) {
                count = count + 1;
                if(count<=personcount) {
                    noseat = noseat + seat8.getText().toString() + "/";
                    seat8.setBackgroundResource(R.drawable.shape4);
                   // seat8.setText("B");
                    if(count==personcount){
                        btnbuyticket.setVisibility(View.VISIBLE);
                    }else{
                        btnbuyticket.setVisibility(View.GONE);
                    }
                }
            }
        } else if (view.getId() == R.id.seat9) {
            Boolean check = false;
            Boolean nocheck = false;
            Button seat = seat9;
            if (!noseat.equalsIgnoreCase("")) {
                nocheck = checkselectseat(seat);
            }
            if (!rbooking.equalsIgnoreCase("") && nocheck.equals(false)) {
                check = checkregisterseat(seat);
            }

            if ((check.equals(false)) && (nocheck.equals(false))) {
                count = count + 1;
                if(count<=personcount) {
                    noseat = noseat + seat9.getText().toString() + "/";
                    seat9.setBackgroundResource(R.drawable.shape4);
                   // seat9.setText("B");
                    if(count==personcount){
                        btnbuyticket.setVisibility(View.VISIBLE);
                    }else{
                        btnbuyticket.setVisibility(View.GONE);
                    }
                }
            }
        } else if (view.getId() == R.id.seat10) {
            Boolean check = false;
            Boolean nocheck = false;
            Button seat = seat10;
            if (!noseat.equalsIgnoreCase("")) {
                nocheck = checkselectseat(seat);
            }
            if (!rbooking.equalsIgnoreCase("") && nocheck.equals(false)) {
                check = checkregisterseat(seat);
            }

            if ((check.equals(false)) && (nocheck.equals(false))) {
                count = count + 1;
                if(count<=personcount) {
                    noseat = noseat + seat10.getText().toString() + "/";
                    seat10.setBackgroundResource(R.drawable.shape4);
                   // seat10.setText("B");
                    if(count==personcount){
                        btnbuyticket.setVisibility(View.VISIBLE);
                    }else{
                        btnbuyticket.setVisibility(View.GONE);
                    }
                }
            }
        } else if (view.getId() == R.id.seat11) {
            Boolean check = false;
            Boolean nocheck = false;
            Button seat = seat11;
            if (!noseat.equalsIgnoreCase("")) {
                nocheck = checkselectseat(seat);
            }
            if (!rbooking.equalsIgnoreCase("") && nocheck.equals(false)) {
                check = checkregisterseat(seat);
            }

            if ((check.equals(false)) && (nocheck.equals(false))) {
                count = count + 1;
                if(count<=personcount) {
                    noseat = noseat + seat11.getText().toString() + "/";
                    seat11.setBackgroundResource(R.drawable.shape4);
                   // seat11.setText("B");
                    if(count==personcount){
                        btnbuyticket.setVisibility(View.VISIBLE);
                    }else{
                        btnbuyticket.setVisibility(View.GONE);
                    }
                }
            }
        } else if (view.getId() == R.id.seat12) {
            Boolean check = false;
            Boolean nocheck = false;
            Button seat = seat12;
            if (!noseat.equalsIgnoreCase("")) {
                nocheck = checkselectseat(seat);
            }
            if (!rbooking.equalsIgnoreCase("") && nocheck.equals(false)) {
                check = checkregisterseat(seat);
            }

            if ((check.equals(false)) && (nocheck.equals(false))) {
                count = count + 1;
                if(count<=personcount) {
                    noseat = noseat + seat12.getText().toString() + "/";
                    seat12.setBackgroundResource(R.drawable.shape4);
                  //  seat12.setText("B");
                    if(count==personcount){
                        btnbuyticket.setVisibility(View.VISIBLE);
                    }else{
                        btnbuyticket.setVisibility(View.GONE);
                    }
                }
            }
        } else if (view.getId() == R.id.seat13) {
            Boolean check = false;
            Boolean nocheck = false;
            Button seat = seat13;
            if (!noseat.equalsIgnoreCase("")) {
                nocheck = checkselectseat(seat);
            }
            if (!rbooking.equalsIgnoreCase("") && nocheck.equals(false)) {
                check = checkregisterseat(seat);
            }

            if ((check.equals(false)) && (nocheck.equals(false))) {
                count = count + 1;
                if(count<=personcount) {
                    noseat = noseat + seat13.getText().toString() + "/";
                    seat13.setBackgroundResource(R.drawable.shape4);
                   // seat13.setText("B");
                    if(count==personcount){
                        btnbuyticket.setVisibility(View.VISIBLE);
                    }else{
                        btnbuyticket.setVisibility(View.GONE);
                    }
                }
            }
        } else if (view.getId() == R.id.seat14) {
            Boolean check = false;
            Boolean nocheck = false;
            Button seat = seat14;
            if (!noseat.equalsIgnoreCase("")) {
                nocheck = checkselectseat(seat);
            }
            if (!rbooking.equalsIgnoreCase("") && nocheck.equals(false)) {
                check = checkregisterseat(seat);
            }

            if ((check.equals(false)) && (nocheck.equals(false))) {
                count = count + 1;
                if(count<=personcount) {
                    noseat = noseat + seat14.getText().toString() + "/";
                    seat14.setBackgroundResource(R.drawable.shape4);
                   // seat14.setText("B");
                    if(count==personcount){
                        btnbuyticket.setVisibility(View.VISIBLE);
                    }else{
                        btnbuyticket.setVisibility(View.GONE);
                    }
                }
            }
        } else if (view.getId() == R.id.seat15) {
            Boolean check = false;
            Boolean nocheck = false;
            Button seat = seat15;
            if (!noseat.equalsIgnoreCase("")) {
                nocheck = checkselectseat(seat);
            }
            if (!rbooking.equalsIgnoreCase("") && nocheck.equals(false)) {
                check = checkregisterseat(seat);
            }

            if ((check.equals(false)) && (nocheck.equals(false))) {
                count = count + 1;
                if(count<=personcount) {
                    noseat = noseat + seat15.getText().toString() + "/";
                    seat15.setBackgroundResource(R.drawable.shape4);
                   // seat15.setText("B");
                    if(count==personcount){
                        btnbuyticket.setVisibility(View.VISIBLE);
                    }else{
                        btnbuyticket.setVisibility(View.GONE);
                    }
                }
            }
        } else if (view.getId() == R.id.seat16) {
            Boolean check = false;
            Boolean nocheck = false;
            Button seat = seat16;
            if (!noseat.equalsIgnoreCase("")) {
                nocheck = checkselectseat(seat);
            }
            if (!rbooking.equalsIgnoreCase("") && nocheck.equals(false)) {
                check = checkregisterseat(seat);
            }

            if ((check.equals(false)) && (nocheck.equals(false))) {
                count = count + 1;
                if(count<=personcount) {
                    noseat = noseat + seat16.getText().toString() + "/";
                    seat16.setBackgroundResource(R.drawable.shape4);
                   // seat16.setText("B");
                    if(count==personcount){
                        btnbuyticket.setVisibility(View.VISIBLE);
                    }else{
                        btnbuyticket.setVisibility(View.GONE);
                    }
                }
            }
        } else if (view.getId() == R.id.seat17) {
            Boolean check = false;
            Boolean nocheck = false;
            Button seat = seat17;
            if (!noseat.equalsIgnoreCase("")) {
                nocheck = checkselectseat(seat);
            }
            if (!rbooking.equalsIgnoreCase("") && nocheck.equals(false)) {
                check = checkregisterseat(seat);
            }

            if ((check.equals(false)) && (nocheck.equals(false))) {
                count = count + 1;
                if(count<=personcount) {
                    noseat = noseat + seat17.getText().toString() + "/";
                    seat17.setBackgroundResource(R.drawable.shape4);
                  //  seat17.setText("B");
                    if(count==personcount){
                        btnbuyticket.setVisibility(View.VISIBLE);
                    }else{
                        btnbuyticket.setVisibility(View.GONE);
                    }
                }
            }
        } else if (view.getId() == R.id.seat18) {
            Boolean check = false;
            Boolean nocheck = false;
            Button seat = seat18;
            if (!noseat.equalsIgnoreCase("")) {
                nocheck = checkselectseat(seat);
            }
            if (!rbooking.equalsIgnoreCase("") && nocheck.equals(false)) {
                check = checkregisterseat(seat);
            }

            if ((check.equals(false)) && (nocheck.equals(false))) {
                count = count + 1;
                if(count<=personcount) {
                    noseat = noseat + seat18.getText().toString() + "/";
                    seat18.setBackgroundResource(R.drawable.shape4);
                   // seat18.setText("B");
                    if(count==personcount){
                        btnbuyticket.setVisibility(View.VISIBLE);
                    }else{
                        btnbuyticket.setVisibility(View.GONE);
                    }
                }
            }
        } else if (view.getId() == R.id.seat19) {
            Boolean check = false;
            Boolean nocheck = false;
            Button seat = seat19;
            if (!noseat.equalsIgnoreCase("")) {
                nocheck = checkselectseat(seat);
            }
            if (!rbooking.equalsIgnoreCase("") && nocheck.equals(false)) {
                check = checkregisterseat(seat);
            }

            if ((check.equals(false)) && (nocheck.equals(false))) {
                count = count + 1;
                if(count<=personcount) {
                noseat = noseat + seat19.getText().toString() + "/";
                    seat19.setBackgroundResource(R.drawable.shape4);
                   // seat19.setText("B");
                if(count==personcount){
                    btnbuyticket.setVisibility(View.VISIBLE);
                }else{
                    btnbuyticket.setVisibility(View.GONE);
                }
            }
            }
        } else if (view.getId() == R.id.seat20) {
            Boolean check = false;
            Boolean nocheck = false;
            Button seat = seat19;
            if (!noseat.equalsIgnoreCase("")) {
                nocheck = checkselectseat(seat);
            }
            if (!rbooking.equalsIgnoreCase("") && nocheck.equals(false)) {
                check = checkregisterseat(seat);
            }

            if ((check.equals(false)) && (nocheck.equals(false))) {
                count = count + 1;
                if(count<=personcount) {
                    noseat = noseat + seat20.getText().toString() + "/";
                    seat20.setBackgroundResource(R.drawable.shape4);
                   // seat20.setText("B");
                    if(count==personcount){
                        btnbuyticket.setVisibility(View.VISIBLE);
                    }else{
                        btnbuyticket.setVisibility(View.GONE);
                    }
                }

            }

            //
        }


    }
    private Boolean checkselectseat(Button seat){
        Boolean c=false;
        for(int i=0;i<se.length;i++){
            if(seat.getText().toString().equalsIgnoreCase(se[i])){
                c=true;
                noseat=noseat.replace(se[i]+"/","");
                seat.setBackgroundResource(R.drawable.shape3);
                count=count-1;
                if(count==personcount){
                    btnbuyticket.setVisibility(View.VISIBLE);
                }else{
                    btnbuyticket.setVisibility(View.GONE);
                }
            }
        }
        return  c;
    }
    private Boolean checkregisterseat(Button seat){
        Boolean c=false;
        for(int i=0;i<rb.length;i++){
            if(seat.getText().toString().equalsIgnoreCase(rb[i])){
                c=true;
            }
        }
        return c;
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK){
            AlertBoxCustom();
        }

        return true;
    }
    private void AlertBoxCustom() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SuperClassActivity.this);
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
