package generate;

import java.util.ArrayList;
import java.util.HashMap;

import data.GroupStage;
import data.Team;
import usual.MyRandom;


/**
 * Class used to generate the group stages
 * @author WCS
 *
 */
public class GenerateGroupStage {

	 private GroupStage poolA;
	 private GroupStage poolB;
	 private GroupStage poolC;
	 private GroupStage poolD;
	 private GroupStage poolE;
	 private GroupStage poolF;
	 private GroupStage poolG;
	 private GroupStage poolH;
	 
	 private GroupStage poolI;
	 private GroupStage poolJ;
	 private GroupStage poolK;
	 private GroupStage poolL;
	 
	 private ArrayList<Team> teams;
	 private HashMap<String, GroupStage> groupStage1= new HashMap<String, GroupStage>();
	 private HashMap<String, GroupStage> groupStage2= new HashMap<String, GroupStage>();
	 private ArrayList<Team> teamsAlreadyChosen;

	 
	 
	 public GenerateGroupStage(ArrayList<Team> teams) {
		this.teams = teams;
	}
	 
	 
	
	 /**
	  * Generate the first groupStage
	  *@return groupeStage1
	  */
	 public  HashMap<String, GroupStage> generateGroupStage1(){
		teamsAlreadyChosen=new ArrayList<Team>();
		
		poolA=generateOnePool("GROUPE A");
		poolB=generateOnePool("GROUPE B");
		poolC=generateOnePool("GROUPE C");
		poolD=generateOnePool("GROUPE D");
		poolE=generateOnePool("GROUPE E");
		poolF=generateOnePool("GROUPE F");
		poolG=generateOnePool("GROUPE G");
		poolH=generateOnePool("GROUPE H");
		
		groupStage1.put("GROUPE A", poolA);
		groupStage1.put("GROUPE B", poolB);
		groupStage1.put("GROUPE C", poolC);
		groupStage1.put("GROUPE D", poolD);
		groupStage1.put("GROUPE E", poolE);
		groupStage1.put("GROUPE F", poolF);
		groupStage1.put("GROUPE G", poolG);
		groupStage1.put("GROUPE H", poolH);
		
	
		return groupStage1;
	}
	
	
	
	 /**
	  * Generate the second groupStage
	  *@return groupeStage2
	  */
	 public  HashMap<String, GroupStage> generateGroupStage2(){
		teamsAlreadyChosen=new ArrayList<Team>();
		
		poolI=generateOnePool("GROUPE I");
		poolJ=generateOnePool("GROUPE J");
		poolK=generateOnePool("GROUPE K");
		poolL=generateOnePool("GROUPE L");
		
		
		groupStage2.put("GROUPE I", poolI);
		groupStage2.put("GROUPE J", poolJ);
		groupStage2.put("GROUPE K", poolK);
		groupStage2.put("GROUPE L", poolL);
		
		
	
		return groupStage2;
	}
	
	 /**
	  * Generate a fool in the groupStage
	  *@param name group 
	  */
	public GroupStage generateOnePool(String name) {
	
		Team groupStageTeam1=selectRandomTeam();
		Team groupStageTeam2=selectRandomTeam(); 
		Team groupStageTeam3=selectRandomTeam();
		Team groupStageTeam4=selectRandomTeam();
		
		return new GroupStage(name,groupStageTeam1,groupStageTeam2,groupStageTeam3,groupStageTeam4);
		
		
	}
	
	
	 /**
	  * Select a team
	  *@return teamChosen
	  */
	public Team selectRandomTeam() {
		
		int max=teams.size()-1;
		
		int index=MyRandom.getIntIntoMinMax(0, max);
		Team teamChosen=teams.get(index);
		
		while(alreadyChosen(teamChosen)) {
			index=MyRandom.getIntIntoMinMax(0, max);
			teamChosen=teams.get(index);
		}
		
		teamsAlreadyChosen.add(teamChosen);
		
		return teamChosen;	
	}
	
	
	 /**
	  * Select a team
	  *@return teamChosen
	  */
	public boolean alreadyChosen(Team teamPotentialChosen) {
		
		for(Team alreadyChosen : teamsAlreadyChosen ) {
			if(teamPotentialChosen.getName().equals(alreadyChosen.getName())) {
				return true;
			}
		}
		
		return false;
		
	}

	/**
	 * @return the teams
	 */
	public ArrayList<Team> getTeams() {
		return teams;
	}

	/**
	 * @return the teamsAlreadyChosen
	 */
	public ArrayList<Team> getTeamsAlreadyChosen() {
		return teamsAlreadyChosen;
	}

	/**
	 * @param teams the teams to set
	 */
	public void setTeams(ArrayList<Team> teams) {
		this.teams = teams;
	}

	/**
	 * @param teamsAlreadyChosen the teamsAlreadyChosen to set
	 */
	public void setTeamsAlreadyChosen(ArrayList<Team> teamsAlreadyChosen) {
		this.teamsAlreadyChosen = teamsAlreadyChosen;
	}

	
	

	

	

	
	 
	 
}
