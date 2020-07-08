package com.project.ticketsystem.Database.databaseTable;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
@Entity
public class car {
    @PrimaryKey(autoGenerate = true)
    private int syskey;
    private String name;
    private String phNo;
    private String Type;   //for super seat or first class
    private String noOfSeat;   //for super seat(44)  ,for firstclass (33)
    private String image;  //url
/*    public void clearProperties(){
        this.setName("");
        this.setPhNo("");
        this.setType("");
        this.setNoOfSeat("");
        this.setImage("");
    }

    public car() {
        clearProperties();
    }*/

    public int getSyskey() {
        return syskey;
    }

    public void setSyskey(int syskey) {
        this.syskey = syskey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhNo() {
        return phNo;
    }

    public void setPhNo(String phNo) {
        this.phNo = phNo;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getNoOfSeat() {
        return noOfSeat;
    }

    public void setNoOfSeat(String noOfSeat) {
        this.noOfSeat = noOfSeat;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
