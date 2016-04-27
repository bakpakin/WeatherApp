package com.example.calvin.weatherapp;

import android.content.Intent;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class DisplayIdealDates extends AppCompatActivity {

    private TextView startDate;
    private TextView endDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_ideal_dates);

        // Get the info entered by the user
        Intent intent = getIntent();
        int num_days = Integer.parseInt(intent.getStringExtra(MainActivity.days_s));
        double user_temp = Double.parseDouble(intent.getStringExtra(MainActivity.temp_s));
        double user_rain = Double.parseDouble(intent.getStringExtra(MainActivity.ppt_s));
        String user_city = intent.getStringExtra(MainActivity.city_name);

        // Get the references to the views
        startDate = (TextView) findViewById(R.id.firstdate);
        endDate = (TextView) findViewById(R.id.seconddate);

        WeatherData.getDates(user_temp, user_rain, num_days, user_city, new VacationIntervalListener() {
            @Override
            public void onIntervalReceived(ArrayList<Calendar> interval) {
                Calendar start = interval.get(0);
                Calendar end = interval.get(1);
                startDate.setText(stringifyDate(start));
                endDate.setText(stringifyDate(end));
            }
        });

    }

    private String stringifyDate(Calendar cal) {
        return new SimpleDateFormat("MMMM d", Locale.getDefault()).format(cal.getTime());
    }
}
