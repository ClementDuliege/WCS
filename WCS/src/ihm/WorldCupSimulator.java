package ihm;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import data.WorldCup;



/**
 * This class is used to launch the World Cup Simulator
 * @author WCS 
 *
 */
public class WorldCupSimulator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			UIManager.setLookAndFeel ("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WorldCup worldCup = new WorldCup();
		worldCup.creationGroupStage1();
		worldCup.simulateGameGroupStage1();
		worldCup.creationGroupStage2();
		worldCup.simulateGameGroupStage2();
		worldCup.simulationQuarterFinal();
		worldCup.simulateSemiFinal();
		worldCup.simulateSmallFinal();
		worldCup.simulateFinal();
		
		new MainFrame("WCS",worldCup);
		 
	}

}
  