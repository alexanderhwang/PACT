package com.alexhwang;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;


public class Skill { //TODO edit all
	//int id
	String name;
	ArrayList<String> requiredFlags;
	int healthCost;
	int energyCost;
	int target;	//0: 1 front enemy (100%), 1: 1 enemy (90%), 2: 2 front/back enemies (75%), 3: front enemy line (50%), 4: enemy line (45%), 5: enemy party (33%),
				//6: 1 ally (100%), 7: ally line (50%), 8: ally party (30%), 9: 1 enemy or ally (95%), 10: enemy or ally line (48%), 11: enemy or ally party (32%),
				//12: random enemy (120%), 13: random ally (125%), 14: random enemy or ally (150%), 15: 2-5 random enemies (40%), 15: everyone (100%)
	ArrayList<Integer> skillTypes; //0: impact, 1: cutting, 2: piercing, 3: magic, 4: psychic, 5: holy, 6: evil, 7: special (no/pure damage)
	ArrayList<String> skillElements; //neutral, earth, air, water, fire, ice, power, force, wood, poison, metal, bone, mind, spirit, light, darkness, arcanum, heaven, hell, chaos, almighty, void
	ArrayList<Integer> attributePercentages; //health, energy, offense, focus, defense, resistance, tolerance, accuracy, evasion, speed, critical rate, cast rate
	int rechargePercentage;
	int damage;
	int accuracyFormula; //0: 100%, 1: 100% (modified by accuracy, determined by target evasion), 2: 100% (modified by 90% accuracy, determined by target evasion),
	//3: 90%
	int damageFormula; //0: no reduction, 1: reduced by 100% defense, 2: reduced by 100% resistance
	ArrayList<String> statusEffects; //effect+percentage
	//death, wound, petrification, dampness, burn, freeze, shock, pressure, poisoning, confusion, silence, sleep
	//health down, energy down, offense down, focus down, defense down, resistance down, tolerance down, accuracy down, evasion down, speed down, critical down, cast down
		//1,2,3,4,5,6,7,8
	String userAnimation; //kick,
	//slash, bash, scratch, stab, rupture, loose, whack, snap, shoot,
	//
	String targetAnimation; //weakhit,
	//slash, knock, scratch, gouge, sever, rupture, snap, bind, knock, smash, whack, gash, skewer, clobber, stab, arrow, bullet,
	//lightshot
	//String description; 
	//Damage: 1-4 minimum, 5-19 minimal, 20-59 minor, 60-139 mediocre, 140-
	//Percent: 1-5 minimum, 6-15 minimal, 16-25 minor, 26-39 mediocre, 40-59 medium, 60-79 major, 80-99 massive
	//Effect: 1 slightly, 2 somewhat, 3 significantly, 4 severely
	
	public Skill(final String id) { //Basic skill creation
		readAspect(id);
	}
	
	public void readAspect(final String id) {
		final File aspectData = new File("src\\main\\resources\\InnerData\\Skills.kg");
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
    					requiredFlags = new ArrayList<String>(Arrays.asList(dataString.split("\\s*,\\s*")));
    					break;
    				case 2:
    					healthCost = Integer.parseInt(dataString);
    					break;
    				case 3:
    					energyCost = Integer.parseInt(dataString);
    					break;
    				case 4:
    					target = Integer.parseInt(dataString);
    					break;
    				case 5:
						final ArrayList<String> tempArray1 = new ArrayList<String>(Arrays.asList(dataString.split("\\s*,\\s*")));
						skillTypes = new ArrayList<Integer>();
    					for (String element : tempArray1) {
    						skillTypes.add((int) Integer.parseInt(element));
    					}
    				case 6:
    					skillElements = new ArrayList<String>(Arrays.asList(dataString.split("\\s*,\\s*")));
    					break;
    				case 7:
						final ArrayList<String> tempArray2 = new ArrayList<String>(Arrays.asList(dataString.split("\\s*,\\s*")));
						attributePercentages = new ArrayList<Integer>();
    					for (String element : tempArray2) {
    						attributePercentages.add((int) Integer.parseInt(element));
    					}
    				case 8:
    					rechargePercentage = Integer.parseInt(dataString);
    					break;
    				case 9:
    					damage = Integer.parseInt(dataString);
    					break;
    				case 10:
    					accuracyFormula = Integer.parseInt(dataString);
    					break;
    				case 11:
    					damageFormula = Integer.parseInt(dataString);
    					break;
    				case 12:
    					statusEffects = new ArrayList<String>(Arrays.asList(dataString.split("\\s*,\\s*")));
    					break;
    				case 13:
    					userAnimation = dataString;
    					break;
    				case 14:
    					targetAnimation = dataString;
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
