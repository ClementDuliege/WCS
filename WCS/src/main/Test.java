package main;

import java.util.ArrayList;
import java.util.HashMap;

import data.Game;
import data.GroupStage;
import data.Team;
import generate.GenerateGroupStage;
import generate.GenerateTeam;
import process.GameSimulation;

public class Test {
	
	

	public static void main(String[] args) {
		
		
		GenerateTeam gT=new GenerateTeam();
		ArrayList<Team> teams =gT.getTeams();
		

		
		for(Team t:teams) {
	//		System.out.println(t.toString());
		}
		
		GenerateGroupStage generateGroupStage=new GenerateGroupStage(teams);
		HashMap<String, GroupStage> groupeStage=generateGroupStage.generateGroupStage1();
		
		for (HashMap.Entry<String, GroupStage> pool : groupeStage.entrySet()) {
		  //  System.out.println(pool.getValue().toString());
		}
	
		
		Team team1 = teams.get(1);
		Team team2 = teams.get(2);
		
		Game game = new Game(team1, team2);
		GameSimulation gameTest = new GameSimulation(game);
		
		gameTest.play();
		System.out.println("team1 :" + gameTest.getGame().getScore1()+" TEAM 2 : " +gameTest.getGame().getScore2() );
 
	}

}
