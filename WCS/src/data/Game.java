package data;

import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

/**
* This class is used to instanciate the game.
*@author WCS
*/ 
public class Game {
	
	
	private Team team1;  
	private Team team2;
	private int score1;
	private int score2;
	private SortedMap<Integer,String> actions;
	private HashMap<String,Integer> statsPoints = new HashMap<String,Integer>();
	private HashMap<String,Integer> statsBlocks = new HashMap<String,Integer>();
	private HashMap<String,Integer> statsSteals = new HashMap<String,Integer>();
	
	
	
	/**
	 * @param team1 first team
	 * @param team2 second team
	 */
	public Game(Team team1, Team team2) {
		this.team1 = team1;
		this.team2 = team2;
		this.score1=0;
		this.score2=0;
		actions= new TreeMap<Integer,String>();
		initStats();
	}

	
	/**
	 * This class initiate the stats of the 2 teams to 0.
	 *
	 */
	public void initStats() {
		for(int i =0; i<10;i++) {
			statsPoints.put(team1.getPlayers().get(i).getName(), 0);
			statsBlocks.put(team1.getPlayers().get(i).getName(), 0);
			statsSteals.put(team1.getPlayers().get(i).getName(), 0);
			statsPoints.put(team2.getPlayers().get(i).getName(), 0);
			statsBlocks.put(team2.getPlayers().get(i).getName(), 0);
			statsSteals.put(team2.getPlayers().get(i).getName(), 0);
		}
	}

	/**
	 * @return the team1
	 */
	public Team getTeam1() {
		return team1;
	}

	/**
	 * @return the team2
	 */
	public Team getTeam2() {
		return team2;
	}

	/**
	 * @return the score1
	 */
	public int getScore1() {
		return score1;
	}

	/**
	 * @return the score2
	 */
	public int getScore2() {
		return score2;
	}

	/**
	 * @return the actions
	 */
	public SortedMap<Integer, String> getActions() {
		return actions;
	}

	/**
	 * @return the statsPoints
	 */
	public HashMap<String, Integer> getStatsPoints() {
		return statsPoints;
	}

	/**
	 * @return the statsBlocks
	 */
	public HashMap<String, Integer> getStatsBlocks() {
		return statsBlocks;
	}

	/**
	 * @return the statsSteals
	 */
	public HashMap<String, Integer> getStatsSteals() {
		return statsSteals;
	}

	/**
	 * @param team1 the team1 to set
	 */
	public void setTeam1(Team team1) {
		this.team1 = team1;
	}

	/**
	 * @param team2 the team2 to set
	 */
	public void setTeam2(Team team2) {
		this.team2 = team2;
	}

	/**
	 * @param score1 the score1 to set
	 */
	public void setScore1(int score1) {
		this.score1 = score1;
	}

	/**
	 * @param score2 the score2 to set
	 */
	public void setScore2(int score2) {
		this.score2 = score2;
	}

	/**
	 * @param actions the actions to set
	 */
	public void setActions(SortedMap<Integer, String> actions) {
		this.actions = actions;
	}

	/**
	 * @param statsPoints the statsPoints to set
	 */
	public void setStatsPoints(HashMap<String, Integer> statsPoints) {
		this.statsPoints = statsPoints;
	}

	/**
	 * @param statsBlocks the statsBlocks to set
	 */
	public void setStatsBlocks(HashMap<String, Integer> statsBlocks) {
		this.statsBlocks = statsBlocks;
	}

	/**
	 * @param statsSteals the statsSteals to set
	 */
	public void setStatsSteals(HashMap<String, Integer> statsSteals) {
		this.statsSteals = statsSteals;
	}

	
	
	@Override
	public String toString() {
		return "Game [team1=" + team1 + ", team2=" + team2 + ", score1=" + score1 + ", score2=" + score2 + ", actions="
				+ actions + ", statsPoints=" + statsPoints + ", statsBlocks=" + statsBlocks + ", statsSteals="
				+ statsSteals + "]";
	}
	
	
	
	/**
	 * This method is used to a x point the the game choosed.
	 * @param team team to whoch one have to add point
	 * @param points points to had to the team
	 */
	public void addPoint(Team team, int points) {
		if(team.getName().equals(team1.getName())){
			score1 += points;
		}
		else{
			score2 += points;
		}
	}
	
	

}
