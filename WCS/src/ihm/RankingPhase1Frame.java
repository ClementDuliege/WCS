package ihm;

import java.awt.BasicStroke;
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
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import data.GroupStage;
import data.WorldCup;
import data.RankingGroupStage;

/**
 * This class display the ranking of the phase1
 * @author WCS
 *
 */
public class RankingPhase1Frame extends JFrame {
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
	private HashMap<String, GroupStage> groupStage1; 
	public RankingPhase1Frame(WorldCup worldCup,String windowsTitle) {
		super(windowsTitle);
		setSize(1300,850);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setAlwaysOnTop(true);
		setVisible(true);
		this.worldCup=worldCup;
		this.contentPane=getContentPane();
		this.windows=this;
		this.groupStage1=worldCup.getGroupStage1();
		displayTableGroup();
		//Initiation of the layout frame
		initLayout();
		
	}
	
	

	/**
	 * This method is used to initialise the layout and place the menu, etc...
	 */
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
	
	
	
	/**
	 * This method is used to initialise actions to the buttons
	 */
	public void initAction() {
		phase1Button.addActionListener(new Phase1());
		phase2Button.addActionListener(new Phase2());
		phaseFinalButton.addActionListener(new PhaseFinale());
		teamsButton.addActionListener(new Teams());
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
	 * ActionListener action of the phase2 button
	 *
	 */
	public class Phase2 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			windows.dispose();
			new Phase2Frame(worldCup,"WCS");
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
	 * ActionListener action of the teams button
	 *
	 */
	public class Teams implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			windows.dispose();
			new ListTeamsFrame("TEAMS",worldCup);
		}
	}
	
	
	
	/**
	 * This method display this ranking of the groups
	 */
	public void displayTableGroup() {
		String listGroup[]= {"GROUPE A","GROUPE B","GROUPE C","GROUPE D","GROUPE E","GROUPE F","GROUPE G","GROUPE H"};
		int k=0;
		for(int i = 0;i<2;i++) {
			for(int j = 0;j<4;j++) {
				JPanel panelTable = new tableGroupPanel(groupStage1.get(listGroup[k]), 0+(320*j), 0+(350*i));
				panelTable.setBounds(25, 135, 1250, 700);
				panel.add(panelTable);
				k++;
			}
		}
		
	}
	
	
	/**
	 * This Class paint the ranking's table of one group
	 * @author WCS
	 *
	 */
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
		
		/**
		 * This method paint the table
		 */
		public void paint(Graphics g) {  
			  
			 Graphics2D g2 = (Graphics2D)g;
			 
			 //rect group
			 g2.drawRect(x, y, 270, 40);
			 g2.setColor(new Color(219,219,219));
			 g2.fillRect(x+1, y+1, 269, 39);
			 g2.setColor(Color.black);
			 
			 //rect infos
			 g2.drawRect(x, y+40, 270, 230);
			 g2.setColor(new Color(135,206,250));
			 g2.fillRect(x+1, y+40, 269, 230);
			 g2.setColor(Color.black);
			

			 g2.setFont(new Font(Font.DIALOG, Font.BOLD, 23)); 
			 g2.drawString(group.getName(), x+75, y+30);
			 
			 g2.setFont(new Font(Font.DIALOG, Font.BOLD, 13));
			 g2.drawString("Rang", x+5, y+60);
			 g2.drawString("Pays", x+65, y+60);
			 g2.drawString("Score", x+220, y+60);


			 g2.drawString("1", x+15, y+95);
			 g2.drawString("2", x+15, y+135);
			 g2.drawString("3", x+15, y+175);
			 g2.drawString("4", x+15, y+215);


			 Image icone1;
			 Image icone2;
			 Image icone3;
			 Image icone4;
			 
			try {
				icone1 = ImageIO.read(new File("Drapeau_rond/"+group.getRanking().getRankingTeams()[0].getName()+".png"));
				icone2 = ImageIO.read(new File("Drapeau_rond/"+group.getRanking().getRankingTeams()[1].getName()+".png"));
				icone3 = ImageIO.read(new File("Drapeau_rond/"+group.getRanking().getRankingTeams()[2].getName()+".png"));
				icone4 = ImageIO.read(new File("Drapeau_rond/"+group.getRanking().getRankingTeams()[3].getName()+".png"));
				g2.drawImage(icone1,x+65,y+79,20,20,this);
				g2.drawImage(icone2,x+65,y+119,20,20,this);
				g2.drawImage(icone3,x+65,y+159,20,20,this);
				g2.drawImage(icone4,x+65,y+199,20,20,this);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
				
			 //name team
			 g2.drawString(""+group.getRanking().getRankingTeams()[0].getName(), x+95, y+94);
			 g2.drawString(""+group.getRanking().getRankingTeams()[1].getName(), x+95, y+134);
			 g2.drawString(""+group.getRanking().getRankingTeams()[2].getName(), x+95, y+174);
			 g2.drawString(""+group.getRanking().getRankingTeams()[3].getName(), x+95, y+213);
			 
			 g2.drawString(""+rankingGroupStage.getScoreTeams().get(group.getRanking().getRankingTeams()[0].getName()), x+240, y+95);
			 g2.drawString(""+rankingGroupStage.getScoreTeams().get(group.getRanking().getRankingTeams()[1].getName()), x+240, y+135);
			 g2.drawString(""+rankingGroupStage.getScoreTeams().get(group.getRanking().getRankingTeams()[2].getName()), x+240, y+175);
			 g2.drawString(""+rankingGroupStage.getScoreTeams().get(group.getRanking().getRankingTeams()[3].getName()), x+240, y+215);

		}
	}

}