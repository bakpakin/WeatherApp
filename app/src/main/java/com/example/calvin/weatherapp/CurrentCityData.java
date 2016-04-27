package com.example.calvin.weatherapp;

/**
 * Created by calvin on 4/27/16.
 */
public class CurrentCityData {

    public final double temp;
    public final String name;
    public final String description;

    public CurrentCityData(String name, double temp, String description) {
        this.name = name;
        this.temp = temp;
        this.description = description;
    }

}
