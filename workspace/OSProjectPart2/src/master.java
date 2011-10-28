import java.io.IOException;
import java.util.*;

public class master {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter how many files you would like to input");
		String numFiles = sc.next();
		int num = Integer.parseInt(numFiles);
		int numParents = num / 2;
		for (int i = 0; i < numParents; i++) {
			try {
				Process.exec(parent.class);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
		
	}
	
}
