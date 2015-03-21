/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team15.GUI;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import team15.WeatherObjects.Forecast;
import team15.WeatherObjects.Weather;

/**
 *
 * @author vsippola
 */
public class LongTermPanel extends JPanel{
    private final boolean units;
    private final String fn = "Tahoma";
    private final Color txtC = new Color(1, 61, 134);
    private final Color bgC = new Color(210, 229, 243);
    private final int NUM = 5;
    
    public LongTermPanel(){
        super();
        units = true;
    }
    
    public LongTermPanel(Forecast f, boolean u){
        super();
        units = u;
        
        JPanel prev = this;
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);
        int width = 32;
        
        this.setBackground(bgC);
        JPanel[] cards = new JPanel[NUM];
        
        for(int i = 0; i < NUM; i++){
            cards[i] = makeCard(f.get(i));
            this.add(cards[i]);
        }
        
        for(int i = 1; i < NUM; i++){
            layout.putConstraint(SpringLayout.WEST, cards[i], 143, SpringLayout.WEST, cards[i-1]);
            layout.putConstraint(SpringLayout.NORTH, cards[i], 0, SpringLayout.NORTH, cards[i-1]);
            
        }
        
        layout.putConstraint(SpringLayout.EAST, this, 0, SpringLayout.EAST, cards[NUM - 1]);
        layout.putConstraint(SpringLayout.SOUTH, this, 0, SpringLayout.SOUTH, cards[0]);
    }
    
    private JPanel makeCard(Weather w){
        JPanel result = new JPanel();
        SpringLayout layout = new SpringLayout();
        result.setBackground(bgC);
        result.setLayout(layout);
        
        int fSize = 12;
        int type = Font.PLAIN;
        
        JLabel date = makeLabel(w.getDate(), fSize+3, Font.BOLD);
        layout.putConstraint(SpringLayout.WEST, date, 15, SpringLayout.WEST, result);
        layout.putConstraint(SpringLayout.NORTH, date, 0, SpringLayout.NORTH, result);
        layout.putConstraint(SpringLayout.EAST, result, 75, SpringLayout.WEST, date);
        result.add(date);
        
        JLabel temp = makeLabel(w.getTemp(this.units), 20, type);
        layout.putConstraint(SpringLayout.EAST, temp, 50, SpringLayout.WEST, result);
        layout.putConstraint(SpringLayout.NORTH, temp, 13, SpringLayout.SOUTH, date);
        result.add(temp);
        
        JLabel icon = new JLabel(w.icon);
        layout.putConstraint(SpringLayout.WEST, icon, 0, SpringLayout.EAST, temp);
        layout.putConstraint(SpringLayout.NORTH, icon, 0, SpringLayout.SOUTH, date);
        result.add(icon);
        
        JLabel sky = makeLabel(w.getCondition(), fSize, type);
        layout.putConstraint(SpringLayout.WEST, sky, 10, SpringLayout.WEST, result);
        layout.putConstraint(SpringLayout.NORTH, sky, 0, SpringLayout.SOUTH, icon);
        result.add(sky);
        
        JLabel lblL = makeLabel("Low:", fSize, type);
        layout.putConstraint(SpringLayout.WEST, lblL, 0, SpringLayout.WEST, sky);
        layout.putConstraint(SpringLayout.NORTH, lblL, 5, SpringLayout.SOUTH, sky);
        result.add(lblL);
        
        JLabel low = makeLabel(w.getMinTemp(units), fSize, type);
        layout.putConstraint(SpringLayout.EAST, low, 75, SpringLayout.WEST, lblL);
        layout.putConstraint(SpringLayout.NORTH, low, 0, SpringLayout.NORTH, lblL);
        result.add(low);
        
        JLabel lblH = makeLabel("High:", fSize, type);
        layout.putConstraint(SpringLayout.WEST, lblH, 0, SpringLayout.WEST, sky);
        layout.putConstraint(SpringLayout.NORTH, lblH, 5, SpringLayout.SOUTH, lblL);
        result.add(lblH);
        
        JLabel high = makeLabel(w.getMaxTemp(units), fSize, type);
        layout.putConstraint(SpringLayout.EAST, high, 0, SpringLayout.EAST, low);
        layout.putConstraint(SpringLayout.NORTH, high, 0, SpringLayout.NORTH, lblH);
        result.add(high);
        
        layout.putConstraint(SpringLayout.SOUTH, result, 0, SpringLayout.SOUTH, lblH);
        
        return result;
    }
    
    private JLabel makeLabel(String s, int size, int type){
        JLabel result = new JLabel(s);
        result.setFont(new Font(fn, type, size));
        result.setForeground(this.txtC);
        return result;
    }
}
