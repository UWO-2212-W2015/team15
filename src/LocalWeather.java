import javafx.stage.WindowEvent;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.GroupLayout.Alignment;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;

public class LocalWeather extends JFrame implements ActionListener{
	
	private JTabbedPane tabbedPane;
	private JPanel current;
	private JPanel shortTerm;
	private JPanel longTerm;
	private SpringLayout layout_1;
	private JLabel tempNumber = new JLabel ("19");
	private JLabel tempScaleLabel = new JLabel ("°c");
	
	public LocalWeather() {
		setTitle("Team 15 Weather");
		setSize(800, 600); // default size is 0,0
		setLocation(400,100);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		
		
		//create the tab pages
		createCurrent();
		createShortTerm();
		
		//create new tabbed pane
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.LIGHT_GRAY);
		tabbedPane.setPreferredSize(new Dimension(5, 800));
		tabbedPane.setMinimumSize(new Dimension(5, 500));
		tabbedPane.setSize(new Dimension(0, 500));
		
		//add the tabs
		tabbedPane.addTab("Current", current);
		tabbedPane.addTab("Short Term Forecast", shortTerm);
		tabbedPane.addTab("Longterm Forecast", longTerm);
		getContentPane().add(tabbedPane);
		
		
		//add menu bar and menus
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("Menu");
		menu.getAccessibleContext().setAccessibleDescription(
		        "Select difference page");
		menuBar.add(menu);
		
	  }
	
	public void createCurrent(){
		//Set layout
		layout_1 = new SpringLayout();
		current = new JPanel();
		current.setLayout(layout_1);
		
		//Location Label
		JLabel locationLabel = new JLabel ("Location");
		locationLabel.setBounds(100,100,150,20);
		locationLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		current.add(locationLabel);
		layout_1.putConstraint(SpringLayout.WEST, locationLabel, 20, SpringLayout.WEST, current);
		
		//Temperature Label
		tempNumber.setFont(new Font("Tahoma", Font.PLAIN, 90));
		current.add(tempNumber);
		layout_1.putConstraint(SpringLayout.WEST, tempNumber, 120, SpringLayout.WEST, current);
		layout_1.putConstraint(SpringLayout.NORTH, tempNumber, 40, SpringLayout.NORTH, locationLabel);
		
		//Temperature Scale Label
		this.tempScaleLabel.setFont(new Font("Tahoma", Font.PLAIN, 60));
		current.add(this.tempScaleLabel);
		layout_1.putConstraint(SpringLayout.WEST, this.tempScaleLabel, 5, SpringLayout.EAST, tempNumber);
		layout_1.putConstraint(SpringLayout.NORTH, this.tempScaleLabel, 40, SpringLayout.NORTH, locationLabel);
		
		//Temperature Radio Celcius
		JRadioButton tempRadioCelcius = new JRadioButton("Celcius", true);
		current.add(tempRadioCelcius);
		layout_1.putConstraint(SpringLayout.NORTH, tempRadioCelcius, 6, SpringLayout.SOUTH, tempNumber);
		layout_1.putConstraint(SpringLayout.WEST, tempRadioCelcius, 120, SpringLayout.WEST, current);
		tempRadioCelcius.addActionListener(this);
		tempRadioCelcius.setActionCommand("c");
		
		//Temperature Radio Fahrenheit
		JRadioButton tempRadioFahrenheit = new JRadioButton("Fahrenheit", false);
		current.add(tempRadioFahrenheit);
		layout_1.putConstraint(SpringLayout.NORTH, tempRadioFahrenheit, 6, SpringLayout.SOUTH, tempNumber);
		layout_1.putConstraint(SpringLayout.WEST, tempRadioFahrenheit, 5, SpringLayout.EAST, tempRadioCelcius);
		tempRadioFahrenheit.addActionListener(this);
		tempRadioFahrenheit.setActionCommand("f");
		
		ButtonGroup group = new ButtonGroup();
        group.add(tempRadioCelcius);
        group.add(tempRadioFahrenheit);
		
	}
	
	public void createShortTerm(){
		//Set layout
		SpringLayout layout = new SpringLayout();
		shortTerm = new JPanel();
		shortTerm.setLayout(layout);
		
		//Location Label
		JLabel locationLabel = new JLabel ("Location");
		locationLabel.setBounds(100,100,150,20);
		locationLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		shortTerm.add(locationLabel);
		layout.putConstraint(SpringLayout.WEST, locationLabel, 20, SpringLayout.WEST, current);
	}
	
	public void createLongTerm(){
		//Set layout
		SpringLayout layout = new SpringLayout();
		longTerm = new JPanel();
		longTerm.setLayout(layout);
		
		//Location Label
		JLabel locationLabel = new JLabel ("Location");
		locationLabel.setBounds(100,100,150,20);
		locationLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		longTerm.add(locationLabel);
		layout.putConstraint(SpringLayout.WEST, locationLabel, 20, SpringLayout.WEST, current);
	}
	
	//Radio Celcius / Fahrenheit
	public void actionPerformed(ActionEvent ae) {
		String action = ae.getActionCommand();
		if (action.equals("c")) {
			this.tempScaleLabel.setText("°c");
		} else if (action.equals("f")){
			this.tempScaleLabel.setText("°f");
		}
	}

	
	public static void main(String[] args) {
	    JFrame frame = new LocalWeather();
	    frame.setVisible(true);
	    frame.setResizable(false);

	    //Close frame
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    //Ignore this. This code segment is for later when we want the program to persist in the background
	    /*frame.addWindowListener( new WindowAdapter()
	    {
	        public void windowClosing(WindowEvent e)
	        {
	            JFrame frame = (JFrame)e.getSource();
	     
	            int result = JOptionPane.showConfirmDialog(
	                frame,
	                "Are you sure you want to exit the application?",
	                "Exit Application",
	                JOptionPane.YES_NO_OPTION);
	     
	            if (result == JOptionPane.YES_OPTION)
	                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        }
	    }); */
	    
	    
	  }
}
