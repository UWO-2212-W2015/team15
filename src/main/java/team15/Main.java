package team15;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Main {
    public static void main (String[] args){
	User mike = new User ("Mike");
	mike.addLocation ("Toronto Canada");
	WeatherBuilder test = new WeatherBuilder();
        ArrayList <Weather> testw = mike.getShortTermWeather();
	System.out.println("----- Short Term Example -----");
	for (int i = 0; i < 9; i++){
	    System.out.println("Temp:\t" + testw.get(i).temperature);
	    System.out.println("Sky State:\t" + testw.get(i).skyCondition);
	    System.out.println("Date:\t" + testw.get(i).time.getTime());
	}
    }
}
