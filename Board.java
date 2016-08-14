import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
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
import javax.swing.Timer;

public class Board extends JFrame implements KeyListener{
	static final int ML = 32;
	
	private JFrame frame = new JFrame("PACT");
	private JLayeredPane mainPanel = new JLayeredPane();
	private JLayeredPane menuPanel = new JLayeredPane();
	private ArrayList<JLabel> menuArray0 = new ArrayList<JLabel>();
	private JLabel menu1 = new JLabel("Party");
	private JLabel menu2 = new JLabel("Spells");
	private JLabel menu3 = new JLabel("Items");
	private JLabel menu4 = new JLabel("Pacts");
	private JLabel menu5 = new JLabel("Registry");
	private JLabel menu6 = new JLabel("File");
	private JLabel menu7 = new JLabel("Options");
	private JLabel menu8 = new JLabel("Quit");
	private ArrayList<JLabel> menuArray6 = new ArrayList<JLabel>();
	private JLabel menu60 = new JLabel("File");
	private JLabel menu61 = new JLabel("Save");
	private JLabel menu62 = new JLabel("Load");
	private JLabel menu63 = new JLabel("Back");
	private ArrayList<JLabel> menuArray7 = new ArrayList<JLabel>();
	private JLabel menu70 = new JLabel("Options");
	private JLabel menu71 = new JLabel("Value Skip");
	private JLabel menu72 = new JLabel("Controls");
	private JLabel menu73 = new JLabel("Battle Animations");
	private JLabel menu74 = new JLabel("Background Color");
	private JLabel menu75 = new JLabel("Back");
	private JPanel foregroundPanel = new JPanel();
	private String icon = "Data\\Objects\\Rock1.png"; 
	private Character character = new Character("?", "Februa", "DOWN", ML*1, ML*1, 1);
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
	private ArrayList<JLabel> thingSpriteArray = new ArrayList<JLabel>();
	private Thing savedThing;
	private ArrayList<Zone> zoneArray = new ArrayList<Zone>();
	private Zone currentZone;
	
	private Color background = new Color(255, 255, 255);
	private Boolean animation = true;
	
	private int valueSkip = 10;
	private int timerRun = 80;
	private int timerStep = 0;
	private int menuButton = 1;
	private int talk = 0;
	private int currentFile = 1;

	private int l1 = KeyEvent.VK_LEFT; private int l2 = KeyEvent.VK_A; private int l3 = KeyEvent.VK_NUMPAD4;
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
	private int su1 = KeyEvent.VK_CAPS_LOCK; private int su2 = KeyEvent.VK_NUM_LOCK; private int su3 = KeyEvent.VK_M;
	private ArrayList<Integer> keyArray = new ArrayList<Integer>(Arrays.asList(l1, l2, l3, r1, r2, r3, u1, u2, u3, d1, d2, d3, ac1, ac2, ac3, p1, p2, p3,
			mu1, mu2, mu3, md1, md2, md3, muu1, muu2, muu3, mdd1, mdd2, mdd3, ms1, ms2, ms3, mb1, mb2, mb3, su1, su2, su3));
	
    public Board() {
    	menuPanel.setPreferredSize(new Dimension(160, 400));
    	menuPanel.setLayout(null);
    	menuPanel.setBackground(new Color(224, 224, 224));
    	menuPanel.setOpaque(true);
    	
    	mainPanel.setPreferredSize(new Dimension(800, 800));
    	mainPanel.setLayout(null);
    	mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    	mainPanel.setBackground(background);
    	mainPanel.setOpaque(true);

    	foregroundPanel.setBounds(1, 1, 798, 798);
    	foregroundPanel.setLayout(null);
    	foregroundPanel.setBackground(new Color(255, 255, 255, 0));
    	foregroundPanel.setOpaque(false);
    	mainPanel.add(foregroundPanel);

    	menuArray0.addAll(Arrays.asList(menu1, menu2, menu3, menu4, menu5, menu6, menu7, menu8));
    	menuArray6.addAll(Arrays.asList(menu60, menu61, menu62, menu63));
    	menuArray7.addAll(Arrays.asList(menu70, menu71, menu72, menu73, menu74, menu75));
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
    	menu1.setFont(new Font("Arial", Font.BOLD, 15));
    	menu1.setForeground(Color.BLACK);
    	
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
				character.setImageIcon("Data\\Characters\\" + character.month + character.direction.substring(0,  1) + character.step + ".png");
		    	mainPanel.remove(characterSprite);
				characterSprite = new JLabel(character.imageIcon);
		    	mainPanel.add(characterSprite);
		    	characterSprite.setBounds(character.x, character.y, ML*1, ML*1);
		    	mainPanel.revalidate();
		    	mainPanel.repaint();
			}
		});
		timer.setInitialDelay(0);
    	
    	menu1.addMouseListener(new MouseAdapter() {
    		public void mouseClicked(MouseEvent c) {
    			if (!paused && menuButton >= 1 && menuButton <= 8 && !keyPaused && !superPaused) {
    				//SET DOUBLE CLICK
	    			menuButton = 1;
	    			menuSet();
    			}
    		}
    	});
    	
    	menu2.addMouseListener(new MouseAdapter() {
    		public void mouseClicked(MouseEvent c) {
    			if (!paused && menuButton >= 1 && menuButton <= 8 && !keyPaused && !superPaused) {
    				//SET DOUBLE CLICK
	    			menuButton = 2;
	    			menuSet();
    			}
    		}
    	});
    	
    	menu3.addMouseListener(new MouseAdapter() {
    		public void mouseClicked(MouseEvent c) {
    			if (!paused && menuButton >= 1 && menuButton <= 8 && !keyPaused && !superPaused) {
    				//SET DOUBLE CLICK
	    			menuButton = 3;
	    			menuSet();
    			}
    		}
    	});
    	
    	menu4.addMouseListener(new MouseAdapter() {
    		public void mouseClicked(MouseEvent c) {
    			if (!paused && menuButton >= 1 && menuButton <= 8 && !keyPaused && !superPaused) {
    				//SET DOUBLE CLICK
	    			menuButton = 4;
	    			menuSet();
    			}
    		}
    	});
    	
    	menu5.addMouseListener(new MouseAdapter() {
    		public void mouseClicked(MouseEvent c) {
    			if (!paused && menuButton >= 1 && menuButton <= 8 && !keyPaused && !superPaused) {
    				//SET DOUBLE CLICK
	    			menuButton = 5;
	    			menuSet();
    			}
    		}
    	});
    	
    	menu6.addMouseListener(new MouseAdapter() {
    		public void mouseClicked(MouseEvent c) {
    			if (!paused && menuButton >= 1 && menuButton <= 8 && !keyPaused && !superPaused) {
    				if (menuButton == 6) {
    					menuButton = 63;
    					for (JLabel menuLabel : menuArray0) {
    						menuLabel.setVisible(false);
    					}
    					for (JLabel menuLabel : menuArray6) {
    						menuLabel.setVisible(true);
    					}
    				}
    				else {
    					menuButton = 6;
    				}
	    			menuSet();
    			}
    		}
    	});

    	menu7.addMouseListener(new MouseAdapter() {
    		public void mouseClicked(MouseEvent c) {
    			if (!paused && menuButton >= 1 && menuButton <= 8 && !keyPaused && !superPaused) {
    				if (menuButton == 7) {
    					menuButton = 75;
    					for (JLabel menuLabel : menuArray0) {
    						menuLabel.setVisible(false);
    					}
    					for (JLabel menuLabel : menuArray7) {
    						menuLabel.setVisible(true);
    					}
    				}
    				else {
    					menuButton = 7;
    				}
	    			menuSet();
    			}
    		}
    	});

    	menu8.addMouseListener(new MouseAdapter() {
    		public void mouseClicked(MouseEvent c) {
    			if (!paused && menuButton >= 1 && menuButton <= 8 && !keyPaused && !superPaused) {
    				if (menuButton == 8) {
    					if (!saved) {
    						Object[] choice = {"Go back", "Quit anyway"};
    						int n = JOptionPane.showOptionDialog(frame, "File has not been saved.", "Exit warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, choice, choice[0]);
    						if (n == JOptionPane.NO_OPTION) {
    							System.exit(1);
    						}
    					}
    					else {
    						System.exit(1);
    					}
    				}
    				else {
    					menuButton = 8;
    				}
	    			menuSet();
    			}
    		}
    	});

    	menu61.addMouseListener(new MouseAdapter() {
    		public void mouseClicked(MouseEvent c) {
    			if (!paused && menuButton >= 61 && menuButton <= 63 && !keyPaused && !superPaused) {
    				if (menuButton == 61) {
    					saveGame();
    					menuButton = 6;
    					for (JLabel menuLabel : menuArray6) {
    						menuLabel.setVisible(false);
    					}
    					for (JLabel menuLabel : menuArray0) {
    						menuLabel.setVisible(true);
    					}
    				}
    				else {
    	    			menuButton = 61;
    				}
	    			menuSet();
    			}
    		}
    	});

    	menu62.addMouseListener(new MouseAdapter() {
    		public void mouseClicked(MouseEvent c) {
    			if (!paused && menuButton >= 61 && menuButton <= 63 && !keyPaused && !superPaused) {
    				if (menuButton == 62) {
    					loadGame();
    					menuButton = 6;
    					for (JLabel menuLabel : menuArray6) {
    						menuLabel.setVisible(false);
    					}
    					for (JLabel menuLabel : menuArray0) {
    						menuLabel.setVisible(true);
    					}
    				}
    				else {
    	    			menuButton = 62;
    				}
	    			menuSet();
    			}
    		}
    	});

    	menu63.addMouseListener(new MouseAdapter() {
    		public void mouseClicked(MouseEvent c) {
    			if (!paused && menuButton >= 61 && menuButton <= 63 && !keyPaused && !superPaused) {
    				if (menuButton == 63) {
    					menuButton = 6;
    					for (JLabel menuLabel : menuArray6) {
    						menuLabel.setVisible(false);
    					}
    					for (JLabel menuLabel : menuArray0) {
    						menuLabel.setVisible(true);
    					}
    				}
    				else {
    	    			menuButton = 63;
    				}
	    			menuSet();
    			}
    		}
    	});

    	menu71.addMouseListener(new MouseAdapter() {
    		public void mouseClicked(MouseEvent c) {
    			if (!paused && menuButton >= 71 && menuButton <= 75 && !keyPaused && !superPaused) {
    				if (menuButton == 71) {
    					setValueSkip();
    					menuButton = 7;
    					for (JLabel menuLabel : menuArray7) {
    						menuLabel.setVisible(false);
    					}
    					for (JLabel menuLabel : menuArray0) {
    						menuLabel.setVisible(true);
    					}
    				}
    				else {
    	    			menuButton = 71;
    				}
	    			menuSet();
    			}
    		}
    	});

    	menu72.addMouseListener(new MouseAdapter() {
    		public void mouseClicked(MouseEvent c) {
    			if (!paused && menuButton >= 71 && menuButton <= 75 && !keyPaused && !superPaused) {
    				if (menuButton == 72) {
    					setControls();
    					menuButton = 7;
    					for (JLabel menuLabel : menuArray7) {
    						menuLabel.setVisible(false);
    					}
    					for (JLabel menuLabel : menuArray0) {
    						menuLabel.setVisible(true);
    					}
    				}
    				else {
    	    			menuButton = 72;
    				}
	    			menuSet();
    			}
    		}
    	});
    	
    	menu75.addMouseListener(new MouseAdapter() {
    		public void mouseClicked(MouseEvent c) {
    			if (!paused && menuButton >= 71 && menuButton <= 75 && !keyPaused && !superPaused) {
    				if (menuButton == 75) {
    					menuButton = 7;
    					for (JLabel menuLabel : menuArray7) {
    						menuLabel.setVisible(false);
    					}
    					for (JLabel menuLabel : menuArray0) {
    						menuLabel.setVisible(true);
    					}
    				}
    				else {
    	    			menuButton = 75;
    				}
	    			menuSet();
    			}
    		}
    	});
    	
    	frame.addKeyListener(this);
    	frame.add(mainPanel, BorderLayout.WEST);
    	frame.add(menuPanel, BorderLayout.EAST);
    	frame.setIconImage(new ImageIcon(icon).getImage());
    	frame.setSize(960,800);
    	frame.setLocation(0, 0);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setResizable(false);
    	frame.pack();
    	frame.setVisible(true);
     }
    
    public void menuSet() {
    	if (menuButton >= 1 && menuButton <= 8) {
    		for (JLabel menuLabel : menuArray0) {
    			menuLabel.setFont(new Font("Arial", Font.PLAIN, 15));
    			menuLabel.setForeground(Color.GRAY);
    		}
        	switch (menuButton) {
    		case 1: 
    			menu1.setFont(new Font("Arial", Font.BOLD, 15));
    			menu1.setForeground(Color.BLACK);
    			break;
    		case 2: 
    			menu2.setFont(new Font("Arial", Font.BOLD, 15));
    			menu2.setForeground(Color.BLACK);
    			break;
    		case 3: 
    			menu3.setFont(new Font("Arial", Font.BOLD, 15));
    			menu3.setForeground(Color.BLACK);
    			break;
    		case 4: 
    			menu4.setFont(new Font("Arial", Font.BOLD, 15));
    			menu4.setForeground(Color.BLACK);
    			break;
    		case 5: 
    			menu5.setFont(new Font("Arial", Font.BOLD, 15));
    			menu5.setForeground(Color.BLACK);
    			break;
    		case 6: 
    			menu6.setFont(new Font("Arial", Font.BOLD, 15));
    			menu6.setForeground(Color.BLACK);
    			break;
    		case 7: 
    			menu7.setFont(new Font("Arial", Font.BOLD, 15));
    			menu7.setForeground(Color.BLACK);
    			break;
    		case 8: 
    			menu8.setFont(new Font("Arial", Font.BOLD, 15));
    			menu8.setForeground(Color.BLACK);
    			break;
        	}

    	}
    	else if (menuButton >= 61 && menuButton <= 63) {
    		for (JLabel menuLabel : menuArray6) {
    			menuLabel.setFont(new Font("Arial", Font.PLAIN, 15));
    			menuLabel.setForeground(Color.GRAY);
    		}
			menu60.setFont(new Font("Arial", Font.BOLD, 15));
			menu60.setForeground(Color.BLACK);
        	switch (menuButton) {
    		case 61: 
    			menu61.setFont(new Font("Arial", Font.BOLD, 15));
    			menu61.setForeground(Color.BLACK);
    			break;
    		case 62: 
    			menu62.setFont(new Font("Arial", Font.BOLD, 15));
    			menu62.setForeground(Color.BLACK);
    			break;
    		case 63: 
    			menu63.setFont(new Font("Arial", Font.BOLD, 15));
    			menu63.setForeground(Color.BLACK);
    			break;
        	}
    	}
    	else if (menuButton >= 71 && menuButton <= 75) {
    		for (JLabel menuLabel : menuArray7) {
    			menuLabel.setFont(new Font("Arial", Font.PLAIN, 15));
    			menuLabel.setForeground(Color.GRAY);
    		}
			menu70.setFont(new Font("Arial", Font.BOLD, 15));
			menu70.setForeground(Color.BLACK);
        	switch (menuButton) {
    		case 71: 
    			menu71.setFont(new Font("Arial", Font.BOLD, 15));
    			menu71.setForeground(Color.BLACK);
    			break;
    		case 72: 
    			menu72.setFont(new Font("Arial", Font.BOLD, 15));
    			menu72.setForeground(Color.BLACK);
    			break;
    		case 73: 
    			menu73.setFont(new Font("Arial", Font.BOLD, 15));
    			menu73.setForeground(Color.BLACK);
    			break;
    		case 74: 
    			menu74.setFont(new Font("Arial", Font.BOLD, 15));
    			menu74.setForeground(Color.BLACK);
    			break;
    		case 75: 
    			menu75.setFont(new Font("Arial", Font.BOLD, 15));
    			menu75.setForeground(Color.BLACK);
    			break;
        	}
    	}
    }
    
    public void menuSelect(String direction)
    {
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
    	}
    	menuSet();
    }

    public void menuJump(String direction)
    {
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
    	}
    	else {
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
    			if (valueSkip >= (3 - menuButton - 60)) {
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
    			if (valueSkip >= (5 - menuButton - 70)) {
    				menuButton = 75;
    			}
    			else {
    				menuButton += valueSkip;
    			}
    		}
    		else if (menuButton == 75) {
    			menuButton = 71;
    		}
    	}
    	menuSet();
    }
    
    public void menuPress() {
    	switch (menuButton) {
		case 1: //Party
			break;
		case 2: //Spells
			break;
		case 3: //Items
			break;
		case 4: //Pacts
			break;
		case 5: //Registry
			break;
		case 6: //File
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
			if (!saved) {
				Object[] choice = {"Go back", "Quit anyway"};
				int n = JOptionPane.showOptionDialog(frame, "File has not been saved.", "Exit warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, choice, choice[0]);
				if (n == JOptionPane.NO_OPTION) {
					System.exit(1);
				}
			}
			else {
				System.exit(1);
			}
			break;
		case 61: //File - Save
			saveGame();
			menuButton = 6;
			for (JLabel menuLabel : menuArray6) {
				menuLabel.setVisible(false);
			}
			for (JLabel menuLabel : menuArray0) {
				menuLabel.setVisible(true);
			}
			break;
		case 62: //File - Load
			loadGame();
			menuButton = 6;
			for (JLabel menuLabel : menuArray6) {
				menuLabel.setVisible(false);
			}
			for (JLabel menuLabel : menuArray0) {
				menuLabel.setVisible(true);
			}
			break;
		case 63: //File - Back
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
			setValueSkip();
			for (JLabel menuLabel : menuArray7) {
				menuLabel.setVisible(false);
			}
			for (JLabel menuLabel : menuArray0) {
				menuLabel.setVisible(true);
			}
			break;
		case 72: //Options - Controls
			setControls();
			for (JLabel menuLabel : menuArray7) {
				menuLabel.setVisible(false);
			}
			for (JLabel menuLabel : menuArray0) {
				menuLabel.setVisible(true);
			}
			break;
		case 73: //Options - Battle Animations
			break;
		case 74: //Options - Background Color
			break;
		case 75: //Options - Back
			menuButton = 7;
			for (JLabel menuLabel : menuArray7) {
				menuLabel.setVisible(false);
			}
			for (JLabel menuLabel : menuArray0) {
				menuLabel.setVisible(true);
			}
			menuSet();
			break;
		}
    }
    
	@Override
	public void keyPressed(KeyEvent k) {
		if (!keyPaused && !superPaused) {
			//MENU
			if ((k.getKeyCode() == mu1 || k.getKeyCode() == mu2 || k.getKeyCode() == mu3) && !paused) {
				menuSelect("UP");
			}
			else if ((k.getKeyCode() == md1 || k.getKeyCode() == md2 || k.getKeyCode() == md3) && !paused) {
				menuSelect("DOWN");
			}
			else if ((k.getKeyCode() == muu1 || k.getKeyCode() == muu2 || k.getKeyCode() == muu3) && !paused) {
				menuJump("UP");
			}
			else if ((k.getKeyCode() == mdd1 || k.getKeyCode() == mdd2 || k.getKeyCode() == mdd3) && !paused) {
				menuJump("DOWN");
			}
			else if ((k.getKeyCode() >= KeyEvent.VK_0 && k.getKeyCode() <= KeyEvent.VK_9) && !paused) {
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
				menuSet();
			}
			else if ((k.getKeyCode() == mb1 || k.getKeyCode() == mb2 || k.getKeyCode() == mb3) && !paused) {
				
				if (menuButton >= 61 && menuButton <= 63) {
					menuButton = 6;
					for (JLabel menuLabel : menuArray6) {
						menuLabel.setVisible(false);
					}
					for (JLabel menuLabel : menuArray0) {
						menuLabel.setVisible(true);
					}
				}
				else if (menuButton >= 71 && menuButton <= 75) {
					menuButton = 7;
					for (JLabel menuLabel : menuArray7) {
						menuLabel.setVisible(false);
					}
					for (JLabel menuLabel : menuArray0) {
						menuLabel.setVisible(true);
					}
				}
				menuSet();
			}
			else if ((k.getKeyCode() == ms1 || k.getKeyCode() == ms2 || k.getKeyCode() == ms3) && !paused) {
				menuPress();
			}
			
			//CHARACTER
			else if ((k.getKeyCode() == l1 || k.getKeyCode() == l2 || k.getKeyCode() == l3) && !paused && !going) {
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
						character.setImageIcon("Data\\Characters\\" + character.month + character.direction.substring(0,  1) + character.step + ".png");
				    	mainPanel.remove(characterSprite);
						characterSprite = new JLabel(character.imageIcon);
				    	mainPanel.add(characterSprite);
				    	characterSprite.setBounds(character.x, character.y, ML*1, ML*1);
				    	mainPanel.revalidate();
				    	mainPanel.repaint();
						going = false;
					}
				}
			}
			else if ((k.getKeyCode() == r1 || k.getKeyCode() == r2 || k.getKeyCode() == r3) && !paused && !going) {
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
						character.setImageIcon("Data\\Characters\\" + character.month + character.direction.substring(0,  1) + character.step + ".png");
				    	mainPanel.remove(characterSprite);
						characterSprite = new JLabel(character.imageIcon);
				    	mainPanel.add(characterSprite);
				    	characterSprite.setBounds(character.x, character.y, ML*1, ML*1);
				    	mainPanel.revalidate();
				    	mainPanel.repaint();
						going = false;
					}
				}
			}
			else if ((k.getKeyCode() == u1 || k.getKeyCode() == u2 || k.getKeyCode() == u3) && !paused && !going) {
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
						character.setImageIcon("Data\\Characters\\" + character.month + character.direction.substring(0,  1) + character.step + ".png");
				    	mainPanel.remove(characterSprite);
						characterSprite = new JLabel(character.imageIcon);
				    	mainPanel.add(characterSprite);
				    	characterSprite.setBounds(character.x, character.y, ML*1, ML*1);
				    	mainPanel.revalidate();
				    	mainPanel.repaint();
						going = false;
					}
				}
			}
			else if ((k.getKeyCode() == d1 || k.getKeyCode() == d2 || k.getKeyCode() == d3) && !paused && !going) {
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
						character.setImageIcon("Data\\Characters\\" + character.month + character.direction.substring(0,  1) + character.step + ".png");
				    	mainPanel.remove(characterSprite);
						characterSprite = new JLabel(character.imageIcon);
				    	mainPanel.add(characterSprite);
				    	characterSprite.setBounds(character.x, character.y, ML*1, ML*1);
				    	mainPanel.revalidate();
				    	mainPanel.repaint();
						going = false;
					}
				}
			}
			else if ((k.getKeyCode() == su1 || k.getKeyCode() == su2 || k.getKeyCode() == su3)) {
				saved = false;
				if (timerRun == 80) {
					timerRun = 20;
				}
				else {
					timerRun = 80;
				}
				timer.setDelay(timerRun);
			}
			else if ((k.getKeyCode() == p1 || k.getKeyCode() == p2 || k.getKeyCode() == p3) && !going && !paused) {
				if (buttonPaused) {
			    	menuPanel.setBackground(new Color(224, 224, 224));
			    	mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			    	foregroundPanel.setBackground(new Color(255, 255, 255, 0));
			    	foregroundPanel.setOpaque(false);
					buttonPaused = false;
				}
				else {
			    	menuPanel.setBackground(new Color(212, 212, 212));
			    	mainPanel.setBorder(BorderFactory.createLineBorder(new Color(127, 0, 63), 1));
			    	foregroundPanel.setBackground(new Color(127, 127, 127, 63));
			    	foregroundPanel.setOpaque(true);
					buttonPaused = true;
				}
			}
			else if ((k.getKeyCode() == ac1 || k.getKeyCode() == ac2 || k.getKeyCode() == ac3) && !going) {
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
										nowThing.setImageIcon("Data\\" + nowThing.type + "\\" + nowThing.name + nowThing.step + ".png");
									}
									else {
										nowThing.setImageIcon("Data\\" + nowThing.type + "\\" + nowThing.name + nowThing.direction.substring(0,  1) + nowThing.step + ".png");
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
		/*if (nowThing.x <= ML*19 && nowThing.y >= ML*3) {
			speech.setBounds(nowThing.x + ML + ML/8, nowThing.y - ML*3, ML*5 - ML/4, ML*3);
			if (nowThing.action == 1) {
				path.moveTo(0, 0);
				path.lineTo(ML*5, 0);
				path.lineTo(ML*5, ML*3);
				path.lineTo(0, ML*3);
				path.lineTo(0, 0);
				speechBox = new SpeechBox(path);
    			speechBox.setBounds(nowThing.x + ML, nowThing.y - ML*3, ML*5, ML*4);
			}
			else {
				path.moveTo(ML + 6, 0);
				path.lineTo(ML*6 - 6, 0);
				path.curveTo(ML*6 - 6, 0, ML*6 - 3, 3, ML*6, 6);
				path.lineTo(ML*6, ML*3 - 6);
				path.curveTo(ML*6, ML*3 - 6, ML*6 - 3, ML*3 - 3, ML*6 - 6, ML*3);
				path.lineTo(ML + 3, ML*3);
				path.lineTo(ML - 6, ML*3 + 6);
				path.lineTo(ML, ML*3 - 3);
				path.lineTo(ML, 6);
				path.curveTo(ML, 6, ML + 3, 3, ML + 6, 0);
				path.lineTo(ML + 6, 0);
				speechBox = new SpeechBox(path);
    			speechBox.setBounds(nowThing.x, nowThing.y - ML*3, ML*6, ML*4);
			}
		}
		else if (nowThing.x <= ML*19 && nowThing.y < ML*3) {
			speech.setBounds(nowThing.x + ML + ML/8, nowThing.y + ML, ML*5 - ML/4, ML*3);
			if (nowThing.action == 1) {
				path.moveTo(0, ML);
				path.lineTo(ML*5, ML);
				path.lineTo(ML*5, ML*4);
				path.lineTo(0, ML*4);
				path.lineTo(0, ML);
				speechBox = new SpeechBox(path);
    			speechBox.setBounds(nowThing.x + ML, nowThing.y, ML*5, ML*4);
			}
			else {
				path.moveTo(ML + 6, ML);
				path.lineTo(ML*6 - 6, ML);
				path.curveTo(ML*6 - 6, ML, ML*6 - 3, ML + 3, ML*6, ML + 6);
				path.lineTo(ML*6, ML*4 - 6);
				path.curveTo(ML*6, ML*4 - 6, ML*6 - 3, ML*4 - 3, ML*6 - 6, ML*4);
				path.lineTo(ML + 3, ML*4);
				path.curveTo(ML + 6, ML*4, ML + 3, ML*4 - 3, ML, ML*4 - 6);
				path.lineTo(ML, ML + 3);
				path.lineTo(ML - 6, ML - 6);
				path.lineTo(ML + 3, ML);
				path.lineTo(ML + 6, ML);
				speechBox = new SpeechBox(path);
    			speechBox.setBounds(nowThing.x, nowThing.y, ML*6, ML*4);
			}
		}
		else if (nowThing.x > ML*19 && nowThing.y < ML*3) {
			speech.setBounds(nowThing.x - ML*5 + ML/8, nowThing.y + ML, ML*5 - ML/4, ML*3);
			if (nowThing.action == 1) {
				path.moveTo(0, ML);
				path.lineTo(ML*5, ML);
				path.lineTo(ML*5, ML*4);
				path.lineTo(0, ML*4);
				path.lineTo(0, ML);
				speechBox = new SpeechBox(path);
    			speechBox.setBounds(nowThing.x - ML*5, nowThing.y, ML*5, ML*4);
			}
			else {
				path.moveTo(6, ML);
				path.lineTo(ML*5 - 3, ML);
				path.lineTo(ML*5 + 6, ML - 6);
				path.lineTo(ML*5, ML + 3);
				path.lineTo(ML*5, ML*4 - 6);
				path.curveTo(ML*5, ML*4 - 6, ML*5 - 3, ML*4 - 3, ML*5 - 6, ML*4);
				path.lineTo(3, ML*4);
				path.curveTo(6, ML*4, 3, ML*4 - 3, 0, ML*4 - 6);
				path.lineTo(0, ML + 6);
				path.curveTo(0, ML + 6, 3, ML + 3, 6, ML);
				path.lineTo(6, ML);
				speechBox = new SpeechBox(path);
    			speechBox.setBounds(nowThing.x - ML*5, nowThing.y, ML*6, ML*4);
			}
		}
		else if (nowThing.x > ML*19 && nowThing.y >= ML*3) {
			speech.setBounds(nowThing.x - ML*5 + ML/8, nowThing.y - ML*3, ML*5 - ML/4, ML*3);
			if (nowThing.action == 1) {
				path.moveTo(0, 0);
				path.lineTo(ML*5, 0);
				path.lineTo(ML*5, ML*3);
				path.lineTo(0, ML*3);
				path.lineTo(0, 0);
				speechBox = new SpeechBox(path);
    			speechBox.setBounds(nowThing.x - ML*5, nowThing.y - ML*3, ML*5, ML*4);
			}
			else {
				path.moveTo(6, 0);
				path.lineTo(ML*5 - 6, 0);
				path.curveTo(ML*5 - 6, 0, ML*5 - 3, 3, ML*5, 6);
				path.lineTo(ML*5, ML*3 - 3);
				path.lineTo(ML*5 + 6, ML*3 + 6);
				path.lineTo(ML*5 - 3, ML*3);
				path.lineTo(3, ML*3);
				path.curveTo(6, ML*3, 3, ML*3 - 3, 0, ML*3 - 6);
				path.lineTo(0, 6);
				path.curveTo(0, 6, 3, 3, 6, 0);
				path.lineTo(6, 0);
				speechBox = new SpeechBox(path);
    			speechBox.setBounds(nowThing.x - ML*5, nowThing.y - ML*3, ML*6, ML*4);
			}
		}*/
    	speech.setVerticalAlignment(JLabel.TOP);
    	mainPanel.setLayer(speechBox, 2);
    	mainPanel.setLayer(speech, 3);
		mainPanel.add(speechBox);
		mainPanel.add(speech);
    	mainPanel.revalidate();
    	mainPanel.repaint();
	}

	public void setValueSkip() {
		Object input = JOptionPane.showInputDialog(frame, "Input integer for value skip:", "Value skip", JOptionPane.PLAIN_MESSAGE, null, null, valueSkip);

		if (input.toString().matches("\\d+")) {
			valueSkip = Integer.valueOf(input.toString());
		}
	}
	
	public void setControls() {
		
	}
	
	public void saveGame() {
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
			superPaused = false;
		}
		else {
			String choice = (String) choiceObject;			
			
			if (choice.equals("4: " + file4Name)) {
				currentFile = 4;
				currentFileName = file4Name;
			}
			else if (choice.equals("3: " + file3Name)) {
				currentFile = 3;
				currentFileName = file3Name;
			}
			else if (choice.equals("2: " + file2Name)) {
				currentFile = 2;
				currentFileName = file2Name;
			}
			else if (choice.equals("1: " + file1Name)) {
				currentFile = 1;
				currentFileName = file1Name;
			}

			if (currentFileName.equals("New file\t")) {
				Object currentFileNameObject = JOptionPane.showInputDialog(frame, "Input file name:", "Save file " + currentFile, JOptionPane.PLAIN_MESSAGE, null, null, character.name);
				
				if (currentFileNameObject == null) {
				}
				else {
					currentFileName = (String) currentFileNameObject;
					writeFile(currentFileName);
					saved = true;
				}
			}
			else {
				Object[] choice2 = {"Yes", "No"};
				Object choice2Object = JOptionPane.showOptionDialog(frame, "Overwrite this file?", "Save file " + currentFile, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, choice2, choice2[0]);
				
				if ((int) choice2Object != JOptionPane.YES_OPTION) {
				}
				else {
					writeFile(currentFileName);
					saved = true;
				}
			}
			superPaused = false;
		}
	}
	
	public void writeFile(String currentFileName) {
		Charset utf8 = StandardCharsets.UTF_8;
		List<String> lines = Arrays.asList(
			//Name 0
			currentFileName,
			//Bindings 2 - 40
			"\tde3vwbc3nodest\t",
			""+l1, ""+l2, ""+l3, ""+r1, ""+r2, ""+r3, ""+u1, ""+u2, ""+u3, ""+d1, ""+d2, ""+d3, 
			""+ac1, ""+ac2, ""+ac3, ""+p1, ""+p2, ""+p3, ""+mu1, ""+mu2, ""+mu3, ""+md1, ""+md2, ""+md3, 
			""+muu1, ""+muu2, ""+muu3, ""+mdd1, ""+mdd2, ""+mdd3, ""+ms1, ""+ms2, ""+ms3, ""+mb1, ""+mb2, ""+mb3, 
			""+su1, ""+su2, ""+su3,
			//Misc 42 - 47
			"\tde3vwmn3stcd\t",
			""+valueSkip, ""+background.getRed(), ""+background.getGreen(), ""+background.getBlue(), ""+animation, ""+currentFile, 
			//Character
			"\tde3vwcdhi1rs\t",
			character.name, character.month, character.direction, ""+character.x, ""+character.y, ""+character.step,
			//Current Zone
			"\tde3vwcd5rsrs2notu\t",
			""+currentZone.id, 
			//Party
			"\tde3vwpq1rstuyz\t",
			//Spells
			"\tde3vwstpq3lmlmst\t",
			//Pacts
			"\tde3vwpq1cdtust\t",
			//Items
			"\tde3vw3tu2mnst\t",
			//Zone Data
			"\tde3vwza4no2st\t",
			//Fae Registry
			"\tde3vwfg13\t",
			//End
			"\t2node\t"
			);
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
			superPaused = false;
		}
		else {
			String choice = (String) choiceObject;			
			
			if (choice.equals("4: " + file4Name)) {
				currentFile = 4;
				currentFileName = file4Name;
			}
			else if (choice.equals("3: " + file3Name)) {
				currentFile = 3;
				currentFileName = file3Name;
			}
			else if (choice.equals("2: " + file2Name)) {
				currentFile = 2;
				currentFileName = file2Name;
			}
			else if (choice.equals("1: " + file1Name)) {
				currentFile = 1;
				currentFileName = file1Name;
			}

			if (currentFileName.equals("New file\t")) {
				JOptionPane.showMessageDialog(frame, "Cannot load a new file.", "Load file", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				Object[] choice2 = {"Yes", "No"};
				Object choice2Object = JOptionPane.showOptionDialog(frame, "Load this file?", "Load file " + currentFile, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, choice2, choice2[0]);
				
				if ((int) choice2Object != JOptionPane.YES_OPTION) {
				}
				else {
					readFile("File\\Save" + currentFile + ".jsmn");
					saved = true;
				}
			}
			character.setImageIcon("Data\\Characters\\" + character.month + character.direction.substring(0,  1) + character.step + ".png");
	    	mainPanel.remove(characterSprite);
			characterSprite = new JLabel(character.imageIcon);
	    	mainPanel.add(characterSprite);
	    	characterSprite.setBounds(character.x, character.y, ML*1, ML*1);
	    	mainPanel.revalidate();
	    	mainPanel.repaint();
			superPaused = false;
		}
	}

	public void readFile(String currentFileId) {
		String line;
    	try (BufferedReader bufferedReader = new BufferedReader(new FileReader(currentFileId))) {
    		line = bufferedReader.readLine();
    		int progress = 0;
    		int overallProgress = 0;
    		int bRed = 255;
    		int bGreen = 255;
    		int bBlue = 255;
    		while (!line.equals("\t2node\t")) {
    			if (overallProgress == 0 && line.equals("\tde3vwbc3nodest\t")) {
    				overallProgress = 2;
    				progress = 0;
    			}
    			else if (overallProgress == 2 && line.equals("\tde3vwmn3stcd\t")) {
    				overallProgress = 3;
    				progress = 0;
    			}
    			else if (overallProgress == 3 && line.equals("\tde3vwcdhi1rs\t")) {
    				overallProgress = 4;
    				progress = 0;
    			}
    			else if (overallProgress == 4 && line.equals("\tde3vwcd5rsrs2notu\t")) {
    				overallProgress = 5;
    				progress = 0;
    			}
    			else if (overallProgress == 5 && line.equals("\tde3vwpq1rstuyz\t")) {
    				overallProgress = 6;
    				progress = 0;
    			}
    			else if (overallProgress == 6 && line.equals("\tde3vwstpq3lmlmst\t")) {
    				overallProgress = 7;
    				progress = 0;
    			}
    			else if (overallProgress == 7 && line.equals("\tde3vwpq1cdtust\t")) {
    				overallProgress = 8;
    				progress = 0;
    			}
    			else if (overallProgress == 8 && line.equals("\tde3vw3tu2mnst\t")) {
    				overallProgress = 9;
    				progress = 0;
    			}
    			else if (overallProgress == 9 && line.equals("\tde3vwza4no2st\t")) {
    				overallProgress = 10;
    				progress = 0;
    			}
    			else if (overallProgress == 10 && line.equals("\tde3vwfg13\t")) {
    				overallProgress = 11;
    				progress = 0;
    			}
    			else if (overallProgress == 2) {
    				keyArray.set(progress, Integer.getInteger(line));
    				progress++;
    			}
    			else if (overallProgress == 3) {
    				switch (progress) {
    				case 0:
    					valueSkip = Integer.parseInt(line);
    					break;
    				case 1:
    					bRed = Integer.parseInt(line);
    					break;
    				case 2:
    					bGreen = Integer.parseInt(line);
    					break;
    				case 3:
    					bBlue = Integer.parseInt(line);
    					background = new Color(bRed, bGreen, bBlue);
    					break;
    				case 4:
    					animation = Boolean.parseBoolean(line);
    					break;
    				case 5:
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
			for (int i = 0; i <= 3; i++) {
				zoneArray.add(new Zone(i));
			}
		}
	}
	
	public void setZone(int id) {
		currentZone = zoneArray.get(id);
	}
	
    public void loadArea() {
    	switch (currentZone.id) {
    	case 0:
    		mainPanel.add(characterSprite);
        	character.setX(ML*12);
        	character.setY(ML*1);
        	characterSprite.setBounds(character.x,character.y,ML*1,ML*1);
        	
        	thingArray = currentZone.thingArray;
    	}
    	
    	for (int i = 0; i < thingArray.size(); i++) {
    		thingSpriteArray.add(new JLabel(thingArray.get(i).imageIcon));
    		mainPanel.add(thingSpriteArray.get(i));
    		thingSpriteArray.get(i).setBounds(thingArray.get(i).x,thingArray.get(i).y,ML*1,ML*1);
    	}
    }
}