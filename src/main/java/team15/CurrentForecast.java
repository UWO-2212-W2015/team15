package team15;

/**
 * The CurrentForecast class extends the Forecast and uses the WeatherBuilder
 * object to update its information in a Weather object. 
 *
 * @author Team 15
 * @version
 */

public class CurrentForecast extends Forecast{
    Weather weather;

    /** Updates the current forecast.
     * @param loc Location's forecast to be updated.
     */
    public void updateForecast (String loc){
    	weather = wb.buildCurrent(loc);
    }

    /** Returns a Weather object with the current forecast.
     * @return A Weather object.
     */
    public Weather getWeatherObject (){
    	return weather;
    }
}

