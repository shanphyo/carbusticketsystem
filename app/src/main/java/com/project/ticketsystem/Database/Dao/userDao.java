package com.project.ticketsystem.Database.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.project.ticketsystem.Database.databaseTable.user;

import java.util.List;

@Dao
public interface userDao {
    @Query("select * from user")
    List<user> getUser();

    @Insert
    void addUser(user u);

    @Query("select * from user where  email  = :username and password = :pass")
    List<user>getSpecialUser(String username, String pass);

    @Query("update user set name=:newname,password=:newpassword,img=:newimg where syskey=:skey")
    void updatedataandpassword(String newname, String newpassword, String newimg, String skey);

    @Query("update user set name=:newname,img=:newimg where syskey=:skey")
    void updatedata(String newname, String newimg, String skey);
    @Query("select * from user where syskey=:skey")
    List<user>getheaderProfile(String skey);

    @Query("delete from user where syskey=:pos")
    void deleteuser(int pos);

    @Query("select * from user where name like :t or email like :t")
    List<user>getsearchlogin(String t);
    @Query("select * from user where email=:use")
    List<user>getresetuser(String use);
    @Query("update user set password= :pass where syskey=:skey")
    void getupdatePassword(String pass,int skey);

}
