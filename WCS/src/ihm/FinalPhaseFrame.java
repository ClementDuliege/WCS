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
import javax.swing.JPanel;

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
		
		
		initAction();
		
		JPanel pane2 =new TableauPhaseFinal(worldCup);
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
	
	
	public void addActionToPanelScore(JPanel panelScore,Game game) {
		panelScore.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
	           setCursor(handCursor);
	           
	         }
			public void mousePressed(MouseEvent e) {
				 windows.dispose();
		         new GameFrame("Game",worldCup,game);
			}
			 public void mouseExited(MouseEvent e) {
				 Cursor defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);
				 setCursor(defaultCursor);
			 }
			
		});
		
		
	}
	
	
	class TableauPhaseFinal extends JPanel{

		/**
		 *
		 */
		private static final long serialVersionUID = 1L;
		private WorldCup worldCup;
		private Game[] quarterFinal;
		private Game[] semiFinal;
		private Game finalGame;
		private Game smallFinalGame;
	
		public TableauPhaseFinal(WorldCup worldCup) {
			this.worldCup=worldCup;
			quarterFinal=worldCup.getQuarterFinal();
			semiFinal=worldCup.getSemiFinal();
			finalGame=worldCup.getFinalGame();
			smallFinalGame=worldCup.getSmallFinalGame();
		}
		 public void paint(Graphics g) {  
			  
			 Graphics2D g2 = (Graphics2D)g;
			 Image icone2;
			 
			 g2.setStroke(new BasicStroke(10));
			 g2.setColor(new Color(225,82,11));
			 g2.drawLine(125, 43, 125, 121);
			 g2.drawLine(125, 400, 125, 322);
			 g2.drawLine(925, 43, 925, 121);
			 g2.drawLine(925, 400, 925, 322);
			 
			 g2.drawLine(125, 121, 220, 121);
			 g2.drawLine(125, 322, 220, 322);
			 g2.drawLine(925, 121, 830, 121);
			 g2.drawLine(925, 322, 830, 322);
			 
			 g2.drawLine(220, 121, 220, 201);
			 g2.drawLine(830, 121, 830, 201);
			 g2.drawLine(220, 322, 220, 242);
			 g2.drawLine(830, 322, 830, 242);
			 
			 g2.drawLine(350, 222, 400, 222);
			 g2.drawLine(650, 222, 710, 222);
			 
			 int k=0;
			 
			
			 for(int i = 0;i<2;i++) {
				 for(int j = 0 ;j<2;j++) {
					 Game quarterGame=quarterFinal[k];
					 
					 JPanel panelQuarter=new ScorePanel(quarterGame);
					 panelQuarter.setBounds(0 +(j*800),0+(i*400),250,43);
					 addActionToPanelScore(panelQuarter,quarterGame);
					 add(panelQuarter);
					 try {
							icone2 = ImageIO.read(new File("score.jpg")); // "score.jpg"
							g2.drawImage(icone2,0 +(j*800),0+(i*400),250,43,this);
									
						}catch(IOException exc){
								exc.printStackTrace();
						}
					 g2.setFont(new Font("TimesRoman", Font.BOLD, 15)); 
					 //g2.setColor(Color.blue);
					 g2.setColor(Color.black);
					 g2.drawString(quarterGame.getTeam1().getName(), 5 +(j*800), 25+(i*400));
					 g2.drawString(quarterGame.getTeam2().getName(), 170+(j*800), 25+(i*400));
					 g2.setColor(Color.white);
					 g2.drawString(""+quarterGame.getScore1(), 83+(j*800), 25+(i*400));
					 g2.drawString(""+quarterGame.getScore2(), 150+(j*800), 25+(i*400));
					 k++;
				 }
			 }
			 
			 for(int i2= 0;i2<2;i2++) {
				 Game semiGame=semiFinal[i2];
				 JPanel panelSemi=new ScorePanel(semiGame);
				 panelSemi.setBounds(100 +(i2*610),200,250,43);
				 addActionToPanelScore(panelSemi,semiGame);
				 add(panelSemi);
				 try {
						icone2 =  ImageIO.read(new File("score.jpg")); // "score.jpg"
						g2.drawImage(icone2,100 +(i2*610),200,250,43,this);
								
				}catch(IOException exc){
							exc.printStackTrace();
				}
				 g2.setFont(new Font("TimesRoman", Font.BOLD, 15)); 
				 //g2.setColor(Color.blue);
				 g2.setColor(Color.black);
				 g2.drawString(semiGame.getTeam1().getName(), 105+(i2*610), 225);
				 g2.drawString(semiGame.getTeam2().getName(), 270+(i2*610), 225);
				 g2.setColor(Color.white);
				 g2.drawString(""+semiGame.getScore1(), 183+(i2*610), 225);
				 g2.drawString(""+semiGame.getScore2(), 250+(i2*610), 225);
				 k++;
				 
			 }
			 
			 
			 try {
					icone2 =  ImageIO.read(new File("score.jpg")); // "score.jpg"
					g2.drawImage(icone2,400,200,250,43,this);
							
			}catch(IOException exc){
						exc.printStackTrace();
			}
			 
			 JPanel panelFinal=new ScorePanel(finalGame);
			 panelFinal.setBounds(400,200,250,43);
			 addActionToPanelScore(panelFinal,finalGame);
			 add(panelFinal);
			 
			 g2.setFont(new Font("TimesRoman", Font.BOLD, 15)); 
			 //g2.setColor(Color.blue);
			 g2.setColor(Color.black);
			 g2.drawString(finalGame.getTeam1().getName(), 405, 225);
			 g2.drawString(finalGame.getTeam2().getName(), 575, 225);
			 g2.setColor(Color.white);
			 g2.drawString(""+finalGame.getScore1(), 483, 225);
			 g2.drawString(""+finalGame.getScore2(), 550, 225);
			 
			 
			 
			 try {
					icone2 =  ImageIO.read(new File("score.jpg")); // "score.jpg"
					g2.drawImage(icone2,400,300,250,43,this);
							
			}catch(IOException exc){
						exc.printStackTrace();
			}
			 
			 JPanel panelSmallFinal=new ScorePanel(smallFinalGame);
			 panelSmallFinal.setBounds(400,300,250,43);
			 addActionToPanelScore(panelSmallFinal,smallFinalGame);
			 add(panelSmallFinal);
			 
			 g2.setFont(new Font("TimesRoman", Font.BOLD, 15)); 
			 //g2.setColor(Color.blue);
			 g2.setColor(Color.black);
			 g2.drawString(smallFinalGame.getTeam1().getName(), 405, 325);
			 g2.drawString(smallFinalGame.getTeam2().getName(), 575, 325);
			 g2.setColor(Color.white);
			 g2.drawString(""+smallFinalGame.getScore1(), 483, 325);
			 g2.drawString(""+smallFinalGame.getScore2(), 550, 325);
			 //g2.draw
			 
			try {
					icone2 = ImageIO.read(new File("trophee.png")); // "score.jpg"
					g2.drawImage(icone2,360,350,320,320,this);
							
			}catch(IOException exc){
						exc.printStackTrace();
			}
			 g2.setColor(Color.black);
			Team winner = worldCup.getWinnerTeam();
			 g2.setFont(new Font("TimesRoman", Font.BOLD, 20)); 

			 g2.drawString(winner.getName(), 450, 412);
			
			
			
		 }
		 
		
	}
	
	

	
}