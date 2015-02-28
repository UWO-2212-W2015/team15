public class CurrentForecast extends Weather{

	String date;
	String precipitation_24h;
	
	Weather weather = new Weather();
	
	public CurrentForecast(){

		super();
		date = time[0];
	}
		
	 

	//what is that method supposed to do?
	public void updateDate(){

		date=time[0];
		
	}
	
	public String getDate(){
		
		return date;
	
	}
	
}

