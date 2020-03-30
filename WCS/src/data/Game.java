package data;

import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

public class Game {
	
	private Team team1;
	private Team team2;
	private int score1;
	private int score2;
	private SortedMap<Integer,String> actions;
	private HashMap<String,Integer> statsPoints = new HashMap<String,Integer>();
	private HashMap<String,Integer> statsBlocks = new HashMap<String,Integer>();
	private HashMap<String,Integer> statsSteals = new HashMap<String,Integer>();
	
	
	public Game(Team team1, Team team2) {
		this.team1 = team1;
		this.team2 = team2;
		this.score1=0;
		this.score2=0;
		actions= new TreeMap<Integer,String>();
		initStats();
	}

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



	/**
	 * @return the actions
	 */
	public SortedMap<Integer, String> getActions() {
		return actions;
	}


	/**
	 * @param actions the actions to set
	 */
	public void setActions(SortedMap<Integer, String> actions) {
		this.actions = actions;
	}


	public Team getTeam1() {
		return team1;
	}


	public void setTeam1(Team team1) {
		this.team1 = team1;
	}


	public Team getTeam2() {
		return team2;
	}


	public void setTeam2(Team team2) {
		this.team2 = team2;
	}


	public int getScore1() {
		return score1;
	}


	public void setScore1(int score1) {
		this.score1 = score1;
	}


	public int getScore2() {
		return score2;
	}


	public void setScore2(int score2) {
		this.score2 = score2;
	}
	
	
	public void addPoint(Team team, int points) {
		if(team.getName().equals(team1.getName())){
			score1 += points;
		}
		else{
			score2 += points;
		}
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String value="";
		value+=getTeam1().getName()+" VS "+getTeam2().getName()+"\n";
		value+="	"+getTeam1().getName()+" : "+getScore1()+"\n";
		value+="	"+getTeam2().getName()+" : "+getScore2()+"\n";
		return value;
	}
	
	
	
	
	

}
