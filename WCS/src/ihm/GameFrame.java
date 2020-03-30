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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import data.Game;
import data.Player;
import data.Team;
import data.WorldCup;
import ihm.Phase1Frame.Phase2;
import ihm.Phase1Frame.PhaseFinale;
import ihm.Phase1Frame.Teams;

public class GameFrame extends JFrame {
	private WorldCup worldCup;
	private Game game;
	private JFrame windows;
	private Container contentPane;
	private JPanel panel = new JPanel();
	private JLabel gameLabel;
	private JLabel displayActionsLabel;
	private JButton phase1Button=new JButton("Phase 1");
	private JButton phase2Button=new JButton("Phase 2");
	private JButton phaseFinalButton=new JButton("Phase Finale");
	private JButton teamsButton=new JButton("Equipes");
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
		contentPane=getContentPane();
		windows=this;
		initLayout();
	}
	
	public void initLayout() {
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		
		gameLabel=new JLabel(game.getTeam1().getName()+" VS "+game.getTeam2().getName());
		gameLabel.setBounds(500, 100, 300, 25);
		gameLabel.setFont(new Font("TimesRoman", Font.BOLD, 25)); 
		phase1Button.setBounds(150, 50, 200, 25);
		phase2Button.setBounds(400, 50, 200, 25);
		phaseFinalButton.setBounds(650, 50, 200, 25);
		teamsButton.setBounds(900, 50, 200, 25);
		
		JScrollPane jp ;
		displayActionsLabel=new JLabel(getActions());
	
		jp =new JScrollPane(displayActionsLabel);
		jp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jp.setBounds(370, 150, 500, 650);
		
		panel.add(phase1Button);
		panel.add(phase2Button);
		panel.add(phaseFinalButton);
		panel.add(teamsButton);
		panel.add(gameLabel);
		panel.add(jp);
		initAction();
		displayStatsTeam(game.getTeam1(),0);
		displayStatsTeam(game.getTeam2(),1);
		contentPane.add(panel);
	}
	
	
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
	 		String res="";
	 		res+=players.get(i).getName()+"=> "+statsPoints.get(players.get(i).getName())+" Points";
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
	
	public void displayStatsTeam2() {
		HashMap<String,Integer> statsPoints = game.getStatsPoints();
		HashMap<String,Integer> statsBlocks =game.getStatsBlocks();
	 	HashMap<String,Integer> statsSteals = game.getStatsSteals();
	 	ArrayList<Player> players =game.getTeam2().getPlayers();
	 	for(int i =0;i<10;i++) {
	 		String res="";
	 		res+=players.get(i).getName()+"=> "+statsPoints.get(players.get(i).getName())+" Points";
	 		JLabel statsLabel = new JLabel(res);
	 		statsLabel.setBounds(950, 100+(25*i), 250, 15);
	 		panel.add(statsLabel);
	 	}
	 	
	}
	
	public String getActions() {
		String res="<html>";
		Set s = game.getActions().entrySet(); 
		  
        // Using iterator in SortedMap 
        Iterator i = s.iterator(); 
  
        // Traversing map. Note that the traversal 
        // produced sorted (by keys) output . 
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
	
	public void initAction() {
		phase1Button.addActionListener(new Phase1());
		phase2Button.addActionListener(new Phase2());
		phaseFinalButton.addActionListener(new PhaseFinale());
		teamsButton.addActionListener(new Teams());
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
	
	public class PhaseFinale implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			windows.dispose();
			new FinalPhaseFrame(worldCup,"WCS");
			
		   
			
		}
	}
	
	public class Teams implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			windows.dispose();
			new ListTeamsFrame("TEAMS",worldCup);
			
		   
			
		}
	}
}
