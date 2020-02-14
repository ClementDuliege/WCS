package generate;
import java.util.ArrayList;
import java.util.HashMap;

import data.Player;
import data.Team;


public class GenerateTeam {
 
	private ArrayList<Team> teams=new ArrayList<Team>();
	private GenerateInformations nameTeam = new GenerateInformations();

	public GenerateTeam() {
		
		String[][] informations = nameTeam.infoTeam();
		  
		for(int i=0;i<32;i++) {
				String teamName = informations[i][0];
				GeneratePlayer generatePlayer=new GeneratePlayer();
				ArrayList<Player> playerTeam= new ArrayList<Player> ();
				for(int j=1;j<11;j++) {
					playerTeam.add(generatePlayer.generatePlayer(informations[i][j]));
					
				}
				Team teamCreated=new Team(teamName,playerTeam);
				teams.add(teamCreated);
			
		}
	}

	public ArrayList<Team> getTeams() {
		return teams;
	} 
	
	
}
