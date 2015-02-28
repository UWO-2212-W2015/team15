package weatherApp;

public class LongtermForecast extends Weather{

		/*not sure if the times here is an array */
		String[] days;
		String[] precipitation;
		
		//counter for getTimes()
		private int counter=0;
		
		public LongtermForecast(){

			super();
			//precipitation=precipitationArrayy in Weather 
			days = time;
			
		}

		public void updateDays(){
			
			days=time;
			
		}
		
		
		public String getDays(){
			//in 5 days
			 if(counter<5)
				return days[counter++];
			else
				//throw new NullPointerException();?
			 	return null;
			
		}


		
	}

}
