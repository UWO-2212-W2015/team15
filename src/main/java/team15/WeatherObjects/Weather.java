package team15.WeatherObjects;

/**
 * The Weather class is constituted by variables present in the weather forecast
 * 
 * Even though it is a small class, it is necessary in order to have a 
 * unified class which supports all kinds of weather forecast.
 *
 * @author team15
 */

//Imports
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import javax.swing.ImageIcon;
import org.json.JSONException;
import org.json.JSONObject;

public class Weather implements Serializable{
    //JSON key arrays
    private static final String[] localKeys = {"dt", "weather-description", 
                    "weather-icon", "main-temp", "main-temp_min", 
                    "main-temp_max", "main-humidity", "main-pressure", 
                    "sys-sunrise", "sys-sunset", "wind-speed", "wind-deg"}; 
    private static final String[] shortKeys = {"dt", "weather-description", 
                    "weather-icon", "main-temp"};
    private static final String[] longKeys = {"dt", "weather-description", 
                    "weather-icon", "temp-day", "temp-min", "temp-max"};

    //List of Weather Icons
    private static HashMap<String, ImageIcon> iconList;
    
    //Enumeration of all the different types of weather objects
    public static enum WeatherType{LOCAL, SHORTTERM, LONGTERM;}
    
    //Temperatures
    private WeatherValue temp, minTemp, maxTemp;
    
    //Wind
    public WeatherValue windSpeed, windDirection;
    
    //Sky
    public WeatherValue airPressure, humidity, skyCondition, iconPath;
    public ImageIcon icon;
    
    //Time
    public WeatherValue sunrise, sunset, time;
    
    //Type of weather object this represents
    public WeatherType type;
    
    //Time this object was created at
    public long created;
    
    /**
     * Creates an instance of the Weather, class, setting all values to 0 or
     * a default object.
     */
    public Weather(){
        loadDefaults(WeatherType.LOCAL);
    }
    
    public Weather(WeatherType t){
        loadDefaults(t);
    }
    
    /**
     * Creates new weather object from the given json object
     * @param json the json object containing the weather data
     * @param t The type of the Weather object.  Taken from values in the
 WeatherType enumeration
     * @throws JSONException thrown if there is any problem using the json 
     */
    public Weather(JSONObject json, WeatherType t) throws JSONException{
        loadDefaults(t);
        
        WeatherValue[] values = this.valueArray();
        String[] keys = this.keyArray();
        
        for(int i = 0; i < values.length; i++){
            String[] key = keys[i].split("-");

            JSONObject tempJson = json;
            int j = 0;
            while(j < (key.length - 1)){
                if(key[j].equals("weather"))
                    tempJson = tempJson.getJSONArray("weather").getJSONObject(0);
                else tempJson = tempJson.getJSONObject(key[j]);
                j++;
            }

            values[i].setValue(tempJson.get(key[j]).toString());
        }
        
        time.setValue(convertTime(time.value, true));
        sunrise.setValue(convertTime(sunrise.value, false));
        sunset.setValue(convertTime(sunset.value, false));
        icon = iconList.get(iconPath.toString());
        created = System.currentTimeMillis();
    }
    
    /**
     * Returns the temperature value of the weather object in the specified
     * system (Celsius or Fahrenheit) 
     * @param system Represents the temperature system to use.
     * False = Fahrenheit, True = Celsius
     * @return a string representing the temperature value of the weather
     */
    public String getTemp(boolean system){
        String s = temp.value;
        return (s.equals("N/A"))?s:convertTemp(s, system);
    }
    
    /**
     * Returns the minimum temperature value of the weather object in the 
     * specified system (Celsius or Fahrenheit) 
     * @param system Represents the temperature system to use.
     * False = Fahrenheit, True = Celsius
     * @return a string representing the minimum temperature value of the 
     * weather
     */
    public String getMinTemp(boolean system){
        String s = minTemp.value;
        return (s.equals("N/A"))?s:convertTemp(s, system);
    }
    
    /**
     * Returns the maximum temperature value of the weather object in the 
     * specified system (Celsius or Fahrenheit) 
     * @param system Represents the temperature system to use.
     * False = Fahrenheit, True = Celsius
     * @return a string representing the maximum temperature value of the 
     * weather
     */
    public String getMaxTemp(boolean system){
        String s = maxTemp.value;
        return (s.equals("N/A"))?s:convertTemp(s, system);
    }
    
    /**
     * Converts a given value in Kelvin to either Celsius or Fahrenheit
     * @param t the temperature in Kelvin
     * @param system Represents the temperature system to use.
     * False = Fahrenheit, True = Celsius
     * @return a string representing the Kelvin temperature after it has been
     * converted
     */
    private String convertTemp(String t, boolean system){
        Double result = Double.valueOf(t) - 273.15;
        
        //Check if we need to display tempriture in fahrenheit
        if(!system) result = 32+(result*9)/5;
        
        result = (Math.round(result*100)/100.0);
        return result.toString();
    }
    
    /**
     * Converts a time value returned from OpenWeather into a Date object
     * @param t the time value from OpenWeather
     * @param full if true return the full date, otherwise return just the time
     * @return the full date or time that the given value t represents
     */
    private String convertTime(String t, boolean full){
        String result;
        Date date = new Date();
        
        date.setTime((long)1000*Integer.valueOf(t));
        result = date.toString();
        if(!full) result = result.split(" ")[3];
        
        return result;
    }
    
    /**
     * Loads a new weather object with all default values and a type t
     * @param t the type of the Weather object. Taken from the WeatherType
 enumeration
     */
    private void loadDefaults(WeatherType t){
        if(iconList == null) loadIcons();
        
        temp = new WeatherValue("N/A");
        minTemp = new WeatherValue("N/A");
        maxTemp = new WeatherValue("N/A");
        
        windSpeed = new WeatherValue("N/A");
        windDirection = new WeatherValue("N/A");
        
        airPressure = new WeatherValue("N/A");
        humidity = new WeatherValue("N/A");
        skyCondition = new WeatherValue("N/A");
        iconPath = new WeatherValue("01d");
        icon = iconList.get(iconPath.toString());
        
        sunrise = new WeatherValue("0");
        sunset = new WeatherValue("0");
        time = new WeatherValue("0");
        
        type = t;
        created = 0;
    }
    
    /**
     * Returns an array of pointers to the fields of the weather object that
     * will be used to hold the data from the JSON object passed in the 
     * constructor of the class.
     * 
     * The order of these pointers should match the order of their respective 
     * keys that are returned in valueArray()
     * @return an array of pointers to weather values that are not default in
     * the weather object
     */
    public final WeatherValue[] valueArray(){
        WeatherValue[] localValues = {time, skyCondition, iconPath, temp, 
        minTemp, maxTemp, humidity, airPressure, sunrise, sunset, windSpeed, 
        windDirection}; 
        WeatherValue[] shortValues = {time, skyCondition, iconPath, temp};
        WeatherValue[] longValues = {time, skyCondition, iconPath, temp, 
            minTemp, maxTemp};
        
        WeatherValue[] result = null;
    
        switch (this.type) {
            case LOCAL:  
                result = localValues;
                break;
            case SHORTTERM:  
                result = shortValues;
                break;
            case LONGTERM:  
                result = longValues;
                break;
        }
        return result;
    }
    
    /**
     * Returns an array of key values that will be used to extract values from
     * the JSON object passed in the constructor of the class.
     * 
     * The order of these keys should match the order of their respective 
     * destinations that are returned in valueArray()
     * @return an array of key values for use in getting data from a JSON obect
     */
    private String[] keyArray(){
        switch (this.type) {
            case LOCAL: 
                return localKeys;
            case SHORTTERM: 
                return shortKeys;
            case LONGTERM:
                return longKeys;
        }
        return null;
    }
   
    private void loadIcons(){
        try{
            InputStream fi = ImageIcon.class.getResourceAsStream("/icon.dat");
            ObjectInputStream in = new ObjectInputStream(fi);
            iconList = (HashMap) in.readObject();
        } catch(Exception ex){
            System.out.println("Error loading icons from icon.dat");
            System.exit(1);
        }
    }
}