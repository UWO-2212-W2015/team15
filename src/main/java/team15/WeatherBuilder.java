package team15;

import org.json.JSONException;
import org.json.JSONObject;


public class WeatherBuilder extends JSONObject{
	
	JSONFetcher json = new JSONFetcher();
	
	Weather weather;
	private Object weatherInformation = new Object();
	private String[] parts = new String[10];
	
	String location;
	/* Constructor */
	public WeatherBuilder(String region){;
		location = region;
		/* Do we need anything else here? */
	}
	
	/* Current Weather */
	public Weather buildCurrent(){
		weather = new Weather();
		JSONObject currentWeather = json.getCurrentWeather(location);
		
		/* main */
		try {
			weatherInformation = currentWeather.get("main");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		parts = weatherInformation.toString().split("[\\:,}]");
		
		/* Temperature */
		weather.temperature[0] = parts[1];
		
		/* Humidity */
		weather.humidity[0] = parts[5];
		
		/* Air pressure */
		weather.airPressure[0] = parts[7];
		
		/* Minimum temperature */
		weather.minTemp[0] = parts[3];
		
		/* Maximum temperature */
		weather.maxTemp[0] = parts[13];
		
		/* wind */
		try {
			weatherInformation = currentWeather.get("wind");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		parts = weatherInformation.toString().split("[\\:,}]");
		
		/* Wind speed */
		weather.windSpeed[0] = parts[3];
		
		/* Wind direction */
		weather.windDirection[0] = parts[1];
		
		/* Weather */
		try {
			weatherInformation = currentWeather.get("weather");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		parts = weatherInformation.toString().split("[\\:,}]");
		
		/* Sky condition */
		weather.skyCondition[0] = parts[3];
		
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
		weather.icon[0] = parts[0];
		
		/* sys */
		try {
			weatherInformation = currentWeather.get("sys");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		parts = weatherInformation.toString().split("[\\:,}]");
		
		/* Sunrise */
		weather.sunrise[0] = parts[3];
		
		/* Sunset */
		weather.sunset[0] = parts[5];
		
		/* Time ????*/
		try {
			weatherInformation = currentWeather.get("sys");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		parts = weatherInformation.toString().split("[\\:,}]");
		weather.time[0] = parts[5];
		
		return weather;
	}
	
	/* Short Term */
	public Weather buildShortTerm(){
		weather = new Weather();
		JSONObject shortTerm = json.get3HourForecast(location);
		int cont, lastIndex;
		
		/* List */
		try {
			weatherInformation = shortTerm.get("list");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		/* Temperature */
		String findStr = "\"" + "temp" +"\"" + ":";
		lastIndex = 0;
		for(cont = 0; cont < 8; cont++){

		       lastIndex =  weatherInformation.toString().indexOf(findStr,lastIndex);

		       if( lastIndex != -1){
		             lastIndex+=findStr.length();
		       }
		       String partOfIt;
			   partOfIt = weatherInformation.toString().substring(lastIndex, lastIndex+8);
			   String[] split;
			   split = partOfIt.split(",");
			   weather.temperature[cont] = split[0];
		}
		
		/* Sky Condition */
		findStr = "\"" + "description" +"\"" + ":";
		lastIndex = 0;
		for(cont = 0; cont < 8; cont++){

		       lastIndex =  weatherInformation.toString().indexOf(findStr,lastIndex);

		       if( lastIndex != -1){
		             lastIndex+=findStr.length();
		       }
		       String partOfIt;
			   partOfIt = weatherInformation.toString().substring(lastIndex, lastIndex+18);
			   String[] split;
			   split = partOfIt.split(",");
			   weather.skyCondition[cont] = split[0];
		}
		/* Icon */
		findStr = "\"" + "icon" +"\"" + ":" + "\"";
		lastIndex = 0;
		for(cont = 0; cont < 8; cont++){

		       lastIndex =  weatherInformation.toString().indexOf(findStr,lastIndex);

		       if( lastIndex != -1){
		             lastIndex+=findStr.length();
		       }
		       String partOfIt;
			   partOfIt = weatherInformation.toString().substring(lastIndex, lastIndex+3);
			   String[] split;
			   split = partOfIt.split(",");
			   weather.icon[cont] = split[0];
		}
		return weather;
	}
	
	/* Long term */
	public Weather buildLongTerm(){
		weather = new Weather();
		JSONObject longTerm = json.getDailyForecast(location);
		int cont, lastIndex;
		
		/* List */
		try {
			weatherInformation = longTerm.get("list");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		/* Temperature */
		String findStr = "\"" + "day" +"\"" + ":";
		lastIndex = 0;
		for(cont = 0; cont < 5; cont++){

		       lastIndex =  weatherInformation.toString().indexOf(findStr,lastIndex);

		       if( lastIndex != -1){
		             lastIndex+=findStr.length();
		       }
		       String partOfIt;
			   partOfIt = weatherInformation.toString().substring(lastIndex, lastIndex+8);
			   String[] split;
			   split = partOfIt.split(",");
			   weather.temperature[cont] = split[0];
		}
		
		/* Minimum Temperature */
		findStr = "\"" + "min" +"\"" + ":";
		lastIndex = 0;
		
		for(cont = 0; cont < 5; cont++){

		       lastIndex =  weatherInformation.toString().indexOf(findStr,lastIndex);

		       if( lastIndex != -1){
		             lastIndex+=findStr.length();
		       }
		       String partOfIt;
			   partOfIt = weatherInformation.toString().substring(lastIndex, lastIndex+8);
			   String[] split;
			   split = partOfIt.split(",");
			   weather.minTemp[cont] = split[0];
		}
		
		/* Maximum Temperature */
		findStr = "\"" + "max" +"\"" + ":";
		lastIndex = 0;
		
		for(cont = 0; cont < 5; cont++){

		       lastIndex =  weatherInformation.toString().indexOf(findStr,lastIndex);

		       if( lastIndex != -1){
		             lastIndex+=findStr.length();
		       }
		       String partOfIt;
			   partOfIt = weatherInformation.toString().substring(lastIndex, lastIndex+8);
			   String[] split;
			   split = partOfIt.split(",");
			   weather.maxTemp[cont] = split[0];
		}
		
		/* Sky Condition */
		findStr = "\"" + "description" +"\"" + ":";
		lastIndex = 0;
		
		for(cont = 0; cont < 5; cont++){

		       lastIndex =  weatherInformation.toString().indexOf(findStr,lastIndex);

		       if( lastIndex != -1){
		             lastIndex+=findStr.length();
		       }
		       String partOfIt;
			   partOfIt = weatherInformation.toString().substring(lastIndex, lastIndex+18);
			   String[] split;
			   split = partOfIt.split(",");
			   weather.skyCondition[cont] = split[0];
		}
		
		/* Icon */
		findStr = "\"" + "icon" +"\"" + ":" + "\"";
		lastIndex = 0;
		for(cont = 0; cont < 5; cont++){

		       lastIndex =  weatherInformation.toString().indexOf(findStr,lastIndex);

		       if( lastIndex != -1){
		             lastIndex+=findStr.length();
		       }
		       String partOfIt;
			   partOfIt = weatherInformation.toString().substring(lastIndex, lastIndex+3);
			   String[] split;
			   split = partOfIt.split(",");
			   weather.icon[cont] = split[0];
		}
		return weather;
	}
	
	/* Mars Current Weather*/
	public Weather buildMarsWeather(){
		weather = new Weather();
		JSONObject marsWeather = json.getMarsJason();
		int lastIndex;
		
		/* Report */
		try {
			//System.out.println(marsWeather);
			weatherInformation = marsWeather.get("report");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		/* Temperature */
		String findStr = "\"" + "max_temp" +"\"" + ":";
		lastIndex = 0;

		lastIndex =  weatherInformation.toString().indexOf(findStr,lastIndex);

		if( lastIndex != -1){
		     lastIndex+=findStr.length();
		}
		
		String partOfIt;
		partOfIt = weatherInformation.toString().substring(lastIndex, lastIndex+2);
		String[] split;
		split = partOfIt.split(",");
		weather.temperature[0] = split[0];
		
		
		/* Wind Speed */
		findStr = "\"" + "wind_speed" +"\"" + ":";
		lastIndex = 0;

		lastIndex =  weatherInformation.toString().indexOf(findStr,lastIndex);

		if( lastIndex != -1){
		     lastIndex+=findStr.length();
		}
		
		partOfIt = weatherInformation.toString().substring(lastIndex, lastIndex+5);
		split = partOfIt.split(",");
		weather.windSpeed[0] = split[0];
				
		/* Wind direction */
		findStr = "\"" + "wind_direction" +"\"" + ":";
		lastIndex = 0;

		lastIndex =  weatherInformation.toString().indexOf(findStr,lastIndex);

		if( lastIndex != -1){
		     lastIndex+=findStr.length();
		}
		
		partOfIt = weatherInformation.toString().substring(lastIndex, lastIndex+5);
		split = partOfIt.split(",");
		weather.windDirection[0] = split[0];
		
		/* Air pressure */
		findStr = "\"" + "pressure" +"\"" + ":";
		lastIndex = 0;

		lastIndex =  weatherInformation.toString().indexOf(findStr,lastIndex);

		if( lastIndex != -1){
		     lastIndex+=findStr.length();
		}
		
		partOfIt = weatherInformation.toString().substring(lastIndex, lastIndex+5);
		split = partOfIt.split(",");
		weather.airPressure[0] = split[0];
		
		/* Humidity */
		findStr = "\"" + "abs_humidity" +"\"" + ":";
		lastIndex = 0;

		lastIndex =  weatherInformation.toString().indexOf(findStr,lastIndex);

		if( lastIndex != -1){
		     lastIndex+=findStr.length();
		}
		
		partOfIt = weatherInformation.toString().substring(lastIndex, lastIndex+5);
		split = partOfIt.split(",");
		weather.humidity[0] = split[0];	
		
		/* Sky condition */
		findStr = "\"" + "atmo_opacity" +"\"" + ":";
		lastIndex = 0;

		lastIndex =  weatherInformation.toString().indexOf(findStr,lastIndex);

		if( lastIndex != -1){
		     lastIndex+=findStr.length();
		}
		
		partOfIt = weatherInformation.toString().substring(lastIndex, lastIndex+9);
		split = partOfIt.split(",");
		weather.skyCondition[0] = split[0];
		
		/* time???? */
		
		/* Icon ???? */
		findStr = "\"" + "atmo_opacity" +"\"" + ":";
		lastIndex = 0;

		lastIndex =  weatherInformation.toString().indexOf(findStr,lastIndex);

		if( lastIndex != -1){
		     lastIndex+=findStr.length();
		}
		
		partOfIt = weatherInformation.toString().substring(lastIndex, lastIndex+9);
		split = partOfIt.split(",");
		weather.icon[0] = split[0];
		
		return weather;
	}
}

