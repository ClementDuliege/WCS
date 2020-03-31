package ihm;

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
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import data.Game;
import data.WorldCup;

public class Phase2Frame extends JFrame {
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
	
	public Phase2Frame(WorldCup worldCup, String windowsTitle) {
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
		String listGroup[]= {"GROUPE I","GROUPE J","GROUPE K","GROUPE L"};

		for(int i = 0 ;i<4;i++) {
			displayGameGroup(listGroup[i],i);
		}
	}
	
	public void initLayout() {
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		
		
		phase1Button.setBounds(150, 50, 200, 25);
		phase2Button.setBounds(400, 50, 200, 25);
		phaseFinalButton.setBounds(650, 50, 200, 25);
		teamsButton.setBounds(900, 50, 200, 25);
		
		panel.add(phase1Button);
		panel.add(phase2Button);
		panel.add(phaseFinalButton);
		panel.add(teamsButton);
		
		initAction();
		
		displayGroup();
		contentPane.add(panel);
	}
	
	
	
	public void initAction() {
		phase1Button.addActionListener(new Phase1());
		phaseFinalButton.addActionListener(new PhaseFinale());
		teamsButton.addActionListener(new Teams());
	}
	
	
	public class Phase1 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			windows.dispose();
			new Phase1Frame(worldCup,"WCS");
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
	 
	public void displayGroup() {
		JButton groupI=new JButton("GROUPE I");
		groupI.setBounds(80, 100, 200, 30);
		groupI.setForeground(Color.WHITE);
		groupI.setBackground(new Color(225,82,11));
		 
		JButton groupJ=new JButton("GROUPE J");
		groupJ.setBounds(380, 100, 200, 30);
		groupJ.setForeground(Color.WHITE);
		groupJ.setBackground(new Color(225,82,11));
		
		JButton groupK=new JButton("GROUPE K");
		groupK.setBounds(680, 100, 200, 30);
		groupK.setForeground(Color.WHITE);
		groupK.setBackground(new Color(225,82,11));
		
		JButton groupL=new JButton("GROUPE L");
		groupL.setBounds(980, 100, 200, 30);
		groupL.setForeground(Color.WHITE);
		groupL.setBackground(new Color(225,82,11));
		JButton[] listLabel = {groupI,groupJ,groupK,groupL};
		addActionToLabel(listLabel);
		panel.add(groupI);
		panel.add(groupJ);
		panel.add(groupK);
		panel.add(groupL);	
	}
	
	public void addActionToPanelScore(JPanel panelScore,Game game) {
		panelScore.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
	           setCursor(handCursor);
	           
	         }
			public void mousePressed(MouseEvent e) {
				 windows.dispose();
				 new Thread(new GameFrame("Game",worldCup,game)).start();
			}
			 public void mouseExited(MouseEvent e) {
				 Cursor defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);
				 setCursor(defaultCursor);
			 }
			
		});
		
		
	}
	
	public void addActionToLabel(JButton[] listLabel) {
		for(int i = 0;i<4;i++) {
			JButton j = listLabel[i];
			j.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					//Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
		           // setCursor(handCursor);
		           
		         }
				public void mousePressed(MouseEvent e) {
					 windows.dispose();
			         new RankingGroup2Frame(worldCup,"Rangink Group 1");
				}
				 public void mouseExited(MouseEvent e) {
					// Cursor defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);
			            //setCursor(defaultCursor);
			         }
				
			});
		}
	}
	
	public void displayGameGroup(String group, int j) {
		ArrayList<Game> listGameGroup2 = worldCup.getGroupStage2().get(group).getGames();
		
		for(int k = 0; k<listGameGroup2.size();k++) {
			Game game = listGameGroup2.get(k);
			JPanel panelScore = new ScorePanel(game);
			panelScore.setBounds(50+(j*300) , 150+(50*k), 250, 43);
			addActionToPanelScore(panelScore, game);
			JLabel nameTeam1Label=new JLabel(game.getTeam1().getName(),SwingConstants.CENTER);
			nameTeam1Label.setBounds(50+(j*300)+5, 150+(50*k)+15, 73, 13);
			nameTeam1Label.setFont(new Font(Font.DIALOG, Font.BOLD, 13));
			panel.add(nameTeam1Label);
			JLabel nameTeam2Label=new JLabel(game.getTeam2().getName(),SwingConstants.CENTER);
			nameTeam2Label.setBounds(50+(j*300)+173, 150+(50*k)+15, 73, 13);
			nameTeam2Label.setFont(new Font(Font.DIALOG, Font.BOLD, 13));
			panel.add(nameTeam2Label);
			panel.add(panelScore);
			
		}
	}
	
	
	
	
	
	
}
