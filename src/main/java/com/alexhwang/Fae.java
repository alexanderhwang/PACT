package com.alexhwang;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;

public class Fae {
	static final int IPSIZE = 10;
	static final int IASIZE = 10;
	static final int IBASIZE = 12;
	static final int TSIZE = 7;
	static final int ESIZE = 24;
	static final int ASIZE = 12;
	
	//int id
	String name;
	String faeClass;
	ArrayList<String> givenNameArray;
	ArrayList<String> varietyArray; //manifest as sex+type (when applicable) separately, check for bracketed values to add to flags
	ArrayList<String> rankArray; //rank+condition
	ArrayList<String> flagArray;
	ArrayList<Integer> initialPreferenceArray; //vitality, wisdom, strength, intelligence, stamina, dexterity, passion, resolve, agility, luck
	ArrayList<Integer> initialAttributeArray; //vitality, wisdom, strength, intelligence, stamina, dexterity, passion, resolve, agility, luck
	ArrayList<Integer> initialBasicAttributeArray; //health, energy, offense, focus, defense, resistance, tolerance, accuracy, evasion, speed, critical rate, cast rate
	ArrayList<String> possibleAspectArray;
	ArrayList<String> possibleSkillArray; //Aspect+condition -- maybe none?
	ArrayList<String> possibleTransformationArray; //Fae, conditions
	int levelClass; //0: fast, 1: medium to fast, 2: fast to medium, 3: medium, 4: slow to fast, 5: fast to slow, 6: slow to medium, 7: medium to slow, 8: slow
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
	int elementBlood;
	int elementPassion;
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
			elementMetal, elementBone, elementBlood, elementPassion, elementMind, elementSpirit, elementLight, elementDarkness, elementArcanum, elementHeaven, elementHell, elementChaos, elementAlmighty, elementVoid));
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
	int essence;
	int experienceMultiplier;
	ArrayList<String> spriteBaseArray;
	String description;
	
	ImageIcon frontSprite;
	ImageIcon backSprite;
	
	public Fae(final String id) { //Basic fae creation
		readFae(id);
	}

	public Fae(final int menuButton) { //Months only
		final String id = "000" + ("" + menuButton).substring(1, 3);
		readFae(id);
	}
	
	public void readFae(final String id) {
		final File faeData = new File("src\\main\\resources\\InnerData\\Fae.kg");
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
    					rankArray = new ArrayList<String>(Arrays.asList(dataString.split("\\s*,\\s*")));
    					break;
    				case 5:
    					flagArray = new ArrayList<String>(Arrays.asList(dataString.split("\\s*,\\s*")));
    					break;
    				case 6:
						final ArrayList<String> tempArray1 = new ArrayList<String>(Arrays.asList(dataString.split("\\s*,\\s*")));
						if (tempArray1.size() != IPSIZE) { //error checking
							System.out.println("Fae initialPreferenceArray error: " + id);
						}
    					initialPreferenceArray = new ArrayList<Integer>();
    					for (String element : tempArray1) {
    						initialPreferenceArray.add((int) Integer.parseInt(element));
    					}
    					break;
    				case 7:
						final ArrayList<String> tempArray2 = new ArrayList<String>(Arrays.asList(dataString.split("\\s*,\\s*")));
						if (tempArray2.size() != IASIZE) { //error checking
							System.out.println("Fae initialAttributeArray error: " + id);
						}
    					initialAttributeArray = new ArrayList<Integer>();
    					for (String element : tempArray2) {
    						initialAttributeArray.add((int) Integer.parseInt(element));
    					}
    					break;
    				case 8:
						final ArrayList<String> tempArray3 = new ArrayList<String>(Arrays.asList(dataString.split("\\s*,\\s*")));
						if (tempArray3.size() != IBASIZE) { //error checking
							System.out.println("Fae initialBasicAttributeArray error: " + id);
						}
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
						if (tempArray4.size() != TSIZE) { //error checking
							System.out.println("Fae typeArray error: " + id);
						}
    					typeArray = new ArrayList<Integer>();
    					for (String element : tempArray4) {
    						typeArray.add((int) Integer.parseInt(element));
    					}
    					break;
    				case 15:
						final ArrayList<String> tempArray5 = new ArrayList<String>(Arrays.asList(dataString.split("\\s*,\\s*")));
						if (tempArray5.size() != ESIZE) { //error checking
							System.out.println("Fae elementArray error: " + id);
						}
    					elementArray = new ArrayList<Integer>();
    					for (String element : tempArray5) {
    						elementArray.add((int) Integer.parseInt(element));
    					}
    					break;
    				case 16:
						final ArrayList<String> tempArray6 = new ArrayList<String>(Arrays.asList(dataString.split("\\s*,\\s*")));
						if (tempArray6.size() != ASIZE) { //error checking
							System.out.println("Fae ailmentArray error: " + id);
						}
    					ailmentArray = new ArrayList<Integer>();
    					for (String element : tempArray6) {
    						ailmentArray.add(Integer.parseInt(element));
    					}
    					break;
    				case 17:
    					essence = Integer.parseInt(dataString);
    					break;
    				case 18:
    					experienceMultiplier = Integer.parseInt(dataString);
    					break;
    				case 19:
    					spriteBaseArray = new ArrayList<>(Arrays.asList(dataString.split("\\s*,\\s*")));
    					break;
    				case 20:
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
