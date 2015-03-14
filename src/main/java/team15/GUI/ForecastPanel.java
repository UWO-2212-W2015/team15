package team15.GUI;

/**
 * This class represents a Jpanel that will be used to store and display the
 * weather data contained in a short or long term forecast
 * 
 * @author team15
 */

//Imports
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import team15.WeatherObjects.Forecast;

public class ForecastPanel extends JPanel{
    //Label variables
    private final JLabel loc, error, ref;
    
    //Cards - Small panels
    private final ArrayList<ForecastCard> cards;
    
    /**
     * Creates a new Panel that will contain all the data in a given short or
     * long term forecast.
     */
    public ForecastPanel(){
        super();
        
        //Create the layout
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);
        
        //Initialize the labels
        loc = new JLabel();
        error = new JLabel();
        ref = new JLabel();
        
        //Location label
        loc.setBounds(100,100,150,20);
        loc.setFont(new Font("Tahoma", Font.PLAIN, 30));
        layout.putConstraint
                          (SpringLayout.WEST, loc, 20, SpringLayout.WEST, this);
        this.add(loc);

        //Add the Forecast cards to a panel
        cards = new ArrayList();
        
        //Create all the cards to add to the tab
        for(int i = 0; i < Forecast.NUM; i++) cards.add(new ForecastCard());

        //Add each card to the this panel
        for(int i = 0; i < Forecast.NUM; i++){
            ForecastCard card = cards.get(i);
            
            int x = 300*(i%4)+20;
            int y = 275*(i/4)+60;
            
            layout.putConstraint
                          (SpringLayout.WEST, card, x, SpringLayout.WEST, this);
            layout.putConstraint
                        (SpringLayout.NORTH, card, y, SpringLayout.NORTH, this);
            this.add(card);
        }
        
        //Error label
        error.setFont(new Font("Tahoma", Font.PLAIN, 20));
        layout.putConstraint
                        (SpringLayout.WEST, error, 20, SpringLayout.WEST, this);
        layout.putConstraint
                     (SpringLayout.SOUTH, error, -20, SpringLayout.SOUTH, this);
        this.add(error);
        
        //Refresh Label
        ref.setFont(new Font("Tahoma", Font.PLAIN, 20));
        layout.putConstraint
                         (SpringLayout.EAST, ref, -20, SpringLayout.EAST, this);
        layout.putConstraint
                       (SpringLayout.SOUTH, ref, -20, SpringLayout.SOUTH, this);
        this.add(ref);
    }
    
    /**
     * Updates all the cards with the data contained in the given forecast.
     * @param f a forecast which can be either short or long term
     * @param units the units the temperature will be displayed in. Ture = 
     * Celsius, False = Fahrenheit
     * @param location The name of the current location
     * @param refresh The time of the last refresh of the forecast data
     */
    public void 
             update(Forecast f, boolean units, String location, String refresh){
        loc.setText("Location:   " + location);
        ref.setText("Last Refresh: " + refresh);

        for(int i = 0; i < Forecast.NUM; i++){
            cards.get(i).update(f.get(i), units);
        }
    }
    
    /**
     * Update the error label in the panel with the given string
     * @param err the string to display in the error message label of the panel
     */
    public void setError(String err){
        error.setText(err);
    }
}