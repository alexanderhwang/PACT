import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class Fae {
	//int id
	String name;
	String faeClass;
	ArrayList<String> givenNameArray;
	ArrayList<String> varietyArray; //manifest as sex and type (when applicable) separately, check for bracketed values to add to flags
	ArrayList<String> rankArray;
	ArrayList<String> flagArray;
	ArrayList<Integer> initialAttributeArray; //vitality, wisdom, strength, intelligence, stamina, dexterity, passion, resolve, agility, luck
	ArrayList<Integer> initialBasicAttributeArray; //health, energy, attack, focus, defense, resistance, tolerance, accuracy, evasion, speed, critical rate, cast rate
	ArrayList<PossibleAspect> possibleAspectArray; //Aspect, condition -- maybe none?
	ArrayList<PossibleSkill> possibleSkillArray; //Aspect, condition -- maybe none?
	ArrayList<Transformation> transformationArray; //Fae, conditions
	LevelClass levelClass;
	int typeImpact;
	int typeCutting;
	int typePiercing;
	int typeMagic;
	int typePsychic;
	int typeHoly;
	int typeEvil;
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
	int manaStorage;
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
    					rankArray = new ArrayList<String>(Arrays.asList(dataString.split("\\s*,\\s*")));
    					break;
    				case 5:
    					flagArray = new ArrayList<String>(Arrays.asList(dataString.split("\\s*,\\s*")));
    					break;
    				case 6:
    					ArrayList<String> tempArray1 = new ArrayList<String>(Arrays.asList(dataString.split("\\s*,\\s*")));
    					initialAttributeArray = new ArrayList<Integer>();
    					for (String element : tempArray1) {
    						initialAttributeArray.add((int) Integer.parseInt(element));
    					}
    					break;
    				case 7:
    					ArrayList<String> tempArray2 = new ArrayList<String>(Arrays.asList(dataString.split("\\s*,\\s*")));
    					initialBasicAttributeArray = new ArrayList<Integer>();
    					for (String element : tempArray2) {
    						initialBasicAttributeArray.add((int) Integer.parseInt(element));
    					}
    					break;
    					//TODO finish
    				}
    				line = line.substring(dataString.length() + 2, line.length());
    				//TODO put string into value, update line
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
