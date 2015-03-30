package team15.UserOjects;

/**
 * A container for the preference variables the the user keeps and the
 * weather program will use to change how data is displayed.
 * 
 * @author team15
 */

//Imports
import java.io.Serializable;

public class Preferences implements Serializable{

    private static final long serialVersionUID = 4624299410738972362L;
    
    //Display properties - True = draw
    public boolean 
              temperature, wind, pressure, humidity, sun, minMaxTemp, sky;
        
    //Tempriture preferences - True = Celcius, False = Fahrenheit
    public boolean tempUnits;
    
    /**
     * Creates a new set of preferences all defaulted with True. For display
     * properties that means Draw. For temperature units that means Celsius.
     */
    public Preferences(){
        temperature = true;
        wind = true;
        pressure = true;
        humidity = true;
        minMaxTemp = true;
        sky = true;
        sun = true;
        tempUnits = true;
    }
}
