package ihm;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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

import data.Game;
import data.WorldCup;
import ihm.TeamFrame.Back;

public class Phase1Frame extends JFrame {
	private WorldCup worldCup;
	private JFrame windows;
	private Container contentPane;
	private JPanel panel = new JPanel();
	private JButton backButton=new JButton("Back");
	private JButton phase2Button=new JButton("Phase2");
	
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
		
		backButton.setBounds(1200, 750, 100, 40);
		backButton.addActionListener(new Back());
		
		phase2Button.setBounds(200, 50, 100, 25);
		phase2Button.addActionListener(new Phase2());
		panel.add(backButton);
		panel.add(phase2Button);
		
		displayGroup();
		contentPane.add(panel);
	}
	
	public void displayGroup() {
		JLabel groupA=new JLabel("GROUPE A");
		groupA.setBounds(145, 100, 200, 30);
		groupA.setForeground(new Color(239,144,52));
		JLabel groupB=new JLabel("GROUPE B");
		groupB.setBounds(445, 100, 200, 30);
		groupB.setForeground(new Color(239,144,52));
		JLabel groupC=new JLabel("GROUPE C");
		groupC.setBounds(745, 100, 200, 30);
		groupC.setForeground(new Color(239,144,52));
		JLabel groupD=new JLabel("GROUPE D");
		groupD.setBounds(1045, 100, 200, 30);
		groupD.setForeground(new Color(239,144,52));
		JLabel groupE=new JLabel("GROUPE E");
		groupE.setBounds(145, 450, 200, 30);
		groupE.setForeground(new Color(239,144,52));
		JLabel groupF=new JLabel("GROUPE F");
		groupF.setBounds(445, 450, 200, 30);
		groupF.setForeground(new Color(239,144,52));
		JLabel groupG=new JLabel("GROUPE G");
		groupG.setBounds(745, 450, 200, 30);
		groupG.setForeground(new Color(239,144,52));
		JLabel groupH=new JLabel("GROUPE H");
		groupH.setBounds(1045, 450, 200, 30);
		groupH.setForeground(new Color(239,144,52));
		
		panel.add(groupA);
		panel.add(groupB);
		panel.add(groupC);
		panel.add(groupD);
		panel.add(groupE);
		panel.add(groupF);
		panel.add(groupG);
		panel.add(groupH);
		
		
		
		
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
		private Game ga;
		public ScorePanel(Game g) {
			
			this.ga=g;
		}
		 public void paint(Graphics g) {  
			  
			 Graphics2D g2 = (Graphics2D)g;
			 try {
				Image icone2 = ImageIO.read(new File("score.jpg"));
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
			 g2.drawString(""+ga.getScore1(), 83, 25);
			 g2.drawString(""+ga.getScore2(), 150, 25);
			 
			
			
		 }
	}
	
	public class Back implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			windows.dispose();
			new MainFrame("WCS",worldCup);
			
		   
			
		}
	}
	
	public class Phase2 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			windows.dispose();
			new Phase2Frame(worldCup,"WCS");
			
		   
			
		}
	}
}
