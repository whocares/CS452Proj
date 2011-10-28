package math;

import java.util.ArrayList;
import java.util.Scanner;

public class NewThinkingMain {

	public static void main (String[] args) {

		/*Start of trying to get the file name and retrieving the file*/
		System.out.println("This program creates the matrix representation of a relation");
		System.out.println("and determines if the relation is transitive\n");

		System.out.println("Please enter in a text file: "); 
		Scanner sc = new Scanner(System.in);

		System.out.println("\n");//adds two blank lines

		String fileName = sc.next();
		/*Ends acquiring the file name*/

		NewThinking nt = new NewThinking(fileName);
		nt.makeIntArray();
		nt.setUpEs();
		ArrayList<int[][]> hold = nt.makeMatrixs();
		nt.insertNums(hold);
		nt.printMatrix(hold);
		nt.printTransitive();
	}
}
