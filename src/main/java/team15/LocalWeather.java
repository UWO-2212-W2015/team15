package weather;


import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
	private JLabel tempScaleLabel = new JLabel ("c");
	private JTable table;
	private JLabel locationLabel_1;
	private boolean tempBool = true;
	private JRadioButton tempRadioCelcius;
	
	
	private int temperature1 = 20;
	private String temperatureString1 = "" + temperature1;
	private JLabel tempNum1 = new JLabel(temperatureString1);
	private JLabel tempScaleLabel1 = new JLabel("c");
	
	
	
	//Constructor
	public LocalWeather() {
		setTitle("Team 15 Weather");
		setSize(1200, 800); // default size is 0,0
		setLocation(100,50);
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
		GridBagLayout layout = new GridBagLayout();
		shortTerm = new JPanel();
		shortTerm.setLayout(layout);
		
		
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
		shortTerm.add(locationLabel,gc);
		
		gc.gridx = 0;
		gc.gridy = 1;
		JPanel p1 = new JPanel();
		p1.setPreferredSize(new Dimension(250,200));
		p1.setBackground(Color.WHITE);
		SpringLayout sprlayout1 = new SpringLayout();
		p1.setLayout(sprlayout1);
		tempNum1.setFont(new Font("Tahoma",Font.PLAIN,60));	

		p1.add(tempNum1);
		sprlayout1.putConstraint(SpringLayout.WEST, tempNum1, 100, SpringLayout.WEST, p1);
		JLabel tempscale1 = tempScaleLabel;
		tempscale1.setFont(new Font("Tahoma",Font.PLAIN,40));
		p1.add(tempscale1);
		sprlayout1.putConstraint(SpringLayout.WEST, tempscale1, 5, SpringLayout.EAST, tempNum1);
		sprlayout1.putConstraint(SpringLayout.NORTH,tempscale1, 5,SpringLayout.NORTH,p1);
		JRadioButton c1 = new JRadioButton("Celcius", true);
		c1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				temperature1 = (temperature1 - 30)/2;
				temperatureString1 = "" + temperature1;
				tempNum1.setText(temperatureString1);
				tempScaleLabel1.setText("c");
			}
		});
		JRadioButton f1 = new JRadioButton("Fahrenheit", false);
		
		f1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				temperature1 = temperature1*2 + 30;
				temperatureString1 = "" + temperature1;
				tempNum1.setText(temperatureString1);
				tempScaleLabel1.setText("f");
			}
		});
		
		ButtonGroup group1 = new ButtonGroup();
		group1.add(c1);
		group1.add(f1);
		
		p1.add(c1);
		p1.add(f1);
		sprlayout1.putConstraint(SpringLayout.WEST,c1,80,SpringLayout.WEST,p1);
		sprlayout1.putConstraint(SpringLayout.NORTH, c1, 5, SpringLayout.SOUTH, tempNum1);
		sprlayout1.putConstraint(SpringLayout.WEST,f1,5,SpringLayout.EAST,c1);
		sprlayout1.putConstraint(SpringLayout.NORTH, f1, 5, SpringLayout.SOUTH, tempNum1);
		
		JLabel t1 = new JLabel("Temperature :");
		JLabel sc1 = new JLabel("Sky Condition :");
		p1.add(t1);
		p1.add(sc1);
		sprlayout1.putConstraint(SpringLayout.WEST, t1, 20, SpringLayout.WEST, p1);
		sprlayout1.putConstraint(SpringLayout.NORTH, t1, 120, SpringLayout.NORTH, p1);
		sprlayout1.putConstraint(SpringLayout.WEST, sc1, 20, SpringLayout.WEST, p1);
		sprlayout1.putConstraint(SpringLayout.NORTH,sc1,10,SpringLayout.SOUTH,t1);
		
		
		
		shortTerm.add(p1,gc);
		
		gc.gridx = 1;
		gc.gridy = 1;
		JPanel p2 = new JPanel();
		p2.setPreferredSize(new Dimension(250,200));
		p2.setBackground(Color.WHITE);
		SpringLayout sprlayout2 = new SpringLayout();
		p2.setLayout(sprlayout2);
		JLabel tempNum2 = new JLabel("19");
		tempNum2.setFont(new Font("Tahoma",Font.PLAIN,60));	

		p2.add(tempNum2);
		sprlayout2.putConstraint(SpringLayout.WEST, tempNum2, 100, SpringLayout.WEST, p2);
		JLabel tempscale2 = new JLabel("c");
		tempscale2.setFont(new Font("Tahoma",Font.PLAIN,40));
		p2.add(tempscale2);
		sprlayout2.putConstraint(SpringLayout.WEST, tempscale2, 5, SpringLayout.EAST, tempNum2);
		sprlayout2.putConstraint(SpringLayout.NORTH,tempscale2, 5,SpringLayout.NORTH,p2);
		JRadioButton c2 = new JRadioButton("Celcius", true);
		JRadioButton f2 = new JRadioButton("Fahrenheit", false);
		p2.add(c2);
		p2.add(f2);
		sprlayout2.putConstraint(SpringLayout.WEST,c2,80,SpringLayout.WEST,p2);
		sprlayout2.putConstraint(SpringLayout.NORTH, c2, 5, SpringLayout.SOUTH, tempNum2);
		sprlayout2.putConstraint(SpringLayout.WEST,f2,5,SpringLayout.EAST,c2);
		sprlayout2.putConstraint(SpringLayout.NORTH, f2, 5, SpringLayout.SOUTH, tempNum2);
		
		JLabel t2 = new JLabel("Temperature :");
		JLabel sc2 = new JLabel("Sky Condition :");
		p2.add(t2);
		p2.add(sc2);
		sprlayout2.putConstraint(SpringLayout.WEST, t2, 20, SpringLayout.WEST, p2);
		sprlayout2.putConstraint(SpringLayout.NORTH, t2, 120, SpringLayout.NORTH, p2);
		sprlayout2.putConstraint(SpringLayout.WEST, sc2, 20, SpringLayout.WEST, p2);
		sprlayout2.putConstraint(SpringLayout.NORTH,sc2,10,SpringLayout.SOUTH,t2);
		
		shortTerm.add(p2,gc);
		
		gc.gridx = 2;
		gc.gridy = 1;
		JPanel p3 = new JPanel();
		p3.setPreferredSize(new Dimension(250,200));
		p3.setBackground(Color.WHITE);
		
		SpringLayout sprlayout3 = new SpringLayout();
		p3.setLayout(sprlayout3);
		JLabel tempNum3 = new JLabel("19");
		tempNum3.setFont(new Font("Tahoma",Font.PLAIN,60));	

		p3.add(tempNum3);
		sprlayout3.putConstraint(SpringLayout.WEST, tempNum3, 100, SpringLayout.WEST, p3);
		JLabel tempscale3 = new JLabel("c");
		tempscale3.setFont(new Font("Tahoma",Font.PLAIN,40));
		p3.add(tempscale3);
		sprlayout3.putConstraint(SpringLayout.WEST, tempscale3, 5, SpringLayout.EAST, tempNum3);
		sprlayout3.putConstraint(SpringLayout.NORTH,tempscale3, 5,SpringLayout.NORTH,p3);
		JRadioButton c3 = new JRadioButton("Celcius", true);
		JRadioButton f3 = new JRadioButton("Fahrenheit", false);
		p3.add(c3);
		p3.add(f3);
		sprlayout3.putConstraint(SpringLayout.WEST,c3,80,SpringLayout.WEST,p3);
		sprlayout3.putConstraint(SpringLayout.NORTH, c3, 5, SpringLayout.SOUTH, tempNum3);
		sprlayout3.putConstraint(SpringLayout.WEST,f3,5,SpringLayout.EAST,c3);
		sprlayout3.putConstraint(SpringLayout.NORTH, f3, 5, SpringLayout.SOUTH, tempNum3);
		
		JLabel t3 = new JLabel("Temperature :");
		JLabel sc3 = new JLabel("Sky Condition :");
		p3.add(t3);
		p3.add(sc3);
		sprlayout3.putConstraint(SpringLayout.WEST, t3, 20, SpringLayout.WEST, p3);
		sprlayout3.putConstraint(SpringLayout.NORTH, t3, 120, SpringLayout.NORTH, p3);
		sprlayout3.putConstraint(SpringLayout.WEST, sc3, 20, SpringLayout.WEST, p3);
		sprlayout3.putConstraint(SpringLayout.NORTH,sc3,10,SpringLayout.SOUTH,t3);
		
		shortTerm.add(p3,gc);
		
		gc.gridx = 3;
		gc.gridy = 1;
		JPanel p4 = new JPanel();
		p4.setPreferredSize(new Dimension(250,200));
		p4.setBackground(Color.WHITE);
		
		SpringLayout sprlayout4 = new SpringLayout();
		p4.setLayout(sprlayout4);
		JLabel tempNum4 = new JLabel("19");
		tempNum4.setFont(new Font("Tahoma",Font.PLAIN,60));	

		p4.add(tempNum4);
		sprlayout4.putConstraint(SpringLayout.WEST, tempNum4, 100, SpringLayout.WEST, p4);
		JLabel tempscale4 = new JLabel("c");
		tempscale4.setFont(new Font("Tahoma",Font.PLAIN,40));
		p4.add(tempscale4);
		sprlayout4.putConstraint(SpringLayout.WEST, tempscale4, 5, SpringLayout.EAST, tempNum4);
		sprlayout4.putConstraint(SpringLayout.NORTH,tempscale4, 5,SpringLayout.NORTH,p4);
		JRadioButton c4 = new JRadioButton("Celcius", true);
		JRadioButton f4 = new JRadioButton("Fahrenheit", false);
		p4.add(c4);
		p4.add(f4);
		sprlayout4.putConstraint(SpringLayout.WEST,c4,80,SpringLayout.WEST,p4);
		sprlayout4.putConstraint(SpringLayout.NORTH, c4, 5, SpringLayout.SOUTH, tempNum4);
		sprlayout4.putConstraint(SpringLayout.WEST,f4,5,SpringLayout.EAST,c4);
		sprlayout4.putConstraint(SpringLayout.NORTH, f4, 5, SpringLayout.SOUTH, tempNum4);
		
		JLabel t4 = new JLabel("Temperature :");
		JLabel sc4 = new JLabel("Sky Condition :");
		p4.add(t4);
		p4.add(sc4);
		sprlayout4.putConstraint(SpringLayout.WEST, t4, 20, SpringLayout.WEST, p4);
		sprlayout4.putConstraint(SpringLayout.NORTH, t4, 120, SpringLayout.NORTH, p4);
		sprlayout4.putConstraint(SpringLayout.WEST, sc4, 20, SpringLayout.WEST, p4);
		sprlayout4.putConstraint(SpringLayout.NORTH,sc4,10,SpringLayout.SOUTH,t4);
		
		shortTerm.add(p4,gc);
		
		gc.gridx = 0;
		gc.gridy = 2;
		JPanel p5 = new JPanel();
		p5.setPreferredSize(new Dimension(250,200));
		p5.setBackground(Color.WHITE);
		
		SpringLayout sprlayout5 = new SpringLayout();
		p5.setLayout(sprlayout5);
		JLabel tempNum5 = new JLabel("19");
		tempNum5.setFont(new Font("Tahoma",Font.PLAIN,60));	

		p5.add(tempNum5);
		sprlayout5.putConstraint(SpringLayout.WEST, tempNum5, 100, SpringLayout.WEST, p5);
		JLabel tempscale5 = new JLabel("c");
		tempscale5.setFont(new Font("Tahoma",Font.PLAIN,40));
		p5.add(tempscale5);
		sprlayout5.putConstraint(SpringLayout.WEST, tempscale5, 5, SpringLayout.EAST, tempNum5);
		sprlayout5.putConstraint(SpringLayout.NORTH,tempscale5, 5,SpringLayout.NORTH,p5);
		JRadioButton c5 = new JRadioButton("Celcius", true);
		JRadioButton f5 = new JRadioButton("Fahrenheit", false);
		p5.add(c5);
		p5.add(f5);
		sprlayout5.putConstraint(SpringLayout.WEST,c5,80,SpringLayout.WEST,p5);
		sprlayout5.putConstraint(SpringLayout.NORTH, c5, 5, SpringLayout.SOUTH, tempNum5);
		sprlayout5.putConstraint(SpringLayout.WEST,f5,5,SpringLayout.EAST,c5);
		sprlayout5.putConstraint(SpringLayout.NORTH, f5, 5, SpringLayout.SOUTH, tempNum5);
		
		JLabel t5 = new JLabel("Temperature :");
		JLabel sc5 = new JLabel("Sky Condition :");
		p5.add(t5);
		p5.add(sc5);
		sprlayout5.putConstraint(SpringLayout.WEST, t5, 20, SpringLayout.WEST, p5);
		sprlayout5.putConstraint(SpringLayout.NORTH, t5, 120, SpringLayout.NORTH, p5);
		sprlayout5.putConstraint(SpringLayout.WEST, sc5, 20, SpringLayout.WEST, p5);
		sprlayout5.putConstraint(SpringLayout.NORTH,sc5,10,SpringLayout.SOUTH,t5);
		
		shortTerm.add(p5,gc);
		
		gc.gridx = 1;
		gc.gridy = 2;
		JPanel p6 = new JPanel();
		p6.setPreferredSize(new Dimension(250,200));
		p6.setBackground(Color.WHITE);
		
		SpringLayout sprlayout6 = new SpringLayout();
		p6.setLayout(sprlayout6);
		JLabel tempNum6 = new JLabel("19");
		tempNum6.setFont(new Font("Tahoma",Font.PLAIN,60));	

		p6.add(tempNum6);
		sprlayout6.putConstraint(SpringLayout.WEST, tempNum6, 100, SpringLayout.WEST, p6);
		JLabel tempscale6 = new JLabel("c");
		tempscale6.setFont(new Font("Tahoma",Font.PLAIN,40));
		p6.add(tempscale6);
		sprlayout6.putConstraint(SpringLayout.WEST, tempscale6, 5, SpringLayout.EAST, tempNum6);
		sprlayout6.putConstraint(SpringLayout.NORTH,tempscale6, 5,SpringLayout.NORTH,p6);
		JRadioButton c6 = new JRadioButton("Celcius", true);
		JRadioButton f6 = new JRadioButton("Fahrenheit", false);
		p6.add(c6);
		p6.add(f6);
		sprlayout6.putConstraint(SpringLayout.WEST,c6,80,SpringLayout.WEST,p6);
		sprlayout6.putConstraint(SpringLayout.NORTH, c6, 5, SpringLayout.SOUTH, tempNum6);
		sprlayout6.putConstraint(SpringLayout.WEST,f6,5,SpringLayout.EAST,c6);
		sprlayout6.putConstraint(SpringLayout.NORTH, f6, 5, SpringLayout.SOUTH, tempNum6);
		
		JLabel t6 = new JLabel("Temperature :");
		JLabel sc6 = new JLabel("Sky Condition :");
		p6.add(t6);
		p6.add(sc6);
		sprlayout6.putConstraint(SpringLayout.WEST, t6, 20, SpringLayout.WEST, p6);
		sprlayout6.putConstraint(SpringLayout.NORTH, t6, 120, SpringLayout.NORTH, p6);
		sprlayout6.putConstraint(SpringLayout.WEST, sc6, 20, SpringLayout.WEST, p6);
		sprlayout6.putConstraint(SpringLayout.NORTH,sc6,10,SpringLayout.SOUTH,t6);
		
		shortTerm.add(p6,gc);
		
		gc.gridx = 2;
		gc.gridy = 2;
		JPanel p7 = new JPanel();
		p7.setPreferredSize(new Dimension(250,200));
		p7.setBackground(Color.WHITE);
		
		SpringLayout sprlayout7 = new SpringLayout();
		p7.setLayout(sprlayout7);
		JLabel tempNum7 = new JLabel("19");
		tempNum7.setFont(new Font("Tahoma",Font.PLAIN,60));	

		p7.add(tempNum7);
		sprlayout7.putConstraint(SpringLayout.WEST, tempNum7, 100, SpringLayout.WEST, p7);
		JLabel tempscale7 = new JLabel("c");
		tempscale7.setFont(new Font("Tahoma",Font.PLAIN,40));
		p7.add(tempscale7);
		sprlayout7.putConstraint(SpringLayout.WEST, tempscale7, 5, SpringLayout.EAST, tempNum7);
		sprlayout7.putConstraint(SpringLayout.NORTH,tempscale7, 5,SpringLayout.NORTH,p7);
		JRadioButton c7 = new JRadioButton("Celcius", true);
		JRadioButton f7 = new JRadioButton("Fahrenheit", false);
		p7.add(c7);
		p7.add(f7);
		sprlayout7.putConstraint(SpringLayout.WEST,c7,80,SpringLayout.WEST,p7);
		sprlayout7.putConstraint(SpringLayout.NORTH, c7, 5, SpringLayout.SOUTH, tempNum7);
		sprlayout7.putConstraint(SpringLayout.WEST,f7,5,SpringLayout.EAST,c7);
		sprlayout7.putConstraint(SpringLayout.NORTH, f7, 5, SpringLayout.SOUTH, tempNum7);
		
		JLabel t7 = new JLabel("Temperature :");
		JLabel sc7 = new JLabel("Sky Condition :");
		p7.add(t7);
		p7.add(sc7);
		sprlayout7.putConstraint(SpringLayout.WEST, t7, 20, SpringLayout.WEST, p7);
		sprlayout7.putConstraint(SpringLayout.NORTH, t7, 120, SpringLayout.NORTH, p7);
		sprlayout7.putConstraint(SpringLayout.WEST, sc7, 20, SpringLayout.WEST, p7);
		sprlayout7.putConstraint(SpringLayout.NORTH,sc7,10,SpringLayout.SOUTH,t7);
		
		shortTerm.add(p7,gc);
		
		gc.gridx = 3;
		gc.gridy = 2;
		JPanel p8 = new JPanel();
		p8.setPreferredSize(new Dimension(250,200));
		p8.setBackground(Color.WHITE);
		
		SpringLayout sprlayout8 = new SpringLayout();
		p8.setLayout(sprlayout8);
		JLabel tempNum8 = new JLabel("19");
		tempNum8.setFont(new Font("Tahoma",Font.PLAIN,60));	

		p8.add(tempNum8);
		sprlayout8.putConstraint(SpringLayout.WEST, tempNum8, 100, SpringLayout.WEST, p8);
		JLabel tempscale8 = new JLabel("c");
		tempscale8.setFont(new Font("Tahoma",Font.PLAIN,40));
		p8.add(tempscale8);
		sprlayout8.putConstraint(SpringLayout.WEST, tempscale8, 5, SpringLayout.EAST, tempNum8);
		sprlayout8.putConstraint(SpringLayout.NORTH,tempscale8, 5,SpringLayout.NORTH,p8);
		JRadioButton c8 = new JRadioButton("Celcius", true);
		JRadioButton f8 = new JRadioButton("Fahrenheit", false);
		p8.add(c8);
		p8.add(f8);
		sprlayout8.putConstraint(SpringLayout.WEST,c8,80,SpringLayout.WEST,p8);
		sprlayout8.putConstraint(SpringLayout.NORTH, c8, 5, SpringLayout.SOUTH, tempNum8);
		sprlayout8.putConstraint(SpringLayout.WEST,f8,5,SpringLayout.EAST,c8);
		sprlayout8.putConstraint(SpringLayout.NORTH, f8, 5, SpringLayout.SOUTH, tempNum8);
		
		JLabel t8 = new JLabel("Temperature :");
		JLabel sc8 = new JLabel("Sky Condition :");
		p8.add(t8);
		p8.add(sc8);
		sprlayout8.putConstraint(SpringLayout.WEST, t8, 20, SpringLayout.WEST, p8);
		sprlayout8.putConstraint(SpringLayout.NORTH, t8, 120, SpringLayout.NORTH, p8);
		sprlayout8.putConstraint(SpringLayout.WEST, sc8, 20, SpringLayout.WEST, p8);
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
