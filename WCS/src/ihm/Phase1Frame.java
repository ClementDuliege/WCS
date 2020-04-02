package ihm;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import data.Game;
import data.WorldCup;



/**
* This class is used to display the results of the first phase of the World Cup.
*@author WCS
*/ 
public class Phase1Frame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private WorldCup worldCup;
	private JFrame windows;
	private Container contentPane;
	private JPanel panel = new JPanel();
	private JButton phase1Button=new JButton("Phase 1");
	private JButton phase2Button=new JButton("Phase 2");
	private JButton phaseFinalButton=new JButton("Phase Finale");
	private JButton teamsButton=new JButton("Equipes");
	private JButton  details = new JButton("Détails");
	
	public Phase1Frame(WorldCup worldCup, String windowsTitle) {
		super(windowsTitle);
		setSize(1300,900);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setAlwaysOnTop(true);
		setVisible(true);
		
		this.worldCup=worldCup;
		this.contentPane=getContentPane();
		this.windows=this;
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
		
		details.setBounds(530,819,200,35);
		details.setForeground(Color.black);
		details.setFont(new Font("Georgia", Font.BOLD, 15));
		details.setBackground(new Color(135,206,250));
		
		panel.add(phase1Button);
		panel.add(phase2Button);
		panel.add(phaseFinalButton);
		panel.add(teamsButton);
		
		panel.add(details);
		//Init action to buttons
		initAction();
		//Display of the groups's games
		displayNameGroupsLabel();
		//Display of the groups's games
		String listGroup[]= {"GROUPE A","GROUPE B","GROUPE C","GROUPE D","GROUPE E","GROUPE F","GROUPE G","GROUPE H"};
		int k=0;
		for(int i = 0 ;i<2;i++) {
			for(int j = 0;j<4;j++) {
				displayGameGroup(listGroup[k], i,j);
				k++;
			}
		}
		contentPane.add(panel);
	}
	

	
	/**
	 * This method is used to create labels to write de name of the groups.
	 */
	public void displayNameGroupsLabel() {
		//Creation of the font
		Font font=new Font(Font.DIALOG, Font.BOLD, 15);
		//Creation of the labels
		JLabel groupA=new JLabel("GROUPE A",SwingConstants.CENTER);	
		groupA.setBounds(50, 110, 250, 30);
		groupA.setForeground(Color.WHITE);
		groupA.setOpaque(true);
		groupA.setFont(font);
		groupA.setBackground(new Color(225,82,11));
		
		JLabel groupB=new JLabel("GROUPE B",SwingConstants.CENTER);
		groupB.setBounds(350, 110, 250, 30);
		groupB.setForeground(Color.WHITE);
		groupB.setOpaque(true);
		groupB.setFont(font);
		groupB.setBackground(new Color(225,82,11));
		
		JLabel groupC=new JLabel("GROUPE C",SwingConstants.CENTER);
		groupC.setBounds(650, 110, 250, 30);
		groupC.setForeground(Color.WHITE);
		groupC.setOpaque(true);
		groupC.setFont(font);
		groupC.setBackground(new Color(225,82,11));
		
		JLabel groupD=new JLabel("GROUPE D",SwingConstants.CENTER);
		groupD.setBounds(950, 110, 250, 30);
		groupD.setForeground(Color.WHITE);
		groupD.setOpaque(true);
		groupD.setFont(font);
		groupD.setBackground(new Color(225,82,11));
		
		JLabel groupE=new JLabel("GROUPE E",SwingConstants.CENTER);
		groupE.setBounds(50, 460, 250, 30);
		groupE.setForeground(Color.WHITE);
		groupE.setOpaque(true);
		groupE.setFont(font);
		groupE.setBackground(new Color(225,82,11));
		 
		JLabel groupF=new JLabel("GROUPE F",SwingConstants.CENTER);
		groupF.setBounds(350, 460, 250, 30);
		groupF.setForeground(Color.WHITE);
		groupF.setOpaque(true);
		groupF.setFont(font);
		groupF.setBackground(new Color(225,82,11));
		
		JLabel groupG=new JLabel("GROUPE G",SwingConstants.CENTER);
		groupG.setBounds(650, 460, 250, 30);
		groupG.setForeground(Color.WHITE);
		groupG.setOpaque(true);
		groupG.setFont(font);
		groupG.setBackground(new Color(225,82,11));
		
		JLabel groupH=new JLabel("GROUPE H",SwingConstants.CENTER);
		groupH.setBounds(950, 460, 250, 30);
		groupH.setForeground(Color.WHITE);
		groupH.setOpaque(true);
		groupH.setFont(font);
		groupH.setBackground(new Color(225,82,11));
		
		//Add the labels to the panel
		panel.add(groupA);
		panel.add(groupB);
		panel.add(groupC);
		panel.add(groupD);
		panel.add(groupE);
		panel.add(groupF);
		panel.add(groupG);
		panel.add(groupH);	
	}
	
	
	
	/**
	 * This method is used to display the groups's games
	 * @param group Name of the group
	 * @param i Integer used to find the display position 
	 * @param j Integer used to find the display position 
	 */
	public void displayGameGroup(String group,int i, int j) {
		ArrayList<Game> listGameGroupA = worldCup.getGroupStage1().get(group).getGames();
		
		for(int k = 0; k<listGameGroupA.size();k++) {
			Game game = listGameGroupA.get(k);
			//Panel to display image
			JPanel panelScore = new ScorePanel(game);
			panelScore.setBounds(50+(j*300) , 150+(i*350)+(50*k), 250, 43);
			addActionToPanelScore(panelScore, game);
			//Label to display the name of team1
			JLabel nameTeam1Label=new JLabel(game.getTeam1().getName(),SwingConstants.CENTER);
			nameTeam1Label.setBounds(50+(j*300)+5, 150+(i*350)+(50*k)+15, 73, 13);
			nameTeam1Label.setFont(new Font(Font.DIALOG, Font.BOLD, 13));
			panel.add(nameTeam1Label);
			//Label to display the name of team2
			JLabel nameTeam2Label=new JLabel(game.getTeam2().getName(),SwingConstants.CENTER);
			nameTeam2Label.setBounds(50+(j*300)+173, 150+(i*350)+(50*k)+15, 73, 13);
			nameTeam2Label.setFont(new Font(Font.DIALOG, Font.BOLD, 13));
			panel.add(nameTeam2Label);
			panel.add(panelScore);
		}
	}
	
	
	
	/**
	 * This method is used to add action to the scores panels.
	 * @param panelScore JPanel
	 * @param game Game
	 */
	public void addActionToPanelScore(JPanel panelScore,Game game) {
		panelScore.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
	           setCursor(handCursor);
	           
	         }
			public void mousePressed(MouseEvent e) {
				 windows.dispose();
		         new Thread(new GameFrame("Game",worldCup,game)).start();
			}
			 public void mouseExited(MouseEvent e) {
				 Cursor defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);
				 setCursor(defaultCursor);
			 }
			
		});
	}
	
	
	
	/**
	 * This method is used to initialise actions to the buttons
	 */
	public void initAction() {
		phase2Button.addActionListener(new Phase2());
		phaseFinalButton.addActionListener(new PhaseFinale());
		teamsButton.addActionListener(new Teams());
		details.addActionListener(new Details());
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
	 * ActionListener action of the details button
	 *
	 */
	public class Details implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			windows.dispose();
	         new RankingPhase1Frame(worldCup,"Rangink Group 1");
		}
	}
}
