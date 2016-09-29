import java.util.ArrayList;

import javax.swing.ImageIcon;


public class Fae {
	String name;
	String faeClass;
	ArrayList<String> givenNameArray;
	ArrayList<String> varietyArray;
	ArrayList<String> rankArray;
	ArrayList<String> flagArray;
	ArrayList<Integer> initialAttributeArray; //vitality, wisdom, strength, intelligence, stamina, dexterity, passion, resolve, agility, luck
	ArrayList<Integer> initialHiddenAttributeArray; //health, energy, attack, focus, defense, resistance, tolerance, accuracy, evasion, speed, critical rate, cast rate
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
	//String spriteBase;
	
	ImageIcon frontSprite;
	ImageIcon backSprite;
	
	public Fae(String name) { //Basic fae creation, randomized
		this.name = name;
		setData();
	}
	
	public void setData() {
		//TODO set all init data
		
	}
}
