package com.example.calvin.weatherapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.example.calvin.weatherapp.WeatherData;

import java.util.ArrayList;
import java.util.Calendar;

public class DisplayIdealDates extends AppCompatActivity {

    private TextView firstdate;
    private TextView seconddate;
    String num_days;
    String user_temp;
    String user_city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_ideal_dates);
        Intent intent = getIntent();
        num_days = intent.getStringExtra(MainActivity.days_s);
        user_temp = intent.getStringExtra(MainActivity.temp_s);
        //String user_ppt = intent.getStringExtra(MainActivity.ppt_s);
        user_city = intent.getStringExtra(MainActivity.city_name);

        /*ArrayList<Calendar> dates = new ArrayList(2);
        dates = WeatherData.doTest(user_temp, num_days, user_city);


        Calendar cal1 = dates.get(1);
        int startDayOfMonth = cal1.get(Calendar.DAY_OF_MONTH);
        int startMonthOfYear = cal1.get(Calendar.MONTH);

        Calendar d = dates.get(2);

        int endDayOfMonth = d.get(Calendar.DAY_OF_MONTH);
        int endMonthOfYear = d.get(Calendar.MONTH);

        ArrayList<String> months = new ArrayList(12);
        months.set(1, "January");
        months.set(2, "February");
        months.set(3, "March");
        months.set(4, "April");
        months.set(5, "May");
        months.set(6, "June");
        months.set(7, "July");
        months.set(8, "August");
        months.set(9, "September");
        months.set(10, "October");
        months.set(11, "November");
        months.set(12, "December");

        String startmonth = months.get(startMonthOfYear);
        String startday = Integer.toString(startDayOfMonth);

        String endmonth = months.get(endMonthOfYear);
        String endday = Integer.toString(endDayOfMonth);

        String startfinal = startday + " " + startmonth;
        String endfinal = endday + " " + endmonth;

        firstdate = (TextView) findViewById(R.id.firstdate);
        firstdate.setText(startfinal);

        seconddate = (TextView) findViewById(R.id.seconddate);
        seconddate.setText(endfinal);*/




    }

}
