package com.alexhwang;

import java.util.ArrayList;
import java.util.Arrays;

public class Member {
	Fae fae;
	String chosenName;
	String variety;
	String rank;
	//ArrayList<Aspect> aspectArray;
	//ArrayList<Skill> skillArray;
	
	int level;
	int vitality;
	int wisdom;
	int strength;
	int intelligence;
	int stamina;
	int dexterity;
	int passion;
	int resolve;
	int agility;
	int luck;
	int health;
	int currentHealth;
	int energy;
	int currentEnergy;
	int attack;
	int focus;
	int defense;
	int resistance;
	int tolerance;
	int accuracy;
	int evasion;
	int speed;
	int criticalRate;
	int castRate;
	
	ArrayList<Integer> attributes = new ArrayList<Integer>(Arrays.asList(vitality, wisdom, strength, intelligence, stamina, dexterity, passion, resolve, agility, luck));
	ArrayList<Integer> hiddenAttributes = new ArrayList<Integer>(Arrays.asList(attack, focus, defense, resistance, tolerance, accuracy, evasion, speed, criticalRate, castRate));
	
	public Member(final Fae fae, final String chosenName, final String variety, final String rank) {
		this.fae = fae;
		this.chosenName = chosenName;
		this.variety = variety;
		this.rank = rank;
	}
}
