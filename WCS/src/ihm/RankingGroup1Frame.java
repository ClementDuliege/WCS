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
import data.RankingGroupStage;

public class RankingGroup1Frame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private WorldCup worldCup;
	private JFrame windows;
	private Container contentPane;
	private JPanel panel = new JPanel();
	//private JButton backButton=new JButton("Back");
	private JButton phase1Button=new JButton("Phase 1");
	private JButton phase2Button=new JButton("Phase 2");
	private JButton phaseFinalButton=new JButton("Phase Finale");
	private JButton teamsButton=new JButton("Equipes");
	private HashMap<String, GroupStage> groupStage1; 
	public RankingGroup1Frame(WorldCup worldCup,String windowsTitle) {
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
		groupStage1=worldCup.getGroupStage1();
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
		String listGroup[]= {"GROUPE A","GROUPE B","GROUPE C","GROUPE D","GROUPE E","GROUPE F","GROUPE G","GROUPE H"};
		int k=0;
		for(int i = 0;i<2;i++) {
			for(int j = 0;j<4;j++) {
				JPanel panelTable = new tableGroupPanel(groupStage1.get(listGroup[k]), 0+(320*j), 0+(350*i));
				panelTable.setBounds(25, 85, 1250, 700);
				panel.add(panelTable);
				k++;
			}
		}
		
	}
	
	public class tableGroupPanel extends JPanel {
		/**
		 *
		 */
		private static final long serialVersionUID = 1L;
		private GroupStage group;
		private RankingGroupStage rankingGroupStage;
		private int x,y;
		public tableGroupPanel(GroupStage group ,int x,int y) {
			this.group=group;
			this.rankingGroupStage = group.getRanking();
			this.x=x;
			this.y=y;
		}
		
		public void paint(Graphics g) {  
			  
			 Graphics2D g2 = (Graphics2D)g;
			 
			 g2.setStroke(new BasicStroke(5));
			 g2.setColor(new Color(225,82,11));
			 g2.drawLine(x, y, x+280, y);
			 g2.drawLine(x+280, y, x+280, y+300);
			 g2.drawLine(x+280, y+300, x, y+300);
			 g2.drawLine(x, y+300, x, y);
			
			 g2.setColor(new Color(239,144,52));
			 g2.setFont(new Font("TimesRoman", Font.BOLD, 25)); 
			 g2.drawString(group.getName(), x+75, y+30);
			 
			 g2.setFont(new Font("TimesRoman", Font.BOLD, 15));
			 g2.setColor(new Color(225,82,11));
			 g2.drawString("Rang", x+5, y+60);
			 g2.drawString("Pays", x+60, y+60);
			 g2.drawString("Score", x+220, y+60);


			 g2.drawString("1", x+15, y+90);
			 g2.drawString("2", x+15, y+120);
			 g2.drawString("3", x+15, y+150);
			 g2.drawString("4", x+15, y+180);

			 g2.setColor(new Color(239,144,52));
			 g2.drawString(""+group.getRanking().getRankingTeams()[0].getName(), x+50, y+90);
			 g2.drawString(""+group.getRanking().getRankingTeams()[1].getName(), x+50, y+120);
			 g2.drawString(""+group.getRanking().getRankingTeams()[2].getName(), x+50, y+150);
			 g2.drawString(""+group.getRanking().getRankingTeams()[3].getName(), x+50, y+180);
			 
			 g2.drawString(""+rankingGroupStage.getScoreTeams().get(group.getRanking().getRankingTeams()[0].getName()), x+250, y+90);
			 g2.drawString(""+rankingGroupStage.getScoreTeams().get(group.getRanking().getRankingTeams()[1].getName()), x+250, y+120);
			 g2.drawString(""+rankingGroupStage.getScoreTeams().get(group.getRanking().getRankingTeams()[2].getName()), x+250, y+150);
			 g2.drawString(""+rankingGroupStage.getScoreTeams().get(group.getRanking().getRankingTeams()[3].getName()), x+250, y+180);

			 g2.setColor(new Color(225,82,11));
			 g2.drawLine(x+0, y+40, x+280, y+40);
			 g2.drawLine(x+0, y+70, x+280, y+70);
			 g2.drawLine(x+0, y+100, x+280, y+100);
			 g2.drawLine(x+0, y+130, x+280, y+130);
			 g2.drawLine(x+0, y+160, x+280, y+160);
			 g2.drawLine(x+0, y+190, x+280, y+190);

		}
	}

}