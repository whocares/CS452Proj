package classes;

import characters.characterAbs;

public class tank extends proffAbs {

	public tank(characterAbs chara) {
		super(chara);
		strGain = 6;
		intGain = 4;
		agiGain = 2;
		hpGain = 8;
		luckGain = 2;
		acc = 3; 
		defGain = 10;
		name = "Tank";
		limit = 4;
	}
	
}
