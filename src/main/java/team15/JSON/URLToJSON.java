package team15.JSON;

/**
 * This class takes a URL and creates a JSON object from the data contained
 * at that URL
 * @author team15
 */

//Imports
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;


public class URLToJSON{
    /**
     * Makes a JSON object from the data at the given url
     * @param url the target url from which to poll data
     * @return a JSON object made from the data at the target url
     * @throws MalformedURLException thrown if the given url is improperly 
     * constructed
     * @throws IOException thrown if there is a problem opening, closing or
     * reading from the target url
     * @throws JSONException thrown if there is a problem creating the JSON
     * object from the data at the target url
     */
    public static JSONObject makeJSON(String url) 
                       throws MalformedURLException, IOException, JSONException{
        URL in = new URL(url);
        BufferedReader input = new BufferedReader(
                                        new InputStreamReader(in.openStream()));    

        JSONObject json = new JSONObject(input.readLine());
        input.close();
        return json;
    }
}