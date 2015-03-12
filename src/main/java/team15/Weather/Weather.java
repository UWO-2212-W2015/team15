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
import java.io.Serializable;
import java.util.Date;
import javax.swing.ImageIcon;

public class Weather implements Serializable{
    //Temperatures
    private double temp, minTemp, maxTemp;
    
    //Wind
    public String windSpeed, windDirection;
    
    //Sky
    public String airPressure, humidity, skyCondition;
    public ImageIcon icon;
    
    //Time
    public String sunrise, sunset;
    public Date time;
    
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
        time = new Date();
    }

    /**
     * Set the temperature value of the weather object  
     * @param t the new temperature for the weather object
     */
    public void setTemp(String t){
        this.temp = Double.valueOf(t);
    }
    
    /**
     * Set the minimum temperature value of the weather object  
     * @param t the new minimum temperature for the weather object
     */
    public void setMinTemp(String t){
        this.minTemp = Double.valueOf(t);
    }

    /**
     * Set the maximum temperature value of the weather object  
     * @param t the new maximum temperature for the weather object
     */
    public void setMaxTemp(String t){
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

