package com.project.ticketsystem.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.ticketsystem.Database.databaseTable.user;
import com.project.ticketsystem.Database.mainDatabase.Database;
import com.project.ticketsystem.Firebase.Constants;
import com.project.ticketsystem.Firebase.MyNotificationManager;
import com.project.ticketsystem.R;

import java.util.List;
import java.util.Random;

public class resetPassword extends AppCompatActivity {
    public EditText resetusername;
    public Button resetbtn;
    Database db;
    String pass;
    List<user>list=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        resetusername=findViewById(R.id.reset_username);
        resetbtn=findViewById(R.id.resetbtn_reset);
        db = Database.getINSTANCE(resetPassword.this);
        resetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String username= resetusername.getText().toString();
               if(username.equalsIgnoreCase(null)){
                   resetusername.setError("please fill username");
               }else {
                    list = db.userDao().getresetuser(username);
                   if(list.isEmpty()){
                       resetusername.setError("Please fill correct username");

                   }else{
                       Random rand = new Random();
                        pass=(rand.nextInt(10000))+"";


                       db.userDao().getupdatePassword(pass,list.get(0).getSyskey());
                       pushMessage(pass);



                   }
               }

            }
        });



    }

    public void pushMessage(String p){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationManager mNotificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(Constants.CHANNEL_ID, Constants.CHANNEL_NAME, importance);
            mChannel.setDescription(Constants.CHANNEL_DESCRIPTION);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mNotificationManager.createNotificationChannel(mChannel);
        }

        MyNotificationManager.getInstance(this).displayNotification("Reset Password ", "Your New Password="+p);
    }
}
