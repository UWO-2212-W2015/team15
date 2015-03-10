package team15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * The JSONFetcher Class, when instantiated, returns a Json object depending on 
 * the request of a WeatherBuilder object. The current, short and long term 
 * forecast are the information available. Specific internal links
 * for each kind of forecast make possible to obtain that information according
 * to the location that was provided.
 *
 * @author Team 15
 * @version
 */

public class JSONFetcher extends JSONObject{
    /** Returns the json representing either the current weather, a short term
     * forcast or a long term forcest
     * @param location The location for the current weather forecast.
     * @param type "local", "shortterm", "longterm" based on which type of
     * json object you wish to create
     * @return  A Json object containing the information or null if there was
     * an error creating the JSON
     */
    public static JSONObject makeJSON(String location,String type){
        String url = "http://api.openweathermap.org/data/2.5/";

        //Makes the url based on the specified type of json object we
        //wish to fetch
        if(type.equals("local"))
            url += "weather?q="+location;
        else if(type.equals("shortterm"))
            url += "forecast?q="+location+"&mode=json";
        else if(type.equals("longterm"))
            url += "forecast/daily?q="+location
                    +"&mode=json&units=metri&cnt=8";

        URL in;
        URLConnection c;
        BufferedReader input;

        //Attempt to open the url connection and creater a reader for the
        //text in the url
        try {
            in = new URL(url); //Create a new url to openweather
            //Open the connection to openweather
            c = in.openConnection();
            //create a new input reader to read from the r
            input = new BufferedReader(
                    new InputStreamReader(c.getInputStream()));    
        } 
        catch (IOException e) {
            System.out.println("Error opening connection to URL");
            e.printStackTrace();
            return null;
        } 
        
        //Attempt to read from the URL and create the JSON from the given string
        try{
            return new JSONObject(input.readLine());
        }
        catch(JSONException e){
            System.out.println("Error creating the JSON");
            e.printStackTrace();
            return null;
        }
        catch (IOException e) {
            System.out.println("Error reading from the URL");
            e.printStackTrace();
            return null;
        } 
    }
}

