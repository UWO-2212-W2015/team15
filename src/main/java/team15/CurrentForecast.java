package team15;

public class CurrentForecast extends Forecast{
    Weather weather;

    public void updateForecast (String loc){
	weather = wb.buildCurrent(loc);
    }

    public Weather getWeatherObject (){
	return weather;
    }
}

