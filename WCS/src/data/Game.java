package data;

import java.util.SortedMap;

public class Game {
	
	private Team team1;
	private Team team2;
	private int score1;
	private int score2;
	private SortedMap<Integer,String> actions;
	
	
	public Game(Team team1, Team team2) {
		this.team1 = team1;
		this.team2 = team2;
		this.score1=0;
		this.score2=0;
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
