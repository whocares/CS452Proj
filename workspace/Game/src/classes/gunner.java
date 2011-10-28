package classes;

import characters.characterAbs;

public class gunner extends proffAbs {
	
	public gunner(characterAbs chara) {
		super(chara);
		strGain = 3;
		intGain = 3;
		agiGain = 8;
		hpGain = 4;
		luckGain = 3;
		acc = 10; 
		defGain = 4;
		name = "Gunner";
		limit = 0;
	}
}