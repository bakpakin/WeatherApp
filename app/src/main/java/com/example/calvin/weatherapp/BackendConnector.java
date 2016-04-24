package com.example.calvin.weatherapp;

import java.util.ArrayList;

/**
 * Created by Calvin on 4/23/16.
 */
public class BackendConnector {

    private ArrayList<BackendListener> listeners;

    public BackendConnector() {

    }

    public BackendListener addListener(BackendListener listener) {
        listeners.add(listener);
        return listener;
    }

    public void getCityDataByName(String name, int year) {

    }

    public void getCityDataByID(int id, int year) {

    }


}
