package ihm;

import java.awt.Container;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.Game;
import data.WorldCup;

public class Phase1 extends JFrame {
	private WorldCup worldCup;
	private JFrame windows;
	private Container contentPane;
	private JPanel panel = new JPanel();
	
	public Phase1(WorldCup worldCup, String windowsTitle) {
		super(windowsTitle);
		setSize(1300,800);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setAlwaysOnTop(true);
		setVisible(true);
		this.worldCup=worldCup;
		contentPane=getContentPane();
		windows=this;
		panel.setLayout(null);
		displayGroup();
		String listGroup[]= {"GROUPE A","GROUPE B","GROUPE C","GROUPE D","GROUPE E","GROUPE F","GROUPE G","GROUPE H"};
		int k=0;
		for(int i = 0 ;i<2;i++) {
			for(int j = 0;j<4;j++) {
				displayGameGroup(listGroup[k], i,j);
				k++;
			}
		}
	}
	
	public void displayGroup() {
		JLabel groupA=new JLabel("GROUPE A");
		groupA.setBounds(100, 100, 200, 30);
		JLabel groupB=new JLabel("GROUPE B");
		groupB.setBounds(400, 100, 200, 30);
		JLabel groupC=new JLabel("GROUPE C");
		groupC.setBounds(700, 100, 200, 30);
		JLabel groupD=new JLabel("GROUPE D");
		groupD.setBounds(1000, 100, 200, 30);
		JLabel groupE=new JLabel("GROUPE E");
		groupE.setBounds(100, 500, 200, 30);
		JLabel groupF=new JLabel("GROUPE F");
		groupF.setBounds(400, 500, 200, 30);
		JLabel groupG=new JLabel("GROUPE G");
		groupG.setBounds(700, 500, 200, 30);
		JLabel groupH=new JLabel("GROUPE H");
		groupH.setBounds(1000, 500, 200, 30);
		
		panel.add(groupA);
		panel.add(groupB);
		panel.add(groupC);
		panel.add(groupD);
		panel.add(groupE);
		panel.add(groupF);
		panel.add(groupG);
		panel.add(groupH);
		
		contentPane.add(panel);
		
		
	}
	
	public void displayGameGroup(String group,int i, int j) {
		ArrayList<Game> listGameGroupA = worldCup.getGroupStage1().get(group).getGames();
		
		for(int k = 0; k<listGameGroupA.size();k++) {
			Game game = listGameGroupA.get(k);
			String res="";
			res += game.getTeam1().getName()+" "+game.getScore1()+" : "+game.getScore2()+" "+game.getTeam2().getName();
			JLabel label = new JLabel(res);
			label.setBounds(50+(j*300) , 150+(i*400)+(30*k), 250, 30);
			panel.add(label);
		}
		
		
	}

}
