package com.alexhwang;

import java.util.ArrayList;
import java.util.Arrays;


public class Member {
	Fae fae;
	String chosenName;
	String variety;
	String rank;
	ArrayList<Aspect> aspectArray;
	ArrayList<Skill> skillArray;
	Boolean mainCharacter = false;
	
	int level;
	int vitality; int wisdom; int strength; int intelligence; int stamina; int dexterity; int passion; int resolve; int agility; int luck;
	int health; int currentHealth; int energy; int currentEnergy;
	int offense; int focus; int defense; int resistance; int tolerance; int accuracy; int evasion; int speed; int criticalRate; int castRate;
	
	ArrayList<Integer> attributes = new ArrayList<Integer>(Arrays.asList(vitality, wisdom, strength, intelligence, stamina, dexterity, passion, resolve, agility, luck));
	ArrayList<Integer> basicAttributes = new ArrayList<Integer>(Arrays.asList(health, energy, offense, focus, defense, resistance, tolerance, accuracy, evasion, speed, criticalRate, castRate));
	
	public Member(Fae fae, String chosenName, String variety, String rank, int level) { //default creation
		this.fae = fae;
		this.chosenName = chosenName;
		this.variety = variety;
		if (variety.equals("")) {
			variety = fae.varietyArray.get(0);
		}
		this.rank = rank;
		if (rank.equals("")) {
			rank = fae.rankArray.get(0);
		}
		this.level = level;
		
		//TODO check level, set aspects/skills
		for (int i = 0; i < attributes.size(); i++) {
			attributes.set(i, fae.initialAttributeArray.get(i));
		}
		for (int i = 0; i < basicAttributes.size(); i++) {
			basicAttributes.set(i, fae.initialBasicAttributeArray.get(i));
		}
		currentHealth = health;
		currentEnergy = energy;
	}

	public Member(Fae fae, String chosenName, String variety, String rank, int level, ArrayList<Aspect> aspectArray, ArrayList<Skill> skillArray, Boolean mainCharacter, ArrayList<Integer> attributes, ArrayList<Integer> basicAttributes, int currentHealth, int currentEnergy) {
		this.fae = fae;
		this.chosenName = chosenName;
		this.variety = variety;
		if (variety.equals("")) {
			variety = fae.varietyArray.get(0);
		}
		this.rank = rank;
		if (rank.equals("")) {
			rank = fae.rankArray.get(0);
		}
		this.level = level;
		this.aspectArray = new ArrayList<Aspect>(aspectArray);
		this.skillArray = new ArrayList<Skill>(skillArray);
		this.mainCharacter = mainCharacter;
		this.attributes = new ArrayList<Integer>(attributes);
		this.basicAttributes = new ArrayList<Integer>(basicAttributes);
		this.currentHealth = currentHealth;
		this.currentEnergy = currentEnergy;
	}
	
	public void setMainCharacter() {
		mainCharacter = true;
	}
}

