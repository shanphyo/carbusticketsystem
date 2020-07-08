package com.project.ticketsystem.Database.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.project.ticketsystem.Database.databaseTable.booking;

import java.util.List;

@Dao
public interface bookingDao {
    @Query("select * from booking")
    List<booking>getBooking();

    @Insert
    void addBoking(booking b);

    @Query("select * from booking where carTypeSyskey=:a")
    List<booking>getRecordlist(String a);
}
