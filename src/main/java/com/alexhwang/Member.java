package com.alexhwang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class Member {
	static final int ASPECTMAX = 4;
	static final int SKILLMAX = 8;
	
	Fae fae;
	String chosenName;
	String variety;
	String rank;
	ArrayList<Aspect> aspectArray; //max = 4
	ArrayList<Skill> skillArray; //max = 8
	Boolean mainCharacter = false;
	
	int level;
	int vitality; int wisdom; int strength; int intelligence; int stamina; int dexterity; int passion; int resolve; int agility; int luck;
	int health; int currentHealth; int energy; int currentEnergy;
	int offense; int focus; int defense; int resistance; int tolerance; int accuracy; int evasion; int speed; int criticalRate; int castRate;

	ArrayList<Integer> preferences; //vitality, wisdom, strength, intelligence, stamina, dexterity, passion, resolve, agility, luck
	ArrayList<Integer> attributes = new ArrayList<Integer>(Arrays.asList(vitality, wisdom, strength, intelligence, stamina, dexterity, passion, resolve, agility, luck));
	ArrayList<Integer> basicAttributes = new ArrayList<Integer>(Arrays.asList(health, energy, offense, focus, defense, resistance, tolerance, accuracy, evasion, speed, criticalRate, castRate));

	int allowedAspects; //determined by rank+1 by default, unless transformed
	int rankValue;
	
	public Member(Fae fae, String chosenName, int varietyNumber, int level) { //default creation
		this.fae = fae;
		this.chosenName = chosenName;
		this.variety = fae.varietyArray.get(varietyNumber);
		this.level = level;
		for (final String consideredRank : fae.rankArray) {
			if (!consideredRank.contains("+")) {
				rank = consideredRank;
			}
			else {
				final String rankLevel = consideredRank.substring(consideredRank.indexOf('+'), consideredRank.length() - 1);
				
				if (level >= Integer.parseInt(rankLevel)) {
					rank = consideredRank.substring(0, consideredRank.indexOf('+') - 1);
				} 
				/*else {
					break;
				}*/
			}
		}
		rankValue = fae.rankOffset + fae.rankArray.indexOf(this.rank);
		allowedAspects = fae.rankArray.indexOf(this.rank) + 1;
		preferences = new ArrayList<Integer>(fae.initialPreferenceArray);
		
		aspectArray = new ArrayList<Aspect>();
		for (int h = 0; h < allowedAspects; h++) {
			if (aspectArray.size() < ASPECTMAX) {
				Boolean done = false;
				int loopCheck = 0;
				while (!done) {
					Random random = new Random();
					int randomChoice = random.nextInt(fae.possibleAspectArray.size());
					String randomAspect = fae.possibleAspectArray.get(randomChoice);
					Aspect potentialAspect = new Aspect(randomAspect);
					
					if (rankValue >= potentialAspect.rankRequirement && !aspectArray.contains(potentialAspect)) {
						aspectArray.add(potentialAspect);
						for (String preferenceModule : potentialAspect.preferences) {
							if (preferenceModule.length() >= 1) {
								int consideredPreferenceIndex = Integer.parseInt(preferenceModule.substring(0, preferenceModule.indexOf('+') - 1));
								int consideredPreferenceIncrement = Integer.parseInt(preferenceModule.substring(preferenceModule.indexOf('+'), preferenceModule.length() - 1));
								preferences.set(consideredPreferenceIndex, preferences.get(consideredPreferenceIndex) + consideredPreferenceIncrement);
							}
						}
						done = true;
					}
					else {
						loopCheck++;
						if (loopCheck > fae.rankArray.size()) {
							System.out.println("Member aspectArray error: loopCheck failed for " + fae.name);
							done = true;
						}
					}
				} //TODO check if works
			}
		}
		skillArray = new ArrayList<Skill>();
		for (String consideredSkill : fae.possibleSkillArray) {
			if (skillArray.size() < SKILLMAX) {
				if (!consideredSkill.contains("+")) {
					final Skill potentialSkill = new Skill(consideredSkill);
					if (!skillArray.contains(potentialSkill)) {
						skillArray.add(potentialSkill);
					}
				}
				else {
					final String skillLevel = consideredSkill.substring(consideredSkill.indexOf('+'), consideredSkill.length() - 1);
					final Skill potentialSkill = new Skill(consideredSkill.substring(0, consideredSkill.indexOf('+') - 1));
					if (!skillArray.contains(potentialSkill) && level >= Integer.parseInt(skillLevel)) {
						skillArray.add(potentialSkill);
						for (String preferenceModule : potentialSkill.preferences) {
							if (preferenceModule.length() >= 1) {
								int consideredPreferenceIndex = Integer.parseInt(preferenceModule.substring(0, preferenceModule.indexOf('+') - 1));
								int consideredPreferenceIncrement = Integer.parseInt(preferenceModule.substring(preferenceModule.indexOf('+'), preferenceModule.length() - 1));
								preferences.set(consideredPreferenceIndex, preferences.get(consideredPreferenceIndex) + consideredPreferenceIncrement);
							}
						}
					}
					/*else {
						break;
					}*/
				} //TODO check if works
			}
		}
		for (int j = 0; j < attributes.size(); j++) {
			attributes.set(j, fae.initialAttributeArray.get(j));
		}
		for (int k = 0; k < basicAttributes.size(); k++) {
			basicAttributes.set(k, fae.initialBasicAttributeArray.get(k));
		}
		if (level >= 2) {
			ArrayList<Integer> preferenceReferenceArray = new ArrayList<Integer>();
			int runningSum = 0;
			for (int i = 0; i < preferences.size(); i++) {
				runningSum += preferences.get(i);
				preferenceReferenceArray.add(runningSum);
			}
			for (int l = 2; l <= level; l++) {
				Random random = new Random();
				int randomChoice = random.nextInt(runningSum) + 1;
				for (int m = 0; m < preferenceReferenceArray.size(); m++) {
					if (randomChoice <= preferenceReferenceArray.get(m)) {
						attributes.set(m, attributes.get(m) + 1);
					}
				}
				//TODO check if works
			}
		}
		attributeCorrect();
		currentHealth = health;
		currentEnergy = energy;
	}

	public Member(Fae fae, int level) { //random creation
		Random rand = new Random();
		int randChoice = rand.nextInt(fae.givenNameArray.size());
		
		this.fae = fae;
		this.chosenName = fae.givenNameArray.get(randChoice);
		this.variety = fae.varietyArray.get(randChoice % fae.varietyArray.size());
		this.level = level;
		for (final String consideredRank : fae.rankArray) {
			if (!consideredRank.contains("+")) {
				rank = consideredRank;
			}
			else {
				final String rankLevel = consideredRank.substring(consideredRank.indexOf('+'), consideredRank.length() - 1);
				
				if (level >= Integer.parseInt(rankLevel)) {
					rank = consideredRank.substring(0, consideredRank.indexOf('+') - 1);
				} 
				/*else {
					break;
				}*/
			}
		}
		rankValue = fae.rankOffset + fae.rankArray.indexOf(this.rank);
		allowedAspects = fae.rankArray.indexOf(this.rank) + 1;
		preferences = new ArrayList<Integer>(fae.initialPreferenceArray);
		
		aspectArray = new ArrayList<Aspect>();
		for (int h = 0; h < allowedAspects; h++) {
			if (aspectArray.size() < ASPECTMAX) {
				Boolean done = false;
				int loopCheck = 0;
				while (!done) {
					Random random = new Random();
					int randomChoice = random.nextInt(fae.possibleAspectArray.size());
					String randomAspect = fae.possibleAspectArray.get(randomChoice);
					Aspect potentialAspect = new Aspect(randomAspect);
					
					if (rankValue >= potentialAspect.rankRequirement && !aspectArray.contains(potentialAspect)) {
						aspectArray.add(potentialAspect);
						for (String preferenceModule : potentialAspect.preferences) {
							if (preferenceModule.length() >= 1) {
								int consideredPreferenceIndex = Integer.parseInt(preferenceModule.substring(0, preferenceModule.indexOf('+') - 1));
								int consideredPreferenceIncrement = Integer.parseInt(preferenceModule.substring(preferenceModule.indexOf('+'), preferenceModule.length() - 1));
								preferences.set(consideredPreferenceIndex, preferences.get(consideredPreferenceIndex) + consideredPreferenceIncrement);
							}
						}
						done = true;
					}
					else {
						loopCheck++;
						if (loopCheck > fae.rankArray.size()) {
							System.out.println("Member aspectArray error: loopCheck failed for " + fae.name);
							done = true;
						}
					}
				} //TODO check if works
			}
		}
		skillArray = new ArrayList<Skill>();
		for (String consideredSkill : fae.possibleSkillArray) {
			if (skillArray.size() < SKILLMAX) {
				if (!consideredSkill.contains("+")) {
					final Skill potentialSkill = new Skill(consideredSkill);
					if (!skillArray.contains(potentialSkill)) {
						skillArray.add(potentialSkill);
					}
				}
				else {
					final String skillLevel = consideredSkill.substring(consideredSkill.indexOf('+'), consideredSkill.length() - 1);
					final Skill potentialSkill = new Skill(consideredSkill.substring(0, consideredSkill.indexOf('+') - 1));
					if (!skillArray.contains(potentialSkill) && level >= Integer.parseInt(skillLevel)) {
						skillArray.add(potentialSkill);
						for (String preferenceModule : potentialSkill.preferences) {
							if (preferenceModule.length() >= 1) {
								int consideredPreferenceIndex = Integer.parseInt(preferenceModule.substring(0, preferenceModule.indexOf('+') - 1));
								int consideredPreferenceIncrement = Integer.parseInt(preferenceModule.substring(preferenceModule.indexOf('+'), preferenceModule.length() - 1));
								preferences.set(consideredPreferenceIndex, preferences.get(consideredPreferenceIndex) + consideredPreferenceIncrement);
							}
						}
					}
					/*else {
						break;
					}*/
				} //TODO check if works
			}
		}
		for (int j = 0; j < attributes.size(); j++) {
			attributes.set(j, fae.initialAttributeArray.get(j));
		}
		for (int k = 0; k < basicAttributes.size(); k++) {
			basicAttributes.set(k, fae.initialBasicAttributeArray.get(k));
		}
		if (level >= 2) {
			ArrayList<Integer> preferenceReferenceArray = new ArrayList<Integer>();
			int runningSum = 0;
			for (int i = 0; i < preferences.size(); i++) {
				runningSum += preferences.get(i);
				preferenceReferenceArray.add(runningSum);
			}
			for (int l = 2; l <= level; l++) {
				Random random = new Random();
				int randomChoice = random.nextInt(runningSum) + 1;
				for (int m = 0; m < preferenceReferenceArray.size(); m++) {
					if (randomChoice <= preferenceReferenceArray.get(m)) {
						attributes.set(m, attributes.get(m) + 1);
					}
				}
				//TODO check if works
			}
		}
		attributeCorrect();
		currentHealth = health;
		currentEnergy = energy;
	}
	
	public Member(Fae fae, String chosenName, int varietyNumber, String rank, int level, ArrayList<Aspect> aspectArray, ArrayList<Skill> skillArray, Boolean mainCharacter, ArrayList<Integer> preferences, ArrayList<Integer> attributes, ArrayList<Integer> basicAttributes, int currentHealth, int currentEnergy, int allowedAspects) {
		this.fae = fae;
		this.chosenName = chosenName;
		this.variety = fae.varietyArray.get(varietyNumber);
		this.rank = rank;
		if (rank.equals("")) {
			this.rank = fae.rankArray.get(0);
		}
		this.level = level;
		this.aspectArray = new ArrayList<Aspect>(aspectArray);
		this.skillArray = new ArrayList<Skill>(skillArray);
		this.mainCharacter = mainCharacter;
		this.attributes = new ArrayList<Integer>(attributes);
		this.basicAttributes = new ArrayList<Integer>(basicAttributes);
		this.currentHealth = currentHealth;
		this.currentEnergy = currentEnergy;
		this.allowedAspects = allowedAspects;

		rankValue = fae.rankOffset + fae.rankArray.indexOf(this.rank);
	}
	
	public void attributeCorrect() { //initialization only
		int index = 0;
		for (int basicAttribute : basicAttributes) {
			switch (index) {
			case 0: //health 
				basicAttribute += attributes.get(0) * 5;
				basicAttribute += attributes.get(2) * 2;
				basicAttribute += attributes.get(4) * 3;
				break;
			case 1: //energy 
				basicAttribute += attributes.get(1) * 5;
				basicAttribute += attributes.get(3) * 2;
				basicAttribute += attributes.get(4) * 3;
				break;
			case 2: //offense 
				basicAttribute += attributes.get(2) * 5;
				basicAttribute += attributes.get(5) * 1;
				basicAttribute += attributes.get(6) * 2;
				basicAttribute += attributes.get(7) * 1;
				basicAttribute += attributes.get(8) * 1;
				break;
			case 3: //focus 
				basicAttribute += attributes.get(1) * 1;
				basicAttribute += attributes.get(3) * 5;
				basicAttribute += attributes.get(5) * 1;
				basicAttribute += attributes.get(6) * 2;
				basicAttribute += attributes.get(7) * 1;
				break;
			case 4: //defense 
				basicAttribute += attributes.get(0) * 4;
				basicAttribute += attributes.get(2) * 3;
				basicAttribute += attributes.get(7) * 3;
				break;
			case 5: //resistance 
				basicAttribute += attributes.get(0) * 1;
				basicAttribute += attributes.get(1) * 4;
				basicAttribute += attributes.get(3) * 3;
				basicAttribute += attributes.get(7) * 2;
				break;
			case 6: //tolerance 
				basicAttribute += attributes.get(0) * 2;
				basicAttribute += attributes.get(2) * 1;
				basicAttribute += attributes.get(4) * 3;
				basicAttribute += attributes.get(7) * 3;
				basicAttribute += attributes.get(9) * 1;
				break;
			case 7: //accuracy 
				basicAttribute += attributes.get(3) * 1;
				basicAttribute += attributes.get(5) * 5;
				basicAttribute += attributes.get(8) * 1;
				basicAttribute += attributes.get(9) * 3;
				break;
			case 8: //evasion 
				basicAttribute += attributes.get(4) * 1;
				basicAttribute += attributes.get(5) * 1;
				basicAttribute += attributes.get(7) * 2;
				basicAttribute += attributes.get(8) * 4;
				basicAttribute += attributes.get(9) * 2;
				break;
			case 9: //speed 
				basicAttribute += attributes.get(2) * 1;
				basicAttribute += attributes.get(4) * 1;
				basicAttribute += attributes.get(6) * 3;
				basicAttribute += attributes.get(8) * 5;
				break;
			case 10: //critical rate 
				basicAttribute += attributes.get(5) * 3;
				basicAttribute += attributes.get(6) * 2;
				basicAttribute += attributes.get(9) * 5;
				break;
			case 11: //cast rate 
				basicAttribute += attributes.get(1) * 2;
				basicAttribute += attributes.get(3) * 1;
				basicAttribute += attributes.get(4) * 1;
				basicAttribute += attributes.get(5) * 1;
				basicAttribute += attributes.get(6) * 3;
				basicAttribute += attributes.get(8) * 1;
				basicAttribute += attributes.get(9) * 1;
				break;
			}
			basicAttributes.set(index, basicAttribute);
			index++;
		}
		//System.out.println(basicAttributes);
	}
	
	public void setMainCharacter() {
		mainCharacter = true;
	}
}

