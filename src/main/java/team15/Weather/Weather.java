package team15.Weather;

import java.util.Calendar;
import javax.swing.ImageIcon;

/**
 * The Weather class is constituted by variables present in the weather forecast.
 * Even though it is a small class, it is necessary in order to have a unified class
 * which supports all kinds of weather forecast.
 *
 * @author Team 15
 * @version
 */

public class Weather {
    //Tempritures
    private double temp;
    private double minTemp;
    private double maxTemp;
    
    //Wind
    public String windSpeed;
    public String windDirection;
    
    //Sky
    public String airPressure;
    public String humidity;
    public String skyCondition;
    public ImageIcon icon;
    
    //Time
    public String sunrise;
    public String sunset;
    public Calendar time;
    
    public void setTemp(String t){
        this.temp = Double.valueOf(t);
    }
    
    public void setMinTemp(String t){
        this.minTemp = Double.valueOf(t);
    }
    
    public void setMaxTemp(String t){
        this.maxTemp = Double.valueOf(t);
    }
    
    public String getTemp(boolean system){
        return convertTemp(temp, system);
    }
    
    public String getMinTemp(boolean system){
        return convertTemp(minTemp, system);
    }
    
    public String getMaxTemp(boolean system){
        return convertTemp(maxTemp, system);
    }
    
    private String convertTemp(double t, boolean system){
        double result = t - 273.15;
        
        //Check if we need to display tempriture in fahrenheit
        if(!system) result = 32+(result*9)/5;
        
        result = (Math.round(result*100)/100.0);
        return "" + result;
    }
}

