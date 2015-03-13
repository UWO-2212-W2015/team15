package team15;

/**
 * A class the creates a single jpanel that will hold the data for a short term
 * or a long term forecast.
 * 
 * @author team15
 */

//Imports
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.*;

public class ForecastCard extends JPanel{
    private final SpringLayout layout;
    private final JLabel time, temp, icon, scale, maxt, mint, sky;
    
    /**
     * Makes a new forecast card JPanel the holds the text fields that will
     * display the data contained in a weather forecast
     */
    public ForecastCard(){
        super();
        
        time = new JLabel();
        temp = new JLabel();
        icon = new JLabel();
        scale = new JLabel();
        maxt = new JLabel();
        mint = new JLabel();
        sky = new JLabel();
        
        //Set the preferences of the panel
        this.setPreferredSize(new Dimension(250,200));
        this.setBackground(Color.WHITE);
        this.layout = new SpringLayout();
        this.setLayout(layout);
                
        this.add(time);
        layout.putConstraint
                         (SpringLayout.WEST, time, 20, SpringLayout.WEST, this);
        
        //Set up the tempriture label
        temp.setFont(new Font("Tahoma",Font.PLAIN,50));
        this.add(temp);
        layout.putConstraint
                         (SpringLayout.WEST, temp, 80, SpringLayout.WEST, this);
        layout.putConstraint
                        (SpringLayout.NORTH, temp,10, SpringLayout.NORTH, this);
        
        //Set the icon label
        this.add(icon);
        layout.putConstraint
                         (SpringLayout.WEST, icon, 10, SpringLayout.WEST, this);
        layout.putConstraint
                       (SpringLayout.NORTH, icon, 20, SpringLayout.NORTH, this);
        
        //Set the tempriture scale label
        scale.setFont(new Font("Tahoma",Font.PLAIN,40));
        this.add(scale);
        layout.putConstraint
                         (SpringLayout.WEST, scale, 5, SpringLayout.EAST, temp);
        layout.putConstraint
                         (SpringLayout.NORTH,scale, 5,SpringLayout.NORTH, this);
        
        //Set the sky condition label
        this.add(sky);
        layout.putConstraint
                          (SpringLayout.WEST, sky, 20, SpringLayout.WEST, this);
        layout.putConstraint
                           (SpringLayout.NORTH,sky,10,SpringLayout.SOUTH, temp);
        
        //Set the min/max tempriture label
        this.add(maxt);
        this.add(mint);
        layout.putConstraint
                         (SpringLayout.WEST, maxt, 20, SpringLayout.WEST, this);
        layout.putConstraint
                         (SpringLayout.NORTH, maxt,10, SpringLayout.SOUTH, sky);
        layout.putConstraint
                         (SpringLayout.WEST, mint, 20, SpringLayout.WEST, this);
        layout.putConstraint
                        (SpringLayout.NORTH, mint,10, SpringLayout.SOUTH, maxt);
    }
    
    /**
     * Updates the text fields in this card with the data contained in the 
     * given weather object
     * @param w the weather object from which the labels and icons will be
     * populated
     * @param units the units that the temperatures will be displayed in. True
     * is Celsius and false is Fahrenheit
     */
    public void update(Weather w, boolean units){
        time.setText(w.time);
        temp.setText(w.getTemp(units));
        icon.setIcon(w.icon);
        scale.setText(units?"c":"f");
        sky.setText("Sky Condition :   " + w.skyCondition);
        
        if(Double.valueOf(w.getMinTemp(units)) < -273) return;
        
        maxt.setText("Maximum Temperature: " + w.getMaxTemp(units));
        mint.setText("Minmum Temperature: " + w.getMinTemp(units));
    }
}
