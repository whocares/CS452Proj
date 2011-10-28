package classes;

import characters.characterAbs;

/**
* Trained to use all weapons but having no real weakness soldiers are the ideal
* starting class for all warriors as they progress in their training. 
**/

public class soldier extends proffAbs {
	
	public soldier(characterAbs chara) {
		super(chara);
		strGain = 5;
		intGain = 5;
		agiGain = 5;
		hpGain = 5;
		luckGain = 5;
		acc = 5; 
		defGain = 5;
		name = "Soldier";
		limit = 0;
	}
}
