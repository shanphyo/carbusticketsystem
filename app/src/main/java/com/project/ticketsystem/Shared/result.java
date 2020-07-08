package com.project.ticketsystem.Shared;

public class result {
    private  int syskey;
    private int carSyskey;
    private String date;  //20190124
    private String time;  //24 hour format
    private String price;
    private String fromtocity;
    private String type;
    private String image;

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFromtocity() {
        return fromtocity;
    }

    public void setFromtocity(String fromtocity) {
        this.fromtocity = fromtocity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
