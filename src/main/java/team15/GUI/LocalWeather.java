/**
 * Local Weather class asks the user for their name and location and builds a user object using that data
 * It then constructs a UI using the weather data that is inside the user object.
 */

package team15.GUI;


import javax.swing.*;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import org.json.JSONException;

import team15.Weather.Weather;
import team15.User.*;

public class LocalWeather extends JFrame{
    //User variable
    private final User user;
    
    //Error label
    private JLabel error;
    
    //Refresh Time Label
    private JLabel reflabel;
    
    //Location labels
    private JLabel curLoc;
    private JLabel shortLoc;
    private JLabel longLoc;
    
    private JTabbedPane tabbedPane;
    private JPanel current;
    private JPanel shortTerm;
    private JPanel longTerm;

    private SpringLayout layout_1;

    private JLabel tempNumber = new JLabel();
    private JLabel tempScaleLabel = new JLabel ("c");

    private JLabel windSpeedLabel = new JLabel();
    private JLabel windDirectionLabel = new JLabel();
    private JLabel airPressureLabel = new JLabel();
    private JLabel humidityLabel = new JLabel();
    private JLabel skyConditionLabel = new JLabel();
    private JLabel expMinLabel = new JLabel();
    private JLabel expMaxLabel = new JLabel();
    private JLabel sunriseLabel = new JLabel();
    private JLabel sunsetLabel = new JLabel();
    private JLabel iconLabel = new JLabel();

    /**
     * Constructor for the local weather class
     */
    public LocalWeather(User u){
        super();
        this.user = u;
        
        error = new JLabel();
        reflabel = new JLabel();
        
        curLoc = new JLabel();
        curLoc.setBounds(100,100,150,20);
        curLoc.setFont(new Font("Tahoma", Font.PLAIN, 30));
        
        shortLoc = new JLabel();
        shortLoc.setBounds(100,100,150,20);
        shortLoc.setFont(new Font("Tahoma", Font.PLAIN, 30));
        
        longLoc = new JLabel();
        longLoc.setBounds(100,100,150,20);
        longLoc.setFont(new Font("Tahoma", Font.PLAIN, 30));
                
        setTitle("Team 15 Weather");
        setSize(1200, 800); 
        setLocation(100,50);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        
        //Create the tab pages
        createCurrent();
        
        updatePanels();
        
        //Create new tabbed pane
        this.tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBackground(Color.LIGHT_GRAY);
        tabbedPane.setPreferredSize(new Dimension(5, 800));
        tabbedPane.setMinimumSize(new Dimension(5, 500));
        tabbedPane.setSize(new Dimension(0, 500));

        //Add the tabs
        tabbedPane.addTab("Current", current);
        tabbedPane.addTab("Short Term Forecast", shortTerm);
        tabbedPane.addTab("Longterm Forecast", longTerm);
        getContentPane().add(tabbedPane);

        //Add menu bar and menus
        JMenuBar menuBar = new JMenuBar();
        
        JMenuItem refresh = new JMenuItem("Refresh");
        refresh.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                System.out.println("Refresh " + (refresh()?"failed":"passed"));
            }
        });
        
        JMenuItem preferences = new JMenuItem ("Preferences");
        preferences.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                PreferencesDialog.makeWindow(user);
                updateCurrent();
            }
        });

        setJMenuBar(menuBar);

        JMenu menu = new JMenu("Menu");
        menu.getAccessibleContext().setAccessibleDescription(
                "Select difference page");
        menuBar.add(menu);
        menu.add(refresh);
        menu.add(preferences);
        
        this.setVisible(true);
        this.setResizable(false);

        //Close frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        refresh();
        
        
}
	
    /**
     * a method that builds the current weather panel
     */
    private void createCurrent(){
        //Set layout
        layout_1 = new SpringLayout();
        current = new JPanel();
        current.setLayout(layout_1);

        //Location label
        layout_1.putConstraint(SpringLayout.WEST, curLoc, 20, SpringLayout.WEST, current);
        current.add(curLoc);

        //Temperature label
        tempNumber.setFont(new Font("Tahoma", Font.PLAIN, 90));
        current.add(tempNumber);
        layout_1.putConstraint(SpringLayout.WEST, tempNumber, 120, SpringLayout.WEST, current);
        layout_1.putConstraint(SpringLayout.NORTH, tempNumber, 11, SpringLayout.SOUTH, curLoc);

        tempScaleLabel.setFont(new Font("Tahoma", Font.PLAIN, 60));
        current.add(this.tempScaleLabel);
        layout_1.putConstraint(SpringLayout.WEST, this.tempScaleLabel, 5, SpringLayout.EAST, tempNumber);
        layout_1.putConstraint(SpringLayout.NORTH, this.tempScaleLabel, 16, SpringLayout.SOUTH, curLoc);

        //Air pressure label
        airPressureLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
        layout_1.putConstraint(SpringLayout.WEST, airPressureLabel, 30, SpringLayout.EAST, tempScaleLabel);
        layout_1.putConstraint(SpringLayout.NORTH, airPressureLabel, 80, SpringLayout.NORTH, current);
        current.add(airPressureLabel);

        //Humidity Label
        humidityLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
        layout_1.putConstraint(SpringLayout.WEST, humidityLabel, 30, SpringLayout.EAST, tempScaleLabel);
        layout_1.putConstraint(SpringLayout.NORTH, humidityLabel, 10, SpringLayout.SOUTH, airPressureLabel);
        current.add(humidityLabel);

        //skyCondition Label
        skyConditionLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
        layout_1.putConstraint(SpringLayout.WEST, skyConditionLabel, 30, SpringLayout.EAST, tempScaleLabel);
        layout_1.putConstraint(SpringLayout.NORTH, skyConditionLabel, 10, SpringLayout.SOUTH, humidityLabel);
        current.add(skyConditionLabel);

        //Expected Minimum Label
        expMinLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
        layout_1.putConstraint(SpringLayout.WEST, expMinLabel, 30, SpringLayout.EAST, tempScaleLabel);
        layout_1.putConstraint(SpringLayout.NORTH, expMinLabel, 10, SpringLayout.SOUTH, skyConditionLabel);
        current.add(expMinLabel);

        //Expected Maxmimum Label
        expMaxLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
        layout_1.putConstraint(SpringLayout.WEST, expMaxLabel, 30, SpringLayout.EAST, tempScaleLabel);
        layout_1.putConstraint(SpringLayout.NORTH, expMaxLabel, 10, SpringLayout.SOUTH, expMinLabel);
        current.add(expMaxLabel);

        //Wind Speed Label
        windSpeedLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
        layout_1.putConstraint(SpringLayout.WEST, windSpeedLabel, 30, SpringLayout.EAST, tempScaleLabel);
        layout_1.putConstraint(SpringLayout.NORTH, windSpeedLabel, 10, SpringLayout.SOUTH, expMaxLabel);
        current.add(windSpeedLabel);

        //Wind Direction Label
        windDirectionLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
        layout_1.putConstraint(SpringLayout.WEST, windDirectionLabel, 30, SpringLayout.EAST, tempScaleLabel);
        layout_1.putConstraint(SpringLayout.NORTH, windDirectionLabel, 10, SpringLayout.SOUTH, windSpeedLabel);
        current.add(windDirectionLabel);

        //Sunrise Label
        sunriseLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
        layout_1.putConstraint(SpringLayout.WEST, sunriseLabel, 30, SpringLayout.EAST, tempScaleLabel);
        layout_1.putConstraint(SpringLayout.NORTH, sunriseLabel, 10, SpringLayout.SOUTH, windDirectionLabel);
        //current.add(sunriseLabel);

        //Sunset Label
        sunsetLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
        layout_1.putConstraint(SpringLayout.WEST, sunsetLabel, 30, SpringLayout.EAST, tempScaleLabel);
        layout_1.putConstraint(SpringLayout.NORTH, sunsetLabel, 10, SpringLayout.SOUTH, sunriseLabel);
        //current.add(sunsetLabel);

        //Icon Label
        layout_1.putConstraint(SpringLayout.WEST, iconLabel, 30, SpringLayout.WEST, current);
        layout_1.putConstraint(SpringLayout.NORTH, iconLabel, 60, SpringLayout.NORTH, current);
        current.add(iconLabel);
    }

    private JPanel createForecastPanel(ArrayList<Weather> weather, JLabel loc){
        JPanel result = new JPanel();
        result.setLayout(new GridBagLayout());
        
        ArrayList<ForecastCard> cards = new ArrayList();
        
        //Create all the cards to add to the tab
        for(Weather w: weather) 
            cards.add(new ForecastCard(w, user.pref.tempUnits));
        
        //Set up the grid bag constraints
        GridBagConstraints gc = new GridBagConstraints();
        gc.weightx = 0.5;
        gc.weighty = 0.5;
        
        //Add the location label
        gc.gridx = 0;
        gc.gridy = 0;
        result.add(loc, gc);
        
        //Add each JPanel to the tab
        for(int i = 0; i < 8; i++){
            gc.gridx = i%4;
            gc.gridy = (i/4) + 1;
            result.add(cards.get(i), gc);
        }
        
        return result;
    }
    
    /**
     * a method that gets the values out of the weather object and changes the 
     * labels for the current pane this method also writes the labels depending 
     * on the preferences
     */
    private void updateCurrent(){
        Weather w = user.getCurrentWeather();
        Preferences pref = user.pref;
        
        if(pref.temperature){
            tempNumber.setText("" + w.getTemp(pref.tempUnits));
            tempScaleLabel.setText(pref.tempUnits?"c":"f");
        }
        else{
            tempNumber.setText("");
            tempScaleLabel.setText("");
        }

        if(pref.wind){
            windSpeedLabel.setText("WindSpeed: " + w.windSpeed);
            windDirectionLabel.setText("Wind Direction: " + w.windDirection + " degrees");
        } 
        else{
            windSpeedLabel.setText("");
            windDirectionLabel.setText("");
        }

        if(pref.pressure){
            airPressureLabel.setText("Air Pressure: " + w.airPressure);
        }
        else{
            airPressureLabel.setText("");
        }

        if(pref.humidity){
            humidityLabel.setText("Humidity: " + w.humidity);
        }else
        {
            humidityLabel.setText("");
        }

        if(pref.sky){
            skyConditionLabel.setText("Condition: " + w.skyCondition);
        }
        else{
            skyConditionLabel.setText("");
        }

        if(pref.icon){
            iconLabel = new JLabel(w.icon);
        }
        else{
           iconLabel = new JLabel(); 
        }
        
        if(pref.minMaxTemp){
            String lbl = "Minimum Temperature: " 
                    + w.getMinTemp(pref.tempUnits) 
                    + (pref.tempUnits?"c":"f");
            expMinLabel.setText(lbl);
            lbl = "Maximum Temperature: " + w.getMaxTemp(pref.tempUnits)
                    + (pref.tempUnits?"c":"f");
            expMaxLabel.setText(lbl);
        } 
        else{
            expMinLabel.setText("");
            expMaxLabel.setText("");
        }

        if(pref.sun){
            sunriseLabel.setText("Sunrise: " + w.sunrise);
            sunsetLabel.setText("Sunset: " + w.sunset);
        } 
        else{
            sunriseLabel.setText("");
            sunsetLabel.setText("");
        }
    }  
    
    private boolean refresh(){
        try{
            user.getCurrentLocation().updateForecasts();
        } catch (IOException ex) {
            error.setText("Error: problem connecting to OpenWeather.com");
            return false;
        } catch (JSONException ex) {
            error.setText("Error: Unable to get new data from OpenWeather.com");
            return false;
        }
        
        //Update the last refresh time
        Date time = new Date(System.currentTimeMillis());
        reflabel.setText("Last Refresh: " + time);
        
        try{
            user.saveUser();
        }
        catch(Exception e){
            error.setText("Error: Could not save new weather data to disk");
        }
        
        updatePanels();
        
        return true;
    }
    
    private void updatePanels(){
        String newLoc = "Location :   " + user.getCurrentLocation();
        
        curLoc.setText(newLoc);
        shortLoc.setText(newLoc);
        longLoc.setText(newLoc);
        
        updateCurrent();
        shortTerm = createForecastPanel(user.getShortTermWeather(), shortLoc);
        longTerm = createForecastPanel(user.getLongTermWeather(), longLoc);
        
                
    }
}