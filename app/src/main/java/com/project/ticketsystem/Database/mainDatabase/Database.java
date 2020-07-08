package com.project.ticketsystem.Database.mainDatabase;


import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.project.ticketsystem.Database.Dao.bookingDao;
import com.project.ticketsystem.Database.Dao.carDao;
import com.project.ticketsystem.Database.Dao.carTypeDao;
import com.project.ticketsystem.Database.Dao.locationDao;
import com.project.ticketsystem.Database.Dao.userDao;
import com.project.ticketsystem.Database.databaseTable.Location;
import com.project.ticketsystem.Database.databaseTable.booking;
import com.project.ticketsystem.Database.databaseTable.car;
import com.project.ticketsystem.Database.databaseTable.carType;
import com.project.ticketsystem.Database.databaseTable.user;

@androidx.room.Database(entities = {user.class, booking.class, carType.class, car.class, Location.class},version = 1)
public abstract class Database extends RoomDatabase {
    public abstract userDao userDao();
    public abstract carDao carDao();
    public abstract carTypeDao carTypeDao();
    public abstract bookingDao bookingDao();
    public abstract locationDao locationDao();
    public static Database INSTANCE;
    public static Database getINSTANCE(Context con){
        if(INSTANCE == null){
            INSTANCE= Room.databaseBuilder(con.getApplicationContext(), Database.class,"busticket_db").allowMainThreadQueries().build();

        }
        return INSTANCE;
    }


}
