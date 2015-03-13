package team15.GUI;

/**
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

import team15.User.Preferences;
import team15.User.User;

public class PreferencesDialog{
    private static JCheckBox tempChk, iconChk, windChk, pressureChk, humidityChk, 
            minMaxChk, sunChk, skyCondChk, celChk, fahChk;
    
    private static boolean updated;
    
    public static void makeWindow(final User user){
        JButton confirm;
        final JDialog dialog;

        updated = false;
        
        Preferences pref = user.pref;
        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        panel.setLayout(layout);
        
        //Set up the dialog parameters
        dialog = new JDialog(new JFrame(), "Current Weather Preferences", true);
        dialog.setResizable(false);
        dialog.getContentPane().add(panel);
        dialog.setSize(350, 300);
        dialog.setLocation(200, 200);

        //Add all the checkboxes to the dialog
        tempChk = new JCheckBox("Temperature", pref.temperature);
        layout.putConstraint(SpringLayout.WEST, tempChk, 10, 
                                                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, tempChk, 
                                           10, SpringLayout.NORTH, panel);
        panel.add(tempChk);

        iconChk = new JCheckBox("Icon", pref.icon);
        layout.putConstraint(SpringLayout.WEST, iconChk, 10, 
                                                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, iconChk, 40, 
                                                   SpringLayout.NORTH, tempChk);
        panel.add(iconChk);

        windChk = new JCheckBox("Wind Speed/Direction", pref.wind);
        layout.putConstraint(SpringLayout.WEST, windChk, 10, 
                                                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, windChk, 40, 
                                                   SpringLayout.NORTH, iconChk);
        panel.add(windChk);

        pressureChk = new JCheckBox("Air Pressure", pref.pressure);
        layout.putConstraint(SpringLayout.WEST, pressureChk, 10, 
                                                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, pressureChk, 
                                               40, SpringLayout.NORTH, windChk);
        panel.add(pressureChk);

        celChk = new JCheckBox("Celsius", pref.tempUnits);
        layout.putConstraint(SpringLayout.WEST, celChk, 10, 
                                                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, celChk, 40, 
                                               SpringLayout.NORTH, pressureChk);
        panel.add(celChk);

        humidityChk = new JCheckBox("Humidity", pref.humidity);
        layout.putConstraint(SpringLayout.WEST, humidityChk, 200, 
                                                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, humidityChk, 10, 
                                               SpringLayout.NORTH, panel);
        panel.add(humidityChk);

        minMaxChk = new JCheckBox("Minimum/Maximum", pref.minMaxTemp);
        layout.putConstraint(SpringLayout.WEST, minMaxChk, 200, 
                                                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, minMaxChk, 40, 
                                               SpringLayout.NORTH, humidityChk);
        panel.add(minMaxChk);

        sunChk = new JCheckBox("Sunset/Sunrise", pref.sun);
        layout.putConstraint(SpringLayout.WEST, sunChk, 200, 
                                                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, sunChk, 40, 
                                                   SpringLayout.NORTH, minMaxChk);
        panel.add(sunChk);

        skyCondChk = new JCheckBox("Sky Condition", pref.sky);
        layout.putConstraint(SpringLayout.WEST, skyCondChk, 200, 
                                                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, skyCondChk, 40, 
                                                    SpringLayout.NORTH, sunChk);
        panel.add(skyCondChk);
        
        fahChk = new JCheckBox("Fahrenheit", !pref.tempUnits);
        layout.putConstraint(SpringLayout.WEST, fahChk, 200, 
                                                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, fahChk, 40, 
                                                SpringLayout.NORTH, skyCondChk);
        panel.add(fahChk);
        
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
        
        confirm = new JButton("Confirm");
        layout.putConstraint(SpringLayout.WEST, confirm, 115, 
                                                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, confirm, 40, 
                                                SpringLayout.NORTH, fahChk);
        panel.add(confirm);    
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
                
                dialog.dispose();
            }    
        });
        
        dialog.setVisible(true);
    }
    
    private static Preferences getPref(){
        Preferences result = new Preferences();
        result.humidity = humidityChk.isSelected();
        result.icon = iconChk.isSelected();
        result.minMaxTemp = minMaxChk.isSelected();
        result.pressure = pressureChk.isSelected();
        result.sky = skyCondChk.isSelected();
        result.sun = sunChk.isSelected();
        result.temperature = tempChk.isSelected();
        result.wind = windChk.isSelected();
        result.tempUnits = celChk.isSelected();
        
        return result;
    }
    
    public boolean prefUpdated(){
        return updated;
    }
}
