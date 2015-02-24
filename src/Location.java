class Location {
    String userSearch;
    String city;
    String province;
    String country;
    String httpLocation;
    int latitude, longitude;
    
    Location (String searchString){
	this.userSearch = searchString;
	this.httpLocation = userSearch.replace(" ", ",");
    }

    Location (String city, String country){
	this.city = city;
	this.country = country;
        StringBuilder builder = new StringBuilder(city);
	builder.append(",");
	builder.append(country);
	this.httpLocation = builder.toString();
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
    } 
   
    String parseUserSearch (String toParse){
	String stemp = toParse;
	//Remove spaces and replace with commas
	stemp = stemp.replace(" ", ",");
	return stemp;
    }

    String getHttpLocation (){
	return httpLocation;
    }
}

    
