package ihm;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import data.Game;
import data.Player;
import data.Team;
import data.WorldCup;
import ihm.Phase1Frame.Phase2;
import ihm.Phase1Frame.PhaseFinale;
import ihm.Phase1Frame.Teams;
/**
 * This class display a game.
 * @author WCS
 *
 */
public class GameFrame extends JFrame implements Runnable {
	private int speed;
	private WorldCup worldCup;
	private Game game;
	private JFrame windows;
	private JButton seeGameButton=new JButton("Lancer le match");
	private JButton resumeGameButton=new JButton("Résumé du match");
	private Container contentPane;
	private JPanel panel = new JPanel();
	private JPanel gamePanel;
	private JLabel displayActionsLabel;
	private JButton phase1Button=new JButton("Phase 1");
	private JButton phase2Button=new JButton("Phase 2");
	private JButton phaseFinalButton=new JButton("Phase Finale");
	private JButton teamsButton=new JButton("Equipes");
	private JButton speed1Button=new JButton("vitesse 1");
	private JButton speed2Button=new JButton("vitesse 2");
	private JButton speed3Button=new JButton("vitesse 3");
	private JButton endButton=new JButton("Fin");
	private boolean gameIsFinish;
	private boolean seeGame;
	public GameFrame(String windowsTitle,WorldCup worldCup, Game game) {
		super(windowsTitle);
		setSize(1300,850);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setAlwaysOnTop(true);
		setVisible(true);
		
		this.worldCup=worldCup;
		this.game=game;
		this.contentPane=getContentPane();
		this.windows=this;
		this.speed=3000;
		this.gameIsFinish=false;
		this.seeGame=false;
		//Initiation of the layout frame
		initLayout();
	}
	
	
	
	/**
	 * This method is used to initialise the layout and place the menu, etc...
	 */
	public void initLayout() {
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		
		gamePanel= new ScorePanel(game);
		gamePanel.setBounds(470, 100, 250, 43);
		
		JLabel nameTeam1Label=new JLabel(game.getTeam1().getName(),SwingConstants.CENTER);
		nameTeam1Label.setBounds(475, 115, 73, 13);
		nameTeam1Label.setFont(new Font(Font.DIALOG, Font.BOLD, 13));
		
		JLabel nameTeam2Label=new JLabel(game.getTeam2().getName(),SwingConstants.CENTER);
		nameTeam2Label.setBounds(643, 115, 73, 13);
		nameTeam2Label.setFont(new Font(Font.DIALOG, Font.BOLD, 13));
		
		seeGameButton.setBounds(290, 105, 150, 30);
		resumeGameButton.setBounds(750, 105, 150, 30);
		phase1Button.setBounds(150, 50, 200, 25);
		phase2Button.setBounds(400, 50, 200, 25);
		phaseFinalButton.setBounds(650, 50, 200, 25);
		teamsButton.setBounds(900, 50, 200, 25);
		speed1Button.setBounds(15, 550, 80, 20);
		speed2Button.setBounds(100, 550, 80, 20);
		speed3Button.setBounds(185, 550, 80, 20);
		endButton.setBounds(270, 550, 80, 20);
		
		speed1Button.setVisible(false);
		speed2Button.setVisible(false);
		speed3Button.setVisible(false);
		endButton.setVisible(false);
		
		panel.add(speed1Button);
		panel.add(speed2Button);
		panel.add(speed3Button);
		panel.add(endButton);
		panel.add(nameTeam2Label);
		panel.add(phase1Button);
		panel.add(phase2Button);
		panel.add(phaseFinalButton);
		panel.add(teamsButton);
		panel.add(gamePanel);
		panel.add(nameTeam1Label);
		panel.add(seeGameButton);
		panel.add(resumeGameButton);
		
		contentPane.add(panel);
		
		//Init Action to the buttons
		initAction();
		displayStatsTeam(game.getTeam1(),0);
		displayStatsTeam(game.getTeam2(),1);
	}
	
	
	
	/**
	 * This method is used to display the players's stats of the game
	 * @param team Team
	 * @param t Integer used to find the good position for labels 
	 */
	public void displayStatsTeam(Team team,int t) {
		HashMap<String,Integer> statsPoints = game.getStatsPoints();
		HashMap<String,Integer> statsBlocks =game.getStatsBlocks();
	 	HashMap<String,Integer> statsSteals = game.getStatsSteals();
	 	ArrayList<Player> players =team.getPlayers();
	 	JLabel score = new JLabel("POINTS");
	 	score.setForeground(Color.WHITE);
	 	score.setBackground(new Color(50,50,50));
	 	score.setOpaque(true);
	 	score.setBounds(150+(t*900), 170, 62, 30);
 		panel.add(score);
 		JLabel steals = new JLabel("INTERCEPTIONS");
 		steals.setForeground(Color.WHITE);
 		steals.setBackground(new Color(50,50,50));
 		steals.setOpaque(true);
 		steals.setBounds(212+(t*900), 170, 62, 30);
 		panel.add(steals);
 		JLabel blocks = new JLabel("CONTRES");
 		blocks.setForeground(Color.WHITE);
 		blocks.setBackground(new Color(50,50,50));
 		blocks.setOpaque(true);
 		blocks.setBounds(274+(t*900), 170, 62, 30);
 		panel.add(blocks);
	 	for(int i =0;i<10;i++) {
	 		JLabel nameLabel = new JLabel(players.get(i).getName());
	 		nameLabel.setForeground(Color.WHITE);
			nameLabel.setBackground(Color.GRAY);
			nameLabel.setOpaque(true);
	 		nameLabel.setBounds(25+(t*900), 200+(30*i), 125, 30);
	 		panel.add(nameLabel);
	 		
	 		JLabel pointsLabel = new JLabel(statsPoints.get(players.get(i).getName()).toString(),SwingConstants.CENTER);
	 		pointsLabel.setForeground(Color.WHITE);
			pointsLabel.setBackground(new Color(160,160,160));
			pointsLabel.setOpaque(true);
	 		pointsLabel.setBounds(150+(t*900), 200+(30*i), 62, 30);
	 		panel.add(pointsLabel);
	 		
	 		JLabel stealsLabel = new JLabel(statsSteals.get(players.get(i).getName()).toString(),SwingConstants.CENTER);
	 		stealsLabel.setForeground(Color.WHITE);
			stealsLabel.setBackground(Color.GRAY);
			stealsLabel.setOpaque(true);
	 		stealsLabel.setBounds(212+(t*900), 200+(30*i), 62, 30);
	 		panel.add(stealsLabel);
	 		
	 		JLabel blocksLabel = new JLabel(statsBlocks.get(players.get(i).getName()).toString(),SwingConstants.CENTER);
	 		blocksLabel.setForeground(Color.WHITE);
			blocksLabel.setBackground(new Color(160,160,160));
			blocksLabel.setOpaque(true);
	 		blocksLabel.setBounds(274+(t*900), 200+(30*i), 62, 30);
	 		panel.add(blocksLabel);
	 	}
	}
	
	
	
	/**
	 * This method is used to see a game action by action
	 */
	public void seeActions() {
        	
		String res="<html>";
		Set s = game.getActions().entrySet(); 
		  
	    // Using iterator in SortedMap 
	    Iterator i = s.iterator(); 
	    int j=0;
		while (i.hasNext()) { 
			if(j==10) {
				 res="<html>";
				 j=0;
	    	}
    		j++;
            Map.Entry m = (Map.Entry)i.next();
            int key = (Integer)m.getKey(); 
            String value = (String)m.getValue(); 
            res+=value;
            displayActionsLabel.setText(res);
            panel.repaint();
	        try { 
	        	Thread.sleep(speed);
	 		}catch (InterruptedException e) {
	 			// TODO Auto-generated catch block
			e.printStackTrace();
	 		}
	    }
		res="<html>"+getActions()+"</html>";
		displayActionsLabel.setText(res);
		panel.repaint();
		gameIsFinish=true;
		speed1Button.setVisible(false);
		speed2Button.setVisible(false);
		speed3Button.setVisible(false);
		endButton.setVisible(false);
		seeGameButton.setVisible(false);
	}
	
	
	
	/**
	 * This method is used to get all the actions of the game.
	 * @return String all the actions of the game
	 */
	public String getActions() {
		String res="<html>";
		Set s = game.getActions().entrySet(); 
        // Using iterator in SortedMap 
        Iterator i = s.iterator(); 
        while (i.hasNext()) 
        { 
            Map.Entry m = (Map.Entry)i.next(); 
  
            int key = (Integer)m.getKey(); 
            String value = (String)m.getValue(); 
  
            res+=value;
        }
        res+="</html>";
        return res;
	}
	
	
	/**
	 * This method is used to initialise actions to the buttons
	 */
	public void initAction() {
		phase1Button.addActionListener(new Phase1());
		phase2Button.addActionListener(new Phase2());
		phaseFinalButton.addActionListener(new PhaseFinale());
		teamsButton.addActionListener(new Teams());
		resumeGameButton.addActionListener(new ResumeGame());
		seeGameButton.addActionListener(new SeeGame());
		speed1Button.addActionListener(new Speed1());
		speed2Button.addActionListener(new Speed2());
		speed3Button.addActionListener(new Speed3());
		endButton.addActionListener(new EndGame());
	}
	
	
	
	/**
	 * ActionListener action of the resumeGame button
	 *
	 */
	public class ResumeGame implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JScrollPane jp ;
			displayActionsLabel=new JLabel(getActions());
		
			jp =new JScrollPane(displayActionsLabel);
			jp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			jp.setBounds(360, 150, 530, 650);
			panel.add(jp);
			seeGameButton.setVisible(false);
		}
	}
	
	
	
	/**
	 * ActionListener action of the seeGame button
	 *
	 */
	public class SeeGame implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JScrollPane jp ;
			displayActionsLabel=new JLabel();
			jp =new JScrollPane(displayActionsLabel);
			jp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			jp.setBounds(360, 150, 530, 650);
			panel.add(jp);
			speed1Button.setVisible(true);
			speed2Button.setVisible(true);
			speed3Button.setVisible(true);
			endButton.setVisible(true);
			seeGame=true;
			resumeGameButton.setVisible(false);
		}
	}
	
	
	
	/**
	 * ActionListener action of the speed1 button
	 *
	 */
	public class Speed1 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			speed=3000;
		}
	}
	
	
	
	/**
	 * ActionListener action of the speed2 button
	 *
	 */
	public class Speed2 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			speed=1000;
		}
	}
	
	
	
	/**
	 * ActionListener action of the speed3 button
	 *
	 */
	public class Speed3 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			speed=500;
		}
	}
	
	
	
	/**
	 * ActionListener action of the end button
	 *
	 */
	public class EndGame implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			speed=0;
		}
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
	 * ActionListener action of the finalPhase button
	 *
	 */
	public class PhaseFinale implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			windows.dispose();
			new FinalPhaseFrame(worldCup,"WCS");	
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

	@Override
	public void run() {
		while(!gameIsFinish) {
			System.out.print("");
			if(seeGame) {
				seeActions();
			}
			
		}
		
	}
}
