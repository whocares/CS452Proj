package classes;

import characters.characterAbs;

public class technician extends proffAbs {

	public technician(characterAbs chara) {
		super(chara);
		strGain = 4;
		intGain = 8;
		agiGain = 4;
		hpGain = 6;
		luckGain = 4;
		acc = 4; 
		defGain = 5;
		name = "Technician";
		limit = 4;
	}
}
