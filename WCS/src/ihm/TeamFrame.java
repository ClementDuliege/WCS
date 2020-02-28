package ihm;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.Team;
import data.WorldCup;

public class TeamFrame extends JFrame {
	
	private WorldCup worldCup;
	private Container contentPane;
	private JButton tabButton[]= new JButton[32];
	private JLabel teamsLabel;
	private int indexTeam;
	
	public TeamFrame(String windowsTitle, WorldCup worldCup, int indexTeam) {
		super(windowsTitle);
		setSize(900,600);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setAlwaysOnTop(true);
		setVisible(true);
		
		this.worldCup=worldCup;
		contentPane=getContentPane();
		this.indexTeam=indexTeam;
		
		initLayout();
		
		
		
	}
	
	public void initLayout() {
		
		ArrayList<Team> teams=worldCup.getTeams();
		
		GridLayout grid = new GridLayout(1,1);
		contentPane.setLayout(grid);
		
		JPanel panel=new JPanel();
		panel.setLayout(null);
		
		teamsLabel=new JLabel(teams.get(indexTeam).toString());
		teamsLabel.setFont(new Font("Serif", Font.BOLD,10));
		teamsLabel.setBounds(300, 15, 275, 75);
		System.out.print(teams.get(indexTeam));
		panel.add(teamsLabel);
		
		contentPane.add(panel);
		
		
	}
	
	
	

	
	

}
