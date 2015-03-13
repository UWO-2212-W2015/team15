package team15;

/**
 *
 * @author team15
 */

//Imports
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import org.json.JSONException;

public class LocationsDialog extends JDialog implements ActionListener{
    private final JList locations;
    private final DefaultListModel<Location> model;
    private final JTextField newLocation;
    private final JLabel error, cur;
    private final User user;
    
    public LocationsDialog(User user){
        super(new JFrame(), "Location List", true);
        
        this.user = user;
        
        //Set the list parameters
        locations = new JList();
        locations.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        locations.setVisibleRowCount(-1);
        model = new DefaultListModel();
        locations.setModel(model);
        
        JPanel panel = new JPanel();
        this.setResizable(false);
        this.getContentPane().add(panel);
        this.setSize(320, 370);
        this.setLocation(200, 200);
        
        SpringLayout layout = new SpringLayout();
        panel.setLayout(layout);
        
        JScrollPane scroller = new JScrollPane(locations);
        scroller.setPreferredSize(new Dimension(250, 200));
        layout.putConstraint(SpringLayout.WEST, scroller, 30, 
                                                SpringLayout.WEST, panel);
        panel.add(scroller);
        
                
        cur = new JLabel("Current location: " + user.getCurrentLocation());
        layout.putConstraint(SpringLayout.WEST, cur, 30, 
                                                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, cur, 20, 
                                                SpringLayout.SOUTH, scroller);
        panel.add(cur);
        
        //Add all the locations to the listbox
        for(Location l: user.getLocations()){
            model.addElement(l);
        }
        
        //Add buttons
        JButton set = new JButton("Change Current");
        JButton add = new JButton("Add Location");
        JButton remove = new JButton("Remove Location");
        
        layout.putConstraint(SpringLayout.WEST, add, 20, 
                                                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, add, 20, 
                                                SpringLayout.SOUTH, cur);
        panel.add(add);
        
        layout.putConstraint(SpringLayout.WEST, set, 20, 
                                                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, set, 20, 
                                                SpringLayout.SOUTH, add);
        panel.add(set);
        
        layout.putConstraint(SpringLayout.WEST, remove, 20, 
                                                SpringLayout.EAST, set);
        layout.putConstraint(SpringLayout.NORTH, remove, 20, 
                                                SpringLayout.SOUTH, add);
        panel.add(remove); 
        
        remove.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                remove();
            }    
        });
        
        add.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                add();
            }    
        });
        
        set.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                set();
            }    
        });
        
        //Add the text field for input of new weather objects
        newLocation = new JTextField();
        newLocation.setColumns(11);
        layout.putConstraint(SpringLayout.WEST, newLocation, 20, 
                                                SpringLayout.EAST, set);
        layout.putConstraint(SpringLayout.NORTH, newLocation, 24, 
                                                SpringLayout.SOUTH, cur);
        panel.add(newLocation);
        
        error = new JLabel();
        layout.putConstraint(SpringLayout.WEST, error, 20, 
                                                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, error, 20, 
                                                SpringLayout.SOUTH, remove);
        panel.add(error);
        
        this.setVisible(true);
    }

    private void set(){
        int i = locations.getSelectedIndex();
        
        if(i == -1){
            error.setText("Please select a location from the list.");
            return;
        }
        
        Location loc = model.get(i);
        
        if(loc.equals(user.getCurrentLocation())){
            error.setText("Error: This is already your current location.");
            return;
        }
        
        Location oldLocation = user.getCurrentLocation();
        if(!user.setCurrentLoc(loc)){
            error.setText("Error: Could not find location in user file.");
            return;
        }
        
        try{
            user.saveUser();
        } catch(Exception ex){
            user.setCurrentLoc(oldLocation);
            error.setText("Error saving user to the local drive");
            return;
        }
        
        error.setText("");
    }
    
    private void add(){
        String location = newLocation.getText();
        
        if(location.isEmpty()){
            newLocation.setText("Enter the new location here.");
            return;
        }
        
        Location loc = new Location(location);;
        
        for(Location l: user.getLocations()){
            if(loc.equals(l)){
                error.setText("Error: that location already exists.");
                newLocation.setText("");
                return;
            }
        }
        
        try{
            loc.updateForecasts();
        } catch (IOException ex) {
            error.setText("Error connecting to OpenWeather");
            return;
        } catch (JSONException ex) {
            error.setText("Error adding new location");
            return;
        }
        
        user.addLocation(loc);

        try{
            user.saveUser();
        } catch(Exception ex){
            user.removeLocation(loc);
            error.setText("Error saving user to the local drive");
        }
        
        model.addElement(loc);
        error.setText("");
        newLocation.setText("");
    }
    
    private void remove(){
        int i = locations.getSelectedIndex();
        
        if(i == -1){
            error.setText("Please select a location from the list.");
            return;
        }
        
        Location loc = model.get(i);
        if(loc == user.getCurrentLocation()){
            error.setText("You can not delete your current location.");
            return;
        }
        
        user.removeLocation(loc);
        
        try{
            user.saveUser();
        } catch(Exception ex){
            user.addLocation(i, loc);
            error.setText("Error saving user to local drive.");
            return;
        }
        
        model.remove(i);
        error.setText("");
    }

    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
