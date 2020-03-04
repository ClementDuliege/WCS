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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.Game;
import data.WorldCup;
import ihm.Phase1Frame.Back;
import ihm.Phase1Frame.Phase2;
import ihm.Phase1Frame.ScorePanel;

public class Phase2Frame extends JFrame {
	private WorldCup worldCup;
	private JFrame windows;
	private Container contentPane;
	private JPanel panel = new JPanel();
	private JButton backButton=new JButton("Back");
	private JButton phase2Button=new JButton("Phase2");
	private JButton phaseFinalButton = new JButton("Final Phase");
	
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
		
		backButton.setBounds(1200, 750, 100, 40);
		backButton.addActionListener(new Back());
		
		phaseFinalButton.setBounds(100, 50, 150, 30);
		phaseFinalButton.addActionListener(new PhaseFinal());
		panel.add(phaseFinalButton);
		panel.add(backButton);
		
		
		displayGroup();
		contentPane.add(panel);
	}
	
	public void displayGroup() {
		JLabel groupI=new JLabel("GROUPE I");
		groupI.setBounds(145, 100, 200, 30);
		groupI.setForeground(new Color(239,144,52));
		JLabel groupJ=new JLabel("GROUPE J");
		groupJ.setBounds(445, 100, 200, 30);
		groupJ.setForeground(new Color(239,144,52));
		JLabel groupK=new JLabel("GROUPE K");
		groupK.setBounds(745, 100, 200, 30);
		groupK.setForeground(new Color(239,144,52));
		JLabel groupL=new JLabel("GROUPE L");
		groupL.setBounds(1045, 100, 200, 30);
		groupL.setForeground(new Color(239,144,52));
		
		panel.add(groupI);
		panel.add(groupJ);
		panel.add(groupK);
		panel.add(groupL);
	
		
		
		
		
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
	
	public class PhaseFinal implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			windows.dispose();
			new FinalPhaseFrame(worldCup,"WCS");
			
		   
			
		}
	}
}
