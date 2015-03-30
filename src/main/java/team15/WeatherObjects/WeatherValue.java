package team15.WeatherObjects;

/**
 * A wrapper for a string that will hold a value for a weather object. This is
 * used so that JSON values can be loaded in a way that is chosen during run
 * time (since default String objects are final)
 * 
 * @author team15
 */
import java.io.Serializable;

public class WeatherValue implements Serializable{
    public String value;
    
    /**
     * Makes a new empty WeatherValue
     */
    public WeatherValue(){
        value = "";
    }
    
    /**
     * Makes a new WeatherValue holding the given string v
     * @param v the string to store in the object
     */
    public WeatherValue(String v){
        value = v;
    }

    /**
     * Sets the value contained in the object to this new string
     * @param v the new string to store in the object
     */
    public void setValue(String v){
        value = v;
    }
    
    /**
     * Displays the WeatherValue as a string. This is the same as the value
     * string that is contained within the object
     * @return value
     */
    public String toString(){
        return value;
    }
}
