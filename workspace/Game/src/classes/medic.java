package classes;

import characters.characterAbs;

public class medic extends proffAbs {
	
	public medic(characterAbs chara) {
		super(chara);
		strGain = 2;
		intGain = 8;
		agiGain = 4;
		hpGain = 6;
		luckGain = 6;
		acc = 5; 
		defGain = 4;
		name = "Medic";
		limit = 0;
	}
}
