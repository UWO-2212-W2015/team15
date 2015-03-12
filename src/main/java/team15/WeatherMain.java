package team15;

/**

 * @author Team 15
 */

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.JFrame;
import org.json.JSONException;
import team15.GUI.LocalWeather;
import team15.User.User;

public class WeatherMain {
    public static void main(String[] args) {
        User u = null;
        
        try{
            u = User.loadUser();
        }
        catch(Exception e){
            System.out.println("user.dat does not exist or is wrong.");
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
