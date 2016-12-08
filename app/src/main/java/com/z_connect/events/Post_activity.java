package com.z_connect.events;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Timer;
import java.util.TimerTask;

public class Post_activity extends AppCompatActivity {
    int day , month, hour, minute;
    int year;
    EditText mTitle , mDesc;
    TimePickerDialog timePickerDialog ;
    DatePickerDialog datepickerDialog;
    DatabaseReference mDatabase;
    private ProgressDialog pBar;

  Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Event");



        //Time Selection
        {
            findViewById(R.id.timebtn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    timePickerDialog = new TimePickerDialog(context,
                            new TimePickerDialog.OnTimeSetListener() {

                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay,
                                                      int minuteofDay) {
                                    hour = hourOfDay;
                                    minute = minuteofDay;

                                }


                            }, 10, 10, true);
                    timePickerDialog.show();
                }
            });
        }

        //Date Selection

        {
            findViewById(R.id.datebtn).setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onClick(View view) {
                    final Calendar c = Calendar.getInstance();
                    int mYear = c.get(Calendar.YEAR);
                    int mMonth = c.get(Calendar.MONTH);
                    int mDay = c.get(Calendar.DAY_OF_MONTH);

                    datepickerDialog = new DatePickerDialog(context,
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int yearob,
                                                      int monthOfYear, int dayOfMonth) {
                                    year = yearob;
                                    month = monthOfYear;
                                    day = dayOfMonth;
                                }


                            }, mYear, mMonth, mDay);
                    datepickerDialog.show();


                }
            });
        }

        pBar=new ProgressDialog(this);
        mTitle= (EditText)findViewById(R.id.eventName);
        mDesc= (EditText)findViewById(R.id.eventDetails);
        findViewById(R.id.btnDone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPosting();
                
            }
        });

    }


    private void startPosting() {

        String Title , Desc;
        Title= mTitle.getText().toString().trim();
        Desc= mDesc.getText().toString().trim();

        if(!TextUtils.isEmpty(Title)&&!TextUtils.isEmpty(Desc)) {
            pBar.setMessage("Posting Event....");
            pBar.show();
            DatabaseReference newEvent = mDatabase.push();
            newEvent.child("Title").setValue(Title);
            newEvent.child("Description").setValue(Desc);
            newEvent.child("Day").setValue(day);
            newEvent.child("Month").setValue(month);
            newEvent.child("Year").setValue(year);
            newEvent.child("Hour").setValue(hour);
            newEvent.child("Minute").setValue(minute);


            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    pBar.dismiss();
                    Toast.makeText(getApplicationContext(), "Posted", Toast.LENGTH_LONG).show();

                }
            }, 2000);
        }

        else
        {

            Toast.makeText(context, "One or more fields are empty", Toast.LENGTH_LONG).show();
        }
}}