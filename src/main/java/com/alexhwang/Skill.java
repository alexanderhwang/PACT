package com.alexhwang;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;


public class Skill { //TODO edit all
	//int id
	String name;
	int rankRequirement;
	int aspectType; //0: stat boost (self), 1: aura (allies), 2: aura (enemies), 3: super aura (self/allies)
	ArrayList<String> affectedEffects; //none
	//vitality, wisdom, strength, intelligence, stamina, dexterity, passion, resolve, agility, luck
	//health, energy, offense, focus, defense, resistance, tolerance, accuracy, evasion, speed, critical rate, cast rate
	//impact, cutting, piercing, magic, psychic, holy, evil
	//neutral, earth, air, water, fire, ice, power, force, wood, poison, metal, bone, mind, spirit, light, darkness, arcanum, heaven, hell, chaos, almighty, void
	//death, wound, petrification, dampness, burn, freeze, shock, pressure, poisoning, confusion, silence, sleep
	ArrayList<Integer> affectedValues;
	int valueType; //0: percent, 1: value
	ArrayList<String> preferences; //preference+value
	String description;
	
	public Skill(final String id) { //Basic skill creation
		readAspect(id);
	}
	
	public void readAspect(final String id) {
		final File aspectData = new File("src\\main\\resources\\InnerData\\Aspects.kg");
		String idCheck = "00000";
		String line;
		String dataString;
		Boolean found = false;
		int progress = 0;
		Boolean done = false;
    	try (BufferedReader bufferedReader = new BufferedReader(new FileReader(aspectData))) {
    		line = bufferedReader.readLine();
    		while (!found) {
        		idCheck = line.substring(0, 7);
    			if (idCheck.equals("\t" + id + ";")) {
    				found = true;
    			}
    			else {
        			line = bufferedReader.readLine();
    			}
    		}
    		line = line.substring(7, line.length() - 1);
    		while (!done) {
    			if (!line.contains(";")) {
    				done = true;
    			}
    			else {
    				dataString = line.substring(1, line.indexOf(';'));
    				switch (progress) {
    				case 0:
    					name = dataString;
    					break;
    				case 1:
    					rankRequirement = Integer.parseInt(dataString);
    					break;
    				case 2:
    					aspectType = Integer.parseInt(dataString);
    					break;
    				case 3:
    					affectedEffects = new ArrayList<String>(Arrays.asList(dataString.split("\\s*,\\s*")));
    					break;
    				case 4:
						final ArrayList<String> tempArray1 = new ArrayList<String>(Arrays.asList(dataString.split("\\s*,\\s*")));
						affectedValues = new ArrayList<Integer>();
    					for (String element : tempArray1) {
    						affectedValues.add((int) Integer.parseInt(element));
    					}
    				case 5:
    					valueType = Integer.parseInt(dataString);
    					break;
    				case 6:
    					preferences = new ArrayList<String>(Arrays.asList(dataString.split("\\s*,\\s*")));
    					break;
    				case 7:
    					description = dataString;
    					break;
    				}
    				line = line.substring(dataString.length() + 2, line.length());
    				//TODO check completion
    				progress++;
    			}
    		}
    		
			bufferedReader.close();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
	
}
