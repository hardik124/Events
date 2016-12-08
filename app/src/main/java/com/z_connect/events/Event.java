package com.z_connect.events;

import android.widget.Toast;

/**
 * Created by f390 on 12/11/16.
 */

public class Event {

    long Day, Month, Hour, Minute;
    long Year;
    String Description, Title;


    public Event() {

    }


    public Event(long xyz, long abc, long def, long hij, long klm, String nop, String qrs) {
        Day = xyz    ;
        Month = abc;
        Hour = def;
        Minute = hij;
        Year = klm;
        Description = nop;
        Title = qrs;
    }

   public long getDa() {
        return Day;
    }
    public long getHo() {
        return Hour;
    }
    public String getTitl() {
        return Title;
    }
   public long getMon() {
        return Month;
    }
    public long getMin() {
        return Minute;
    }
    public String getDesc() {
        return Description;
    }

    public long getYea() {
        return Year;
    }

   /* public void setDay(long day) {
        Day = day;
    }*/



  /*  public void setMonth(long month) {
        Month = month;
    }



    public void setHour(long hour) {
        Hour = hour;
    }



    public void setMinute(long minute) {
        Minute = minute;
    }


    public void setYear(long year) {
        Year = year;
    }



    public void setDescription(String description) {
        Description = description;
    }*/



    public void setTitl(String title) {
        Title = title;
    }}

