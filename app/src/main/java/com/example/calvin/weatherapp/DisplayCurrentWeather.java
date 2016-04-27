package com.example.calvin.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by calvin on 4/26/16.
 */
public class DisplayCurrentWeather extends AppCompatActivity {

    private TextView temperature;
    private TextView description;
    private TextView cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_weather);

        temperature = (TextView) findViewById(R.id.current_temp);
        description = (TextView) findViewById(R.id.weather_description);
        cityName = (TextView) findViewById(R.id.weather_city);

        Intent intent = getIntent();
        String city = intent.getStringExtra(MainActivity.city_name);
        cityName.setText(city);

        BackendConnector.instance().getWeatherByName(city, new CurrentBackendListener() {
            @Override
            public void onCurrentDataReceived(CurrentCityData data) {
                if (data == null) {
                    description.setText("no data found");
                    temperature.setText("??");
                } else {
                    description.setText(data.description);
                    temperature.setText(Integer.toString((int) data.temp) + "\u00b0C");
                    cityName.setText(data.name);
                }
            }
        });

    }
}
