class mike_main{
    public static void main (String[] args){
        Location loc1 = new Location ("Toronto---Canada");
	Location loc2 = new Location ("Edmonton");
        User mike = new User("Mike");
	mike.addLocation("London");
	System.out.println(mike.getCurrentLocation().httpLocation);
    }
}
