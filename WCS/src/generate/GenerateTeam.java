package generate;
import java.util.ArrayList;
import java.util.HashMap;

import data.Player;
import data.Team;


public class GenerateTeam {

	private ArrayList<Team> teams=new ArrayList<Team>();

	public GenerateTeam() {
		
		for(int i=0;i<32;i++) {
				String teamName = "Team "+i;
				GeneratePlayer generatePlayer=new GeneratePlayer();
				ArrayList<Player> playerTeam= new ArrayList<Player> ();
				for(int j=0;j<10;j++) {
					playerTeam.add(generatePlayer.generatePlayer("Player "+j));
					
				}
				Team teamCreated=new Team(teamName,playerTeam);
				teams.add(teamCreated);
			
		}
	}

	public ArrayList<Team> getTeams() {
		return teams;
	}
	
	
	
	
	
}
