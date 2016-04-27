package com.example.calvin.weatherapp;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by calvin on 4/26/16.
 */
public interface VacationIntervalListener {

    void onIntervalReceived(ArrayList<Calendar> interval);

}
