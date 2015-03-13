package team15.Weather;

/**
 * The Weather class is constituted by variables present in the weather forecast
 * 
 * Even though it is a small class, it is necessary in order to have a 
 * unified class which supports all kinds of weather forecast.
 *
 * @author Team 15
 */

//Imports
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import javax.swing.ImageIcon;
import org.json.JSONException;
import org.json.JSONObject;

public class Weather implements Serializable{
    //Temperatures
    private double temp, minTemp, maxTemp;
    
    //Wind
    public String windSpeed, windDirection;
    
    //Sky
    public String airPressure, humidity, skyCondition;
    public ImageIcon icon;
    
    //Time
    public String sunrise, sunset, time;
    
    /**
     * Creates an instance of the Weather, class, setting all values to 0 or
     * a default object.
     */
    public Weather(){
        temp = 0;
        minTemp = 0;
        maxTemp = 0;
        
        windSpeed = "";
        windDirection = "";
        
        airPressure = "";
        humidity = "";
        skyCondition = "";
        icon = new ImageIcon();
        
        sunrise = "";
        sunset = "";
        time = "";
    }
    /**
     * Creates new weather object from the given json object
     * @param j the json object containing the weather data
     * @param forecast true if the weather object is in a forecast, false is not
     * @throws MalformedURLException thrown if any of the urls are
     * malformed
     * @throws IOException thrown if there is any problem interacting with the
     * OpenWeather api
     * @throws JSONException thrown if there is any problem using the json 
     */
    public Weather(JSONObject j, boolean forecast) 
                       throws JSONException, MalformedURLException, IOException{
        temp = 0;
        minTemp = 0;
        maxTemp = 0;
        
        windSpeed = "";
        windDirection = "";
        
        airPressure = "";
        humidity = "";
        skyCondition = "";
        icon = new ImageIcon();
        
        sunrise = "";
        sunset = "";
        time = "";
        
        JSONObject tempJ;
        
        //Set the time value
        Date tempT = new Date();
        tempT.setTime((long)1000*Integer.valueOf(j.get("dt").toString()));
        this.time = tempT.toString();
        
        //Set sky condition and sky icon
        tempJ = j.getJSONArray("weather").getJSONObject(0);
        this.skyCondition = tempJ.get("description").toString();
        this.icon = new ImageIcon(new URL("http://openweathermap.org/img/w/" +
                tempJ.get("icon") + ".png"));

        //Set temperature values

        //Set long term termpriture values
        if(j.has("temp")){
            tempJ = j.getJSONObject("temp");
            this.setTemp(tempJ.get("day").toString());
            this.setMinTemp(tempJ.get("min").toString());
            this.setMaxTemp(tempJ.get("max").toString());
        }
        //Set short and current tempriture values
        else{
            tempJ = j.getJSONObject("main");
            this.setTemp(tempJ.get("temp").toString());
        }

        if(forecast) return;

        //Set current min/max tempriture
        this.setMinTemp(tempJ.get("temp_min").toString());
        this.setMaxTemp(tempJ.get("temp_max").toString());

        //Set current pressure and humidity
        this.humidity = tempJ.get("humidity").toString();
        this.airPressure = tempJ.get("pressure").toString();
        
        //Set sunrise and sunset for current
        tempJ = j.getJSONObject("sys");
        
        tempT.setTime((long)1000*Integer.valueOf(tempJ.get("sunrise").toString()));
        this.sunrise = tempT.toString().split(" ")[3];
        
        tempT.setTime((long)1000*Integer.valueOf(tempJ.get("sunset").toString()));
        this.sunset = tempT.toString().split(" ")[3];

        //Set windspeed and degree for current
        tempJ = j.getJSONObject("wind");
        this.windSpeed = tempJ.get("speed").toString();
        this.windDirection = tempJ.get("deg").toString();
    }
    
    /**
     * Set the temperature value of the weather object  
     * @param t the new temperature for the weather object
     */
    public final void setTemp(String t){
        this.temp = Double.valueOf(t);
    }
    
    /**
     * Set the minimum temperature value of the weather object  
     * @param t the new minimum temperature for the weather object
     */
    public final void setMinTemp(String t){
        this.minTemp = Double.valueOf(t);
    }

    /**
     * Set the maximum temperature value of the weather object  
     * @param t the new maximum temperature for the weather object
     */
    public final void setMaxTemp(String t){
        this.maxTemp = Double.valueOf(t);
    }
    
    /**
     * Returns the temperature value of the weather object in the specified
     * system (Celsius or Fahrenheit) 
     * @param system Represents the temperature system to use.
     * False = Fahrenheit, True = Celsius
     * @return a string representing the temperature value of the weather
     */
    public String getTemp(boolean system){
        return convertTemp(temp, system);
    }
    
    /**
     * Returns the minimum temperature value of the weather object in the 
     * specified system (Celsius or Fahrenheit) 
     * @param system Represents the temperature system to use.
     * False = Fahrenheit, True = Celsius
     * @return a string representing the minimum temperature value of the 
     * weather
     */
    public String getMinTemp(boolean system){
        return convertTemp(minTemp, system);
    }
    
    /**
     * Returns the maximum temperature value of the weather object in the 
     * specified system (Celsius or Fahrenheit) 
     * @param system Represents the temperature system to use.
     * False = Fahrenheit, True = Celsius
     * @return a string representing the maximum temperature value of the 
     * weather
     */
    public String getMaxTemp(boolean system){
        return convertTemp(maxTemp, system);
    }
    
    /**
     * Converts a given value in Kelvin to either Celsius or Fahrenheit
     * @param t the temperature in Kelvin
     * @param system Represents the temperature system to use.
     * False = Fahrenheit, True = Celsius
     * @return a string representing the Kelvin temperature after it has been
     * converted
     */
    private String convertTemp(double t, boolean system){
        double result = t - 273.15;
        
        //Check if we need to display tempriture in fahrenheit
        if(!system) result = 32+(result*9)/5;
        
        result = (Math.round(result*100)/100.0);
        return "" + result;
    }
}

