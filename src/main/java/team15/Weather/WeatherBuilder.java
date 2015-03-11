package team15.Weather;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import java.util.GregorianCalendar;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import team15.JSON.URLToJSON;

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

public class WeatherBuilder{
    //TESTS
    private final String def = "{\"coord\":{\"lon\":-81.23,\"lat\":42.98},\"sys\":{\"type\":3,\"id\":36456,\"message\":2.1257,\"country\":\"CA\",\"sunrise\":1426074180,\"sunset\":1426116398},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"Sky is Clear\",\"icon\":\"01d\"}],\"base\":\"cmc stations\",\"main\":{\"temp\":276.48,\"pressure\":1018,\"temp_min\":276.48,\"temp_max\":276.48,\"humidity\":90},\"wind\":{\"speed\":2.57,\"gust\":4.11,\"deg\":0},\"clouds\":{\"all\":0},\"dt\":1426088655,\"id\":6058560,\"name\":\"London\",\"cod\":200}";
    //Number of weather objects in a forcast
    private static final int NUM_FORECAST = 8;
    
    //URL variables
    private final String localURL;
    private final String shortTermURL;
    private final String longTermURL;
    
    //Weather weather;
    private Object weatherInformation = new Object();
    private static String[] mainKeys = {"temp", "temp_min", "temp_max", "pressure", "humidity"};
	
    public WeatherBuilder(String loc){
        String prefix = "http://api.openweathermap.org/data/2.5/";

        //Make the urls for each type of build;
        localURL = prefix + "weather?q=" +loc;
        shortTermURL = prefix + "forecast?q=" + loc + "&mode=json";
        longTermURL =prefix + "forecast/daily?q=" + loc 
                +"&mode=json&units=metri&cnt=8";
    }
    
    /** Returns the current weather which has the values of temperature, humidity, air pressure,
     * air direction, minimum temperature, maximum temperature, wind speed, condition of the sky, 
     * sunrise, sunset and the icon.
     * @return  A Weather object.
     * @throws team15.Weather.WeatherBuilderException
     */
    /* Current Weather */
    public Weather buildCurrent(){
        JSONObject currentWeather = URLToJSON.makeJSON(localURL);
        return createWeather(currentWeather, true);
    } 

    /** Returns the short term which has the values of temperature, condition of the sky, 
     * and the icon.
     * @return  An array of Weather objects.
     */
    public ArrayList<Weather> buildShortTerm (){
        ArrayList<Weather> weather = new ArrayList(NUM_FORECAST);
        JSONObject shortTerm = URLToJSON.makeJSON(shortTermURL);
        
        try {
            //Pick out the array list
            JSONArray subArray;
            subArray = shortTerm.getJSONArray("list");

            //Loop through the indices of the array
            for (int i = 0; i < NUM_FORECAST; i++){
                weather.add(createWeather(subArray.getJSONObject(i), false));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return weather;
    }
    
    public ArrayList<Weather> buildLongTerm (){
        ArrayList<Weather> weather = new ArrayList(NUM_FORECAST);
        JSONObject longTerm = URLToJSON.makeJSON(longTermURL);
        
        try {
            //Pick out the array list
            JSONArray subArray;
            subArray = longTerm.getJSONArray("list");

            //Loop through the indices of the array
            for (int i = 0; i < NUM_FORECAST; i++){
                weather.add(createWeather(subArray.getJSONObject(i), false));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return weather;
    }
    
    private Weather createWeather(JSONObject j, boolean current){
        Weather result = new Weather();
        try {
            JSONObject temp;
            
            //Set sky condition and sky icon
            temp = j.getJSONArray("weather").getJSONObject(0);
            result.skyCondition = temp.get("description").toString();
            result.icon = new ImageIcon(new URL("http://openweathermap.org/img/w/" +
                    temp.get("icon") + ".png"));
            
            //Set tempriture values
            
            //Set long term termpriture values
            if(j.has("temp")){
                temp = j.getJSONObject("temp");
                result.setTemp(temp.get("day").toString());
                result.setMinTemp(temp.get("min").toString());
                result.setMaxTemp(temp.get("max").toString());
            }
            //Set short and current tempriture values
            else{
                temp = j.getJSONObject("main");
                result.setTemp(temp.get("temp").toString());
            }

            if(!current) return result;
            
            //Set current min/max tempriture
            result.setMinTemp(temp.get("temp_min").toString());
            result.setMaxTemp(temp.get("temp_max").toString());
            
            //Set current pressure and humidity
            result.humidity = temp.get("humidity").toString();
            result.airPressure = temp.get("pressure").toString();
            
            //Set sunrise and sunset for current
            temp = j.getJSONObject("sys");
            result.sunrise = temp.get("sunrise").toString();
            result.sunset = temp.get("sunset").toString();
            
            //Set windspeed and degree for current
            temp = j.getJSONObject("wind");
            result.windSpeed = temp.get("speed").toString();
            result.windDirection = temp.get("deg").toString();
            
        } catch (MalformedURLException ex) {
            result.error = "Problem with URL.";
        }
        
        return result;
    }
}

