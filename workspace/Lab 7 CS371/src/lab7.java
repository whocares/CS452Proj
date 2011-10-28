import java.io.*;
import java.util.ArrayList;


public class lab7 {

	private ArrayList<ArrayList<String>> names = new ArrayList<ArrayList<String>>();
	private int counter = 0;
	
	public void getFile() {
		File file = new File("C:\\Users\\Dan\\Downloads\\teams2005.txt");
		FileInputStream fis = null;
		DataInputStream dis = null;
		
		
		
		try { 
			fis = new FileInputStream(file);
			dis = new DataInputStream(fis);
			BufferedReader br = new BufferedReader(new InputStreamReader(dis));
			String strLine;
			
			String array[];
			
			while ((strLine = br.readLine()) != null) {
				array = strLine.split(",");
				
				for (int r = 0; r < array.length; r++) {
					//names.add(array[r]);
				}
			}
			
			fis.close();
			dis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
