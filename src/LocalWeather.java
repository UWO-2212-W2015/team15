import javafx.stage.WindowEvent;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.GroupLayout.Alignment;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.TextArea;

public class LocalWeather extends JFrame implements ActionListener{
	
	private JTabbedPane tabbedPane;
	private JPanel current;
	private JPanel shortTerm;
	private JPanel longTerm;
	private SpringLayout layout_1;
	private int temperature = 19;
	private String temperatureString = "" + temperature;
	private JLabel tempNumber = new JLabel (temperatureString);
	private JLabel tempScaleLabel = new JLabel ("°c");
	private JTable table;
	private JLabel locationLabel_1;
	private boolean tempBool = true;
	private JRadioButton tempRadioCelcius;
	
	//Constructor
	public LocalWeather() {
		setTitle("Team 15 Weather");
		setSize(800, 600); // default size is 0,0
		setLocation(400,100);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		
		
		//Create the tab pages
		createCurrent();
		createShortTerm();
		createLongTerm();
		
		//Create new tabbed pane
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.LIGHT_GRAY);
		tabbedPane.setPreferredSize(new Dimension(5, 800));
		tabbedPane.setMinimumSize(new Dimension(5, 500));
		tabbedPane.setSize(new Dimension(0, 500));
		
		//Add the tabs
		tabbedPane.addTab("Current", current);
		tabbedPane.addTab("Short Term Forecast", shortTerm);
		tabbedPane.addTab("Longterm Forecast", longTerm);
		getContentPane().add(tabbedPane);
		
		JSeparator separator = new JSeparator();
		layout_1.putConstraint(SpringLayout.NORTH, separator, -2, SpringLayout.SOUTH, tempRadioCelcius);
		layout_1.putConstraint(SpringLayout.WEST, separator, 6, SpringLayout.EAST, tempScaleLabel);
		layout_1.putConstraint(SpringLayout.SOUTH, separator, 0, SpringLayout.SOUTH, tempRadioCelcius);
		layout_1.putConstraint(SpringLayout.EAST, separator, 6, SpringLayout.EAST, tempScaleLabel);
		current.add(separator);
		
		//Add menu bar and menus
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("Menu");
		menu.getAccessibleContext().setAccessibleDescription(
		        "Select difference page");
		menuBar.add(menu);
		
	  }
	
	//Current weather panel
	public void createCurrent(){
		
		//Set layout
		layout_1 = new SpringLayout();
		current = new JPanel();
		current.setLayout(layout_1);
		
		
		//Textpane
		DefaultStyledDocument document = new DefaultStyledDocument();
		JTextPane textPane = new JTextPane(document);
		//new style
		StyleContext context = new StyleContext();
		
		//Attribute set
		Font f = new Font("Tahoma" , Font.PLAIN, 18);
		textPane.setFont(f);
		AttributeSet aset = context.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.red);
		
		Style style = context.addStyle("test", null);
		textPane.setEditable(false);
		String data = "Wind" + "\n" + "\n" + 
				"Pressure" + "\n" +  "\n" + 
				"Humidity" + "\n" +  "\n" ;
		try {
			document.insertString(0, data, style);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		textPane.setCharacterAttributes(aset, false);
		layout_1.putConstraint(SpringLayout.NORTH, textPane, 39, SpringLayout.NORTH, current);
		layout_1.putConstraint(SpringLayout.WEST, textPane, 100, SpringLayout.EAST, tempNumber);
		current.add(textPane);
		
		//Location label
		locationLabel_1 = new JLabel ("Location");
		locationLabel_1.setBounds(100,100,150,20);
		locationLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		current.add(locationLabel_1);
		
		//Temperature label
		tempNumber.setFont(new Font("Tahoma", Font.PLAIN, 90));
		layout_1.putConstraint(SpringLayout.WEST, locationLabel_1, 20, SpringLayout.WEST, current);
		current.add(tempNumber);
		layout_1.putConstraint(SpringLayout.WEST, tempNumber, 120, SpringLayout.WEST, current);
		layout_1.putConstraint(SpringLayout.NORTH, tempNumber, 20, SpringLayout.NORTH, locationLabel_1);
		
		this.tempScaleLabel.setFont(new Font("Tahoma", Font.PLAIN, 60));
		current.add(this.tempScaleLabel);
		layout_1.putConstraint(SpringLayout.WEST, this.tempScaleLabel, 5, SpringLayout.EAST, tempNumber);
		layout_1.putConstraint(SpringLayout.NORTH, this.tempScaleLabel, 25, SpringLayout.NORTH, locationLabel_1);
		
		//Temperature radio celcius
		tempRadioCelcius = new JRadioButton("Celcius", true);
		current.add(tempRadioCelcius);
		layout_1.putConstraint(SpringLayout.NORTH, tempRadioCelcius, 6, SpringLayout.SOUTH, tempNumber);
		layout_1.putConstraint(SpringLayout.WEST, tempRadioCelcius, 120, SpringLayout.WEST, current);
		tempRadioCelcius.addActionListener(this);
		tempRadioCelcius.setActionCommand("c");
		
		//Temperature radio fahrenheit
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
	
	//Short term forecast panel
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
	
	//Long term forecast panel
	public void createLongTerm(){
		//Set layout
		SpringLayout layout = new SpringLayout();
		longTerm = new JPanel();
		longTerm.setLayout(layout);
		
		//Location label
		JLabel locationLabel = new JLabel ("Location");
		locationLabel.setBounds(100,100,150,20);
		locationLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		longTerm.add(locationLabel);
		layout.putConstraint(SpringLayout.WEST, locationLabel, 20, SpringLayout.WEST, current);
	}
	
	//Radio celcius / fahrenheit conversions
	public void actionPerformed(ActionEvent ae) {
		String action = ae.getActionCommand();
		if (action.equals("c") && tempBool == false) {
			this.tempScaleLabel.setText("°c");
			this.temperature = (this.temperature-30)/2;
			this.temperatureString = ""+temperature;
			this.tempNumber.setText(temperatureString);
			tempBool = true;
		} else if (action.equals("f") && tempBool == true){
			this.tempScaleLabel.setText("°f");
			this.temperature = (this.temperature*2)+30;
			this.temperatureString = ""+temperature;
			this.tempNumber.setText(temperatureString);
			tempBool = false;
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
