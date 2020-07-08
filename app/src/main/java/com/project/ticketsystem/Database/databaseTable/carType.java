package com.project.ticketsystem.Database.databaseTable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class carType {
    @PrimaryKey(autoGenerate = true)
    private  int syskey;
    private int carSyskey;
    private String date;  //20190124
    private String time;  //24 hour format
    private String price;
    private String fromcity;
    private String tocity;


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFromcity() {
        return fromcity;
    }

    public void setFromcity(String fromcity) {
        this.fromcity = fromcity;
    }

    public String getTocity() {
        return tocity;
    }

    public void setTocity(String tocity) {
        this.tocity = tocity;
    }

    public int getSyskey() {
        return syskey;
    }

    public void setSyskey(int syskey) {
        this.syskey = syskey;
    }

    public int getCarSyskey() {
        return carSyskey;
    }

    public void setCarSyskey(int carSyskey) {
        this.carSyskey = carSyskey;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
