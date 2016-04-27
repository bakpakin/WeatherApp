package com.example.calvin.weatherapp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class WeatherData {

    //fields
    public ArrayList<Double> temps;
    public ArrayList<Double> rainfall;
    public boolean leapyear;

    public WeatherData() {
        this(
                new ArrayList<>(Collections.nCopies(366, 0.0)),
                new ArrayList<>(Collections.nCopies(366, 0.0)));
    }

    public WeatherData(ArrayList<Double> my_temperatures, ArrayList<Double> my_rainfall) {
        temps = my_temperatures;
        rainfall = my_rainfall;
        leapyear = false;
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

    public static void getDates(final double ideal_temp, final int days_of_travel, String destination_city, final VacationIntervalListener listener) {

        final int currentYear = 2016;
        final int numberOfReferenceYears = 5;
        final double ideal_rain = 0;
        final double rain_multiplier = 2.0;
        final double temp_multiplier = 1.0;

        YearlyBackendListener l = new YearlyBackendListener() {

            private int yearsReceived = 0;
            private WeatherData[] multiYearData = new WeatherData[numberOfReferenceYears];

            @Override
            public void onCityDataReceived(YearlyCityData data) {

                WeatherData wd = new WeatherData(data.temperatureHighs, data.rainfall);
                multiYearData[currentYear - data.getYear()] = wd;
                yearsReceived++;

                // If all of the data for each year has been retrieved...
                if (yearsReceived >= numberOfReferenceYears) {

                    WeatherData five_yr_avg = new WeatherData();
                    for (int i = 0; i < 365; i++) {
                        double temp_day = 0;
                        double rain_day = 0;
                        for (int j = 0; j < numberOfReferenceYears; j++) {
                            rain_day += multiYearData[j].get_rain(i);
                            temp_day += multiYearData[j].get_temp(i);
                        }
                        five_yr_avg.set_rain(rain_day / numberOfReferenceYears, i);
                        five_yr_avg.set_temp(temp_day / numberOfReferenceYears, i);
                    }
                    //Moving average to find best days_of_travel
                    //MA = (Day-n + Day-(n+1) + ...+ Day + Day+1 + ... + Day+n)/2*n

                    WeatherData moving_average = new WeatherData();
                    //Temp moving average

                    ArrayList<Double> scores = new ArrayList<>();
                    for (int i = 0; i < 365; i++) {
                        double rain_sum = 0.0;
                        double temp_sum = 0.0;
                        for (int j = 0; j < days_of_travel; j++) {
                            rain_sum += five_yr_avg.get_rain((i + j) % 365);
                            temp_sum += five_yr_avg.get_temp((i + j) % 365);
                        }
                        double rain_score = Math.abs((rain_sum / days_of_travel) - ideal_rain) * rain_multiplier;
                        double temp_score = Math.abs((temp_sum / days_of_travel) - ideal_temp) * temp_multiplier;
                        scores.add(rain_score + temp_score);
                    }
                    int minIndex = scores.indexOf(Collections.min(scores));

//                    ArrayList<Double> temp_window = new ArrayList<>(Collections.nCopies(days_of_travel, five_yr_avg.get_temp(0)));
//
//                    double temp_avg;
//                    for (int i = 0; i < 365; i++) {
//                        temp_window.remove(0);
//                        temp_window.add(five_yr_avg.get_temp(i));
//                        Double temp_sum = 0.0;
//
//                        for (int j = 0; j < days_of_travel; j++) {
//                            temp_sum += temp_window.get(j);
//                        }
//                        temp_avg = temp_sum / days_of_travel;
//                        moving_average.set_temp(temp_avg, i);
//
//                    }
//
//                    //Rain moving average
//                    Double rain_initialize = five_yr_avg.get_rain(0);
//                    ArrayList<Double> rain_window = new ArrayList<>(Collections.nCopies(365, 0.0));
//                    for (int k = 0; k < days_of_travel; k++) {
//                        rain_window.set(k, rain_initialize);
//                    }
//                    Double rain_avg;
//
//                    for (int i = 0; i < 365; i++) {
//                        rain_window.remove(0);
//                        rain_window.add(five_yr_avg.get_rain(i));
//                        Double rain_sum = 0.0;
//                        for (int j = 0; j < days_of_travel; j++) {
//                            rain_sum += rain_window.get(j);
//                        }
//                        rain_avg = rain_sum / days_of_travel;
//                        moving_average.set_rain(rain_avg, i);
//                    }
//
//                    //Return ideal travel days to go
//                    ArrayList<Double> temp_difference = new ArrayList<>(Collections.nCopies(365, 0.0));
//                    for (int i = 0; i < 365; i++) {
//                        temp_difference.set(i, moving_average.get_temp(i) - ideal_temp);
//                    }
//                    //1 cm in rain difference is worse than 1 degree in temp difference, so weight rain more heavily.
//                    Double range_temps = Collections.max(moving_average.get_temp()) - Collections.min(moving_average.get_temp());
//                    Double range_rain = Collections.max(moving_average.get_rain()) - Collections.min(moving_average.get_rain());
//                    Double weight = range_temps / range_rain;
//
//                    ArrayList<Double> weighted_rain = new ArrayList<>(Collections.nCopies(365, 0.0));
//                    for (int i = 0; i < 365; i++) {
//                        weighted_rain.set(i, weight * moving_average.get_rain(i));
//                    }
//                    ArrayList<Double> optim_vector = new ArrayList<>(Collections.nCopies(365, 0.0));
//                    for (int i = 0; i < 365; i++) {
//                        optim_vector.set(i, temp_difference.get(i) + weighted_rain.get(i));
//                    }
//                    int minIndex = optim_vector.indexOf(Collections.min(optim_vector));

                    Calendar cal1 = Calendar.getInstance();
                    cal1.set(Calendar.DAY_OF_YEAR, minIndex + 1);
                    Calendar d = Calendar.getInstance();
                    d.set(Calendar.DAY_OF_YEAR, minIndex + 1 + days_of_travel);
                    ArrayList<Calendar> travel_days = new ArrayList<>();
                    travel_days.add(cal1);
                    travel_days.add(d);

                    listener.onIntervalReceived(travel_days);
                }
            }
        };

        for (int i = 0; i < numberOfReferenceYears; i++) {
            BackendConnector.instance().getCityDataByName(destination_city, currentYear - i, l);
        }

    }
}