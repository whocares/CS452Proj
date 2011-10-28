package game;

public class Game {

	private String difficulty;
	
	public Game() {
		difficulty = "Medium";
	}
	
	public void setDifficulty(String diff) {
		if (diff.equals("Medium") || diff.equals("Hard") || diff.equals("Easy"))
			difficulty = diff;
	}
	
	
}
