package team15;

/**
 * This class represents a Jpanel that will be used to store and display the
 * local weather data
 * 
 * @author team15
 */

//Imports
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class LocalPanel extends JPanel{
    private final JLabel loc, temp, tempScale, airP, hum, sky, minT, maxT, 
                               windS, windD, sunR, sunS, icon, error, ref, time;
    
    public LocalPanel(){
        super();
        
        loc = new JLabel();
        temp = new JLabel();
        tempScale = new JLabel();
        airP = new JLabel();
        hum = new JLabel();
        sky = new JLabel();
        minT = new JLabel();
        maxT = new JLabel();
        windS = new JLabel();
        windD = new JLabel();
        sunR = new JLabel();
        sunS = new JLabel();
        icon = new JLabel();
        error = new JLabel();
        ref = new JLabel();
        time = new JLabel();
        
        //Set layout
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        //Location label
        loc.setBounds(100,100,150,20);
        loc.setFont(new Font("Tahoma", Font.PLAIN, 30));
        layout.putConstraint(SpringLayout.WEST, loc, 20, SpringLayout.WEST, this);
        this.add(loc);
        
        //Error label
        error.setFont(new Font("Tahoma", Font.PLAIN, 20));
        layout.putConstraint(SpringLayout.WEST, error, 20, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.SOUTH, error, -20, SpringLayout.SOUTH, this);
        this.add(error);
        
        //Refresh Label
        ref.setFont(new Font("Tahoma", Font.PLAIN, 20));
        layout.putConstraint(SpringLayout.EAST, ref, -20, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.SOUTH, ref, -20, SpringLayout.SOUTH, this);
        this.add(ref);
        
        //Time label
        time.setFont(new Font("Tahoma", Font.PLAIN, 30));
        layout.putConstraint(SpringLayout.WEST, time, 20, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, time, 11, SpringLayout.SOUTH, loc);
        this.add(time);
        
        //Temperature label
        temp.setFont(new Font("Tahoma", Font.PLAIN, 90));
        this.add(temp);
        layout.putConstraint(SpringLayout.WEST, temp, 120, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, temp, 11, SpringLayout.SOUTH, time);

        tempScale.setFont(new Font("Tahoma", Font.PLAIN, 60));
        this.add(this.tempScale);
        layout.putConstraint(SpringLayout.WEST, tempScale, 5, SpringLayout.EAST, temp);
        layout.putConstraint(SpringLayout.NORTH, tempScale, 16, SpringLayout.SOUTH, time);

        //Air pressure label
        airP.setFont(new Font("Tahoma", Font.PLAIN, 30));
        layout.putConstraint(SpringLayout.WEST, airP, 30, SpringLayout.EAST, tempScale);
        layout.putConstraint(SpringLayout.NORTH, airP, 80, SpringLayout.NORTH, this);
        this.add(airP);

        //Humidity Label
        hum.setFont(new Font("Tahoma", Font.PLAIN, 30));
        layout.putConstraint(SpringLayout.WEST, hum, 30, SpringLayout.EAST, tempScale);
        layout.putConstraint(SpringLayout.NORTH, hum, 10, SpringLayout.SOUTH, airP);
        this.add(hum);

        //skyCondition Label
        sky.setFont(new Font("Tahoma", Font.PLAIN, 30));
        layout.putConstraint(SpringLayout.WEST, sky, 30, SpringLayout.EAST, tempScale);
        layout.putConstraint(SpringLayout.NORTH, sky, 10, SpringLayout.SOUTH, hum);
        this.add(sky);

        //Expected Minimum Label
        minT.setFont(new Font("Tahoma", Font.PLAIN, 30));
        layout.putConstraint(SpringLayout.WEST, minT, 30, SpringLayout.EAST, tempScale);
        layout.putConstraint(SpringLayout.NORTH, minT, 10, SpringLayout.SOUTH, sky);
        this.add(minT);

        //Expected Maxmimum Label
        maxT.setFont(new Font("Tahoma", Font.PLAIN, 30));
        layout.putConstraint(SpringLayout.WEST, maxT, 30, SpringLayout.EAST, tempScale);
        layout.putConstraint(SpringLayout.NORTH, maxT, 10, SpringLayout.SOUTH, minT);
        this.add(maxT);

        //Wind Speed Label
        windS.setFont(new Font("Tahoma", Font.PLAIN, 30));
        layout.putConstraint(SpringLayout.WEST, windS, 30, SpringLayout.EAST, tempScale);
        layout.putConstraint(SpringLayout.NORTH, windS, 10, SpringLayout.SOUTH, maxT);
        this.add(windS);

        //Wind Direction Label
        windD.setFont(new Font("Tahoma", Font.PLAIN, 30));
        layout.putConstraint(SpringLayout.WEST, windD, 30, SpringLayout.EAST, tempScale);
        layout.putConstraint(SpringLayout.NORTH, windD, 10, SpringLayout.SOUTH, windS);
        this.add(windD);

        //Sunrise Label
        sunR.setFont(new Font("Tahoma", Font.PLAIN, 30));
        layout.putConstraint(SpringLayout.WEST, sunR, 30, SpringLayout.EAST, tempScale);
        layout.putConstraint(SpringLayout.NORTH, sunR, 10, SpringLayout.SOUTH, windD);
        this.add(sunR);

        //Sunset Label
        sunS.setFont(new Font("Tahoma", Font.PLAIN, 30));
        layout.putConstraint(SpringLayout.WEST, sunS, 30, SpringLayout.EAST, tempScale);
        layout.putConstraint(SpringLayout.NORTH, sunS, 10, SpringLayout.SOUTH, sunR);
        this.add(sunS);

        //Icon Label
        layout.putConstraint(SpringLayout.WEST, icon, 30, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, icon, 60, SpringLayout.NORTH, time);
        this.add(icon);
    }
    
    public void update(Weather w, Preferences pref, String location, String refresh){
        String u = pref.tempUnits?"c":"f"; //Tempriture system label
        
        temp.setText(pref.temperature?w.getTemp(pref.tempUnits):"");
        tempScale.setText(pref.temperature?u:"");
        airP.setText(pref.pressure?"Air Pressure: " + w.airPressure:"");
        hum.setText(pref.humidity?"Humidity: " + w.humidity:"");
        sky.setText(pref.sky?"Condition: " + w.skyCondition:"");
        minT.setText(pref.minMaxTemp?"Minimum Temperature: " 
                                         + w.getMinTemp(pref.tempUnits) + u:"");
        maxT.setText(pref.minMaxTemp?"Maximum Temperature: " 
                                         + w.getMaxTemp(pref.tempUnits) + u:"");    
        windS.setText(pref.wind?"WindSpeed: " + w.windSpeed:"");
        windD.setText(pref.wind?"Wind Direction: " + w.windDirection 
                                                               + " degrees":"");
        sunR.setText(pref.sun?"Sunrise: " + w.sunrise:"");
        sunS.setText(pref.sun?"Sunset: " + w.sunset:"");
        icon.setIcon(pref.icon?w.icon:new ImageIcon());
        time.setText(w.time);
        loc.setText("Location:   " + location);
        ref.setText("Last Refresh: " + refresh);
    }
    
    public void setError(String err){
        error.setText(err);
    }
}
