package com.alexhwang;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

public class Zone {
	private static final int ML = 32;
	int id;
	ArrayList<Thing> thingArray = new ArrayList<Thing>();
	ArrayList<Thing> falseThingArray = new ArrayList<Thing>();
	private Color fieldColor;
	//private Color foregroundColor;
	private Color menuColor;
	private String areaMusic;

	public Zone(int id) {
		this.id = id;
		populate(id);
	}
	public Zone(int id, ArrayList<Thing> thingArray, ArrayList<Thing> falseThingArray) {
		this.id = id;
		for (Thing nowThing : thingArray) {
			ArrayList<String> nowThingMiscClone = new ArrayList<String>();
    		nowThingMiscClone.addAll(nowThing.misc);
			this.thingArray.add(new Thing(nowThing.name, nowThing.type, nowThing.direction, nowThing.x, nowThing.y, nowThing.step, nowThing.action, nowThingMiscClone, true));
		}
		for (Thing nowFalseThing : falseThingArray) {
			ArrayList<String> nowFalseThingMiscClone = new ArrayList<String>();
			nowFalseThingMiscClone.addAll(nowFalseThing.misc);
			this.falseThingArray.add(new Thing(nowFalseThing.name, nowFalseThing.type, nowFalseThing.direction, nowFalseThing.x, 
					nowFalseThing.y, nowFalseThing.step, nowFalseThing.action, nowFalseThingMiscClone, false));
		}
	}
	
	public Color getFieldColor() {
		return fieldColor;
	}
	
	/*public Color getForegroundColor() {
		return foregroundColor;
	}*/
	
	public Color getMenuColor() {
		return menuColor;
	}
	
	public String getAreaMusic() {
		return areaMusic;
	}
	
	public void changeAreaMusic(String areaMusic) {
		this.areaMusic = areaMusic;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void populate(int setupId) {
		switch (setupId) {
		case 0:
			areaMusic = Board.BASE_RESOURCE_PATH + "Music\\Silence.wav";
			fieldColor = new Color(0, 0, 0);
			menuColor = new Color(0, 0, 0);
			break;
		case 1:
			areaMusic = Board.BASE_RESOURCE_PATH + "Music\\Silence.wav";
			fieldColor = new Color(0, 0, 0);
			menuColor = new Color(0, 0, 0);
			break;
		case 2: //TODO make sure this works still
			areaMusic = Board.BASE_RESOURCE_PATH + "Music\\KageyGame.wav";
			fieldColor = new Color(255, 255, 255);
			menuColor = new Color(224, 224, 224);
			
			falseThingArray.add(new Thing("Rock", "Objects", "", ML*4, ML*1, 1, 0, new ArrayList<String>(), false));
			falseThingArray.add(new Thing("Rock", "Objects", "", ML*5, ML*1, 1, 0, new ArrayList<String>(), false));
			falseThingArray.add(new Thing("Rock", "Objects", "", ML*5, ML*4, 1, 0, new ArrayList<String>(), false));
			falseThingArray.add(
					new Thing("Rock", "Objects", "", ML*12, ML*14, 1, 3, 
							new ArrayList<String>(Arrays.asList(
        							"3", "12", "12")), false));
			thingArray.add(new Thing("Rock", "Objects", "", ML*2, ML*2, 1, 0, new ArrayList<String>(), true));
        	thingArray.add(new Thing("Rock", "Objects", "", ML*3, ML*3, 1, 0, new ArrayList<String>(), true));
        	thingArray.add(
        			new Thing("Rock", "Objects", "", ML*6, ML*8, 1, 1, 
        					new ArrayList<String>(Arrays.asList(
        							"<html>The rock is warm to the touch.</html>",
        							"<html>It's also ugly.</html>",
        							"<html>So, so ugly. You're going to be sick because of how ugly it is.</html>")), true));
        	thingArray.add(
        			new Thing("Rock", "Objects", "", ML*21, ML*4, 1, 1, 
        					new ArrayList<String>(Arrays.asList(
        							"<html>The rock is warm to the touch.</html>")), true));
        	thingArray.add(
        			new Thing("Rock", "Objects", "", ML*21, ML*2, 1, 1, 
        					new ArrayList<String>(Arrays.asList(
        							"<html>The rock is warm to the touch.</html>")), true));
        	thingArray.add(
        			new Thing("Rock", "Objects", "", ML*17, ML*4, 1, 1, 
        					new ArrayList<String>(Arrays.asList(
        							"<html>The rock is warm to the touch.</html>")), true));
        	thingArray.add(
        			new Thing("Rock", "Objects", "", ML*17, ML*2, 1, 1, 
        					new ArrayList<String>(Arrays.asList(
        							"<html>The rock is warm to the touch.</html>")), true));
        	thingArray.add(
        			new Thing("Rock", "Objects", "", ML*14, ML*2, 1, 1, 
        					new ArrayList<String>(Arrays.asList(
        							"<html>The rock sucks.</html>")), true));
        	thingArray.add(
        			new Thing("Rock", "Objects", "", ML*13, ML*2, 1, 1, 
        					new ArrayList<String>(Arrays.asList(
        							"<html>The rock suckse e e e e e e e e e e e e e e ee e e ee e e e ee e e e e e e .</html>")), true));
        	thingArray.add(
        			new Thing("Pyane", "Others", "DOWN", ML*4, ML*2, 1, 2, 
        					new ArrayList<String>(Arrays.asList(
        							"<html><b>Pyane:</b> Oi, what's e e ee e e e e e e e e  ee e e e e   e e e e e e e  ee e e e e  ?</html>")), true));
        	thingArray.add(
        			new Thing("Pyane", "Others", "DOWN", ML*5, ML*5, 1, 2, 
        					new ArrayList<String>(Arrays.asList(
        							"<html><b>Pyane:</b> Oi, what's up?</html>")), true));
        	thingArray.add(
        			new Thing("Pyane", "Others", "DOWN", ML*5, ML*6, 1, 2, 
        					new ArrayList<String>(Arrays.asList(
        							"<html><b>Pyane:</b> Oi, what's upee ee  e ee e ee  e ee e e e e e e e e e e  e ee e e e e e e e ?</html>")), true));
        	thingArray.add(
        			new Thing("Pyane", "Others", "DOWN", ML*4, ML*6, 1, 2, 
        					new ArrayList<String>(Arrays.asList(
        							"<html><b>Pyane:</b> Oi, what's upee ee ee e  e e ee e  ee  ee e e e e e e ee ee eeee e eee e eee eee e eee e ?</html>")), true));
        	thingArray.add(
        			new Thing("Pyane", "Others", "DOWN", ML*20, ML*3, 1, 2, 
        					new ArrayList<String>(Arrays.asList(
        							"<html><b>Pyane:</b> Oi, what's upe e e e e  ee e e  e e e ee  e ee  e e e?</html>")), true));
        	thingArray.add(
        			new Thing("Pyane", "Others", "DOWN", ML*20, ML*2, 1, 2, 
        					new ArrayList<String>(Arrays.asList(
        							"<html><b>Pyane:</b> Oi, what's upe e e e e  ee ee  ee e e e e  ee e e e e ?</html>")), true));
        	thingArray.add(
        			new Thing("Pyane", "Others", "DOWN", ML*19, ML*3, 1, 2, 
        					new ArrayList<String>(Arrays.asList(
        							"<html><b>Pyane:</b> Oi, what's up?</html>", 
        							"<html><b>Pyane:</b> Yeh, figures. You always were the quiet one, eh?</html>",
        							"<html><b>Pyane:</b> Gotta love that about you!</html>")), true));
        	thingArray.add(
        			new Thing("Pyane", "Others", "DOWN", ML*18, ML*3, 1, 2, 
        					new ArrayList<String>(Arrays.asList(
        							"<html><b>Pyane:</b> Oi, what's upee ee  e ee e ee  e ee e e e e e e e e e e  e ee e e e e e e e ?</html>")), true));
        	thingArray.add(
        			new Thing("Pyane", "Others", "DOWN", ML*17, ML*3, 1, 2, 
        					new ArrayList<String>(Arrays.asList(
        							"<html><b>Pyane:</b> Oi, what's upee ee  e ee e ee e e e  e e ee  e ee e e e e e e e e e e  e ee e e e e e e e ?</html>")), true));
        	break;
		case 3:
			areaMusic = Board.BASE_RESOURCE_PATH + "Music\\KageyGame.wav";
			fieldColor = new Color(125, 155, 155);
			menuColor = new Color(224, 224, 224);
			
			falseThingArray.add(
					new Thing("Rock", "Objects", "", ML*12, ML*14, 1, 3, 
							new ArrayList<String>(Arrays.asList(
        							"2", "12", "12")), false));
        	break;
		}
	}
}

