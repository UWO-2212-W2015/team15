package team15.GUI;

/**
 * This class is the main GUI for the OpenWeather API program. It handles the
 * loading and saving of data during the session. 
 * 
 * As well it handles building the entire GUI, changing the users preferences,
 * and refreshing all the weather and forecast data.
 * 
 * Views supported are a local view with a customizable display, a short term
 * forecast over 24h in 3h increments, and a long term forecast that is daily
 * over 8 days.
 * 
 * @author team15
 */

//Imports
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import team15.UserOjects.User;
import team15.WeatherObjects.LocationWeather;

public class OpenWeatherGUI extends JFrame{
    //User variable
    private static User user;
    private static LocationWeather locWeather;
    
    /**
     * The main GUI window class for the OpenWeather API program.
     */
    public OpenWeatherGUI(){
        super();

        //Frame settings
        this.setLocation(100,50);
        this.setSize(730, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //If the user has no locations make sure that they add at least one
        if(user.getLocations().isEmpty()){
            startLoactionDialog();
            
            if(user.getLocations().isEmpty()){
                System.out.println("Location list can not be empty.");
                System.exit(1);
            }  
        }

        //Create the menu bar
        JMenuBar menuBar = new JMenuBar();
        
        //Add the menu bar
        this.setJMenuBar(menuBar);
        
        //Create a new JMenu
        JMenu menu = new JMenu("Menu");
        menu.getAccessibleContext()
                            .setAccessibleDescription("Select difference page");
        menuBar.add(menu);
        
        //Create the menu items
        JMenuItem ref = new JMenuItem("Refresh");
        JMenuItem preferences = new JMenuItem ("Preferences");
        JMenuItem locationList = new JMenuItem ("Locations Menu");
        JMenuItem exit = new JMenuItem ("Exit");
        
        //Add the menu items
        menu.add(preferences);
        menu.add(locationList);
        menu.add(ref);
        menu.add(exit);
        
        //Add the menu item action listeners
        //Refresh action listener
        ref.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                updatePanels();
            }
        });
        
        //Preferences action listener
        preferences.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                //Open the edit preferences dialog
                PreferencesDialog preferences = new PreferencesDialog(user);
                
                //Check if the preferences were saved if no display an error
                if(preferences.wasUpdated()) updatePanels();
                else{
                    //updateError("Error: failed to save user "      + "data to the local drive.");
                }
            }
        });
        
        //Locations action listener
        locationList.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                startLoactionDialog();
                updatePanels();
            }
        });
        
        //Exit button action listener
        exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                System.exit(0);
            }
        });
        
        //Try and refresh the data
        this.updatePanels();       
    }
    
    /**
     * Updates each panel with the most recent weather data, forecast data, 
     * location data and preferences.
     */
    private void updatePanels(){
        String refresh = locWeather.getRefresh();
        String error = locWeather.updateForecasts();
        
        this.setTitle(locWeather.toString());
        
        SpringLayout layout = new SpringLayout();
        JPanel view = new JPanel();
        view.setLayout(layout);
        view.setBackground(new Color(210, 229, 243));

        boolean units = user.pref.tempUnits;
        
        JPanel local = new LocalPanel(locWeather, user.pref);
        JPanel shortTerm = new ShortTermPanel(locWeather.getShortTerm(), units);
        JPanel longTerm = new LongTermPanel(locWeather.getLongTerm(), units);
        
        layout.putConstraint(SpringLayout.WEST, local, 0, SpringLayout.WEST, view);
        layout.putConstraint(SpringLayout.NORTH, local, 0, SpringLayout.NORTH, view);
 
        layout.putConstraint(SpringLayout.WEST, shortTerm, 20, SpringLayout.WEST, view);
        layout.putConstraint(SpringLayout.NORTH, shortTerm, 35, SpringLayout.SOUTH, local);
        
        layout.putConstraint(SpringLayout.WEST, longTerm, 0, SpringLayout.WEST, shortTerm);
        layout.putConstraint(SpringLayout.NORTH, longTerm, 50, SpringLayout.SOUTH, shortTerm);
        
        layout.putConstraint(SpringLayout.EAST, view, 712, SpringLayout.WEST, local);
        
        JLabel lblRef = new JLabel("LATEST UPDATE: ");
        lblRef.setForeground(new Color(1, 61, 134));
        lblRef.setFont(new Font("Tahoma", Font.PLAIN, 8));
        JLabel ref = new JLabel(locWeather.getRefresh().substring(0,16));
        ref.setFont(new Font("Tahoma", Font.PLAIN, 8));
        
        layout.putConstraint(SpringLayout.EAST, ref, -3, SpringLayout.EAST, view);
        layout.putConstraint(SpringLayout.NORTH, ref, 25, SpringLayout.SOUTH, longTerm);
        
        layout.putConstraint(SpringLayout.EAST, lblRef, 0, SpringLayout.WEST, ref);
        layout.putConstraint(SpringLayout.NORTH, lblRef, 0, SpringLayout.NORTH, ref);
        
        
        layout.putConstraint(SpringLayout.SOUTH, view, 0, SpringLayout.SOUTH, lblRef);
        
        view.add(shortTerm);
        view.add(longTerm);
        view.add(local);
        view.add(lblRef);
        view.add(ref);
        this.add(view);
        this.setVisible(true);
    }
 
    /**
     * Creates the location dialog and handles any possible errors it may
     * produce.
     */
    private void startLoactionDialog(){
        LocationsDialog window = new LocationsDialog(user);
        window.dispose();
        locWeather = new LocationWeather(user.getCurrentLocation());
    }
    
    /**
     * The main function of the OpenWeather Api program.
     * 
     * It deals with the loading or creating of the user object and then
     * calls the constructor of the GUI
     * @param args unused
     */
    public static void main(String args[]){
        user = null;
        
        //Make sure the cache folder exists, and if not create it.
        try{
            File cache = new File("WeatherCache");
            if(!cache.isDirectory()) cache.mkdir();
        }
        catch(Exception ex){
            System.out.println("Fatal Error: Could not create cache folder");
            System.exit(1);
        }
        
        //Try to load the previously saved user file from user.dat
        try {
            user = User.loadUser();
        } catch (IOException ex) {
            File f = new File("user.dat");
            if(f.isFile()){
                System.out.println("Error: user.dat exists but could "
                                                            + "not be loaded.");
                return;
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: User class not found when loading "
                                       + "user.dat.  Likely version mismatch.");
            return;
        }
        
        //If the user was not loaded from user.dat create a new user object
        if(user == null){
            user = new User();
            
            try {
                user.saveUser();
            } catch (IOException ex) {
                System.out.println("Error: Could not save the initial User "
                                                        + "object to user.dat");
                return;
            }
        }

        locWeather = new LocationWeather(user.getCurrentLocation());
        new OpenWeatherGUI();
    }
}