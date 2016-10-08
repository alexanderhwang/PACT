package com.alexhwang;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;

public class Fae {
	//int id
	String name;
	String faeClass;
	ArrayList<String> givenNameArray;
	ArrayList<String> varietyArray; //manifest as sex+type (when applicable) separately, check for bracketed values to add to flags
	ArrayList<String> possibleRankArray; //rank+condition
	ArrayList<String> flagArray;
	ArrayList<Integer> initialPreferenceArray; //vitality, wisdom, strength, intelligence, stamina, dexterity, passion, resolve, agility, luck
	ArrayList<Integer> initialAttributeArray; //vitality, wisdom, strength, intelligence, stamina, dexterity, passion, resolve, agility, luck
	ArrayList<Integer> initialBasicAttributeArray; //health, energy, attack, focus, defense, resistance, tolerance, accuracy, evasion, speed, critical rate, cast rate
	ArrayList<String> possibleAspectArray;
	ArrayList<String> possibleSkillArray; //Aspect+condition -- maybe none?
	ArrayList<String> possibleTransformationArray; //Fae, conditions
	int levelClass; //0 - 8: fast, medium to fast, fast to medium, medium, slow to fast, fast to slow, slow to medium, medium to slow, slow
	int rankOffset;
	int typeImpact;
	int typeCutting;
	int typePiercing;
	int typeMagic;
	int typePsychic;
	int typeHoly;
	int typeEvil;
	ArrayList<Integer> typeArray = new ArrayList<Integer>(Arrays.asList(typeImpact, typeCutting, typePiercing, typeMagic, typePsychic, typeHoly, typeEvil));
	int elementNeutral;
	int elementEarth;
	int elementAir;
	int elementWater;
	int elementFire;
	int elementIce;
	int elementPower;
	int elementForce;
	int elementWood;
	int elementPoison;
	int elementMetal;
	int elementBone;
	int elementMind;
	int elementSpirit;
	int elementLight;
	int elementDarkness;
	int elementArcanum;
	int elementHeaven;
	int elementHell;
	int elementChaos;
	int elementAlmighty;
	int elementVoid;
	ArrayList<Integer> elementArray = new ArrayList<Integer>(Arrays.asList(elementNeutral, elementEarth, elementAir, elementWater, elementFire, elementIce, elementPower, elementForce, elementWood, elementPoison,
			elementMetal, elementBone, elementMind, elementSpirit, elementLight, elementDarkness, elementArcanum, elementHeaven, elementHell, elementChaos, elementAlmighty, elementVoid));
	int ailmentDeath;
	int ailmentWound;
	int ailmentPetrification;
	int ailmentDampness;
	int ailmentBurn;
	int ailmentFreeze;
	int ailmentShock;
	int ailmentPressure;
	int ailmentPoisoning;
	int ailmentConfusion;
	int ailmentSilence;
	int ailmentSleep;
	ArrayList<Integer> ailmentArray = new ArrayList<Integer>(Arrays.asList(ailmentDeath, ailmentWound, ailmentPetrification, ailmentDampness, ailmentBurn, ailmentFreeze, ailmentShock,
			ailmentPressure, ailmentPoisoning, ailmentConfusion, ailmentSilence, ailmentSleep));
	int spirit;
	int experienceMultiplier;
	ArrayList<String> spriteBaseArray;
	
	ImageIcon frontSprite;
	ImageIcon backSprite;
	
	public Fae(String id) { //Basic fae creation
		readFae(id);
	}

	public Fae(int menuButton) { //Months only
		String id = "000" + ("" + menuButton).substring(1, 3);
		readFae(id);
	}
	
	public void readFae(String id) {
		File faeData = new File("Data\\InnerData\\Fae.kg");
		String idCheck = "00000";
		String line;
		String dataString;
		Boolean found = false;
		int progress = 0;
		Boolean done = false;
    	try (BufferedReader bufferedReader = new BufferedReader(new FileReader(faeData))) {
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
    					ArrayList<String> tempArray1 = new ArrayList<String>(Arrays.asList(dataString.split("\\s*,\\s*")));
    					initialPreferenceArray = new ArrayList<Integer>();
    					for (String element : tempArray1) {
    						initialPreferenceArray.add((int) Integer.parseInt(element));
    					}
    					break;
    				case 7:
    					ArrayList<String> tempArray2 = new ArrayList<String>(Arrays.asList(dataString.split("\\s*,\\s*")));
    					initialAttributeArray = new ArrayList<Integer>();
    					for (String element : tempArray2) {
    						initialAttributeArray.add((int) Integer.parseInt(element));
    					}
    					break;
    				case 8:
    					ArrayList<String> tempArray3 = new ArrayList<String>(Arrays.asList(dataString.split("\\s*,\\s*")));
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
    					ArrayList<String> tempArray4 = new ArrayList<String>(Arrays.asList(dataString.split("\\s*,\\s*")));
    					typeArray = new ArrayList<Integer>();
    					for (String element : tempArray4) {
    						typeArray.add((int) Integer.parseInt(element));
    					}
    					break;
    				case 15:
    					ArrayList<String> tempArray5 = new ArrayList<String>(Arrays.asList(dataString.split("\\s*,\\s*")));
    					elementArray = new ArrayList<Integer>();
    					for (String element : tempArray5) {
    						elementArray.add((int) Integer.parseInt(element));
    					}
    					break;
    				case 16:
    					ArrayList<String> tempArray6 = new ArrayList<String>(Arrays.asList(dataString.split("\\s*,\\s*")));
    					ailmentArray = new ArrayList<Integer>();
    					for (String element : tempArray6) {
    						ailmentArray.add((int) Integer.parseInt(element));
    					}
    					break;
    				case 17:
    					spirit = Integer.parseInt(dataString);
    					break;
    				case 18:
    					experienceMultiplier = Integer.parseInt(dataString);
    					break;
    				case 19:
    					spriteBaseArray = new ArrayList<String>(Arrays.asList(dataString.split("\\s*,\\s*")));
    					break;
    				}
    				line = line.substring(dataString.length() + 2, line.length());
    				//TODO check completion
    				progress++;
    			}
    		}
    		
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
}
