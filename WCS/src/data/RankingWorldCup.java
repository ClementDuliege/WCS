package data;

import java.util.ArrayList;
import java.util.HashMap;

public class RankingWorldCup {
	
	private Team[] rankingTeams = new Team[32];
	private HashMap<String, Integer> scoreTeams = new HashMap<String, Integer>();
	
	
	public RankingWorldCup(ArrayList<Team> teams) {
		for(int i = 0; i< teams.size();i++) {
			scoreTeams.put(teams.get(i).getName(), 0);
			rankingTeams[i]=teams.get(i);
		}
	}
	
	public void addPointWinningTeam(String nameTeam, int point) {
		int score =scoreTeams.get(nameTeam);
		score+=point;
		scoreTeams.replace(nameTeam, score);
		refreshRanking();
		
	}
	
	public void refreshRanking() {
		for(int j =0; j<30;j++) {
			for(int i = 0; i<31;i++) {
				int scoreTeam1 = scoreTeams.get(rankingTeams[i].getName());
				int scoreTeam2 = scoreTeams.get(rankingTeams[i+1].getName());
				if(scoreTeam1<scoreTeam2){
					Team tmp = rankingTeams[i];
					rankingTeams[i]=rankingTeams[i+1];
					rankingTeams[i+1]=tmp;
				}
			}
		}
		
	}
	
	public String toString() {
		
		String value="Ranking\n";
		for ( int i =0;i<32;i++) {
			Team team = rankingTeams[i];
			value+=" 	"+(i+1)+"-- "+team.getName()+" : "+scoreTeams.get(team.getName())+" points\n";
		}
		return value;
		
	}
	
	

}
