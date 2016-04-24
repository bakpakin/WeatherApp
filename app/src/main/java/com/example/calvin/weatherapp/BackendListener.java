package com.example.calvin.weatherapp;

/**
 * Created by Calvin on 4/23/16.
 */
public interface BackendListener {

    // This method is called when data is received from the server.
    // Implement this interface to get data.

    /* Example:
        BackendConnector connector = new BackendConnector();
        connector.getYearlyCityDataByName("Boston", 1970, new BackendListener() {
            public void onCityDataReceived(YearlyCityData data) {
                // Prints out rainfall on January 1, 1970. (day 0)
                System.out.println(data.getRainfall(0));
            }
        });
     */

    public void onCityDataReceived(YearlyCityData data);

}
