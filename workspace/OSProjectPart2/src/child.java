import java.io.*;
import java.util.*;

public class child {

	public child() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Please enter a file name to be sorted");
		String fileName = sc.next();
		
		
		String str = "";
		String file = "";
		try {
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			while ((str = in.readLine()) != null) {
				file = file + str;
				file = file + " ";
			}
			in.close();
			System.out.println(file);
		} catch (IOException e) {
			System.out.println("There was an error reading the file");
		}
		
	}
	

    
//    public child(int id, int parID,String fName){
//        int cID=id;
//        int pID=parID;
//        String temp="";
//        String str;
//        
//        
//        try {
//            BufferedReader in = new BufferedReader(new FileReader(fName));
//            while ((str = in.readLine()) != null) {
//                temp = temp+str;
//                temp = temp+" ";
//            }
//            in.close();
//            System.out.println(temp);
//        } catch (IOException e) {
//            System.out.println("There was an error reading the file");
//        }
//        
//        String sortTemp [] = temp.split(" ");
//        int sortIntTemp []= new int[sortTemp.length];
//        for (int i =0; i<(sortTemp.length);i++)
//        {
//            try{
//                sortIntTemp[i]=Integer.parseInt(sortTemp[i]);
//            }catch (NumberFormatException e) {
//                System.out.println("Must be integers");
//            }
//        }
//        
//        Arrays.sort(sortIntTemp);
//        for (int i =0; i<(sortIntTemp.length);i++)
//        {
//            System.out.print(sortIntTemp[i]+" ");
//        }
//        System.out.println("");
//    }
//    
//    public void setOutPipe() {
//    	
//    	
//    }
}