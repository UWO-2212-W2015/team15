package team15.User;

import java.util.ArrayList;
import java.io.Serializable;
import team15.Weather.Weather;

/**
 * The User object provides an api to interact with the underlying
 * weather application.  Users are identified by a unique name
 * to be provided when initialized.  Each user's preferences and location
 * are stored between program runs.
 *
 * @author Team 15
 * @version
 */

public class User implements Serializable {

    String name;
    Location currentLocation;
    public Preferences userPreferences;
    //Use vector if threaded
    ArrayList <Location> locList = new ArrayList<Location>();

    /** Main constructor for the user class.  A unique user name
     * is to be provided (need to implement).
     *
     * @param userName  Name of the user
     */
      
    public User (String userName){
        this.userPreferences = new Preferences();
    	name = userName;
    }
    
    /** Returns the current user's name.
     * @return  A string containing the user's name
     */
    public String getName (){
    	return name;
    }
    
    /** Returns the user's list of locations as an
	{@link ArrayList}.
     * @return ArrayList of the user's locations.
     */
    public ArrayList<Location> getLocationList(){
    	return locList;
    }
    
    /** Set the current user's preferred location.
     * @param loc A location object that specifies the user's new preferred location.
     */
    public void setCurrentLoc (Location loc){
    	currentLocation = loc;
    }

    /** Returns the users preferred location.
     * @return A location object that represents the user's current preferred location
     */
    public Location getCurrentLocation(){
    	return currentLocation;
    }

    /** Add a new location to the list of user's locations.
     * If the user does not have a current location, the new location added
     * will be set as the user's current preferred location.
     * The string should contain at least a city and country, but may also
     * contain a province/state.
     * @param location A string specifying the user's location
     */
    public void addLocation(String location){
		Location newLoc = new Location(location);
		if (locList.size() == 0){
		    currentLocation = newLoc;
		}
		locList.add(newLoc);
    }
    
    /** Allows the user to remove a specified location from their list.
     * @param loc A location object that specifies the location to remove
     */
    public void removeLocation(Location loc){
    	locList.remove(loc);
    }

    /** Returns a single weather object that contains the most recent weather
     * information for the user's current preferred location.
     * The method also converts the weather's temperature to celsius.
     *
     * @return A Weather object with the most recent weather data for the current location
     */
    public Weather getCurrentWeather(){
        return currentLocation.getCurrentForecast();
    }
    
    /** Returns an ArrayList of Weather objects.  The number of Weather objects
     * returned need to be specified.  The temperature is converted from kelvin to celsius.
     * @param num Number of Weather objects, in 3 hours increment to be returned.
     * @return An ArrayList of Weather objects.
     */
    public ArrayList<Weather> getShortTermWeather(){
	return currentLocation.getShortTermForecast();
    }
    
    public ArrayList<Weather> getLongTermWeather(){
        return currentLocation.getLongTermForecast();
    }
}
