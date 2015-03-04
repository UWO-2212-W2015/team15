package team15;
import java.util.ArrayList;

public class Location {
    static int locID = 0;
    String userSearch;
    String city;
    String province;
    String country;
    String httpLocation;
    int latitude, longitude;
    
    CurrentForecast currentForecast = new CurrentForecast();
    ShortTermForecast shortTermForecast = new ShortTermForecast();

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
}

