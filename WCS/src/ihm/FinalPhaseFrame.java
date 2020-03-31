package ihm;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import data.Game;
import data.Team;
import data.WorldCup;


public class FinalPhaseFrame extends JFrame {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	private WorldCup worldCup;
	private JFrame windows;
	private Container contentPane;
	private JPanel panel = new JPanel();
	private JButton phase1Button=new JButton("Phase 1");
	private JButton phase2Button=new JButton("Phase 2");
	private JButton phaseFinalButton=new JButton("Phase Finale");
	private JButton teamsButton=new JButton("Equipes");
	private JButton rankButton=new JButton("Classement");

	public FinalPhaseFrame(WorldCup worldCup, String windowsTitle) {
		super(windowsTitle);
		setSize(1300,850);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setAlwaysOnTop(true);
		setVisible(true);
		this.worldCup=worldCup;
		contentPane=getContentPane();
		windows=this;
		initLayout();
	}
	
	public void initLayout() {
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		
		displayNameTeamInScore();
		phase1Button.setBounds(150, 50, 200, 25);
		phase2Button.setBounds(400, 50, 200, 25);
		phaseFinalButton.setBounds(650, 50, 200, 25);
		teamsButton.setBounds(900, 50, 200, 25);
		rankButton.setBounds(1000, 750, 200, 40);
		panel.add(phase1Button);
		panel.add(phase2Button);
		panel.add(phaseFinalButton);
		panel.add(teamsButton);
		panel.add(rankButton);
		
		JLabel winnerLabel=new JLabel(worldCup.getWinnerTeam().getName(),SwingConstants.CENTER);
		winnerLabel.setBounds(530, 540, 170, 30);
		winnerLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
		panel.add(winnerLabel);
		
		initAction();
		
		JPanel pane2 =new TableauPhaseFinal(worldCup,windows);
		pane2.setLayout(null);
		pane2.setBounds(100, 150, 1200, 800);
		
		panel.add(pane2);
		contentPane.add(panel);
		
	}
	
	public void initAction() {
		phase1Button.addActionListener(new Phase1());
		phase2Button.addActionListener(new Phase2());
		teamsButton.addActionListener(new Teams());
		rankButton.addActionListener(new RankingAction());
	}
	
	
	public class Phase1 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			windows.dispose();
			new Phase1Frame(worldCup,"WCS");
		}
	}
	
	public class Phase2 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			windows.dispose();
			new Phase2Frame(worldCup,"WCS");
		}
	}
	
	
	public class Teams implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			windows.dispose();
			new ListTeamsFrame("TEAMS",worldCup);
		}
	}
	  
		
	public class RankingAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			windows.dispose();
			new RankingWorldCupFrame("Classement",worldCup);
		}
	}
	
	
	public void displayNameTeamInScore() {
		Game[] quarterFinal=worldCup.getQuarterFinal();
		Game[] semiFinal=worldCup.getSemiFinal();
		Game finalGame=worldCup.getFinalGame();
		Game smallFinalGame=worldCup.getSmallFinalGame();
		int k=0;
		 for(int i = 0;i<2;i++) {
			 for(int j = 0 ;j<2;j++) {
				Game game=quarterFinal[k];
				JLabel nameTeam1Label=new JLabel(game.getTeam1().getName(),SwingConstants.CENTER);
				nameTeam1Label.setBounds(103 +(j*802), 163+(i*400), 73, 13);
				nameTeam1Label.setFont(new Font(Font.DIALOG, Font.BOLD, 13));
				panel.add(nameTeam1Label);
				JLabel nameTeam2Label=new JLabel(game.getTeam2().getName(),SwingConstants.CENTER);
				nameTeam2Label.setBounds(273+(j*802), 163+(i*400), 73, 13);
				nameTeam2Label.setFont(new Font(Font.DIALOG, Font.BOLD, 13));
				panel.add(nameTeam2Label);
				k++;
			 }
		 }
		 
		 for(int i2= 0;i2<2;i2++) {
			 Game game=semiFinal[i2];
			 JLabel nameTeam1Label=new JLabel(game.getTeam1().getName(),SwingConstants.CENTER);
			 nameTeam1Label.setBounds(205 +(i2*610), 363, 73, 13);
			 nameTeam1Label.setFont(new Font(Font.DIALOG, Font.BOLD, 13));
			 panel.add(nameTeam1Label);
			 JLabel nameTeam2Label=new JLabel(game.getTeam2().getName(),SwingConstants.CENTER);
			 nameTeam2Label.setBounds(375+(i2*610), 363, 73, 13);
			 nameTeam2Label.setFont(new Font(Font.DIALOG, Font.BOLD, 13));
			 panel.add(nameTeam2Label);
				
		
			
			 k++;
			 
		 }
		 JLabel nameTeamFinal1Label=new JLabel(finalGame.getTeam1().getName(),SwingConstants.CENTER);
		 nameTeamFinal1Label.setBounds(505, 363, 73, 13);
		 nameTeamFinal1Label.setFont(new Font(Font.DIALOG, Font.BOLD, 13));
		 panel.add(nameTeamFinal1Label);
		 JLabel nameTeamFinal2Label=new JLabel(finalGame.getTeam2().getName(),SwingConstants.CENTER);
		 nameTeamFinal2Label.setBounds(675, 363, 73, 13);
		 nameTeamFinal2Label.setFont(new Font(Font.DIALOG, Font.BOLD, 13));
		 panel.add(nameTeamFinal2Label);
		
		 
		 JLabel nameTeamFinal3Label=new JLabel(smallFinalGame.getTeam1().getName(),SwingConstants.CENTER);
		 nameTeamFinal3Label.setBounds(505, 463, 73, 13);
		 nameTeamFinal3Label.setFont(new Font(Font.DIALOG, Font.BOLD, 13));
		 panel.add(nameTeamFinal3Label);
		 JLabel nameTeamFinal4Label=new JLabel(smallFinalGame.getTeam2().getName(),SwingConstants.CENTER);
		 nameTeamFinal4Label.setBounds(675, 463, 73, 13);
		 nameTeamFinal4Label.setFont(new Font(Font.DIALOG, Font.BOLD, 13));
		 panel.add(nameTeamFinal4Label);
	}
	
	
	
	
	

	
	

	
}