package generate;
import java.util.ArrayList;

import data.Player;
import data.Team;


public class GenerateTeam {
 
	private ArrayList<Team> teams=new ArrayList<Team>();
	private GenerateInformations nameTeam = new GenerateInformations();

	public GenerateTeam() {
		
		String[][] informations = nameTeam.infoTeam();
		int position=1;  
		for(int i=0;i<32;i++) {
				String teamName = informations[i][0];
				GeneratePlayer generatePlayer=new GeneratePlayer();
				ArrayList<Player> playerTeam= new ArrayList<Player> ();
				for(int j=1;j<11;j++) {
					playerTeam.add(generatePlayer.generatePlayer(informations[i][j],position));
					position++;
					if(position==6) {
						position=1;
					}
					
				}
				Team teamCreated=new Team(teamName,playerTeam);
				teams.add(teamCreated);
			
		}
	}

	public ArrayList<Team> getTeams() {
		return teams;
	} 
	
	
}
