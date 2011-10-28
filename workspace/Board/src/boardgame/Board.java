package boardgame;

public class Board {
	
	/**
	 * TODO: You need to establish how to tell which player is 
	 * which. Probably going to use one person as caps the other
	 * player as lower case. The only problem this causes is if 
	 * the player is on grass should it be a lower or upper case 
	 * g? Or perhaps have it be a new character all together?
	 */
	
	private char[][] gBoard; //Actual Game board
	private String name; //name of the map to be used
	char grass, dirt, mount, city, hq, prod; //types of terrain
	private int size; //size of the board
	
	public Board(String map) {
		name = map;
		
		if (name.equals("map x")) 
			size = 20;
		else if (name.equals("map y")) 
			size = 50;
		else 
			size = 10;
		
		gBoard = new char[20][20];
		grass = 'g';
		dirt = 'd'; 
		mount = 'm'; //mountain 
		city = 'c'; 
		hq = 'h';
		prod = 'p'; //production building
	}
	
	public void constructMap() {
		if (name.equals("map x")) {
			for (int r = 0; r < size; r++) {
				for (int c = 0; c < size; c++) {
					
				}
			}
		} else if (name.equals("map y")) {
			
		} else {
			
		}
	}
}
