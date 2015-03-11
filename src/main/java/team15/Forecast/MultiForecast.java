package team15.Forecast;

import java.util.ArrayList;
import team15.Weather.Weather;

/**
 * The MultiForecast Class contains an array of weather for the short term forecast.
 * The weather object is built according to the user's location. It is also possible
 * to update the forecast when the user desires.
 *
 * @author Team 15
 * @version
 */

public class MultiForecast{
    private ArrayList <Weather> weather;

    public MultiForecast(ArrayList<Weather> w){
        this.weather = w;
    }
    
    /** Returns an array of weather objects.
     * @return An array of Weather objects.
     */
    public ArrayList <Weather> getWeather(){
        return weather;
    }
}
