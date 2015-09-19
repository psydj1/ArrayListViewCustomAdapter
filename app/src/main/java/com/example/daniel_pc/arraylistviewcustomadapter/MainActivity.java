package com.example.daniel_pc.arraylistviewcustomadapter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView1;
    Button btnAdd;

    //all properties here
    TextView countdownTimer;
    EditText hours;

    String stringBroadcast;


    //global array
    ArrayList weather_arrList = new ArrayList<>();



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        countdownTimer = (TextView)findViewById(R.id.counterRemText);
        hours = (EditText)findViewById(R.id.hoursText);

        btnAdd = (Button) this.findViewById(R.id.btnAdd);



//        weather_arrList.clear();

        final WeatherAdapter adapter = new WeatherAdapter(this, R.layout.listview_item_row, weather_arrList);


        listView1 = (ListView) findViewById(R.id.listView1);


        //creating and aplying inflater the short way to the view
        View header = (View) getLayoutInflater().inflate(R.layout.listview_header_row, null);

        listView1.addFooterView(header);

        //apply adapter for listView
        listView1.setAdapter(adapter);


        final Weather weather = new Weather(R.layout.just_button, stringBroadcast);


        btnAdd.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), CountDownTimeService.class);

                v.getContext().startService(intent);

                weather.setTitle("stringBroadcast");


                adapter.notifyDataSetChanged();

            }


        });


        //filter says which intents it should listen to.
         registerReceiver(uiUpdated, new IntentFilter("COUNTDOWN_UPDATED"));

    }



    public void stopService(View view){

        //make this slightly more sophisticated
        //e.g. pausing might be too difficult but restarting from the start is ok I guess
        stopService(new Intent(this, CountDownTimeService.class));
    }


    //New boradcasrReceiver with the same name as he one registered earlier
    public BroadcastReceiver uiUpdated = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            //This is the part where I get the timer value from the service and I update it every second,
            //because I send the data from the service every second.



            //countdownTimer.setText(intent.getExtras().getString("countdown"));



            stringBroadcast = intent.getExtras().getString("countdown");





        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
