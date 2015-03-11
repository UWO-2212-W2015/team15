package team15.User;

import java.io.Serializable;

public class Preferences implements Serializable {
    //Display properties - True = draw
    public boolean temperature = true;
    public boolean wind = true;
    public boolean pressure = true;
    public boolean humidity = true;
    public boolean minMaxTemp = true;
    public boolean sky = true;
    public boolean sun = true;
    public boolean icon = true;
    
    //Tempriture preferences - True = Celcius, False = Fahrenheit
    public boolean tempUnits = true;
}
