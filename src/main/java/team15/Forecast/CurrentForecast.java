package team15.Forecast;

import team15.Weather.Weather;

/**
 * The CurrentForecast class extends the Forecast and uses the WeatherBuilder
 * object to update its information in a Weather object. 
 *
 * @author Team 15
 * @version
 */

public class CurrentForecast{
    private Weather weather;

    public CurrentForecast(Weather w){
        this.weather = w;
    }
    
    /** Returns a Weather object with the current forecast.
     * @return A Weather object.
     */
    public Weather getWeather (){
    	return weather;
    }
}

