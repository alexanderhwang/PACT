package com.alexhwang;

import javax.swing.ImageIcon;

public class Character{
	String name;
	String month;
	String direction;
	int x;
	int y;
	int step; //Used for sprite
	ImageIcon imageIcon;
	
	public Character(final String name, final String month, final String direction, final int x, final int y,
                     final int step){
		this.name = name;
		this.month = month;
		this.direction = direction;
		this.x = x;
		this.y = y;
		this.step = step;
		this.imageIcon = new ImageIcon("Data\\Characters\\" + month + direction.substring(0,  1) + step + ".png");
	}
	
/*	public String getName() {
		return name;
	}
	public String getMonth() {
		return month;
	}
	public String getDirection() {
		return direction;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public ImageIcon getImageIcon() {
		return imageIcon;
	}*/

	public void setName(final String name) {
		this.name = name;
	}

	public void setMonth(final String month) {
		this.month = month;
	}
	
	public void setDirection(final String direction) {
		this.direction = direction;
	}
	
	public void setX(final int x) {
		this.x = x;
	}
	
	public void setY(final int y) {
		this.y = y;
	}
	
	public void setStep(final int step) {
		this.step = step;
	}
	
	public void setImageIcon(final String iconString) {
		this.imageIcon = new ImageIcon(iconString);
	}
}
