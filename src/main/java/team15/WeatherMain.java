package team15;

import javax.swing.JFrame;
import team15.GUI.LocalWeather;

/**
 * The WetherMain class starts the Graphical User Interface (GUI) and loads the data for
 * the chosen user. This is the base of the Weather Forecast project (team15). The first
 * access to the program will require a user registration. The new user can then search for
 * her locations of interest and verify the current, short and long term forecast. Next 
 * accesses to the system will also load the user's preferences.
 *
 * @author Team 15
 * @version
 */

public class WeatherMain {
    public static void main(String[] args) {
        LocalWeather frame = new LocalWeather();
        frame.setVisible(true);
        frame.setResizable(false);

        //Close frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
