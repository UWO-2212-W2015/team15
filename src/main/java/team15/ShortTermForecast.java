package team15;
import java.util.ArrayList;

/**
 * The ShortTermForecast Class contains an array of weather for the short term forecast.
 * The weather object is built according to the user's location. It is also possible
 * to update the forecast when the user desires.
 *
 * @author Team 15
 * @version
 */

public class ShortTermForecast extends Forecast{
    ArrayList <Weather> weatherObjs;
    
    /** Updates the short term forecast.
     * @param loc Location's forecast to be updated.
     */
    public void updateForecast(String loc){
	weatherObjs = wb.buildShortTerm(loc);
    }

    /** Returns an array of weather objects.
     * @param num Thus parameter specifies how many forecasts 
     * for the array.
     * @return An array of Weather objects.
     */
    public ArrayList <Weather> getWeatherList (int num){
	ArrayList<Weather> tempWeather = new ArrayList<Weather>();
	for (int i = 0; i < num; i++)
	    tempWeather.add(weatherObjs.get(i));
	return tempWeather;
    }

    //getWeatherList (int how_many) -> return an arraylist the size of how_many
}
