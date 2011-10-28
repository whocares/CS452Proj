package characters;

import classes.*;
import skills.*;

public abstract class characterAbs {
	
	protected String name;
	protected proffAbs prof;
	protected int Str;
	protected int Int;
	protected int Agi;
	protected int Def;
	protected int Lck;
	protected int Hp;
	protected int Acc;
	
	public characterAbs() {
		prof = new soldier(this);
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setHp(int hp) {
		Hp = hp;
	}
	
	public void setAcc(int acc) {
		Acc = acc;
	}
	
	public void changeProf(proffAbs prof){
		this.prof = prof;
	}

	public void setStr(int str) {
		Str = str;
	}

	public void setInt(int intel) {
		Int = intel;
	}
	
	public void setAgi(int agi) {
		Agi = agi;
	}
	
	public int getDef() {
		return Def;
	}

	public void setDef(int def) {
		Def = def;
	}

	public int getLck() {
		return Lck;
	}

	public void setLck(int lck) {
		Lck = lck;
	}

	public int getStr() {
		return Str;
	}

	public int getInt() {
		return Int;
	}

	public int getAgi() {
		return Agi;
	}

	public String getName() {
		return name;
	}
	
	public int getAcc() {
		return Acc;
	}
	
	public int getHP() {
		return Hp;
	}
	
	public proffAbs getProf() {
		return prof;
	}
}