package team15.User;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.io.Serializable;
import java.net.MalformedURLException;
import org.json.JSONException;
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

public class User implements Serializable{
    private int locIndex;
    public Preferences pref;
    private final ArrayList<Location> locations;

    /**
     * Creates a new user object with the given initial location.
     * @param location 
     * @throws MalformedURLException thrown if any of the urls are
     * malformed
     * @throws IOException thrown if there is any problem interacting with the
     * OpenWeather api
     * @throws JSONException thrown if there is any problem using the json
     */
    public User (String location)
                       throws MalformedURLException, IOException, JSONException{
        locIndex = 0;
        pref = new Preferences();
        locations = new ArrayList<Location>();
        this.addLocation(location);
    }
    
    /**
     * Returns the list of locations that are saved to this user. When dealing
     * with this list order should be preserved as the indexes are important
     * to the add and remove functions
     * @return the list of locations that are saved to this user
     */
    public ArrayList<Location> getLocations(){
    	return locations;
    }
    
    /**
     * Set the current location to the given index
     * @param i the index of the new current location
     */
    public void setCurrentLoc (int i){
    	locIndex = i;
    }

    /**
     * Returns the current location object that the user is viewing
     * @return the current location object that the user is viewing
     */
    public Location getCurrentLocation(){
    	return locations.get(locIndex);
    }

    /**
     * Adds a location to the location list for this user
     * @param location the string that represents the location 
     * @return true is the location was added
     * @throws MalformedURLException thrown if any of the urls are
     * malformed
     * @throws IOException thrown if there is any problem interacting with the
     * OpenWeather api
     * @throws JSONException thrown if there is any problem using the json
     */
    public final boolean addLocation(String location)
                       throws MalformedURLException, IOException, JSONException{
        return locations.add(new Location(location));
    }
    
    /**
     * removes the location at the given index from the list of locations
     * @param i the index of the location object to be removed
     */
    public void removeLocation(int i){
    	locations.remove(i);
    }

    /**
     * returns the current weather for the current location of this user
     * @return the current weather for the current location of this user
     */
    public Weather getCurrentWeather(){
        return this.getCurrentLocation().getCurrent();
    }
    
    /**
     * returns the short term forecast for the current location of the user
     * @return the short term forecast for the current location of the user
     */
    public ArrayList<Weather> getShortTermWeather(){
	return this.getCurrentLocation().getShortTerm();
    }
    /**
     * returns the long term forecast for the current location of the user
     * @return the long term forecast for the current location of the user
     */
    public ArrayList<Weather> getLongTermWeather(){
        return this.getCurrentLocation().getLongTerm();
    }
    
    public void saveUser() 
                         throws IOException, FileNotFoundException, IOException{
        ObjectOutputStream out 
                = new ObjectOutputStream(new FileOutputStream("user.dat"));
        out.writeObject(this);
        out.close();
    }            
    
    public static User loadUser() throws IOException, FileNotFoundException, 
                                            IOException, ClassNotFoundException{
        ObjectInputStream in 
                       = new ObjectInputStream(new FileInputStream("user.dat"));
        User result = (User) in.readObject();
        return result;
    }  
}
