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
import java.util.ArrayList;

import team15.Weather.Weather;
import team15.User.*;

public class LocalWeather extends JFrame implements ActionListener{
    //User variable
    private final User user;
    
    //Checkboxes for preferences menu
    private JCheckBox tempChk;
    private JCheckBox iconChk;
    private JCheckBox windChk;
    private JCheckBox pressureChk;
    private JCheckBox humidityChk;
    private JCheckBox minMaxChk;
    private JCheckBox sunChk;
    private JCheckBox skyCondChk;
    private JCheckBox celChk;
    private JCheckBox fahChk;

    //Variables for the short term forecast panel
    private JLabel STLoc;
    
    //Variables for the long term forecast panel
    private JLabel LTLoc;
    
    private JTabbedPane tabbedPane;
    private JPanel current;
    private JPanel shortTerm;
    private JPanel longTerm;

    private SpringLayout layout_1;

    private JLabel tempNumber = new JLabel();
    private JLabel tempScaleLabel = new JLabel ("c");
    private JLabel locationLabel_1 = new JLabel();

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

    private JFrame dialogFrame;
    
    private JDialog dialog;
	
    /**
     * Constructor for the local weather class
     */
    public LocalWeather(){
        //Defaults for testing
        String name = "Team15";
        String loc = "london,ca";
        
        /*JOptionPane test = new JOptionPane();
        nameString = test.showInputDialog("Enter your name");
        JOptionPane location = new JOptionPane();
        this.locationName = test.showInputDialog("Enter the city and country");*/
        
        user = new User(name);
        user.setCurrentLoc(new Location(loc));

        setTitle("Team 15 Weather");
        setSize(1200, 800); 
        setLocation(100,50);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        
        //Create the tab pages
        createCurrent();
        createShortTerm();
        createLongTerm();

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
        JMenuItem preferences = new JMenuItem ("Preferences");
        preferences.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                prefInit();
            }
        });

        setJMenuBar(menuBar);

        JMenu menu = new JMenu("Menu");
        menu.getAccessibleContext().setAccessibleDescription(
                "Select difference page");
        menuBar.add(menu);
        menu.add(refresh);
        menu.add(preferences);
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
        locationLabel_1 = new JLabel ("Location :   " + user.getCurrentLocation().getHttpLocation());
        locationLabel_1.setBounds(100,100,150,20);
        locationLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
        layout_1.putConstraint(SpringLayout.WEST, locationLabel_1, 20, SpringLayout.WEST, current);
        current.add(locationLabel_1);

        //Temperature label
        tempNumber.setFont(new Font("Tahoma", Font.PLAIN, 90));
        current.add(tempNumber);
        layout_1.putConstraint(SpringLayout.WEST, tempNumber, 120, SpringLayout.WEST, current);
        layout_1.putConstraint(SpringLayout.NORTH, tempNumber, 11, SpringLayout.SOUTH, locationLabel_1);

        tempScaleLabel.setFont(new Font("Tahoma", Font.PLAIN, 60));
        current.add(this.tempScaleLabel);
        layout_1.putConstraint(SpringLayout.WEST, this.tempScaleLabel, 5, SpringLayout.EAST, tempNumber);
        layout_1.putConstraint(SpringLayout.NORTH, this.tempScaleLabel, 16, SpringLayout.SOUTH, locationLabel_1);

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
        
        updateCurrent();
    }
	
    /**
     * a method that builds the short term weather panel
     */
    private void createShortTerm(){
        STLoc = new JLabel ();
        STLoc.setBounds(100,100,150,20);
        STLoc.setFont(new Font("Tahoma", Font.PLAIN, 30));
        
        updateShortTerm();
    }
	
    private void updateShortTerm(){
        STLoc.setText("Location:   " 
                + user.getCurrentLocation().getHttpLocation());  
        
        shortTerm = createForecastPanel(user.getShortTermWeather(), STLoc);
    }
    
    /**
     * A method that builds the long term weather panel
     */
    private void createLongTerm(){
        LTLoc = new JLabel ();
        LTLoc.setBounds(100,100,150,20);
        LTLoc.setFont(new Font("Tahoma", Font.PLAIN, 30));
        
        updateLongTerm();
    }
    
    private void updateLongTerm(){
        LTLoc.setText("Location:   " 
                + user.getCurrentLocation().getHttpLocation());  
        
        longTerm = createForecastPanel(user.getLongTermWeather(), LTLoc);
    }
    
    private JPanel createForecastPanel(ArrayList<Weather> weather, JLabel loc){
        JPanel result = new JPanel();
        result.setLayout(new GridBagLayout());
        
        ArrayList<ForecastCard> cards = new ArrayList();
        
        //Create all the cards to add to the tab
        for(Weather w: weather) 
            cards.add(new ForecastCard(w, user.userPreferences.tempUnits));
        
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
        Preferences pref = user.userPreferences;
        
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
    
    /**
     * 
     * @param ae
     */
    public void actionPerformed(ActionEvent ae) {
        String action = ae.getActionCommand();
        if(action.equals("Preferences_Confirm")){
            user.userPreferences.humidity = humidityChk.isSelected();
            user.userPreferences.icon = iconChk.isSelected();
            user.userPreferences.minMaxTemp = minMaxChk.isSelected();
            user.userPreferences.pressure = pressureChk.isSelected();
            user.userPreferences.sky = skyCondChk.isSelected();
            user.userPreferences.sun = sunChk.isSelected();
            user.userPreferences.temperature = tempChk.isSelected();
            user.userPreferences.wind = windChk.isSelected();
            user.userPreferences.tempUnits = celChk.isSelected();
            
            updateCurrent();
            
            dialog.dispose();
        }
        else if(action.equals("Celsius_Click")){
            fahChk.setSelected(!celChk.isSelected());
        }
        else if(action.equals("Fahrenheit_Click")){
            celChk.setSelected(!fahChk.isSelected());
        }
    }

    private void prefInit(){
        Preferences pref = user.userPreferences;
        
        SpringLayout prefLayout = new SpringLayout();
        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(prefLayout);
        
        dialog = new 
                JDialog(this.dialogFrame, "Current Weather Preferences", true);
        dialog.setResizable(false);
        dialog.getContentPane().add(dialogPanel);
        dialog.setSize(350, 300);
        dialog.setLocation(200, 200);

        tempChk = new JCheckBox("Temperature", pref.temperature);
        prefLayout.putConstraint(SpringLayout.WEST, tempChk, 10, 
                                                SpringLayout.WEST, dialogPanel);
        prefLayout.putConstraint(SpringLayout.NORTH, tempChk, 
                                           10, SpringLayout.NORTH, dialogPanel);
        dialogPanel.add(tempChk);

        iconChk = new JCheckBox("Icon", pref.icon);
        prefLayout.putConstraint(SpringLayout.WEST, iconChk, 10, 
                                                SpringLayout.WEST, dialogPanel);
        prefLayout.putConstraint(SpringLayout.NORTH, iconChk, 40, 
                                                   SpringLayout.NORTH, tempChk);
        dialogPanel.add(iconChk);

        windChk = new JCheckBox("Wind Speed/Direction", pref.wind);
        prefLayout.putConstraint(SpringLayout.WEST, windChk, 10, 
                                                SpringLayout.WEST, dialogPanel);
        prefLayout.putConstraint(SpringLayout.NORTH, windChk, 40, 
                                                   SpringLayout.NORTH, iconChk);
        dialogPanel.add(windChk);

        pressureChk = new JCheckBox("Air Pressure", pref.pressure);
        prefLayout.putConstraint(SpringLayout.WEST, pressureChk, 10, 
                                                SpringLayout.WEST, dialogPanel);
        prefLayout.putConstraint(SpringLayout.NORTH, pressureChk, 
                                               40, SpringLayout.NORTH, windChk);
        dialogPanel.add(pressureChk);

        celChk = new JCheckBox("Celsius", pref.tempUnits);
        prefLayout.putConstraint(SpringLayout.WEST, celChk, 10, 
                                                SpringLayout.WEST, dialogPanel);
        prefLayout.putConstraint(SpringLayout.NORTH, celChk, 40, 
                                               SpringLayout.NORTH, pressureChk);
        dialogPanel.add(celChk);
        celChk.addActionListener(this);
        celChk.setActionCommand("Celsius_Click");
        
        humidityChk = new JCheckBox("Humidity", pref.humidity);
        prefLayout.putConstraint(SpringLayout.WEST, humidityChk, 200, 
                                                SpringLayout.WEST, dialogPanel);
        prefLayout.putConstraint(SpringLayout.NORTH, humidityChk, 10, 
                                               SpringLayout.NORTH, dialogPanel);
        dialogPanel.add(humidityChk);

        minMaxChk = new JCheckBox("Minimum/Maximum", pref.minMaxTemp);
        prefLayout.putConstraint(SpringLayout.WEST, minMaxChk, 200, 
                                                SpringLayout.WEST, dialogPanel);
        prefLayout.putConstraint(SpringLayout.NORTH, minMaxChk, 40, 
                                               SpringLayout.NORTH, humidityChk);
        dialogPanel.add(minMaxChk);

        sunChk = new JCheckBox("Sunset/Sunrise", pref.sun);
        prefLayout.putConstraint(SpringLayout.WEST, sunChk, 200, 
                                                SpringLayout.WEST, dialogPanel);
        prefLayout.putConstraint(SpringLayout.NORTH, sunChk, 40, 
                                                   SpringLayout.NORTH, minMaxChk);
        dialogPanel.add(sunChk);

        skyCondChk = new JCheckBox("Sky Condition", pref.sky);
        prefLayout.putConstraint(SpringLayout.WEST, skyCondChk, 200, 
                                                SpringLayout.WEST, dialogPanel);
        prefLayout.putConstraint(SpringLayout.NORTH, skyCondChk, 40, 
                                                    SpringLayout.NORTH, sunChk);
        dialogPanel.add(skyCondChk);
        
        fahChk = new JCheckBox("Fahrenheit", !pref.tempUnits);
        prefLayout.putConstraint(SpringLayout.WEST, fahChk, 200, 
                                                SpringLayout.WEST, dialogPanel);
        prefLayout.putConstraint(SpringLayout.NORTH, fahChk, 40, 
                                                SpringLayout.NORTH, skyCondChk);
        dialogPanel.add(fahChk);
        fahChk.addActionListener(this);
        fahChk.setActionCommand("Fahrenheit_Click");
        
        JButton confirm = new JButton("Confirm");
        prefLayout.putConstraint(SpringLayout.WEST, confirm, 115, 
                                                SpringLayout.WEST, dialogPanel);
        prefLayout.putConstraint(SpringLayout.NORTH, confirm, 40, 
                                                SpringLayout.NORTH, fahChk);
        dialogPanel.add(confirm);    
        confirm.addActionListener(this);
        confirm.setActionCommand("Preferences_Confirm");
        
        dialog.setVisible(true);
    }
}