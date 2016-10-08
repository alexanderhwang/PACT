package com.alexhwang;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

import com.alexhwang.util.Colors;

public class Zone {
	private static final int ML = 32;
	int id;
	ArrayList<Thing> thingArray = new ArrayList<Thing>();
	ArrayList<FalseThing> falseThingArray = new ArrayList<FalseThing>();
	private Color fieldColor;
	//private Color foregroundColor;
	private Color menuColor;
	private String areaMusic;

	public Zone(final int id) {
		this.id = id;
		populate(id);
	}
	public Zone(final int id, final ArrayList<Thing> thingArray, final ArrayList<FalseThing> falseThingArray) {
		this.id = id;
		for (final Thing nowThing : thingArray) {
            final ArrayList<String> nowThingMiscClone = new ArrayList<String>();
    		nowThingMiscClone.addAll(nowThing.misc);
			this.thingArray.add(new Thing(nowThing.name, nowThing.type, nowThing.direction, nowThing.x, nowThing.y, nowThing.step, nowThing.action, nowThingMiscClone));
		}
		for (final FalseThing nowFalseThing : falseThingArray) {
            final ArrayList<String> nowFalseThingMiscClone = new ArrayList<String>();
			nowFalseThingMiscClone.addAll(nowFalseThing.misc);
			this.falseThingArray.add(new FalseThing(nowFalseThing.name, nowFalseThing.type, nowFalseThing.direction, nowFalseThing.x, 
					nowFalseThing.y, nowFalseThing.step, nowFalseThing.action, nowFalseThingMiscClone));
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
	
	public void changeAreaMusicfinal (String areaMusic) {
		this.areaMusic = areaMusic;
	}
	
	public void setId(final int id) {
		this.id = id;
	}
	
	public void populate(final int setupId) {
		switch (setupId) {
		case 0:
			areaMusic = "Data\\Music\\Silence.wav";
			fieldColor = Colors.BLACK;
			menuColor = Colors.BLACK;

			/*falseThingArray.add(
					new FalseThing("Rock", "Objects", "", ML*5, ML*2, 1, 1, 
							new ArrayList<String>(Arrays.asList(
        							"1", "7", "1"))));*/
			break;
		case 1:
			areaMusic = "Data\\Music\\KageyGame.wav";
			fieldColor = Colors.WHITE;
			menuColor = new Color(224, 224, 224);
			
			falseThingArray.add(new FalseThing("Rock", "Objects", "", ML*4, ML*1, 1, 0, new ArrayList<String>()));
			falseThingArray.add(new FalseThing("Rock", "Objects", "", ML*5, ML*1, 1, 0, new ArrayList<String>()));
			falseThingArray.add(new FalseThing("Rock", "Objects", "", ML*5, ML*4, 1, 0, new ArrayList<String>()));
			falseThingArray.add(
					new FalseThing("Rock", "Objects", "", ML*3, ML*1, 1, 1, 
							new ArrayList<String>(Arrays.asList(
        							"0", "2", "4"))));
			thingArray.add(new Thing("Rock", "Objects", "", ML*2, ML*2, 1, 0, new ArrayList<String>()));
        	thingArray.add(new Thing("Rock", "Objects", "", ML*3, ML*3, 1, 0, new ArrayList<String>()));
        	thingArray.add(
        			new Thing("Rock", "Objects", "", ML*6, ML*8, 1, 1, 
        					new ArrayList<String>(Arrays.asList(
        							"<html>The rock is warm to the touch.</html>",
        							"<html>It's also ugly.</html>",
        							"<html>So, so ugly. You're going to be sick because of how ugly it is.</html>"))));
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
        	break;
        	
		}
	}
}
