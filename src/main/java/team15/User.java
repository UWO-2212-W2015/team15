package team15;

import java.util.List;

class User {

    String name;
    //Use vector if threaded
    List <Location> locList;
    void setName (String s) {
	this.name = s;
    }

    String getName (){
	return name;
    }
    
    Location getLocations(){
        return null; //Stub
    }

    void addLocation(){
    }

    void removeLocation(){
    }
}
