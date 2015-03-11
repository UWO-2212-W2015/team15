package team15.User;

import java.util.ArrayList;
import java.io.Serializable;
import java.io.ObjectInputStream;
import java.io.IOException;

import team15.Weather.*;
import team15.Forecast.*;

/**
 * The Location Class is responsible for keeping track of the characteristics of the location. 
 * A Location object contains information such as the city, province, country, latitude, longitude
 * and the url to obtain the forecast. The users have Locations as their attributes (a set of locations).
 * @author Team 15
 * @version
 */

public class Location implements Serializable{
    String userSearch;
    String city;
    String province;
    String country;
    String httpLocation;
    int latitude, longitude;
    private WeatherBuilder wb;
    
    private transient CurrentForecast currentForecast;
    private transient MultiForecast shortTermForecast;
    private transient MultiForecast longTermForecast;

    /** Constructor for the Location class. Receives the location 
     * and prepares the url. In this case the user/GUI can pass a string
     * containing the name of the city along with the name of the province 
     * and country or not. 
     * @param searchString The location.
     */
    public Location (String searchString){
    	this.userSearch = searchString;
    	this.httpLocation = userSearch.replace(" ", ",");
        wb = new WeatherBuilder(this.httpLocation);
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
        this.httpLocation = city + "," + country;
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
        this.httpLocation = city + "," + province + "," + country;
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
        //Try to build a current weather object
        CurrentForecast temp;
        try{
            temp = new CurrentForecast(wb.buildCurrent());
            
            //Update the currentForecast
            currentForecast = temp;
        }
        catch(WeatherBuilderException e){
            e.printStackTrace();
        }
        return currentForecast.getWeather(); 
    }

    /** Returns the Short Term Forecast for the location.
     * @return shortTermForecast This represents an array of Weather object.
     */
    public ArrayList <Weather> getShortTermForecast(){
        //Try to build a new short term forecast
        MultiForecast temp = new MultiForecast(wb.buildShortTerm());
        //Update the shortTermForecast
        shortTermForecast = temp;
        
    	return shortTermForecast.getWeather();
    }
    
    /** Returns the long term forecast for the location.
     * @return longTermForecast This represents an array of Weather object.
     */
    public ArrayList<Weather> getLongTermForecast(){
        //Try to build a new long term forecast
        MultiForecast temp = new MultiForecast(wb.buildLongTerm());
        //Update the shortTermForecast
        longTermForecast = temp;
        
    	return longTermForecast.getWeather();
    }
    
    /** Reads an ObjectInputStream and creates the forecasts.
     * @param ois The ObjectInputStream
     */
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException{
    	ois.defaultReadObject();
    }
}

