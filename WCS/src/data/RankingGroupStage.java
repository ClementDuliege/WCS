package data;

import java.util.HashMap;

/**
 * Class used to implement the ranking of a groupStage
 * @author WCS
 *
 */
public class RankingGroupStage {
	
	private Team[] rankingTeams = new Team[4];
	private HashMap<String, Integer> scoreTeams = new HashMap<String, Integer>();
	
	
	

	/**
	 * Constructor RankingGroupStage
	 * @param team1 first team
	 * @param team2 second team
	 * @param team3 third team
	 * @param team4 fourth team
	 */
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


	/**
	 * Add point to the winning team in the ranking
	 * @param nameTeam winningteam name
	 */
	public void addPointWinningTeam(String nameTeam) {
		int score =scoreTeams.get(nameTeam);
		score+=3;
		scoreTeams.replace(nameTeam, score);
		refreshRanking();
		
	}
	
	/**
	 * Refresh ranking according to team points
	 */
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
	
	
	@Override
	public String toString() {
		
		String value="Ranking\n";
		for ( int i =0;i<4;i++) {
			Team team = rankingTeams[i];
			value+=" 	"+team.getName()+" : "+scoreTeams.get(team.getName())+" points\n";
		}
		return value;
		
	}



	/**
	 * @return the scoreTeams
	 */
	public HashMap<String, Integer> getScoreTeams() {
		return scoreTeams;
	}



	/**
	 * @param rankingTeams the rankingTeams to set
	 */
	public void setRankingTeams(Team[] rankingTeams) {
		this.rankingTeams = rankingTeams;
	}



	/**
	 * @param scoreTeams the scoreTeams to set
	 */
	public void setScoreTeams(HashMap<String, Integer> scoreTeams) {
		this.scoreTeams = scoreTeams;
	}



	
	
	

}
