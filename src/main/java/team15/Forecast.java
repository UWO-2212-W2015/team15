package team15;

public abstract class Forecast {
    static WeatherBuilder wb = new WeatherBuilder();
    public abstract void updateForecast (String loc);
}

