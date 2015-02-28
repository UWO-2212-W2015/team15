package weatherApp;

public class Forecast {
	
	
	String lastUpdate;//the time of lastest update
	CurrentForecast current;
	ShortTermForecast shortTerm;
	LongtermForecast longterm;
	
	private String place;

	public Forecast(Location region){
		
		place=region.city;//get the city name
		
		current= (CurrentForecast) new WeatherBuilder(place).buildCurrent();
		shortTerm= (ShortTermForecast) new WeatherBuilder(place).buildShortTerm();
		longterm= (LongtermForecast) new WeatherBuilder(place).buildLongTerm();
		
		//this time will be very precious 
		lastUpdate=shortTerm.getTimes();
	}
	
	/**
	 * update weather info once the method is called
	 *
	 */
	public void updateWeather(){
		
		//update the weather
		current= (CurrentForecast) new WeatherBuilder(place).buildCurrent();
		shortTerm= (ShortTermForecast) new WeatherBuilder(place).buildShortTerm();
		longterm= (LongtermForecast) new WeatherBuilder(place).buildLongTerm();
		
		//this time will be very specific 
		lastUpdate=shortTerm.getTimes();
	}
	
	public void loadWeather(){
		//I don't know what this method is about. 
		//I think the constructor will load the weather info at the very first beginning 
		
	}
	
}
