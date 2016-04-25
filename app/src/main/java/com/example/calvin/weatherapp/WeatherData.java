package com.example.calvin.weatherapp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import com.example.calvin.weatherapp.BackendConnector;

public class WeatherData {
    //fields
    public ArrayList<Double> temps;
    public ArrayList<Double> rainfall;
    public boolean leapyear;

    //constructors
    public WeatherData(){
        temps = null;
        rainfall = null;
        leapyear = false;
    }

    public WeatherData(String city){
        BackendConnector connector = new BackendConnector();
        connector.getCityDataByName(city, 2016);
        connector.addListener(new BackendListener() {
            @Override
            public void onCityDataReceived(YearlyCityData data) {
                temps.addAll(data.temperatureAverages);//fetch temps from city this year
                rainfall.addAll(data.rainfall);//fetch rainfall from city this year
            }
        });
    }

    public WeatherData(String city, int year){
        BackendConnector connector = new BackendConnector();
        connector.getCityDataByName(city, year);
        connector.addListener(new BackendListener() {
            @Override
            public void onCityDataReceived(YearlyCityData data) {
                temps.addAll(data.temperatureAverages);//fetch temps from city this year
                rainfall.addAll(data.rainfall);//fetch rainfall from city this year
            }
        });
    }

    public WeatherData(ArrayList<Double> my_temperatures, ArrayList<Double> my_rainfall){
        temps = my_temperatures;
        rainfall = my_rainfall;
    }

    // methods
    public double get_temp(int day) {
        return temps.get(day);
    }

    public ArrayList<Double> get_temp() {
        return temps;
    }

    public double get_rain(int day) {
        return rainfall.get(day);
    }
    public ArrayList<Double> get_rain() {
        return rainfall;
    }

    public void set_temp(double degrees, int day) {
        temps.set(day, degrees);
    }

    public void set_rain(double inches, int day) {
        rainfall.set(day, inches);
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
            five_yr_avg.set_temp(temp_day,i);
        }

        //Moving average to find best days_of_travel
        //MA = (Day-n + Day-(n+1) + ...+ Day + Day+1 + ... + Day+n)/2*n


        WeatherData moving_average = new WeatherData();
        //Temp moving average
        double temp_initialize = five_yr_avg.get_temp(0);
        ArrayList <Double> temp_window = new ArrayList(days_of_travel);
        for (int k = 0; k < days_of_travel; k++){
            temp_window.set(k, temp_initialize);
        }
        double temp_avg;
        for(int i = 0; i < 365; i++) {
                temp_window.remove(0);
                temp_window.add(five_yr_avg.get_temp(i));
                Double temp_sum = 0.0;

                for(int j = 0; j<days_of_travel; j++){
                    temp_sum+= temp_window.get(i);
                }
                temp_avg = temp_sum/days_of_travel;
                moving_average.set_temp(temp_avg, i);

            }

        //Rain moving average
        Double rain_initialize = five_yr_avg.get_rain(0);
        ArrayList <Double> rain_window = new ArrayList(days_of_travel);
        for (int k = 0; k < days_of_travel; k++) {
            rain_window.set(k, rain_initialize);
        }
        Double rain_avg;

    for(int i = 0; i<365; i++) {
            rain_window.remove(0);
            rain_window.add(five_yr_avg.get_rain(i));
            Double rain_sum = 0.0;
            for(int j = 0; j<days_of_travel; j++) {
                rain_sum += rain_window.get(j);
            }
        rain_avg = rain_sum/days_of_travel;
        moving_average.set_rain(rain_avg, i);
        }

        //Return ideal travel days to go
        ArrayList <Double> temp_difference = new ArrayList(365);
        for (int i = 0; i< 365; i++){
            temp_difference.set(i, moving_average.get_temp(i)- ideal_temp);
        }
        //1 cm in rain difference is worse than 1 degree in temp difference, so weight rain more heavily.
        Double range_temps = Collections.max(moving_average.get_temp())- Collections.min(moving_average.get_temp());
        Double range_rain = Collections.max(moving_average.get_rain())- Collections.min(moving_average.get_rain());
        Double weight = range_temps/range_rain;

        ArrayList <Double> weighted_rain = new ArrayList(365);
        for (int i = 0; i< 365; i++){
        weighted_rain.set(i, weight * moving_average.get_rain(i));
        }
        ArrayList <Double> optim_vector = new ArrayList(365);
        for (int i = 0; i<365; i++){
            optim_vector.set(i, temp_difference.get(i) + weighted_rain.get(i));
        }
        int minIndex = optim_vector.indexOf(Collections.min(optim_vector));

        //minIndex is the best day.  Best time to travel is minIndex + days_of_travel;
        //convert minIndex to date
        /*
        String month;
        int day;
        if (minIndex < 31){
            month = "January";
            day = minIndex+1;
        }
        else if (minIndex > 30 && minIndex < 59){
            month = "February";
            day = minIndex-30;
        }
        else if (minIndex > 58 && minIndex < 90){
            month = "March";
            day = minIndex - 58;
        }
        else if (minIndex > 89 && minIndex < 120){
            month = "April";
            day = minIndex-89;
        }
        else if (minIndex > 119 && minIndex < 151){
            month = "May";
            day = minIndex - 119;
        }
        else if (minIndex > 150 && minIndex < 181){
            month = "June";
            day = minIndex-150;
        }
        else if (minIndex > 180 && minIndex < 212){
            month = "July";
            day = minIndex-180;
        }
        else if (minIndex > 211 && minIndex < 243){
            month = "August";
            day = minIndex-211;
        }
        else if (minIndex > 242 && minIndex < 273){
            month = "September";
            day = minIndex-242;
        }
        else if (minIndex > 272 && minIndex < 304){
            month = "October";
            day = minIndex-273;
        }
        else if (minIndex > 303 && minIndex < 334){
            month = "November";
            day = minIndex-303;
        }
        else {
            month = "December";
            day = minIndex-333;
        }
*/
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_YEAR, minIndex+1);
        int startDayOfMonth = c.get(Calendar.DAY_OF_MONTH);
        int startMonthOfYear = c.get(Calendar.MONTH);

        Calendar d = Calendar.getInstance();
        d.set(Calendar.DAY_OF_YEAR, minIndex+1+days_of_travel);

        int endDayOfMonth = d.get(Calendar.DAY_OF_MONTH);
        int endMonthOfYear = d.get(Calendar.MONTH);

    }
}