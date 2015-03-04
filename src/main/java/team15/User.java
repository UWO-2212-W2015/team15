package team15;
import java.util.ArrayList;

public class User {

    String name;
    Location currentLocation;
    Preferences userPreferences = new Preferences();
    //Use vector if threaded
    ArrayList <Location> locList = new ArrayList<Location>();

    public User (String s){
	name = s;
    }

    public void setName (String s) {
	this.name = s;
    }

    public String getName (){
	return name;
    }
    

    public ArrayList<Location> getLocationList(){
	return locList;
    }
    
    public void setCurrentLoc (Location loc){
	currentLocation = loc;
    }

    public Location getCurrentLocation(){
	return currentLocation;
    }

    public void addLocation(String input){
	Location newLoc = new Location(input);
	if (locList.size() == 0){
	    currentLocation = newLoc;
	}
	locList.add(newLoc);
    }
    
    public void removeLocation(Location loc){
	locList.remove(loc);
    }

    public Weather getCurrentWeather(){
	Weather w = currentLocation.getCurrentForecast();
	double t = Double.parseDouble(w.temperature);
	if (userPreferences.tempUnits == false){
	    t = t - 273.15;
	}else{
	    t = (t - 273.15) *1.8;
	}
	w.temperature = Double.toString(t);
	return w;
    }
    
    public ArrayList <Weather> getShortTermWeather(){
	ArrayList <Weather> weather =  currentLocation.getShortTermForecast();
	double t;
	for (int i = 0; i < weather.size(); i++){
	    if (userPreferences.tempUnits == false){
	    	weather.get(i).temperature = Double.toString(Double.parseDouble(weather.get(i).temperature) - 273.15);
	    }else{
		weather.get(i).temperature = Double.toString((Double.parseDouble(weather.get(i).temperature) - 273.15) * 1.8);
	    }
	}
	return weather;
	}
}
