package classes;

import characters.characterAbs;

public class fortress extends proffAbs {

	public fortress(characterAbs chara) {
		super(chara);
		strGain = 15;
		intGain = 5;
		agiGain = 5;
		hpGain = 20;
		luckGain = 5;
		acc = 8; 
		defGain = 22;
		name = "Fortress";
		limit = 14;
	}

}
