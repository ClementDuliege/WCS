package ihm;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import data.Player;
import data.Team;
import data.WorldCup;


/**
 * This class display one team
 * @author WCS
 *
 */
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
	private StatPanel playerPanel;
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
		this.windows=this;
		this.worldCup=worldCup;
		this.contentPane=getContentPane();
		this.indexTeam=indexTeam;
		ArrayList<Team> teams=worldCup.getTeams();
		this.team=teams.get(this.indexTeam);
		this.panel=new JPanel();
		this.panel.setLayout(null);
		this.panel.setBackground(Color.WHITE);
		
		//Initiation of the layout frame
		initLayout();
		//Display name's player
		writePlayerName();
		
	}
	
	
	
	/**
	 * This method is used to initialise the layout and place the menu, to create a button for each team.
	 */
	public void initLayout() {
		GridLayout grid = new GridLayout(1,1);
		contentPane.setLayout(grid);
		Font freshman = null;
		
		
		//name's team
		teamsLabel=new JLabel(team.getName());
		try {
			freshman = Font.createFont(Font.TRUETYPE_FONT, new File("Freshman.ttf")).deriveFont(45f);
		     GraphicsEnvironment ge =
		         GraphicsEnvironment.getLocalGraphicsEnvironment();
		     ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Freshman.ttf")));
		} catch (IOException|FontFormatException e) {
		     //Handle exception
		}
		
		JLabel flag = new JLabel(new ImageIcon("pictures/Drapeau/"+team.getName()+".png"));
		flag.setBounds(20, 101, 100, 100);
		
		teamsLabel.setFont(freshman);
		teamsLabel.setBounds(120, 130, 500, 50);
		
		phase1Button.setBounds(150, 50, 200, 25);
		phase2Button.setBounds(400, 50, 200, 25);
		phaseFinalButton.setBounds(650, 50, 200, 25);
		teamsButton.setBounds(900, 50, 200, 25);
		
		JButton button = new JButton("Général");
		button.setBounds(100, 650, 150, 30);
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(225,82,11));
		button.addActionListener(new TeamStatsAction());
		
		panel.add(button);
		panel.add(phase1Button);
		panel.add(phase2Button);
		panel.add(phaseFinalButton);
		panel.add(teamsButton);
		panel.add(flag);
		panel.add(teamsLabel);
		
		contentPane.add(panel);
		
		initAction();	
	}
	
	
	
	/**
	 * This method is used to initialise actions to the buttons
	 */
	public void initAction() {
		phase1Button.addActionListener(new Phase1());
		phaseFinalButton.addActionListener(new PhaseFinale());
		phase2Button.addActionListener(new Phase2());
		teamsButton.addActionListener(new TeamAction());
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
	 * ActionListener action of the phaseFinal button
	 *
	 */
	public class PhaseFinale implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			windows.dispose();
			new FinalPhaseFrame(worldCup,"WCS");
		}
	}
	
	
	/**
	 * ActionListener action of the phase2 button
	 *
	 */
	public class Phase2 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			windows.dispose();
			new Phase2Frame(worldCup,"TEAMS");
		}
	}
	
	
	/**
	 * ActionListener action of the teams button
	 *
	 */
	public class TeamAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			windows.dispose();
			new ListTeamsFrame("TEAMS",worldCup);
		}
	}
	
	
	/**
	 * ActionListener action of the players button
	 *
	 */
	public class PlayersAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
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
	}
	
	
	/**
	 * ActionListener action of the team button
	 *
	 */
	public class TeamStatsAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			ArrayList<Player>  players = team.getPlayers();
			numberPlayers = players.size();
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
	}
	
	
	/**
	 * This method create and place JButtons of the players's name
	 */
	public void writePlayerName() {
		ArrayList<Player>  players = team.getPlayers();
		
		for(int i = 0;i<players.size();i++) {
			JButton playerButton = new JButton(players.get(i).getName());
			playerButton.setBounds(100, 200+(i*40), 150, 30);
			playerButton.setForeground(Color.WHITE);
			playerButton.setBackground(new Color(225,82,11));
			playerButton.addActionListener(new PlayersAction());
			panel.add(playerButton);
			
		}
		
		playerPanel= new StatPanel(players.get(0));
		playerPanel.setBounds(550, 60, 600, 750);
		panel.add(playerPanel);
		
		
	}
	

	
	/**
	 * This class display the stats of one player or team
	 * @author WCS
	 *
	 */
	public class StatPanel extends JPanel{
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
		public StatPanel(Player p) {
			
			this.player=p;
		}
		
		/**
		 * This method paints the stats
		 */
		 public void paint(Graphics g) {  
			  
			 Graphics2D g2 = (Graphics2D)g;
			 Font subscribe = null;
			 Font test = null;
			 
			 if(varStatTeam) {
				 try {
						Image icone3 = ImageIO.read(new File("pictures/pictureTeams/Equipes/"+team.getName()+".png"));//"player.png"
						g2.drawImage(icone3,80,125,300,200,this);
								
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
				 
				 //Team's information
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
					subscribe = Font.createFont(Font.TRUETYPE_FONT, new File("others_files/Freshman.ttf")).deriveFont(25f);
			
				} catch (FontFormatException|IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 g2.setFont(subscribe);
				 g2.drawString("STATS GENERALES DE L'EQUIPE", 20, 380);
			 } 
			 
			 else {
				 try {
					Image icone2 = ImageIO.read(new File("pictures/pictureTeams/"+team.getName()+"/"+player.getName()+".png"));//"player.png"
					g2.drawImage(icone2,125,130,200,200,this);
							
				}catch(IOException exc){
						exc.printStackTrace();
				}
				 g2.setFont(new Font("Calibri", Font.BOLD, 25)); 
				 g2.setColor(new Color(225,82,11));
				 String[] names= player.getName().split("\\.");
				 g2.drawString(names[0]+" "+names[1], 123, 115);
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
						subscribe = Font.createFont(Font.TRUETYPE_FONT, new File("others_files/BeTrueToYourSchool.ttf")).deriveFont(35f);
				
					} catch (FontFormatException|IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 g2.setFont(subscribe);
				 g2.drawString("Poste : "+player.getPositionString(), 125, 380);
			}
		 }
		 /**
		  * Set Player
		  * @param p Player
		  */
		 public void setPlayer(Player p) {
			 player=p;
		 }
	}
	
	

	
	

}
