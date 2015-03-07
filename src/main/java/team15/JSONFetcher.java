package team15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * The JSONFetcher Class, when instantiated, returns a Json object depending on 
 * the request of a WeatherBuilder object. The current, short, long term forecast 
 * and Mars forecast are the information available. Specific internal links
 * for each kind of forecast make possible to obtain that information according
 * to the location that was provided.
 *
 * @author Team 15
 * @version
 */

public class JSONFetcher extends JSONObject{
	String url_current;
	String url_3hour;
	String url_daily;
	String url_mars;
	static JSONObject json;
	static String url;

	/* Constructor */
	public JSONFetcher(){;
		/* Do we need something here...? */
	}
	
    /** Returns the current weather for the location.
     * @param location The location for the current weather forecast.
     * @return  A Json object containing the information.
     */
	public JSONObject getCurrentWeather(String location){
		url_current = "http://api.openweathermap.org/data/2.5/weather?q="+location;
		url = url_current;
		try {
			readFromUrl();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}
	
    /** Returns the short term forecast for the location.
     * @param location The location for short term forecast.
     * @return  A Json object containing the information.
     */
	public JSONObject get3HourForecast(String location){
		url_3hour = "http://api.openweathermap.org/data/2.5/forecast?q="+location+"&mode=json";
		url = url_3hour;
		try {
			readFromUrl();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}
	
    /** Returns the long term forecast for the location.
     * @param location The location for the long term forecast.
     * @return  A Json object containing the information.
     */
	public JSONObject getDailyForecast(String location){
		url_daily = "http://api.openweathermap.org/data/2.5/forecast/daily?q="+location+"&mode=json&units=metri&cnt=5";
		url = url_daily;
		try {
			readFromUrl();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}
	
    /** Returns Mars forecast.
     * @return  A Json object containing the information.
     */
	public JSONObject getMarsJason(){
		url_mars = "http://marsweather.ingenology.com/v1/latest/?format=json";
		url = url_mars;
		try {
			readFromUrl();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	/** Reads the whole structure in the Json format.
     * @param reader The structure containing the input stream (buffered).
     * @return  A String object containing the forecast information.
     */
	private static String readAllInformation(Reader reader) throws IOException {
		StringBuilder strBuilder = new StringBuilder();
		int cp;
		while ((cp = reader.read()) != -1) {
			strBuilder.append((char) cp);
		}
		return strBuilder.toString();
	}
 
	/** Reads from the specified url either for the current, short, long term forecast or 
	 * Mars forecast.
     * @return  A json object with the text inside of it.
     */
	public JSONObject readFromUrl() throws IOException, JSONException {
	   InputStream is = new URL(url).openStream();
	   try {
	     BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	     String jsonText = readAllInformation(reader);
	     json = new JSONObject(jsonText);
	     return json;
	   } 
	   finally {
	      is.close();
	   }
	}
}

