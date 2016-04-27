package com.example.calvin.weatherapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Button;
import android.widget.ArrayAdapter;

public class MainActivity extends AppCompatActivity {

    public final static String days_s = "com.example.calvin.weatherapp.MESSAGE";
    public final static String temp_s = "com.example.calvin.weatherapp.DAYS";
    public final static String ppt_s = "com.example.calvin.weatherapp.PPT";
    public final static String city_name = "com.example.calvin.weatherapp.CITIES";

    private SeekBar myTemp;
    private TextView Tempfinal;
    private SeekBar myppt;
    private TextView Pptfinal;
    private AutoCompleteTextView CityName;
    private EditText user_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BackendConnector.initInstance(getApplicationContext());

        myTemp = (SeekBar) findViewById(R.id.user_temp);
        user_input = (EditText) findViewById(R.id.user_days);
        Tempfinal = (TextView) findViewById(R.id.text_temp);
        CityName = (AutoCompleteTextView) findViewById(R.id.autocomplete_city);
        myppt = (SeekBar) findViewById(R.id.user_rain);
        Pptfinal = (TextView) findViewById(R.id.text_rain);
        // Get the string array
        String[] cities = getResources().getStringArray(R.array.cities_array);
        CityName.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cities));

        myTemp.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar mySeekBar, int progress, boolean fromUser) {
                Tempfinal.setText(Integer.toString(progress));
            }

            public void onStartTrackingTouch(SeekBar mySeekBar) {

            }

            public void onStopTrackingTouch(SeekBar mySeekBar) {

            }
        });



        myppt.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar mySeekBar, int progress, boolean fromUser) {
                Pptfinal.setText(Integer.toString(progress));
            }

            public void onStartTrackingTouch(SeekBar mySeekBar) {

            }

            public void onStopTrackingTouch(SeekBar mySeekBar) {

            }
        });

    }

    public void calc_ideal(View view) {

        Intent intent = new Intent(this, DisplayIdealDates.class);

        String string_days = user_input.getText().toString();
        intent.putExtra(days_s,string_days);

        String user_temp = Tempfinal.getText().toString();
        intent.putExtra(temp_s,user_temp);

        String user_rain = Pptfinal.getText().toString();
        intent.putExtra(ppt_s,user_rain);

        String city = CityName.getText().toString();
        intent.putExtra(city_name,city);

        startActivity(intent);

    }

    public void get_current_weather(View view) {
        Intent intent = new Intent(this, DisplayCurrentWeather.class);
        String city = CityName.getText().toString();
        intent.putExtra(city_name,city);
        startActivity(intent);
    }

}
