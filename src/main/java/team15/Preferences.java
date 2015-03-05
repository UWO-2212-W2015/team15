package team15;
import java.io.Serializable;
class Preferences implements Serializable {
    //Initialized to false - if true than hide component
    boolean temperature, wind, pressure, humidity, condition, minTemp, maxTemp, tempUnits;
}
