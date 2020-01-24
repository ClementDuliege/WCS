package data;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * It is a data class which named "WorldCup"
 * This class constructs the object which 
 * @author WCS Corporation
 *
 */
public class WorldCup {

	/**
	 * 
	 */
	private HashMap<String, GroupStage> groupStage1; 
	private HashMap<String, GroupStage> groupStage2; 
	private ArrayList<Game> quarterFinal;
	private ArrayList<Game> semiFinal;
	private Game finalGame;
	private Game smallFinalGame;
	private HashMap<String,Team> teams;
	
	
	
	public WorldCup(HashMap<String, Team> teams) {
		this.teams = teams;
	}
	
	
	public HashMap<String, GroupStage> getGroupStage1() {
		return groupStage1;
	}
	public void setGroupStage1(HashMap<String, GroupStage> groupStage1) {
		this.groupStage1 = groupStage1;
	}
	public HashMap<String, GroupStage> getGroupStage2() {
		return groupStage2;
	}
	public void setGroupStage2(HashMap<String, GroupStage> groupStage2) {
		this.groupStage2 = groupStage2;
	}
	public ArrayList<Game> getQuarterFinal() {
		return quarterFinal;
	}
	public void setQuarterFinal(ArrayList<Game> quarterFinal) {
		this.quarterFinal = quarterFinal;
	}
	public ArrayList<Game> getSemiFinal() {
		return semiFinal;
	}
	public void setSemiFinal(ArrayList<Game> semiFinal) {
		this.semiFinal = semiFinal;
	}
	public Game getFinalGame() {
		return finalGame;
	}
	public void setFinalGame(Game finalGame) {
		this.finalGame = finalGame;
	}
	public Game getSmallFinalGame() {
		return smallFinalGame;
	}
	public void setSmallFinalGame(Game smallFinalGame) {
		this.smallFinalGame = smallFinalGame;
	}
	public HashMap<String, Team> getTeams() {
		return teams;
	}
	public void setTeams(HashMap<String, Team> teams) {
		this.teams = teams;
	}
	
	
	
}
