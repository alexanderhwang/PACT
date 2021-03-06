package com.alexhwang;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Thing{
	String name;
	String type;
	String direction;
	int x;
	int y;
	int step; //Used for sprite
	int action; //0 = nothing, 1 = generic thing, 2 = generic person, 3 = zone change (zone, x, y)
	ArrayList<String> misc; //TODO insert checks for custom special characters
	Boolean trueness;
	ImageIcon imageIcon;
	
	int flag; //set per interaction

	public Thing(final String name, final String type, final String direction, final int x, final int y,
                 final int step, final int action, final ArrayList<String> misc, final Boolean trueness) {
		this.name = name;
		this.type = type;
		this.direction = direction;
		this.x = x;
		this.y = y;
		this.step = step;
		this.action = action;
		this.misc = misc;
		this.trueness = trueness;
		if (!direction.equals("")) {
			this.imageIcon = new ImageIcon(Board.BASE_RESOURCE_PATH + type + "\\" + name + direction.substring(0,  1) + step + ".png");
		}
		else {
			this.imageIcon = new ImageIcon(Board.BASE_RESOURCE_PATH + type + "\\" + name + step + ".png");
		}
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

	public void setFlag(final int flag) {
		this.flag = flag;
	}
}
