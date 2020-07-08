package com.project.ticketsystem.Database.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.project.ticketsystem.Database.databaseTable.car;

import java.util.List;

@Dao
public interface carDao {
    @Query("select * from car")
    List<car>getCar();

    @Insert
    void addCar(car c);


    @Query("select syskey from car where name  = :name1 and type = :type")
    int getSyskey(String name1, String type);
    @Query("delete from car where syskey=:pos")
    void deletecar(int pos);

    @Query("select * from car where name like :t or type like :t")
    List<car> getCarSearch(String t);
    @Query("select * from car where syskey=:skey")
    List<car>getcarName(int skey);
}
