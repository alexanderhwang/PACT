import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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
	//ArrayList<PossibleAspect> possibleAspectArray; //Aspect, condition -- maybe none?
	//ArrayList<PossibleSkill> possibleSkillArray; //Aspect, condition -- maybe none?
	//ArrayList<Transformation> transformationArray; //Fae, conditions
	//LevelClass levelClass;
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
    		System.out.println(line);
    		//TODO read until ;
    		while (!done) {
    			dataString = String.valueOf(bufferedReader.read());
    			while (!dataString.equals(";")) {
    				System.out.println(dataString);
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
