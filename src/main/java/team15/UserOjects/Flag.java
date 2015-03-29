package team15.UserOjects;

import java.io.Serializable;
import javax.swing.ImageIcon;

/**
 * The Flag Class specifies the characteristics of a flag object.
 * Through its information is possible to load the flags for each location.
 * 
 * @author team15
 */

public class Flag implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String flagName;
	private ImageIcon flagImage;
	
	/**
	 * Initializes the name and the image of the current flag.
	 * @param fN the string that represents the name of the flag.
	 * @param fI an ImageIcon for the picture of the flag.  
	 */
	public Flag(String fN, ImageIcon fI){
		flagName = fN;
		flagImage = fI;
	}
	
	/**
     * Returns the image of the flag. 
     * @return flagImage the image of the flag.
     */
	public ImageIcon getImage(){
		return flagImage;
	}
	
	/**
     * Returns the name of the flag. 
     * @return flagName the name of the flag.
     */
	public String getName(){
		return flagName;
	}
}
