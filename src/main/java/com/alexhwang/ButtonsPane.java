package com.alexhwang;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

public class ButtonsPane extends JOptionPane{
	ArrayList<JButton> buttonButtons = new ArrayList<JButton>();
	JButton bDefault = new JButton("Default"); 
	JButton bConfirm = new JButton("Confirm"); 
	JButton bCancel = new JButton("Cancel"); 
	Boolean confirmation = false;
	ArrayList<Integer> keyArray = new ArrayList<Integer>(Arrays.asList(KeyEvent.VK_LEFT, KeyEvent.VK_A, KeyEvent.VK_NUMPAD4,
			KeyEvent.VK_RIGHT, KeyEvent.VK_D, KeyEvent.VK_NUMPAD6, KeyEvent.VK_UP, KeyEvent.VK_W, KeyEvent.VK_NUMPAD8,
			KeyEvent.VK_DOWN, KeyEvent.VK_S, KeyEvent.VK_NUMPAD2, KeyEvent.VK_SPACE, KeyEvent.VK_Q, KeyEvent.VK_NUMPAD0,
			KeyEvent.VK_V, KeyEvent.VK_P, KeyEvent.VK_0, KeyEvent.VK_PAGE_UP, KeyEvent.VK_SHIFT, KeyEvent.VK_MINUS,
			KeyEvent.VK_PAGE_DOWN, KeyEvent.VK_CONTROL, KeyEvent.VK_EQUALS, KeyEvent.VK_HOME, KeyEvent.VK_PERIOD, KeyEvent.VK_OPEN_BRACKET,
			KeyEvent.VK_END, KeyEvent.VK_SLASH, KeyEvent.VK_CLOSE_BRACKET, KeyEvent.VK_ENTER, KeyEvent.VK_Z, KeyEvent.VK_BACK_SLASH,
			KeyEvent.VK_ESCAPE, KeyEvent.VK_BACK_SPACE, KeyEvent.VK_DELETE, KeyEvent.VK_CAPS_LOCK, KeyEvent.VK_NUM_LOCK, KeyEvent.VK_M));
	ArrayList<String> buttonText = new ArrayList<String>(Arrays.asList("Left 1", "Left 2", "Left 3", "Right 1", "Right 2", "Right 3", 
			"Up 1", "Up 2", "Up 3", "Down 1", "Down 2", "Down 3", "Action 1", "Action 2", "Action 3",
			"Pause 1", "Pause 2", "Pause 3", "Menu Up 1", "Menu Up 2", "Menu Up 3", 
			"Menu Down 1", "Menu Up 2", "Menu Up 3", "Menu Skip Up 1", "Menu Skip Up 2", "Menu Skip Up 3",
			"Menu Skip Down 1", "Menu Skip Down 2", "Menu Skip Down 3", "Menu Action 1", "Menu Action 2", "Menu Action 3",
			"Menu Back 1", "Menu Back 2", "Menu Back 3", "Speed Toggle 1", "Speed Toggle 2", "Speed Toggle 3"));
	int keyArrayIndex = -1;
	int currentButton = -1;
	int i = 0;
	
	public ButtonsPane(final ArrayList<Integer> keyArray) {
		confirmation = false;
		this.keyArray = keyArray;
		setControls();
	}

	public void setControls() {
		for (final int key : keyArray) {
			buttonButtons.add(new JButton(KeyEvent.getKeyText(key)));
		}
		activateButton("");
		this.setLayout(null);
		this.setPreferredSize(new Dimension(500, 600));
		JDialog dialog = this.createDialog(null, "Controls");
		for (i = 0; i < buttonText.size(); i++) {
			JLabel thisLabel = new JLabel(buttonText.get(i));
			this.add(thisLabel);
			thisLabel.setBounds((i%3)*150+36,(i/3)*40,130,20);
			JButton thisButton = buttonButtons.get(i);
			this.add(thisButton);
			thisButton.setBounds((i%3)*150+35,(i/3)*40+20,130,20);
			if (thisButton.getText().equals("Escape") || thisButton.getText().equals("Enter")) {
				thisButton.setEnabled(false);
			}
			else {
				thisButton.addActionListener(new ActionListener() {
					int index = i;
					@Override
					public void actionPerformed(ActionEvent e) {
						keyArrayIndex = index;
						activateButton(e.getActionCommand());
					}
				});
				thisButton.addKeyListener(new KeyListener() {
					@Override
					public void keyPressed(final KeyEvent e) {
                        final int thisKey = e.getKeyCode();
						Boolean conflict = false;
						for (final int key : keyArray) {
							if (thisKey == key) {
								conflict = true;
							}
						}
						/*if (thisKey == KeyEvent.VK_ALT) {
							buttonButtons.get(keyArrayIndex).setBackground(Color.GRAY);
							keyArray.set(keyArrayIndex, 3);
							buttonButtons.get(keyArrayIndex).setText("Key " + (keyArrayIndex+1) + " unset");
						}*/
						if (!conflict) {
							if (keyArrayIndex != -1 && !(thisKey >= KeyEvent.VK_0 && thisKey <= KeyEvent.VK_9)) {
								//buttonButtons.get(keyArrayIndex).setBackground(new JButton().getBackground());
								keyArray.set(keyArrayIndex, thisKey);
								buttonButtons.get(keyArrayIndex).setText(KeyEvent.getKeyText(thisKey));
							}
						}
					}
		
					@Override
					public void keyReleased(KeyEvent e) {
					}
					
					@Override
					public void keyTyped(KeyEvent e) {
					}
				});
			}
		}
		this.add(bDefault);
		bDefault.setBounds(40,550,120,30);
		bDefault.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				keyArray = new ArrayList<Integer>(Arrays.asList(KeyEvent.VK_LEFT, KeyEvent.VK_A, KeyEvent.VK_NUMPAD4,
						KeyEvent.VK_RIGHT, KeyEvent.VK_D, KeyEvent.VK_NUMPAD6, KeyEvent.VK_UP, KeyEvent.VK_W, KeyEvent.VK_NUMPAD8,
						KeyEvent.VK_DOWN, KeyEvent.VK_S, KeyEvent.VK_NUMPAD2, KeyEvent.VK_SPACE, KeyEvent.VK_Q, KeyEvent.VK_NUMPAD0,
						KeyEvent.VK_V, KeyEvent.VK_P, KeyEvent.VK_0, KeyEvent.VK_PAGE_UP, KeyEvent.VK_SHIFT, KeyEvent.VK_MINUS,
						KeyEvent.VK_PAGE_DOWN, KeyEvent.VK_CONTROL, KeyEvent.VK_EQUALS, KeyEvent.VK_HOME, KeyEvent.VK_PERIOD, KeyEvent.VK_OPEN_BRACKET,
						KeyEvent.VK_END, KeyEvent.VK_SLASH, KeyEvent.VK_CLOSE_BRACKET, KeyEvent.VK_ENTER, KeyEvent.VK_Z, KeyEvent.VK_BACK_SLASH,
						KeyEvent.VK_ESCAPE, KeyEvent.VK_BACK_SPACE, KeyEvent.VK_DELETE, KeyEvent.VK_CAPS_LOCK, KeyEvent.VK_NUM_LOCK, KeyEvent.VK_M));
				for (int i = 0; i < keyArray.size(); i++) {
					buttonButtons.get(i).setText(KeyEvent.getKeyText(keyArray.get(i)));
				}
				activateButton("");
			}
		});
		this.add(bConfirm);
		bConfirm.setBounds(190,550,120,30);
		bConfirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				confirmation = true;
				close();
			}
		});
		this.add(bCancel);
		bCancel.setBounds(340,550,120,30);
		bCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				confirmation = false;
				close();
			}
		});
		this.repaint();
		dialog.setVisible(true);
	}
	
	public void activateButton(final String activeString) {
		for (final JButton activeButton : buttonButtons) {
			if (activeButton.getText().equals("Escape") || activeButton.getText().equals("Enter")) {
			}
			else if (activeButton.getText().equals(activeString)) {
				activeButton.setBorder(new LineBorder(Color.RED, 2));
			}
			else {
				activeButton.setBorder(new LineBorder(Color.BLACK, 1));
			}
		}
		currentButton = keyArrayIndex;
	}
	
	public void close() {
		keyArrayIndex = -1;
		i = 0;
        final Window w = SwingUtilities.getWindowAncestor(this);
		w.setVisible(false);
	}

}
