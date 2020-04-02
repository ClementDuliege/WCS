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
* This class is used to display the results of the second phase of the World Cup.
*@author WCS
*/ 
public class Phase2Frame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private WorldCup worldCup;
	private JFrame windows;
	private Container contentPane;
	private JPanel panel = new JPanel();
	private JButton phase1Button=new JButton("Phase 1");
	private JButton phase2Button=new JButton("Phase 2");
	private JButton phaseFinalButton=new JButton("Phase Finale");
	private JButton teamsButton=new JButton("Equipes");
	private JButton details = new JButton("Détails");
	
	public Phase2Frame(WorldCup worldCup, String windowsTitle) {
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
		
		details = new JButton("Détails");
		details.setBounds(530,550,200,35);
		details.setForeground(Color.black);
		details.setFont(new Font("Georgia", Font.BOLD, 15));
		details.setBackground(new Color(135,206,250));
		
		phase1Button.setBounds(150, 50, 200, 25);
		phase2Button.setBounds(400, 50, 200, 25);
		phaseFinalButton.setBounds(650, 50, 200, 25);
		teamsButton.setBounds(900, 50, 200, 25);
		
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
		String listGroup[]= {"GROUPE I","GROUPE J","GROUPE K","GROUPE L"};

		for(int i = 0 ;i<4;i++) {
			displayGameGroup(listGroup[i],i);
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
		JLabel groupI=new JLabel("GROUPE I",SwingConstants.CENTER);
		groupI.setBounds(50, 100, 250, 30);
		groupI.setOpaque(true);
		groupI.setFont(font);
		groupI.setForeground(Color.WHITE);
		groupI.setBackground(new Color(225,82,11));
		 
		JLabel groupJ=new JLabel("GROUPE J",SwingConstants.CENTER);
		groupJ.setBounds(350, 100, 250, 30);
		groupJ.setOpaque(true);
		groupJ.setFont(font);
		groupJ.setForeground(Color.WHITE);
		groupJ.setBackground(new Color(225,82,11));
		
		JLabel groupK=new JLabel("GROUPE K",SwingConstants.CENTER);
		groupK.setBounds(650, 100, 250, 30);
		groupK.setOpaque(true);
		groupK.setFont(font);
		groupK.setForeground(Color.WHITE);
		groupK.setBackground(new Color(225,82,11));
		
		JLabel groupL=new JLabel("GROUPE L",SwingConstants.CENTER);
		groupL.setBounds(950, 100, 250, 30);
		groupL.setOpaque(true);
		groupL.setFont(font);
		groupL.setForeground(Color.WHITE);
		groupL.setBackground(new Color(225,82,11));
		
		//Add the labels to the panel
		panel.add(groupI);
		panel.add(groupJ);
		panel.add(groupK);
		panel.add(groupL);
	}
	
	
	
	/**
	 * This method is used to display the groups's games
	 * @param group Name of the group
	 * @param j Integer used to find the display position 
	 */
	public void displayGameGroup(String group, int j) {
		ArrayList<Game> listGameGroup2 = worldCup.getGroupStage2().get(group).getGames();
		
		for(int k = 0; k<listGameGroup2.size();k++) {
			Game game = listGameGroup2.get(k);
			JPanel panelScore = new ScorePanel(game);
			panelScore.setBounds(50+(j*300) , 150+(50*k), 250, 43);
			addActionToPanelScore(panelScore, game);
			JLabel nameTeam1Label=new JLabel(game.getTeam1().getName(),SwingConstants.CENTER);
			nameTeam1Label.setBounds(50+(j*300)+5, 150+(50*k)+15, 73, 13);
			nameTeam1Label.setFont(new Font(Font.DIALOG, Font.BOLD, 13));
			panel.add(nameTeam1Label);
			JLabel nameTeam2Label=new JLabel(game.getTeam2().getName(),SwingConstants.CENTER);
			nameTeam2Label.setBounds(50+(j*300)+173, 150+(50*k)+15, 73, 13);
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
		phase1Button.addActionListener(new Phase1());
		phaseFinalButton.addActionListener(new PhaseFinale());
		teamsButton.addActionListener(new Teams());
		details.addActionListener(new Details());
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
	         new RankingPhase2Frame(worldCup,"Rangink Group 2");
			
		}
	}
	
}
