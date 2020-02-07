package main;

import java.util.ArrayList;
import data.ClassementGroupStage;
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
			//System.out.println(t.toString());
		}
		
		GenerateGroupStage generateGroupStage=new GenerateGroupStage(teams);
		HashMap<String, GroupStage> groupeStage=generateGroupStage.generateGroupStage1();
		
		for (HashMap.Entry<String, GroupStage> pool : groupeStage.entrySet()) {
		    
		}
	
		 
		Team team1 = teams.get(1);
		Team team2 = teams.get(2);
		Team team3 = teams.get(3);
		Team team4 = teams.get(4);
		
		Game game = new Game(team1, team2);
		GameSimulation gameTest = new GameSimulation(game);
		
		System.out.println(game.getTeam1().getShot2Pts()+" DDDD" +game.getTeam1().getBlock() + " CLEMENT : " + game.getTeam2().getShot2Pts()+"FAFA"+game.getTeam2().getBlock() );
		
		gameTest.play();
		System.out.println("team1 : " + gameTest.getGame().getScore1()+" TEAM 2 : " +gameTest.getGame().getScore2() );
		
		ClassementGroupStage c = new ClassementGroupStage(team1,team2,team3,team4);
		c.addPointWinningTeam(team2.getName());
		c.addPointWinningTeam(team2.getName());
		c.addPointWinningTeam(team4.getName());
		
		System.out.print(c.toString());
		
 
	}

}
