package com.example.calvin.weatherapp;

import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Calvin on 4/23/16.
 */
public class BackendConnector  {

    private static final String apibase = "https://cowweather.herokuapp.com/";
    private static BackendConnector instance = null;

    private RequestQueue requestQueue;

    private ArrayList<BackendListener> listeners;

    public static void initInstance(Context c) {
        instance = new BackendConnector(c);
    }

    public static BackendConnector getInstance() {
        if (instance == null) {
            System.err.println("Backend connector singleton instance not initialized.");
        }
        return instance;
    }

    private BackendConnector(Context c) {
        requestQueue = Volley.newRequestQueue(c);
    }

    public BackendListener addListener(BackendListener listener) {
        listeners.add(listener);
        return listener;
    }

    private YearlyCityData parseHistoryData(JSONObject obj) throws JSONException {
        // TODO
        // This is the meat of this class. Converts the JSON data into a YearlyCityObject.
        return null;
    }

    public void getCityDataByName(String name, int year) {
        final BackendConnector outerThis = this;
        JsonObjectRequest jsonRequest = new JsonObjectRequest
                (Request.Method.GET, apibase + name, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Parse the returned JSON object here...
                            System.out.println(response);
                            YearlyCityData data = outerThis.parseHistoryData(response);
                            for (BackendListener listener : listeners) {
                                listener.onCityDataReceived(data);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
        this.requestQueue.add(jsonRequest);
    }

    public void getCityDataByID(int id, int year) {
        final BackendConnector outerThis = this;
        JsonObjectRequest jsonRequest = new JsonObjectRequest
                (Request.Method.GET, apibase + id, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Parse the returned JSON object here...
                            System.out.println(response);
                            YearlyCityData data = outerThis.parseHistoryData(response);
                            for (BackendListener listener : listeners) {
                                listener.onCityDataReceived(data);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
        this.requestQueue.add(jsonRequest);
    }

}
