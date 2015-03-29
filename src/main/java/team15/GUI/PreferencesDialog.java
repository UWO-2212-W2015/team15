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
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import team15.UserOjects.Preferences;
import team15.UserOjects.User;
import java.awt.Color;

public class PreferencesDialog extends JDialog{
    //Check boxes
    private static JCheckBox tempChk, windChk, pressureChk, 
	humidityChk, minMaxChk, sunChk, skyCondChk, celChk, fahChk;
    
    //A field to track if the dialog successfully updates the settings
    private static boolean updated;
    
    //Colour
    public final Color BGCOLOR = new Color(210, 229, 243); 
    private final Color txtC = new Color(1, 61, 134);
    
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
	JPanel root = new JPanel();
	root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        panel.setLayout(layout);
	panel.setBorder(BorderFactory.createTitledBorder("Current Weather Show/Hide Options"));
	panel.setBackground(BGCOLOR);

        //Set up the dialog parameters
        this.setResizable(false);
        this.setSize(400, 350);
        this.setLocation(200, 200);

        //Add all the checkboxes to the dialog
        //Tempriture check box
        tempChk = new JCheckBox("Temperature", pref.temperature);
        tempChk.setForeground(txtC);
        tempChk.setBackground(BGCOLOR);
        tempChk.setToolTipText("Check or uncheck this box to "
			       + "show/hide the temperature");
        layout.putConstraint(SpringLayout.WEST, tempChk, 10, 
			     SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, tempChk, 
			     10, SpringLayout.NORTH, panel);
        panel.add(tempChk);

        //Wind check box
        windChk = new JCheckBox("Wind Speed/Direction", pref.wind);
        windChk.setForeground(txtC);
        windChk.setBackground(BGCOLOR);
        windChk.setToolTipText("Check or uncheck this box to show/hide "
			       + "the wind speed and direction");
        layout.putConstraint(SpringLayout.WEST, windChk, 10, 
			     SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, windChk, 40, 
			     SpringLayout.NORTH, tempChk);
        panel.add(windChk);
        
        //Pressure check box
        pressureChk = new JCheckBox("Air Pressure", pref.pressure);
        pressureChk.setForeground(txtC);
        pressureChk.setBackground(BGCOLOR);
        pressureChk.setToolTipText("Check or uncheck this box to "
				   + "show/hide the pressure");
        layout.putConstraint(SpringLayout.WEST, pressureChk, 10, 
			     SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, pressureChk, 
			     40, SpringLayout.NORTH, windChk);
        panel.add(pressureChk);

        //Sky condition check box
        skyCondChk = new JCheckBox("Sky Condition", pref.sky);
        skyCondChk.setForeground(txtC);
        skyCondChk.setBackground(BGCOLOR);
        skyCondChk.setToolTipText("Check or uncheck this box to "
				  + "show/hide the sky condition");
        layout.putConstraint(SpringLayout.WEST, skyCondChk, 10, 
			     SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, skyCondChk, 40, 
			     SpringLayout.NORTH, pressureChk);
        panel.add(skyCondChk);
        

        //Humidty check box
        humidityChk = new JCheckBox("Humidity", pref.humidity);
        humidityChk.setForeground(txtC);
        humidityChk.setBackground(BGCOLOR);
        humidityChk.setToolTipText("Check or uncheck this box to "
				   + "show/hide the humidity");
        layout.putConstraint(SpringLayout.WEST, humidityChk, 250, 
			     SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, humidityChk, 10, 
			     SpringLayout.NORTH, panel);
        panel.add(humidityChk);

        //Min/max temperature check box
        minMaxChk = new JCheckBox("Min/Max", pref.minMaxTemp);
        minMaxChk.setForeground(txtC);
        minMaxChk.setBackground(BGCOLOR);
        minMaxChk.setToolTipText("Check or uncheck this box to show/hide "
				 + "the minimum/maximum temperatures of the day");
        layout.putConstraint(SpringLayout.WEST, minMaxChk, 250, 
			     SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, minMaxChk, 40, 
			     SpringLayout.NORTH, humidityChk);
        panel.add(minMaxChk);

        //Sunrise/sunset check box
        sunChk = new JCheckBox("Sunset/Sunrise", pref.sun);
        sunChk.setForeground(txtC);
        sunChk.setBackground(BGCOLOR);
        sunChk.setToolTipText("Check or uncheck this box to show/hide "
			      + "the sunset/sunrise times of the day");
        layout.putConstraint(SpringLayout.WEST, sunChk, 250, 
			     SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, sunChk, 40, 
			     SpringLayout.NORTH, minMaxChk);
	layout.putConstraint(SpringLayout.SOUTH, panel, 10, 
			     SpringLayout.SOUTH, sunChk);
	layout.putConstraint(SpringLayout.EAST, panel, 10, 
			     SpringLayout.EAST, sunChk);
        panel.add(sunChk); 
	root.add(panel);
	
	//Create new panel for temperature options
	panel = new JPanel();
	panel.setLayout(layout);
	panel.setBorder(BorderFactory.createTitledBorder("Select Temperature Units"));
	panel.setBackground(BGCOLOR);
	root.add(panel);
        //Celsius check box
        celChk = new JCheckBox("Celsius", pref.tempUnits);
        celChk.setForeground(txtC);
        celChk.setBackground(BGCOLOR);
        celChk.setToolTipText("Check this box to convert the "
			      + "temperature numbers to celsius");
        layout.putConstraint(SpringLayout.WEST, celChk, 10, 
			     SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, celChk, 10, 
			     SpringLayout.NORTH, panel);
        panel.add(celChk);

        //Fahrenheit check box
        fahChk = new JCheckBox("Fahrenheit", !pref.tempUnits);
        fahChk.setForeground(txtC);
        fahChk.setBackground(BGCOLOR);
        fahChk.setToolTipText("Check this box to convert the "
			      + "temperature numbers to fahrenheit");
        layout.putConstraint(SpringLayout.WEST, fahChk, 250, 
			     SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, fahChk, 10, 
			     SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.EAST, panel, 10, 
			     SpringLayout.EAST, sunChk);
        panel.add(fahChk);
        

         //Confirm button
        JButton confirm = new JButton("Ok");
        confirm.setToolTipText("Click here to save your preferences.");
        layout.putConstraint(SpringLayout.WEST, confirm, 230, 
			     SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, confirm, 40, 
			     SpringLayout.NORTH, fahChk);
        layout.putConstraint(SpringLayout.SOUTH, panel, 10, 
			     SpringLayout.SOUTH, confirm);
        panel.add(confirm); 

        //Cancel button
        JButton cancel = new JButton("Cancel");
        cancel.setToolTipText("Click here to cancel.");
        layout.putConstraint(SpringLayout.WEST, cancel, 300, 
			     SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, cancel, 40, 
			     SpringLayout.NORTH, fahChk);
        layout.putConstraint(SpringLayout.SOUTH, panel, 10, 
			     SpringLayout.SOUTH, cancel);
        panel.add(cancel); 
        
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
        
        //Add action to the Ok button
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
        
         //Add action to the Cancel button
            cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				System.out.println("hello");   
				dispose();
			}    
	    });

	this.add(root);
	this.pack();
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
