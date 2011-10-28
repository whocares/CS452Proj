import java.io.*;

public class Decode {

	public static File file = new File("C:\\Users\\Dan\\Desktop\\teams2005.txt");
	public static FileReader fr;
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static String delimitor = ",";
	public static String[] teamAtt = new String[5];
	public static String[][] allAtt = new String[119][5];
		
	public static void main(String[] args) {
		readFile();
		writeFile();
		System.out.println("Done");
	}

	public static void readFile(){
		
		String team;
		int i = 0;
		
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);

			while ((team = br.readLine()) != null) {
				teamAtt = team.split(delimitor);
				for(int k = 0; k < teamAtt.length; k++){
					allAtt[i][k] = teamAtt[k];
				}
				i++;
			}
						
			fr.close();
			br.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeFile(){
		
		try{
			bw = new BufferedWriter(new FileWriter("teams.dtd"));
			bw.write("<?xml version=\"1.0\" encoding\"UTF-8\"?>\n");
			bw.write("<!ELEMENT TEAMS (TEAM+)>\n");
			bw.write("<!ELEMENT TEAM (TEAM_ID, LEAGUE, COLLEGE, WINS, LOSSES)>\n");
			bw.write("<!ELEMENT TEAM_ID (#PCDATA)>\n");
			bw.write("<!ELEMENT LEAGUE (#PCDATA)>\n");
			bw.write("<!ELEMENT COLLEGE (#PCDATA)>\n");
			bw.write("<!ELEMENT WINS (#PCDATA)>\n");
			bw.write("<!ELEMENT LOSSES (#PCDATA)>\n");
			bw.newLine();
			
			bw.flush();
			bw.close();
			
			bw = new BufferedWriter(new FileWriter("teams.xml"));
			
			bw.write("<TEAMS>\n");
			for(int i = 0; i < 119; i++){
				if(allAtt[i][2].equals("TexasA&M"))
					allAtt[i][2] = "TexasA&amp;M";
				bw.write("\t<TEAM>\n");
				bw.write("\t\t<TEAM_ID>"+allAtt[i][1]+"</TEAM_ID>\n");
				bw.write("\t\t<LEAGUE>"+allAtt[i][0]+"</LEAGUE>\n");
				bw.write("\t\t<COLLEGE>"+allAtt[i][2]+"</COLLEGE>\n");
				bw.write("\t\t<WINS>"+allAtt[i][3]+"</WINS>\n");
				bw.write("\t\t<LOSSES>"+allAtt[i][4]+"</LOSSES>\n");
				bw.write("\t</TEAM>\n");
			}
			bw.write("</TEAMS>");
			
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			
			try{
				if(bw != null){
					bw.flush();
					bw.close();
				}
			} catch (IOException ex){
				ex.printStackTrace();
			}
		
		}
	}
}