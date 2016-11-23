package com.alexhwang;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

import javax.swing.JLabel;

public class SpeechBox extends JLabel{
	private GeneralPath path;
	private Color fill;
	private Color border;
	
	public SpeechBox(final GeneralPath path, final Color fill, final Color border) {
		this.path = path;
		this.fill = fill;
		this.border = border;
	}

	@Override
	protected void paintComponent(final Graphics g) {
		final Graphics2D graphics2D = (Graphics2D) g;
		graphics2D.setPaint(fill);
		graphics2D.fill(this.path);
		graphics2D.setPaint(border);
		graphics2D.setStroke(new BasicStroke(2));
		graphics2D.draw(this.path);
	}

}
