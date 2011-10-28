import java.util.Scanner;


public class test2 extends test4 {
	
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		test2 t = new test2();
		
		System.out.println("What is your characters name?");
		String name = scan.next();
		System.out.println("" + t.placeName(name));
	}
	
	private String placeName(String name) {
		setName(name);
		return getName();
	}
}