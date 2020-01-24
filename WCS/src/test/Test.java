package test;

import java.util.ArrayList;
import data.Team;
import generate.GenerateTeam;

public class Test {
	
	

	public static void main(String[] args) {
		
		
		GenerateTeam gT=new GenerateTeam();
		ArrayList<Team> teams =gT.getTeams();
		
		
		for(Team t:teams) {
			System.out.println(t.toString());
		}

	}

}
