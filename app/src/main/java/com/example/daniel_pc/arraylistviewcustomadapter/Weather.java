package com.example.daniel_pc.arraylistviewcustomadapter;


import android.widget.Button;

public class Weather {

    public int icon;
    public String title;

    public Weather(){
        super();
    }

    public Weather(int icon, String title) {
        super();

        this.icon = icon;
        this.title = title;
    }

    public void setTitle(String string){

        this.title = string;
    }

}