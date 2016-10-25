package com.alexhwang;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.geom.GeneralPath;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import com.alexhwang.util.Colors;

public class Board extends JFrame implements KeyListener{
	static final int ML = 32;
	static final String BASE_RESOURCE_PATH = "src\\main\\resources\\";
	
	private final JFrame frame = new JFrame("PACT");
	private final JLayeredPane mainPanel = new JLayeredPane();
	private final JLayeredPane menuPanel = new JLayeredPane();
	private final ArrayList<JLabel> menuArray0 = new ArrayList<JLabel>();
	private final JLabel menu1 = new JLabel("Party");
	private final JLabel menu2 = new JLabel("Inventory");
	private final JLabel menu3 = new JLabel("Equipment");
	private final JLabel menu4 = new JLabel("Pacts");
	private final JLabel menu5 = new JLabel("Registry");
	private final JLabel menu6 = new JLabel("File");
	private final JLabel menu7 = new JLabel("Options");
	private final JLabel menu8 = new JLabel("Quit");
	private final ArrayList<JLabel> menuArray6 = new ArrayList<JLabel>();
	private final JLabel menu60 = new JLabel("File");
	private final JLabel menu61 = new JLabel("Save");
	private final JLabel menu62 = new JLabel("Load");
	private final JLabel menu63 = new JLabel("Back");
	private final ArrayList<JLabel> menuArray7 = new ArrayList<JLabel>();
	private final JLabel menu70 = new JLabel("Options");
	private final JLabel menu71 = new JLabel("Value Skip");
	private final JLabel menu72 = new JLabel("Controls");
	private final JLabel menu73 = new JLabel("Battle Animations");
	private final JLabel menu74 = new JLabel("Autosave");
	private final JLabel menu75 = new JLabel("Back");
	private final ArrayList<JLabel> menuArray100 = new ArrayList<JLabel>();
	private final JLabel menu100 = new JLabel("Character Select");
	private final JLabel menu101 = new JLabel("Marx");
	private final JLabel menu102 = new JLabel("April");
	private final JLabel menu103 = new JLabel("Maia");
	private final JLabel menu104 = new JLabel("Juno");
	private final JLabel menu105 = new JLabel("Julia");
	private final JLabel menu106 = new JLabel("Augustus");
	private final JLabel menu107 = new JLabel("Septembra");
	private final JLabel menu108 = new JLabel("Octobelle");
	private final JLabel menu109 = new JLabel("November");
	private final JLabel menu110 = new JLabel("Decembus");
	private final JLabel menu111 = new JLabel("Janus");
	private final JLabel menu112 = new JLabel("Februa");
	private final ArrayList<JLabel> menuArray120 = new ArrayList<JLabel>();
	private final JLabel menu120 = new JLabel("PACT");
	private final JLabel menu121 = new JLabel("New Game");
	private final JLabel menu122 = new JLabel("Load File");
	private final JLabel menu123 = new JLabel("Quit");
	
	//private final JPanel foregroundPanel = new JPanel();
	private final JPanel pausePanel = new JPanel();
	private final JPanel dataPanel = new JPanel();
	private final JLabel portraitData = new JLabel();
	private final JLabel nameData = new JLabel();
	private final ArrayList<JLabel> nameDataArray0 = new ArrayList<JLabel>();
	private final JLabel nameDataName = new JLabel("<html><font color='gray'><b>Marx</b></font><br></html>");
	private final JLabel nameDataSpecies = new JLabel("<html>One of us</html>");
	private final JLabel nameDataClass = new JLabel("<html>Soldier</html>");
	private final ArrayList<JLabel> nameDataArray1 = new ArrayList<JLabel>();
	private final JLabel descriptionData = new JLabel("<html></html>");
	private final ArrayList<JLabel> descriptionDataArray = new ArrayList<JLabel>();
	
	private String icon = BASE_RESOURCE_PATH + "Objects\\Rock1.png";
	private Character character = new Character("?", "Marx", "DOWN", ML*1, ML*1, 1);
	private JLabel characterSprite = new JLabel(character.imageIcon);
	private SpeechBox speechBox;
	private GeneralPath path;
	private JLabel speech = new JLabel("");
	private Boolean going = false;
	private Boolean paused = false;
	private Boolean buttonPaused = false;
	private Boolean keyPaused = false;
	private Boolean superPaused = false;
	private Boolean saved = false;
	private Timer timer;
	private ArrayList<Thing> thingArray = new ArrayList<Thing>();
	private ArrayList<FalseThing> falseThingArray = new ArrayList<FalseThing>();
	private ArrayList<JLabel> thingSpriteArray = new ArrayList<JLabel>();
	private ArrayList<JLabel> falseThingSpriteArray = new ArrayList<JLabel>();
	private Thing savedThing;
	private ArrayList<Zone> zoneArray = new ArrayList<Zone>();
	private Zone currentZone;
	
	private ArrayList<Fae> faeRegistryArray = new ArrayList<Fae>();
	private ArrayList<Member> partyMemberArray = new ArrayList<Member>();
	//TODO arrays for items, pacts
	
	private Boolean animation = true;
	private Boolean autosave = false;
	private String currentFileName = "";
	
	private int valueSkip = 10;
	private int timerRun = 80;
	private int timerStep = 0;
	private int menuButton = 121;
	private int talk = 0;
	private int currentFile = 0;

	/*private int l1 = KeyEvent.VK_LEFT; private int l2 = KeyEvent.VK_A; private int l3 = KeyEvent.VK_NUMPAD4;
	private int r1 = KeyEvent.VK_RIGHT; private int r2 = KeyEvent.VK_D; private int r3 = KeyEvent.VK_NUMPAD6;
	private int u1 = KeyEvent.VK_UP; private int u2 = KeyEvent.VK_W; private int u3 = KeyEvent.VK_NUMPAD8;
	private int d1 = KeyEvent.VK_DOWN; private int d2 = KeyEvent.VK_S; private int d3 = KeyEvent.VK_NUMPAD2;
	private int ac1 = KeyEvent.VK_SPACE; private int ac2 = KeyEvent.VK_Q; private int ac3 = KeyEvent.VK_NUMPAD0;
	private int p1 = KeyEvent.VK_V; private int p2 = KeyEvent.VK_P; private int p3 = KeyEvent.VK_0; 
	private int mu1 = KeyEvent.VK_PAGE_UP; private int mu2 = KeyEvent.VK_SHIFT; private int mu3 = KeyEvent.VK_MINUS;
	private int md1 = KeyEvent.VK_PAGE_DOWN; private int md2 = KeyEvent.VK_CONTROL; private int md3 = KeyEvent.VK_EQUALS;
	private int muu1 = KeyEvent.VK_HOME; private int muu2 = KeyEvent.VK_PERIOD; private int muu3 = KeyEvent.VK_OPEN_BRACKET;
	private int mdd1 = KeyEvent.VK_END; private int mdd2 = KeyEvent.VK_SLASH; private int mdd3 = KeyEvent.VK_CLOSE_BRACKET;
	private int ms1 = KeyEvent.VK_ENTER; private int ms2 = KeyEvent.VK_Z; private int ms3 = KeyEvent.VK_BACK_SLASH;
	private int mb1 = KeyEvent.VK_ESCAPE; private int mb2 = KeyEvent.VK_BACK_SPACE; private int mb3 = KeyEvent.VK_DELETE;
	private int su1 = KeyEvent.VK_CAPS_LOCK; private int su2 = KeyEvent.VK_NUM_LOCK; private int su3 = KeyEvent.VK_M;*/
	private ArrayList<Integer> keyArray = new ArrayList<Integer>(Arrays.asList(KeyEvent.VK_LEFT, KeyEvent.VK_A, KeyEvent.VK_NUMPAD4,
			KeyEvent.VK_RIGHT, KeyEvent.VK_D, KeyEvent.VK_NUMPAD6, KeyEvent.VK_UP, KeyEvent.VK_W, KeyEvent.VK_NUMPAD8,
			KeyEvent.VK_DOWN, KeyEvent.VK_S, KeyEvent.VK_NUMPAD2, KeyEvent.VK_SPACE, KeyEvent.VK_Q, KeyEvent.VK_NUMPAD0,
			KeyEvent.VK_V, KeyEvent.VK_P, KeyEvent.VK_0, KeyEvent.VK_PAGE_UP, KeyEvent.VK_SHIFT, KeyEvent.VK_MINUS,
			KeyEvent.VK_PAGE_DOWN, KeyEvent.VK_CONTROL, KeyEvent.VK_EQUALS, KeyEvent.VK_HOME, KeyEvent.VK_PERIOD, KeyEvent.VK_OPEN_BRACKET,
			KeyEvent.VK_END, KeyEvent.VK_SLASH, KeyEvent.VK_CLOSE_BRACKET, KeyEvent.VK_ENTER, KeyEvent.VK_Z, KeyEvent.VK_BACK_SLASH,
			KeyEvent.VK_ESCAPE, KeyEvent.VK_BACK_SPACE, KeyEvent.VK_DELETE, KeyEvent.VK_CAPS_LOCK, KeyEvent.VK_NUM_LOCK, KeyEvent.VK_M));

    public Board() {
    	menuPanel.setPreferredSize(new Dimension(160, 400));
    	menuPanel.setLayout(null);
    	menuPanel.setBackground(new Color(224, 224, 224));
    	menuPanel.setOpaque(true);
    	
    	mainPanel.setPreferredSize(new Dimension(800, 800));
    	mainPanel.setLayout(null);
    	mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    	mainPanel.setBackground(Colors.WHITE);
    	mainPanel.setOpaque(true);

    	/*foregroundPanel.setBounds(1, 1, 798, 798);
    	foregroundPanel.setLayout(null);
    	foregroundPanel.setBackground(new Color(255, 127, 255, 31));
    	mainPanel.setLayer(foregroundPanel, 0);
    	mainPanel.add(foregroundPanel);*/
		
    	pausePanel.setBounds(1, 1, 798, 798);
    	pausePanel.setLayout(null);
    	pausePanel.setBackground(new Color(127, 127, 127, 63));
    	//pausePanel.setOpaque(false);
    	//mainPanel.add(pausePanel);

    	dataPanel.setBounds(1, 1, 798, 798);
    	dataPanel.setLayout(new BorderLayout());
    	dataPanel.setBackground(new Color(24, 24, 24, 255));
    	portraitData.setIcon(new ImageIcon(BASE_RESOURCE_PATH + "BattleSprites\\MarxF.png"));
    	portraitData.setHorizontalAlignment(SwingConstants.CENTER);
    	portraitData.setPreferredSize(new Dimension(400, 250));
    	dataPanel.add(portraitData, BorderLayout.NORTH);
    	nameDataArray0.add(new JLabel("<html>Name: </html>"));
    	nameDataArray0.add(new JLabel("<html>Species: </html>"));
    	nameDataArray0.add(new JLabel("<html>Class: </html>"));
    	nameDataArray1.add(nameDataName);
    	nameDataArray1.add(nameDataSpecies);
    	nameDataArray1.add(nameDataClass);
    	nameData.setLayout(null);
    	for (int i = 0; i < nameDataArray0.size(); i++) {
    		nameDataArray0.get(i).setBounds(340, 15 + i * 25, 140, 20);
    		nameDataArray0.get(i).setFont(new Font("Arial", Font.PLAIN, 16));
    		nameDataArray0.get(i).setForeground(new Color(212, 212, 212));
    		nameData.add(nameDataArray0.get(i));
    	}
    	for (int i = 0; i < nameDataArray1.size(); i++) {
    		nameDataArray1.get(i).setBounds(415, 15 + i * 25, 140, 20);
    		nameDataArray1.get(i).setFont(new Font("Arial", Font.PLAIN, 16));
    		nameDataArray1.get(i).setForeground(Color.WHITE);
    		nameData.add(nameDataArray1.get(i));
    	}
    	nameData.setVerticalAlignment(SwingConstants.TOP);
    	dataPanel.add(nameData, BorderLayout.CENTER);
    	dataPanel.add(descriptionData, BorderLayout.EAST);
    	
    	menuArray0.addAll(Arrays.asList(menu1, menu2, menu3, menu4, menu5, menu6, menu7, menu8));
    	menuArray6.addAll(Arrays.asList(menu60, menu61, menu62, menu63));
    	menuArray7.addAll(Arrays.asList(menu70, menu71, menu72, menu73, menu74, menu75));
    	menuArray100.addAll(Arrays.asList(menu100, menu101, menu102, menu103, menu104, menu105, menu106, menu107, menu108, menu109, menu110, menu111, menu112));
    	menuArray120.addAll(Arrays.asList(menu120, menu121, menu122, menu123));
    	for (int i = 0; i < menuArray0.size(); i++) {
    		menuArray0.get(i).setBounds(20, 20 + i * 30, 140, 25);
    		menuArray0.get(i).setFont(new Font("Arial", Font.PLAIN, 15));
    		menuArray0.get(i).setForeground(Color.GRAY);
    		menuArray0.get(i).setVisible(true);
    		menuPanel.setLayer(menuArray0.get(i), 1);
    		menuPanel.add(menuArray0.get(i));
    	}
    	for (int i = 0; i < menuArray6.size(); i++) {
    		if (i == 0) {
    			menuArray6.get(i).setBounds(10, 20 + i * 30, 140, 25);
        		menuArray6.get(i).setFont(new Font("Arial", Font.BOLD, 15));
        		menuArray6.get(i).setForeground(Color.BLACK);
    		}
    		else {
    			menuArray6.get(i).setBounds(20, 20 + i * 30, 140, 25);
        		menuArray6.get(i).setFont(new Font("Arial", Font.PLAIN, 15));
        		menuArray6.get(i).setForeground(Color.GRAY);
    		}
    		menuArray6.get(i).setVisible(false);
    		menuPanel.setLayer(menuArray6.get(i), 2);
    		menuPanel.add(menuArray6.get(i));
    	}
    	for (int i = 0; i < menuArray7.size(); i++) {
    		if (i == 0) {
    			menuArray7.get(i).setBounds(10, 20 + i * 30, 140, 25);
        		menuArray7.get(i).setFont(new Font("Arial", Font.BOLD, 15));
        		menuArray7.get(i).setForeground(Color.BLACK);
    		}
    		else {
    			menuArray7.get(i).setBounds(20, 20 + i * 30, 140, 25);
        		menuArray7.get(i).setFont(new Font("Arial", Font.PLAIN, 15));
        		menuArray7.get(i).setForeground(Color.GRAY);
    		}
    		menuArray7.get(i).setVisible(false);
    		menuPanel.setLayer(menuArray7.get(i), 2);
    		menuPanel.add(menuArray7.get(i));
    	}
    	for (int i = 0; i < menuArray100.size(); i++) {
    		if (i == 0) {
    			menuArray100.get(i).setBounds(10, 20 + i * 30, 140, 25);
    			menuArray100.get(i).setFont(new Font("Arial", Font.BOLD, 15));
    			menuArray100.get(i).setForeground(Color.WHITE);
    		}
    		else
    		{
        		menuArray100.get(i).setBounds(20, 20 + i * 30, 140, 25);
        		menuArray100.get(i).setFont(new Font("Arial", Font.PLAIN, 15));
        		menuArray100.get(i).setForeground(Color.GRAY);
    		}
    		menuPanel.setLayer(menuArray100.get(i), 2);
    		menuPanel.add(menuArray100.get(i));
    	}
    	for (int i = 0; i < menuArray120.size(); i++) {
    		if (i == 0) {
    			menuArray120.get(i).setBounds(0, 20 + i * 40, 140, 25);
    			menuArray120.get(i).setFont(new Font("Arial", Font.BOLD, 25));
    			menuArray120.get(i).setForeground(Color.WHITE);
    		}
    		else
    		{
    			menuArray120.get(i).setBounds(15, 60 + i * 40, 140, 25);
    			menuArray120.get(i).setFont(new Font("Arial", Font.PLAIN, 18));
    			menuArray120.get(i).setForeground(Color.GRAY);
    		}
    		menuPanel.setLayer(menuArray120.get(i), 2);
    		menuPanel.add(menuArray120.get(i));
    	}
    	
		timer = new Timer(timerRun, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				timerStep++;
				switch (timerStep) {
				case 1:
					character.setStep(2);
					break;
				case 2:
					character.setStep(1);
					break;
				case 3:
					character.setStep(3);
					break;
				case 4:
					character.setStep(1);
					going = false;
					timerStep = 0;
					timer.stop();
					break;
				}
				if (character.direction.equals("LEFT")) {
					character.setX(character.x - ML/4);
				}
				else if (character.direction.equals("RIGHT")) {
					character.setX(character.x + ML/4);
				}
				else if (character.direction.equals("UP")) {
					character.setY(character.y - ML/4);
				}
				else if (character.direction.equals("DOWN")) {
					character.setY(character.y + ML/4);
				}
				character.setImageIcon(BASE_RESOURCE_PATH + "Characters\\" + character.month + character.direction.substring(0, 1) + character.step + ".png");
		    	mainPanel.remove(characterSprite);
				characterSprite = new JLabel(character.imageIcon);
		    	mainPanel.setLayer(characterSprite, 1);
		    	mainPanel.add(characterSprite);
		    	characterSprite.setBounds(character.x, character.y, ML*1, ML*1);
		    	//FALSE THING
		    	for (int i = 0; i < falseThingArray.size(); i++) {
		    		FalseThing nowFalseThing = falseThingArray.get(i);
		    		if (character.x == nowFalseThing.x && character.y == nowFalseThing.y) {
		    			switch(nowFalseThing.action) {
		    			case 1:
		    				pauseMusic();
		    	        	character.setX(ML*Integer.parseInt(nowFalseThing.misc.get(1)));
		    	        	character.setY(ML*Integer.parseInt(nowFalseThing.misc.get(2)));
		    				setZone(Integer.parseInt(nowFalseThing.misc.get(0)));
		    				loadArea();
		    				break;
		    			default:
		    				break;
		    			}
		    		}
		    	}
		    	mainPanel.revalidate();
		    	mainPanel.repaint();
			}
		});
		timer.setInitialDelay(0);
    	
    	for (int i = 0; i < menuArray0.size(); i++) {
    		menuArray0.get(i).addMouseListener(new MegaMouseAdapter(1 + i) {
    	    	public void mouseClicked(MouseEvent c) {
    	    		int buttonIndex = getSavedValue();
    	    		if (!paused && menuButton >= 1 && menuButton <= 8 && !keyPaused && !superPaused) {
    	    			if (menuButton == buttonIndex) {
    	    				menuPress();
    	    			}
    	    			else {
    	    				playSound(BASE_RESOURCE_PATH + "Sounds\\MenuMove.wav");
    	        			menuButton = buttonIndex;
    	    			}
    		   			menuSet();
    	    		}
    	    	}
    	    });
    	}

    	for (int i = 1; i < menuArray6.size(); i++) {
    		menuArray6.get(i).addMouseListener(new MegaMouseAdapter(60 + i) {
    	    	public void mouseClicked(MouseEvent c) {
    	    		int buttonIndex = getSavedValue();
    	    		if (!paused && menuButton >= 61 && menuButton <= 63 && !keyPaused && !superPaused) {
    	    			if (menuButton == buttonIndex) {
    	    				menuPress();
    	    			}
    	    			else {
    	    				playSound(BASE_RESOURCE_PATH + "Sounds\\MenuMove.wav");
    	        			menuButton = buttonIndex;
    	    			}
    		   			menuSet();
    	    		}
    	    	}
    	    });
    	}
    	
    	for (int i = 1; i < menuArray7.size(); i++) {
    		menuArray7.get(i).addMouseListener(new MegaMouseAdapter(70 + i) {
    	    	public void mouseClicked(MouseEvent c) {
    	    		int buttonIndex = getSavedValue();
    	    		if (!paused && menuButton >= 71 && menuButton <= 75 && !keyPaused && !superPaused) {
    	    			if (menuButton == buttonIndex) {
    	    				menuPress();
    	    			}
    	    			else {
    	    				playSound(BASE_RESOURCE_PATH + "Sounds\\MenuMove.wav");
    	        			menuButton = buttonIndex;
    	    			}
    		   			menuSet();
    	    		}
    	    	}
    	    });
    	}
    	
    	for (int i = 1; i < menuArray100.size(); i++) {
    	    menuArray100.get(i).addMouseListener(new MegaMouseAdapter(100 + i) {
    	    	public void mouseClicked(MouseEvent c) {
    	    		int buttonIndex = getSavedValue();
    	    		if (menuButton >= 101 && menuButton <= 112) {
    	    			if (menuButton == buttonIndex) {
    	    				menuPress();
    	    			}
    	    			else {
    	    				playSound(BASE_RESOURCE_PATH + "Sounds\\MenuMove.wav");
    	        			menuButton = buttonIndex;
    	    			}
    		   			menuSet();
    	    		}
    	    	}
    	    });
    	}

    	for (int i = 1; i < menuArray120.size(); i++) {
    	    menuArray120.get(i).addMouseListener(new MegaMouseAdapter(120 + i) {
    	    	public void mouseClicked(MouseEvent c) {
    	    		int buttonIndex = getSavedValue();
    	    		if (menuButton >= 121 && menuButton <= 123) {
    	    			if (menuButton == buttonIndex) {
    	    				menuPress();
    	    			}
    	    			else {
    	    				playSound(BASE_RESOURCE_PATH + "Sounds\\MenuMove.wav");
    	        			menuButton = buttonIndex;
    	    			}
    		   			menuSet();
    	    		}
    	    	}
    	    });
    	}
    	
    	frame.addWindowListener(new java.awt.event.WindowAdapter() {
    	    @Override
    	    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
    			if (autosave && !going && !paused && !superPaused) {
    				saveGame();
    				System.exit(0);
    			}
    			else if (autosave) {
					Object[] choice = {"Go back", "Quit anyway"};
					int n = JOptionPane.showOptionDialog(frame, "File has not been saved. \nAutosave requires no running threads.", "Exit warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, choice, choice[0]);
					if (n == JOptionPane.NO_OPTION) {
						System.exit(0);
					}
					else {
    					playSound(BASE_RESOURCE_PATH + "Sounds\\MenuBack.wav");
					}
    			}
    			else if (!saved) {
					Object[] choice = {"Go back", "Quit anyway"};
					int n = JOptionPane.showOptionDialog(frame, "File has not been saved.", "Exit warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, choice, choice[0]);
					if (n == JOptionPane.NO_OPTION) {
						System.exit(0);
					}
					else {
    					playSound(BASE_RESOURCE_PATH + "Sounds\\MenuBack.wav");
					}
				}
				else {
					System.exit(0);
				}
    	    }
    	});
    	
    	frame.addKeyListener(this);
    	frame.add(mainPanel, BorderLayout.WEST);
    	frame.add(menuPanel, BorderLayout.EAST);
    	frame.setIconImage(new ImageIcon(icon).getImage());
    	frame.setSize(960,800);
    	frame.setLocation(0, 0);
    	frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    	frame.setResizable(false);
    	frame.pack();
    	frame.setVisible(true);
     }
    
    public void menuSet() {
    	if (menuButton >= 0 && menuButton <= 8) {
    		for (int i = 0; i < menuArray0.size(); i++) {
    			if (i == menuButton - 1) {
    				menuArray0.get(i).setFont(new Font("Arial", Font.BOLD, 15));
    				menuArray0.get(i).setForeground(Color.BLACK);
    			}
    			else {
    				menuArray0.get(i).setFont(new Font("Arial", Font.PLAIN, 15));
    				menuArray0.get(i).setForeground(Color.GRAY);
    			}
    		}
    	}
    	else if (menuButton >= 61 && menuButton <= 63) {
    		for (int i = 0; i < menuArray6.size(); i++) {
    			if (i == menuButton - 60) {
    				menuArray6.get(i).setFont(new Font("Arial", Font.BOLD, 15));
    				menuArray6.get(i).setForeground(Color.BLACK);
    			}
    			else {
    				menuArray6.get(i).setFont(new Font("Arial", Font.PLAIN, 15));
    				menuArray6.get(i).setForeground(Color.GRAY);
    			}
    		}
			menu60.setFont(new Font("Arial", Font.BOLD, 15));
			menu60.setForeground(Color.BLACK);
    	}
    	else if (menuButton >= 71 && menuButton <= 75) {
    		for (int i = 0; i < menuArray7.size(); i++) {
    			if (i == menuButton - 70) {
        			menuArray7.get(i).setFont(new Font("Arial", Font.BOLD, 15));
        			menuArray7.get(i).setForeground(Color.BLACK);
    			}
    			else {
        			menuArray7.get(i).setFont(new Font("Arial", Font.PLAIN, 15));
        			menuArray7.get(i).setForeground(Color.GRAY);
    			}
    		}
			menu70.setFont(new Font("Arial", Font.BOLD, 15));
			menu70.setForeground(Color.BLACK);
    	}
    	else if (menuButton >= 101 && menuButton <= 112) {
        	for (int i = 1; i < menuArray100.size(); i++) {
            	menuArray100.get(i).setBounds(20, 20 + i * 30, 140, 25);
        		if (i == menuButton - 100) {
        			menuArray100.get(i).setFont(new Font("Arial", Font.BOLD, 15));
        			menuArray100.get(i).setForeground(Color.WHITE);
        		}
        		else {
                	menuArray100.get(i).setFont(new Font("Arial", Font.PLAIN, 15));
                	menuArray100.get(i).setForeground(Color.GRAY);
        		}
        	}
    		dataPanel.remove(portraitData);
        	switch (menuButton) {
        	case 101:
            	portraitData.setIcon(new ImageIcon(BASE_RESOURCE_PATH + "BattleSprites\\MarxF.png"));
            	nameDataName.setText("<html><font color='gray'><b>Marx</b></font><br></html>");
            	nameDataClass.setText("<html>Soldier</html>");
        		break;
        	case 102:
            	portraitData.setIcon(new ImageIcon(BASE_RESOURCE_PATH + "BattleSprites\\AprilF.png"));
            	nameDataName.setText("<html><font color='gray'><b>April</b></font><br></html>");
            	nameDataClass.setText("<html>Revealer</html>");
        		break;
        	case 103:
            	portraitData.setIcon(new ImageIcon(BASE_RESOURCE_PATH + "BattleSprites\\MaiaF.png"));
            	nameDataName.setText("<html><font color='gray'><b>Maia</b></font><br></html>");
            	nameDataClass.setText("<html>Harvester</html>");
        		break;
        	case 104:
            	portraitData.setIcon(new ImageIcon(BASE_RESOURCE_PATH + "BattleSprites\\JunoF.png"));
            	nameDataName.setText("<html><font color='gray'><b>Juno</b></font><br></html>");
            	nameDataClass.setText("<html>Guardian</html>");
        		break;
        	case 105:
            	portraitData.setIcon(new ImageIcon(BASE_RESOURCE_PATH + "BattleSprites\\JuliaF.png"));
            	nameDataName.setText("<html><font color='gray'><b>Julia</b></font><br></html>");
            	nameDataClass.setText("<html>Patron</html>");
        		break;
        	case 106:
            	portraitData.setIcon(new ImageIcon(BASE_RESOURCE_PATH + "BattleSprites\\AugustusF.png"));
            	nameDataName.setText("<html><font color='gray'><b>Augustus</b></font><br></html>");
            	nameDataClass.setText("<html>Architect</html>");
        		break;
        	case 107:
            	portraitData.setIcon(new ImageIcon(BASE_RESOURCE_PATH + "BattleSprites\\SeptembraF.png"));
            	nameDataName.setText("<html><font color='gray'><b>Septembra</b></font><br></html>");
            	nameDataClass.setText("<html>Teacher</html>");
        		break;
        	case 108:
            	portraitData.setIcon(new ImageIcon(BASE_RESOURCE_PATH + "BattleSprites\\OctobelleF.png"));
            	nameDataName.setText("<html><font color='gray'><b>Octobelle</b></font><br></html>");
            	nameDataClass.setText("<html>Judge</html>");
        		break;
        	case 109:
            	portraitData.setIcon(new ImageIcon(BASE_RESOURCE_PATH + "BattleSprites\\NovemberF.png"));
            	nameDataName.setText("<html><font color='gray'><b>November</b></font><br></html>");
            	nameDataClass.setText("<html>Monk</html>");
        		break;
        	case 110:
            	portraitData.setIcon(new ImageIcon(BASE_RESOURCE_PATH + "BattleSprites\\DecembusF.png"));
            	nameDataName.setText("<html><font color='gray'><b>Decembus</b></font><br></html>");
            	nameDataClass.setText("<html>Director</html>");
        		break;
        	case 111:
            	portraitData.setIcon(new ImageIcon(BASE_RESOURCE_PATH + "BattleSprites\\JanusF.png"));
            	nameDataName.setText("<html><font color='gray'><b>Janus</b></font><br></html>");
            	nameDataClass.setText("<html>Gatekeeper</html>");
        		break;
        	case 112:
            	portraitData.setIcon(new ImageIcon(BASE_RESOURCE_PATH + "BattleSprites\\FebruaF.png"));
            	nameDataName.setText("<html><font color='gray'><b>Februa</b></font><br></html>");
            	nameDataClass.setText("<html>Purifier</html>");
        		break;
        	}
        	portraitData.setHorizontalAlignment(SwingConstants.CENTER);
        	portraitData.setPreferredSize(new Dimension(400, 250));
        	dataPanel.add(portraitData, BorderLayout.NORTH);
        	nameData.setVerticalAlignment(SwingConstants.TOP);
        	dataPanel.add(nameData, BorderLayout.CENTER);
        	dataPanel.add(descriptionData, BorderLayout.EAST);
        	dataPanel.repaint();
    	}
    	else if (menuButton >= 121 && menuButton <= 123) {
        	for (int i = 1; i < menuArray120.size(); i++) {
        		if (i == menuButton - 120) {
            		menuArray120.get(i).setFont(new Font("Arial", Font.BOLD, 18));
            		menuArray120.get(i).setForeground(Color.WHITE);
        		}
        		else {
            		menuArray120.get(i).setFont(new Font("Arial", Font.PLAIN, 18));
            		menuArray120.get(i).setForeground(Color.GRAY);
        		}
        	}
    	}
    }
    
    public void menuSelect(String direction)
    {
		playSound(BASE_RESOURCE_PATH + "Sounds\\MenuMove.wav");
    	if (direction.equals("UP")) {
    		if (menuButton > 1 && menuButton <= 8) {
    			menuButton--;
    		}
    		else if (menuButton == 1) {
    			menuButton = 8;
    		}
    		else if (menuButton > 61 && menuButton <= 63) {
    			menuButton--;
    		}
    		else if (menuButton == 61) {
    			menuButton = 63;
    		}
    		else if (menuButton > 71 && menuButton <= 75) {
    			menuButton--;
    		}
    		else if (menuButton == 71) {
    			menuButton = 75;
    		}
    		else if (menuButton > 101 && menuButton <= 112) {
    			menuButton--;
    		}
    		else if (menuButton == 101) {
    			menuButton = 112;
    		}
    		else if (menuButton > 121 && menuButton <= 123) {
    			menuButton--;
    		}
    		else if (menuButton == 121) {
    			menuButton = 123;
    		}
    	}
    	else {
    		if (menuButton < 8 && menuButton >= 1) {
    			menuButton++;
    		}
    		else if (menuButton == 8) {
    			menuButton = 1;
    		}
    		else if (menuButton < 63 && menuButton >= 61) {
    			menuButton++;
    		}
    		else if (menuButton == 63) {
    			menuButton = 61;
    		}
    		else if (menuButton < 75 && menuButton >= 71) {
    			menuButton++;
    		}
    		else if (menuButton == 75) {
    			menuButton = 71;
    		}
    		else if (menuButton < 112 && menuButton >= 101) {
    			menuButton++;
    		}
    		else if (menuButton == 112) {
    			menuButton = 101;
    		}
    		else if (menuButton < 123 && menuButton >= 121) {
    			menuButton++;
    		}
    		else if (menuButton == 123) {
    			menuButton = 121;
    		}
    	}
    	menuSet();
    }

    public void menuJump(String direction)
    {
		playSound(BASE_RESOURCE_PATH + "Sounds\\MenuMove.wav");
    	if (direction.equals("UP")) {
    		if (menuButton > 1 && menuButton <= 8) {
    			if (valueSkip >= menuButton) {
    				menuButton = 1;
    			}
    			else {
    				menuButton -= valueSkip;
    			}
    		}
    		else if (menuButton == 1) {
    			menuButton = 8;
    		}
    		else if (menuButton > 61 && menuButton <= 63) {
    			if (valueSkip >= (menuButton - 60)) {
    				menuButton = 61;
    			}
    			else {
    				menuButton -= valueSkip;
    			}
    		}
    		else if (menuButton == 61) {
    			menuButton = 63;
    		}
    		else if (menuButton > 71 && menuButton <= 75) {
    			if (valueSkip >= (menuButton - 70)) {
    				menuButton = 71;
    			}
    			else {
    				menuButton -= valueSkip;
    			}
    		}
    		else if (menuButton == 71) {
    			menuButton = 75;
    		}
    		else if (menuButton > 101 && menuButton <= 112) {
    			if (valueSkip >= (menuButton - 100)) {
    				menuButton = 101;
    			}
    			else {
    				menuButton -= valueSkip;
    			}
    		}
    		else if (menuButton == 101) {
    			menuButton = 112;
        	}
    		else if (menuButton > 121 && menuButton <= 123) {
    			if (valueSkip >= (menuButton - 120)) {
    				menuButton = 121;
    			}
    			else {
    				menuButton -= valueSkip;
    			}
    		}
    		else if (menuButton == 121) {
    			menuButton = 123;
        	}
		}
    	else { //"DOWN"
    		if (menuButton < 8 && menuButton >= 1) {
    			if (valueSkip >= 8 - menuButton) {
    				menuButton = 8;
    			}
    			else {
    				menuButton += valueSkip;
    			}
    		}
    		else if (menuButton == 8) {
    			menuButton = 1;
    		}
    		else if (menuButton < 63 && menuButton >= 61) {
    			if (valueSkip >= (3 - (menuButton - 60))) {
    				menuButton = 63;
    			}
    			else {
    				menuButton += valueSkip;
    			}
    		}
    		else if (menuButton == 63) {
    			menuButton = 61;
    		}
    		else if (menuButton < 75 && menuButton >= 71) {
    			if (valueSkip >= (5 - (menuButton - 70))) {
    				menuButton = 75;
    			}
    			else {
    				menuButton += valueSkip;
    			}
    		}
    		else if (menuButton == 75) {
    			menuButton = 71;
    		}
    		else if (menuButton < 112 && menuButton >= 101) {
    			if (valueSkip >= (12 - (menuButton - 100))) {
    				menuButton = 112;
    			}
    			else {
    				menuButton += valueSkip;
    			}
    		}
    		else if (menuButton == 112) {
    			menuButton = 101;
    		}
    		else if (menuButton < 123 && menuButton >= 121) {
    			if (valueSkip >= (3 - (menuButton - 120))) {
    				menuButton = 123;
    			}
    			else {
    				menuButton += valueSkip;
    			}
    		}
    		else if (menuButton == 123) {
    			menuButton = 121;
    		}
    	}
    	menuSet();
    }
    
    public void menuPress() {
    	switch (menuButton) {
		case 1: //Party
			playSound(BASE_RESOURCE_PATH + "Sounds\\MenuSelect.wav");
			break;
		case 2: //Inventory
			playSound(BASE_RESOURCE_PATH + "Sounds\\MenuSelect.wav");
			break;
		case 3: //Equipment
			playSound(BASE_RESOURCE_PATH + "Sounds\\MenuSelect.wav");
			break;
		case 4: //Pacts
			playSound(BASE_RESOURCE_PATH + "Sounds\\MenuSelect.wav");
			break;
		case 5: //Registry
			playSound(BASE_RESOURCE_PATH + "Sounds\\MenuSelect.wav");
			break;
		case 6: //File
			playSound(BASE_RESOURCE_PATH + "Sounds\\MenuSelect.wav");
			menuButton = 63;
			for (JLabel menuLabel : menuArray0) {
				menuLabel.setVisible(false);
			}
			for (JLabel menuLabel : menuArray6) {
				menuLabel.setVisible(true);
			}
			menuSet();
			break;
		case 7: //Options
			playSound(BASE_RESOURCE_PATH + "Sounds\\MenuSelect.wav");
			menuButton = 75;
			for (JLabel menuLabel : menuArray0) {
				menuLabel.setVisible(false);
			}
			for (JLabel menuLabel : menuArray7) {
				menuLabel.setVisible(true);
			}
			menuSet();
			break;
		case 8: //Quit
			playSound(BASE_RESOURCE_PATH + "Sounds\\MenuSelect.wav");
			if (autosave && !going && !paused && !superPaused) {
				saveGame();
				System.exit(0);
			}
			else if (autosave) {
				Object[] choice = {"Go back", "Quit anyway"};
				int n = JOptionPane.showOptionDialog(frame, "File has not been saved. \nAutosave requires no running threads.", "Exit warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, choice, choice[0]);
				if (n == JOptionPane.NO_OPTION) {
					System.exit(0);
				}
			}
			else if (!saved) {
				Object[] choice = {"Go back", "Quit anyway"};
				int n = JOptionPane.showOptionDialog(frame, "File has not been saved.", "Exit warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, choice, choice[0]);
				if (n == JOptionPane.NO_OPTION) {
					System.exit(0);
				}
			}
			else {
				System.exit(0);
			}
			break;
		case 61: //File - Save
			if (!autosave) {
				playSound(BASE_RESOURCE_PATH + "Sounds\\MenuSelect.wav");
			}
			saveGame();
			menuButton = 6;
			for (JLabel menuLabel : menuArray6) {
				menuLabel.setVisible(false);
			}
			for (JLabel menuLabel : menuArray0) {
				menuLabel.setVisible(true);
			}
			menuSet();
			break;
		case 62: //File - Load
			playSound(BASE_RESOURCE_PATH + "Sounds\\MenuSelect.wav");
			loadGame();
			menuButton = 6;
			for (JLabel menuLabel : menuArray6) {
				menuLabel.setVisible(false);
			}
			for (JLabel menuLabel : menuArray0) {
				menuLabel.setVisible(true);
			}
			menuSet();
			break;
		case 63: //File - Back
			playSound(BASE_RESOURCE_PATH + "Sounds\\MenuBack.wav");
			menuButton = 6;
			for (JLabel menuLabel : menuArray6) {
				menuLabel.setVisible(false);
			}
			for (JLabel menuLabel : menuArray0) {
				menuLabel.setVisible(true);
			}
			menuSet();
			break;
		case 71: //Options - Value Skip
			playSound(BASE_RESOURCE_PATH + "Sounds\\MenuSelect.wav");
			menuButton = 7;
			setValueSkip();
			for (JLabel menuLabel : menuArray7) {
				menuLabel.setVisible(false);
			}
			for (JLabel menuLabel : menuArray0) {
				menuLabel.setVisible(true);
			}
			menuSet();
			break;
		case 72: //Options - Controls
			playSound(BASE_RESOURCE_PATH + "Sounds\\MenuSelect.wav");
			menuButton = 7;
			setControls();
			for (JLabel menuLabel : menuArray7) {
				menuLabel.setVisible(false);
			}
			for (JLabel menuLabel : menuArray0) {
				menuLabel.setVisible(true);
			}
			menuSet();
			break;
		case 73: //Options - Battle Animations
			playSound(BASE_RESOURCE_PATH + "Sounds\\MenuSelect.wav");
			menuButton = 7;
			setBattleAnimations();
			for (JLabel menuLabel : menuArray7) {
				menuLabel.setVisible(false);
			}
			for (JLabel menuLabel : menuArray0) {
				menuLabel.setVisible(true);
			}
			menuSet();
			break;
		case 74: //Options - Autosave
			playSound(BASE_RESOURCE_PATH + "Sounds\\MenuSelect.wav");
			menuButton = 7;
			setAutosave();
			for (JLabel menuLabel : menuArray7) {
				menuLabel.setVisible(false);
			}
			for (JLabel menuLabel : menuArray0) {
				menuLabel.setVisible(true);
			}
			menuSet();
			break;
		case 75: //Options - Back
			playSound(BASE_RESOURCE_PATH + "Sounds\\MenuBack.wav");
			menuButton = 7;
			for (JLabel menuLabel : menuArray7) {
				menuLabel.setVisible(false);
			}
			for (JLabel menuLabel : menuArray0) {
				menuLabel.setVisible(true);
			}
			menuSet();
			break;
		case 101: //Choose Month
		case 102:
		case 103:
		case 104:
		case 105:
		case 106:
		case 107:
		case 108:
		case 109:
		case 110:
		case 111:
		case 112:
			playSound(BASE_RESOURCE_PATH + "Sounds\\MenuSelect.wav");
			chooseMonth();
			break;
		case 121: //New Game
			playSound(BASE_RESOURCE_PATH + "Sounds\\MenuSelect.wav");
			menuButton = 101;
			for (JLabel menuLabel : menuArray120) {
				menuLabel.setVisible(false);
				menuPanel.remove(menuLabel);
			}
			for (JLabel menuLabel : menuArray100) {
				menuLabel.setVisible(true);
			}
			setZone(1);
			loadArea();
			break;
		case 122: //Load File
			playSound(BASE_RESOURCE_PATH + "Sounds\\MenuSelect.wav");
			loadGame();
			break;
		case 123: //Quit
			playSound(BASE_RESOURCE_PATH + "Sounds\\MenuSelect.wav");
			System.exit(0);
			break;
		}
    }
    
	@Override
	public void keyPressed(KeyEvent k) {
		if (superPaused && menuButton >= 101 && menuButton <= 123) {
			//INIT
			if (k.getKeyCode() == keyArray.get(6) || k.getKeyCode() == keyArray.get(7) || k.getKeyCode() == keyArray.get(8)
					|| k.getKeyCode() == keyArray.get(18) || k.getKeyCode() == keyArray.get(19) || k.getKeyCode() == keyArray.get(20)) {
				menuSelect("UP"); 
			}
			else if (k.getKeyCode() == keyArray.get(9) || k.getKeyCode() == keyArray.get(10) || k.getKeyCode() == keyArray.get(11)
					|| k.getKeyCode() == keyArray.get(21) || k.getKeyCode() == keyArray.get(22) || k.getKeyCode() == keyArray.get(23)) {
				menuSelect("DOWN");
			}
			else if (k.getKeyCode() == keyArray.get(0) || k.getKeyCode() == keyArray.get(1) || k.getKeyCode() == keyArray.get(2)
					|| k.getKeyCode() == keyArray.get(24) || k.getKeyCode() == keyArray.get(25) || k.getKeyCode() == keyArray.get(26)) {
				menuJump("UP");
			}
			else if (k.getKeyCode() == keyArray.get(3) || k.getKeyCode() == keyArray.get(4) || k.getKeyCode() == keyArray.get(5)
					|| k.getKeyCode() == keyArray.get(27) || k.getKeyCode() == keyArray.get(28) || k.getKeyCode() == keyArray.get(29)) {
				menuJump("DOWN");
			}
			else if (k.getKeyCode() >= KeyEvent.VK_0 && k.getKeyCode() <= KeyEvent.VK_9) {
				if (menuButton >= 101 && menuButton <= 112) {
					playSound(BASE_RESOURCE_PATH + "Sounds\\MenuMove.wav");
					if (k.getKeyCode() >= KeyEvent.VK_1 && k.getKeyCode() <= KeyEvent.VK_9) {
						menuButton = 100 + k.getKeyCode() - KeyEvent.VK_0;
					}
					else {
						menuButton = 110;
					}
				}
				menuSet();
			}
			else if (k.getKeyCode() == keyArray.get(12) || k.getKeyCode() == keyArray.get(13) || k.getKeyCode() == keyArray.get(14)
					|| k.getKeyCode() == keyArray.get(30) || k.getKeyCode() == keyArray.get(31) || k.getKeyCode() == keyArray.get(32)) {
				menuPress();
			}
		}
		else if (!keyPaused && !superPaused) {
			//MENU
			if ((k.getKeyCode() == keyArray.get(18) || k.getKeyCode() == keyArray.get(19) || k.getKeyCode() == keyArray.get(20)) && !paused) {
				menuSelect("UP");
			}
			else if ((k.getKeyCode() == keyArray.get(21) || k.getKeyCode() == keyArray.get(22) || k.getKeyCode() == keyArray.get(23)) && !paused) {
				menuSelect("DOWN");
			}
			else if ((k.getKeyCode() == keyArray.get(24) || k.getKeyCode() == keyArray.get(25) || k.getKeyCode() == keyArray.get(26)) && !paused) {
				menuJump("UP");
			}
			else if ((k.getKeyCode() == keyArray.get(27) || k.getKeyCode() == keyArray.get(28) || k.getKeyCode() == keyArray.get(29)) && !paused) {
				menuJump("DOWN");
			}
			else if ((k.getKeyCode() >= KeyEvent.VK_0 && k.getKeyCode() <= KeyEvent.VK_9) && !paused) {
				playSound(BASE_RESOURCE_PATH + "Sounds\\MenuMove.wav");
				if (menuButton >= 1 && menuButton <= 8) {
					if (k.getKeyCode() >= KeyEvent.VK_1 && k.getKeyCode() <= KeyEvent.VK_8) {
						menuButton = k.getKeyCode() - KeyEvent.VK_0;
					}
					else {
						menuButton = 8;
					}
				}
				else if (menuButton >= 61 && menuButton <= 63) {
					if (k.getKeyCode() >= KeyEvent.VK_1 && k.getKeyCode() <= KeyEvent.VK_3) {
						menuButton = 60 + k.getKeyCode() - KeyEvent.VK_0;
					}
					else {
						menuButton = 63;
					}
				}
				else if (menuButton >= 71 && menuButton <= 75) {
					if (k.getKeyCode() >= KeyEvent.VK_1 && k.getKeyCode() <= KeyEvent.VK_5) {
						menuButton = 70 + k.getKeyCode() - KeyEvent.VK_0;
					}
					else {
						menuButton = 75;
					}
				}
				menuSet();
			}
			else if ((k.getKeyCode() == keyArray.get(33) || k.getKeyCode() == keyArray.get(34) || k.getKeyCode() == keyArray.get(35)) && !paused) {
				
				if (menuButton >= 61 && menuButton <= 63) {
					playSound(BASE_RESOURCE_PATH + "Sounds\\MenuBack.wav");
					menuButton = 6;
					for (JLabel menuLabel : menuArray6) {
						menuLabel.setVisible(false);
					}
					for (JLabel menuLabel : menuArray0) {
						menuLabel.setVisible(true);
					}
					menuSet();
				}
				else if (menuButton >= 71 && menuButton <= 75) {
					playSound(BASE_RESOURCE_PATH + "Sounds\\MenuBack.wav");
					menuButton = 7;
					for (JLabel menuLabel : menuArray7) {
						menuLabel.setVisible(false);
					}
					for (JLabel menuLabel : menuArray0) {
						menuLabel.setVisible(true);
					}
					menuSet();
				}
			}
			else if ((k.getKeyCode() == keyArray.get(30) || k.getKeyCode() == keyArray.get(31) || k.getKeyCode() == keyArray.get(32)) && !paused) {
				menuPress();
			}
			
			//CHARACTER
			else if ((k.getKeyCode() == keyArray.get(0) || k.getKeyCode() == keyArray.get(1) || k.getKeyCode() == keyArray.get(2)) && !paused && !going) {
				if (buttonPaused) {
					menuJump("UP");
				}
				else {
					Boolean allowed = true;
					going = true;
					saved = false;
					character.setDirection("LEFT");
			    	for (int i = 0; i < thingArray.size(); i++) {
			    		if (character.x - ML*1 == thingArray.get(i).x && character.y == thingArray.get(i).y) {
			    			allowed = false;
			    		}
			    	}
					if (allowed) {
						timer.start();
					}
					else {
						character.setImageIcon(BASE_RESOURCE_PATH + "Characters\\" + character.month + character.direction.substring(0,  1) + character.step + ".png");
				    	mainPanel.remove(characterSprite);
						characterSprite = new JLabel(character.imageIcon);
				    	mainPanel.setLayer(characterSprite, 1);
				    	mainPanel.add(characterSprite);
				    	characterSprite.setBounds(character.x, character.y, ML*1, ML*1);
				    	mainPanel.revalidate();
				    	mainPanel.repaint();
						going = false;
					}
				}
			}
			else if ((k.getKeyCode() == keyArray.get(3) || k.getKeyCode() == keyArray.get(4) || k.getKeyCode() == keyArray.get(5)) && !paused && !going) {
				if (buttonPaused) {
					menuJump("DOWN");
				}
				else {
					Boolean allowed = true;
					going = true;
					saved = false;
					character.setDirection("RIGHT");
			    	for (int i = 0; i < thingArray.size(); i++) {
			    		if (character.x + ML*1 == thingArray.get(i).x && character.y == thingArray.get(i).y) {
			    			allowed = false;
			    		}
			    	}
					if (allowed) {
						timer.start();
					}
					else {
						character.setImageIcon(BASE_RESOURCE_PATH + "Characters\\" + character.month + character.direction.substring(0,  1) + character.step + ".png");
				    	mainPanel.remove(characterSprite);
						characterSprite = new JLabel(character.imageIcon);
				    	mainPanel.setLayer(characterSprite, 1);
				    	mainPanel.add(characterSprite);
				    	characterSprite.setBounds(character.x, character.y, ML*1, ML*1);
				    	mainPanel.revalidate();
				    	mainPanel.repaint();
						going = false;
					}
				}
			}
			else if ((k.getKeyCode() == keyArray.get(6) || k.getKeyCode() == keyArray.get(7) || k.getKeyCode() == keyArray.get(8)) && !paused && !going) {
				if (buttonPaused) {
					menuSelect("UP");
				}
				else {
					Boolean allowed = true;
					going = true;
					saved = false;
					character.setDirection("UP");
			    	for (int i = 0; i < thingArray.size(); i++) {
			    		if (character.x == thingArray.get(i).x && character.y - ML*1 == thingArray.get(i).y) {
			    			allowed = false;
			    		}
			    	}
					if (allowed) {
						timer.start();
					}
					else {
						character.setImageIcon(BASE_RESOURCE_PATH + "Characters\\" + character.month + character.direction.substring(0,  1) + character.step + ".png");
				    	mainPanel.remove(characterSprite);
						characterSprite = new JLabel(character.imageIcon);
				    	mainPanel.setLayer(characterSprite, 1);
				    	mainPanel.add(characterSprite);
				    	characterSprite.setBounds(character.x, character.y, ML*1, ML*1);
				    	mainPanel.revalidate();
				    	mainPanel.repaint();
						going = false;
					}
				}
			}
			else if ((k.getKeyCode() == keyArray.get(9) || k.getKeyCode() == keyArray.get(10) || k.getKeyCode() == keyArray.get(11)) && !paused && !going) {
				if (buttonPaused) {
					menuSelect("DOWN");
				}
				else {
					Boolean allowed = true;
					going = true;
					saved = false;
					character.setDirection("DOWN");
			    	for (int i = 0; i < thingArray.size(); i++) {
			    		if (character.x == thingArray.get(i).x && character.y + ML*1 == thingArray.get(i).y) {
			    			allowed = false;
			    		}
			    	}
					if (allowed) {
						timer.start();
					}
					else {
						character.setImageIcon(BASE_RESOURCE_PATH + "Characters\\" + character.month + character.direction.substring(0,  1) + character.step + ".png");
				    	mainPanel.remove(characterSprite);
						characterSprite = new JLabel(character.imageIcon);
				    	mainPanel.setLayer(characterSprite, 1);
				    	mainPanel.add(characterSprite);
				    	characterSprite.setBounds(character.x, character.y, ML*1, ML*1);
				    	mainPanel.revalidate();
				    	mainPanel.repaint();
						going = false;
					}
				}
			}
			else if ((k.getKeyCode() == keyArray.get(36) || k.getKeyCode() == keyArray.get(37) || k.getKeyCode() == keyArray.get(38))) {
				saved = false;
				if (timerRun == 80) {
					timerRun = 20;
				}
				else {
					timerRun = 80;
				}
				timer.setDelay(timerRun);
			}
			//PAUSE
			else if ((k.getKeyCode() == keyArray.get(15) || k.getKeyCode() == keyArray.get(16) || k.getKeyCode() == keyArray.get(17)) && !going && !paused) {
				if (buttonPaused) {
					resumeMusic();
			    	menuPanel.setBackground(currentZone.getMenuColor());
			    	mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			    	mainPanel.remove(pausePanel);
					buttonPaused = false;
					repaint();
				}
				else {
					pauseMusic();
			    	menuPanel.setBackground(new Color(
			    			(int) (currentZone.getMenuColor().getRed()/1.2), 
			    			(int) (currentZone.getMenuColor().getGreen()/1.2), 
			    			(int) (currentZone.getMenuColor().getBlue()/1.2)));
			    	mainPanel.setBorder(BorderFactory.createLineBorder(new Color(127, 0, 63), 1));
			    	mainPanel.add(pausePanel);
					buttonPaused = true;
					repaint();
				}
			}
			//ACTION
			else if ((k.getKeyCode() == keyArray.get(12) || k.getKeyCode() == keyArray.get(13) || k.getKeyCode() == keyArray.get(14)) && !going) {
				if (buttonPaused) {
					menuPress();
				}
				else {
					saved = false;
					if (!paused && talk == 0) {
						int actX = character.x;
						int actY = character.y;
						if (character.direction.equals("LEFT")) {actX -= ML*1;}
						else if (character.direction.equals("RIGHT")) {actX += ML*1;}
						else if (character.direction.equals("UP")) {actY -= ML*1;}
						else if (character.direction.equals("DOWN")) {actY += ML*1;}
						for (int i = 0; i < thingArray.size(); i++) {
							Thing nowThing = thingArray.get(i);
							if (actX == nowThing.x && actY == nowThing.y) {
								switch (nowThing.action) {
								case 0:
									break;
								case 1:
								case 2:
									paused = true;
									if (nowThing.type.equals("Objects")) {
									}
									else if (character.direction.equals("RIGHT")) {
										thingArray.get(i).setDirection("LEFT");
									}
									else if (character.direction.equals("LEFT")) {
										thingArray.get(i).setDirection("RIGHT");
									}
									else if (character.direction.equals("DOWN")) {
										thingArray.get(i).setDirection("UP");
									}
									else {
										thingArray.get(i).setDirection("DOWN");
									}
									if (nowThing.type.equals("Objects")) {
										nowThing.setImageIcon(BASE_RESOURCE_PATH + "" + nowThing.type + "\\" + nowThing.name + nowThing.step + ".png");
									}
									else {
										nowThing.setImageIcon(BASE_RESOURCE_PATH + "" + nowThing.type + "\\" + nowThing.name + nowThing.direction.substring(0,  1) + nowThing.step + ".png");
									}
							    	mainPanel.remove(thingSpriteArray.get(i));
							    	thingSpriteArray.set(i, new JLabel(nowThing.imageIcon));
							    	mainPanel.add(thingSpriteArray.get(i));
							    	thingSpriteArray.get(i).setBounds(nowThing.x, nowThing.y, ML*1, ML*1);
							    	mainPanel.revalidate();
							    	mainPanel.repaint();
							    	talk = nowThing.misc.size();
							    	if (talk > 0) {
							    		drawSpeechBox(nowThing);
							    		ArrayList<String> nowThingMiscClone = new ArrayList<String>();
							    		nowThingMiscClone.addAll(nowThing.misc);
							    		savedThing = new Thing(nowThing.name, nowThing.type, nowThing.direction, nowThing.x, nowThing.y, nowThing.step, nowThing.action, nowThingMiscClone);
							    		
							    		nowThing.setFlag(nowThing.flag + 1);
							    	}
									break;
								}
							}
						}
					}
					else if(paused && talk >= 1) {
						mainPanel.remove(speechBox);
						mainPanel.remove(speech);
				    	mainPanel.revalidate();
				    	mainPanel.repaint();
						talk -= 1;
						if (talk == 0) {
					    	savedThing = null;
							paused = false;
						}
						else {
							savedThing.misc.remove(0);
							drawSpeechBox(savedThing);
						}
					}
				}
			}
		}
		else if (keyPaused) {
			
		}
	}
	
	@Override
	public void keyReleased(KeyEvent k) {
	}
	
	@Override
	public void keyTyped(KeyEvent k) {
	}
	
	public void drawSpeechBox(Thing nowThing) {
		path = new GeneralPath();
		speech = new JLabel(nowThing.misc.get(0));
    	speech.setFont(new Font("Arial", Font.PLAIN, 12));
    	int speechLines = (nowThing.misc.get(0).length()/12) * 2;
		if (nowThing.x <= ML*19 && nowThing.y >= ML*3) {
			speech.setBounds(nowThing.x + ML + ML/8, nowThing.y - ML/4 + (-speechLines+2)*4, ML*5 - ML/4, ML+speechLines*4);
			if (nowThing.action == 1) {
				path.moveTo(0, 0);
				path.lineTo(ML*5, 0);
				path.lineTo(ML*5, speechLines*5);
				path.lineTo(0, speechLines*5);
				path.lineTo(0, 0);
				speechBox = new SpeechBox(path);
    			speechBox.setBounds(nowThing.x + ML, nowThing.y - ML/4 + (-speechLines+2)*4, ML*6, ML+speechLines*4);
			}
			else {
				path.moveTo(ML + 6, 0);
				path.lineTo(ML*6 - 6, 0);
				path.curveTo(ML*6 - 6, 0, ML*6 - 3, 3, ML*6, 6);
				path.lineTo(ML*6, speechLines*4 - 6);
				path.curveTo(ML*6, speechLines*4 - 6, ML*6 - 3, speechLines*4 - 3, ML*6 - 6, speechLines*4);
				path.lineTo(ML + 3, speechLines*4);
				path.lineTo(ML - 6, speechLines*4 + 6);
				path.lineTo(ML, speechLines*4 - 3);
				path.lineTo(ML, 6);
				path.curveTo(ML, 6, ML + 3, 3, ML + 6, 0);
				path.lineTo(ML + 6, 0);
				speechBox = new SpeechBox(path);
    			speechBox.setBounds(nowThing.x, nowThing.y - ML/4 + (-speechLines+2)*4, ML*6, ML+speechLines*4);
			}
		}
		else if (nowThing.x <= ML*19 && nowThing.y < ML*3) {
			speech.setBounds(nowThing.x + ML + ML/8, nowThing.y + ML, ML*5 - ML/4, ML+speechLines*4);
			if (nowThing.action == 1) {
				path.moveTo(0, ML);
				path.lineTo(ML*5, ML);
				path.lineTo(ML*5, ML+speechLines*5);
				path.lineTo(0, ML+speechLines*5);
				path.lineTo(0, ML);
				speechBox = new SpeechBox(path);
    			speechBox.setBounds(nowThing.x + ML, nowThing.y, ML*6, ML+speechLines*5);
			}
			else {
				path.moveTo(ML + 6, ML);
				path.lineTo(ML*6 - 6, ML);
				path.curveTo(ML*6 - 6, ML, ML*6 - 3, ML + 3, ML*6, ML + 6);
				path.lineTo(ML*6, ML+speechLines*4 - 6);
				path.curveTo(ML*6, ML+speechLines*4 - 6, ML*6 - 3, ML+speechLines*4 - 3, ML*6 - 6, ML+speechLines*4);
				path.lineTo(ML + 3, ML+speechLines*4);
				path.curveTo(ML + 6, ML+speechLines*4, ML + 3, ML+speechLines*4 - 3, ML, ML+speechLines*4 - 6);
				path.lineTo(ML, ML + 3);
				path.lineTo(ML - 6, ML - 6);
				path.lineTo(ML + 3, ML);
				path.lineTo(ML + 6, ML);
				speechBox = new SpeechBox(path);
    			speechBox.setBounds(nowThing.x, nowThing.y, ML*6, ML+speechLines*5);
			}
		}
		else if (nowThing.x > ML*19 && nowThing.y < ML*3) {
			speech.setBounds(nowThing.x - ML*5 + ML/8, nowThing.y + ML, ML*5 - ML/4, ML+speechLines*4);
			if (nowThing.action == 1) {
				path.moveTo(0, ML);
				path.lineTo(ML*5, ML);
				path.lineTo(ML*5, ML+speechLines*5);
				path.lineTo(0, ML+speechLines*5);
				path.lineTo(0, ML);
				speechBox = new SpeechBox(path);
    			speechBox.setBounds(nowThing.x - ML*5, nowThing.y, ML*6, ML+speechLines*5);
			}
			else {
				path.moveTo(6, ML);
				path.lineTo(ML*5 - 3, ML);
				path.lineTo(ML*5 + 6, ML - 6);
				path.lineTo(ML*5, ML + 3);
				path.lineTo(ML*5, ML+speechLines*4 - 6);
				path.curveTo(ML*5, ML+speechLines*4 - 6, ML*5 - 3, ML+speechLines*4 - 3, ML*5 - 6, ML+speechLines*4);
				path.lineTo(3, ML+speechLines*4);
				path.curveTo(6, ML+speechLines*4, 3, ML+speechLines*4 - 3, 0, ML+speechLines*4 - 6);
				path.lineTo(0, ML + 6);
				path.curveTo(0, ML + 6, 3, ML + 3, 6, ML);
				path.lineTo(6, ML);
				speechBox = new SpeechBox(path);
    			speechBox.setBounds(nowThing.x - ML*5, nowThing.y, ML*6, ML+speechLines*5);
			}
		}
		else if (nowThing.x > ML*19 && nowThing.y >= ML*3) {
			speech.setBounds(nowThing.x - ML*5 + ML/8, nowThing.y - ML/4 + (-speechLines+2)*4, ML*5 - ML/4, ML+speechLines*4);
			if (nowThing.action == 1) {
				path.moveTo(0, 0);
				path.lineTo(ML*5, 0);
				path.lineTo(ML*5, speechLines*5);
				path.lineTo(0, speechLines*5);
				path.lineTo(0, 0);
				speechBox = new SpeechBox(path);
    			speechBox.setBounds(nowThing.x - ML*5, nowThing.y - ML/4 + (-speechLines+2)*4, ML*6, ML+speechLines*4);
			}
			else {
				path.moveTo(ML + 6, 0);
				path.lineTo(ML*6 - 6, 0);
				path.curveTo(ML*6 - 6, 0, ML*6 - 3, 3, ML*6, 6);
				path.lineTo(ML*6, speechLines*4 - 3);
				path.lineTo(ML*6 + 6, speechLines*4 + 6);
				path.lineTo(ML*6 - 3, speechLines*4);
				path.lineTo(ML + 3, speechLines*4);
				path.curveTo(ML + 6, speechLines*4, ML + 3, speechLines*4 - 3, ML, speechLines*4 - 6);
				path.lineTo(ML, 6);
				path.curveTo(ML, 6, ML + 3, 3, ML + 6, 0);
				path.lineTo(ML + 6, 0);
				speechBox = new SpeechBox(path);
    			speechBox.setBounds(nowThing.x - ML*6, nowThing.y - ML/4 + (-speechLines+2)*4, ML*7, ML*2+speechLines*4);
			}
		}
    	speech.setVerticalAlignment(JLabel.TOP);
    	mainPanel.setLayer(speechBox, 2);
    	mainPanel.setLayer(speech, 3);
		mainPanel.add(speechBox);
		mainPanel.add(speech);
    	//mainPanel.revalidate();
    	//mainPanel.repaint();
	}

	public void setValueSkip() {
		superPaused = true;
		Object input = JOptionPane.showInputDialog(frame, "Input integer for value skip (max: 999):", "Value skip", JOptionPane.PLAIN_MESSAGE, null, null, valueSkip);

		if (input != null && input.toString().matches("\\d?\\d?\\d")) {
			playSound(BASE_RESOURCE_PATH + "Sounds\\MenuSelect.wav");
			valueSkip = Integer.valueOf(input.toString());
			saved = false;
		}
		else {
			playSound(BASE_RESOURCE_PATH + "Sounds\\MenuBack.wav");
		}
		superPaused = false;
	}
	
	public void setControls() {
		superPaused = true;
		ArrayList<Integer> keyArrayClone = new ArrayList<Integer>(keyArray);
		ButtonsPane buttonsPane = new ButtonsPane(keyArrayClone);
		if (buttonsPane.confirmation) {
			playSound(BASE_RESOURCE_PATH + "Sounds\\MenuSelect.wav");
			keyArray = new ArrayList<Integer>(buttonsPane.keyArray);
			saved = false;
		}
		else {
			playSound(BASE_RESOURCE_PATH + "Sounds\\MenuBack.wav");
		}
		superPaused = false;
	}

	public void setBattleAnimations() {
		superPaused = true;
		Object[] choices = {"On", "Off"};
		Object defaultChoice;
		if (animation) {
			defaultChoice = choices[0];
		}
		else {
			defaultChoice = choices[1];
		}
		int choice = JOptionPane.showOptionDialog(frame, "Enable animations in battle:", "Battle animations", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, choices, defaultChoice);
		if (choice == JOptionPane.YES_OPTION) {
			playSound(BASE_RESOURCE_PATH + "Sounds\\MenuSelect.wav");
			animation = true;
			saved = false;
		}
		else if (choice == JOptionPane.NO_OPTION) {
			playSound(BASE_RESOURCE_PATH + "Sounds\\MenuSelect.wav");
			animation = false;
			saved = false;
		}
		else {
			playSound(BASE_RESOURCE_PATH + "Sounds\\MenuBack.wav");
		}
		superPaused = false;
	}
	
	public void setAutosave() {
		superPaused = true;
		Object[] choices = {"On", "Off"};
		Object defaultChoice;
		if (autosave) {
			defaultChoice = choices[0];
		}
		else {
			defaultChoice = choices[1];
		}
		int choice = JOptionPane.showOptionDialog(frame, "Quick-save and automatically save when quitting:", "Autosave", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, choices, defaultChoice);
		if (choice == JOptionPane.YES_OPTION) {
			File autoFile = new File("File\\Save" + currentFile + ".jsmn");
	        if (autoFile.exists()) {
	        	String autoFileName = "New file\t";
	        	try (BufferedReader bufferedReader = new BufferedReader(new FileReader("File\\Save" + currentFile + ".jsmn"))) {
	        		autoFileName = bufferedReader.readLine();
	    			bufferedReader.close();
	    		} catch (FileNotFoundException e) {
	    			e.printStackTrace();
	    		} catch (IOException e1) {
	    			e1.printStackTrace();
	    		}
	        	if (!autoFileName.equals("New file\t")) {
	    			playSound(BASE_RESOURCE_PATH + "Sounds\\MenuSelect.wav");
		        	autosave = true;
	        	}
	        	else {
	    			playSound(BASE_RESOURCE_PATH + "Sounds\\Error.wav");
	        		JOptionPane.showMessageDialog(frame, "Autosave requires a saved file.", "Autosave", JOptionPane.ERROR_MESSAGE);
	        	}
	        }
	        else {
    			playSound(BASE_RESOURCE_PATH + "Sounds\\Error.wav");
	        	JOptionPane.showMessageDialog(frame, "Autosave requires a saved file.", "Autosave", JOptionPane.ERROR_MESSAGE);
	        }
			saved = false;
		}
		else if (choice == JOptionPane.NO_OPTION) {
			playSound(BASE_RESOURCE_PATH + "Sounds\\MenuSelect.wav");
			autosave = false;
			saved = false;
		}
		else {
			playSound(BASE_RESOURCE_PATH + "Sounds\\MenuBack.wav");
		}
		superPaused = false;
	}
	
	public void playSound(String filepath) {
		try {
			SoundEffect.play(filepath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void playMusic(String filepath) {
		try {
			Music.play(filepath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void pauseMusic() {
		try {
			Music.pause();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void resumeMusic() {
		try {
			Music.resume();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void musicVolume(float value) {
		try {
			Music.changeVolume(value);;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void chooseMonth() {
		String monthChoice;
		switch(menuButton) {
		default:
			monthChoice = "Marx";
			break;
		case (102):
			monthChoice = "April";
			break;
		case (103):
			monthChoice = "Maia";
			break;
		case (104):
			monthChoice = "Juno";
			break;
		case (105):
			monthChoice = "Julia";
			break;
		case (106):
			monthChoice = "Augustus";
			break;
		case (107):
			monthChoice = "Septembra";
			break;
		case (108):
			monthChoice = "Octobelle";
			break;
		case (109):
			monthChoice = "November";
			break;
		case (110):
			monthChoice = "Decembus";
			break;
		case (111):
			monthChoice = "Janus";
			break;
		case (112):
			monthChoice = "Februa";
			break;
		}
		Object nameInput = JOptionPane.showInputDialog(frame, "Input a name:", "Character", JOptionPane.PLAIN_MESSAGE, null, null, monthChoice);
		
		if (nameInput == null) {
			playSound(BASE_RESOURCE_PATH + "Sounds\\MenuBack.wav");
		}
		else {
			String nameString = (String) nameInput;
			character = new Character(nameString, monthChoice, "DOWN", ML*1, ML*1, 1);
			characterSprite = new JLabel(character.imageIcon);
			playSound(BASE_RESOURCE_PATH + "Sounds\\MenuSelect.wav");
        	mainPanel.remove(dataPanel);
        	mainPanel.repaint(); 
			for (JLabel menuLabel : menuArray100) {
				menuLabel.setVisible(false);
			}
			for (JLabel menuLabel : menuArray0) {
				menuLabel.setVisible(true);
			}
			partyMemberArray.add(new Member(new Fae(menuButton), nameString, 0, 1)); //TODO expand upon?
			partyMemberArray.get(0).setMainCharacter();
			menuButton = 1;
        	character.setX(ML*12);
        	character.setY(ML*12);
			setZone(2);
			loadArea();
			superPaused = false;
		}
	}
	
	public void saveGame() {
		superPaused = true;
		
		if (currentFile == 0) {
			currentFile = 1;
		}
		if (autosave) {
			File autoFile = new File("File\\Save" + currentFile + ".jsmn");
	        if (autoFile.exists()) {
	        	String autoFileName = "New file\t";
	        	try (BufferedReader bufferedReader = new BufferedReader(new FileReader("File\\Save" + currentFile + ".jsmn"))) {
	        		autoFileName = bufferedReader.readLine();
	    			bufferedReader.close();
	    		} catch (FileNotFoundException e) {
	    			e.printStackTrace();
	    		} catch (IOException e1) {
	    			e1.printStackTrace();
	    		}
	        	if (!autoFileName.equals("New file\t")) {
	        		currentFileName = autoFileName;
					writeFile();
					saved = true;
	        	}
	        	else {
	    			playSound(BASE_RESOURCE_PATH + "Sounds\\Error.wav");
	        		JOptionPane.showMessageDialog(frame, "Autosave was thwarted by outside forces.", "Save", JOptionPane.ERROR_MESSAGE);
	        		autosave = false;
	        		saveGame();
	        	}
	        }
	        else {
    			playSound(BASE_RESOURCE_PATH + "Sounds\\Error.wav");
	        	JOptionPane.showMessageDialog(frame, "Autosave was thwarted by outside forces.", "Save", JOptionPane.ERROR_MESSAGE);
	        	autosave = false;
        		saveGame();
	        }
		}
		else {
			File file1 = new File("File\\Save1.jsmn");
			File file2 = new File("File\\Save2.jsmn");
			File file3 = new File("File\\Save3.jsmn");
			File file4 = new File("File\\Save4.jsmn");
			String file1Name = ""; 
			String file2Name = ""; 
			String file3Name = ""; 
			String file4Name = "";
			String newCurrentFileName = "";
	        if (!file1.exists()) {
	        	try {
					file1.createNewFile();
					FileWriter fileWriter = new FileWriter("File\\Save1.jsmn");
					fileWriter.write("New file\t");
					fileWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
	        if (!file2.exists()) {
	        	try {
					file2.createNewFile();
					FileWriter fileWriter = new FileWriter("File\\Save2.jsmn");
					fileWriter.write("New file\t");
					fileWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
	        if (!file3.exists()) {
	        	try {
					file3.createNewFile();
					FileWriter fileWriter = new FileWriter("File\\Save3.jsmn");
					fileWriter.write("New file\t");
					fileWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
	        if (!file4.exists()) {
	        	try {
					file4.createNewFile();
					FileWriter fileWriter = new FileWriter("File\\Save4.jsmn");
					fileWriter.write("New file\t");
					fileWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
	    	try (BufferedReader bufferedReader = new BufferedReader(new FileReader("File\\Save1.jsmn"))) {
	    		file1Name = bufferedReader.readLine();
				bufferedReader.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	    	try (BufferedReader bufferedReader = new BufferedReader(new FileReader("File\\Save2.jsmn"))) {
	    		file2Name = bufferedReader.readLine();
				bufferedReader.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	    	try (BufferedReader bufferedReader = new BufferedReader(new FileReader("File\\Save3.jsmn"))) {
	    		file3Name = bufferedReader.readLine();
				bufferedReader.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	    	try (BufferedReader bufferedReader = new BufferedReader(new FileReader("File\\Save4.jsmn"))) {
	    		file4Name = bufferedReader.readLine();
				bufferedReader.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	    	
			Object[] saveFiles = {"1: " + file1Name, "2: " + file2Name, "3: " + file3Name, "4: " + file4Name};
			Object choiceObject = JOptionPane.showInputDialog(frame, "Choose a file:", "Save", JOptionPane.PLAIN_MESSAGE, null, saveFiles, saveFiles[currentFile - 1]);
			
			if (choiceObject == null) {
				playSound(BASE_RESOURCE_PATH + "Sounds\\MenuBack.wav");
			}
			else {
				String choice = (String) choiceObject;
				int currentFileCopy = currentFile;

				playSound(BASE_RESOURCE_PATH + "Sounds\\MenuSelect.wav");
				if (choice.equals("4: " + file4Name)) {
					currentFileCopy = 4;
					newCurrentFileName = file4Name;
				}
				else if (choice.equals("3: " + file3Name)) {
					currentFileCopy = 3;
					newCurrentFileName = file3Name;
				}
				else if (choice.equals("2: " + file2Name)) {
					currentFileCopy = 2;
					newCurrentFileName = file2Name;
				}
				else if (choice.equals("1: " + file1Name)) {
					currentFileCopy = 1;
					newCurrentFileName = file1Name;
				}
	
				if (newCurrentFileName.equals("New file\t")) {
					Object currentFileNameObject = JOptionPane.showInputDialog(frame, "Input file name:", "Save file " + currentFileCopy, JOptionPane.PLAIN_MESSAGE, null, null, character.name);
					
					if (currentFileNameObject == null) {
						playSound(BASE_RESOURCE_PATH + "Sounds\\MenuBack.wav");
					}
					else {
						newCurrentFileName = (String) currentFileNameObject;
						currentFileName = newCurrentFileName;
						currentFile = currentFileCopy;
						writeFile();
						saved = true;
					}
				}
				else {
					Object[] choice2 = {"Yes", "No"};
					Object choice2Object = JOptionPane.showOptionDialog(frame, "Overwrite this file?", "Save file " + currentFileCopy, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, choice2, choice2[0]);
					
					if ((int) choice2Object != JOptionPane.YES_OPTION) {
						playSound(BASE_RESOURCE_PATH + "Sounds\\MenuBack.wav");
					}
					else {
						currentFileName = newCurrentFileName;
						currentFile = currentFileCopy;
						writeFile();
						saved = true;
					}
				}
			}
		}
		superPaused = false;
	}
	
	public void writeFile() {
		Charset utf8 = StandardCharsets.UTF_8;
		List<String> lines = Arrays.asList(
			//Name 0
			currentFileName,
			//Bindings 2 - 41
			"\tde3vwbc3nodest\t",
			""+keyArray.get(0), ""+keyArray.get(1), ""+keyArray.get(2), ""+keyArray.get(3), ""+keyArray.get(4), ""+keyArray.get(5), 
			""+keyArray.get(6), ""+keyArray.get(7), ""+keyArray.get(8), ""+keyArray.get(9), ""+keyArray.get(10), ""+keyArray.get(11), 
			""+keyArray.get(12), ""+keyArray.get(13), ""+keyArray.get(14), ""+keyArray.get(15), ""+keyArray.get(16), ""+keyArray.get(17), 
			""+keyArray.get(18), ""+keyArray.get(19), ""+keyArray.get(20), ""+keyArray.get(21), ""+keyArray.get(22), ""+keyArray.get(23), 
			""+keyArray.get(24), ""+keyArray.get(25), ""+keyArray.get(26), ""+keyArray.get(27), ""+keyArray.get(28), ""+keyArray.get(29), 
			""+keyArray.get(30), ""+keyArray.get(31), ""+keyArray.get(32), ""+keyArray.get(33), ""+keyArray.get(34), ""+keyArray.get(35), 
			""+keyArray.get(36), ""+keyArray.get(37), ""+keyArray.get(38),
			//Misc 43 - 46
			"\tde3vwmn3stcd\t",
			""+valueSkip, ""+autosave, ""+animation, ""+currentFile, 
			//Character
			"\tde3vwcdhi1rs\t",
			character.name, character.month, character.direction, ""+character.x, ""+character.y, ""+character.step,
			//Current Zone
			"\tde3vwcd5rsrs2notu\t",
			""+currentZone.id, 
			//Party
			"\tde3vwpq1rstuyz\t",
			//Inventory
			"\tde3vw3novw2notu4rsyz\t",
			//Equipment
			"\tde3vw2qr53pqmn2notu\t",
			//Items
			"\tde3vw3tu2mnst\t",
			//Pacts
			"\tde3vwpq1cdtust\t",
			//Zone Data
			"\tde3vwza4no2st\t",
			//Fae Registry
			"\tde3vwfg13\t",
			//End
			"\t2node\t"
			); //TODO finish saving all things
		playSound(BASE_RESOURCE_PATH + "Sounds\\Save.wav");
		try {
		    Files.write(Paths.get("File\\Save" + currentFile + ".jsmn"), lines, utf8);
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	public void loadGame() {
		superPaused = true;
		
		File file1 = new File("File\\Save1.jsmn");
		File file2 = new File("File\\Save2.jsmn");
		File file3 = new File("File\\Save3.jsmn");
		File file4 = new File("File\\Save4.jsmn");
		String file1Name = ""; 
		String file2Name = ""; 
		String file3Name = ""; 
		String file4Name = "";
		String currentFileName = "";

		if (currentFile == 0) {
			currentFile = 1;
		}
        if (!file1.exists()) {
        	try {
				file1.createNewFile();
				FileWriter fileWriter = new FileWriter("File\\Save1.jsmn");
				fileWriter.write("New file\t");
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        if (!file2.exists()) {
        	try {
				file2.createNewFile();
				FileWriter fileWriter = new FileWriter("File\\Save2.jsmn");
				fileWriter.write("New file\t");
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        if (!file3.exists()) {
        	try {
				file3.createNewFile();
				FileWriter fileWriter = new FileWriter("File\\Save3.jsmn");
				fileWriter.write("New file\t");
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        if (!file4.exists()) {
        	try {
				file4.createNewFile();
				FileWriter fileWriter = new FileWriter("File\\Save4.jsmn");
				fileWriter.write("New file\t");
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
    	try (BufferedReader bufferedReader = new BufferedReader(new FileReader("File\\Save1.jsmn"))) {
    		file1Name = bufferedReader.readLine();
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	try (BufferedReader bufferedReader = new BufferedReader(new FileReader("File\\Save2.jsmn"))) {
    		file2Name = bufferedReader.readLine();
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	try (BufferedReader bufferedReader = new BufferedReader(new FileReader("File\\Save3.jsmn"))) {
    		file3Name = bufferedReader.readLine();
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	try (BufferedReader bufferedReader = new BufferedReader(new FileReader("File\\Save4.jsmn"))) {
    		file4Name = bufferedReader.readLine();
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	
		Object[] loadFiles = {"1: " + file1Name, "2: " + file2Name, "3: " + file3Name, "4: " + file4Name};
		Object choiceObject = JOptionPane.showInputDialog(frame, "Choose a file:", "Load", JOptionPane.PLAIN_MESSAGE, null, loadFiles, loadFiles[currentFile - 1]);
		
		if (choiceObject == null) {
			playSound(BASE_RESOURCE_PATH + "Sounds\\MenuBack.wav");
		}
		else {
			String choice = (String) choiceObject;
			int currentFileCopy = currentFile;

			playSound(BASE_RESOURCE_PATH + "Sounds\\MenuSelect.wav");
			if (choice.equals("4: " + file4Name)) {
				currentFileCopy = 4;
				currentFileName = file4Name;
			}
			else if (choice.equals("3: " + file3Name)) {
				currentFileCopy = 3;
				currentFileName = file3Name;
			}
			else if (choice.equals("2: " + file2Name)) {
				currentFileCopy = 2;
				currentFileName = file2Name;
			}
			else if (choice.equals("1: " + file1Name)) {
				currentFileCopy = 1;
				currentFileName = file1Name;
			}

			if (currentFileName.equals("New file\t")) {
				playSound(BASE_RESOURCE_PATH + "Sounds\\Error.wav");
				JOptionPane.showMessageDialog(frame, "Cannot load a new file.", "Load file", JOptionPane.ERROR_MESSAGE);
			}
			else {
				Object[] choice2 = {"Yes", "No"};
				Object choice2Object = JOptionPane.showOptionDialog(frame, "Load this file?", "Load file " + currentFileCopy, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, choice2, choice2[0]);
				
				if ((int) choice2Object != JOptionPane.YES_OPTION) {
					playSound(BASE_RESOURCE_PATH + "Sounds\\MenuBack.wav");
				}
				else if (!saved && !autosave) {
					Object[] innerChoice = {"Go back", "Load anyway"};
					int n = JOptionPane.showOptionDialog(frame, "File has not been saved.", "Load warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, innerChoice, innerChoice[0]);
					if (n == JOptionPane.NO_OPTION) {
						if (menuButton == 122) {
							for (JLabel menuLabel : menuArray120) {
								menuLabel.setVisible(false);
								menuPanel.remove(menuLabel);
							}
							for (JLabel menuLabel : menuArray0) {
								menuLabel.setVisible(true);
							}
						}
						playSound(BASE_RESOURCE_PATH + "Sounds\\Load.wav");
						pauseMusic();
						readFile("File\\Save" + currentFileCopy + ".jsmn");
						currentFile = currentFileCopy;
						populateZones(); //TODO modify this segment?
						setZone(currentZone.id);
						loadArea();
						menuButton = 1;
						saved = true;
						character.setImageIcon(BASE_RESOURCE_PATH + "Characters\\" + character.month + character.direction.substring(0,  1) + character.step + ".png");
				    	mainPanel.remove(characterSprite);
						characterSprite = new JLabel(character.imageIcon);
				    	mainPanel.add(characterSprite);
				    	characterSprite.setBounds(character.x, character.y, ML*1, ML*1);
				    	mainPanel.revalidate();
				    	mainPanel.repaint();
					}
				}
				else {
					if (menuButton == 122) {
						for (JLabel menuLabel : menuArray120) {
							menuLabel.setVisible(false);
							menuPanel.remove(menuLabel);
						}
						for (JLabel menuLabel : menuArray0) {
							menuLabel.setVisible(true);
						}
					}
					playSound(BASE_RESOURCE_PATH + "Sounds\\Load.wav");
					pauseMusic();
					readFile("File\\Save" + currentFileCopy + ".jsmn");
					currentFile = currentFileCopy;
					populateZones(); //TODO modify this segment?
					setZone(currentZone.id);
					loadArea();
					menuButton = 1;
					saved = true;
					character.setImageIcon(BASE_RESOURCE_PATH + "Characters\\" + character.month + character.direction.substring(0,  1) + character.step + ".png");
			    	mainPanel.remove(characterSprite);
					characterSprite = new JLabel(character.imageIcon);
			    	mainPanel.add(characterSprite);
			    	characterSprite.setBounds(character.x, character.y, ML*1, ML*1);
			    	mainPanel.revalidate();
			    	mainPanel.repaint();
				}
			}
		}
		superPaused = false;
	}

	public void readFile(String currentFileId) {
		ArrayList<Integer> newKeyArray = new ArrayList<Integer>();
		if (autosave) {
			saveGame();
		}
		String line;
    	try (BufferedReader bufferedReader = new BufferedReader(new FileReader(currentFileId))) {
    		line = bufferedReader.readLine();
    		int progress = 0;
    		int overallProgress = 0;
    		while (!line.equals("\t2node\t")) {
    			//Bindings
    			if (overallProgress == 0 && line.equals("\tde3vwbc3nodest\t")) {
    				overallProgress = 2;
    				progress = 0;
    			}
    			//Misc
    			else if (overallProgress == 2 && line.equals("\tde3vwmn3stcd\t")) {
    				overallProgress = 3;
    				progress = 0;
    			}
    			//Character
    			else if (overallProgress == 3 && line.equals("\tde3vwcdhi1rs\t")) {
    				overallProgress = 4;
    				progress = 0;
    			}
    			//Current Zone
    			else if (overallProgress == 4 && line.equals("\tde3vwcd5rsrs2notu\t")) {
    				overallProgress = 5;
    				progress = 0;
    			}
    			//Party
    			else if (overallProgress == 5 && line.equals("\tde3vwpq1rstuyz\t")) {
    				overallProgress = 6;
    				progress = 0;
    			}
    			//Equipment
    			else if (overallProgress == 6 && line.equals("\tde3vw2qr53pqmn2notu\t")) {
    				overallProgress = 7;
    				progress = 0;
    			}
    			//Items
    			else if (overallProgress == 8 && line.equals("\tde3vw3tu2mnst\t")) {
    				overallProgress = 8;
    				progress = 0;
    			}
    			//Pacts
    			else if (overallProgress == 7 && line.equals("\tde3vwpq1cdtust\t")) {
    				overallProgress = 9;
    				progress = 0;
    			}
    			//Zone Data
    			else if (overallProgress == 9 && line.equals("\tde3vwza4no2st\t")) {
    				overallProgress = 10;
    				progress = 0;
    			}
    			//Fae Registry
    			else if (overallProgress == 10 && line.equals("\tde3vwfg13\t")) {
    				overallProgress = 11;
    				progress = 0;
    			}
    			else if (overallProgress == 2) {
    				newKeyArray.add(Integer.parseInt(line));
    				progress++;
    			}
    			else if (overallProgress == 3) {
    				switch (progress) {
    				case 0:
    					valueSkip = Integer.parseInt(line);
    					break;
    				case 1:
    					autosave = Boolean.parseBoolean(line);
    					break;
    				case 2:
    					animation = Boolean.parseBoolean(line);
    					break;
    				case 3:
    					currentFile = Integer.parseInt(line);
    					break;
    				}
    				progress++;
    			}
    			else if (overallProgress == 4) {
    				switch (progress) {
    				case 0:
    					character.setName(line);
    					break;
    				case 1:
    					character.setMonth(line);
    					break;
    				case 2:
    					character.setDirection(line);
    					break;
    				case 3:
    					character.setX(Integer.parseInt(line));
    					break;
    				case 4:
    					character.setY(Integer.parseInt(line));
    					break;
    				case 5:
    					character.setStep(Integer.parseInt(line));
    					break;
    				}
    				progress++;
    			}
    			else if (overallProgress == 5) {
    				switch (progress) {
    				case 0:
    					currentZone.setId(Integer.parseInt(line));
    					break;
    				}
    			}
    			line = bufferedReader.readLine();
    		}
    		keyArray = newKeyArray;
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void populateZones() {
		//INIT
		if (zoneArray.size() == 0) {
			for (int i = 0; i <= 3; i++) { //TODO zone amount
				zoneArray.add(new Zone(i));
			}
		}
	}
	
	public void setZone(int id) {
		currentZone = zoneArray.get(id);
	}
	
    public void loadArea() {
    	mainPanel.removeAll();
    	
    	menuPanel.setBackground(currentZone.getMenuColor());
    	mainPanel.setBackground(currentZone.getFieldColor());

    	thingArray = currentZone.thingArray;
    	falseThingArray = currentZone.falseThingArray;
		playMusic(currentZone.getAreaMusic());
    	switch (currentZone.id) {
    	case 0:
    		superPaused = true;
    		saved = true;
    		menuButton = 121;
			for (JLabel menuLabel : menuArray0) {
				menuLabel.setVisible(false);
			}
			for (JLabel menuLabel : menuArray100) {
				menuLabel.setVisible(false);
			}
			for (JLabel menuLabel : menuArray120) {
				menuLabel.setVisible(true);
			}
			menuSet();
    		break;
    	case 1:
    		superPaused = true;
    		saved = true;
			menuButton = 101;
			for (JLabel menuLabel : menuArray0) {
				menuLabel.setVisible(false);
			}
			for (JLabel menuLabel : menuArray100) {
				menuLabel.setVisible(true);
			}
			mainPanel.add(dataPanel);
			menuSet();
        	break;
    	default:
    		saved = false;
    		mainPanel.add(characterSprite);
        	characterSprite.setBounds(character.x,character.y,ML*1,ML*1);
			menuSet();
        	break;
    	}
    	for (int i = 0; i < thingArray.size(); i++) {
    		thingSpriteArray.add(new JLabel(thingArray.get(i).imageIcon));
	    	mainPanel.setLayer(thingSpriteArray.get(i), 1);
    		mainPanel.add(thingSpriteArray.get(i));
    		thingSpriteArray.get(i).setBounds(thingArray.get(i).x,thingArray.get(i).y,ML*1,ML*1);
    	}
    	for (int i = 0; i < falseThingArray.size(); i++) {
    		falseThingSpriteArray.add(new JLabel(falseThingArray.get(i).imageIcon));
    		mainPanel.setLayer(falseThingSpriteArray.get(i), 0);
    		mainPanel.add(falseThingSpriteArray.get(i));
    		falseThingSpriteArray.get(i).setBounds(falseThingArray.get(i).x,falseThingArray.get(i).y,ML*1,ML*1);
    	}
    }
}
