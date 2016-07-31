import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements KeyListener{
	private JFrame frame = new JFrame("OrbFae");
	private MainPanel mainPanel = new MainPanel();
	private JPanel menuPanel = new JPanel();
	private JLabel menu1 = new JLabel("Fae");
	private JLabel menu2 = new JLabel("Items");
	private JLabel menu3 = new JLabel("Registry");
	private JLabel menu4 = new JLabel("File");
	private JLabel menu5 = new JLabel("Options");
	private JLabel menu6 = new JLabel("Quit");
	private String icon = "Data\\Objects\\Rock1.png"; 
	private Color background = new Color(255, 255, 255);
	private Character character = new Character("?", "Februa", "Data\\Characters\\FebruaD1.png", "DOWN", 32, 32, 1);
	private JLabel characterSprite = new JLabel(character.imageIcon);
	private Boolean going = false;
	private Boolean paused = false;
	private Timer timer;
	
	private int timerStep = 0;
	private int menuButton = 1;

	private int l1 = KeyEvent.VK_LEFT; private int l2 = KeyEvent.VK_A; private int l3 = KeyEvent.VK_NUMPAD4;
	private int r1 = KeyEvent.VK_RIGHT; private int r2 = KeyEvent.VK_D; private int r3 = KeyEvent.VK_NUMPAD6;
	private int u1 = KeyEvent.VK_UP; private int u2 = KeyEvent.VK_W; private int u3 = KeyEvent.VK_NUMPAD8;
	private int d1 = KeyEvent.VK_DOWN; private int d2 = KeyEvent.VK_S; private int d3 = KeyEvent.VK_NUMPAD2;
	private int ac1 = KeyEvent.VK_SPACE; private int ac2 = KeyEvent.VK_Q; private int ac3 = KeyEvent.VK_NUMPAD0;
	private int mu1 = KeyEvent.VK_PAGE_UP; private int mu2 = KeyEvent.VK_SHIFT; private int mu3 = KeyEvent.VK_MINUS;
	private int md1 = KeyEvent.VK_PAGE_DOWN; private int md2 = KeyEvent.VK_CONTROL; private int md3 = KeyEvent.VK_EQUALS;
	private int ms1 = KeyEvent.VK_ENTER; private int ms2 = KeyEvent.VK_Z; private int ms3 = KeyEvent.VK_BACK_SLASH;
	
    public Board() {
    	mainPanel.setPreferredSize(new Dimension(800, 800));
    	mainPanel.setLayout(null);
    	mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    	mainPanel.setBackground(background);
    	
    	menuPanel.setPreferredSize(new Dimension(120, 400));
    	menuPanel.setBackground(new Color(224, 224, 224));
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
    	
		timer = new Timer(100, new ActionListener() {
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
					character.setX(character.x - 8);
				}
				else if (character.direction == "RIGHT") {
					character.setX(character.x + 8);
				}
				else if (character.direction == "UP") {
					character.setY(character.y - 8);
				}
				else if (character.direction == "DOWN") {
					character.setY(character.y + 8);
				}
				character.setImageIcon("Data\\Characters\\" + character.month + character.direction.substring(0,  1) + character.step + ".png");
		    	mainPanel.remove(characterSprite);
				characterSprite = new JLabel(character.imageIcon);
		    	mainPanel.add(characterSprite);
		    	characterSprite.setBounds(character.x, character.y, 32, 32);
		    	mainPanel.revalidate();
		    	mainPanel.repaint();
			}
		});
    	
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

    public void loadGame() {
    	//INIT
    	mainPanel.add(characterSprite);
    	characterSprite.setBounds(32,32,32,32);
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
			//TODO check if allowed
			if (allowed) {
				timer.start();
			}
		}
		else if ((k.getKeyCode() == r1 || k.getKeyCode() == r2 || k.getKeyCode() == r3) && !paused && !going) {
			Boolean allowed = true;
			going = true;
			character.setDirection("RIGHT");
			//TODO check if allowed
			if (allowed) {
				timer.start();
			}
		}
		else if ((k.getKeyCode() == u1 || k.getKeyCode() == u2 || k.getKeyCode() == u3) && !paused && !going) {
			Boolean allowed = true;
			going = true;
			character.setDirection("UP");
			//TODO check if allowed
			if (allowed) {
				timer.start();
			}
		}
		else if ((k.getKeyCode() == d1 || k.getKeyCode() == d2 || k.getKeyCode() == d3) && !paused && !going) {
			Boolean allowed = true;
			going = true;
			character.setDirection("DOWN");
			//TODO check if allowed
			if (allowed) {
				timer.start();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent k) {
	}
	@Override
	public void keyTyped(KeyEvent k) {
	}

}
