public class Forecast {
	
	String lastUpdate;//the time of lastest update
	
	CurrentForecast current = new CurrentForecast();
	ShortTermForecast shortTerm = new ShortTermForecast();
	LongtermForecast longterm = new LongtermForecast();
	
	WeatherBuilder weatherBuilder;
	
	private String place;

	public Forecast(String string){
		place=string;//get the city name
		weatherBuilder = new WeatherBuilder(place);
		loadWeather();
		//this time will be very precious 
		lastUpdate=shortTerm.getTimes();
	}
	
	/**
	 * update weather info once the method is called
	 *
	 */
	public void updateWeather(){
		
		//update the weather
		loadWeather();
		//this time will be very specific 
		lastUpdate=shortTerm.getTimes();
	}
	
	public void loadWeather(){
		//I don't know what this method is about. 
		//I think the constructor will load the weather info at the very first beginning 
		current.weather = weatherBuilder.buildCurrent();
		shortTerm.weather = weatherBuilder.buildShortTerm();
		longterm.weather = weatherBuilder.buildLongTerm();
	}
	
}

