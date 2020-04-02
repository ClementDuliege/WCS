package tests;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import data.WorldCup;
import ihm.MainFrame;
import ihm.WorldCupSimulator;

public class Test {

	public static void main(String[] args) {
		
		long start = System.nanoTime();
		WorldCup worldCup = new WorldCup();
		worldCup.creationGroupStage1();
		worldCup.simulateGameGroupStage1();
		worldCup.creationGroupStage2();
		worldCup.simulateGameGroupStage2();
		worldCup.simulationQuarterFinal();
		worldCup.simulateSemiFinal();
		worldCup.simulateSmallFinal();
		worldCup.simulateFinal();
		
		try {
			UIManager.setLookAndFeel ("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JFrame main = new MainFrame("WCS",worldCup);
		
		
		
		
		/***********************************************   Test d'Optimisation permettant de calculer le temps d'Execution ****************************************************/
		long finish = System.nanoTime();
		long time = finish - start;
		System.out.println("Temps d'execution de la simulation : " + time/1000000);
		
		/************************************************ Execution des tests ******************************************************/
		
		TestSystem ts = new TestSystem();
		ts.statsPlayer();
		ts.statsTeam();
		ts.nbPlayer();
		ts.nbTeam();
		ts.rankingWorldCup();
		ts.nbPool1();
		ts.nbPool2();
		ts.QuarterFinal();
		ts.SemiFinal();
		ts.smallFinal();
		ts.Final();
		ts.winner();
		
	}
}
