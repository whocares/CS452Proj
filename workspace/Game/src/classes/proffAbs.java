package classes;

import characters.characterAbs;

public abstract class proffAbs  {
	
	protected String name;
	protected int limit;
	protected int strGain, intGain, agiGain, defGain;
	protected int hpGain, luckGain, acc;
	protected characterAbs chara;
	
	public proffAbs(characterAbs chara) {
		this.chara = chara;
	}
	
	public String getClassName() {
		return name;
	}
	
	public int getLimit() {
		return limit;
	}

	public void levelUp() {
		chara.setAgi(agiGain + chara.getAgi());
		chara.setDef(defGain + chara.getDef());
		chara.setInt(intGain + chara.getInt());
		chara.setStr(strGain + chara.getStr());
		chara.setLck(luckGain + chara.getLck());
		chara.setHp(hpGain + chara.getHP());
		chara.setAcc(acc + chara.getAcc());
	}
	
} 