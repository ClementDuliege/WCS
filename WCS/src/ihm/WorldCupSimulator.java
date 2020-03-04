package ihm;

import java.util.ArrayList;

import javax.swing.JFrame;

import data.Team;
import data.WorldCup;

public class WorldCupSimulator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WorldCup worldCup = new WorldCup();
		worldCup.creationGroupStage1();
		
		worldCup.simulateGameGroupStage1();
		worldCup.creationGroupStage2();
		
		worldCup.simulateGameGroupStage2();
		
	
		worldCup.simulationQuarterFinal();
		
		
		worldCup.simulateSemiFinal();
		
		
		worldCup.simulateSmallFinal();
		
		
		worldCup.simulateFinal();
		
		JFrame main = new MainFrame("WCS",worldCup);
		
	}

}
