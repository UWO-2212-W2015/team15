package team15;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import java.util.GregorianCalendar;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.net.URL;
import java.net.MalformedURLException;

/**
 * The WeatherBuilder Class is responsible for extracting the information in the 
 * json object and building a Weather object. Variables such as temperature, humidity,
 * condition of the sky, air direction, air speed and icon are the target. 
 * The WeatherBuilder returns a Weather object or an array of Weather objects according 
 * to the type of forecast.
 *  
 * @author Team 15
 * @version
 */

public class WeatherBuilder extends JSONObject{
	
    static JSONFetcher json = new JSONFetcher();
	
    //Weather weather;
    private Object weatherInformation = new Object();
    private String[] parts = new String[10];
    private static String[] mainKeys = {"temp", "temp_min", "temp_max", "pressure", "humidity"};
	
    String location;
    /*
      public WeatherBuilder(String region){;
      location = region;
      }
    */	
    
    /** Returns the current weather which has the values of temperature, humidity, air pressure,
     * air direction, minimum temperature, maximum temperature, wind speed, condition of the sky, 
     * sunrise, sunset and the icon.
     * @return  A Weather object.
     */
    /* Current Weather */
    public Weather buildCurrent(String location){
		Weather weather = new Weather();
		JSONObject currentWeather = json.getCurrentWeather(location);
		JSONObject tempJSON;
			
		/* main */
		try {
		    tempJSON = currentWeather.getJSONObject("main");

		parts = weatherInformation.toString().split("[\\:,}]");
			
		/* Temperature */
		weather.temperature = tempJSON.get("temp").toString();
			
		/* Humidity */
		weather.humidity = tempJSON.get("humidity").toString();
			
		/* Air pressure */
		weather.airPressure = tempJSON.get("pressure").toString();
			
		/* Minimum temperature */
		weather.minTemp = tempJSON.get("temp_min").toString();
			
		/* Maximum temperature */
		weather.maxTemp = tempJSON.get("temp_max").toString();
		} catch (JSONException e) {
		    e.printStackTrace();
		}
		/* wind */
		try {
		    weatherInformation = currentWeather.get("wind");
		} catch (JSONException e) {
		    e.printStackTrace();
		}
		parts = weatherInformation.toString().split("[\\:,}]");
			
		/* Wind speed */
		weather.windSpeed = parts[3];
			
		/* Wind direction */
		weather.windDirection = parts[1];
			
		/* Weather */
		try {
		    weatherInformation = currentWeather.get("weather");
		} catch (JSONException e) {
		    e.printStackTrace();
		}
		parts = weatherInformation.toString().split("[\\:,}]");
			
		/* Sky condition */
		weather.skyCondition = parts[3];
			
		/* Icon */
		String findStr = "\"" + "icon" +"\"" + ":"+"\"";
		int lastIndex = 0;
	
		lastIndex =  weatherInformation.toString().indexOf(findStr,lastIndex);
	
		if( lastIndex != -1){
		    lastIndex+=findStr.length();
		}
		String partOfIt;
		partOfIt = weatherInformation.toString().substring(lastIndex, lastIndex+5);
		String[] split;
		split = partOfIt.split("\"");
		parts[0] = split[0];
		//weather.icon = parts[0];
		
		try{
		    weather.icon = new ImageIcon(new URL("http://openweathermap.org/img/w/" 
							 + parts[0] + ".png"));
		}catch (MalformedURLException e){
		    e.printStackTrace();
		}
			
		/* sys */
		try {
		    weatherInformation = currentWeather.get("sys");
		} catch (JSONException e) {
		    e.printStackTrace();
		}
		parts = weatherInformation.toString().split("[\\:,}]");
			
		/* Sunrise */
		weather.sunrise = parts[3];
			
		/* Sunset */
		weather.sunset = parts[5];
			
		/* Time ????*/
		try {
		    weatherInformation = currentWeather.get("dt");
		} catch (JSONException e) {
		    e.printStackTrace();
		}
			
		parts = weatherInformation.toString().split("[\\:,}]");
		weather.time = new GregorianCalendar();
		weather.time.setTimeInMillis((long)Integer.parseInt(parts[0])*1000);
		
		return weather;
    } 

    /** Returns the short term which has the values of temperature, condition of the sky, 
     * and the icon.
     * @return  An array of Weather objects.
     */
    public ArrayList <Weather> buildShortTerm (String location){
		ArrayList <Weather> weather = new ArrayList <Weather>();
		JSONObject shortTerm = json.get3HourForecast(location);
		JSONArray subArray = new JSONArray();
		try {
		    //Pick out the array list
		    subArray = shortTerm.getJSONArray("list");
	
		    //Loop through the indices of the array
		    for (int i = 0; i < subArray.length(); i++){
			Weather tempW = new Weather();
			JSONArray subArray2 = new JSONArray();
			JSONObject tmpObj = new JSONObject();
	
			//Pick out the json object at index i
		        tmpObj = subArray.getJSONObject(i);
			
			//save the time & date
			weatherInformation = tmpObj.get("dt");
			tempW.time = new GregorianCalendar();
			tempW.time.setTimeInMillis((long)Integer.parseInt(weatherInformation.toString())*1000);
			
			//Pick out the main json
			tmpObj = tmpObj.getJSONObject("main");
	
			//Loop through the keys to pick out values !!! we dont NEED all the keys, but we can go above the min?
			for (int key = 0; key < mainKeys.length; key++){
	
			    weatherInformation = tmpObj.get(mainKeys[key]);
	
			    switch(key) {
			    case 0: tempW.temperature = weatherInformation.toString();
				break;
			    case 1: tempW.minTemp = weatherInformation.toString();
				break;
			    case 2: tempW.maxTemp = weatherInformation.toString();
				break;
			    case 3: tempW.airPressure = weatherInformation.toString();
				break;
			    case 4: tempW.humidity = weatherInformation.toString();
				break;
			    }
			}
	
			tmpObj = subArray.getJSONObject(i).getJSONArray("weather").getJSONObject(0);
			tempW.skyCondition = tmpObj.get("description").toString();
			//tempW.icon = tmpObj.get("icon").toString();
			tempW.icon = new ImageIcon(new URL("http://openweathermap.org/img/w/" + 
							   tmpObj.get("icon").toString() + ".png"));
			weather.add(tempW);
		    }
		} catch (JSONException e) {
		    e.printStackTrace();
		} catch (MalformedURLException e){
		    e.printStackTrace();
		}
		return weather;
    }
}

