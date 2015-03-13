package team15.GUI;

/**
 * 
 * @author team15
 */

import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import org.json.JSONException;

import team15.User.User;

public class LocalWeather extends JFrame{
    //User variable
    private final User user;
    
    //Strings for error and refresh time
    String error, refresh;
    
    //Container for tabs
    private final JTabbedPane tabbedPane;
    
    //Tabs
    private final LocalPanel local;
    private final ForecastPanel shortTerm, longTerm;

    /**
     * 
     * @param u 
     */
    public LocalWeather(User u){
        super();
        
        this.user = u;
        error = "";
        refresh = user.refresh;
        
        this.setTitle("Team 15 Weather");
        this.setSize(1200, 800); 
        this.setLocation(100,50);
        this.getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        
        //Create the tab pages
        local = new LocalPanel();
        shortTerm = new ForecastPanel();
        longTerm = new ForecastPanel();
        
        updatePanels();
        
        //Create new tabbed pane
        this.tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBackground(Color.LIGHT_GRAY);
        tabbedPane.setPreferredSize(new Dimension(5, 800));
        tabbedPane.setMinimumSize(new Dimension(5, 500));
        tabbedPane.setSize(new Dimension(0, 500));

        //Add the tabs
        tabbedPane.addTab("Current", local);
        tabbedPane.addTab("Short Term Forecast", shortTerm);
        tabbedPane.addTab("Longterm Forecast", longTerm);
        this.getContentPane().add(tabbedPane);

        //Add menu bar and menus
        JMenuBar menuBar = new JMenuBar();
        
        JMenuItem ref = new JMenuItem("Refresh");
        ref.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                refresh();
            }
        });
        
        JMenuItem preferences = new JMenuItem ("Preferences");
        preferences.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                PreferencesDialog preferences = new PreferencesDialog(user);
                
                if(preferences.wasUpdated()) updatePanels();
                else{
                    error = "Error saving prefernces to local drive";
                    updateError();
                }
            }
        });
        
        JMenuItem locationList = new JMenuItem ("Change/Add Locations");
        locationList.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                LocationsDialog preferences = new LocationsDialog(user);
            }
        });

        setJMenuBar(menuBar);

        JMenu menu = new JMenu("Menu");
        menu.getAccessibleContext().setAccessibleDescription(
                "Select difference page");
        menuBar.add(menu);
        
        menu.add(preferences);
        menu.add(locationList);
        menu.add(ref);
        
        this.setVisible(true);
        this.setResizable(false);

        //Close frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        refresh();       
    }
    
    private String refreshForecasts(){
        try{
            user.getCurrentLocation().updateForecasts();
        } catch (IOException ex) {
            return "Error: problem connecting to OpenWeather.com";
        } catch (JSONException ex) {
            return "Error: Unable to get data from OpenWeather.com";
        }
        
        //Update the last refreshForecasts time
        Date time = new Date(System.currentTimeMillis());
        refresh = time.toString();
        user.refresh = refresh;
        
        try{
            user.saveUser();
        }
        catch(Exception e){
            return "Error: Could not save new weather data to disk";
        }
        
        return "";
    }
    
    private void updatePanels(){
        String location = user.getCurrentLocation().toString();
        boolean units = user.pref.tempUnits;
        
        local.update(user.getLocalWeather(), user.pref, location, refresh);
        shortTerm.update(user.getShortTermForecast(), units, location, refresh);
        longTerm.update(user.getLongTermForecast(), units, location, refresh);
    }
    
    private void updateError(){
        local.setError(error);
        shortTerm.setError(error);
        longTerm.setError(error);
    }
    
    private void refresh(){
        error = refreshForecasts();
        updateError();
        updatePanels();
    }
}