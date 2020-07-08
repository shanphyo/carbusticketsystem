package com.project.ticketsystem.Shared;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class util {
    public static String dataToString(String d){
        String date="";

        if(!d.isEmpty()){
           String []arr=d.split("-");
           if(Integer.parseInt(arr[1])<10){
               if(Integer.parseInt(arr[0])<10){
                   date = arr[2] + "0" + arr[1] +"0"+ arr[0];
               }else {
                   date = arr[2] + "0" + arr[1] + arr[0];
               }
           }else {
               if(Integer.parseInt(arr[0])<10){
                   date = arr[2] + arr[1] +"0"+ arr[0];
               }else {
                   date = arr[2] + arr[1] + arr[0];
               }
           }

        }

        return  date;

    }
    public static String stringToDate(String d){
        String date="";
        if(!d.equalsIgnoreCase(null)){
            String dd,mm,yy;
            yy=d.substring(0,4);
            mm=d.substring(4,6);
            dd=d.substring(6,8);
            date=dd+"-"+mm+"-"+yy;
        }
        return date;
    }

    public static String twelveTo24(String t){
        String time="";
        int hr,min;
        String []arr=t.split(" ");
        String []arr1=arr[0].split(":");

        hr=Integer.parseInt(arr1[0]);
        min=Integer.parseInt(arr1[1]);
        if(!t.equalsIgnoreCase(null)){
            if(t.contains("AM") || t.contains("am")){
               if(hr==12){
                   if(min>9) {
                       time = "00" + min + "";
                   }else{
                       time = "00" +"0"+ min + "";
                   }

               }else if(hr==00){
                   if(min>9){
                       time="24"+min+"";
                   }else{
                       time="240"+min+"";
                   }
               }
               else{
                   if(hr>10) {
                       if(min>9) {
                           time = hr + min + "";
                       }else{
                           time = hr +"0"+ min + "";
                       }
                   }else{
                       if(min>9) {
                           time = "0" + hr + min + "";
                       }else{
                           time = "0" + hr +"0"+ min + "";
                       }
                   }


               }

            }else {
                if(hr==12){
                    if(min>9) {

                        time = hr + min + "";
                    }else{
                        time = hr +"0"+ min + "";
                    }
                }else{
                    int tt=12+hr;
                    if(min>9) {
                        time = tt +""+ min + "";
                    }else{
                        time = tt +"0"+ min + "";
                    }
                }
            }
        }
        return time;
    }

    public static String twentyfourToTwelve(String t){
        String time="";

        int hr=Integer.parseInt(t.substring(0,2));
        int min= Integer.parseInt(t.substring(2,4));

        if(hr==12){
            if(min>9) {
                time = hr + ":" + min + " PM";
            }else{
                time = hr + ":0" + min + " PM";
            }
        }else if(hr>12) {
            if(hr<24){
            int hrr=hr-12;
            if(min>9) {
                time = hrr + ":" + min + " PM";
            }else{
                time = hrr + ":0" + min + " PM";
            }}else{
                if(min>9){
                    time="00:"+min+" AM";
                }else{
                    time="00:0"+min+" AM";
                }
            }
        }else if(hr<12){
            if(min>9) {
                time = "0" + hr + ":" + min + " AM";
            }else{
                time = "0" + hr + ":0" + min + " AM";
            }
        }else if(hr==00){
            if(min>9) {
            time=12+":"+min+" AM";
            }else{
                time=12+":0"+min+" AM";
            }
        }else if(hr==24){
            if(min>9){
                time="00:"+min+" AM";
            }else{
                time="00:0"+min+" AM";
            }
        }
        return time;
    }

    public static String currentdate(){
        String d="";
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        String formattedDate = df.format(c);


        return formattedDate;
    }
    public static String currenttime(){
        SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
        String str = sdf.format(new Date());
        return str;
    }






}
