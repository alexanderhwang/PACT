import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.GeneralPath;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JFrame implements KeyListener{
	static final int ML = 32;
	
	private JFrame frame = new JFrame("OrbFae");
	private JLayeredPane mainPanel = new JLayeredPane();
	private JPanel menuPanel = new JPanel();
	private JLabel menu1 = new JLabel("Fae");
	private JLabel menu2 = new JLabel("Items");
	private JLabel menu3 = new JLabel("Registry");
	private JLabel menu4 = new JLabel("File");
	private JLabel menu5 = new JLabel("Options");
	private JLabel menu6 = new JLabel("Quit");
	private String icon = "Data\\Objects\\Rock1.png"; 
	private Color background = new Color(255, 255, 255);
	private Character character = new Character("?", "Februa", "DOWN", ML*1, ML*1, 1);
	private JLabel characterSprite = new JLabel(character.imageIcon);
	private SpeechBox speechBox;
	private GeneralPath path;
	private JLabel speech = new JLabel("");
	private Boolean going = false;
	private Boolean paused = false;
	private Timer timer;
	private ArrayList<Thing> thingArray = new ArrayList<Thing>();
	private ArrayList<JLabel> thingSpriteArray = new ArrayList<JLabel>();
	private Thing savedThing;
	
	private int timerRun = 80;
	private int timerStep = 0;
	private int menuButton = 1;
	private int talk = 0;

	private int l1 = KeyEvent.VK_LEFT; private int l2 = KeyEvent.VK_A; private int l3 = KeyEvent.VK_NUMPAD4;
	private int r1 = KeyEvent.VK_RIGHT; private int r2 = KeyEvent.VK_D; private int r3 = KeyEvent.VK_NUMPAD6;
	private int u1 = KeyEvent.VK_UP; private int u2 = KeyEvent.VK_W; private int u3 = KeyEvent.VK_NUMPAD8;
	private int d1 = KeyEvent.VK_DOWN; private int d2 = KeyEvent.VK_S; private int d3 = KeyEvent.VK_NUMPAD2;
	private int ac1 = KeyEvent.VK_SPACE; private int ac2 = KeyEvent.VK_Q; private int ac3 = KeyEvent.VK_NUMPAD0;
	private int mu1 = KeyEvent.VK_PAGE_UP; private int mu2 = KeyEvent.VK_SHIFT; private int mu3 = KeyEvent.VK_MINUS;
	private int md1 = KeyEvent.VK_PAGE_DOWN; private int md2 = KeyEvent.VK_CONTROL; private int md3 = KeyEvent.VK_EQUALS;
	private int ms1 = KeyEvent.VK_ENTER; private int ms2 = KeyEvent.VK_Z; private int ms3 = KeyEvent.VK_BACK_SLASH;
	private int su1 = KeyEvent.VK_CAPS_LOCK; private int su2 = KeyEvent.VK_NUM_LOCK; private int su3 = KeyEvent.VK_M;
	
    public Board() {
    	menuPanel.setPreferredSize(new Dimension(120, 400));
    	menuPanel.setBackground(new Color(224, 224, 224));
    	
    	mainPanel.setPreferredSize(new Dimension(800, 800));
    	mainPanel.setLayout(null);
    	mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    	mainPanel.setBackground(background);
    	
    	menu1.setPreferredSize(new Dimension(100, 25));
    	menu2.setPreferredSize(new Dimension(100, 25));
    	menu3.setPreferredSize(new Dimension(100, 25));
    	menu4.setPreferredSize(new Dimension(100, 25));
    	menu5.setPreferredSize(new Dimension(100, 25));
    	menu6.setPreferredSize(new Dimension(100, 25));
    	menu1.setFont(new Font("Arial", Font.BOLD, 15));
    	menu2.setFont(new Font("Arial", Font.PLAIN, 15));
    	menu3.setFont(new Font("Arial", Font.PLAIN, 15));
    	menu4.setFont(new Font("Arial", Font.PLAIN, 15));
    	menu5.setFont(new Font("Arial", Font.PLAIN, 15));
    	menu6.setFont(new Font("Arial", Font.PLAIN, 15));
    	menu1.setForeground(Color.BLACK);
    	menu2.setForeground(Color.GRAY);
    	menu3.setForeground(Color.GRAY);
    	menu4.setForeground(Color.GRAY);
    	menu5.setForeground(Color.GRAY);
    	menu6.setForeground(Color.GRAY);
    	menuPanel.add(menu1);
    	menuPanel.add(menu2);
    	menuPanel.add(menu3);
    	menuPanel.add(menu4);
    	menuPanel.add(menu5);
    	menuPanel.add(menu6);
    	
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
				if (character.direction == "LEFT") {
					character.setX(character.x - ML/4);
				}
				else if (character.direction == "RIGHT") {
					character.setX(character.x + ML/4);
				}
				else if (character.direction == "UP") {
					character.setY(character.y - ML/4);
				}
				else if (character.direction == "DOWN") {
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
    			if (!paused) {
	    			menuButton = 1;
	    			menuSet();
    			}
    		}
    	});
    	
    	menu2.addMouseListener(new MouseAdapter() {
    		public void mouseClicked(MouseEvent c) {
    			if (!paused) {
	    			menuButton = 2;
	    			menuSet();
    			}
    		}
    	});
    	
    	menu3.addMouseListener(new MouseAdapter() {
    		public void mouseClicked(MouseEvent c) {
    			if (!paused) {
	    			menuButton = 3;
	    			menuSet();
    			}
    		}
    	});
    	
    	menu4.addMouseListener(new MouseAdapter() {
    		public void mouseClicked(MouseEvent c) {
    			if (!paused) {
	    			menuButton = 4;
	    			menuSet();
    			}
    		}
    	});
    	
    	menu5.addMouseListener(new MouseAdapter() {
    		public void mouseClicked(MouseEvent c) {
    			if (!paused) {
	    			menuButton = 5;
	    			menuSet();
    			}
    		}
    	});
    	
    	menu6.addMouseListener(new MouseAdapter() {
    		public void mouseClicked(MouseEvent c) {
    			if (!paused) {
	    			menuButton = 6;
	    			menuSet();
    			}
    		}
    	});
    	
    	frame.addKeyListener(this);
    	frame.add(mainPanel, BorderLayout.WEST);
    	frame.add(menuPanel, BorderLayout.EAST);
    	frame.setIconImage(new ImageIcon(icon).getImage());
    	frame.setSize(800,920);
    	frame.setLocation(0, 0);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setResizable(false);
    	frame.pack();
    	frame.setVisible(true);
     }
    
    public void menuSet()
    {
    	menu1.setFont(new Font("Arial", Font.PLAIN, 15));
    	menu2.setFont(new Font("Arial", Font.PLAIN, 15));
    	menu3.setFont(new Font("Arial", Font.PLAIN, 15));
    	menu4.setFont(new Font("Arial", Font.PLAIN, 15));
    	menu5.setFont(new Font("Arial", Font.PLAIN, 15));
    	menu6.setFont(new Font("Arial", Font.PLAIN, 15));
    	menu1.setForeground(Color.GRAY);
    	menu2.setForeground(Color.GRAY);
    	menu3.setForeground(Color.GRAY);
    	menu4.setForeground(Color.GRAY);
    	menu5.setForeground(Color.GRAY);
    	menu6.setForeground(Color.GRAY);
    	
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
    	}
    }
    public void menuSelect(String direction)
    {
    	if (direction == "UP") {
    		if (menuButton > 1) {
    			menuButton--;
    		}
    		else {
    			menuButton = 6;
    		}
    	}
    	else {
    		if (menuButton < 6) {
    			menuButton++;
    		}
    		else {
    			menuButton = 1;
    		}
    	}
    	menuSet();
    }
    
	@Override
	public void keyPressed(KeyEvent k) {
		//MENU
		if ((k.getKeyCode() == mu1 || k.getKeyCode() == mu2 || k.getKeyCode() == mu3) && !paused) {
			menuSelect("UP");
		}
		else if ((k.getKeyCode() == md1 || k.getKeyCode() == md2 || k.getKeyCode() == md3) && !paused) {
			menuSelect("DOWN");
		}
		
		//CHARACTER
		else if ((k.getKeyCode() == l1 || k.getKeyCode() == l2 || k.getKeyCode() == l3) && !paused && !going) {
			Boolean allowed = true;
			going = true;
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
		else if ((k.getKeyCode() == r1 || k.getKeyCode() == r2 || k.getKeyCode() == r3) && !paused && !going) {
			Boolean allowed = true;
			going = true;
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
		else if ((k.getKeyCode() == u1 || k.getKeyCode() == u2 || k.getKeyCode() == u3) && !paused && !going) {
			Boolean allowed = true;
			going = true;
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
		else if ((k.getKeyCode() == d1 || k.getKeyCode() == d2 || k.getKeyCode() == d3) && !paused && !going) {
			Boolean allowed = true;
			going = true;
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
		else if ((k.getKeyCode() == su1 || k.getKeyCode() == su2 || k.getKeyCode() == su3)) {
			if (timerRun == 80) {
				timerRun = 20;
			}
			else {
				timerRun = 80;
			}
			timer.setDelay(timerRun);
		}
		else if ((k.getKeyCode() == ac1 || k.getKeyCode() == ac2 || k.getKeyCode() == ac3) && !going) {
			if (!paused && talk == 0) {
				int actX = character.x;
				int actY = character.y;
				if (character.direction == "LEFT") {actX -= ML*1;}
				else if (character.direction == "RIGHT") {actX += ML*1;}
				else if (character.direction == "UP") {actY -= ML*1;}
				else if (character.direction == "DOWN") {actY += ML*1;}
				for (int i = 0; i < thingArray.size(); i++) {
					Thing nowThing = thingArray.get(i);
					if (actX == nowThing.x && actY == nowThing.y) {
						switch (nowThing.action) {
						case 0:
							break;
						case 1:
						case 2:
							paused = true;
							if (nowThing.type == "Objects") {
							}
							else if (character.direction == "RIGHT") {
								thingArray.get(i).setDirection("LEFT");
							}
							else if (character.direction == "LEFT") {
								thingArray.get(i).setDirection("RIGHT");
							}
							else if (character.direction == "DOWN") {
								thingArray.get(i).setDirection("UP");
							}
							else {
								thingArray.get(i).setDirection("DOWN");
							}
							if (nowThing.type == "Objects") {
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
	
	public void drawSpeechBox(Thing nowThing) {
		path = new GeneralPath();
		speech = new JLabel(nowThing.misc.get(0));
    	speech.setFont(new Font("Arial", Font.PLAIN, 12));
    	int speechLines = (nowThing.misc.get(0).length()/13) * 2;
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

	@Override
	public void keyReleased(KeyEvent k) {
	}
	
	@Override
	public void keyTyped(KeyEvent k) {
	}
	
    public void loadGame() {
    	//INIT
    	mainPanel.add(characterSprite);
    	character.setX(ML*12);
    	character.setY(ML*1);
    	characterSprite.setBounds(character.x,character.y,ML*1,ML*1);
    	
    	thingArray.add(new Thing("Rock", "Objects", "", ML*2, ML*2, 1, 0, new ArrayList<String>()));
    	thingArray.add(new Thing("Rock", "Objects", "", ML*3, ML*3, 1, 0, new ArrayList<String>()));
    	thingArray.add(
    			new Thing("Rock", "Objects", "", ML*6, ML*8, 1, 1, 
    					new ArrayList<String>(Arrays.asList(
    							"<html>The rock is warm to the touch.</html>"))));
    	thingArray.add(
    			new Thing("Rock", "Objects", "", ML*21, ML*4, 1, 1, 
    					new ArrayList<String>(Arrays.asList(
    							"<html>The rock is warm to the touch.</html>"))));
    	thingArray.add(
    			new Thing("Rock", "Objects", "", ML*21, ML*2, 1, 1, 
    					new ArrayList<String>(Arrays.asList(
    							"<html>The rock is warm to the touch.</html>"))));
    	thingArray.add(
    			new Thing("Rock", "Objects", "", ML*17, ML*4, 1, 1, 
    					new ArrayList<String>(Arrays.asList(
    							"<html>The rock is warm to the touch.</html>"))));
    	thingArray.add(
    			new Thing("Rock", "Objects", "", ML*17, ML*2, 1, 1, 
    					new ArrayList<String>(Arrays.asList(
    							"<html>The rock is warm to the touch.</html>"))));
    	thingArray.add(
    			new Thing("Rock", "Objects", "", ML*14, ML*2, 1, 1, 
    					new ArrayList<String>(Arrays.asList(
    							"<html>The rock sucks.</html>"))));
    	thingArray.add(
    			new Thing("Rock", "Objects", "", ML*13, ML*2, 1, 1, 
    					new ArrayList<String>(Arrays.asList(
    							"<html>The rock suckse e e e e e e e e e e e e e e ee e e ee e e e ee e e e e e e .</html>"))));
    	thingArray.add(
    			new Thing("Pyane", "Others", "DOWN", ML*4, ML*2, 1, 2, 
    					new ArrayList<String>(Arrays.asList(
    							"<html><b>Pyane:</b> Oi, what's e e ee e e e e e e e e  ee e e e e   e e e e e e e  ee e e e e  ?</html>"))));
    	thingArray.add(
    			new Thing("Pyane", "Others", "DOWN", ML*5, ML*5, 1, 2, 
    					new ArrayList<String>(Arrays.asList(
    							"<html><b>Pyane:</b> Oi, what's up?</html>"))));
    	thingArray.add(
    			new Thing("Pyane", "Others", "DOWN", ML*5, ML*6, 1, 2, 
    					new ArrayList<String>(Arrays.asList(
    							"<html><b>Pyane:</b> Oi, what's upee ee  e ee e ee  e ee e e e e e e e e e e  e ee e e e e e e e ?</html>"))));
    	thingArray.add(
    			new Thing("Pyane", "Others", "DOWN", ML*4, ML*6, 1, 2, 
    					new ArrayList<String>(Arrays.asList(
    							"<html><b>Pyane:</b> Oi, what's upee ee ee e  e e ee e  ee  ee e e e e e e ee ee eeee e eee e eee eee e eee e ?</html>"))));
    	thingArray.add(
    			new Thing("Pyane", "Others", "DOWN", ML*20, ML*3, 1, 2, 
    					new ArrayList<String>(Arrays.asList(
    							"<html><b>Pyane:</b> Oi, what's upe e e e e  ee e e  e e e ee  e ee  e e e?</html>"))));
    	thingArray.add(
    			new Thing("Pyane", "Others", "DOWN", ML*20, ML*2, 1, 2, 
    					new ArrayList<String>(Arrays.asList(
    							"<html><b>Pyane:</b> Oi, what's upe e e e e  ee ee  ee e e e e  ee e e e e ?</html>"))));
    	thingArray.add(
    			new Thing("Pyane", "Others", "DOWN", ML*19, ML*3, 1, 2, 
    					new ArrayList<String>(Arrays.asList(
    							"<html><b>Pyane:</b> Oi, what's up?</html>", 
    							"<html><b>Pyane:</b> Yeh, figures. You always were the quiet one, eh?</html>",
    							"<html><b>Pyane:</b> Gotta love that about you!</html>"))));
    	thingArray.add(
    			new Thing("Pyane", "Others", "DOWN", ML*18, ML*3, 1, 2, 
    					new ArrayList<String>(Arrays.asList(
    							"<html><b>Pyane:</b> Oi, what's upee ee  e ee e ee  e ee e e e e e e e e e e  e ee e e e e e e e ?</html>"))));
    	thingArray.add(
    			new Thing("Pyane", "Others", "DOWN", ML*17, ML*3, 1, 2, 
    					new ArrayList<String>(Arrays.asList(
    							"<html><b>Pyane:</b> Oi, what's upee ee  e ee e ee e e e  e e ee  e ee e e e e e e e e e e  e ee e e e e e e e ?</html>"))));

    	for (int i = 0; i < thingArray.size(); i++) {
    		thingSpriteArray.add(new JLabel(thingArray.get(i).imageIcon));
    		mainPanel.add(thingSpriteArray.get(i));
    		thingSpriteArray.get(i).setBounds(thingArray.get(i).x,thingArray.get(i).y,ML*1,ML*1);
    	}
    }
}
