package test;

import java.util.ArrayList;
import java.util.HashMap;

import data.GroupStage;
import data.Team;
import generate.GenerateGroupStage;
import generate.GenerateTeam;

public class Test {
	
	

	public static void main(String[] args) {
		
		
		GenerateTeam gT=new GenerateTeam();
		ArrayList<Team> teams =gT.getTeams();
		

		
		for(Team t:teams) {
			System.out.println(t.toString());
		}
		
		GenerateGroupStage generateGroupStage=new GenerateGroupStage(teams);
		HashMap<String, GroupStage> groupeStage=generateGroupStage.generateGroupStage1();
		
		for (HashMap.Entry<String, GroupStage> pool : groupeStage.entrySet()) {
		    System.out.println(pool.getValue().toString());
		}
		
	}

}
