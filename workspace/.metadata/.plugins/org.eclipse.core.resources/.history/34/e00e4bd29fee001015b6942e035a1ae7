package math;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class NewThinking {

	int totalSize = 0;
	ArrayList<String> matrix = new ArrayList<String>();
	int[] ees = new int[99];
	int eCount = 0;
	ArrayList<int[]> intArray = new ArrayList<int[]>();

	public NewThinking(String fileName) {

		/*Setting up simple variables to use*/
		File file;;

		/*Starts the reading in from the file*/
		try {		
			file = new File("C:/Users/Dan/" + fileName + ".txt");
			FileInputStream fstream = new FileInputStream(file); 
			DataInputStream in = new DataInputStream(fstream); 
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			String strLine;

			/*This here will read in the file and get rid of the comma and space and then add it to an arraylist*/
			while ((strLine = br.readLine()) != null) {
				if (strLine.length() > 1) {
					String[] line = (strLine.split(", "));
					String complete = "";
					for (int i = 0; i < line.length; i++) {
						complete = complete.concat(line[i]);
					}
					matrix.add(complete);
					totalSize++;
				} else
					matrix.add(strLine);
			}
		} catch (Exception e){
			System.err.println("Error : " + e.getMessage());
		}
		/*Ends reading in from the file and making the array list*/
	}

	public void makeIntArray() {
		/*Creates an array list of integer arrays*/

		for(int i = 0; i < matrix.size(); i++) {
			int[] z = new int[matrix.get(i).length()];
			int count = 0; 
			for (int c = 0; c < matrix.get(i).length(); c++) {
				if (!(matrix.get(i).equals("E"))) {
					z[count] = (Integer.parseInt(matrix.get(i).charAt(c) + ""));
					count++;
				} else 
					count = -1;
			}
			if (count >= 0)
				intArray.add(z);
		}
	}

	public void setUpEs() {
		/* This finds all of the E in the input */

		for (int i = 0; i < matrix.size(); i++) {
			for (int c = 0; c < matrix.get(i).length(); c++) {
				if ((matrix.get(i).equals("E"))) {
					ees[eCount] = i;
					eCount++;
				}
			} 
		}
		/*Ends finding all of the E's*/
	}

	public ArrayList<int[][]> makeMatrixs() {
		/*Start of trying to print out the input in matrix form*/
		ArrayList<int[][]> ma = new ArrayList<int[][]>();
		int oldSize = 0;


		for (int i = 0; i < eCount; i++) {
			int[][] matrixArray = new int[ees[i]-oldSize][ees[i]-oldSize];

			/*Makes everything in matrix array be 0*/
			for (int r = 0; r < ees[i]-oldSize; r++) {
				for (int c = 0; c < ees[i]-oldSize; c++) {
					matrixArray[r][c] = 0;
				}
			}
			/*Finishes making matrix array have all 0s*/

			ma.add(matrixArray);
			oldSize = ees[i] + 1;
		}
		return ma;
	}

	public void insertNums(ArrayList<int[][]> ma) {
		/*Insert numbers into blank matrix*/
		int old = 0;

		for (int i = 0; i < eCount; i++) {
			for (int r = 0; r < ees[i]-old; r++) {
				for (int c = 0; c < ees[i]-old; c++) {

					for (int y = 0; y < intArray.get(c).length;y++) {
						int a = intArray.get(c)[y];
						ma.get(i)[c][a] = 1;
					}
				}
				old = ees[i] + 1;

				for (int c = 0; c < intArray.size(); c++) {
					intArray.remove(0);
				}
			}

		}
		/*ends trying to insert the numbers into the blank matrix*/
	}	

	public void printMatrix(ArrayList<int[][]> ma) {
		/* print out new matrix?*/
		for(int i = 0; i < eCount; i++) {
			for(int r = 0; r < ma.get(i).length; r++) {
				System.out.print("[");
				for (int c = 0; c < ma.get(i).length; c++) {
					System.out.print(" " + ma.get(i)[r][c] + " ");
				}
				System.out.println("]");
			}
			System.out.println();
		}
		/*Ends printing of matrix*/
	}

	/*The goal here is to find the transitivity of one matrix*/
	//How I will accomplish that we will find out!
	public void printTransitive() {
		int[] hold = new int[99];
		ArrayList<int[]> matrixs = new ArrayList<int[]>();
		int counter = 0;

		for (int i = 0; i < matrix.size(); i++) {
			for(int c = 0; c < matrix.get(i).length(); c++) {
				if(matrix.get(i).equals("E")) {
					matrixs.add(hold);
					counter = 0;
				} else {
					hold[counter] = Integer.parseInt(matrix.get(i).charAt(c)+"");
					counter++;
				}
			}
		}//all matrixs are now seperated in the matrixs

		int start, mid, last;
		start = mid = last = 0;
		boolean transitive = false;
		boolean leave = false; 

		for(int i = 0; i < matrixs.size(); i++) {//goes through each matrix
			for(int r = 0; r < matrixs.get(i).length; r++) {
				//grabs the individual numbers in the matrix

				start = matrixs.get(i)[r];
				//so you get the first array and the r number from it

				//since you have the first start, you go to the new element
				for(int c = 0; c < matrixs.get(start).length; c++) {
					//so you now have the new node and you'll then cycle through here
					if (c != r) {
						mid = matrixs.get(start)[c];
						for (int x = 0; x < matrixs.get(mid).length; x++) {
							if ( x != c && x != r) {
								last = matrixs.get(mid)[x];

								/*you don't care if last == start because
								 * start points to a new node, it doesn't represent
								 * the actual node, its the path. Mid represents the
								 * next point not the actual node itself but you don't 
								 * care about that. Last represents a path as well and if 
								 * that path leads back to r which is the original node then 
								 * that sequence is transitive, however the whole matrix must
								 * be transitive not just one part. */
								if (last == r)
									transitive = true;
								else {
									transitive = false; 

//									System.out.print("Node: " + r + " goes to Node: " + 
//											c + " which goes to Node: " + x + " however " + 
//											" Node: " + x + " does not go to Node: " + r + "\n");
									
									System.out.print("Node: " + r + " points to Node: " + 
											c + " who points to Node: " + x + " who " +
											"does not point to Node: " + start + "\n");
									
									leave = true;
									break;
								}
							}
						}
					}
					if (leave == true)
						break;
				}
				if (leave == true) 
					break;
			}
			leave = false;
		}

		if (transitive == true) {
			System.out.println("The node was transitive hurrah!");
		}

	}
}

