package Data;

import java.util.ArrayList;

public class GroupStage {
	
	private String name;
	private Team team1;
	private Team team2;
	private Team team3;
	private Team team4;
	private ArrayList<Game> games;
	
	public GroupStage(String name, Team team1, Team team2, Team team3, Team team4) {
		this.name = name;
		this.team1 = team1;
		this.team2 = team2;
		this.team3 = team3;
		this.team4 = team4;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Team getTeam3() {
		return team3;
	}

	public void setTeam3(Team team3) {
		this.team3 = team3;
	}

	public Team getTeam4() {
		return team4;
	}

	public void setTeam4(Team team4) {
		this.team4 = team4;
	}

	public ArrayList<Game> getGames() {
		return games;
	}

	public void setGames(ArrayList<Game> games) {
		this.games = games;
	}
	
	
	
	

}
