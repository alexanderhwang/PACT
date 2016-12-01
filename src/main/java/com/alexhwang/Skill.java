package com.alexhwang;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;


public class Skill {
	static final int IBASIZE = 12;
	
	//int id
	String name;
	ArrayList<String> requiredFlags; //includes rank[int], level[int]
	int healthCost;
	int energyCost;
	int target;	//0: 1 front enemy (100%), 1: 1 enemy (90%), 2: 2 front/back enemies (75%), 3: front enemy line (50%), 4: enemy line (45%), 5: enemy party (33%),
				//6: 1 ally (100%), 7: ally line (50%), 8: ally party (30%), 9: 1 enemy or ally (95%), 10: enemy or ally line (48%), 11: enemy or ally party (32%),
				//12: random enemy (120%), 13: random ally (125%), 14: random enemy or ally (150%), 15: 2-5 random enemies (40%), 16: everyone (100%)
	ArrayList<Integer> skillTypes; //0: impact, 1: cutting, 2: piercing, 3: magic, 4: psychic, 5: holy, 6: evil, 7: special (no/pure damage)
	ArrayList<Integer> skillElements; //0: neutral, 1: earth, 2: air, 3: water, 4: fire, 5: ice, 6: power, 7: force, 8: wood, 9: poison, 10: metal, 11: bone, 12: blood, 
		//13: emotion, 14: mind, 15: spirit, 16: light, 17: darkness, 18: arcanum, 19: heaven, 20: hell, 21: chaos, 22: almighty, 23: void, 24: special (no/pure damage)
	ArrayList<Integer> basicAttributePercentages; //health, energy, offense, focus, defense, resistance, tolerance, accuracy, evasion, speed, critical rate, cast rate
	int chargeValue; //[charge * (1000 / 1000 + cast rate)]
	int rechargeValue; //[recharge * (2000 / 2000 + speed)]
	int baseDamage;
	int accuracyFormula; //0: 100%, 1: 100% (modified by accuracy, determined by target evasion), 2: 100% (modified by 90% accuracy, determined by target evasion),
	//3: 90% 
	//TODO determine further accuracies? maybe re-add accuracy segment
	int damageFormula; //0: no reduction, 1: reduced by 100% defense, 2: reduced by 100% resistance
	ArrayList<String> statusEffects; //effect+percentage
	//death, wound, petrification, dampness, burn, freeze, shock, pressure, poisoning, confusion, silence, sleep
	//health down, energy down, offense down, focus down, defense down, resistance down, tolerance down, accuracy down, evasion down, speed down, critical down, cast down
	//health up, energy up, offense up, focus up, defense up, resistance up, tolerance up, accuracy up, evasion up, speed up, critical up, cast up
		//1,2,3,4,5,6,7,8,9,10,11,12
	String userAnimation; //kick,
	//slash, knock, scratch, stab, rupture, loose, whack, snap, shoot,
	//
	String targetAnimation; //weakhit,
	//slash, bash, scratch, gouge, sever, rupture, snap, bind, knock, smash, whack, gash, skewer, clobber, stab, arrow, bullet,
	//lightshot
	ArrayList<String> preferences; //preference+value
	String description; 
	//Damage: 1-4 minimum, 5-19 minimal, 20-59 minor, 60-139 mediocre, 140-
	//Percent: 1-5 minimum, 6-15 minimal, 16-25 minor, 26-39 mediocre, 40-59 medium, 60-79 major, 80-99 massive
	//Effect: 1 slightly, 2 somewhat, 3 significantly, 4 severely
	
	public Skill(final String id) { //Basic skill creation
		readAspect(id);
	}
	
	public String parseTarget() {
		//TODO finish (if applicable)
		String targetString = "";
		switch (target) {
		case 0:
			targetString = "<font color=rgb(225,125,0)>1 enemy in the front row";
			break;
		case 1:
			targetString = "<font color=rgb(225,75,0)>1 enemy";
			break;
		case 2:
			targetString = "<font color=rgb(225,25,25>1 enemy in the front row and the one behind it";
			break;
		case 3:
			targetString = "<font color=rgb(225,125,75)>enemies in the front row";
			break;
		case 4:
			targetString = "<font color=rgb(225,75,75)>enemies in a row";
			break;
		case 5:
			targetString = "<font color=rgb(255,0,0)>all enemies";
			break;
		case 6:
			targetString = "<font color=rgb(0,75,225)>1 ally";
			break;
		case 7:
			targetString = "<font color=rgb(75,75,225)>allies in a row";
			break;
		case 8:
			targetString = "<font color=rgb(0,0,255)>all allies";
			break;
		case 9:
			targetString = "<font color=rgb(225,75,225)>1 enemy or ally";
			break;
		case 10:
			targetString = "<font color=rgb(255,75,255)>enemies or allies in a row";
			break;
		case 11:
			targetString = "<font color=rgb(255,0,255)>all enemies or allies";
			break;
		case 12:
			targetString = "<font color=rgb(225,175,0)>1 random enemy";
			break;
		case 13:
			targetString = "<font color=rgb(0,175,225)>1 random ally";
			break;
		case 14:
			targetString = "<font color=rgb(225,175,225)>1 random enemy or ally";
			break;
		case 15:
			targetString = "<font color=rgb(225,225,0)>random enemies";
			break;
		case 16:
			targetString = "<font color=rgb(255,0,255)>everyone";
			break;
		}
		return targetString + "</font>";
	}
	
	public String parseTypes() {
		String typeString = "";
		for (int i = 0; i < skillTypes.size(); i++) {
			switch (skillTypes.get(i)) {
			case 0:
				typeString += "<font color=rgb(175,175,125)>Impact</font>";
				break;
			case 1:
				typeString += "<font color=rgb(255,175,25)>Cutting</font>";
				break;
			case 2:
				typeString += "<font color=rgb(175,255,25)>Piercing</font>";
				break;
			case 3:
				typeString += "<font color=rgb(0,255,125)>Magic</font>";
				break;
			case 4:
				typeString += "<font color=rgb(125,0,255)>Psychic</font>";
				break;
			case 5:
				typeString += "<font color=rgb(255,225,255)>Holy</font>";
				break;
			case 6:
				typeString += "<font color=rgb(125,75,125)>Evil</font>";
				break;
			case 7:
				typeString += "-";
				break;
			default:
				break;
			}
			if (i != skillTypes.size() - 1) {
				typeString += "/";
			}
		}
		return typeString;
	}
	
	public String parseElements() {
		String elementString = "";
		for (int i = 0; i < skillElements.size(); i++) {
			switch (skillElements.get(i)) {
			case 0:
				elementString += "<font color=rgb(225,225,225)>Neutral</font>";
				break;
			case 1:
				elementString += "<font color=rgb(150,75,25)>Earth</font>";
				break;
			case 2:
				elementString += "<font color=rgb(200,225,200)>Air</font>";
				break;
			case 3:
				elementString += "<font color=rgb(0,0,255)>Water</font>";
				break;
			case 4:
				elementString += "<font color=rgb(255,0,0)>Fire</font>";
				break;
			case 5:
				elementString += "<font color=rgb(0,255,255)>Ice</font>";
				break;
			case 6:
				elementString += "<font color=rgb(255,255,0)>Power</font>";
				break;
			case 7:
				elementString += "<font color=rgb(25,10,125)>Force</font>";
				break;
			case 8:
				elementString += "<font color=rgb(150,175,50)>Wood</font>";
				break;
			case 9:
				elementString += "<font color=rgb(100,0,200)>Poison</font>";
				break;
			case 10:
				elementString += "<font color=rgb(125,125,125)>Metal</font>";
				break;
			case 11:
				elementString += "<font color=rgb(225,225,220)>Bone</font>";
				break;
			case 12:
				elementString += "<font color=rgb(175,0,10)>Blood</font>";
				break;
			case 13:
				elementString += "<font color=rgb(255,100,175)>Emotion</font>";
				break;
			case 14:
				elementString += "<font color=rgb(225,150,255)>Mind</font>";
				break;
			case 15:
				elementString += "<font color=rgb(150,255,225)>Spirit</font>";
				break;
			case 16:
				elementString += "<font color=rgb(255,255,205)>Light</font>";
				break;
			case 17:
				elementString += "<font color=rgb(0,0,75)>Darkness</font>";
				break;
			case 18:
				elementString += "<font color=rgb(255,100,50)>Arcanum</font>";
				break;
			case 19:
				elementString += "<font color=rgb(255,225,150)>Heaven</font>";
				break;
			case 20:
				elementString += "<font color=rgb(100,0,0)>Hell</font>";
				break;
			case 21:
				elementString += "<font color=rgb(255,125,0)>Chaos</font>";
				break;
			case 22:
				elementString += "<font color=rgb(255,0,200)>Almighty</font>";
				break;
			case 23:
				elementString += "<font color=rgb(0,75,25)>Void</font>";
				break;
			case 24:
				elementString += "-";
				break;
			default:
				break;
			}
			if (i != skillElements.size() - 1) {
				elementString += "/";
			}
		}
		return elementString;
	}
	
	public int returnCharge(final int castRate) {
		return (int) (chargeValue * (1000.0 / (1000.0 + castRate)));
	}

	public int returnRecharge(final int speed) {
		return (int) (rechargeValue * (2000.0 / (2000.0 + speed)));
	}
	
	public String returnChargeString(final int castRate) {
		int trueCharge = returnCharge(castRate);
		int shavedColor = (int) (255.0 * ((double) trueCharge / (double) chargeValue));
		return "<font color=rgb(" + shavedColor + ",255," + shavedColor + ")>" + trueCharge + "</font>";
	}

	public String returnRechargeString(final int speed) {
		int trueRecharge = returnRecharge(speed);
		int shavedColor = (int) (255.0 * ((double) trueRecharge / (double) rechargeValue));
		return "<font color=rgb(" + shavedColor + ",255," + shavedColor + ")>" + trueRecharge + "</font>";
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
						final ArrayList<String> tempArray0 = new ArrayList<String>(Arrays.asList(dataString.split("\\s*,\\s*")));
						skillTypes = new ArrayList<Integer>();
    					for (String element : tempArray0) {
    						skillTypes.add((int) Integer.parseInt(element));
    					}
    					break;
    				case 6:
						final ArrayList<String> tempArray1 = new ArrayList<String>(Arrays.asList(dataString.split("\\s*,\\s*")));
						skillElements = new ArrayList<Integer>();
    					for (String element : tempArray1) {
    						skillElements.add((int) Integer.parseInt(element));
    					}
    					break;
    				case 7:
						final ArrayList<String> tempArray2 = new ArrayList<String>(Arrays.asList(dataString.split("\\s*,\\s*")));
						if (tempArray2.size() != IBASIZE) { //error checking
							System.out.println("Skill basicAttributePercentages error: " + id);
						}
						basicAttributePercentages = new ArrayList<Integer>();
    					for (String element : tempArray2) {
    						basicAttributePercentages.add((int) Integer.parseInt(element));
    					}
    					break;
    				case 8:
    					chargeValue = Integer.parseInt(dataString);
    					break;
    				case 9:
    					rechargeValue = Integer.parseInt(dataString);
    					break;
    				case 10:
    					baseDamage = Integer.parseInt(dataString);
    					break;
    				case 11:
    					accuracyFormula = Integer.parseInt(dataString);
    					break;
    				case 12:
    					damageFormula = Integer.parseInt(dataString);
    					break;
    				case 13:
    					statusEffects = new ArrayList<String>(Arrays.asList(dataString.split("\\s*,\\s*")));
    					break;
    				case 14:
    					userAnimation = dataString;
    					break;
    				case 15:
    					targetAnimation = dataString;
    					break;
    				case 16:
    					preferences = new ArrayList<String>(Arrays.asList(dataString.split("\\s*,\\s*")));
    					break;
    				case 17:
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
