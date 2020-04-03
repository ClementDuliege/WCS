package data;

import java.util.ArrayList;

/**
 * Class used to implement the GroupStage
 * @author WCS
 *
 */
public class GroupStage {
	
	private String name;
	private Team team1;
	private Team team2;
	private Team team3;
	private Team team4;
	private ArrayList<Game> listGames=new  ArrayList<Game>() ;
	private RankingGroupStage ranking;
	
	
	
	/**
	 * GroupStage constructor.
	 * @param name name 
	 * @param team1 
	 * @param team2
	 * @param team3
	 * @param team4
	 *
	 */
	public GroupStage(String name, Team team1, Team team2, Team team3, Team team4) {
		this.name = name;
		this.team1 = team1;
		this.team2 = team2;
		this.team3 = team3;
		this.team4 = team4;
		
		this.ranking=new RankingGroupStage(team1, team2, team3, team4);
		
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
	 * @return the name
	 */
	public String getName() {
		return name;
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
	 * @return the team3
	 */
	public Team getTeam3() {
		return team3;
	}



	/**
	 * @return the team4
	 */
	public Team getTeam4() {
		return team4;
	}



	/**
	 * @return the listGames
	 */
	public ArrayList<Game> getGames() {
		return listGames;
	}



	/**
	 * @return the ranking
	 */
	public RankingGroupStage getRanking() {
		return ranking;
	}



	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @param team3 the team3 to set
	 */
	public void setTeam3(Team team3) {
		this.team3 = team3;
	}



	/**
	 * @param team4 the team4 to set
	 */
	public void setTeam4(Team team4) {
		this.team4 = team4;
	}



	/**
	 * @param listGames the listGames to set
	 */
	public void setListGames(ArrayList<Game> listGames) {
		this.listGames = listGames;
	}



	/**
	 * @param ranking the ranking to set
	 */
	public void setRanking(RankingGroupStage ranking) {
		this.ranking = ranking;
	}



	@Override
	public String toString() {
		return "GroupStage [name=" + name + ", team1=" + team1 + ", team2=" + team2 + ", team3=" + team3 + ", team4="
				+ team4 + ", listGames=" + listGames + ", ranking=" + ranking + "]";
	}
	
	
	
	
	

}
