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

public class Phase1Frame extends JFrame {
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
	
	public Phase1Frame(WorldCup worldCup, String windowsTitle) {
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
		String listGroup[]= {"GROUPE A","GROUPE B","GROUPE C","GROUPE D","GROUPE E","GROUPE F","GROUPE G","GROUPE H"};
		int k=0;
		for(int i = 0 ;i<2;i++) {
			for(int j = 0;j<4;j++) {
				displayGameGroup(listGroup[k], i,j);
				k++;
			}
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
	

	
	public void displayGroup() {
		JLabel groupA=new JLabel("GROUPE A",SwingConstants.CENTER);	
		groupA.setBounds(80, 100, 200, 30);
		groupA.setForeground(Color.WHITE);
		groupA.setOpaque(true);
		groupA.setBackground(new Color(239,144,52));
		
		JLabel groupB=new JLabel("GROUPE B",SwingConstants.CENTER);
		groupB.setBounds(380, 100, 200, 30);
		groupB.setForeground(Color.WHITE);
		groupB.setOpaque(true);
		groupB.setBackground(new Color(239,144,52));
		
		JLabel groupC=new JLabel("GROUPE C",SwingConstants.CENTER);
		groupC.setBounds(680, 100, 200, 30);
		groupC.setForeground(Color.WHITE);
		groupC.setOpaque(true);
		groupC.setBackground(new Color(239,144,52));
		
		JLabel groupD=new JLabel("GROUPE D",SwingConstants.CENTER);
		groupD.setBounds(980, 100, 200, 30);
		groupD.setForeground(Color.WHITE);
		groupD.setOpaque(true);
		groupD.setBackground(new Color(239,144,52));
		
		JLabel groupE=new JLabel("GROUPE E",SwingConstants.CENTER);
		groupE.setBounds(80, 450, 200, 30);
		groupE.setForeground(Color.WHITE);
		groupE.setOpaque(true);
		groupE.setBackground(new Color(239,144,52));
		
		JLabel groupF=new JLabel("GROUPE F",SwingConstants.CENTER);
		groupF.setBounds(380, 450, 200, 30);
		groupF.setForeground(Color.WHITE);
		groupF.setOpaque(true);
		groupF.setBackground(new Color(239,144,52));
		
		JLabel groupG=new JLabel("GROUPE G",SwingConstants.CENTER);
		groupG.setBounds(680, 450, 200, 30);
		groupG.setForeground(Color.WHITE);
		groupG.setOpaque(true);
		groupG.setBackground(new Color(239,144,52));
		
		JLabel groupH=new JLabel("GROUPE H",SwingConstants.CENTER);
		groupH.setBounds(980, 450, 200, 30);
		groupH.setForeground(Color.WHITE);
		groupH.setOpaque(true);
		groupH.setBackground(new Color(239,144,52));
		
		JLabel[] listLabel = {groupA,groupB,groupC,groupD,groupE,groupF,groupG,groupH};
		addActionToLabel(listLabel);
		panel.add(groupA);
		panel.add(groupB);
		panel.add(groupC);
		panel.add(groupD);
		panel.add(groupE);
		panel.add(groupF);
		panel.add(groupG);
		panel.add(groupH);
			
	}
	
	public void addActionToLabel(JLabel[] listLabel) {
		for(int i = 0;i<8;i++) {
			JLabel j = listLabel[i];
			j.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
		            setCursor(handCursor);
		           
		         }
				public void mousePressed(MouseEvent e) {
					 windows.dispose();
			         new RankingGroup1Frame(worldCup,"Rangink Group 1");
				}
				 public void mouseExited(MouseEvent e) {
					 Cursor defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);
			            setCursor(defaultCursor);
			         }
				
			});
		}
	}
	
	public void displayGameGroup(String group,int i, int j) {
		ArrayList<Game> listGameGroupA = worldCup.getGroupStage1().get(group).getGames();
		
		for(int k = 0; k<listGameGroupA.size();k++) {
			Game game = listGameGroupA.get(k);
			JPanel panelScore = new ScorePanel(game);
			panelScore.setBounds(50+(j*300) , 150+(i*350)+(50*k), 250, 43);
			panel.add(panelScore);
			
		}
		
		
		
	}
	
	class ScorePanel extends JPanel{
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
	
	public class Back implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			windows.dispose();
			new MainFrame("WCS",worldCup);
			
		   
			
		}
	}
	
	public void initAction() {
		phase2Button.addActionListener(new Phase2());
		phaseFinalButton.addActionListener(new PhaseFinale());
		teamsButton.addActionListener(new Teams());
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
