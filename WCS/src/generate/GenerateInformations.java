package generate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GenerateInformations {
	
	public String[][] informationPlayerTeam = new String[32][11];
	
	public String[][] infoTeam() {
	     String csvFile = "teamWCS.csv";
	     String line = "";
	     String cvsSplitBy = ";";
	     int i = 0;

	     try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
	    	 reader.readLine();/*On lit la première ligne car c'est les infos des colonnes*/
	         while ((line = reader.readLine()) != null && i < 32) {/*On lit toutes les lignes de notre fichier*/

	            //On stocke dans notre tableau nos différents éléments graçe à notre séparateur
	             String[]payslip = line.split(cvsSplitBy);
	             
	             
	             informationPlayerTeam[i][0] = payslip[0];
	             //System.out.println("Team: " + namePlayer[i][0]);
	             for (int j = 1; j<11 ; j++) {
	            	 informationPlayerTeam[i][j] = payslip[j];
	            	 
	            	 //System.out.println("Joueur: " + namePlayer[i][j]);
	             }
	                
		         i++;
	         }              
	        
		}
		
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return informationPlayerTeam;
	     
	}

}
