import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;


public class JSONFetcher extends JSONObject{
	String url_current;
	String url_3hour;
	String url_daily;
	String url_mars;
	static JSONObject json;
	static String url;

	/* Constructor */
	public JSONFetcher(){;
		/* Do we need something here... */
	}
	
	public JSONObject getCurrentWeather(String location){
		url_current = "http://api.openweathermap.org/data/2.5/weather?q="+location;
		url = url_current;
		try {
			readJsonFromUrl();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	public JSONObject get3HourForecast(String location){
		url_3hour = "http://api.openweathermap.org/data/2.5/forecast?q="+location+"&mode=json";
		url = url_3hour;
		try {
			readJsonFromUrl();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	public JSONObject getDailyForecast(String location){
		url_daily = "http://api.openweathermap.org/data/2.5/forecast/daily?q="+location+"&mode=json&units=metri&cnt=5";
		url = url_daily;
		try {
			readJsonFromUrl();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	public JSONObject getMarsJason(){
		url_mars = "http://marsweather.ingenology.com/v1/latest/?format=json";
		url = url_mars;
		try {
			readJsonFromUrl();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	
	public JSONObject getWeatherInformation(){
		return json;
	}

	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}
  
	public JSONObject readJsonFromUrl() throws IOException, JSONException {
	   InputStream is = new URL(url).openStream();
	   try {
	     BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	     String jsonText = readAll(rd);
	     json = new JSONObject(jsonText);
	     return json;
	   } 
	   finally {
	      is.close();
	   }
	}
}

