package classes;

import characters.characterAbs;

public class FieldOfficer extends proffAbs {

	public FieldOfficer(characterAbs chara) {
		super(chara);
		strGain = 10;
		intGain = 10;
		agiGain = 10;
		hpGain = 10;
		luckGain = 10;
		acc = 10; 
		defGain = 10;
		name = "Field Officer";
		limit = 17;
	}

}
