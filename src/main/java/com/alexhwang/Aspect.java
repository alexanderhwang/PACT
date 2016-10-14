package com.alexhwang;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;

public class Aspect {
	//int id
	String name;
	int rankRequirement;
	int aspectType; //0: stat boost (self), 1: aura (allies), 2: aura (enemies), 3: super aura (self/allies)
	ArrayList<String> affectedEffects; //none
	//vitality, wisdom, strength, intelligence, stamina, dexterity, passion, resolve, agility, luck
	//health, energy, attack, focus, defense, resistance, tolerance, accuracy, evasion, speed, critical rate, cast rate
	//impact, cutting, piercing, magic, psychic, holy, evil
	//neutral, earth, air, water, fire, ice, power, force, wood, poison, metal, bone, mind, spirit, light, darkness, arcanum, heaven, hell, chaos, almighty, void
	//death, wound, petrification, dampness, burn, freeze, shock, pressure, poisoning, confusion, silence, sleep
	ArrayList<Integer> affectedValues;
	int valueType; //0: percent, 1: value
	ArrayList<String> preferences; //preference+value
	
	public Aspect(final String id) { //Basic aspect creation
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
    					faeClass = dataString;
    					break;
    				case 2:
    					givenNameArray = new ArrayList<String>(Arrays.asList(dataString.split("\\s*,\\s*")));
    					break;
    				case 3:
    					varietyArray = new ArrayList<String>(Arrays.asList(dataString.split("\\s*,\\s*")));
    					break;
    				case 4:
    					possibleRankArray = new ArrayList<String>(Arrays.asList(dataString.split("\\s*,\\s*")));
    					break;
    				case 5:
    					flagArray = new ArrayList<String>(Arrays.asList(dataString.split("\\s*,\\s*")));
    					break;
    				case 6:
						final ArrayList<String> tempArray1 = new ArrayList<String>(Arrays.asList(dataString.split("\\s*,\\s*")));
    					initialPreferenceArray = new ArrayList<Integer>();
    					for (String element : tempArray1) {
    						initialPreferenceArray.add((int) Integer.parseInt(element));
    					}
    					break;
    				case 7:
						final ArrayList<String> tempArray2 = new ArrayList<String>(Arrays.asList(dataString.split("\\s*,\\s*")));
    					initialAttributeArray = new ArrayList<Integer>();
    					for (String element : tempArray2) {
    						initialAttributeArray.add((int) Integer.parseInt(element));
    					}
    					break;
    				case 8:
						final ArrayList<String> tempArray3 = new ArrayList<String>(Arrays.asList(dataString.split("\\s*,\\s*")));
    					initialBasicAttributeArray = new ArrayList<Integer>();
    					for (String element : tempArray3) {
    						initialBasicAttributeArray.add((int) Integer.parseInt(element));
    					}
    					break;
    				case 9:
    					possibleAspectArray = new ArrayList<String>(Arrays.asList(dataString.split("\\s*,\\s*")));
    					break;
    				case 10:
    					possibleSkillArray = new ArrayList<String>(Arrays.asList(dataString.split("\\s*,\\s*")));
    					break;
    				case 11:
    					possibleTransformationArray = new ArrayList<String>(Arrays.asList(dataString.split("\\s*,\\s*")));
    					break;
    				case 12:
    					levelClass = Integer.parseInt(dataString);
    					break;
    				case 13:
    					rankOffset = Integer.parseInt(dataString);
    					break;
    				case 14:
						final ArrayList<String> tempArray4 = new ArrayList<String>(Arrays.asList(dataString.split("\\s*,\\s*")));
    					typeArray = new ArrayList<Integer>();
    					for (String element : tempArray4) {
    						typeArray.add((int) Integer.parseInt(element));
    					}
    					break;
    				case 15:
						final ArrayList<String> tempArray5 = new ArrayList<String>(Arrays.asList(dataString.split("\\s*,\\s*")));
    					elementArray = new ArrayList<Integer>();
    					for (String element : tempArray5) {
    						elementArray.add((int) Integer.parseInt(element));
    					}
    					break;
    				case 16:
						final ArrayList<String> tempArray6 = new ArrayList<String>(Arrays.asList(dataString.split("\\s*,\\s*")));
    					ailmentArray = new ArrayList<Integer>();
    					for (String element : tempArray6) {
    						ailmentArray.add(Integer.parseInt(element));
    					}
    					break;
    				case 17:
    					spirit = Integer.parseInt(dataString);
    					break;
    				case 18:
    					experienceMultiplier = Integer.parseInt(dataString);
    					break;
    				case 19:
    					spriteBaseArray = new ArrayList<>(Arrays.asList(dataString.split("\\s*,\\s*")));
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
