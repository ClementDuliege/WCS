package ihm;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
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
		
		 writePlayerName();
		
	}
	
	public void initLayout() {
		
		
		
		GridLayout grid = new GridLayout(1,1);
		contentPane.setLayout(grid);
		
		
		
		teamsLabel=new JLabel(team.getName());
		teamsLabel.setFont(new Font("Serif", Font.BOLD,40));
		teamsLabel.setBounds(120, 150, 500, 40);
		
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
			 
			
			 
			 try {
				Image icone2 = ImageIO.read(new File("player.png"));//"player.png"
				g2.drawImage(icone2,15,130,191,245,this);
						
			}catch(IOException exc){
					exc.printStackTrace();
			}
			 g2.setFont(new Font("TimesRoman", Font.BOLD, 25)); 
			 g2.setColor(new Color(225,82,11));
			 String[] names= player.getName().split("\\.");
			 g2.drawString(names[0]+" "+names[1], 50, 115);
			 g2.setColor(Color.black);
			
			 g2.drawRect(0, 400, 450, 310);
			 //colonne 1
			 g2.drawString("ATTAQUE : ", 15, 450);
			 g2.drawString(""+player.getAttack(), 170, 450);
			 
			 g2.drawString("Shot 2 Pts : ", 15, 490);
			 g2.drawString(""+player.getShot2Pts(), 170, 490);
			 
			 g2.drawString("Shot 3 Pts : ", 15, 530);
			 g2.drawString(""+player.getShot3Pts(), 170, 530);
			 
			 g2.drawString("Free throws : ", 15, 570);
			 g2.drawString(""+player.getFreeThrows(), 170, 570);
			 
			 g2.drawString("Pass : ", 15, 610);
			 g2.drawString(""+player.getPass(), 170, 610);
			 
			 g2.drawString("Ball handle : ", 15, 650);
			 g2.drawString(""+player.getBallHandle(), 170, 650);
			 
			 g2.drawString("Spedd : ", 15, 690);
			 g2.drawString(""+player.getSpeed(), 170, 690);
			 
			 //Colonne 2
			 g2.drawString("DEFENSE : ", 250, 450);
			 g2.drawString(""+player.getDefense(), 405, 450);
			 
			 g2.drawString("Block : ", 250, 490);
			 g2.drawString(""+player.getBlock(), 405, 490);
			 
			 g2.drawString("Steal : ", 250, 530);
			 g2.drawString(""+player.getSteal(), 405, 530);
			 
			 g2.drawString("Rebound off : ", 250, 570);
			 g2.drawString(""+player.getReboundOff(), 405, 570);
			 
			 g2.drawString("Rebound def : ", 250, 610);
			 g2.drawString(""+player.getReboundDef(), 405, 610);
			 
			 g2.drawString("Strenght : ", 250, 650);
			 g2.drawString(""+player.getStrenght(), 405, 650);
			 
			 g2.drawString("Reaction : ", 250, 690);
			 g2.drawString(""+player.getReactionTime(), 405, 690);
			 
			 g2.drawString("Poste : "+player.getPositionString(), 150, 350);
		 }
		 
		 public void setPlayer(Player p) {
			 player=p;
		 }
	}
	
	

	
	

}
