package generate;
import java.util.ArrayList;

import data.Player;
import data.Team;



/**
 * Class used to generate all the teams informations 
 * @author WCS
 *
 */
public class GenerateTeam {
 
	private ArrayList<Team> teams=new ArrayList<Team>();
	private GenerateInformations nameTeam = new GenerateInformations();

	
	 /**
	  * put teams issued from GenerateInformations into list 
	  */
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



	/**
	 * @return the teams
	 */
	public ArrayList<Team> getTeams() {
		return teams;
	}



	/**
	 * @return the nameTeam
	 */
	public GenerateInformations getNameTeam() {
		return nameTeam;
	}



	/**
	 * @param teams the teams to set
	 */
	public void setTeams(ArrayList<Team> teams) {
		this.teams = teams;
	}



	/**
	 * @param nameTeam the nameTeam to set
	 */
	public void setNameTeam(GenerateInformations nameTeam) {
		this.nameTeam = nameTeam;
	}

	
	
	
}
