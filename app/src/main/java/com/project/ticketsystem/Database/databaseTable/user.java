package com.project.ticketsystem.Database.databaseTable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class user {
    @PrimaryKey(autoGenerate = true)
    private int syskey;
    private String name;
    private String password;
    private String email;
    private String img;
/*
    public user(String img) {
        this.img = "";
    }*/

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
