package team15;

/**

 * @author Team 15
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.TreeMap;
import team15.GUI.LocalWeather;
import team15.UserOjects.Location;
import team15.UserOjects.User;

public class WeatherMain {
    private static TreeMap<String, ArrayList<Location>> locations;
    private static InputStream in = File.class.getResourceAsStream("/citylist.txt");
    
    public static void main(String[] args) {
        User u = null;
        
        try {
            loadLocations();
        } catch (IOException ex) {
            System.out.println("Error loading locations list.");
            System.out.println(ex.getMessage());
            return;
        }
        
        //Why can't this catch FileNotFoundException if loadUser throws it?
        try {
            u = User.loadUser();
        } catch (IOException ex) {
            System.out.println("Error reading file or file not found.");
        } catch (ClassNotFoundException ex) {
            System.out.println("Class User not found. Major error (try deleting user.in if in beta)");
            return;
        }
        
        if(u == null) u = new User();
 
        LocalWeather frame = new LocalWeather(u, locations);
    }
    
    /**
     * Loads the list of all valid openweather locations from citylist.txt
     * @throws FileNotFoundException if citylist.txt is not found
     * @throws IOException if there is a problem loading the treemap from
     * citylist.txt
     */
    private static void loadLocations() throws FileNotFoundException, IOException{
        locations = new TreeMap();
 
        BufferedReader input =  new BufferedReader(new InputStreamReader(in));
        
        ArrayList<Location> locs = new ArrayList();
        String key = "";
        
        while(input.ready()){
            String in = input.readLine();
            String[] s = in.split("\t");
            String lat = "";
            String lng = "";
            
            if(s.length == 5){
                lat = s[3];
                lng = s[4];
            }
            
            Location l = new Location(s[0], s[1], s[2], lat, lng);
            if(key.isEmpty()) key = s[0];
            
            if(!key.equals(s[0])){
                locations.put(key, locs);
                key = s[0];
                
                locs = new ArrayList();
            }
            
            locs.add(l);
        }
        locations.put(key, locs);
        
        //output.close();
        input.close(); 
    }
}
