package team15.UserOjects;

import java.io.Serializable;

import javax.swing.ImageIcon;

public class Flag implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String flagName;
	private ImageIcon flagImage;
	
	public Flag(String fN, ImageIcon fI){
		flagName = fN;
		flagImage = fI;
	}
	public ImageIcon getImage(){
		return flagImage;
	}
	public String getName(){
		return flagName;
	}
}
