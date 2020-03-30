package ihm;


import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import data.WorldCup;

public class WorldCupSimulator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WorldCup worldCup = new WorldCup();
		
		try {
			UIManager.setLookAndFeel ("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JFrame main = new MainFrame("WCS",worldCup);
		 
	}

}
