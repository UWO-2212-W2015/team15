package team15.UserOjects;

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
import java.util.Date;
import org.json.JSONException;
import team15.WeatherObjects.Forecast;

import team15.JSON.URLToJSON;
import team15.WeatherObjects.Weather;

public class Location implements Serializable{
    private final String location, id, coord, country;
    
    //URL variables
    private final String localURL, shortURL, longURL;
    
    private Weather current;
    private Forecast shortTerm, longTerm;
    
    private long refresh;
    
    /**
     * Creates a new blank location object
     */
    public Location(){
        location = "";
        country = "";
        id = "";
        coord="";
        localURL = "";
        shortURL = "";
        longURL = "";
        current = new Weather();
        shortTerm = new Forecast();
        longTerm = new Forecast();
        refresh = 0;
    }
    
    /**
     * Creates a new location object with the given search string. The search
     * string is input by the user.
     * @param location A string representing the location (city,country) that 
     * the object represents
     * @param id the openweather id of the current weather location
     */
    public Location (String country, String prov,  String id, String lat, String lng){
    	this.location = prov + ", " + country;
        this.country = country;
        this.id = id;
        this.coord = lat.isEmpty()?"":" (" + lat +", " + lng + ")";
        
        //Make the urls for each type of build
        String prefix = "http://api.openweathermap.org/data/2.5/";
        this.localURL = prefix + "weather?id=" + id;
        this.shortURL = prefix + "forecast?id=" + id + "&mode=json";
        this.longURL = prefix + "forecast/daily?id=" + id + location 
                + "&mode=json&units=metri&cnt=8";
        current = new Weather();
        shortTerm = new Forecast();
        longTerm = new Forecast();
        refresh = 0;
    }

    /**
     * Returns the location that the object represents
     * @return the location that the object represents
     */
    public String getLocation (){
    	return location;
    }

    /**
     * Returns the country value of this location
     * @return the country value of this location
     */
    public String getCountry(){
        return country;
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
     * @return true if the forecasts were updated, false if the process was
     * stopped because the last refresh request was less than 1h ago.
     * 
     * (this is the time that openweather expects a single location to be 
     * polled)
     * 
     * @throws MalformedURLException thrown if any of the urls are
     * malformed
     * @throws IOException thrown if there is any problem interacting with the
     * OpenWeather api
     * @throws JSONException thrown if there is any problem using the json 
     */
    public final boolean updateForecasts() 
                       throws MalformedURLException, IOException, JSONException{
        Weather tempC;
        Forecast tempS, tempL;
        
        Long newRef = System.currentTimeMillis();
        //Make sure at least one hour has passed since last refresh.
        if((newRef - refresh) <= 3600000){
            return false;
        }
        
        //Attempt to buiild new weather objects for the given location
        try{
            tempC = new Weather(URLToJSON.makeJSON(localURL), false);
            tempS = new Forecast(URLToJSON.makeJSON(shortURL));
            tempL = new Forecast(URLToJSON.makeJSON(longURL));
        } catch(IOException ex){
            refresh = newRef;
            throw new IOException();
        }
        
        /* If all the weather objects were creatd correctly then update the
         * local variables*/
        refresh = newRef;
        current = tempC;
        shortTerm = tempS;
        longTerm = tempL;
        
        return true;
    }
    
    /**
     * Returns the date of the last time the weather objects were refreshed
     * @return the date of the last time the weather objects were refreshed
     */
    public String getRefresh(){
        Date time = new Date(System.currentTimeMillis());
        return time.toString();
    }
    
    /**
     * Returns the string representation of the object
     * @return the string representation of the object
     */
    public String toString(){
        return location + coord;
    }
    
    /**
     * A method the checks if this Location object is equal to the given object.
     * Two locations are equal if their location string is the same
     * @param o the object to check against this location
     * @return true if o is a Location object and it has the same location
     * string as this object, false otherwise.
     */
    public boolean equals(Object o){
        if(!(o instanceof Location)) return false;
        
        Location l = (Location) o;
        
        String s1 = this.id;
        String s2 = l.id;
        return s1.equals(s2);
    }
    
    
}

