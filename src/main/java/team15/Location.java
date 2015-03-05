package team15;
import java.util.ArrayList;
import java.io.Serializable;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.lang.ClassNotFoundException;

public class Location implements Serializable{
    static int locID = 0;
    String userSearch;
    String city;
    String province;
    String country;
    String httpLocation;
    int latitude, longitude;
    
    transient CurrentForecast currentForecast = new CurrentForecast();
    transient ShortTermForecast shortTermForecast = new ShortTermForecast();

    public Location (String searchString){
	this.userSearch = searchString;
	this.httpLocation = userSearch.replace(" ", ",");
	locID++;
    }

    public Location (String city, String country){
	this.city = city;
	this.country = country;
        StringBuilder builder = new StringBuilder(city);
	builder.append(",");
	builder.append(country);
	this.httpLocation = builder.toString();
	locID++;
    }

    public Location (String city, String province, String country){
    	this.city = city;
	this.province = province;
	this.country = country;
        StringBuilder builder = new StringBuilder(city);
	builder.append(",");
	builder.append(province);
	builder.append(",");
	builder.append(country);
	this.httpLocation = builder.toString();
	locID++;
    } 

    public String getHttpLocation (){
	return httpLocation;
    }

    public String getCityName(){
	return city;
    }

    public String getCountry(){
	return country;
    }

    public String getProvince() {
	return province;
    }

    public Weather getCurrentForecast (){
	currentForecast.updateForecast(this.httpLocation);
	return currentForecast.getWeatherObject();
    }

    public ArrayList <Weather> getShortTermForecast(int num){
	shortTermForecast.updateForecast (this.httpLocation);
	return shortTermForecast.getWeatherList(num);
    }

    private void readObject(ObjectInputStream ois)
	throws IOException, ClassNotFoundException
    {
	ois.defaultReadObject();
	currentForecast = new CurrentForecast();
	shortTermForecast = new ShortTermForecast();
    }
}

