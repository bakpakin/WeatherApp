package com.example.calvin.weatherapp;

import java.util.ArrayList;

/**
 * Created by Calvin on 4/23/16.
 */
public class YearlyCityData {

    // The year that this data belongs to
    private int year;

    // Each of the array lists holds data for each day of the year
    private ArrayList<Double> rainfall; // centimeters

    // All temperatures celcius
    private ArrayList<Double> temperatureHighs;
    private ArrayList<Double> temperatureLows;
    private ArrayList<Double> temperatureAverages;
    private ArrayList<Double> humidity; // make up your own scale?
    private ArrayList<Double> clouds; // 0 is sunny, 100 is very cloudy

    public void setRainfallData(ArrayList<Double> rainfallData) {
        this.rainfall = rainfallData;
    }
    public void setTemperatureHighsData(ArrayList<Double> temperatureHighsData) {
        this.temperatureHighs = temperatureHighsData;
    }
    public void setTemperatureLowsData(ArrayList<Double> temperatureLowsData) {
        this.temperatureLows = temperatureLowsData;
    }
    public void setTemperatureAveragesData(ArrayList<Double> temperatureAveragesData) {
        this.temperatureAverages = temperatureAveragesData;
    }
    public void setHumidity(ArrayList<Double> humidityData) {
        this.humidity = humidityData;
    }
    public void setCloudsData(ArrayList<Double> cloudsData) {
        this.clouds = cloudsData;
    }
    public void setYear(int year) {
        this.year = year;
    }

    // day should be from 0 - 365. (Only leap years have a day 365)
    public double getRainfall(int day) {
        if (rainfall == null) return Double.NaN;
        return rainfall.get(day);
    }

    public double getTemperatureLows(int day) {
        if (temperatureLows == null) return Double.NaN;
        return temperatureLows.get(day);
    }

    public double getTemperatureAverages(int day) {
        if (temperatureAverages == null) return Double.NaN;
        return temperatureAverages.get(day);
    }

    public double getHumidity(int day) {
        if (humidity == null) return Double.NaN;
        return humidity.get(day);
    }

    public double getClouds(int day) {
        if (clouds == null) return Double.NaN;
        return clouds.get(day);
    }

    public double getTemperatureHighs(int day) {
        if (temperatureHighs == null) return Double.NaN;
        return temperatureHighs.get(day);
    }

    public DayCityData getDay(int day) {
        DayCityData data = new DayCityData();
        data.setClouds(getClouds(day));
        data.setHumidity(getHumidity(day));
        data.setRainfall(getRainfall(day));
        data.setTemperatureAverages(getTemperatureAverages(day));
        data.setTemperatureHighs(getTemperatureHighs(day));
        data.setTemperatureLows(getTemperatureLows(day));
        return data;
    }

    public int getYear() {
        return year;
    }
}
