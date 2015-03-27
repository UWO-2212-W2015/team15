package team15.GUI;

/**
 * This class represents a panel that will be used to store and display the
 * local weather data
 * 
 * @author team15
 */

//Imports
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import team15.UserOjects.Preferences;
import team15.WeatherObjects.Weather;
import team15.WeatherObjects.LocationWeather;

public class LocalPanel extends JPanel{
    private final LocalPanelConfig LPC;
    private final Preferences pref;
    private final Weather w;
    private final String name;
    
    public LocalPanel(){
        super();
        LPC = new LocalPanelConfig();
        w = new Weather();
        pref = new Preferences();
        name = "";
    }
    
    /**
     * Creates a new panel that will display the weather located in a single
     * weather object representing a local forecast
     * @param locW
     * @param p
     */
    public LocalPanel(LocationWeather locW, Preferences p){
        super();
        
        this.LPC = new LocalPanelConfig();
        this.w = locW.getLocal();
        this.name = locW.toString();
        this.pref = p;
    
        //Set up panel parameters
        this.setBackground(LPC.BGCOLOR);
        
        //Set layout
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);
        
        //Header panel
        JPanel header = makeHeader();
        layout.putConstraint(SpringLayout.WEST, header, 34, SpringLayout.WEST, 
                this);
        layout.putConstraint(SpringLayout.NORTH, header, 17, SpringLayout.NORTH, 
                this);
        this.add(header);
        
        //Temperature panels
        JPanel temp = makeTemp();
        
        layout.putConstraint(SpringLayout.WEST, temp, 20, SpringLayout.WEST, header);
        layout.putConstraint(SpringLayout.NORTH, temp, 0, SpringLayout.SOUTH, header);
        this.add(temp);

        JPanel sec = makeSecondary();
        
        layout.putConstraint(SpringLayout.WEST, sec, 30, SpringLayout.EAST, temp);
        layout.putConstraint(SpringLayout.NORTH, sec, 10, SpringLayout.SOUTH, header);
        this.add(sec);
        
        layout.putConstraint(SpringLayout.EAST, this, 20, SpringLayout.EAST, 
                sec);
        layout.putConstraint(SpringLayout.SOUTH, this, 235, SpringLayout.NORTH, 
                this);
    }
    
    /**
     * Makes the panel that holds all the secondary weather data. Sunrise/sunset
     * humidity, pressure, and wind.
     * @return a JPanel with all the secondary weather data that is set visible
     */
    private JPanel makeSecondary(){
        JPanel result = new JPanel();
        JPanel prev, right;
        
        SpringLayout layout = new SpringLayout();
        
        right = null;
        
        result.setLayout(layout);
        result.setBackground(LPC.BGCOLOR);
        
        String sunr = w.getSunrise();
        String suns = w.getSunset();
        String hum = w.getHumidity();
        String pre = w.getPressure();
        String wind = w.getWind();
        int offset = LPC.SECONDARYOFFSET;
        int size = LPC.SECONDARYFONT;
        int type = LPC.FONTBOLD;
        int space = LPC.SECONDARYSPACE;
        
        JPanel pnlSunr = makeValueLabel("SUNRISE:", sunr, offset, size, type);
        JPanel pnlSuns = makeValueLabel("SUNSET:", suns, offset, size, type);
        JPanel pnlHum = makeValueLabel("HUMIDITY:", hum, offset, size, type);
        JPanel pnlPre = makeValueLabel("PRESSURE:", pre, offset, size, type);
        JPanel pnlWind = makeValueLabel("WIND:", wind, offset, size, type);
        
        prev = result;
        
        JPanel[] panels = {pnlSunr, pnlSuns, pnlHum, pnlPre, pnlWind};
        boolean[] visible = {pref.sun, pref.sun, pref.humidity, pref.pressure, 
            pref.wind};
        
        int i = 0;
        for(int j = 0; j < visible.length; j++){
            if(visible[j]){
                int n = (i%2);
                int x = (n==1)?space:0;
                int y = (n==1)?0:10;
                String h = (n==1)?SpringLayout.EAST:SpringLayout.WEST;
                String v = ((n==1)||(prev == result))
                        ?SpringLayout.NORTH:SpringLayout.SOUTH;
                
                layout.putConstraint(SpringLayout.WEST, panels[j], x, h, prev);
                layout.putConstraint(SpringLayout.NORTH, panels[j], y, v, prev);
                
                result.add(panels[j]);
                
                if(n==0) prev = panels[j];
                if((i==0)||(n==1)) right = panels[j];
                
                i++;
            }
        }
               
        if(right != null){
            layout.putConstraint(SpringLayout.EAST, result, 0, 
                    SpringLayout.EAST, right);
            layout.putConstraint(SpringLayout.SOUTH, result, 0, 
                    SpringLayout.SOUTH, prev);
        }
        return result;
    }
    
    /**
     * Makes the panel that contains the temperatures and sky condition.
     * @return the panel that contains the temperatures and sky condition.
     */
    private JPanel makeTemp(){
        JPanel result = new JPanel();
        SpringLayout layout = new SpringLayout();
        
        result.setLayout(layout);
        result.setBackground(LPC.BGCOLOR);
        
        JPanel left = null;
        JPanel right = null;
        int lh = 0;
        int rh = 0;
        if(pref.temperature){
            left = new JPanel();
            left.add(makeLabel(w.getTemp(pref.tempUnits), LPC.TEMPFONT, LPC.FONTPLAIN));
            left.setBackground(LPC.BGCOLOR);
            
            if(pref.sky) right = makeSkyCondition();
            else if(pref.minMaxTemp){
                right = largeMinMax();
                rh = 10;
            }
        }
        else if(pref.minMaxTemp){
            left = largeMinMax();
            lh = 20;
            if(pref.sky) right = makeSkyCondition();
        }
        else if(pref.sky) left = makeSkyCondition();
        
        if(left == null) return result;

        layout.putConstraint(SpringLayout.WEST, left, 0, SpringLayout.WEST, result);
        layout.putConstraint(SpringLayout.NORTH, left, lh, SpringLayout.NORTH, result);
        result.add(left);
        
        if(right == null){
            layout.putConstraint(SpringLayout.SOUTH, result, 0, SpringLayout.SOUTH, left);
            layout.putConstraint(SpringLayout.EAST, result, 0, SpringLayout.EAST, left);
            
            return result;
        }
        
        layout.putConstraint(SpringLayout.WEST, right, 20, SpringLayout.EAST, left);
        layout.putConstraint(SpringLayout.NORTH, right, rh, SpringLayout.NORTH, result);
        layout.putConstraint(SpringLayout.EAST, result, 0, SpringLayout.EAST, right);
        result.add(right);

        if((pref.temperature)&&(pref.sky)&&(pref.minMaxTemp)){
            JPanel mm = smallMinMax();
            
            layout.putConstraint(SpringLayout.WEST, mm, 20, SpringLayout.WEST, result);
            layout.putConstraint(SpringLayout.NORTH, mm, 0, SpringLayout.SOUTH, left);
            layout.putConstraint(SpringLayout.SOUTH, result, 0, SpringLayout.SOUTH, mm);
            result.add(mm);
        }
        else layout.putConstraint(SpringLayout.SOUTH, result, 0, SpringLayout.SOUTH, left);

        return result;
    }
    
    private JPanel largeMinMax(){
        JPanel result = new JPanel();
        SpringLayout layout = new SpringLayout();
        
        result.setLayout(layout);
        result.setBackground(LPC.BGCOLOR);
        
        String min = w.getMinTemp(pref.tempUnits);
        String max = w.getMaxTemp(pref.tempUnits);
        int offset = LPC.MINMAXOFFSET+110;
        int size = LPC.MINMAXFONT+24;
        int type = LPC.FONTPLAIN;
        JPanel lblMin = makeValueLabel("Low:", min, offset, size, type);
        JPanel lblMax = makeValueLabel("High:", max, offset, size, type);
        
        result.add(lblMax);
        result.add(lblMin);
        layout.putConstraint(SpringLayout.WEST, lblMax, 0, SpringLayout.WEST, 
                lblMin);
        layout.putConstraint(SpringLayout.NORTH, lblMax, 5, SpringLayout.SOUTH, 
                lblMin);
        layout.putConstraint(SpringLayout.EAST, result, 0, SpringLayout.EAST, 
                lblMax);
        layout.putConstraint(SpringLayout.SOUTH, result, 0, SpringLayout.SOUTH, 
                lblMax);
        
        return result;
    }
    
    /**
     * Makes the Panel that displays the min and max temperatures
     * @return the panel that displays the min and max temperature
     */
    private JPanel smallMinMax(){
        JPanel result = new JPanel();
        SpringLayout layout = new SpringLayout();
        
        result.setLayout(layout);
        result.setBackground(LPC.BGCOLOR);
        
        String min = w.getMinTemp(pref.tempUnits);
        String max = w.getMaxTemp(pref.tempUnits);
        int offset = LPC.MINMAXOFFSET;
        int size = LPC.MINMAXFONT;
        int type = LPC.FONTBOLD;
        JPanel lblMin = makeValueLabel("LOW:", min, offset, size, type);
        JPanel lblMax = makeValueLabel("HIGH:", max, offset, size, type);
        
        result.add(lblMax);
        result.add(lblMin);
        layout.putConstraint(SpringLayout.WEST, lblMax, 30, SpringLayout.EAST, 
                lblMin);
        layout.putConstraint(SpringLayout.NORTH, lblMax, 0, SpringLayout.NORTH, 
                lblMin);
        layout.putConstraint(SpringLayout.EAST, result, 0, SpringLayout.EAST, 
                lblMax);
        layout.putConstraint(SpringLayout.SOUTH, result, 0, SpringLayout.SOUTH, 
                lblMax);
        
        return result;
    }
    
    /**
     * Makes a panel that contains the sky condition description and icon
     * @param icon the sky condition icon
     * @param condition the sky condition description
     * @return the panel containing the sky condition information
     */
    private JPanel makeSkyCondition(){
        JPanel result = new JPanel();
        SpringLayout layout = new SpringLayout();
        
        result.setLayout(layout);
        result.setBackground(LPC.BGCOLOR);
        
        //Set icon
        Image img = w.icon.getImage();
        int size = LPC.ICONSIZE;
        img = img.getScaledInstance(size, size, Image.SCALE_SMOOTH);
        JLabel lblIcon = new JLabel(new ImageIcon(img));
        result.add(lblIcon);
        
        //Set condition
        JLabel con = makeLabel(w.getCondition(), LPC.CONFONT, LPC.FONTBOLD);
        con.setHorizontalAlignment(SwingConstants.CENTER);
        layout.putConstraint(SpringLayout.WEST, con, 14, SpringLayout.WEST, 
                result);
        layout.putConstraint(SpringLayout.NORTH, con, -25, SpringLayout.SOUTH, 
                lblIcon);
        result.add(con);
        
        layout.putConstraint(SpringLayout.EAST, result, 0, SpringLayout.EAST, 
                lblIcon);
        layout.putConstraint(SpringLayout.SOUTH, result, 0, SpringLayout.SOUTH, 
                con);
        
        return result;
    }
    
    /**
     * Makes the header panel that contains the location name and the date
     * @param location the locations name
     * @param time the date of the weather object
     * @return the formated header that displays the location name and the date
     * of the weather forecast
     */
    private JPanel makeHeader(){
        int font = LPC.FONTPLAIN;
        
        JPanel result = new JPanel();
        SpringLayout layout = new SpringLayout();
        
        result.setLayout(layout);
        result.setBackground(LPC.BGCOLOR);
        
        //Location label
        JLabel loc = makeLabel(this.name, LPC.LOCATIONFONT, font);
        result.add(loc);
        
        //Time label
        JLabel date = makeLabel(w.getMonthDay(), LPC.TIMEFONT, font);
        layout.putConstraint(SpringLayout.WEST, date, 5, SpringLayout.WEST, 
                result);
        layout.putConstraint(SpringLayout.NORTH, date, 0, SpringLayout.SOUTH, 
                loc);
        result.add(date);
        
        layout.putConstraint(SpringLayout.EAST, result, 0, SpringLayout.EAST, 
                loc);
        layout.putConstraint(SpringLayout.SOUTH, result, 0, SpringLayout.SOUTH, 
                date);
        
        return result;
    }
    
    /**
     * Makes a new JLabel with the specified font, with white text of the given
     * size
     * @param size the size of the font
     * @return a JLabel with Tahoma font, of the given size, and white text
     */
    private JLabel makeLabel(String lbl, int size, int type){
        JLabel result = new JLabel(lbl);
        result.setFont(new Font(LPC.FONTNAME, type, size));
        result.setForeground(Color.WHITE);
        return result;
    }
    
    /**
     * Makes a new Jpanel that has two aligned Jlabels with width offset
     * @param label The label of the value
     * @param value the value to be displayed
     * @param offset the total size of the JPanel
     * @param size the size of the font
     * @param type the type of the font: Bold, Plain, etc
     * @return A JPanel that has the two strings in in the proper font of the
     * specified size
     */
    private JPanel makeValueLabel(String label, String value, int offset, 
            int size, int type){
        JPanel result = new JPanel();
        JLabel l = makeLabel(label, size, type);
        JLabel v = makeLabel(value, size, type);
        SpringLayout layout = new SpringLayout();
        
        result.setBackground(LPC.BGCOLOR);
        result.setLayout(layout);
        result.add(l);
        
        layout.putConstraint(SpringLayout.EAST, v, offset, SpringLayout.WEST, 
                l);
        layout.putConstraint(SpringLayout.NORTH, v, 0, SpringLayout.NORTH, l);
        result.add(v);
        
        layout.putConstraint(SpringLayout.EAST, result, 0, SpringLayout.EAST, 
                v);
        layout.putConstraint(SpringLayout.SOUTH, result, 0, SpringLayout.SOUTH, 
                v);
        
        return result;
    }
}
