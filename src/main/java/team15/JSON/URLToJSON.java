package team15.JSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class takes a URL and creates a JSON object from the data contained
 * at that URL
 * @author team15
 */
public class URLToJSON{
    /**
     * Makes a JSON object from the data at the specified URL
     * @param url the target url to get data from
     * @return A JSON object created from the data at the target URL or null if
     * there is an error
     */
    public static JSONObject makeJSON(String url){
        URL in;
        BufferedReader input;

        //Attempt to open the url connection and creater a reader for the
        //text in the url
        try {
            in = new URL(url); //Create a new url to openweather
            //create a new input reader to read from the r
            input = new BufferedReader(
                    new InputStreamReader(in.openStream()));    
        } 
        catch (IOException e) {
            System.out.println("Error opening connection to URL");
            e.printStackTrace();
            return null;
        } 
        
        //Attempt to read from the URL and create the JSON from the given string
        JSONObject json = null;
        try{
            json = new JSONObject(input.readLine());
            input.close();
        }
        catch(JSONException e){
            System.out.println("Error creating the JSON");
            e.printStackTrace();
        }
        catch (IOException e) {
            System.out.println("Error reading from the URL");
            e.printStackTrace();
        }
        
        return json;
    }
}