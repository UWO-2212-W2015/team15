package team15;

class Location {
    static int locID = 0;
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
	locID++;
    }

    Location (String city, String country){
	this.city = city;
	this.country = country;
        StringBuilder builder = new StringBuilder(city);
	builder.append(",");
	builder.append(country);
	this.httpLocation = builder.toString();
	locID++;
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
	locID++;
    } 

    String getHttpLocation (){
	return httpLocation;
    }

    String getCityName(){
	return city;
    }

    String getCountry(){
	return country;
    }

    String getProvince() {
	return province;
    }
}

