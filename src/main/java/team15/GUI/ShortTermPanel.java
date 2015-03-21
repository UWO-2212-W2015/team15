package team15.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import team15.WeatherObjects.Forecast;
import team15.WeatherObjects.Weather;

/**
 *
 * @author vsippola
 */
public class ShortTermPanel extends JPanel{
    private final boolean units;
    private final String fn = "Tahoma";
    private final Color txtC = new Color(1, 61, 134);
    private final Color bgC = new Color(210, 229, 243);
    private final int NUM = 8;
    
    public ShortTermPanel(){
        super();
        units = true;
    }
    
    public ShortTermPanel(Forecast f, boolean u){
        super();
        units = u;
        
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);
        
        this.setBackground(bgC);
        JPanel[] cards = new JPanel[NUM];
        
        for(int i = 0; i < NUM; i++){
            cards[i] = makeCard(f.get(i));
            this.add(cards[i]);
        }
        
        for(int i = 1; i < NUM; i++){
            layout.putConstraint(SpringLayout.WEST, cards[i], 85, SpringLayout.WEST, cards[i-1]);
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
        
        int fSize = 10;
        int type = Font.PLAIN;
        
        JLabel date = makeLabel(w.getDayTime(), 13, Font.BOLD);
        layout.putConstraint(SpringLayout.WEST, date, 0, SpringLayout.WEST, result);
        layout.putConstraint(SpringLayout.NORTH, date, 0, SpringLayout.NORTH, result);
        layout.putConstraint(SpringLayout.EAST, result, 65, SpringLayout.WEST, date);
        result.add(date);
        
        JLabel temp = makeLabel(w.getTemp(this.units), 15, type);
        layout.putConstraint(SpringLayout.WEST, temp, 0, SpringLayout.WEST, result);
        layout.putConstraint(SpringLayout.NORTH, temp, 3, SpringLayout.SOUTH, date);
        result.add(temp);
        
        Image img = w.icon.getImage();
        img = img.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        JLabel icon = new JLabel(new ImageIcon(img));
        layout.putConstraint(SpringLayout.EAST, icon, 0, SpringLayout.EAST, result);
        layout.putConstraint(SpringLayout.NORTH, icon, 0, SpringLayout.SOUTH, date);
        result.add(icon);
        
        JLabel sky = makeLabel(w.getCondition(), fSize, type);
        layout.putConstraint(SpringLayout.WEST, sky, 0, SpringLayout.WEST, result);
        layout.putConstraint(SpringLayout.NORTH, sky, 0, SpringLayout.SOUTH, icon);
        result.add(sky);
        
        layout.putConstraint(SpringLayout.SOUTH, result, 0, SpringLayout.SOUTH, sky);
        
        return result;
    }
    
    private JLabel makeLabel(String s, int size, int type){
        JLabel result = new JLabel(s);
        result.setFont(new Font(fn, type, size));
        result.setForeground(this.txtC);
        return result;
    }
}
