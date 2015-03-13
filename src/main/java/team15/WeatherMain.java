package team15;

/**

 * @author Team 15
 */

import java.io.IOException;
import org.json.JSONException;
import team15.GUI.LocalWeather;
import team15.UserClasses.User;

public class WeatherMain {
    public static void main(String[] args) {
        User u = null;
        
        //Why can't this catch FileNotFoundException if loadUser throws it?
        try {
            u = User.loadUser();
        } catch (IOException ex) {
            System.out.println("Error reading file or file not found.");
        } catch (ClassNotFoundException ex) {
            System.out.println("Class User not found. Major error");
            return;
        }
        
        try {
            if(u == null){
                u = new User("london,ca");
            }
        } catch (IOException ex) {
            System.out.println("URL wrong, internet error, or open weather "
                    + "failed to return a json string");
            return;
        } catch (JSONException ex) {
            System.out.println("json string failed to parse");
            return;
        }
 
        LocalWeather frame = new LocalWeather(u);
    }
}
