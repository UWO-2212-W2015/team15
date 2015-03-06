package weather;


import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;

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
import java.util.ArrayList;

public class LocalWeather extends JFrame implements ActionListener{
	
	private JTabbedPane tabbedPane;
	private JPanel current;
	private JPanel shortTerm;
	private JPanel longTerm;
	private SpringLayout layout_1;
	private int temperature = 19;
	private String temperatureString = "" + temperature;
	private JLabel tempNumber = new JLabel (temperatureString);
	private JLabel tempScaleLabel = new JLabel ("c");
	private JTable table;
	private JLabel locationLabel_1;
	private boolean tempBool = true;
	private JRadioButton tempRadioCelcius;
	
	//for testing
	
	private int temperature1;
	private String temperatureString1;
	private JLabel tempNum1;
	private JLabel tempScaleLabel1;
	
	private int temperature2;
	private String temperatureString2;
	private JLabel tempNum2;
	private JLabel tempScaleLabel2;
	
	
	
	///////////////////////////////////////////////////////////////////////////////////
	//instance variables for short term 
	private User user;
	private ArrayList<Weather> shortTermList;
	private ArrayList<Weather> longTermList;
	private Weather shortTerm1;
	private Weather shortTerm2;
	private Weather shortTerm3;
	private Weather shortTerm4;
	private Weather shortTerm5;
	private Weather shortTerm6;
	private Weather shortTerm7;
	private Weather shortTerm8;
	
	private Weather longTerm1;
	private Weather longTerm2;
	private Weather longTerm3;
	private Weather longTerm4;
	private Weather longTerm5;
	private Weather longTerm6;
	private Weather longTerm7;
	private Weather longTerm8;
	
	private int shortTemperature1;
	private int shortTemperature2;
	private int shortTemperature3;
	private int shortTemperature4;
	private int shortTemperature5;
	private int shortTemperature6;
	private int shortTemperature7;
	private int shortTemperature8;
	
	private int longTemperature1;
	private int longTemperature2;
	private int longTemperature3;
	private int longTemperature4;
	private int longTemperature5;
	private int longTemperature6;
	private int longTemperature7;
	private int longTemperature8;
	
	
	private JLabel shortScale1 = new JLabel("c");
	private JLabel shortScale2 = new JLabel("c");
	private JLabel shortScale3 = new JLabel("c");
	private JLabel shortScale4 = new JLabel("c");
	private JLabel shortScale5 = new JLabel("c");
	private JLabel shortScale6 = new JLabel("c");
	private JLabel shortScale7 = new JLabel("c");
	private JLabel shortScale8 = new JLabel("c");
	
	
	private JLabel shortTemperatureLabel1;
	private JLabel shortTemperatureLabel2;
	private JLabel shortTemperatureLabel3;
	private JLabel shortTemperatureLabel4;
	private JLabel shortTemperatureLabel5;
	private JLabel shortTemperatureLabel6;
	private JLabel shortTemperatureLabel7;
	private JLabel shortTemperatureLabel8;
	
	private JLabel longTemperatureLabel1;
	private JLabel longTemperatureLabel2;
	private JLabel longTemperatureLabel3;
	private JLabel longTemperatureLabel4;
	private JLabel longTemperatureLabel5;
	private JLabel longTemperatureLabel6;
	private JLabel longTemperatureLabel7;
	private JLabel longTemperatureLabel8;
	
	private JLabel longScale1 = new JLabel("c");
	private JLabel longScale2 = new JLabel("c");
	private JLabel longScale3 = new JLabel("c");
	private JLabel longScale4 = new JLabel("c");
	private JLabel longScale5 = new JLabel("c");
	private JLabel longScale6 = new JLabel("c");
	private JLabel longScale7 = new JLabel("c");
	private JLabel longScale8 = new JLabel("c");
	////////////////////////////////////////////////////////////////////////////////
	
	
	
	
	
	//Constructor
	public LocalWeather() {
		//////////////////////////////////
		//for testing
		this.temperature1 = 50;
		tempNum1 = getTemperatureLabel(temperature1);
		
		this.temperature2 = 50;
		this.temperatureString2 = "" + temperature2;
		this.tempNum2 = new JLabel(temperatureString2);
		this.tempScaleLabel2 = new JLabel("c");
		///////////////////////////////////////
		
		
		
		setTitle("Team 15 Weather");
		setSize(1200, 800); // default size is 0,0
		setLocation(100,50);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		
		//get the name of the user,return a string
		//generates the user object
		//get the name of location,return a string
		//add the location string to the user object, which will generates the 
		//
		
		
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
		////////////////////////////////////////////////////////////////
		this.shortTemperature1 = 10;
		this.shortTemperature2 = 20;
		this.shortTemperature3 = 30;
		this.shortTemperature4 = 40;
		this.shortTemperature5 = 50;
		this.shortTemperature6 = 60;
		this.shortTemperature7 = 70;
		this.shortTemperature8 = 80;
		
		shortTemperatureLabel1 = getTemperatureLabel(shortTemperature1);
		shortTemperatureLabel2 = getTemperatureLabel(shortTemperature2);
		shortTemperatureLabel3 = getTemperatureLabel(shortTemperature3);
		shortTemperatureLabel4 = getTemperatureLabel(shortTemperature4);
		shortTemperatureLabel5 = getTemperatureLabel(shortTemperature5);
		shortTemperatureLabel6 = getTemperatureLabel(shortTemperature6);
		shortTemperatureLabel7 = getTemperatureLabel(shortTemperature7);
		shortTemperatureLabel8 = getTemperatureLabel(shortTemperature8);
		
		///////////////////////////////////////////////////////////////
		
		
		//Set layout
		GridBagLayout layout = new GridBagLayout();
		shortTerm = new JPanel();
		shortTerm.setLayout(layout);
		
		//Location Label
		JLabel locationLabel = new JLabel ("Location");
		locationLabel.setBounds(100,100,150,20);
		locationLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		//use grid bag layout to put eight small size panels into the short term panel//
		GridBagConstraints gc = new GridBagConstraints();
		gc.weightx = 0.5;
		gc.weighty = 0.5;
		gc.gridx = 0;
		gc.gridy = 0;
		shortTerm.add(locationLabel,gc);
		
		gc.gridx = 0;
		gc.gridy = 1;
		JPanel p1 = new JPanel();
		p1.setPreferredSize(new Dimension(250,200));
		p1.setBackground(Color.WHITE);
		SpringLayout sprlayout1 = new SpringLayout();
		p1.setLayout(sprlayout1);
		shortTemperatureLabel1.setFont(new Font("Tahoma",Font.PLAIN,60));	

		p1.add(shortTemperatureLabel1);
		sprlayout1.putConstraint(SpringLayout.WEST, shortTemperatureLabel1, 100, SpringLayout.WEST, p1);
		shortScale1.setFont(new Font("Tahoma",Font.PLAIN,40));
		p1.add(shortScale1);
		sprlayout1.putConstraint(SpringLayout.WEST, shortScale1, 5, SpringLayout.EAST, shortTemperatureLabel1);
		sprlayout1.putConstraint(SpringLayout.NORTH,shortScale1, 5,SpringLayout.NORTH,p1);
		JRadioButton c1 = new JRadioButton("Celcius", true);
		
		c1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				shortTemperature1 = (shortTemperature1 - 30)/2;
				shortTemperatureLabel1.setText(""+ shortTemperature1);
				shortScale1.setText("c");
			}
		});
		JRadioButton f1 = new JRadioButton("Fahrenheit", false);
		
		f1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				shortTemperature1 = shortTemperature1*2 + 30;
				shortTemperatureLabel1.setText(""+shortTemperature1);
				shortScale1.setText("f");
			}
		});
		
		ButtonGroup group1 = new ButtonGroup();
		group1.add(c1);
		group1.add(f1);
		
		p1.add(c1);
		p1.add(f1);
		sprlayout1.putConstraint(SpringLayout.WEST,c1,80,SpringLayout.WEST,p1);
		sprlayout1.putConstraint(SpringLayout.NORTH, c1, 5, SpringLayout.SOUTH, shortTemperatureLabel1);
		sprlayout1.putConstraint(SpringLayout.WEST,f1,5,SpringLayout.EAST,c1);
		sprlayout1.putConstraint(SpringLayout.NORTH, f1, 5, SpringLayout.SOUTH, shortTemperatureLabel1);
		
		JLabel t1 = new JLabel("Temperature :  "+shortTemperature1);
		JLabel sc1 = new JLabel("Sky Condition :");
		p1.add(t1);
		p1.add(sc1);
		sprlayout1.putConstraint(SpringLayout.WEST, t1, 80, SpringLayout.WEST, p1);
		sprlayout1.putConstraint(SpringLayout.NORTH, t1, 120, SpringLayout.NORTH, p1);
		sprlayout1.putConstraint(SpringLayout.WEST, sc1, 80, SpringLayout.WEST, p1);
		sprlayout1.putConstraint(SpringLayout.NORTH,sc1,10,SpringLayout.SOUTH,t1);
	
		shortTerm.add(p1,gc);
		
		//////////////////////////////////////////////////////////////////////////
		gc.gridx = 1;
		gc.gridy = 1;
		JPanel p2 = new JPanel();
		p2.setPreferredSize(new Dimension(250,200));
		p2.setBackground(Color.WHITE);
		SpringLayout sprlayout2 = new SpringLayout();
		p2.setLayout(sprlayout2);
		shortTemperatureLabel2.setFont(new Font("Tahoma",Font.PLAIN,60));	

		p2.add(shortTemperatureLabel2);
		sprlayout2.putConstraint(SpringLayout.WEST, shortTemperatureLabel2, 100, SpringLayout.WEST, p2);
		shortScale2.setFont(new Font("Tahoma",Font.PLAIN,40));
		p2.add(shortScale2);
		sprlayout2.putConstraint(SpringLayout.WEST, shortScale2, 5, SpringLayout.EAST, shortTemperatureLabel2);
		sprlayout2.putConstraint(SpringLayout.NORTH,shortScale2, 5,SpringLayout.NORTH,p2);
		JRadioButton c2 = new JRadioButton("Celcius", true);
		
		c2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				shortTemperature2 = (shortTemperature2 - 30)/2;
				shortTemperatureLabel2.setText(""+ shortTemperature2);
				shortScale2.setText("c");
			}
		});
		JRadioButton f2 = new JRadioButton("Fahrenheit", false);
		
		f2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				shortTemperature2 = shortTemperature2*2 + 30;
				shortTemperatureLabel2.setText(""+shortTemperature2);
				shortScale2.setText("f");
			}
		});
		
		ButtonGroup group2 = new ButtonGroup();
		group2.add(c2);
		group2.add(f2);
		p2.add(c2);
		p2.add(f2);
		
		
		sprlayout2.putConstraint(SpringLayout.WEST,c2,80,SpringLayout.WEST,p2);
		sprlayout2.putConstraint(SpringLayout.NORTH, c2, 5, SpringLayout.SOUTH, shortTemperatureLabel2);
		sprlayout2.putConstraint(SpringLayout.WEST,f2,5,SpringLayout.EAST,c2);
		sprlayout2.putConstraint(SpringLayout.NORTH, f2, 5, SpringLayout.SOUTH, shortTemperatureLabel2);
		
		JLabel t2 = new JLabel("Temperature :   "+shortTemperature2);
		JLabel sc2 = new JLabel("Sky Condition :");
		p2.add(t2);
		p2.add(sc2);
		sprlayout2.putConstraint(SpringLayout.WEST, t2, 80, SpringLayout.WEST, p2);
		sprlayout2.putConstraint(SpringLayout.NORTH, t2, 120, SpringLayout.NORTH, p2);
		sprlayout2.putConstraint(SpringLayout.WEST, sc2, 80, SpringLayout.WEST, p2);
		sprlayout2.putConstraint(SpringLayout.NORTH,sc2,10,SpringLayout.SOUTH,t2);
		
		shortTerm.add(p2,gc);
		///////////////////////////////////////////////////////////////////////////////
		
		
		gc.gridx = 2;
		gc.gridy = 1;
		JPanel p3 = new JPanel();
		p3.setPreferredSize(new Dimension(250,200));
		p3.setBackground(Color.WHITE);
		
		SpringLayout sprlayout3 = new SpringLayout();
		p3.setLayout(sprlayout3);
		shortTemperatureLabel3.setFont(new Font("Tahoma",Font.PLAIN,60));	

		p3.add(shortTemperatureLabel3);
		sprlayout3.putConstraint(SpringLayout.WEST, shortTemperatureLabel3, 100, SpringLayout.WEST, p3);
		shortScale3.setFont(new Font("Tahoma",Font.PLAIN,40));
		p3.add(shortScale3);
		sprlayout3.putConstraint(SpringLayout.WEST, shortScale3, 5, SpringLayout.EAST, shortTemperatureLabel3);
		sprlayout3.putConstraint(SpringLayout.NORTH,shortScale3, 5,SpringLayout.NORTH,p3);
		JRadioButton c3 = new JRadioButton("Celcius", true);
		
		c3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				shortTemperature3 = (shortTemperature3 - 30)/2;
				shortTemperatureLabel3.setText(""+ shortTemperature3);
				shortScale3.setText("c");
			}
		});
		JRadioButton f3 = new JRadioButton("Fahrenheit", false);
		
		f3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				shortTemperature3 = shortTemperature3*2 + 30;
				shortTemperatureLabel3.setText(""+shortTemperature3);
				shortScale3.setText("f");
			}
		});
		
		ButtonGroup group3 = new ButtonGroup();
		group3.add(c3);
		group3.add(f3);
		p3.add(c3);
		p3.add(f3);
		sprlayout3.putConstraint(SpringLayout.WEST,c3,80,SpringLayout.WEST,p3);
		sprlayout3.putConstraint(SpringLayout.NORTH, c3, 5, SpringLayout.SOUTH, shortTemperatureLabel3);
		sprlayout3.putConstraint(SpringLayout.WEST,f3,5,SpringLayout.EAST,c3);
		sprlayout3.putConstraint(SpringLayout.NORTH, f3, 5, SpringLayout.SOUTH, shortTemperatureLabel3);
		
		JLabel t3 = new JLabel("Temperature :   "+shortTemperature3);
		JLabel sc3 = new JLabel("Sky Condition :");
		p3.add(t3);
		p3.add(sc3);
		sprlayout3.putConstraint(SpringLayout.WEST, t3, 80, SpringLayout.WEST, p3);
		sprlayout3.putConstraint(SpringLayout.NORTH, t3, 120, SpringLayout.NORTH, p3);
		sprlayout3.putConstraint(SpringLayout.WEST, sc3, 80, SpringLayout.WEST, p3);
		sprlayout3.putConstraint(SpringLayout.NORTH,sc3,10,SpringLayout.SOUTH,t3);
		
		shortTerm.add(p3,gc);
		
		gc.gridx = 3;
		gc.gridy = 1;
		JPanel p4 = new JPanel();
		p4.setPreferredSize(new Dimension(250,200));
		p4.setBackground(Color.WHITE);
		
		SpringLayout sprlayout4 = new SpringLayout();
		p4.setLayout(sprlayout4);
		shortTemperatureLabel4.setFont(new Font("Tahoma",Font.PLAIN,60));	

		p4.add(shortTemperatureLabel4);
		sprlayout4.putConstraint(SpringLayout.WEST, shortTemperatureLabel4, 100, SpringLayout.WEST, p4);
		shortScale4.setFont(new Font("Tahoma",Font.PLAIN,40));
		p4.add(shortScale4);
		sprlayout4.putConstraint(SpringLayout.WEST, shortScale4, 5, SpringLayout.EAST, shortTemperatureLabel4);
		sprlayout4.putConstraint(SpringLayout.NORTH,shortScale4, 5,SpringLayout.NORTH,p4);
		JRadioButton c4 = new JRadioButton("Celcius", true);
		c4.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				shortTemperature4 = (shortTemperature4 - 30)/2;
				shortTemperatureLabel4.setText(""+ shortTemperature4);
				shortScale4.setText("c");
			}
		});
		JRadioButton f4 = new JRadioButton("Fahrenheit", false);
		
		f4.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				shortTemperature4 = shortTemperature4*2 + 30;
				shortTemperatureLabel4.setText(""+shortTemperature4);
				shortScale4.setText("f");
			}
		});
		
		ButtonGroup group4 = new ButtonGroup();
		group4.add(c4);
		group4.add(f4);
		p4.add(c4);
		p4.add(f4);
		sprlayout4.putConstraint(SpringLayout.WEST,c4,80,SpringLayout.WEST,p4);
		sprlayout4.putConstraint(SpringLayout.NORTH, c4, 5, SpringLayout.SOUTH, shortTemperatureLabel4);
		sprlayout4.putConstraint(SpringLayout.WEST,f4,5,SpringLayout.EAST,c4);
		sprlayout4.putConstraint(SpringLayout.NORTH, f4, 5, SpringLayout.SOUTH, shortTemperatureLabel4);
		
		JLabel t4 = new JLabel("Temperature :   "+shortTemperature4);
		JLabel sc4 = new JLabel("Sky Condition :");
		p4.add(t4);
		p4.add(sc4);
		sprlayout4.putConstraint(SpringLayout.WEST, t4, 80, SpringLayout.WEST, p4);
		sprlayout4.putConstraint(SpringLayout.NORTH, t4, 120, SpringLayout.NORTH, p4);
		sprlayout4.putConstraint(SpringLayout.WEST, sc4, 80, SpringLayout.WEST, p4);
		sprlayout4.putConstraint(SpringLayout.NORTH,sc4,10,SpringLayout.SOUTH,t4);
		
		shortTerm.add(p4,gc);
		
		gc.gridx = 0;
		gc.gridy = 2;
		JPanel p5 = new JPanel();
		p5.setPreferredSize(new Dimension(250,200));
		p5.setBackground(Color.WHITE);
		
		SpringLayout sprlayout5 = new SpringLayout();
		p5.setLayout(sprlayout5);
		shortTemperatureLabel5.setFont(new Font("Tahoma",Font.PLAIN,60));	

		p5.add(shortTemperatureLabel5);
		sprlayout5.putConstraint(SpringLayout.WEST, shortTemperatureLabel5, 100, SpringLayout.WEST, p5);
		shortScale5.setFont(new Font("Tahoma",Font.PLAIN,40));
		p5.add(shortScale5);
		sprlayout5.putConstraint(SpringLayout.WEST, shortScale5, 5, SpringLayout.EAST, shortTemperatureLabel5);
		sprlayout5.putConstraint(SpringLayout.NORTH,shortScale5, 5,SpringLayout.NORTH,p5);
		JRadioButton c5 = new JRadioButton("Celcius", true);
		c5.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				shortTemperature5 = (shortTemperature5 - 30)/2;
				shortTemperatureLabel5.setText(""+ shortTemperature5);
				shortScale5.setText("c");
			}
		});
		JRadioButton f5 = new JRadioButton("Fahrenheit", false);
		
		f5.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				shortTemperature5 = shortTemperature5*2 + 30;
				shortTemperatureLabel5.setText(""+shortTemperature5);
				shortScale5.setText("f");
			}
		});
		
		ButtonGroup group5 = new ButtonGroup();
		group5.add(c5);
		group5.add(f5);
		p5.add(c5);
		p5.add(f5);
		sprlayout5.putConstraint(SpringLayout.WEST,c5,80,SpringLayout.WEST,p5);
		sprlayout5.putConstraint(SpringLayout.NORTH, c5, 5, SpringLayout.SOUTH, shortTemperatureLabel5);
		sprlayout5.putConstraint(SpringLayout.WEST,f5,5,SpringLayout.EAST,c5);
		sprlayout5.putConstraint(SpringLayout.NORTH, f5, 5, SpringLayout.SOUTH, shortTemperatureLabel5);
		
		JLabel t5 = new JLabel("Temperature :   "+shortTemperature5);
		JLabel sc5 = new JLabel("Sky Condition :");
		p5.add(t5);
		p5.add(sc5);
		sprlayout5.putConstraint(SpringLayout.WEST, t5, 80, SpringLayout.WEST, p5);
		sprlayout5.putConstraint(SpringLayout.NORTH, t5, 120, SpringLayout.NORTH, p5);
		sprlayout5.putConstraint(SpringLayout.WEST, sc5, 80, SpringLayout.WEST, p5);
		sprlayout5.putConstraint(SpringLayout.NORTH,sc5,10,SpringLayout.SOUTH,t5);
		
		shortTerm.add(p5,gc);
		
		gc.gridx = 1;
		gc.gridy = 2;
		JPanel p6 = new JPanel();
		p6.setPreferredSize(new Dimension(250,200));
		p6.setBackground(Color.WHITE);
		
		SpringLayout sprlayout6 = new SpringLayout();
		p6.setLayout(sprlayout6);
		shortTemperatureLabel6.setFont(new Font("Tahoma",Font.PLAIN,60));	

		p6.add(shortTemperatureLabel6);
		sprlayout6.putConstraint(SpringLayout.WEST, shortTemperatureLabel6, 100, SpringLayout.WEST, p6);
		shortScale6.setFont(new Font("Tahoma",Font.PLAIN,40));
		p6.add(shortScale6);
		sprlayout6.putConstraint(SpringLayout.WEST, shortScale6, 5, SpringLayout.EAST, shortTemperatureLabel6);
		sprlayout6.putConstraint(SpringLayout.NORTH,shortScale6, 5,SpringLayout.NORTH,p6);
		JRadioButton c6 = new JRadioButton("Celcius", true);
		c6.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				shortTemperature6 = (shortTemperature6 - 30)/2;
				shortTemperatureLabel6.setText(""+ shortTemperature6);
				shortScale6.setText("c");
			}
		});
		JRadioButton f6 = new JRadioButton("Fahrenheit", false);
		
		f6.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				shortTemperature6 = shortTemperature6*2 + 30;
				shortTemperatureLabel6.setText(""+shortTemperature6);
				shortScale6.setText("f");
			}
		});
		
		ButtonGroup group6 = new ButtonGroup();
		group6.add(c6);
		group6.add(f6);
		p6.add(c6);
		p6.add(f6);
		sprlayout6.putConstraint(SpringLayout.WEST,c6,80,SpringLayout.WEST,p6);
		sprlayout6.putConstraint(SpringLayout.NORTH, c6, 5, SpringLayout.SOUTH, shortTemperatureLabel6);
		sprlayout6.putConstraint(SpringLayout.WEST,f6,5,SpringLayout.EAST,c6);
		sprlayout6.putConstraint(SpringLayout.NORTH, f6, 5, SpringLayout.SOUTH, shortTemperatureLabel6);
		
		JLabel t6 = new JLabel("Temperature :   " + shortTemperature6);
		JLabel sc6 = new JLabel("Sky Condition :");
		p6.add(t6);
		p6.add(sc6);
		sprlayout6.putConstraint(SpringLayout.WEST, t6, 80, SpringLayout.WEST, p6);
		sprlayout6.putConstraint(SpringLayout.NORTH, t6, 120, SpringLayout.NORTH, p6);
		sprlayout6.putConstraint(SpringLayout.WEST, sc6, 80, SpringLayout.WEST, p6);
		sprlayout6.putConstraint(SpringLayout.NORTH,sc6,10,SpringLayout.SOUTH,t6);
		
		shortTerm.add(p6,gc);
		
		gc.gridx = 2;
		gc.gridy = 2;
		JPanel p7 = new JPanel();
		p7.setPreferredSize(new Dimension(250,200));
		p7.setBackground(Color.WHITE);
		
		SpringLayout sprlayout7 = new SpringLayout();
		p7.setLayout(sprlayout7);
		shortTemperatureLabel7.setFont(new Font("Tahoma",Font.PLAIN,60));	

		p7.add(shortTemperatureLabel7);
		sprlayout7.putConstraint(SpringLayout.WEST, shortTemperatureLabel7, 100, SpringLayout.WEST, p7);
		shortScale7.setFont(new Font("Tahoma",Font.PLAIN,40));
		p7.add(shortScale7);
		sprlayout7.putConstraint(SpringLayout.WEST, shortScale7, 5, SpringLayout.EAST, shortTemperatureLabel7);
		sprlayout7.putConstraint(SpringLayout.NORTH,shortScale7, 5,SpringLayout.NORTH,p7);
		JRadioButton c7 = new JRadioButton("Celcius", true);
		c7.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				shortTemperature7 = (shortTemperature7 - 30)/2;
				shortTemperatureLabel7.setText(""+ shortTemperature7);
				shortScale7.setText("c");
			}
		});
		JRadioButton f7 = new JRadioButton("Fahrenheit", false);
		
		f7.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				shortTemperature7 = shortTemperature7*2 + 30;
				shortTemperatureLabel7.setText(""+shortTemperature7);
				shortScale7.setText("f");
			}
		});
		
		ButtonGroup group7 = new ButtonGroup();
		group7.add(c7);
		group7.add(f7);
		p7.add(c7);
		p7.add(f7);
		sprlayout7.putConstraint(SpringLayout.WEST,c7,80,SpringLayout.WEST,p7);
		sprlayout7.putConstraint(SpringLayout.NORTH, c7, 5, SpringLayout.SOUTH, shortTemperatureLabel7);
		sprlayout7.putConstraint(SpringLayout.WEST,f7,5,SpringLayout.EAST,c7);
		sprlayout7.putConstraint(SpringLayout.NORTH, f7, 5, SpringLayout.SOUTH, shortTemperatureLabel7);
		
		JLabel t7 = new JLabel("Temperature :   "+shortTemperature7);
		JLabel sc7 = new JLabel("Sky Condition :");
		p7.add(t7);
		p7.add(sc7);
		sprlayout7.putConstraint(SpringLayout.WEST, t7, 80, SpringLayout.WEST, p7);
		sprlayout7.putConstraint(SpringLayout.NORTH, t7, 120, SpringLayout.NORTH, p7);
		sprlayout7.putConstraint(SpringLayout.WEST, sc7, 80, SpringLayout.WEST, p7);
		sprlayout7.putConstraint(SpringLayout.NORTH,sc7,10,SpringLayout.SOUTH,t7);
		
		shortTerm.add(p7,gc);
		
		gc.gridx = 3;
		gc.gridy = 2;
		JPanel p8 = new JPanel();
		p8.setPreferredSize(new Dimension(250,200));
		p8.setBackground(Color.WHITE);
		
		SpringLayout sprlayout8 = new SpringLayout();
		p8.setLayout(sprlayout8);
		shortTemperatureLabel8.setFont(new Font("Tahoma",Font.PLAIN,60));	

		p8.add(shortTemperatureLabel8);
		sprlayout8.putConstraint(SpringLayout.WEST, shortTemperatureLabel8, 100, SpringLayout.WEST, p8);
		shortScale8.setFont(new Font("Tahoma",Font.PLAIN,40));
		p8.add(shortScale8);
		sprlayout8.putConstraint(SpringLayout.WEST, shortScale8, 5, SpringLayout.EAST, shortTemperatureLabel8);
		sprlayout8.putConstraint(SpringLayout.NORTH,shortScale8, 5,SpringLayout.NORTH,p8);
		JRadioButton c8 = new JRadioButton("Celcius", true);
		c8.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				shortTemperature8 = (shortTemperature8 - 30)/2;
				shortTemperatureLabel8.setText(""+ shortTemperature8);
				shortScale8.setText("c");
			}
		});
		JRadioButton f8 = new JRadioButton("Fahrenheit", false);
		
		f8.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				shortTemperature8 = shortTemperature8*2 + 30;
				shortTemperatureLabel8.setText(""+shortTemperature8);
				shortScale8.setText("f");
			}
		});
		
		ButtonGroup group8 = new ButtonGroup();
		group8.add(c8);
		group8.add(f8);
		p8.add(c8);
		p8.add(f8);
		sprlayout8.putConstraint(SpringLayout.WEST,c8,80,SpringLayout.WEST,p8);
		sprlayout8.putConstraint(SpringLayout.NORTH, c8, 5, SpringLayout.SOUTH, shortTemperatureLabel8);
		sprlayout8.putConstraint(SpringLayout.WEST,f8,5,SpringLayout.EAST,c8);
		sprlayout8.putConstraint(SpringLayout.NORTH, f8, 5, SpringLayout.SOUTH, shortTemperatureLabel8);
		
		JLabel t8 = new JLabel("Temperature :   "+shortTemperature8);
		JLabel sc8 = new JLabel("Sky Condition :");
		p8.add(t8);
		p8.add(sc8);
		sprlayout8.putConstraint(SpringLayout.WEST, t8, 80, SpringLayout.WEST, p8);
		sprlayout8.putConstraint(SpringLayout.NORTH, t8, 120, SpringLayout.NORTH, p8);
		sprlayout8.putConstraint(SpringLayout.WEST, sc8, 80, SpringLayout.WEST, p8);
		sprlayout8.putConstraint(SpringLayout.NORTH,sc8,10,SpringLayout.SOUTH,t8);
		
		shortTerm.add(p8,gc);
		
		JPanel temp = new JPanel();
		gc.gridx = 0;
		gc.gridy = 4;
		gc.weightx = 0.5;
		gc.weighty = 3;
		shortTerm.add(temp,gc);
		
		
	}
	
	//Long term forecast panel
	public void createLongTerm(){
		///////////////////////////////////////////////////////////
		Image icon1;
		Image icon2;
		Image icon3;
		Image icon4;
		Image icon5;
		Image icon6;
		Image icon7;
		Image icon8;
		
		this.longTemperature1 = 10;
		this.longTemperature2 = 20;
		this.longTemperature3 = 30;
		this.longTemperature4 = 40;
		this.longTemperature5 = 50;
		this.longTemperature6 = 60;
		this.longTemperature7 = 70;
		this.longTemperature8 = 80;
		
		longTemperatureLabel1 = getTemperatureLabel(longTemperature1);
		longTemperatureLabel2 = getTemperatureLabel(longTemperature2);
		longTemperatureLabel3 = getTemperatureLabel(longTemperature3);
		longTemperatureLabel4 = getTemperatureLabel(longTemperature4);
		longTemperatureLabel5 = getTemperatureLabel(longTemperature5);
		longTemperatureLabel6 = getTemperatureLabel(longTemperature6);
		longTemperatureLabel7 = getTemperatureLabel(longTemperature7);
		longTemperatureLabel8 = getTemperatureLabel(longTemperature8);
		///////////////////////////////////////////////////////////////

		
		//Set layout
		GridBagLayout layout = new GridBagLayout();
		longTerm = new JPanel();
		longTerm.setLayout(layout);
		
		
		
		
		//for setting the layout, can be changed
		
		//Location Label
		JLabel locationLabel = new JLabel ("Location");
		locationLabel.setBounds(100,100,150,20);
		locationLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		//use grid bag layout to put eight small size panels into the short term panel//
		GridBagConstraints gc = new GridBagConstraints();
		gc.weightx = 0.5;
		gc.weighty = 0.5;
		gc.gridx = 0;
		gc.gridy = 0;
		longTerm.add(locationLabel,gc);
		
		gc.gridx = 0;
		gc.gridy = 1;
		JPanel p1 = new JPanel();
		p1.setPreferredSize(new Dimension(250,250));
		p1.setBackground(Color.WHITE);
		SpringLayout sprlayout1 = new SpringLayout();
		p1.setLayout(sprlayout1);
		longTemperatureLabel1.setFont(new Font("Tahoma",Font.PLAIN,60));	

		p1.add(longTemperatureLabel1);
		sprlayout1.putConstraint(SpringLayout.WEST, longTemperatureLabel1, 100, SpringLayout.WEST, p1);
		longScale1.setFont(new Font("Tahoma",Font.PLAIN,40));
		p1.add(longScale1);
		sprlayout1.putConstraint(SpringLayout.WEST, longScale1, 5, SpringLayout.EAST, longTemperatureLabel1);
		sprlayout1.putConstraint(SpringLayout.NORTH,longScale1, 5,SpringLayout.NORTH,p1);
		
		JRadioButton c1 = new JRadioButton("Celcius", true);
		c1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				longTemperature1 = (longTemperature1 - 30)/2;
				longTemperatureLabel1.setText(""+ longTemperature1);
				longScale1.setText("c");
			}
		});
		JRadioButton f1 = new JRadioButton("Fahrenheit", false);
		
		f1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				longTemperature1 = longTemperature1*2 + 30;
				longTemperatureLabel1.setText(""+longTemperature1);
				longScale1.setText("f");
			}
		});
		
		ButtonGroup group1 = new ButtonGroup();
		group1.add(c1);
		group1.add(f1);
		p1.add(c1);
		p1.add(f1);
		sprlayout1.putConstraint(SpringLayout.WEST,c1,80,SpringLayout.WEST,p1);
		sprlayout1.putConstraint(SpringLayout.NORTH, c1, 5, SpringLayout.SOUTH, longTemperatureLabel1);
		sprlayout1.putConstraint(SpringLayout.WEST,f1,5,SpringLayout.EAST,c1);
		sprlayout1.putConstraint(SpringLayout.NORTH, f1, 5, SpringLayout.SOUTH, longTemperatureLabel1);
		
		JLabel t1 = new JLabel("Temperature :");
		JLabel sc1 = new JLabel("Sky Condition :");
		JLabel maxt1 = new JLabel("Maximum Temperature :");
		JLabel mint1 = new JLabel("Minmum Temperature :");
		p1.add(t1);
		p1.add(sc1);
		p1.add(maxt1);
		p1.add(mint1);
		sprlayout1.putConstraint(SpringLayout.WEST, t1, 20, SpringLayout.WEST, p1);
		sprlayout1.putConstraint(SpringLayout.NORTH, t1, 120, SpringLayout.NORTH, p1);
		sprlayout1.putConstraint(SpringLayout.WEST, sc1, 20, SpringLayout.WEST, p1);
		sprlayout1.putConstraint(SpringLayout.NORTH,sc1,10,SpringLayout.SOUTH,t1);
		sprlayout1.putConstraint(SpringLayout.WEST, maxt1, 20, SpringLayout.WEST, p1);
		sprlayout1.putConstraint(SpringLayout.NORTH, maxt1,10, SpringLayout.SOUTH, sc1);
		sprlayout1.putConstraint(SpringLayout.WEST, mint1, 20, SpringLayout.WEST, p1);
		sprlayout1.putConstraint(SpringLayout.NORTH, mint1,10, SpringLayout.SOUTH, maxt1);
		
		
		
		longTerm.add(p1,gc);	
		
		gc.gridx = 1;
		gc.gridy = 1;
		JPanel p2 = new JPanel();
		p2.setPreferredSize(new Dimension(250,250));
		p2.setBackground(Color.WHITE);
		SpringLayout sprlayout2 = new SpringLayout();
		p2.setLayout(sprlayout2);
		longTemperatureLabel2.setFont(new Font("Tahoma",Font.PLAIN,60));	

		p2.add(longTemperatureLabel2);
		sprlayout2.putConstraint(SpringLayout.WEST, longTemperatureLabel2, 100, SpringLayout.WEST, p2);
		longScale2.setFont(new Font("Tahoma",Font.PLAIN,40));
		p2.add(longScale2);
		sprlayout2.putConstraint(SpringLayout.WEST, longScale2, 5, SpringLayout.EAST, longTemperatureLabel2);
		sprlayout2.putConstraint(SpringLayout.NORTH,longScale2, 5,SpringLayout.NORTH,p2);
		JRadioButton c2 = new JRadioButton("Celcius", true);
		JRadioButton f2 = new JRadioButton("Fahrenheit", false);
		p2.add(c2);
		p2.add(f2);
		sprlayout2.putConstraint(SpringLayout.WEST,c2,80,SpringLayout.WEST,p2);
		sprlayout2.putConstraint(SpringLayout.NORTH, c2, 5, SpringLayout.SOUTH, longTemperatureLabel2);
		sprlayout2.putConstraint(SpringLayout.WEST,f2,5,SpringLayout.EAST,c2);
		sprlayout2.putConstraint(SpringLayout.NORTH, f2, 5, SpringLayout.SOUTH, longTemperatureLabel2);
		
		JLabel t2 = new JLabel("Temperature :");
		JLabel sc2 = new JLabel("Sky Condition :");
		JLabel maxt2 = new JLabel("Maximum Temperature :");
		JLabel mint2 = new JLabel("Minmum Temperature :");
		p2.add(t2);
		p2.add(sc2);
		p2.add(maxt2);
		p2.add(mint2);
		sprlayout2.putConstraint(SpringLayout.WEST, t2, 20, SpringLayout.WEST, p2);
		sprlayout2.putConstraint(SpringLayout.NORTH, t2, 120, SpringLayout.NORTH, p2);
		sprlayout2.putConstraint(SpringLayout.WEST, sc2, 20, SpringLayout.WEST, p2);
		sprlayout2.putConstraint(SpringLayout.NORTH,sc2,10,SpringLayout.SOUTH,t2);
		sprlayout2.putConstraint(SpringLayout.WEST, maxt2, 20, SpringLayout.WEST, p2);
		sprlayout2.putConstraint(SpringLayout.NORTH, maxt2,10, SpringLayout.SOUTH, sc2);
		sprlayout2.putConstraint(SpringLayout.WEST, mint2, 20, SpringLayout.WEST, p2);
		sprlayout2.putConstraint(SpringLayout.NORTH, mint2,10, SpringLayout.SOUTH, maxt2);
		
		longTerm.add(p2,gc);
		
		gc.gridx = 2;
		gc.gridy = 1;
		JPanel p3 = new JPanel();
		p3.setPreferredSize(new Dimension(250,250));
		p3.setBackground(Color.WHITE);
		
		SpringLayout sprlayout3 = new SpringLayout();
		p3.setLayout(sprlayout3);
		longTemperatureLabel3.setFont(new Font("Tahoma",Font.PLAIN,60));	

		p3.add(longTemperatureLabel3);
		sprlayout3.putConstraint(SpringLayout.WEST, longTemperatureLabel3, 100, SpringLayout.WEST, p3);
		longScale3.setFont(new Font("Tahoma",Font.PLAIN,40));
		p3.add(longScale3);
		sprlayout3.putConstraint(SpringLayout.WEST, longScale3, 5, SpringLayout.EAST, longTemperatureLabel3);
		sprlayout3.putConstraint(SpringLayout.NORTH,longScale3, 5,SpringLayout.NORTH,p3);
		JRadioButton c3 = new JRadioButton("Celcius", true);
		JRadioButton f3 = new JRadioButton("Fahrenheit", false);
		p3.add(c3);
		p3.add(f3);
		sprlayout3.putConstraint(SpringLayout.WEST,c3,80,SpringLayout.WEST,p3);
		sprlayout3.putConstraint(SpringLayout.NORTH, c3, 5, SpringLayout.SOUTH, longTemperatureLabel3);
		sprlayout3.putConstraint(SpringLayout.WEST,f3,5,SpringLayout.EAST,c3);
		sprlayout3.putConstraint(SpringLayout.NORTH, f3, 5, SpringLayout.SOUTH, longTemperatureLabel3);
		
		JLabel t3 = new JLabel("Temperature :");
		JLabel sc3 = new JLabel("Sky Condition :");
		JLabel maxt3 = new JLabel("Maximum Temperature :");
		JLabel mint3 = new JLabel("Minmum Temperature :");
		p3.add(t3);
		p3.add(sc3);
		p3.add(maxt3);
		p3.add(mint3);
		sprlayout3.putConstraint(SpringLayout.WEST, t3, 20, SpringLayout.WEST, p3);
		sprlayout3.putConstraint(SpringLayout.NORTH, t3, 120, SpringLayout.NORTH, p3);
		sprlayout3.putConstraint(SpringLayout.WEST, sc3, 20, SpringLayout.WEST, p3);
		sprlayout3.putConstraint(SpringLayout.NORTH,sc3,10,SpringLayout.SOUTH,t3);
		sprlayout3.putConstraint(SpringLayout.WEST, maxt3, 20, SpringLayout.WEST, p3);
		sprlayout3.putConstraint(SpringLayout.NORTH, maxt3,10, SpringLayout.SOUTH, sc3);
		sprlayout3.putConstraint(SpringLayout.WEST, mint3, 20, SpringLayout.WEST, p3);
		sprlayout3.putConstraint(SpringLayout.NORTH, mint3,10, SpringLayout.SOUTH, maxt3);
		
		longTerm.add(p3,gc);
		
		gc.gridx = 3;
		gc.gridy = 1;
		JPanel p4 = new JPanel();
		p4.setPreferredSize(new Dimension(250,250));
		p4.setBackground(Color.WHITE);
		
		SpringLayout sprlayout4 = new SpringLayout();
		p4.setLayout(sprlayout4);
		longTemperatureLabel4.setFont(new Font("Tahoma",Font.PLAIN,60));	

		p4.add(longTemperatureLabel4);
		sprlayout4.putConstraint(SpringLayout.WEST, longTemperatureLabel4, 100, SpringLayout.WEST, p4);
		longScale4.setFont(new Font("Tahoma",Font.PLAIN,40));
		p4.add(longScale4);
		sprlayout4.putConstraint(SpringLayout.WEST, longScale4, 5, SpringLayout.EAST, longTemperatureLabel4);
		sprlayout4.putConstraint(SpringLayout.NORTH,longScale4, 5,SpringLayout.NORTH,p4);
		JRadioButton c4 = new JRadioButton("Celcius", true);
		JRadioButton f4 = new JRadioButton("Fahrenheit", false);
		p4.add(c4);
		p4.add(f4);
		sprlayout4.putConstraint(SpringLayout.WEST,c4,80,SpringLayout.WEST,p4);
		sprlayout4.putConstraint(SpringLayout.NORTH, c4, 5, SpringLayout.SOUTH, longTemperatureLabel4);
		sprlayout4.putConstraint(SpringLayout.WEST,f4,5,SpringLayout.EAST,c4);
		sprlayout4.putConstraint(SpringLayout.NORTH, f4, 5, SpringLayout.SOUTH, longTemperatureLabel4);
		
		JLabel t4 = new JLabel("Temperature :");
		JLabel sc4 = new JLabel("Sky Condition :");
		JLabel maxt4 = new JLabel("Maximum Temperature :");
		JLabel mint4 = new JLabel("Minmum Temperature :");
		p4.add(t4);
		p4.add(sc4);
		p4.add(maxt4);
		p4.add(mint4);
		sprlayout4.putConstraint(SpringLayout.WEST, t4, 20, SpringLayout.WEST, p4);
		sprlayout4.putConstraint(SpringLayout.NORTH, t4, 120, SpringLayout.NORTH, p4);
		sprlayout4.putConstraint(SpringLayout.WEST, sc4, 20, SpringLayout.WEST, p4);
		sprlayout4.putConstraint(SpringLayout.NORTH,sc4,10,SpringLayout.SOUTH,t4);
		sprlayout4.putConstraint(SpringLayout.WEST, maxt4, 20, SpringLayout.WEST, p4);
		sprlayout4.putConstraint(SpringLayout.NORTH, maxt4,10, SpringLayout.SOUTH, sc4);
		sprlayout4.putConstraint(SpringLayout.WEST, mint4, 20, SpringLayout.WEST, p4);
		sprlayout4.putConstraint(SpringLayout.NORTH, mint4,10, SpringLayout.SOUTH, maxt4);
		
		longTerm.add(p4,gc);
		
		gc.gridx = 0;
		gc.gridy = 2;
		JPanel p5 = new JPanel();
		p5.setPreferredSize(new Dimension(250,250));
		p5.setBackground(Color.WHITE);
		
		SpringLayout sprlayout5 = new SpringLayout();
		p5.setLayout(sprlayout5);
		longTemperatureLabel5.setFont(new Font("Tahoma",Font.PLAIN,60));	

		p5.add(longTemperatureLabel5);
		sprlayout5.putConstraint(SpringLayout.WEST, longTemperatureLabel5, 100, SpringLayout.WEST, p5);
		longScale5.setFont(new Font("Tahoma",Font.PLAIN,40));
		p5.add(longScale5);
		sprlayout5.putConstraint(SpringLayout.WEST, longScale5, 5, SpringLayout.EAST, longTemperatureLabel5);
		sprlayout5.putConstraint(SpringLayout.NORTH,longScale5, 5,SpringLayout.NORTH,p5);
		JRadioButton c5 = new JRadioButton("Celcius", true);
		JRadioButton f5 = new JRadioButton("Fahrenheit", false);
		p5.add(c5);
		p5.add(f5);
		sprlayout5.putConstraint(SpringLayout.WEST,c5,80,SpringLayout.WEST,p5);
		sprlayout5.putConstraint(SpringLayout.NORTH, c5, 5, SpringLayout.SOUTH, longTemperatureLabel5);
		sprlayout5.putConstraint(SpringLayout.WEST,f5,5,SpringLayout.EAST,c5);
		sprlayout5.putConstraint(SpringLayout.NORTH, f5, 5, SpringLayout.SOUTH, longTemperatureLabel5);
		
		JLabel t5 = new JLabel("Temperature :");
		JLabel sc5 = new JLabel("Sky Condition :");
		JLabel maxt5 = new JLabel("Maximum Temperature :");
		JLabel mint5 = new JLabel("Minmum Temperature :");
		p5.add(t5);
		p5.add(sc5);
		p5.add(maxt5);
		p5.add(mint5);
		sprlayout5.putConstraint(SpringLayout.WEST, t5, 20, SpringLayout.WEST, p5);
		sprlayout5.putConstraint(SpringLayout.NORTH, t5, 120, SpringLayout.NORTH, p5);
		sprlayout5.putConstraint(SpringLayout.WEST, sc5, 20, SpringLayout.WEST, p5);
		sprlayout5.putConstraint(SpringLayout.NORTH,sc5,10,SpringLayout.SOUTH,t5);
		sprlayout5.putConstraint(SpringLayout.WEST, maxt5, 20, SpringLayout.WEST, p5);
		sprlayout5.putConstraint(SpringLayout.NORTH, maxt5,10, SpringLayout.SOUTH, sc5);
		sprlayout5.putConstraint(SpringLayout.WEST, mint5, 20, SpringLayout.WEST, p5);
		sprlayout5.putConstraint(SpringLayout.NORTH, mint5,10, SpringLayout.SOUTH, maxt5);
		
		longTerm.add(p5,gc);
		
		gc.gridx = 1;
		gc.gridy = 2;
		JPanel p6 = new JPanel();
		p6.setPreferredSize(new Dimension(250,250));
		p6.setBackground(Color.WHITE);
		
		SpringLayout sprlayout6 = new SpringLayout();
		p6.setLayout(sprlayout6);
		longTemperatureLabel6.setFont(new Font("Tahoma",Font.PLAIN,60));	

		p6.add(longTemperatureLabel6);
		sprlayout6.putConstraint(SpringLayout.WEST, longTemperatureLabel6, 100, SpringLayout.WEST, p6);
		longScale6.setFont(new Font("Tahoma",Font.PLAIN,40));
		p6.add(longScale6);
		sprlayout6.putConstraint(SpringLayout.WEST, longScale6, 5, SpringLayout.EAST, longTemperatureLabel6);
		sprlayout6.putConstraint(SpringLayout.NORTH,longScale6, 5,SpringLayout.NORTH,p6);
		JRadioButton c6 = new JRadioButton("Celcius", true);
		JRadioButton f6 = new JRadioButton("Fahrenheit", false);
		p6.add(c6);
		p6.add(f6);
		sprlayout6.putConstraint(SpringLayout.WEST,c6,80,SpringLayout.WEST,p6);
		sprlayout6.putConstraint(SpringLayout.NORTH, c6, 5, SpringLayout.SOUTH, longTemperatureLabel6);
		sprlayout6.putConstraint(SpringLayout.WEST,f6,5,SpringLayout.EAST,c6);
		sprlayout6.putConstraint(SpringLayout.NORTH, f6, 5, SpringLayout.SOUTH, longTemperatureLabel6);
		
		JLabel t6 = new JLabel("Temperature :");
		JLabel sc6 = new JLabel("Sky Condition :");
		JLabel maxt6 = new JLabel("Maximum Temperature :");
		JLabel mint6 = new JLabel("Minmum Temperature :");
		p6.add(t6);
		p6.add(sc6);
		p6.add(maxt6);
		p6.add(mint6);
		sprlayout6.putConstraint(SpringLayout.WEST, t6, 20, SpringLayout.WEST, p6);
		sprlayout6.putConstraint(SpringLayout.NORTH, t6, 120, SpringLayout.NORTH, p6);
		sprlayout6.putConstraint(SpringLayout.WEST, sc6, 20, SpringLayout.WEST, p6);
		sprlayout6.putConstraint(SpringLayout.NORTH,sc6,10,SpringLayout.SOUTH,t6);
		sprlayout6.putConstraint(SpringLayout.WEST, maxt6, 20, SpringLayout.WEST, p6);
		sprlayout6.putConstraint(SpringLayout.NORTH, maxt6,10, SpringLayout.SOUTH, sc6);
		sprlayout6.putConstraint(SpringLayout.WEST, mint6, 20, SpringLayout.WEST, p6);
		sprlayout6.putConstraint(SpringLayout.NORTH, mint6,10, SpringLayout.SOUTH, maxt6);
		
		longTerm.add(p6,gc);
		
		gc.gridx = 2;
		gc.gridy = 2;
		JPanel p7 = new JPanel();
		p7.setPreferredSize(new Dimension(250,250));
		p7.setBackground(Color.WHITE);
		
		SpringLayout sprlayout7 = new SpringLayout();
		p7.setLayout(sprlayout7);
		longTemperatureLabel7.setFont(new Font("Tahoma",Font.PLAIN,60));	

		p7.add(longTemperatureLabel7);
		sprlayout7.putConstraint(SpringLayout.WEST, longTemperatureLabel7, 100, SpringLayout.WEST, p7);
		longScale7.setFont(new Font("Tahoma",Font.PLAIN,40));
		p7.add(longScale7);
		sprlayout7.putConstraint(SpringLayout.WEST, longScale7, 5, SpringLayout.EAST, longTemperatureLabel7);
		sprlayout7.putConstraint(SpringLayout.NORTH,longScale7, 5,SpringLayout.NORTH,p7);
		JRadioButton c7 = new JRadioButton("Celcius", true);
		JRadioButton f7 = new JRadioButton("Fahrenheit", false);
		p7.add(c7);
		p7.add(f7);
		sprlayout7.putConstraint(SpringLayout.WEST,c7,80,SpringLayout.WEST,p7);
		sprlayout7.putConstraint(SpringLayout.NORTH, c7, 5, SpringLayout.SOUTH, longTemperatureLabel7);
		sprlayout7.putConstraint(SpringLayout.WEST,f7,5,SpringLayout.EAST,c7);
		sprlayout7.putConstraint(SpringLayout.NORTH, f7, 5, SpringLayout.SOUTH, longTemperatureLabel7);
		
		JLabel t7 = new JLabel("Temperature :");
		JLabel sc7 = new JLabel("Sky Condition :");
		JLabel maxt7 = new JLabel("Maximum Temperature :");
		JLabel mint7= new JLabel("Minmum Temperature :");
		p7.add(t7);
		p7.add(sc7);
		p7.add(maxt7);
		p7.add(mint7);
		sprlayout7.putConstraint(SpringLayout.WEST, t7, 20, SpringLayout.WEST, p7);
		sprlayout7.putConstraint(SpringLayout.NORTH, t7, 120, SpringLayout.NORTH, p7);
		sprlayout7.putConstraint(SpringLayout.WEST, sc7, 20, SpringLayout.WEST, p7);
		sprlayout7.putConstraint(SpringLayout.NORTH,sc7,10,SpringLayout.SOUTH,t7);
		sprlayout7.putConstraint(SpringLayout.WEST, maxt7, 20, SpringLayout.WEST, p7);
		sprlayout7.putConstraint(SpringLayout.NORTH, maxt7,10, SpringLayout.SOUTH, sc7);
		sprlayout7.putConstraint(SpringLayout.WEST, mint7, 20, SpringLayout.WEST, p7);
		sprlayout7.putConstraint(SpringLayout.NORTH, mint7,10, SpringLayout.SOUTH, maxt7);
		
		longTerm.add(p7,gc);
		
		gc.gridx = 3;
		gc.gridy = 2;
		JPanel p8 = new JPanel();
		p8.setPreferredSize(new Dimension(250,250));
		p8.setBackground(Color.WHITE);
		
		SpringLayout sprlayout8 = new SpringLayout();
		p8.setLayout(sprlayout8);
		longTemperatureLabel8.setFont(new Font("Tahoma",Font.PLAIN,60));	

		p8.add(longTemperatureLabel8);
		sprlayout8.putConstraint(SpringLayout.WEST, longTemperatureLabel8, 100, SpringLayout.WEST, p8);
		longScale8.setFont(new Font("Tahoma",Font.PLAIN,40));
		p8.add(longScale8);
		sprlayout8.putConstraint(SpringLayout.WEST, longScale8, 5, SpringLayout.EAST, longTemperatureLabel8);
		sprlayout8.putConstraint(SpringLayout.NORTH,longScale8, 5,SpringLayout.NORTH,p8);
		JRadioButton c8 = new JRadioButton("Celcius", true);
		JRadioButton f8 = new JRadioButton("Fahrenheit", false);
		p8.add(c8);
		p8.add(f8);
		sprlayout8.putConstraint(SpringLayout.WEST,c8,80,SpringLayout.WEST,p8);
		sprlayout8.putConstraint(SpringLayout.NORTH, c8, 5, SpringLayout.SOUTH, longTemperatureLabel8);
		sprlayout8.putConstraint(SpringLayout.WEST,f8,5,SpringLayout.EAST,c8);
		sprlayout8.putConstraint(SpringLayout.NORTH, f8, 5, SpringLayout.SOUTH, longTemperatureLabel8);
		
		JLabel t8 = new JLabel("Temperature :");
		JLabel sc8 = new JLabel("Sky Condition :");
		JLabel maxt8 = new JLabel("Maximum Temperature :");
		JLabel mint8 = new JLabel("Minmum Temperature :");
		p8.add(t8);
		p8.add(sc8);
		p8.add(maxt8);
		p8.add(mint8);
		sprlayout8.putConstraint(SpringLayout.WEST, t8, 20, SpringLayout.WEST, p8);
		sprlayout8.putConstraint(SpringLayout.NORTH, t8, 120, SpringLayout.NORTH, p8);
		sprlayout8.putConstraint(SpringLayout.WEST, sc8, 20, SpringLayout.WEST, p8);
		sprlayout8.putConstraint(SpringLayout.NORTH,sc8,10,SpringLayout.SOUTH,t8);
		sprlayout8.putConstraint(SpringLayout.WEST, maxt8, 20, SpringLayout.WEST, p8);
		sprlayout8.putConstraint(SpringLayout.NORTH, maxt8,10, SpringLayout.SOUTH, sc8);
		sprlayout8.putConstraint(SpringLayout.WEST, mint8, 20, SpringLayout.WEST, p8);
		sprlayout8.putConstraint(SpringLayout.NORTH, mint8,10, SpringLayout.SOUTH, maxt8);
		
		longTerm.add(p8,gc);
		
		JPanel temp = new JPanel();
		gc.gridx = 0;
		gc.gridy = 4;
		gc.weightx = 0.5;
		gc.weighty = 3;
		longTerm.add(temp,gc);

	}
	
	//Radio celcius / fahrenheit conversions
	public void actionPerformed(ActionEvent ae) {
		String action = ae.getActionCommand();
		if (action.equals("c") && tempBool == false) {
			this.tempScaleLabel.setText("c");
			this.temperature = (this.temperature-30)/2;
			this.temperatureString = ""+temperature;
			this.tempNumber.setText(temperatureString);
			tempBool = true;
		} else if (action.equals("f") && tempBool == true){
			this.tempScaleLabel.setText("f");
			this.temperature = (this.temperature*2)+30;
			this.temperatureString = ""+temperature;
			this.tempNumber.setText(temperatureString);
			tempBool = false;
		}
	}
	/**
	 * 
	 * @param temp
	 * @return a JLabel of temperature
	 */
	public JLabel getTemperatureLabel(int temp){
		String tempString = "" + temp;
		JLabel temperatureLabel = new JLabel(tempString);
		return temperatureLabel;
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
