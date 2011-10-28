

public abstract class test4 {
	
	protected String name;
	protected int Str;
	protected int Int;
	protected int Agi;
	protected int Def;
	protected int Mov;
	protected int Lck;
	protected int Ldr;
	
	protected void setName(String name) {
		this.name = name;
	}
	public void setStr(int str) {
		Str = str;
	}
	public void setInt(int intel) {
		intel = Int;
	}
	public String getName() {
		return name;
	}
	
	public int getStr() {
		return Str;
	}
	
	public int getInt() {
		return Int;
	}
}
