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
 * @author team15
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
import team15.WeatherObjects.Weather.WeatherType;

public class Location implements Serializable{
    //Update weather data constraints
    private final static long REFRESH = 600000;
    private final static long POLL = 300000;
    
    //Local fields
    private String location;
    private final String id;
    
    //URL variables
    private final String localURL, shortURL, longURL;
    
    private Weather current;
    private Forecast shortTerm, longTerm;
    
    private long lastRefresh;
    
    /**
     * Creates a new blank location object
     */
    public Location(){
        location = "";
        id = "";
        localURL = "";
        shortURL = "";
        longURL = "";
        current = new Weather(WeatherType.LOCAL);
        shortTerm = new Forecast(WeatherType.SHORTTERM);
        longTerm = new Forecast(WeatherType.LONGTERM);
        lastRefresh = 0;
    }
    
    /**
     * Creates a new location object with the given search string. The search
     * string is input by the user.
     * @param country The country of the location
     * @param city the city/state/province of the location
     * @param lat the latitude of the location (blank if location has a unique
     * name.
     * @param lng the longitude of the location (blank if location has a unique
     * name.
     * @param id the openweather id of the current weather location
     */
    public Location 
              (String country, String city,  String id, String lat, String lng){
    	this.location = city + ", " + country;
        this.location += lat.isEmpty()?"":" (" + lat +", " + lng + ")";
        this.id = id;
        
        //Make the urls for each type of build
        String prefix = "http://api.openweathermap.org/data/2.5/";
        this.localURL = prefix + "weather?id=" + id;
        this.shortURL = prefix + "forecast?id=" + id + "&mode=json";
        this.longURL = prefix + "forecast/daily?id=" + id + location 
                + "&mode=json&units=metri&cnt=8";
        
        current = new Weather(WeatherType.LOCAL);
        shortTerm = new Forecast(WeatherType.SHORTTERM);
        longTerm = new Forecast(WeatherType.LONGTERM);
        
        lastRefresh = 0;
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
     * @return A blank string if the weather was updated successfully. An error
     * message telling.

     * @throws MalformedURLException thrown if any of the urls are
     * malformed
     * @throws IOException thrown if there is any problem interacting with the
     * OpenWeather api
     * @throws JSONException thrown if there is any problem using the json 
     */
    public final String updateForecasts() 
                       throws MalformedURLException, IOException, JSONException{
        Weather tempC;
        Forecast tempS, tempL;
        
        Long newRef = System.currentTimeMillis();
        //Make sure enough time has passed sine the last refresh
        if((newRef - lastRefresh) <= REFRESH) 
            return "Please wait at least 10 minutes before refresing.";
        
        //Attempt to buiild new weather objects for the given location
        if((newRef - current.created) > POLL){
            tempC = new Weather(URLToJSON.makeJSON(localURL), 
                    WeatherType.LOCAL);
            current = tempC;
        }
        if((newRef - shortTerm.created) > POLL){
            tempS = new Forecast(URLToJSON.makeJSON(shortURL), 
                    WeatherType.SHORTTERM);
            shortTerm = tempS;
        }
        if((newRef - longTerm.created) > POLL){
            tempL = new Forecast(URLToJSON.makeJSON(longURL), 
                    WeatherType.LONGTERM);
            longTerm = tempL;
        }
        
        //Updated the refresh time tracker
        lastRefresh = newRef;
        
        return "";
    }
    
    /**
     * Returns the date of the last time the weather objects were refreshed
     * @return the date of the last time the weather objects were refreshed
     */
    public String getRefresh(){
        Date time = new Date(lastRefresh);
        return time.toString();
    }
    
    /**
     * Returns the string representation of the object
     * @return the string representation of the object
     */
    public String toString(){
        return location;
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