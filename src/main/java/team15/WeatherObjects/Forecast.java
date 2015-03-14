package team15.WeatherObjects;

/**
 * This class represents a forecast (either short term or long term).  It will
 * hold 8 weather objects in it in a list.
 * 
 * @author team15
 */

//Imports
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Forecast extends ArrayList<Weather> implements Serializable{
    public final static int NUM = 8;
    
    /**
     * Creates a blank forecast object
     */
    public Forecast(){
        super();
        for(int i = 0; i < NUM; i++){
            this.add(new Weather());
        }
    }
    
    /**
     * Creates a new forecast from the given json object
     * @param j the json that contains all the data for the weather objects
     * @throws MalformedURLException thrown if any of the urls are
     * malformed
     * @throws IOException thrown if there is any problem interacting with the
     * OpenWeather api
     * @throws JSONException thrown if there is any problem using the json 
     */
    public Forecast(JSONObject j)
                       throws JSONException, MalformedURLException, IOException{
        super();
        
        JSONArray forecast = j.getJSONArray("list");
        
        for(int i = 0; i < NUM; i++){
            this.add(new Weather(forecast.getJSONObject(i), true));
        }
    }
}