package com.project.ticketsystem.Database.databaseTable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class booking {
    @PrimaryKey(autoGenerate = true)
    private int syskey;
    private int  carTypeSyskey; //cartype table syskey
    private String name;
    private String pno;
    private String email;
    private String nrcNo;
    private String way; //Yangon-Mandalay
    private String date; //20190124
    private String time; //24 hours format
    private String noOfPassenger;//1 or 2 or 3
    private String seatNo; //1A,1B,1C,1D

    public int getSyskey() {
        return syskey;
    }

    public void setSyskey(int syskey) {
        this.syskey = syskey;
    }

    public int getCarTypeSyskey() {
        return carTypeSyskey;
    }

    public void setCarTypeSyskey(int carTypeSyskey) {
        this.carTypeSyskey = carTypeSyskey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPno() {
        return pno;
    }

    public void setPno(String pno) {
        this.pno = pno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNrcNo() {
        return nrcNo;
    }

    public void setNrcNo(String nrcNo) {
        this.nrcNo = nrcNo;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
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

    public String getNoOfPassenger() {
        return noOfPassenger;
    }

    public void setNoOfPassenger(String noOfPassenger) {
        this.noOfPassenger = noOfPassenger;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }
}
