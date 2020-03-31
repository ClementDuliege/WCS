package ihm;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.AttributedString;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import data.Player;
import data.Team;
import data.WorldCup;
import ihm.Phase2Frame.Phase1;
import ihm.Phase2Frame.PhaseFinale;
import ihm.Phase2Frame.Teams;

public class TeamFrame extends JFrame {
	/**
	 *
	 */ 
	private static final long serialVersionUID = 1L;
	protected JFrame windows;
	private WorldCup worldCup;
	private Container contentPane;
	//private JButton tabButton[]= new JButton[32];
	private JLabel teamsLabel;
	private JButton backButton=new JButton("Back");
	private int indexTeam;
	private Team team;
	private JPanel panel;
	private PlayerPanel playerPanel;
	private JButton phase1Button=new JButton("Phase 1");
	private JButton phase2Button=new JButton("Phase 2");
	private JButton phaseFinalButton=new JButton("Phase Finale");
	private JButton teamsButton=new JButton("Equipes");
	
	private boolean varStatTeam = false;
	private int numberPlayers = 0;
	private int attackGen;
	private int shot2PtsGen;
	private int shot3PtsGen;
	private int freeThrowsGen;
	private int passGen;
	private int ballHandleGen;
	private int reboundOffGen;
	private int defenseGen;
	private int blockGen;
	private int stealGen;
	private int reboundDefGen;
	private int strenghtGen;
	private int reactionTimeGen;
	private int speedGen;
	
	public TeamFrame(String windowsTitle, WorldCup worldCup, int indexTeam) {
		super(windowsTitle);
		setSize(1300,850);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setAlwaysOnTop(true);
		setVisible(true);
		windows=this;
		this.worldCup=worldCup;
		contentPane=getContentPane();
		contentPane.setBackground(Color.YELLOW);
		this.indexTeam=indexTeam;
		ArrayList<Team> teams=worldCup.getTeams();
		team=teams.get(this.indexTeam);
		panel=new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		
		initLayout();
		statsTeams();
		 writePlayerName();
		
	}
	
	public void initLayout() {
		
		
		
		GridLayout grid = new GridLayout(1,1);
		contentPane.setLayout(grid);
		Font freshman = null;
		
		
		//Nom d'équipe
		teamsLabel=new JLabel(team.getName());
		try {
			freshman = Font.createFont(Font.TRUETYPE_FONT, new File("Freshman.ttf")).deriveFont(45f);
		     GraphicsEnvironment ge =
		         GraphicsEnvironment.getLocalGraphicsEnvironment();
		     ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Freshman.ttf")));
		} catch (IOException|FontFormatException e) {
		     //Handle exception
		}
		teamsLabel.setFont(freshman);
		teamsLabel.setBounds(120, 130, 500, 50);
		
		phase1Button.setBounds(150, 50, 200, 25);
		phase2Button.setBounds(400, 50, 200, 25);
		phaseFinalButton.setBounds(650, 50, 200, 25);
		teamsButton.setBounds(900, 50, 200, 25);
		
		panel.add(phase1Button);
		panel.add(phase2Button);
		panel.add(phaseFinalButton);
		panel.add(teamsButton);
		
		initAction();
		panel.add(teamsLabel);
		
		contentPane.add(panel);
		
		
	}
	
	public void initAction() {
		phase1Button.addActionListener(new Phase1());
		phaseFinalButton.addActionListener(new PhaseFinale());
		phase2Button.addActionListener(new Phase2());
		teamsButton.addActionListener(new TeamAction());
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
	
	
	public class Phase2 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			windows.dispose();
			new Phase2Frame(worldCup,"TEAMS");
		}
	}
	
	public class TeamAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			windows.dispose();
			new ListTeamsFrame("TEAMS",worldCup);
		}
	}
	
	
	public void writePlayerName() {
		
		ArrayList<Player>  players = team.getPlayers();

		JButton listLabel[]=new JButton[10];
		
		
		for(int i = 0;i<players.size();i++) {
			JButton playerLabel = new JButton(players.get(i).getName());
			playerLabel.setBounds(100, 200+(i*40), 150, 30);
			playerLabel.setForeground(Color.WHITE);
			playerLabel.setBackground(new Color(225,82,11));
			 
			
			panel.add(playerLabel);
			listLabel[i]=playerLabel;
		}
		
		addActionToLabel(listLabel);
		
		playerPanel= new PlayerPanel(players.get(0));
		playerPanel.setBounds(550, 60, 600, 750);
		panel.add(playerPanel);
		
		
	}
	public void statsTeams() {
		
		
		JButton button = new JButton("Equipe");
		button.setBounds(100, 650, 150, 30);
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(225,82,11));
		panel.add(button);
		addActionStatsTeams(button);
	}
	public void addActionStatsTeams(JButton button) {
		ArrayList<Player>  players = team.getPlayers();
		numberPlayers = players.size();
		button.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				//Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
	            ///setCursor(handCursor);
	           
	         }
			public void mousePressed(MouseEvent e) {
				attackGen = 0;
				shot2PtsGen = 0;
				shot3PtsGen = 0;
				freeThrowsGen = 0;
				passGen = 0;
				ballHandleGen = 0;
				reboundOffGen = 0;
				defenseGen = 0;
				blockGen = 0;
				stealGen = 0;
				reboundDefGen = 0;
				strenghtGen = 0;
				reactionTimeGen = 0;
				speedGen = 0;
				varStatTeam = true;
				for(int i = 0;i<players.size();i++) {
					attackGen += players.get(i).getAttack();
					shot2PtsGen += players.get(i).getShot2Pts();
					shot3PtsGen += players.get(i).getShot3Pts();
					freeThrowsGen += players.get(i).getFreeThrows();
					passGen += players.get(i).getPass();
					ballHandleGen += players.get(i).getBallHandle();
					reboundOffGen += players.get(i).getReboundOff();
					defenseGen += players.get(i).getDefense();
					blockGen += players.get(i).getBlock();
					stealGen += players.get(i).getSteal();
					reboundDefGen += players.get(i).getReboundDef();
					strenghtGen += players.get(i).getStrenght();
					reactionTimeGen += players.get(i).getReactionTime();
					speedGen += players.get(i).getSpeed();
				}
				 panel.repaint();
				
				
				
			}
			 public void mouseExited(MouseEvent e) {
				 //Cursor defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);
		           // setCursor(defaultCursor);
		         }
			
		});
	}
	
	public void addActionToLabel(JButton[] listLabel) {
		for(int i = 0;i<10;i++) {
			JButton j = listLabel[i];
			j.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					//Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
		            ///setCursor(handCursor);
		           
		         }
				public void mousePressed(MouseEvent e) {
					panel.remove(playerPanel);
					JButton playerLabel = (JButton)e.getSource();
					String name = playerLabel.getText();
					ArrayList<Player>  players = team.getPlayers();
			        int i =0;
			        while(!players.get(i).getName().equals(name)) {
			        	i++;
			        }
			        playerPanel.setPlayer(players.get(i));
			        varStatTeam = false;
			        playerPanel.repaint();
					panel.add(playerPanel);
					
			  
			        panel.repaint();
				}
				 public void mouseExited(MouseEvent e) {
					 //Cursor defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);
			           // setCursor(defaultCursor);
			         }
				
			});
		}
	}
	
	public class Back implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			windows.dispose();
			new ListTeamsFrame("listTeams", worldCup);
			
		   
			
		}
	}
	
	
	public class PlayerPanel extends JPanel{
		/**
		 *
		 */
		private static final long serialVersionUID = 1L;
		private Player player;
		private int attack;
		private int shot2Pts;
		private int shot3Pts;
		private int freeThrows;
		private int pass;
		private int ballHandle;
		private int reboundOff;
		private int defense;
		private int block;
		private int steal;
		private int reboundDef;
		private int strenght;
		private int reactionTime;
		private int speed;
		public PlayerPanel(Player p) {
			
			this.player=p;
		}
		 public void paint(Graphics g) {  
			  
			 Graphics2D g2 = (Graphics2D)g;
			 Font subscribe = null;
			 Font test = null;
			 
			 if(varStatTeam) {
				 try {
						Image icone3 = ImageIO.read(new File("koroko.jpg"));//"player.png"
						g2.drawImage(icone3,15,100,420,235,this);
								
					}catch(IOException exc){
							exc.printStackTrace();
					}
				 
				 //rect
				 g2.setStroke(new BasicStroke(5));
				 g2.setColor(Color.black);
				 g2.drawRect(0, 400, 470, 310);
				 g2.setColor(new Color(219,219,219));
				 g2.fillRect(3, 402, 467, 308);
				 g2.setColor(Color.black);
				 g2.drawLine(0, 460, 470, 460);
				 g2.drawLine(225, 400, 225, 710);
				 
				 //Caractéristiques équipe
				 g2.setColor(Color.red);
				 g2.setFont(new Font("Georgia", Font.BOLD, 25));
				 g2.drawString("ATTAQUE : ", 9, 445);
				 g2.drawString((Integer.toString(attackGen/numberPlayers)), 162, 442);
				
				 
				 g2.setFont(new Font("Calibri", Font.BOLD, 25));
				 g2.drawString("Tir 2 Pts : ", 15, 500);
				 g2.drawString((Integer.toString(shot2PtsGen/numberPlayers)), 162, 500);
				 
				
				 g2.drawString("Tir 3 Pts : ", 15, 540);
				 g2.drawString((Integer.toString(shot3PtsGen/numberPlayers)), 162, 540);
				 
				 g2.drawString("Lancer Franc : ", 15, 580);
				 g2.drawString((Integer.toString(freeThrowsGen/numberPlayers)), 162, 580);
				 
				 g2.drawString("Passe : ", 15, 620);
				 g2.drawString((Integer.toString(passGen/numberPlayers)), 162, 620);
				 
				 g2.drawString("Dribble : ", 15, 660);
				 g2.drawString((Integer.toString(ballHandleGen/numberPlayers)), 162, 660);
				 
				 g2.drawString("Vitesse : ", 15, 700);
				 g2.drawString((Integer.toString(speedGen/numberPlayers)), 162, 700);
				 
				 //colonne 2
				 g2.setFont(new Font("Georgia", Font.BOLD, 25));
				 g2.setColor(Color.blue);
				 g2.drawString("DEFENSE : ", 245, 445);
				 g2.drawString((Integer.toString(defenseGen/numberPlayers)), 400, 445);
				 
				 g2.setFont(new Font("Calibri", Font.BOLD, 25));
				 g2.drawString("Contre : ", 250, 500);
				 g2.drawString((Integer.toString(blockGen/numberPlayers)), 400, 500);
				 
				 g2.drawString("Interception : ", 250, 540);
				 g2.drawString((Integer.toString(stealGen/numberPlayers)), 400, 540);
				 
				 
				 g2.drawString("Rebond off : ", 250, 580);
				 g2.drawString((Integer.toString(reboundOffGen/numberPlayers)), 400, 580);
				    
				 g2.drawString("Rebond def : ", 250, 620);
				 g2.drawString((Integer.toString(reboundDefGen/numberPlayers)), 400, 620);
				 
				 g2.drawString("Force : ", 250, 660);
				 g2.drawString((Integer.toString(strenghtGen/numberPlayers)), 400, 660);
				 
				 g2.drawString("Reaction : ", 250, 700);
				 g2.drawString((Integer.toString(reactionTimeGen/numberPlayers)), 400, 700);
				 
				 g2.setColor(Color.black);
				 try {
					subscribe = Font.createFont(Font.TRUETYPE_FONT, new File("Freshman.ttf")).deriveFont(25f);
			
				} catch (FontFormatException|IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 g2.setFont(subscribe);
				 g2.drawString("STATS GENERALES DE L'EQUIPE", 20, 380);
			 } 
			 
			 else {
			 try {
				System.out.print("pictureTeams/"+team.getName()+"/"+player.getName()+".png");
				Image icone2 = ImageIO.read(new File("pictureTeams/"+team.getName()+"/"+player.getName()+".png"));//"player.png"
				System.out.print("pictureTeams/"+team.getName()+"/"+player.getName()+".png");
				g2.drawImage(icone2,15,130,191,245,this);
						
			}catch(IOException exc){
					exc.printStackTrace();
			}
			 g2.setFont(new Font("Calibri", Font.BOLD, 25)); 
			 g2.setColor(new Color(225,82,11));
			 String[] names= player.getName().split("\\.");
			 g2.drawString(names[0]+" "+names[1], 20, 115);
			 g2.setColor(Color.black);
			 
			 //rect
			 g2.setStroke(new BasicStroke(5));
			 g2.setColor(Color.black);
			 g2.drawRect(0, 400, 470, 310);
			 g2.setColor(new Color(219,219,219));
			 g2.fillRect(3, 402, 467, 308);
			 g2.setColor(Color.black);
			 g2.drawLine(0, 460, 470, 460);
			 g2.drawLine(225, 400, 225, 710);
			 
			 //colonne 1
			 g2.setColor(Color.red);
			 g2.setFont(new Font("Georgia", Font.BOLD, 25));
			 g2.drawString("ATTAQUE : ", 9, 445);
			 g2.drawString(""+player.getAttack(), 162, 442);
			 
			 g2.setFont(new Font("Calibri", Font.BOLD, 25));
			 g2.drawString("Tir 2 Pts : ", 15, 500);
			 g2.drawString(""+player.getShot2Pts(), 162, 500);
			 
			 g2.drawString("Tir 3 Pts : ", 15, 540);
			 g2.drawString(""+player.getShot3Pts(), 162, 540);
			 
			 g2.drawString("Lancer Franc : ", 15, 580);
			 g2.drawString(""+player.getFreeThrows(), 162, 580);
			 
			 g2.drawString("Passe : ", 15, 620);
			 g2.drawString(""+player.getPass(), 162, 620);
			 
			 g2.drawString("Dribble : ", 15, 660);
			 g2.drawString(""+player.getBallHandle(), 162, 660);
			 
			 g2.drawString("Vitesse : ", 15, 700);
			 g2.drawString(""+player.getSpeed(), 162, 700);
			 
			 //Colonne 2
			 g2.setColor(Color.blue);
			 g2.setFont(new Font("Georgia", Font.BOLD, 25));
			 g2.drawString("DEFENSE : ", 245, 445);
			 g2.drawString(""+player.getDefense(), 400, 445);
			 
			 g2.setFont(new Font("Calibri", Font.BOLD, 25));
			 g2.drawString("Contre : ", 250, 500);
			 g2.drawString(""+player.getBlock(), 400, 500);
			 
			 g2.drawString("Interception : ", 250, 540);
			 g2.drawString(""+player.getSteal(), 400, 540);
			 
			 g2.drawString("Rebond off : ", 250, 580);
			 g2.drawString(""+player.getReboundOff(), 400, 580);
			 
			 g2.drawString("Rebond def : ", 250, 620);
			 g2.drawString(""+player.getReboundDef(), 400, 620);
			 
			 g2.drawString("Force : ", 250, 660);
			 g2.drawString(""+player.getStrenght(), 400, 660);
			 
			 g2.drawString("Reaction : ", 250, 700);
			 g2.drawString(""+player.getReactionTime(), 400, 700);
			 
			 g2.setColor(Color.BLACK);
			 try {
					subscribe = Font.createFont(Font.TRUETYPE_FONT, new File("BeTrueToYourSchool.ttf")).deriveFont(35f);
			
				} catch (FontFormatException|IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 g2.setFont(subscribe);
			 g2.drawString("Poste : "+player.getPositionString(), 230, 380);
			 }
		 }
		 
		 public void setPlayer(Player p) {
			 player=p;
		 }
	}
	
	

	
	

}
