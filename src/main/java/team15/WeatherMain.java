package team15;

/**

 * @author Team 15
 */

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import org.json.JSONException;
import team15.GUI.LocalWeather;
import team15.User.User;

public class WeatherMain {
    public static void main(String[] args) {
        User u;
        try {
            u = new User("london, ca");
        } catch (IOException ex) {
            Logger.getLogger(WeatherMain.class.getName()).log(Level.SEVERE, null, ex);
            return;
        } catch (JSONException ex) {
            Logger.getLogger(WeatherMain.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        
        LocalWeather frame = new LocalWeather(u);
        frame.setVisible(true);
        frame.setResizable(false);

        //Close frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
