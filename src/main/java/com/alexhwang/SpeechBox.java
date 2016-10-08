package com.alexhwang;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

import javax.swing.JLabel;

public class SpeechBox extends JLabel{
	private GeneralPath path;
	
	public SpeechBox(GeneralPath path) {
		this.path = path;
	}
	
	protected void paintComponent(final Graphics g) {
		final Graphics2D graphics2D = (Graphics2D) g;
		graphics2D.setPaint(new Color(252, 252, 252, 234));
		graphics2D.fill(path);
		graphics2D.setPaint(new Color(3, 3, 3));
		graphics2D.setStroke(new BasicStroke(2));
		graphics2D.draw(path);
	}

}
