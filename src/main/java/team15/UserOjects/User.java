package team15.UserOjects;

/**
 * The User object provides an api to interact with the underlying
 * weather application.  Users are identified by a unique name
 * to be provided when initialized.  Each user's preferences and location
 * are stored between program runs.
 *
 * @author team15
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
     * Returns the list of locations that are saved to this user. When dealing
     * with this list order should be preserved as the indexes are important
     * to the add and remove functions
     * @return the list of locations that are saved to this user
     */
    public ArrayList<Location> getLocations(){
    	return locations;
    }
    
    /**
     * Set the current location to the given location object
     * @param location the location that will be the new current location for
     * this user.
     * @return true is the location is changed, false otherwise
     */
    public boolean setCurrentLoc (Location location){
        if(!locations.contains(location)) return false;
        curLocation = location;
        return true;
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
     */
    public final boolean addLocation(Location location){
        //Check if the location is already in the list
        for(Location l: this.locations){
            if(location.equals(l)) return false;
        }

        locations.add(location); 
        return true;
    }
    
    /**
     * Add the given location to the location list at the given index
     * @param i the index to add the location
     * @param location the location object to be added
     * @return true is the object was added, false otherwise
     */
    public boolean addLocation(int i, Location location){
        if(locations.contains(location)) return false;
        
        locations.add(i, location);
        return true;
    }
    
    /**
     * removes the location from the list of locations
     * @param location the location object to be removed
     * @return true if the object is remove, false otherwise
     */
    public boolean removeLocation(Location location){
    	return locations.remove(location);
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