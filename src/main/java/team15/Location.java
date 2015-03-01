package team15;

class Location {
    String userSearch;
    String city;
    String province;
    String country;
    String httpLocation;
    int latitude, longitude;
    
    Forecast forecast;

    Location (String searchString){
    	this.userSearch = searchString;
    	this.httpLocation = userSearch.replace(" ", ",");
    	System.out.println(getHttpLocation());
    	forecast = new Forecast(getHttpLocation());
    }

    Location (String city, String country){
	this.city = city;
	this.country = country;
        StringBuilder builder = new StringBuilder(city);
	builder.append(",");
	builder.append(country);
	this.httpLocation = builder.toString();
	forecast = new Forecast(getHttpLocation());
    }

    Location (String city, String province, String country){
    	this.city = city;
	this.province = province;
	this.country = country;
        StringBuilder builder = new StringBuilder(city);
	builder.append(",");
	builder.append(province);
	builder.append(",");
	builder.append(country);
	this.httpLocation = builder.toString();
	forecast = new Forecast(getHttpLocation());
    } 

    String getHttpLocation (){
	return httpLocation;
    }
}

