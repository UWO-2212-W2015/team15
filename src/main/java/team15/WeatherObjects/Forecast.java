package team15.WeatherObjects;

/**
 * This class represents a forecast (either short term or long term).  It will
 * hold 8 weather objects in it in a list.
 * 
 * @author team15
 */

//Imports
import java.io.Serializable;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import team15.WeatherObjects.Weather.WeatherType;

public class Forecast extends ArrayList<Weather> implements Serializable{
    public final static int NUM = 8;
    public final WeatherType type;
    public long created;
    
    /**
     * Creates a blank forecast object
     */
    public Forecast(){
        super();
        for(int i = 0; i < NUM; i++){
            this.add(new Weather(WeatherType.SHORTTERM));
        }
        type = WeatherType.SHORTTERM;
        created = 0;
    }
    
    /**
     * Creates a new default forecast object with weather objects of the
     * specified type
     * @param t the type of weather objects contained in the forecast
     */
    public Forecast(WeatherType t){
        super();
        for(int i = 0; i < NUM; i++){
            this.add(new Weather(t));
        }
        type = t;
        created = 0;
    }
    
    /**
     * Creates a new forecast from the given json object
     * @param j the json that contains all the data for the weather objects
     * @param t the type of weather objects contained in this forecast
     */
    public Forecast(JSONObject j, WeatherType t){
        super();
        
        JSONArray forecast = j.getJSONArray("list");
        
        for(int i = 0; i < NUM; i++){
            this.add(new Weather(forecast.getJSONObject(i), t));
        }
        type = t;
        created = System.currentTimeMillis();
    }
}