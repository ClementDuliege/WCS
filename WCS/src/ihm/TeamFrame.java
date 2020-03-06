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
		teamsLabel.setBounds(280, 15, 500, 40);
		
		panel.add(backButton);
		panel.add(teamsLabel);
		
		contentPane.add(panel);
		
		
	}
	
	
	public void writePlayerName() {
		
		ArrayList<Player>  players = team.getPlayers();

		JLabel listLabel[]=new JLabel[10];
		
		
		for(int i = 0;i<players.size();i++) {
			JLabel playerLabel = new JLabel(players.get(i).getName(),SwingConstants.CENTER);
			playerLabel.setBounds(100, 100+(i*40), 150, 30);
			playerLabel.setForeground(Color.WHITE);
			playerLabel.setOpaque(true);
			playerLabel.setBackground(new Color(239,144,52));
			
			
			panel.add(playerLabel);
			listLabel[i]=playerLabel;
		}
		
		addActionToLabel(listLabel);
		
		playerPanel= new PlayerPanel(players.get(0));
		playerPanel.setBounds(550, 60, 400, 700);
		panel.add(playerPanel);
		
		
	}
	
	public void addActionToLabel(JLabel[] listLabel) {
		for(int i = 0;i<10;i++) {
			JLabel j = listLabel[i];
			j.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
		            setCursor(handCursor);
		           
		         }
				public void mousePressed(MouseEvent e) {
					panel.remove(playerPanel);
					JLabel playerLabel = (JLabel)e.getSource();
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
					 Cursor defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);
			            setCursor(defaultCursor);
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
			 String[] names= player.getName().split("\\.");
			 g2.drawString(names[0]+" "+names[1], 50, 115);
			
		 }
		 
		 public void setPlayer(Player p) {
			 player=p;
		 }
	}
	
	

	
	

}
