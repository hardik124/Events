package com.z_connect.events;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {


    private RecyclerView mEventList ;
    private DatabaseReference mDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mEventList= (RecyclerView) findViewById(R.id.eventList);
        mEventList.setHasFixedSize(true);
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Event");

        mEventList.setLayoutManager(new LinearLayoutManager(this));

        //Toast.makeText(getApplicationContext(),"Check 1",Toast.LENGTH_LONG);



    }


    @Override
    protected void onStart() {
        super.onStart();


        FirebaseRecyclerAdapter<Event,eventViewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Event, eventViewHolder>(
                Event.class,
                R.layout.event_row,
                eventViewHolder.class,
                mDatabase) {



            @Override
            protected void populateViewHolder(eventViewHolder viewHolder, Event model, int position) {

                viewHolder.setTitle(model.getTitl());
                viewHolder.setDescription(model.getDesc());

               String Date = model.getDa()+"/"+model.getMon()+"/"+model.getYea()+"";
               String Time = model.getHo()+":"+model.getMin()+"";
               viewHolder.setDate(Date);
                viewHolder.setTime(Time);
                //Toast.makeText(getApplicationContext(),"Check 1",Toast.LENGTH_LONG);


            }


        };
        mEventList.setAdapter(firebaseRecyclerAdapter);
    }



    public static class eventViewHolder extends RecyclerView.ViewHolder

    {
        View mView;
        public eventViewHolder(View itemView) {

            super(itemView);
            mView=itemView;


        }

        public void setTitle(String title)
        {
            TextView postTitle= (TextView) mView.findViewById(R.id.title);
            postTitle.setText(title);

        }
        public void setDescription(String description)
        {
            TextView postDesc= (TextView) mView.findViewById(R.id.desc);
            postDesc.setText(description);

        }
       public void setDate(String title)
        {
            TextView setDate= (TextView) mView.findViewById(R.id.date);
            setDate.setText(title);

        }
        public void setTime(String title)
        {
            TextView setTime= (TextView) mView.findViewById(R.id.time);
            setTime.setText(title);

        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_menu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.action_add)
        {
            startActivity(new Intent(MainActivity.this, Post_activity.class));
        }
        return super.onOptionsItemSelected(item);
    }


}
