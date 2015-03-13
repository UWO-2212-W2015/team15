package team15.User;

/**
 * The Location Class is responsible for keeping track of the characteristics of
 * the location. 
 * 
 * A Location object contains the weather objects representing the current
 * weather and the short term and long term forecasts. 
 * 
 * The users have Locations as their attributes (a set of locations).
 * 
 * @author Team15
 */

//Imports
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import org.json.JSONException;

import team15.Weather.*;
import team15.JSON.URLToJSON;

public class Location implements Serializable{
    private final String location;
    
    //URL variables
    private final String localURL;
    private final String shortURL;
    private final String longURL;
    
    private Weather current;
    private Forecast shortTerm, longTerm;

    /**
     * Creates a new blank location object
     */
    public Location(){
        location = "";
        localURL = "";
        shortURL = "";
        longURL = "";
        current = new Weather();
        shortTerm = new Forecast();
        longTerm = new Forecast();
    }
    
    /**
     * Creates a new location object with the given search string. The search
     * string is input by the user.
     * @param location A string representing the location 
     * (city,state,country) that the object represents
     * @throws MalformedURLException thrown if any of the urls are
     * malformed
     * @throws IOException thrown if there is any problem interacting with the
     * OpenWeather api
     * @throws JSONException thrown if there is any problem using the json 
     */
    public Location (String location) 
                       throws MalformedURLException, IOException, JSONException{
    	this.location = location;
        
        //Make the urls for each type of build
        String prefix = "http://api.openweathermap.org/data/2.5/";
        this.localURL = prefix + "weather?q=" + location;
        this.shortURL = prefix + "forecast?q=" + location + "&mode=json";
        this.longURL = prefix + "forecast/daily?q=" + location 
                + "&mode=json&units=metri&cnt=8";
        
        updateForecasts();
    }

    /**
     * Returns the location that the object represents
     * @return the location that the object represents
     */
    public String getLocation (){
    	return location;
    }

    /**
     * returns the weather object that represents the current weather at the
     * given location
     * @return the weather object that represents the current weather at the
     * given location
     */
    public Weather getLocal(){
        return current;
    }

    /**
     * Returns an array list of weather object that represents the a short term
     * forecast of the weather at the given location
     * @return an array list of weather object that represents the a short term
     * forecast of the weather at the given location
     */
    public Forecast getShortTerm(){
        return shortTerm;
    }
    
    /**
     * Returns an array list of weather object that represents the a long term
     * forecast of the weather at the given location
     * @return an array list of weather object that represents the a long term
     * forecast of the weather at the given location 
     */
    public Forecast getLongTerm(){
        return longTerm;
    }
    
    /**
     * Updates all the weather objects contained in the object. If any of them
     * fail to build none of them are updated and an error will be thrown.
     * @throws MalformedURLException thrown if any of the urls are
     * malformed
     * @throws IOException thrown if there is any problem interacting with the
     * OpenWeather api
     * @throws JSONException thrown if there is any problem using the json 
     */
    public final void updateForecasts() 
                       throws MalformedURLException, IOException, JSONException{
        Weather tempC;
        Forecast tempS, tempL;
        
        //Attempt to buiild new weather objects for the given location
        tempC = new Weather(URLToJSON.makeJSON(localURL), false);
        tempS = new Forecast(URLToJSON.makeJSON(shortURL));
        tempL = new Forecast(URLToJSON.makeJSON(longURL));
        
        /* If all the weather objects were creatd correctly then update the
         * local variables*/
        current = tempC;
        shortTerm = tempS;
        longTerm = tempL;
    }
    
    /**
     * Returns the string representation of the object
     * @return the string representation of the object
     */
    public String toString(){
        return location;
    }
}

