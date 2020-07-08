package com.project.ticketsystem.Database.databaseTable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class Location {
    @PrimaryKey(autoGenerate = true)
    private int syskey;
    private String locationName;
    private double latitute;
    private double longitute;
    private String carname;
    private String province;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCarname() {
        return carname;
    }

    public void setCarname(String carname) {
        this.carname = carname;
    }

    public int getSyskey() {
        return syskey;
    }

    public void setSyskey(int syskey) {
        this.syskey = syskey;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public double getLatitute() {
        return latitute;
    }

    public void setLatitute(double latitute) {
        this.latitute = latitute;
    }

    public double getLongitute() {
        return longitute;
    }

    public void setLongitute(double longitute) {
        this.longitute = longitute;
    }
}
