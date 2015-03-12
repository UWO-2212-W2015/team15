package team15.User;

/**
 * A container for the preference variables the the user keeps and the
 * weather program will use to change how data is displayed.
 * 
 * @author Team 15
 */
import java.io.Serializable;

public class Preferences implements Serializable {
    //Display properties - True = draw
    public boolean 
              temperature, wind, pressure, humidity, sun, icon, minMaxTemp, sky;
        
    //Tempriture preferences - True = Celcius, False = Fahrenheit
    public boolean tempUnits = true;
    
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
        icon = true;
        tempUnits = true;
    }
}
