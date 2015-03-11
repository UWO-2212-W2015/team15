package team15.GUI;

/**
 *
 * @author team15
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.*;

import team15.Weather.Weather;

public class ForecastCard extends JPanel{
    private final SpringLayout layout;
    
    public ForecastCard(Weather w, boolean units){
        super();
        
        //Set the preferences of the panel
        this.setPreferredSize(new Dimension(250,200));
        this.setBackground(Color.WHITE);
        this.layout = new SpringLayout();
        this.setLayout(layout);
        
        //Set up the tempriture label
        JLabel tempLabel = new JLabel(w.getTemp(units));
        tempLabel.setFont(new Font("Tahoma",Font.PLAIN,60));
        this.add(tempLabel);
        layout.putConstraint(SpringLayout.WEST, tempLabel, 80, SpringLayout.WEST, this);
        
        //Set the icon label
        JLabel icon = new JLabel();
        icon.setIcon(w.icon);
        this.add(icon);
        layout.putConstraint(SpringLayout.WEST, icon, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, icon, 20, SpringLayout.NORTH, this);
        
        //Set the tempriture scale label
        JLabel scale = new JLabel(units?"c":"f");
        scale.setFont(new Font("Tahoma",Font.PLAIN,40));
        this.add(scale);
        layout.putConstraint(SpringLayout.WEST, scale, 5, SpringLayout.EAST, tempLabel);
        layout.putConstraint(SpringLayout.NORTH,scale, 5,SpringLayout.NORTH, this);
        
        //Set the sky condition label
        JLabel sky = new JLabel("Sky Condition :   " + w.skyCondition);
        this.add(sky);
        layout.putConstraint(SpringLayout.WEST, sky, 20, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH,sky,10,SpringLayout.SOUTH, tempLabel);
        
        if(w.getMinTemp(units).isEmpty()) return;
        
        //Set the min/max tempriture label
        JLabel maxt = new JLabel("Maximum Temperature: " + w.getMaxTemp(units));
        JLabel mint = new JLabel("Minmum Temperature: " + w.getMinTemp(units));
        this.add(maxt);
        this.add(mint);
        layout.putConstraint(SpringLayout.WEST, maxt, 20, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, maxt,10, SpringLayout.SOUTH, sky);
        layout.putConstraint(SpringLayout.WEST, mint, 20, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, mint,10, SpringLayout.SOUTH, maxt);
    }
}
