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
    public final static String days_s = "com.example.thomas.weatherapp.MESSAGE";
    public final static String temp_s = "com.example.thomas.weatherapp.DAYS";
    public final static String ppt_s = "com.example.thomas.weatherapp.PPT";
    public final static String city_name = "com.example.thomas.weatherapp.CITIES";

    private SeekBar myTemp;
    private TextView Tempfinal;
    private SeekBar myppt;
    private TextView Pptfinal;
    private AutoCompleteTextView CityName;

    EditText string_days;
    Button find_days;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SeekBar myTemp = (SeekBar) findViewById(R.id.user_temp);
        final TextView Tempfinal = (TextView) findViewById(R.id.text_temp);


        myTemp.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            int progresschange = 0;

            public void onProgressChanged(SeekBar mySeekBar, int progress, boolean fromUser) {
                progresschange = progress;
                Tempfinal.setText(Integer.toString(progresschange));
            }

            public void onStartTrackingTouch(SeekBar mySeekBar) {

            }

            public void onStopTrackingTouch(SeekBar mySeekBar) {

            }
        });

        SeekBar myppt = (SeekBar) findViewById(R.id.user_rain);
        final TextView Pptfinal = (TextView) findViewById(R.id.text_rain);


        myppt.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            int progresschange = 0;

            public void onProgressChanged(SeekBar mySeekBar, int progress, boolean fromUser) {
                progresschange = progress;
                Pptfinal.setText(Integer.toString(progresschange));
            }

            public void onStartTrackingTouch(SeekBar mySeekBar) {

            }

            public void onStopTrackingTouch(SeekBar mySeekBar) {

            }
        });

        // Get a reference to the AutoCompleteTextView in the layout
        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autocomplete_city);
        // Get the string array
        String[] cities = getResources().getStringArray(R.array.cities_array);
        // Create the adapter and set it to the AutoCompleteTextView
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cities);
        textView.setAdapter(adapter);

    }

    public void calc_ideal(View view) {



        Intent intent = new Intent(this, DisplayIdealDates.class);

        EditText user_input = (EditText) findViewById(R.id.user_days);
        String string_days = user_input.getText().toString();
        intent.putExtra(days_s,string_days);

        final TextView Tempfinal = (TextView) findViewById(R.id.text_temp);
        String user_temp = Tempfinal.getText().toString();
        intent.putExtra(temp_s,user_temp);

        final TextView Pptfinal = (TextView) findViewById(R.id.text_rain);
        String user_rain = Pptfinal.getText().toString();
        intent.putExtra(ppt_s,user_rain);

        final AutoCompleteTextView CityName = (AutoCompleteTextView) findViewById(R.id.autocomplete_city);
        String city = CityName.getText().toString();
        intent.putExtra(city_name,city);

        startActivity(intent);

    }

}
