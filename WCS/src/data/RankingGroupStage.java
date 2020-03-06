package data;

import java.util.HashMap;

public class RankingGroupStage {
	
	private Team[] rankingTeams = new Team[4];
	private HashMap<String, Integer> scoreTeams = new HashMap<String, Integer>();
	
	
	

	public RankingGroupStage(Team team1,Team team2,Team team3,Team team4) {
		
		rankingTeams[0]=team1;
		rankingTeams[1]=team2;
		rankingTeams[2]=team3;
		rankingTeams[3]=team4;
		
		scoreTeams.put(team1.getName(), 0);
		scoreTeams.put(team2.getName(), 0);
		scoreTeams.put(team3.getName(), 0);
		scoreTeams.put(team4.getName(), 0);
		
		
	}
	
	
	
	/**
	 * @return the rankingTeams
	 */
	public Team[] getRankingTeams() {
		return rankingTeams;
	}



	public void addPointWinningTeam(String nameTeam) {
		int score =scoreTeams.get(nameTeam);
		score+=3;
		scoreTeams.replace(nameTeam, score);
		refreshRanking();
		
	}
	
	public void refreshRanking() {
		for(int j =0; j<3;j++) {
			for(int i = 0; i<3;i++) {
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
		for ( int i =0;i<4;i++) {
			Team team = rankingTeams[i];
			value+=" 	"+team.getName()+" : "+scoreTeams.get(team.getName())+" points\n";
		}
		return value;
		
	}


	public HashMap<String,Integer> getScoreTeams(){
		return scoreTeams;
	}
	
	

}
