package team15;
import java.util.ArrayList;


public class ShortTermForecast extends Forecast{
    ArrayList <Weather> weatherObjs;
    
    public void updateForecast(String loc){
	weatherObjs = wb.buildShortTerm(loc);
    }

    public ArrayList <Weather> getWeatherList (){
	return weatherObjs;
    }

    //getWeatherList (int how_many) -> return an arraylist the size of how_many
}
