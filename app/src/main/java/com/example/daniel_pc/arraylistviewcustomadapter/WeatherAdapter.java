package com.example.daniel_pc.arraylistviewcustomadapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class WeatherAdapter extends ArrayAdapter<Weather> {

    //This class takes three parameters
    Context context;  //pass the reference of the activity in which we use this WeatherAdapter
    int layoutResourceId; //id of the layout file that we want to use for each listView item. Will pass listView_ite_row
    ArrayList<Weather> data; //array of Weather objects used by Adapter to display data

    public WeatherAdapter(Context context, int layoutResourceId, ArrayList<Weather> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }


    //called for every item in the listView with their properties set as we want
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Create new custom View programmatically
        View row = convertView;
        //getView is using temporary holder class declared in WeatherAdapter
        WeatherHolder holder = null;

        if(row == null)
        {
            //Using android built in Inflater to inflate (PARSE) the xml files.
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();  //create inflater

            //use it to inflate row
            row = inflater.inflate(layoutResourceId, parent, false); //apply long way

            holder = new WeatherHolder();

            //since row has been inflated I guess holder can access those now
            holder.imgIcon = (Button)row.findViewById(R.id.imgIcon);
            holder.txtTitle = (TextView)row.findViewById(R.id.txtTitle);

            //fuck knows
            row.setTag(holder);
        }
        else
        {
            //another fuck knows
            holder = (WeatherHolder)row.getTag();
        }

        //Taking the weather data, arrayList
        Weather weather = data.get(position);

        //so that things from holder(linked holder stuff with inflated layout) can be changed
        //for Weather object stuff. Which was created in the Main activity.
        holder.txtTitle.setText(weather.title);
//        holder.imgIcon.setImageResource(weather.icon);
        //I guess this line is not even needed here, because the button is already added above
        //this is just modifying it. I can ad on CLick listenere here if I want e.g.

        return row;
    }

    //Temporary holder class
    //will be used to cache the Image View and text so they can be reused
    //for every row and improve performance
    //No need to find those views every listView item
    static class WeatherHolder
    {
        View imgIcon;
        TextView txtTitle;
    }

    //maybe stop doing your stuff and follow the established rules. Do only things that have been done already
    //dont try to creatae sometbing myself, waste of time and frustration. Not enough knowledge yet.
}