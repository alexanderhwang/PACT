import java.util.ArrayList;

import javax.swing.ImageIcon;

public class FalseThing{
	String name;
	String type;
	String direction;
	int x;
	int y;
	int step; //Used for sprite
	ImageIcon imageIcon;
	int action; //0 = nothing, 1 = zone change (zone, x, y)
	ArrayList<String> misc;

	int flag; //set per interaction

	public FalseThing(String name, String type, String direction, int x, int y, int step, int action, ArrayList<String> misc) {
		this.name = name;
		this.type = type;
		this.direction = direction;
		this.x = x;
		this.y = y;
		this.step = step;
		this.action = action;
		this.misc = misc;
		if (direction != "") {
			this.imageIcon = new ImageIcon("Data\\" + type + "\\" + name + direction.substring(0,  1) + step + ".png");
		}
		else {
			this.imageIcon = new ImageIcon("Data\\" + type + "\\" + name + step + ".png");
		}
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setStep(int step) {
		this.step = step;
	}
	
	public void setImageIcon(String iconString) {
		this.imageIcon = new ImageIcon(iconString);
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
}
