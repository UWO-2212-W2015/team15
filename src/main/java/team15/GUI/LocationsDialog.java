package team15.GUI;

/**
 * A dialog window the allows the user to edit their saved locations and to
 * change their current location.
 * 
 * @author team15
 */

//Imports
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.TreeMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import team15.UserOjects.Location;
import team15.UserOjects.User;

public class LocationsDialog extends JDialog{
    //Lists
    private final JList locations;
    private final DefaultListModel<Location> model;
    
    //Labels
    private final JLabel error, cur;
    
    //Combo boxes
    private JComboBox<Location> cmbLocation;
    private JComboBox<String> country;
    
    //User
    private final User user;
    
    //Dimension
    private final Dimension dim;
    //List of all possible locations
    private static TreeMap<String, ArrayList<Location>> loc;

    /**
     * Creates the dialog that allows the user to interact with their location
     * list and their current location.  The user can add or remove locations
     * from their location list or change which location is their current
     * location
     * @param u the user object
     */
    public LocationsDialog(User u){
        super(new JFrame(), "Location List", true);
        
        this.user = u;
        
        //Load the list of all locations if necessary
        if(loc == null) loadLocations();
        
        //Set the dialog parameters
        JPanel panel = new JPanel();
        this.setResizable(false);
        this.getContentPane().add(panel);
	dim = new Dimension (500,450);
        this.setSize(dim);
        this.setLocation(200, 200);
        
        //Set the list parameters
        locations = new JList();
        locations.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        locations.setVisibleRowCount(-1);
        
        //model settings
        model = new DefaultListModel();
        locations.setModel(model);
        
        //Add all the locations to the model of the list box
        for(Location l: user.getLocations()){
            model.addElement(l);
        }
        
        //Layout settings
        SpringLayout layout = new SpringLayout();
        panel.setLayout(layout);
        
        //Set the list scroller settings
        JScrollPane scroller = new JScrollPane(locations);
        scroller.setPreferredSize(new Dimension(460, 200));
        layout.putConstraint
                    (SpringLayout.WEST, scroller, 20, SpringLayout.WEST, panel);
        panel.add(scroller);
        
        //Current location
        cur = new JLabel("Current location: " + user.getCurrentLocation());
        layout.putConstraint
                         (SpringLayout.WEST, cur, 20, SpringLayout.WEST, panel);
        layout.putConstraint
                    (SpringLayout.NORTH, cur, 20, SpringLayout.SOUTH, scroller);
        panel.add(cur);
        
        //Add buttons
        JButton set = new JButton("Change Current");
        JButton add = new JButton("Add Location");
        JButton remove = new JButton("Remove Location");
	JButton ok = new JButton("OK");
        
        //Add location settings
        layout.putConstraint
                         (SpringLayout.WEST, add, 20, SpringLayout.WEST, panel);
        layout.putConstraint
                         (SpringLayout.NORTH, add, 20, SpringLayout.SOUTH, cur);
        panel.add(add);
        
        //Remove location settings
        layout.putConstraint
                        (SpringLayout.WEST, remove, 20, SpringLayout.EAST, add);
        layout.putConstraint
                      (SpringLayout.NORTH, remove, 20, SpringLayout.SOUTH, cur);
        panel.add(remove);
        
        //Set location settings
        layout.putConstraint
                        (SpringLayout.WEST, set, 20, SpringLayout.EAST, remove);
        layout.putConstraint
                         (SpringLayout.NORTH, set, 20, SpringLayout.SOUTH, cur);
        panel.add(set);
        
        //Combo box labels
        //Coutry Label
        JLabel lblCountry = new JLabel("Country: ");
        layout.putConstraint
                  (SpringLayout.WEST, lblCountry, 20, SpringLayout.WEST, panel);
        layout.putConstraint
                  (SpringLayout.NORTH, lblCountry, 30, SpringLayout.SOUTH, add);
        panel.add(lblCountry);
        
        //Location label
        JLabel lblLoc = new JLabel("Location: ");
        layout.putConstraint
                      (SpringLayout.WEST, lblLoc, 20, SpringLayout.WEST, panel);
        layout.putConstraint
               (SpringLayout.NORTH, lblLoc, 20, SpringLayout.SOUTH, lblCountry);
        panel.add(lblLoc);
        
	panel.add(ok);

        //Combo boxes
        country = new JComboBox(loc.keySet().toArray());
        cmbLocation = new JComboBox();
        
        //Country combo box
        layout.putConstraint
                     (SpringLayout.WEST, country, 90, SpringLayout.WEST, panel);
        layout.putConstraint
                     (SpringLayout.NORTH, country, 24, SpringLayout.SOUTH, add);
        
        //Location combo box
        layout.putConstraint
                 (SpringLayout.WEST, cmbLocation, 90, SpringLayout.WEST, panel);
        layout.putConstraint
          (SpringLayout.NORTH, cmbLocation, 20, SpringLayout.SOUTH, lblCountry);
	
	//Ok Button location
	layout.putConstraint 
	    (SpringLayout.HORIZONTAL_CENTER, ok, 0, SpringLayout.HORIZONTAL_CENTER, panel);
	layout.putConstraint 
	    (SpringLayout.NORTH, ok, 20, SpringLayout.SOUTH, cmbLocation);
        
        panel.add(country);
        panel.add(cmbLocation);
        
        //Erros label
        error = new JLabel();
        layout.putConstraint
                       (SpringLayout.WEST, error, 20, SpringLayout.WEST, panel);
        layout.putConstraint
                    (SpringLayout.NORTH, error, 20, SpringLayout.SOUTH, lblLoc);
        panel.add(error);
        
        //Add an action listener for updating the country combo box
        country.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                String curCountry 
                                = country.getItemAt(country.getSelectedIndex());
                Object[] curLocations = loc.get(curCountry).toArray();
                DefaultComboBoxModel<Location> locModel 
                                       = new DefaultComboBoxModel(curLocations);
                cmbLocation.setModel(locModel);
            }    
        });
        
        //Set the current country to Canada
        country.setSelectedItem("CANADA");
        
        //Button action listeners
        //Remove button
        remove.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                remove();
            }    
        });
        
        //Add button
        add.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                add();
            }    
        });
        
        //Set current button
        set.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                set();
            }    
        });
         
	//OK button
	ok.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent event){
		    dispose();
		}
	    }
	    );
		    
        this.setVisible(true);
    }

    /**
     * Tries to set the current item selected in the location list as the new
     * current location for the user
     */
    private void set(){
        //Get the current index for the list box
        int i = locations.getSelectedIndex();
        
        //If no index is selected display an error
        if(i == -1){
            error.setText("Error: Please select a location from the list.");
            return;
        }
        
        //Get the location at the current index
        Location l = model.get(i);
        
        /* Check to see if the user already has this loation as the current 
         * location */
        if(l.equals(user.getCurrentLocation())){
            error.setText("Error: This is already your current location.");
            return;
        }
        //Save the old location and try to save the new user file
        Location oldLocation = user.getCurrentLocation();
        
        //Try to set the new location as the current location
        if(!user.setCurrentLoc(l)){
            error.setText("Error: Could not find the location");
            user.setCurrentLoc(oldLocation);
            return;
        }
        
        try{
            user.saveUser();
        } catch(Exception ex){
            user.setCurrentLoc(oldLocation);
            error.setText("Error: failed to save user "
                                                  + "data to the local drive.");
            return;
        }
        
        //Update the current location and error labels
        cur.setText("Current location: " + user.getCurrentLocation());
        error.setText("");
    }
    
    /**
     * Try to add the location selected in the location combo box to the list
     * of locations saved in the user object
     */
    private void add(){
        //Get the currently selected location in the combo box
        Location l = cmbLocation.getItemAt(cmbLocation.getSelectedIndex());
        
        //If nothign is slected display an error
        if(l == null){
            error.setText("Error: Please select a location to add.");
        }
        
        //Check to see if the location is already in the location list
        if(user.getLocations().contains(l)){
            error.setText("Error: that location already exists.");
            return;
        }

        //Add the location to the user's location list
        user.addLocation(l);
        
        //Try to save the new user file
        try{
            //If this is the first location in the list make it the current location
            if(user.getLocations().size() == 1){
                user.setCurrentLoc(l);
                cur.setText("Current location: " + user.getCurrentLocation());
            }
            
            user.saveUser();
        } catch(Exception ex){
            user.removeLocation(l);
            user.setCurrentLoc(new Location());
            error.setText("Error: failed to save user "
                                                  + "data to the local drive.");
        }
        
        //Add the new element to location list model
        model.addElement(l);
        
        //Update the error label
        error.setText("");
    }
    
    /**
     * Attempt to remove the location object that is in the location list from
     * the user's location list
     */
    private void remove(){
        //Get the current selected index
        int i = locations.getSelectedIndex();
        
        //If no index is selected display an error
        if(i == -1){
            error.setText("Error: Please select a location from the list.");
            return;
        }
        
        //Get the currently selected locaion object
        Location loc = model.get(i);
        
        //Check if the selected location is the current location
        if(loc.equals(user.getCurrentLocation())){
            error.setText("Error: You can not delete your current location.");
            return;
        }
        
        //Remove the location from the users list
        user.removeLocation(loc);
        
        //Try to save the new user file
        try{
            user.saveUser();
        } catch(Exception ex){
            user.addLocation(i, loc);
            error.setText("Error: failed to save user "
                                                  + "data to the local drive.");
            return;
        }
        
        //Remove the location from the location list model
        model.remove(i);
        
        //Update the error label
        error.setText("");
    }
    
    /**
     * Loads the list of all valid openweather locations from citylist.txt
     * @throws FileNotFoundException if citylist.txt is not found
     * @throws IOException if there is a problem loading the treemap from
     * citylist.txt
     */
    private void loadLocations(){
        //Try to open the city list text file
        try{
            InputStream fi = 
                    ArrayList.class.getResourceAsStream("/locations.dat");
            ObjectInputStream in = new ObjectInputStream(fi);
            ArrayList<Location> locList = (ArrayList) in.readObject();
            
            ArrayList<Location> cntLoc = new ArrayList();
            String cnt = "";
            
            loc = new TreeMap();
            
            for(Location l: locList){
                if(cnt.isEmpty()) cnt = l.getCountry();
                
                if(!cnt.equals(l.getCountry())){ 
                    loc.put(cnt, cntLoc);
                    cntLoc = new ArrayList();
                    cnt = l.getCountry();
                }
                cntLoc.add(l);
            }
        }
        catch(Exception ex){
            System.out.println("Fatal error loading locations.dat");
            System.exit(1);
        }
    }
}
