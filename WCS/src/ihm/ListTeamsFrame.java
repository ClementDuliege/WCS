package ihm;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.Team;
import data.WorldCup;


public class ListTeamsFrame extends JFrame {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	protected JFrame windows;
	private WorldCup worldCup;
	private Container contentPane;
	private JButton tabButton[]= new JButton[32];
	private JLabel teamsLabel;
	private JButton phase1Button=new JButton("Phase 1");
	private JButton phase2Button=new JButton("Phase 2");
	private JButton phaseFinalButton=new JButton("Phase Finale");
	private JButton teamsButton=new JButton("Equipes");
	public ListTeamsFrame(String windowsTitle, WorldCup worldCup) {
		super(windowsTitle);
		setSize(1300,850);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setAlwaysOnTop(true);
		setVisible(true);
		this.worldCup=worldCup;
		contentPane=getContentPane();
		creationButton();
		windows=this;
		
		
	}
	
	public void creationButton() {
		
		GridLayout grid = new GridLayout(1,1);
		contentPane.setLayout(grid);
		
		JPanel panel=new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		 
		teamsLabel= new JLabel("Equipes");
		teamsLabel.setFont(new Font("Serif", Font.BOLD,30));
		teamsLabel.setBounds(150, 115, 275, 75);
		panel.add(teamsLabel);
		
		phase1Button.setBounds(150, 50, 200, 25);
		phase2Button.setBounds(400, 50, 200, 25);
		phaseFinalButton.setBounds(650, 50, 200, 25);
		teamsButton.setBounds(900, 50, 200, 25);
		
		panel.add(phase1Button);
		panel.add(phase2Button);
		panel.add(phaseFinalButton);
		panel.add(teamsButton);
		
		initAction();
		
		
		ArrayList<Team> teams=worldCup.getTeams();
		int x = 150;
		int y= 190;
		int k= 0;
		for(int i =0 ; i<4 ; i++) {
			for(int j=0; j<8; j++) {
				JLabel drapeauLabel =new JLabel(new ImageIcon("Drapeau/"+teams.get(k).getName()+".png"));
				drapeauLabel.setBounds(x-60,y,60,36);
				tabButton[k] = new JButton(teams.get(k).getName());
				tabButton[k].setBounds(x, y, 150, 30);
				tabButton[k].setBackground(new Color(225,82,11));
				tabButton[k].setForeground(Color.white);
				tabButton[k].setActionCommand(Integer.toString(k));
				tabButton[k].addActionListener(new ListTeamAction());
				panel.add(tabButton[k]);
				panel.add(drapeauLabel);
				
				y+=70;
				k++;
			}
			y=190;
			x+=250;
		}
		contentPane.add(panel);
		
	}
	
	public void initAction() {
		phase1Button.addActionListener(new Phase1());
		phaseFinalButton.addActionListener(new PhaseFinale());
		phase2Button.addActionListener(new Phase2());
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
	
	public class ListTeamAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			ArrayList<Team> teams=worldCup.getTeams();
			
			String command = ((JButton) e.getSource()).getActionCommand();
			int i = Integer.parseInt(command);
			
			windows.dispose();
			new TeamFrame(teams.get(i).getName(), worldCup, i);
		   
			
		}
	}
	
	public class Back implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			windows.dispose();
			JFrame main = new MainFrame("WCS",worldCup);
			
		   
	
		}
	}
	
	

	
	

}
