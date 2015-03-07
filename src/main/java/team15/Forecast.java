package team15;

/**
 * The Forecast abstract class specifies common characteristics of Forecast-like Classes.
 * A WeatherBuilder object allows all kind of Forecasts (Current, Short, Long and Mars) to
 *  receive the information according to their types.
 *
 * @author Team 15
 * @version
 */

public abstract class Forecast {
    static WeatherBuilder wb = new WeatherBuilder();
    public abstract void updateForecast (String loc);
}

