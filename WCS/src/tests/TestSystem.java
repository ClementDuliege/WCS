package tests;

import java.util.ArrayList;
import java.util.Iterator;

import data.GroupStage;
import data.Player;
import data.RankingWorldCup;
import data.Team;
import data.WorldCup;
import generate.GenerateGroupStage;
import ihm.RankingWorldCupFrame;


public class TestSystem {

	
	/***********************************************   Test sur la cohérence des équipes et des joueurs ****************************************************/
	

	public void nbPlayer() {
		int i=0;
		WorldCup wp = new WorldCup();
		for(Iterator<Team> it = wp.getTeams().iterator(); it.hasNext();) {
			Team team = it.next();
			i++;
			if(team.getPlayers().size() == 10) {
				System.out.println("\n" + team.getName() + " : " + i + "\nNumber of players : " + team.getPlayers().size() + "\n");
			}else {
				System.err.println("Erreur du nombre d'equipe participante");
			}
		}
	}
	
	public void nbTeam() {
		WorldCup wp = new WorldCup();
		int i=0;
		for(Iterator<Team> it = wp.getTeams().iterator(); it.hasNext();) {
			Team team = it.next();
			i++;
			if(wp.getTeams().size() == 32) {
				System.out.println("Le Nombre d'equipe participant est Correct : " + i + "\n" + team.getName() + "\n");
			}else {
				System.err.println("Erreur du nombre d'equipe participante");
			}
		}
	}
	
	
	public void statsPlayer() {
		WorldCup wp = new WorldCup();
		for(Iterator<Team> it = wp.getTeams().iterator(); it.hasNext();) {
			Team team = it.next();
			for(Iterator<Player> its = team.getPlayers().iterator(); its.hasNext();) {
				Player player = its.next();
				if(player.getAttack()!=0 || player.getBallHandle()!=0 || player.getBlock()!=0 || player.getDefense()!=0 || player.getFreeThrows()!=0 ||
				   player.getPass()!=0 || player.getReactionTime()!=0 || player.getReboundDef()!=0 || player.getReboundOff()!=0 || player.getShot2Pts()!=0 ||
				   player.getShot3Pts()!=0 || player.getSpeed()!=0 || player.getSteal()!=0 || player.getStrenght()!=0) {
					
					if(player.getAttack()<=100 || player.getBallHandle()<=100 || player.getBlock()<=100 || player.getDefense()<=100 || player.getFreeThrows()<=100 ||
						player.getPass()<=100 || player.getReactionTime()<=100 || player.getReboundDef()<=100 || player.getReboundOff()<=100 || player.getShot2Pts()<=100 ||
						player.getShot3Pts()<=100 || player.getSpeed()<=100 || player.getSteal()<=100 || player.getStrenght()<=100) {
					
						System.out.println("\n Name player : " +player.getName()+ "Stat : OK");
					}
				}
				else {
					System.err.println("Erreur Stats Player");
				}
			}
		}
	}
	
	public void statsTeam() {
		WorldCup wp = new WorldCup();
		for(Iterator<Team> it = wp.getTeams().iterator(); it.hasNext();) {
			Team team = it.next();
			
			if(team.getAttack()!=0 || team.getBallHandle()!=0 || team.getBlock()!=0 || team.getDefense()!=0 || team.getFreeThrows()!=0 ||
				team.getPass()!=0 || team.getReactionTime()!=0 || team.getReboundDef()!=0 || team.getReboundOff()!=0 || team.getShot2Pts()!=0 ||
				team.getShot3Pts()!=0 || team.getSpeed()!=0 || team.getSteal()!=0 || team.getStrenght()!=0) {
					
				if(team.getAttack()<=100 || team.getBallHandle()<=100 || team.getBlock()<=100 || team.getDefense()<=100 || team.getFreeThrows()<=100 ||
					team.getPass()<=100 || team.getReactionTime()<=100 || team.getReboundDef()<=100 || team.getReboundOff()<=100 || team.getShot2Pts()<=100 ||
					team.getShot3Pts()<=100 || team.getSpeed()<=100 || team.getSteal()<=100 || team.getStrenght()<=100) {
					
						System.out.println("\n Name team : " +team.getName()+ " Stat : OK");
				}
			}
			else {
				System.err.println("Erreur Stats Player");
			}
		}
	}

	
	public void rankingWorldCup() {
		WorldCup wp = new WorldCup();
		RankingWorldCup rwc = new RankingWorldCup(wp.getTeams());
		wp.creationGroupStage1();
		wp.simulateGameGroupStage1();
		wp.creationGroupStage2();
		wp.simulateGameGroupStage2();
		wp.simulationQuarterFinal();
		wp.simulateSemiFinal();
		wp.simulateSmallFinal();
		wp.simulateFinal();
		
		
		for(int i=0; i<wp.getRanking().getRankingTeams().length; i++) {
			if(wp.getRanking().getScoreTeams().get(wp.getRanking().getRankingTeams()[i].getName()) >= wp.getRanking().getScoreTeams().get(wp.getRanking().getRankingTeams()[i+1].getName())) {
				System.out.println(wp.getRanking().getRankingTeams()[i].getName()+"   "+wp.getRanking().getScoreTeams().get(wp.getRanking().getRankingTeams()[i].getName()));
			}
			else {
				System.err.println("Erreur classement");
			}
		}
		
	}

/***********************************************   Test sur le bon déroulement de la simultaion ****************************************************/
	
	
	public void nbPool1() {
		int i=0;
		WorldCup wp = new WorldCup();
		wp.creationGroupStage1();
		for(Iterator<GroupStage> it = wp.getGroupStage1().values().iterator(); it.hasNext();) {
			GroupStage gs = it.next();
			i++;			
		}
		if(i==wp.getGroupStage1().size()) {
			System.out.println("\n Le nombre de pools de la phase 1 est correcte : " + i + "\n");
		}
		else {
			System.err.println("\n Erreur du nombre d'equipe participante");
		}
	}
	
	
	public void nbPool2() {
		int i=0;
		WorldCup wp = new WorldCup();
		wp.creationGroupStage1();
		wp.creationGroupStage2();
		for(Iterator<GroupStage> it = wp.getGroupStage2().values().iterator(); it.hasNext();) {
			GroupStage gs = it.next();
			i++;			
		}
		if(i==wp.getGroupStage2().size()) {
			System.out.println("\n Le nombre de pools de la phase 2 est correcte : " + i + "\n");
		}
		else {
			System.err.println("\n Erreur du nombre d'equipe participante");
		}
	}
	
	public void QuarterFinal() {
		int i=0;
		WorldCup wp = new WorldCup();
		wp.creationGroupStage1();
		wp.creationGroupStage2();
		wp.simulationQuarterFinal();
		for(i=0; i<wp.getQuarterFinal().length; i++) {
						
		}
		if(i==wp.getQuarterFinal().length) {
			System.out.println("\n Le nombre d'equipe de Quart de final est correcte : " + i*2 + "\n");
		}
		else {
			System.err.println("\n Erreur du nombre d'equipe participante");
		}
	}
	
	
	public void SemiFinal() {
		int i=0;
		WorldCup wp = new WorldCup();
		wp.creationGroupStage1();
		wp.creationGroupStage2();
		wp.simulationQuarterFinal();
		wp.simulateSemiFinal();
		for(i=0; i<wp.getSemiFinal().length; i++) {
						
		}
		if(i==wp.getSemiFinal().length) {
			System.out.println("\n Le nombre d'equipe de la Demi final est correcte : " + i*2 + "\n");
		}
		else {
			System.err.println("\n Erreur du nombre d'equipe participante");
		}
	}
	
	public void smallFinal() {
		WorldCup wp = new WorldCup();
		wp.creationGroupStage1();
		wp.creationGroupStage2();
		wp.simulationQuarterFinal();
		wp.simulateSemiFinal();
		wp.simulateFinal();			
		
		if(wp.getSmallFinalGame().getTeam1() != null  && wp.getFinalGame().getTeam2() !=null) {
			System.out.println("\n Le nombre d'equipe de la petite final est correcte : 2 \n");
		}
		else {
			System.err.println("\n Erreur du nombre d'equipe participante");
		}
	}
	
	public void Final() {
		WorldCup wp = new WorldCup();
		wp.creationGroupStage1();
		wp.creationGroupStage2();
		wp.simulationQuarterFinal();
		wp.simulateSemiFinal();
		wp.simulateFinal();			
		
		if(wp.getFinalGame().getTeam1() != null  && wp.getFinalGame().getTeam2() !=null) {
			System.out.println("\n Le nombre d'equipe de la final est correcte : 2 \n");
		}
		else {
			System.err.println("\n Erreur du nombre d'equipe participante");
		}
	}
	
	public void winner() {
		WorldCup wp = new WorldCup();
		wp.creationGroupStage1();
		wp.creationGroupStage2();
		wp.simulationQuarterFinal();
		wp.simulateSemiFinal();
		wp.simulateFinal();			
		
		if(wp.getFinalGame().getScore1() < wp.getFinalGame().getScore2() || wp.getFinalGame().getScore1() > wp.getFinalGame().getScore2()) {
			System.out.println("\n il existe un vainqueur \n");
		}
		else {
			System.err.println("\n Erreur");
		}
	}
	

}
