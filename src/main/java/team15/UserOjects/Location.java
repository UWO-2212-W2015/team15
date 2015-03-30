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
import java.io.Serializable;

public class Location implements Serializable{
    
    //Version 1 of Location
    private static final long serialVersionUID = -6023922766430552215L;
    //Local fields
    private String location;
    private String country;
    private final String id;
    
    /**
     * Creates a new blank location object
     */
    public Location(){
        location = "N/A";
        country = "";
        id = "";
    }
    
    /**
     * Creates a new location object with the given search string. The search
     * string is input by the user.
     * @param country The country of the location
     * @param city the city/state/province of the location
     * @param id the openweather id of the current weather location
     * @param gps
     */
    public Location (String country, String city,  String id, String gps){
    	this.location = city + ", " + country;
        this.location += gps.isEmpty()?"":(" " + gps);
        this.country = country;
        this.id = id;    
    }

    /**
     * Returns the country that the location is in
     * @return the country that the location is in
     */
    public String getCountry(){
        return this.country;
    }
    
    /**
     * Returns the OpenWeather location Id for this location
     * @return the OpenWeather location Id for this location
     */
    public String getID(){
        return this.id;
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
