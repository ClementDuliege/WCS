package data;

import java.util.ArrayList;

public class GroupStage {
	
	private String name;
	private Team team1;
	private Team team2;
	private Team team3;
	private Team team4;
	private ArrayList<Game> listGames=new  ArrayList<Game>() ;
	private ClassementGroupStage ranking;
	
	
	public GroupStage(String name, Team team1, Team team2, Team team3, Team team4) {
		this.name = name;
		this.team1 = team1;
		this.team2 = team2;
		this.team3 = team3;
		this.team4 = team4;
		
		this.ranking=new ClassementGroupStage(team1, team2, team3, team4);
		
		Game game1_2 = new Game(team1,team2);
		listGames.add(game1_2);
		Game game1_3 = new Game(team1,team3);
		listGames.add(game1_3);
		Game game1_4 = new Game(team1,team4);
		listGames.add(game1_4);
		Game game2_3 = new Game(team2,team3);
		listGames.add(game2_3);
		Game game2_4 = new Game(team2,team4);
		listGames.add(game2_4);
		Game game3_4 = new Game(team3,team4);
		listGames.add(game3_4);
		
	}
	
	

	/**
	 * @return the ranking
	 */
	public ClassementGroupStage getRanking() {
		return ranking;
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
		return listGames;
	}

	

	@Override
	public String toString() {
		String value=name+"\n";
		value+="	1- "+team1.getName()+"\n";
		value+="	2- "+team2.getName()+"\n";
		value+="	3- "+team3.getName()+"\n";
		value+="	4- "+team4.getName()+"\n";
		return value;
	}
	
	
	
	
	
	

}
