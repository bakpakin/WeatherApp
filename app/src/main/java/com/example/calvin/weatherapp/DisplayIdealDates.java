package com.example.calvin.weatherapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayIdealDates extends AppCompatActivity {

    private TextView days;
    private TextView temp;
    private TextView city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_ideal_dates);
        Intent intent = getIntent();
        String num_days = intent.getStringExtra(MainActivity.days_s);
        String user_temp = intent.getStringExtra(MainActivity.temp_s);
        String user_ppt = intent.getStringExtra(MainActivity.ppt_s);
        String user_city = intent.getStringExtra(MainActivity.city_name);

        /*days = (TextView) findViewById(R.id.num_days);
        days.setText(num_days);

        temp = (TextView) findViewById(R.id.user_temp);
        temp.setText(user_temp);

        ppt = (TextView) findViewById(R.id.user_ppt);
        ppt.setText(user_ppt)

        city = (TextView) findViewById(R.id.user_city);
        city.setText(user_city);*/

    }
}
