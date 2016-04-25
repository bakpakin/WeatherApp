package com.example.calvin.weatherapp;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        BackendConnector.initInstance(this);

        // Test the Backend Connector:
        BackendConnector.getInstance().addListener(new BackendListener() {
            @Override
            public void onCityDataReceived(YearlyCityData data) {

            }
        });
        BackendConnector.getInstance().getCityDataByName("Boston", 1970);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
