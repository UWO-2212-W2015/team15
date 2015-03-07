package team15;

import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

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
    public static void main (String[] args){
	User mike = new User ("Mike");
	mike.addLocation ("Toronto Canada");
        ArrayList <Weather> testw = mike.getShortTermWeather(8);
	System.out.println("----- Short Term Example -----");
	for (int i = 0; i < testw.size(); i++){
	    System.out.println("Temp:\t" + testw.get(i).temperature);
	    System.out.println("Sky State:\t" + testw.get(i).skyCondition);
	    System.out.println("Date:\t" + testw.get(i).time.getTime());
	    System.out.println("Icon Height:\t" + testw.get(i).icon.getIconHeight());
	}
	Weather testCurrent = mike.getCurrentWeather();
	System.out.println(testCurrent.icon);

	try{
	    FileOutputStream fout = new FileOutputStream("testObject");
	    ObjectOutputStream oos = new ObjectOutputStream(fout);
	    oos.writeObject(mike);
	    oos.close();
	}catch (Exception e){
	    e.printStackTrace();
	}
	User newUsr = new User("Who");
	try{
	    FileInputStream fin = new FileInputStream("testObject");
	    ObjectInputStream ois = new ObjectInputStream(fin);
	    newUsr = (User)ois.readObject();
	    ois.close();
	}catch (Exception e){
	    e.printStackTrace();
	}
	System.out.println(newUsr.getName());
	testCurrent = newUsr.getCurrentWeather();
	System.out.println("Read object name: " + newUsr.getName());
	System.out.println("Read object temp: " + testCurrent.temperature);
	System.out.println("Read object humidity: " + testCurrent.humidity);
    }
}
