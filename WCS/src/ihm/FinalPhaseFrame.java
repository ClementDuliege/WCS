package ihm;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import data.Game;
import data.WorldCup;

/**
* This class is used to display the results of the final phase of the World Cup.
*@author WCS
*/ 
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
		this.contentPane=getContentPane();
		this.windows=this;
		//Initiation of the layout frame
		initLayout();
	}
	
	/**
	 * This method is used to initialise the layout and place the menu, etc...
	 */
	public void initLayout() {
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		
		displayNameTeamInScore();
		phase1Button.setBounds(150, 50, 200, 25);
		phase2Button.setBounds(400, 50, 200, 25);
		phaseFinalButton.setBounds(650, 50, 200, 25);
		teamsButton.setBounds(900, 50, 200, 25);
		rankButton.setBounds(1000, 750, 200, 40);
		
		JLabel winnerLabel=new JLabel(worldCup.getWinnerTeam().getName(),SwingConstants.CENTER);
		winnerLabel.setBounds(530, 540, 170, 30);
		winnerLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
		panel.add(winnerLabel);
		
		JPanel finalPhasePanel =new TablePhaseFinal(worldCup,windows);
		finalPhasePanel.setLayout(null);
		finalPhasePanel.setBounds(100, 150, 1200, 800);
		
		panel.add(finalPhasePanel);
		panel.add(phase1Button);
		panel.add(phase2Button);
		panel.add(phaseFinalButton);
		panel.add(teamsButton);
		panel.add(rankButton);
		
		contentPane.add(panel);
		//Init action to buttons
		initAction();
	}
	
	
	
	/**
	 * This method is used to create labels to write de name of the teams.
	 */
	public void displayNameTeamInScore() {
		Game[] quarterFinal=worldCup.getQuarterFinal();
		Game[] semiFinal=worldCup.getSemiFinal();
		Game finalGame=worldCup.getFinalGame();
		Game smallFinalGame=worldCup.getSmallFinalGame();
		//Display teams'name of the quarter games
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
		//Display teams'name of the semi games
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
		 }
		 
		//Display teams'name of the final game
		 JLabel nameTeamFinal1Label=new JLabel(finalGame.getTeam1().getName(),SwingConstants.CENTER);
		 nameTeamFinal1Label.setBounds(505, 363, 73, 13);
		 nameTeamFinal1Label.setFont(new Font(Font.DIALOG, Font.BOLD, 13));
		 panel.add(nameTeamFinal1Label);
		 JLabel nameTeamFinal2Label=new JLabel(finalGame.getTeam2().getName(),SwingConstants.CENTER);
		 nameTeamFinal2Label.setBounds(675, 363, 73, 13);
		 nameTeamFinal2Label.setFont(new Font(Font.DIALOG, Font.BOLD, 13));
		 panel.add(nameTeamFinal2Label);
		 
		//Display teams'name of the small final game
		 JLabel nameTeamFinal3Label=new JLabel(smallFinalGame.getTeam1().getName(),SwingConstants.CENTER);
		 nameTeamFinal3Label.setBounds(505, 463, 73, 13);
		 nameTeamFinal3Label.setFont(new Font(Font.DIALOG, Font.BOLD, 13));
		 panel.add(nameTeamFinal3Label);
		 JLabel nameTeamFinal4Label=new JLabel(smallFinalGame.getTeam2().getName(),SwingConstants.CENTER);
		 nameTeamFinal4Label.setBounds(675, 463, 73, 13);
		 nameTeamFinal4Label.setFont(new Font(Font.DIALOG, Font.BOLD, 13));
		 panel.add(nameTeamFinal4Label);
	}
	
	
	/**
	 * This method is used to initialise actions to the buttons
	 */
	public void initAction() {
		phase1Button.addActionListener(new Phase1());
		phase2Button.addActionListener(new Phase2());
		teamsButton.addActionListener(new Teams());
		rankButton.addActionListener(new RankingAction());
	}
	
	/**
	 * ActionListener action of the phase1 button
	 *
	 */
	public class Phase1 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			windows.dispose();
			new Phase1Frame(worldCup,"WCS");
		}
	}
	
	/**
	 * ActionListener action of the phase2 button
	 *
	 */
	public class Phase2 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			windows.dispose();
			new Phase2Frame(worldCup,"WCS");
		}
	}
	
	/**
	 * ActionListener action of the teams button
	 *
	 */
	public class Teams implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			windows.dispose();
			new ListTeamsFrame("TEAMS",worldCup);
		}
	}
	  
	/**
	 * ActionListener action of the ranking button
	 *
	 */	
	public class RankingAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			windows.dispose();
			new RankingWorldCupFrame("Classement",worldCup);
		}
	}
	
	

	
	
	
	
	

	
	

	
}