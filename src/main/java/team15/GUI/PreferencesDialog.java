package team15.GUI;

/**
 * A dialog window that allows the user to edit the display settings of the
 * program
 * 
 * @author team15
 */

//Imports
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import team15.UserOjects.Preferences;
import team15.UserOjects.User;

public class PreferencesDialog extends JDialog{
    //Check boxes
    private static JCheckBox tempChk, windChk, pressureChk, 
                     humidityChk, minMaxChk, sunChk, skyCondChk, celChk, fahChk;
    
    //A field to track if the dialog successfully updates the settings
    private static boolean updated;
    
    /**
     * Creates a new dialog window from the given users preferences and allows
     * the user to change their settings
     * @param user the user object that contains all the settings
     */
    public PreferencesDialog(final User user){
        super(new JFrame(), "Current Weather Preferences", true);
        
        updated = false;
        
        Preferences pref = user.pref;
        
        //Layout and panel settings
        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        panel.setLayout(layout);
        
        //Set up the dialog parameters
        this.setResizable(false);
        this.getContentPane().add(panel);
        this.setSize(350, 300);
        this.setLocation(200, 200);

        //Add all the checkboxes to the dialog
        //Tempriture check box
        tempChk = new JCheckBox("Temperature", pref.temperature);
        layout.putConstraint(SpringLayout.WEST, tempChk, 10, 
                                                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, tempChk, 
                                           10, SpringLayout.NORTH, panel);
        panel.add(tempChk);

        //Wind check box
        windChk = new JCheckBox("Wind Speed/Direction", pref.wind);
        layout.putConstraint(SpringLayout.WEST, windChk, 10, 
                                                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, windChk, 40, 
                                                   SpringLayout.NORTH, tempChk);
        panel.add(windChk);
        
        //Pressure check box
        pressureChk = new JCheckBox("Air Pressure", pref.pressure);
        layout.putConstraint(SpringLayout.WEST, pressureChk, 10, 
                                                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, pressureChk, 
                                               40, SpringLayout.NORTH, windChk);
        panel.add(pressureChk);

        //Sky condition check box
        skyCondChk = new JCheckBox("Sky Condition", pref.sky);
        layout.putConstraint(SpringLayout.WEST, skyCondChk, 10, 
                                                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, skyCondChk, 40, 
                                                    SpringLayout.NORTH, pressureChk);
        panel.add(skyCondChk);
        
        //Celsius check box
        celChk = new JCheckBox("Celsius", pref.tempUnits);
        layout.putConstraint(SpringLayout.WEST, celChk, 10, 
                                                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, celChk, 40, 
                                               SpringLayout.NORTH, skyCondChk);
        panel.add(celChk);

        //Humidty check box
        humidityChk = new JCheckBox("Humidity", pref.humidity);
        layout.putConstraint(SpringLayout.WEST, humidityChk, 200, 
                                                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, humidityChk, 10, 
                                               SpringLayout.NORTH, panel);
        panel.add(humidityChk);

        //Min/max temperature check box
        minMaxChk = new JCheckBox("Minimum/Maximum", pref.minMaxTemp);
        layout.putConstraint(SpringLayout.WEST, minMaxChk, 200, 
                                                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, minMaxChk, 40, 
                                               SpringLayout.NORTH, humidityChk);
        panel.add(minMaxChk);

        //Sunrise/sunset check box
        sunChk = new JCheckBox("Sunset/Sunrise", pref.sun);
        layout.putConstraint(SpringLayout.WEST, sunChk, 200, 
                                                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, sunChk, 40, 
                                                 SpringLayout.NORTH, minMaxChk);
        panel.add(sunChk);
        
        //Fahrenheit check box
        fahChk = new JCheckBox("Fahrenheit", !pref.tempUnits);
        layout.putConstraint(SpringLayout.WEST, fahChk, 200, 
                                                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, fahChk, 0, 
                                                SpringLayout.NORTH, celChk);
        panel.add(fahChk);
        
        /*Add action listeners to make sure only one of Celsius or Fahrenheit
        is selected at any one time */
        celChk.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                fahChk.setSelected(!celChk.isSelected());
            }    
        });
        fahChk.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                celChk.setSelected(!fahChk.isSelected());
            }    
        });
        
        //Confirm button
        JButton confirm = new JButton("Confirm");
        layout.putConstraint(SpringLayout.WEST, confirm, 115, 
                                                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, confirm, 40, 
                                                SpringLayout.NORTH, fahChk);
        panel.add(confirm);  
        
        //Add action to the button
        confirm.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                //Save the old preferences
                Preferences oldPref = user.pref;
                user.pref = getPref();
                
                //Try to save the new user preferences
                try{
                    user.saveUser();
                    updated = true;
                }
                //If we failed to save the new preferences default to previous
                catch(Exception e){
                    user.pref = oldPref;
                }
                
                dispose();
            }    
        });
        
        this.setVisible(true);
    }
    
    /**
     * Makes a new preference object from the values of the dialog's check boxes
     * @return a new preferences object that is populated from check boxes of 
     * the dialog
     */
    private static Preferences getPref(){
        Preferences result = new Preferences();
        result.humidity = humidityChk.isSelected();
        result.minMaxTemp = minMaxChk.isSelected();
        result.pressure = pressureChk.isSelected();
        result.sky = skyCondChk.isSelected();
        result.sun = sunChk.isSelected();
        result.temperature = tempChk.isSelected();
        result.wind = windChk.isSelected();
        result.tempUnits = celChk.isSelected();
        
        return result;
    }

    /**
     * Returns weather the settings were successfully updated by the dialog
     * @return True if the settings were updated, False if there was a problem
     * saving the settings
     */
    public boolean wasUpdated(){
        return updated;
    }
}