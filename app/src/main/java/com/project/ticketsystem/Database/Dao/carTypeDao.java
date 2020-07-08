package com.project.ticketsystem.Database.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.project.ticketsystem.Database.databaseTable.carType;

import java.util.List;

@Dao
public interface carTypeDao {
    @Query("select * from carType")
    List<carType> getCarType();

    @Insert
    void addCarType(carType ct);


    @Query("select ct.syskey as syskey,ct.date as date,ct.time as time,ct.price as price,ct.fromcity as fromcity,ct.tocity as tocity" +
            ",c.name as name1,c.type as type1,c.image as image from carType ct inner join car c on ct.carSyskey=c.syskey")
    public List<junctioncar> getJunctioncar();
    static class junctioncar{
        public String name1;
        public String type1;
        public String image;
        public String date;
        public String time;
        public String price;
        public String fromcity;
        public String tocity;
        public int syskey;

        public int getSyskey() {
            return syskey;
        }

        public void setSyskey(int syskey) {
            this.syskey = syskey;
        }

        public String getName1() {
            return name1;
        }

        public void setName1(String name1) {
            this.name1 = name1;
        }

        public String getType1() {
            return type1;
        }

        public void setType1(String type1) {
            this.type1 = type1;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
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
    }

    @Query("select ct.syskey as syskey,ct.date as date,ct.time as time,ct.price as price,ct.fromcity as fromcity,ct.tocity as tocity" +
            ",c.name as name1,c.type as type1,c.image as image from carType ct inner join car c on ct.carSyskey=c.syskey" +
            " WHERE   (ct.date =:ccdate AND  ct.time>=:cctime) OR (ct.date>:ccdate ) AND ct.date=:cdate AND " +
            "ct.fromcity=:cfrom AND ct.tocity=:cto")
    public List<junctioncar>getSearchCar(String ccdate, String cctime, String cdate, String cfrom, String cto);

    @Query("delete from carType where syskey=:pos")
    void deletecar(int pos);

    @Query("delete from carType where carSyskey=:pos")
    void deletechildcar(int pos);

    @Query("select * from carType where syskey=:pos")
    List<carType>getwatchcar(String pos);
    @Query("select ct.syskey as syskey,ct.date as date,ct.time as time,ct.price as price,ct.fromcity as fromcity,ct.tocity as tocity" +
            ",c.name as name1,c.type as type1,c.image as image from carType ct inner join car c on ct.carSyskey=c.syskey" +
            " WHERE   c.name like :t  or ct.date like:t")
    List<junctioncar>getSearchData(String t);

}
