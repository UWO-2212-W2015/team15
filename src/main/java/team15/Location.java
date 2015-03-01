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
<<<<<<< HEAD:src/Location.java
	this.userSearch = searchString;
	this.httpLocation = userSearch.replace(" ", ",");
	locID++;
=======
    	this.userSearch = searchString;
    	this.httpLocation = userSearch.replace(" ", ",");
    	System.out.println(getHttpLocation());
    	forecast = new Forecast(getHttpLocation());
>>>>>>> 5d93f0f532c5d11f32c0c048362055eb01906b22:src/main/java/team15/Location.java
    }

    Location (String city, String country){
	this.city = city;
	this.country = country;
        StringBuilder builder = new StringBuilder(city);
	builder.append(",");
	builder.append(country);
	this.httpLocation = builder.toString();
<<<<<<< HEAD:src/Location.java
	locID++;
=======
	forecast = new Forecast(getHttpLocation());
>>>>>>> 5d93f0f532c5d11f32c0c048362055eb01906b22:src/main/java/team15/Location.java
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
<<<<<<< HEAD:src/Location.java
	locID++;
=======
	forecast = new Forecast(getHttpLocation());
>>>>>>> 5d93f0f532c5d11f32c0c048362055eb01906b22:src/main/java/team15/Location.java
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

