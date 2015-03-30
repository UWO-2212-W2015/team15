package team15.WeatherObjects;

/**
 * Container that holds the weather data for a given location. 
 * The data includes the current weather, and the short and long term forecasts.
 * @author team15
 */

//Imports
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import team15.JSON.URLToJSON;
import team15.UserOjects.Location;

public class LocationWeather implements Serializable{
    //Update weather data constraints
    private final static long REFRESH = 600000;
    
    //URL variables
    private final String localURL, shortURL, longURL;
    
    //Forecast variables
    private Weather current;
    private Forecast shortTerm, longTerm;
    
    //Location variable
    private Location loc;
    
    //Time of last refresh
    private long lastRefresh;
    
    /**
     * Makes a new default object with filler forecast variables.
     */
    public LocationWeather(){
        localURL = "";
        shortURL = "";
        longURL = "";
        current = new Weather(Weather.WeatherType.LOCAL);
        shortTerm = new Forecast(Weather.WeatherType.SHORTTERM);
        longTerm = new Forecast(Weather.WeatherType.LONGTERM);
        lastRefresh = 0;
        loc = new Location();
    }
    
    /**
     * Makes a new LocationWeather object for the given location.
     * @param location the location that the data contained in this object
     * references
     * @see Location
     */
    public LocationWeather(Location location){
        this.loc = location;
        
        //Make the urls for each type of build
        String prefix = "http://api.openweathermap.org/data/2.5/";
        String id = loc.getID();
        this.localURL = prefix + "weather?id=" + id +"&units=metric";
        this.shortURL = prefix + "forecast?id=" + id 
	    + "&mode=json&units=metric";
        this.longURL = prefix + "forecast/daily?id=" + id + "&mode=json&units=metric&cnt=8";
        
        current = new Weather(Weather.WeatherType.LOCAL);
        shortTerm = new Forecast(Weather.WeatherType.SHORTTERM);
        longTerm = new Forecast(Weather.WeatherType.LONGTERM);
        
        //Try to load previous data from the cache
        try{
            this.load();
        }
        catch(Exception ex){
            lastRefresh = 0;
        }
        
        this.updateForecasts();
    }
    
    /**
     * A method to retrieve the current weather based on the Location object
     * associated with an instance of this class.
     * @return the weather object that represents the current weather
     */
    public Weather getLocal(){
        return current;
    }
    
    /**
     * Returns an array list of weather object that represents the a short term
     * forecast of the weather for the Location object associated with an instance
     * of this class.
     * @return an array list of weather object that represents the a short term
     * forecast
     */
    public Forecast getShortTerm(){
        return shortTerm;
    }
    
    /**
     * Returns an array list of weather object that represents the a long term
     * forecast of the weather for the Location object associated with an instance
     * of this class.
     * @return an array list of weather objects that represents the a long term
     * forecast
     */
    public Forecast getLongTerm(){
        return longTerm;
    }
    
    /**
     * Updates all the weather objects contained in the object. If any of them
     * fail to build none of them are updated.
     * @return A blank string if the weather was updated successfully otherwise 
     * an error message describing the error.
     */
    public final String updateForecasts(){        
        Long newRef = System.currentTimeMillis();
        String result = "";
        
        //Attempt to buiild new weather objects for the given location
        if((newRef - current.lastPoll) > REFRESH){
            try{
                Weather tempC = new Weather(URLToJSON.makeJSON(localURL), 
					    Weather.WeatherType.LOCAL);
                current = tempC;
            }
            catch(Exception ex){
                current.lastPoll = newRef;
                result = "Error updating weather data.";
            }
        }
        if((newRef - shortTerm.lastPoll) > REFRESH){
            try{
                Forecast tempS = new Forecast(URLToJSON.makeJSON(shortURL), 
					      Weather.WeatherType.SHORTTERM);
		shortTerm = tempS;
            }
            catch(Exception ex){
                shortTerm.lastPoll = newRef;
                result = "Error updating weather data.";
            }
        }
        if((newRef - longTerm.lastPoll) > REFRESH){
            try{
                Forecast tempL = new Forecast(URLToJSON.makeJSON(longURL), 
					      Weather.WeatherType.LONGTERM);
		longTerm = tempL;
            }
            catch(Exception ex){
                longTerm.lastPoll = newRef;
                result = "Error updating weather data.";
            }
        }
        
        //Updated the refresh time tracker
        lastRefresh = newRef;
        
        //Try to cache the new data
        try {
            this.save();
        } catch (IOException ex) {}           
        
        return result;
    }
    
    /**
     * Returns the date of the last time the weather objects were refreshed
     * @return a string representing the last time the weather objects were refreshed
     */
    public String getRefresh(){
        Date time = new Date(lastRefresh);
        return time.toString();
    }
    
    /**
     * Returns the string representation of the object. This is the same as
     * the Location that is contained as a variable in the object.
     * @return a string of the location object associated with this forecast
     */
    public String toString(){
        return loc.toString();
    }
    
    /**
     * Saves the LocationWeather object to the cache
     * @throws IOException thrown if there is a problem loading the object
     * @throws FileNotFoundException thrown if the file does not exist
     */
    private void save() throws IOException, FileNotFoundException{
        FileOutputStream fo = 
	    new FileOutputStream("WeatherCache/" + this.loc + ".dat");
        ObjectOutputStream out = new ObjectOutputStream(fo);
        out.writeObject(this);
        out.close();
    }            
    
    /**
     * Loads the LocationWeather object from the cache
     * @throws IOException thrown if there is a problem saving the object
     * @throws FileNotFoundException thrown if the file does not exist
     * @throws ClassNotFoundException thrown if the class LocationWeather is not
     * found
     */
    private void load() throws IOException, FileNotFoundException, 
			       ClassNotFoundException{
        FileInputStream fi 
	    = new FileInputStream("WeatherCache/" + this.loc + ".dat");
        ObjectInputStream in = new ObjectInputStream(fi);
        LocationWeather cached = (LocationWeather) in.readObject();
        
        this.current = cached.current;
        this.shortTerm = cached.shortTerm;
        this.longTerm = cached.longTerm;
        this.lastRefresh = cached.lastRefresh;
    } 
}
