import java.util.ArrayList;
package team15;

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
    

    ArrayList<Location> getLocationList(){
	return locList;
    }
    
    void setCurrentLoc (Location loc){
	currentLocation = loc;
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
