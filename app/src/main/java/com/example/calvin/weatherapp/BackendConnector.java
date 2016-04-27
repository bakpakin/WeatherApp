package com.example.calvin.weatherapp;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Calvin on 4/23/16.
 */
public class BackendConnector {

    private static BackendConnector instance;

    public static BackendConnector instance() {
        return instance;
    }

    public static void initInstance(Context c) {
        instance = new BackendConnector(c);
    }

    private RequestQueue queue;

    private BackendConnector(Context c) {
        queue = Volley.newRequestQueue(c);
    }

    public void getCityDataByName(String name, int year, YearlyBackendListener l) {
        getCityDataByID(0, year, l);
    }

    public void getCityDataByID(int id, int year, YearlyBackendListener l) {
        ArrayList<Double> htemp = new ArrayList<>();
        ArrayList<Double> ptemp = new ArrayList<>();
        for (int i = 0; i < DummyData.temp.length; i++) {
            htemp.add(DummyData.temp[i]);
        }
        for (int i = 0; i < DummyData.precip.length; i++) {
            ptemp.add(DummyData.precip[i]);
        }
        YearlyCityData data = new YearlyCityData();
        data.setYear(year);
        data.setTemperatureHighsData(htemp);
        data.setRainfallData(ptemp);
        l.onCityDataReceived(data);
    }

    public void getWeatherByName(String name, final CurrentBackendListener listener) {
        queue.add(new CustomRequest("https://cowweather.herokuapp.com/" + name, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray list = response.getJSONArray("list");
                    JSONObject currentWeather = list.getJSONObject(0);
                    JSONObject mainInfo = currentWeather.getJSONObject("main");
                    JSONObject weather = currentWeather.getJSONArray("weather").getJSONObject(0);
                    double tempCelcius = mainInfo.getDouble("temp") - 273.15;
                    String description = weather.getString("description");
                    listener.onCurrentDataReceived(new CurrentCityData(tempCelcius, description));
                } catch (JSONException e) {
                    Log.d("JSON Error", e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("VolleyError", error.getMessage());
            }
        }));
    }


}
