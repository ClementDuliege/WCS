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

import data.WorldCup;
import ihm.RankingGroup2Frame.Phase1;
import ihm.RankingGroup2Frame.Phase2;
import ihm.RankingGroup2Frame.PhaseFinale;
import ihm.RankingGroup2Frame.Teams;
import ihm.RankingGroup2Frame.tableGroupPanel;
import data.GroupStage;
import data.RankingGroupStage;
import data.RankingWorldCup;


public class RankingWorldCupFrame extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private RankingWorldCup rankingWorldCup;
    private int pos = 15;

	private WorldCup worldCup;
	private JFrame windows;
	private Container contentPane;
	private JPanel panel = new JPanel();
	//private JButton backButton=new JButton("Back");
	private JButton phase1Button=new JButton("Phase 1");
	private JButton phase2Button=new JButton("Phase 2");
	private JButton phaseFinalButton=new JButton("Phase Finale");
	private JButton teamsButton=new JButton("Equipe");
	private HashMap<String, GroupStage> groupStage2; 
	public RankingWorldCupFrame(String windowsTitle, WorldCup worldCup) {
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
		String listGroup[]= {"","GROUPE J","GROUPE K","GROUPE L"};
		int k=0;
		for(int i = 0;i<2;i++) {
			
			JPanel panelTable = new tableGroupPanel(worldCup.getRanking(), 0+(320*i), 0,i);
			panelTable.setBounds(25, 85, 1250, 700);
			panel.add(panelTable);
			k++;
			
		}
		
	}
	
	public class tableGroupPanel extends JPanel {
		/**
		 *
		 */
		private static final long serialVersionUID = 1L;
		private RankingWorldCup rankingWorldCup;
		private int x,y;
		private int value;
		public tableGroupPanel(RankingWorldCup rankingWorldCup, int x,int y,int i) {
			this.rankingWorldCup = rankingWorldCup;
			this.value = i;
			this.x=x;
			this.y=y;
		}
		
		public void paint(Graphics g) {  
			  
			 Graphics2D g2 = (Graphics2D)g;
			 
		/*	 g2.setStroke(new BasicStroke(5));
			 g2.drawLine(x, y, x+280, y);//ligne haut
			 g2.drawLine(x+280, y, x+280, y+550);//ligne droite
			 g2.drawLine(x+280, y+550, x, y+550);//ligne bas
			 g2.drawLine(x, y+550, x, y);*/
			 
			 //rect 1
			 g2.drawRect(x, y+30, 285, 40);
			 g2.setColor(new Color(219,219,219));
			 g2.fillRect(x+1, y+31, 284, 39);
			 g2.setColor(Color.black);
			 g2.setColor(Color.black);
			 
			//rect infos
			 g2.drawRect(x, y+70, 285, 530);
			 g2.setColor(new Color(110,167,255));
			 g2.fillRect(x+1, y+70, 284, 530);
			 g2.setColor(Color.black);
			 
			 g2.setFont(new Font(Font.DIALOG, Font.BOLD, 13));
			 g2.drawString("Rang", x+5, y+90);
			 g2.drawString("Pays", x+60, y+90);
			 g2.drawString("Score", x+235, y+90);





			 g2.setColor(Color.black);
			  

			 if(value==0) {
				 
				 g2.setFont(new Font(Font.DIALOG, Font.BOLD, 23));
				 g2.drawString("Part1", x+105, y+60);
				 g2.setFont(new Font(Font.DIALOG, Font.BOLD, 13)); 
				 
				
				 
				
				 y += 30;
				 
				 for(int i = 0; i<16;i++) {
					
					 g2.drawString(Integer.toString(i+1), x+15, y+30*(i+3));
					 g2.drawString(""+rankingWorldCup.getRankingTeams()[i].getName(), x+90, y+30*(i+3));
					 g2.drawString(""+rankingWorldCup.getScoreTeams().get(rankingWorldCup.getRankingTeams()[i].getName()), x+250, y+30*(i+3));

					 try {
							Image icone1 = ImageIO.read(new File("Drapeau_rond/"+rankingWorldCup.getRankingTeams()[i].getName()+".png"));
							
							g2.drawImage(icone1,x+66,pos+30*(i+3),20,20,this);
							
						} catch (IOException e) {
							
							e.printStackTrace();
						}
				 }
			 }
			 else {
				 g2.setFont(new Font(Font.DIALOG, Font.BOLD, 23));
				 g2.drawString("Part2", x+105, y+60);
				 g2.setFont(new Font(Font.DIALOG, Font.BOLD, 13));
				 y += 30;
				 
				 for(int i = 16; i<32;i++) {
					
					 
					 g2.drawString(Integer.toString(i+1), x+15, y+30*(i-16+3));
					 g2.drawString(""+rankingWorldCup.getRankingTeams()[i].getName(), x+90, y+30*(i-16+3));
					 g2.drawString(""+rankingWorldCup.getScoreTeams().get(rankingWorldCup.getRankingTeams()[i].getName()), x+250, y+30*(i-16+3));
					 
					 try {
							Image icone2 = ImageIO.read(new File("Drapeau_rond/"+rankingWorldCup.getRankingTeams()[i].getName()+".png"));
							
							g2.drawImage(icone2,x+66,pos+30*(i-16+3),20,20,this);
							
						} catch (IOException e) {
							
							e.printStackTrace();
						}

				 }
			 }
			 
			 
			 /*
		
			 g2.drawString(""+rankingWorldCup.getRanking().getRankingTeams()[0].getName(), x+50, y+90);
			 g2.drawString(""+group.getRanking().getRankingTeams()[1].getName(), x+50, y+120);
			 g2.drawString(""+group.getRanking().getRankingTeams()[2].getName(), x+50, y+150);
			 g2.drawString(""+group.getRanking().getRankingTeams()[3].getName(), x+50, y+180);
			 
			 g2.drawString(""+rankingGroupStage.getScoreTeams().get(group.getRanking().getRankingTeams()[0].getName()), x+250, y+90);
			 g2.drawString(""+rankingGroupStage.getScoreTeams().get(group.getRanking().getRankingTeams()[1].getName()), x+250, y+120);
			 g2.drawString(""+rankingGroupStage.getScoreTeams().get(group.getRanking().getRankingTeams()[2].getName()), x+250, y+150);
			 g2.drawString(""+rankingGroupStage.getScoreTeams().get(group.getRanking().getRankingTeams()[3].getName()), x+250, y+180);

	*/
		/*	 g2.drawLine(x+0, y+40, x+280, y+40);
			 g2.drawLine(x+0, y+70, x+280, y+70);
			 g2.drawLine(x+0, y+100, x+280, y+100);
			 g2.drawLine(x+0, y+130, x+280, y+130);
			 g2.drawLine(x+0, y+160, x+280, y+160);
			 g2.drawLine(x+0, y+190, x+280, y+190);
			 g2.drawLine(x+0, y+220, x+280, y+220);
			 g2.drawLine(x+0, y+250, x+280, y+250);
			 g2.drawLine(x+0, y+280, x+280, y+280);
			 g2.drawLine(x+0, y+310, x+280, y+310);
			 g2.drawLine(x+0, y+340, x+280, y+340);
			 g2.drawLine(x+0, y+370, x+280, y+370);
			 g2.drawLine(x+0, y+400, x+280, y+400);
			 g2.drawLine(x+0, y+430, x+280, y+430);
			 g2.drawLine(x+0, y+460, x+280, y+460);
			 g2.drawLine(x+0, y+490, x+280, y+490);
			 g2.drawLine(x+0, y+520, x+280, y+520);
			 g2.drawLine(x+0, y+550, x+280, y+550);*/


			 
		}
	}
	

}