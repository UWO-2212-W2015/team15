package team15;

public class ShortTermForecast extends Weather{

	/*not sure if the times here is an array */
	String[] times;
	String[] precipitation_3h;
	
	Weather weather = new Weather();
	
	//counter for getTimes()
	private int counter=0;
	
	public ShortTermForecast(){

		super();
		times = time;
		
	}

	public void updateTimes(){
		
		times=time;
		
	}
	
	
	public String getTimes(){
		
		//totally 9 shortTermForecast
		 if(counter<9)
			return times[counter++];
		else
			//throw new NullPointerException();?
		 	return null;
		
	}


	
}
