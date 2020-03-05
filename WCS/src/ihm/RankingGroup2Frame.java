package ihm;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import data.GroupStage;
import data.WorldCup;
import ihm.Phase2Frame.Phase1;
import ihm.Phase2Frame.PhaseFinale;
import ihm.Phase2Frame.Teams;

public class RankingGroup2Frame extends JFrame {
	private WorldCup worldCup;
	private JFrame windows;
	private Container contentPane;
	private JPanel panel = new JPanel();
	private JButton backButton=new JButton("Back");
	private JButton phase1Button=new JButton("Phase 1");
	private JButton phase2Button=new JButton("Phase 2");
	private JButton phaseFinalButton=new JButton("Phase Finale");
	private JButton teamsButton=new JButton("Equipe");
	private HashMap<String, GroupStage> groupStage2; 
	public RankingGroup2Frame(WorldCup worldCup,String windowsTitle) {
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
		groupStage2=worldCup.getGroupStage2();
		displayTableGroup();
		initLayout();
		
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
		contentPane.add(panel);
	}
	
	public void initAction() {
		phase1Button.addActionListener(new Phase1());
		phase2Button.addActionListener(new Phase2());
		phaseFinalButton.addActionListener(new PhaseFinale());
		teamsButton.addActionListener(new Teams());
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
	
	public void displayTableGroup() {
		String listGroup[]= {"GROUPE I","GROUPE J","GROUPE K","GROUPE L"};
		int k=0;
		for(int i = 0;i<4;i++) {
			
			JPanel panelTable = new tableGroupPanel(groupStage2.get(listGroup[k]), 0+(320*i), 0);
			panelTable.setBounds(25, 85, 1250, 700);
			panel.add(panelTable);
			k++;
			
		}
		
	}
	
	public class tableGroupPanel extends JPanel {
		private GroupStage group;
		private int x,y;
		public tableGroupPanel(GroupStage group, int x,int y) {
			this.group=group;
			this.x=x;
			this.y=y;
		}
		
		public void paint(Graphics g) {  
			  
			 Graphics2D g2 = (Graphics2D)g;
			 
			 g2.setStroke(new BasicStroke(5));
			 g2.drawLine(x, y, x+280, y);
			 g2.drawLine(x+280, y, x+280, y+300);
			 g2.drawLine(x+280, y+300, x, y+300);
			 g2.drawLine(x, y+300, x, y);
			
			 g2.setColor(Color.black);
			 g2.setFont(new Font("TimesRoman", Font.BOLD, 25)); 
			 g2.drawString(group.getName(), x+75, y+30);
			 
			 g2.setFont(new Font("TimesRoman", Font.BOLD, 15)); 
			 g2.drawString("1 - "+group.getRanking().getRankingTeams()[0].getName(), x+15, y+60);
			 g2.drawString("2 - "+group.getRanking().getRankingTeams()[1].getName(), x+15, y+90);
			 g2.drawString("3 - "+group.getRanking().getRankingTeams()[2].getName(), x+15, y+120);
			 g2.drawString("4 - "+group.getRanking().getRankingTeams()[3].getName(), x+15, y+150);
			 
			 
			 
		}
	}

}
