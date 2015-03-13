package team15.User;

/**
 * The User object provides an api to interact with the underlying
 * weather application.  Users are identified by a unique name
 * to be provided when initialized.  Each user's preferences and location
 * are stored between program runs.
 *
 * @author Team 15
 */

//Imports
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
import team15.Weather.*;

public class User implements Serializable{
    public Preferences pref;
    private final ArrayList<Location> locations;
    private Location curLocation;

    /**
     * Creates a new default user object
     */
    public User(){
        pref = new Preferences();
        locations = new ArrayList();
        curLocation = new Location();
    }
    
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
        pref = new Preferences();
        locations = new ArrayList<Location>();
        curLocation = new Location(location);
        locations.add(curLocation);
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
        curLocation = locations.get(i);
    }

    /**
     * Returns the current location object that the user is viewing
     * @return the current location object that the user is viewing
     */
    public Location getCurrentLocation(){
    	return curLocation;
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
        //Check if the location is already in the list
        for(Location l: this.locations){
            if(location.equals(l.toString())) return false;
        }
        
        //Try to make the new location
        Location l = new Location(location);
        
        locations.add(l);
        
        return true;
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
    public Weather getLocalWeather(){
        return curLocation.getLocal();
    }
    
    /**
     * returns the short term forecast for the current location of the user
     * @return the short term forecast for the current location of the user
     */
    public Forecast getShortTermForecast(){
	return curLocation.getShortTerm();
    }
    /**
     * returns the long term forecast for the current location of the user
     * @return the long term forecast for the current location of the user
     */
    public Forecast getLongTermForecast(){
        return curLocation.getLongTerm();
    }
    
    /**
     * Saves the user object to user.dat
     * @throws IOException thrown if there is a problem loading the object
     * @throws FileNotFoundException thrown if the file user.dat does not exist
     */
    public void saveUser() throws IOException, FileNotFoundException{
        FileOutputStream fo = new FileOutputStream("user.dat");
        ObjectOutputStream out = new ObjectOutputStream(fo);
        out.writeObject(this);
        out.close();
    }            
    
    /**
     * Loads the user object from the file user.dat
     * @return a user object loaded from the file user.day
     * @throws IOException thrown if there is a problem saving the object
     * @throws FileNotFoundException thrown if the file user.dat does not exist
     * @throws ClassNotFoundException thrown if the class User is not found
     */
    public static User loadUser() throws IOException, FileNotFoundException, 
                                                         ClassNotFoundException{
        FileInputStream fi = new FileInputStream("user.dat");
        ObjectInputStream in = new ObjectInputStream(fi);
        User result = (User) in.readObject();
        return result;
    }  
}
