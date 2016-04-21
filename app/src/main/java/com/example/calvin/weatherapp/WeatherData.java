package com.example.calvin.weatherapp;

import java.util.ArrayList;

public class WeatherData {
    //fields
    public ArrayList<Double> temps;
    public ArrayList<Double> rainfall;

    //constructors
    public WeatherData(){
        temps = null;
        rainfall = null;
    }

    public WeatherData(String city){
        temps.add(1.1); //fetch temps from city this year;
        temps.add(1.1);
        temps.add(1.1);
        temps.add(1.1);
        temps.add(1.1);
        rainfall.add(1.1); //fetch rainfall from city this year;
        rainfall.add(1.1);
        rainfall.add(1.1);
        rainfall.add(1.1);
        rainfall.add(1.1);
    }

    public WeatherData(String city, int year){
        temps.add(1.1); //fetch temps from city this year;
        temps.add(1.1);
        temps.add(1.1);
        temps.add(1.1);
        temps.add(1.1);
        rainfall.add(1.1); //fetch rainfall from city this year;
        rainfall.add(1.1);
        rainfall.add(1.1);
        rainfall.add(1.1);
        rainfall.add(1.1);
    }

    public WeatherData(ArrayList<Double> my_temperatures, ArrayList<Double> my_rainfall){
        temps = my_temperatures;
        rainfall = my_rainfall;
    }

    // methods
    public double get_temp(int day) {
        double day_temp = temps.get(day);
        return day_temp;
    }

    public ArrayList<Double> get_temp() {
        return temps;
    }

    public double get_rain(int day) {
        double day_rain = rainfall.get(day);
        return day_rain;
    }

    public void set_temp(double degrees, int day) {
        temps.add(day, degrees);
    }

    public void set_rain(double inches, int day) {
        rainfall.add(day, inches);
    }

    public ArrayList<Double> get_rain() {
        return rainfall;
    }

    public WeatherData add(WeatherData weather){
        WeatherData weather_sum = null;

        return weather_sum;
    }

    public WeatherData average(WeatherData weather){
        WeatherData avg_weather = null;
        return avg_weather;
    }

    public static void doTest() {

        //input ideal temp, number of travel days and the city to travel to from app screen
        int ideal_temp = 65; //change this later
        int days_of_travel = 7; // change this later
        String destination_city = "Boston"; //change this later

        //Get 5 years of weather data from destination_city
        int year = 2016;
        WeatherData year1 = new WeatherData(destination_city, year);
        WeatherData year2 = new WeatherData(destination_city, year-1);
        WeatherData year3 = new WeatherData(destination_city, year-2);
        WeatherData year4 = new WeatherData(destination_city, year-3);
        WeatherData year5 = new WeatherData(destination_city, year-4);

        WeatherData five_yr_avg = new WeatherData();
        for (int i = 0; i<365; i++){
            double rain_day = (year1.get_rain(i) + year2.get_rain(i) + year3.get_rain(i) + year4.get_rain(i) + year5.get_rain(i)) /5;
            double temp_day = (year1.get_temp(i) + year2.get_temp(i) + year3.get_temp(i) + year4.get_temp(i) + year5.get_temp(i))/ 5;
            five_yr_avg.set_rain(rain_day,i);
            //five_yr_avg.set_temps(temp_day, i);
        }

        //Moving average to find best days_of_travel
        //MA = (Day-n + Day-(n+1) + ...+ Day + Day+1 + ... + Day+n)/2*n


        //Temp moving average

        for(int i = 0; i < 365; i++) {
            if (i < days_of_travel) {

            }
            else {
                five_yr_avg.get_temp(i);
            }
        }
        WeatherData moving_average = new WeatherData();

        int N = 10;
        double[] a = new double[N];
        double sum = 0.0;
        for (int i = 0; i < 365; i++) {
            sum -= a[i % N];
            a[i % N] = Math.random();
            sum += a[i % N];
            if (i >= N) moving_average.set_temp(sum/N,i);
        }



        //Rain moving average

        for(int i = 0; i<365; i++) {

        }

        //Return ideal travel days to go


    }
}