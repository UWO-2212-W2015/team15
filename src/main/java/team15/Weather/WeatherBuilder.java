package team15.Weather;

/**
 * The WeatherBuilder Class is responsible for extracting the information in the 
 * json object and building Weather objects. Variables such as temperature, 
 * humidity, condition of the sky, air direction, air speed and icon are the 
 * target. 
 * 
 * The WeatherBuilder returns a Weather object or an array of Weather objects 
 * according to the type of forecast.
 *  
 * @author Team 15
 */

//Imports
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.net.URL;
import java.net.MalformedURLException;

import team15.JSON.URLToJSON;

public class WeatherBuilder{
    //Number of weather objects in a forecast
    private static final int NUM_FORECAST = 8;
    
    //URL variables
    private final String localURL;
    private final String shortTermURL;
    private final String longTermURL;
	
    /**
     * Creates a new weather builder that can poll from OpenWeather each of
     * the three different forecasts from the given location
     * @param loc The location that the weather builder is building weather
     * objects for
     */
    public WeatherBuilder(String loc){
        String prefix = "http://api.openweathermap.org/data/2.5/";

        //Make the urls for each type of build;
        localURL = prefix + "weather?q=" +loc;
        shortTermURL = prefix + "forecast?q=" + loc + "&mode=json";
        longTermURL =prefix + "forecast/daily?q=" + loc 
                +"&mode=json&units=metri&cnt=8";
    }
    
    /**
     * Builds a single weather object representing the current local weather
     * for the given location.
     * @return the weather object representing the local weather
     * @throws MalformedURLException thrown if any of the urls are
     * malformed
     * @throws IOException thrown if there is any problem interacting with the
     * OpenWeather api
     * @throws JSONException thrown if there is any problem using the json 
     */
    public Weather buildCurrent() 
                       throws MalformedURLException, IOException, JSONException{
        JSONObject currentWeather = URLToJSON.makeJSON(localURL);
        return createWeather(currentWeather, true);
    } 

    /**
     * Builds are array of objects that represents a 24 forecast in 3h
     * increments. 
     * @return An array of weather objects representing a short term forecast
     * @throws MalformedURLException thrown if any of the urls are
     * malformed
     * @throws IOException thrown if there is any problem interacting with the
     * OpenWeather api
     * @throws JSONException thrown if there is any problem using the json 
     */
    public ArrayList<Weather> buildShortTerm() 
                       throws MalformedURLException, IOException, JSONException{
        return buildForecast(false);
    }
    
    /**
     * Builds are array of objects that represents a n 8 day forecast. 
     * @return An array of weather objects representing a long term forecast
     * @throws MalformedURLException thrown if any of the urls are
     * malformed
     * @throws IOException thrown if there is any problem interacting with the
     * OpenWeather api
     * @throws JSONException thrown if there is any problem using the json 
     */
    public ArrayList<Weather> buildLongTerm() 
                       throws MalformedURLException, IOException, JSONException{
        return buildForecast(true);
    }
    
    /**
     * Builds a forecast of the given type.
     * @param type True = long term, False = short term
     * @return An array of weather objects representing the given forecast
     * @throws MalformedURLException thrown if any of the urls are
     * malformed
     * @throws IOException thrown if there is any problem interacting with the
     * OpenWeather api
     * @throws JSONException thrown if there is any problem using the json
     */
    private ArrayList<Weather> buildForecast(boolean type)
                       throws MalformedURLException, IOException, JSONException{
        JSONObject forecast = URLToJSON.makeJSON(type?longTermURL:shortTermURL);
        ArrayList<Weather> result = new ArrayList(NUM_FORECAST);
        
         //Pick out the array list
        JSONArray subArray = forecast.getJSONArray("list");
        
        //Create the requires number of weather objects for the forecast
        for (int i = 0; i < NUM_FORECAST; i++){
            result.add(createWeather(subArray.getJSONObject(i), false));
        }
        
        return result;
    }
    
   /**
    * From a JSON object pulls the values at the specified keys and makes a
    * weather of object.
    * @param j The JSON object to pulls values from
    * @param current True = If the Weather object represents the current
    * weather, False = if the weather object represents a segment of time in
    * a forecast
    * @return a weather object created from the values in the json object
     * @throws MalformedURLException thrown if any of the urls are
     * malformed
     * @throws JSONException thrown if there is any problem using the json
    */
    private Weather createWeather(JSONObject j, boolean current) 
                                     throws MalformedURLException,JSONException{
        Weather result = new Weather();

        JSONObject temp;

        //Set sky condition and sky icon
        temp = j.getJSONArray("weather").getJSONObject(0);
        result.skyCondition = temp.get("description").toString();
        result.icon = new ImageIcon(new URL("http://openweathermap.org/img/w/" +
                temp.get("icon") + ".png"));

        //Set temperature values

        //Set long term termpriture values
        if(j.has("temp")){
            temp = j.getJSONObject("temp");
            result.setTemp(temp.get("day").toString());
            result.setMinTemp(temp.get("min").toString());
            result.setMaxTemp(temp.get("max").toString());
        }
        //Set short and current tempriture values
        else{
            temp = j.getJSONObject("main");
            result.setTemp(temp.get("temp").toString());
        }

        if(!current) return result;

        //Set current min/max tempriture
        result.setMinTemp(temp.get("temp_min").toString());
        result.setMaxTemp(temp.get("temp_max").toString());

        //Set current pressure and humidity
        result.humidity = temp.get("humidity").toString();
        result.airPressure = temp.get("pressure").toString();

        //Set sunrise and sunset for current
        temp = j.getJSONObject("sys");
        result.sunrise = temp.get("sunrise").toString();
        result.sunset = temp.get("sunset").toString();

        //Set windspeed and degree for current
        temp = j.getJSONObject("wind");
        result.windSpeed = temp.get("speed").toString();
        result.windDirection = temp.get("deg").toString();
            
        return result;
    }
}

