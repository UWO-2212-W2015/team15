package team15;
import java.util.ArrayList;
import java.io.Serializable;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.lang.ClassNotFoundException;

/**
 * The Location Class is responsible for keeping track of the characteristics of the location. 
 * A Location object contains information such as the city, province, country, latitude, longitude
 * and the url to obtain the forecast. The users have Locations as their attributes (a set of locations).
 * @author Team 15
 * @version
 */

public class Location implements Serializable{
    static int locID = 0;
    String userSearch;
    String city;
    String province;
    String country;
    String httpLocation;
    int latitude, longitude;
    
    transient CurrentForecast currentForecast = new CurrentForecast();
    transient ShortTermForecast shortTermForecast = new ShortTermForecast();

    /** Constructor for the Location class. Receives the location 
     * and prepares the url. In this case the user/GUI can pass a string
     * containing the name of the city along with the name of the province 
     * and country or not. 
     * @param searchString The location.
     */
    public Location (String searchString){
    	this.userSearch = searchString;
    	this.httpLocation = userSearch.replace(" ", ",");
    	locID++;
    }

    /** Constructor for the Location class. Receives the location 
     * and prepares the url. Here the user/GUI passes the name of the 
     * city and the name of the country separately (two strings).
     * @param city The name of the city.
     * @param country The name of the country.
     */
    public Location (String city, String country){
    	this.city = city;
    	this.country = country;
        StringBuilder builder = new StringBuilder(city);
        builder.append(",");
        builder.append(country);
        this.httpLocation = builder.toString();
		locID++;
    }

    /** Constructor for the Location class. Receives the location 
     * and prepares the url. Here the user/GUI passes the name of the 
     * city, province and the name of the country separately (three strings).
     * @param city The name of the city.
     * @param province The name of the province.
     * @param country The name of the country.
     */
    public Location (String city, String province, String country){
    	this.city = city;
    	this.province = province;
    	this.country = country;
        StringBuilder builder = new StringBuilder(city);
        builder.append(",");
        builder.append(province);
        builder.append(",");
        builder.append(country);
        this.httpLocation = builder.toString();
        locID++;
    } 

    /** Returns the url for the chosen location.
     * @return httpLocation url for the location.
     */
    public String getHttpLocation (){
    	return httpLocation;
    }

    /** Returns the the name of the city.
     * @return city The name of the city.
     */
    public String getCityName(){
    	return city;
    }

    /** Returns the the name of the country.
     * @return country The name of the country.
     */
    public String getCountry(){
    	return country;
    }

    /** Returns the the name of the province.
     * @return province The name of the province.
     */
    public String getProvince() {
    	return province;
    }

    /** Returns the Current Forecast for the location.
     * @return currentForecast This represents a Weather object.
     */
    public Weather getCurrentForecast (){
    	currentForecast.updateForecast(this.httpLocation);
    	return currentForecast.getWeatherObject();
    }

    /** Returns the Short Term Forecast for the location.
     * @return shortTermForecast This represents an array of Weather object.
     */
    public ArrayList <Weather> getShortTermForecast(int num){
    	shortTermForecast.updateForecast (this.httpLocation);
    	return shortTermForecast.getWeatherList(num);
    }
    
    /** Reads an ObjectInputStream and creates the forecasts.
     * @param ois The ObjectInputStream
     */
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException{
    	ois.defaultReadObject();
    	currentForecast = new CurrentForecast();
    	shortTermForecast = new ShortTermForecast();
    }
}

