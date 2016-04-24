package com.example.calvin.weatherapp;

import java.util.ArrayList;

/**
 * Created by Calvin on 4/23/16.
 */
public class DayCityData {

    private double rainfall;
    private double temperatureHighs;
    private double temperatureLows;
    private double temperatureAverages;
    private double humidity;
    private double clouds; // 0 is sunny, 100 is very cloudy

    public double getRainfall() {
        return rainfall;
    }

    public void setRainfall(double rainfall) {
        this.rainfall = rainfall;
    }

    public void setTemperatureHighs(double temperatureHighs) {
        this.temperatureHighs = temperatureHighs;
    }

    public double getTemperatureLows() {
        return temperatureLows;
    }

    public void setTemperatureLows(double temperatureLows) {
        this.temperatureLows = temperatureLows;
    }

    public double getTemperatureAverages() {
        return temperatureAverages;
    }

    public void setTemperatureAverages(double temperatureAverages) {
        this.temperatureAverages = temperatureAverages;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getClouds() {
        return clouds;
    }

    public void setClouds(double clouds) {
        this.clouds = clouds;
    }

    public double getTemperatureHighs() {
        return temperatureHighs;
    }
}
