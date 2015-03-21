package team15.WeatherObjects;

/**
 * The Weather class is constituted by variables present in the weather forecast
 *
 * @author team15
 */

//Imports
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
    private WeatherValue windSpeed, windDirection;
    
    //Sky
    private WeatherValue airPressure, humidity, skyCondition, iconPath;
    public ImageIcon icon;
    
    //Time
    private WeatherValue sunrise, sunset, time;
    
    //Type of weather object this represents
    public WeatherType type;
    
    //Time this object was lastPoll at
    public long lastPoll;
    
    /**
     * Creates an instance of the Weather, class, setting all values to 0 or
     * a default object.
     */
    public Weather(){
        loadDefaults(WeatherType.LOCAL);
    }
    
    /**
     * Creates a weather object with default values and the given type
     * @param t the type of the weather object
     */
    public Weather(WeatherType t){
        loadDefaults(t);
    }
    
    /**
     * Creates new weather object from the given json object
     * @param json the json object containing the weather data
     * @param t The type of the Weather object.  Taken from values in the
     * WeatherType enumeration
     */
    public Weather(JSONObject json, WeatherType t){
        loadDefaults(t);
        
        WeatherValue[] values = this.valueArray();
        String[] keys = this.keyArray();
        
        /*Try to load the value of each key into ot's respective variable. If
         *there is a problem loading the value leave the variable as a default*/
        for(int i = 0; i < values.length; i++){
            try{
                String[] key = keys[i].split("-");

                JSONObject tempJson = json;
                int j = 0;
                while(j < (key.length - 1)){
                    if(key[j].equals("weather"))
                        tempJson = tempJson.getJSONArray("weather")
                                .getJSONObject(0);
                    else tempJson = tempJson.getJSONObject(key[j]);
                    j++;
                }

                values[i].setValue(tempJson.get(key[j]).toString());
            }
            catch(JSONException ex){}
        }
        
        icon = iconList.get(iconPath.toString());
        
        time.setValue(convertTime(time.value));
        
        //Capitalize sky condition string
        convertSkyCondition();
        
        //Change pressure to KPA
        convertPressure();
        
        //Change the wind direct from degree to N, NW etc
        windDirection.setValue(convertDegree(windDirection.value));
        
        //Change the sun rise/set time to just the time
        sunrise.setValue(convertSuntime(sunrise.value));
        sunset.setValue(convertSuntime(sunset.value));

        lastPoll = System.currentTimeMillis();
    }
    
    public String getMonthDay(){
        if(time.value.equals("N/A")) return "N/A";
        return this.time.value.substring(4, 10);
    }
    
    public String getCondition(){
        return this.skyCondition.value;
    }
    
    public String getHumidity(){
        if(humidity.value.equals("N/A")) return "N/A";
        return this.humidity.value + "%";
    }
    
    public String getDate(){
        if(time.value.equals("N/A")) return "N/A";
        return this.time.value.substring(0, 10);
    }
    
    public String getPressure(){
        return this.airPressure.value;
    }
    
    public String getSunset(){
        return this.sunset.value;
    }
    
    public String getSunrise(){
        return this.sunrise.value;
    }
    
    public String getWind(){
        return this.windSpeed + " m/s " + this.windDirection;
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
     * @param t the temperature in Celsius
     * @param system Represents the temperature system to use.
     * False = Fahrenheit, True = Celsius
     * @return a string representing the Kelvin temperature after it has been
     * converted
     */
    private static String convertTemp(String t, boolean system){
        double temp = Double.valueOf(t);
        
        //Check if we need to temp tempriture in fahrenheit
        if(!system) temp = 32+(temp*9)/5;
        
        int result = (int) Math.round(temp);
        
        char deg = '°';
        return result + (deg + (system?"C":"F"));
    }
    
    /**
     * Converts a time value returned from OpenWeather into a Date object
     * @param t the time value from OpenWeather
     * @param full if true return the full date, otherwise return just the time
     * @return the full date or time that the given value t represents
     */
    private static String convertTime(String t){
        if(t.equals("N/A")) return "N/A";
        Date date = new Date();
        date.setTime((long)1000*Integer.valueOf(t));
        return date.toString();
    }
    
    /**
     * Converts the pressure string into its displayable form
     */
    private void convertPressure(){
        if(airPressure.value.equals("N/A")) return;
        Double p = Double.valueOf(this.airPressure.value)/10;
        this.airPressure.setValue(Math.round(p) + " kPa");
    }
    
    private void convertSkyCondition() {
        String[] s = skyCondition.value.split(" ");
        String result = "";
        int i = 1;
        for(String word: s){
            String first = word.substring(0, 1);
            result += first.toUpperCase() + word.substring(1)+" ";
            if((i%2)==0) result += "\n";
            i++;
        }
        skyCondition.setValue(result);
    }
    
    private String convertSuntime(String t){
        if(t.equals("N/A")) return "N/A";
        Date date = new Date();
        date.setTime((long)1000*Integer.valueOf(t));
        return date.toString().substring(11, 16);
    }
    
    /**
     * Loads a new weather object with all default values and a type t
     * @param t the type of the Weather object. Taken from the WeatherType
     * enumeration
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
        
        sunrise = new WeatherValue("N/A");
        sunset = new WeatherValue("N/A");
        time = new WeatherValue("N/A");
        
        type = t;
        lastPoll = 0;
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
   
    /**
     * Converts the given wind direction degree into a cardinal direction or
     * sub cardinal direct
     * @param d a string representing the direction of the wind in degrees
     * @return a label N, NNE, NE, etc representing the direction the wind is
     * blowing
     */
    private static String convertDegree(String d){
        if(d.equals("N/A")) return "N/A";
        Double degree = Double.valueOf(d);
        
        if(degree < 11.25) return "N";
        else if (degree < 33.75) return "NNE";
        else if (degree < 56.25) return "NE";
        else if (degree < 78.75) return "ENE";
        else if (degree < 101.25) return "E";
        else if (degree < 123.75) return "ESE";
        else if (degree < 146.25) return "SE";
        else if (degree < 168.75) return "SSE";
        else if (degree < 191.25) return "S";
        else if (degree < 213.75) return "SSW";
        else if (degree < 236.25) return "SW";
        else if (degree < 258.75) return "WSW";
        else if (degree < 281.25) return "W";
        else if (degree < 303.75) return "WNW";
        else if (degree < 326.25) return "NW";
        else if (degree < 348.75) return "NNW";
        else return "N";
    }
    
    /**
     * Loads the icons that will be used to display the sky state.
     */
    private static void loadIcons(){
        try{
            InputStream fi = ImageIcon.class.getResourceAsStream("/icon.dat");
            ObjectInputStream in = new ObjectInputStream(fi);
            iconList = (HashMap) in.readObject();
            in.close();
        } catch(Exception ex){
            System.out.println("Error loading icons from icon.dat");
            System.exit(1);
        }
    }
}
