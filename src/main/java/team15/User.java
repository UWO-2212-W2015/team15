<<<<<<< HEAD:src/User.java
import java.util.ArrayList;
=======
package team15;

import java.util.List;
>>>>>>> 5d93f0f532c5d11f32c0c048362055eb01906b22:src/main/java/team15/User.java

class User {

    String name;
    Location currentLocation;
    //Use vector if threaded
    ArrayList <Location> locList;

    User (String s){
	name = s;
	locList = new ArrayList<Location>();
    }

    void setName (String s) {
	this.name = s;
    }

    String getName (){
	return name;
    }
    
<<<<<<< HEAD:src/User.java
    ArrayList<Location> getLocationList(){
	return locList;
    }
    
    void setCurrentLoc (Location loc){
	currentLocation = loc;
=======
    Location getLocations(){
        return null; //Stub
>>>>>>> 5d93f0f532c5d11f32c0c048362055eb01906b22:src/main/java/team15/User.java
    }

    Location getCurrentLocation(){
	return currentLocation;
    }

    void addLocation(String input){
	Location newLoc = new Location(input);
	if (locList.size() == 0){
	    currentLocation = newLoc;
	}
	locList.add(newLoc);
    }
    
    void removeLocation(Location loc){
	locList.remove(loc);
    }
}
