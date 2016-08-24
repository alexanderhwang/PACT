import javax.swing.ImageIcon;

public class Character{
	String name;
	String month;
	String direction;
	int x;
	int y;
	int step; //Used for sprite
	ImageIcon imageIcon;
	
	public Character(String name, String month, String direction, int x, int y, int step){
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

	public void setName(String name) {
		this.name = name;
	}

	public void setMonth(String month) {
		this.month = month;
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
}
