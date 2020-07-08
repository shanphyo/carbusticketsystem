package com.project.ticketsystem.Database.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.project.ticketsystem.Database.databaseTable.Location;

import java.util.List;

@Dao
public interface locationDao {
    @Query("select * from location")
    List<Location>getlocation();
    @Insert
    void addLocation(Location u);
    @Query("select * from location where carname like :t or locationName like :t")
    List<Location>getsearchlocation(String t);
    @Query("delete from location where syskey= :pos")
    void deleteloc(int pos);
    @Query("select * from location where carname=:cname and province=:ctype")
    List<Location>getspeciallocation(String cname,String ctype);

}
