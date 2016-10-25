package com.alexhwang;

import java.util.ArrayList;
import java.util.Arrays;


public class Member {
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
	
	ArrayList<Integer> attributes = new ArrayList<Integer>(Arrays.asList(vitality, wisdom, strength, intelligence, stamina, dexterity, passion, resolve, agility, luck));
	ArrayList<Integer> basicAttributes = new ArrayList<Integer>(Arrays.asList(health, energy, offense, focus, defense, resistance, tolerance, accuracy, evasion, speed, criticalRate, castRate));

	int allowedAspects; //determined by rank+1 by default, unless transformed
	int rankValue;
	
	public Member(Fae fae, String chosenName, int varietyNumber, int level) { //default creation
		this.fae = fae;
		this.chosenName = chosenName;
		this.variety = fae.varietyArray.get(varietyNumber);
		this.level = level;
		for (final String thatRank : fae.rankArray) {
			if (!thatRank.contains("+")) {
				rank = thatRank;
			}
			else {
				final String rankLevel = thatRank.substring(thatRank.indexOf('+'), thatRank.length());
				if (level >= Integer.parseInt(rankLevel)) {
					rank = thatRank;
				} 
				else {
					break;
				}
			}
		}
		allowedAspects = fae.rankArray.indexOf(this.rank) + 1;
		for (int i = 0; i < allowedAspects; i++) {
			//TODO
		}
		for (int i = 0; i < fae.possibleSkillArray.size(); i++) {
			//TODO
		}
		for (int i = 0; i < attributes.size(); i++) {
			attributes.set(i, fae.initialAttributeArray.get(i));
		}
		for (int i = 0; i < basicAttributes.size(); i++) {
			basicAttributes.set(i, fae.initialBasicAttributeArray.get(i));
		}
		//TODO set attributes up by level
		currentHealth = health;
		currentEnergy = energy;
		rankValue = fae.rankOffset + fae.rankArray.indexOf(this.rank);
	}

	public Member(Fae fae, String chosenName, int varietyNumber, String rank, int level, ArrayList<Aspect> aspectArray, ArrayList<Skill> skillArray, Boolean mainCharacter, ArrayList<Integer> attributes, ArrayList<Integer> basicAttributes, int currentHealth, int currentEnergy, int allowedAspects) {
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
	
	public void setMainCharacter() {
		mainCharacter = true;
	}
}

