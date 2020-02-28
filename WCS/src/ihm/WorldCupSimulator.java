package ihm;

import java.util.ArrayList;

import javax.swing.JFrame;

import data.Team;
import data.WorldCup;

public class WorldCupSimulator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WorldCup worldCup = new WorldCup();
		
		JFrame main = new MainFrame("WCS",worldCup);
		
	}

}
