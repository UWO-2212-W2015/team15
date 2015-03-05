package team15;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

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
    }
}
