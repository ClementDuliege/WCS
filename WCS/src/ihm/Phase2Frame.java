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
		JLabel groupI=new JLabel("GROUPE I",SwingConstants.CENTER);
		groupI.setBounds(80, 100, 200, 30);
		groupI.setForeground(Color.WHITE);
		groupI.setOpaque(true);
		groupI.setBackground(new Color(239,144,52));
		
		JLabel groupJ=new JLabel("GROUPE J",SwingConstants.CENTER);
		groupJ.setBounds(380, 100, 200, 30);
		groupJ.setForeground(Color.WHITE);
		groupJ.setOpaque(true);
		groupJ.setBackground(new Color(239,144,52));
		
		JLabel groupK=new JLabel("GROUPE K",SwingConstants.CENTER);
		groupK.setBounds(680, 100, 200, 30);
		groupK.setForeground(Color.WHITE);
		groupK.setOpaque(true);
		groupK.setBackground(new Color(239,144,52));
		
		JLabel groupL=new JLabel("GROUPE L",SwingConstants.CENTER);
		groupL.setBounds(980, 100, 200, 30);
		groupL.setForeground(Color.WHITE);
		groupL.setOpaque(true);
		groupL.setBackground(new Color(239,144,52));
		JLabel[] listLabel = {groupI,groupJ,groupK,groupL};
		addActionToLabel(listLabel);
		panel.add(groupI);
		panel.add(groupJ);
		panel.add(groupK);
		panel.add(groupL);	
	}
	
	public void addActionToLabel(JLabel[] listLabel) {
		for(int i = 0;i<4;i++) {
			JLabel j = listLabel[i];
			j.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
		            setCursor(handCursor);
		           
		         }
				public void mousePressed(MouseEvent e) {
					 windows.dispose();
			         new RankingGroup2Frame(worldCup,"Rangink Group 1");
				}
				 public void mouseExited(MouseEvent e) {
					 Cursor defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);
			            setCursor(defaultCursor);
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
			panel.add(panelScore);
			
		}
	}
	
	
	public class ScorePanel extends JPanel{
		/**
		 *
		 */
		private static final long serialVersionUID = 1L;
		private Game ga;
		public ScorePanel(Game g) {
			
			this.ga=g;
		}
		 public void paint(Graphics g) {  
			  
			 Graphics2D g2 = (Graphics2D)g;
			 try {
				Image icone2 =  ImageIO.read(new File("score.jpg")); // "score.jpg"
				g2.drawImage(icone2,0,0,250,43,this);
						
			}catch(IOException exc){
					exc.printStackTrace();
			}
			 g2.setFont(new Font("TimesRoman", Font.BOLD, 15)); 
			 //g2.setColor(Color.blue);
			 g2.setColor(Color.black);
			 g2.drawString(ga.getTeam1().getName(), 5, 25);
			 g2.drawString(ga.getTeam2().getName(), 170, 25);
			 g2.setColor(Color.white);
			 if(ga.getScore1()<ga.getScore2()) {
				 g2.setColor(Color.red);
			 }
			 else {
				 g2.setColor(Color.green);
			 }
			 g2.drawString(""+ga.getScore1(), 83, 25);
			 if(ga.getScore1()>ga.getScore2()) {
				 g2.setColor(Color.red);
			 }
			 else {
				 g2.setColor(Color.green);
			 }
			 g2.drawString(""+ga.getScore2(), 150, 25);
		 }
	}
	
	
	
}
