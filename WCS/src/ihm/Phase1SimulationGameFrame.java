package ihm;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import data.WorldCup;


public class Phase1SimulationGameFrame extends JFrame{
	private WorldCup worldCup;
	private JFrame windows;
	private Container contentPane;
	private JPanel panel = new JPanel();
	private JButton phase1Button=new JButton("Phase 1");
	private JButton phase2Button=new JButton("Phase 2");
	private JButton phaseFinalButton=new JButton("Phase Finale");
	private JButton teamsButton=new JButton("Equipes");
	
	public Phase1SimulationGameFrame(String titleWindows,WorldCup worldCup) {
		super(titleWindows);
		setSize(1300,850);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setAlwaysOnTop(true);
		setVisible(true);
		this.windows=this;
		this.contentPane=getContentPane();
		this.worldCup=worldCup;
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
		phase2Button.addActionListener(new Phase2SimulationGame());
		phaseFinalButton.addActionListener(new PhaseFinaleSimulationGames());
		teamsButton.addActionListener(new Teams());
	}
	
	public class Phase2SimulationGame implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			windows.dispose();
			new Phase2SimulationGameFrame("Phase 2", worldCup);
			
			
		   
			
		}
	}
	
	public class PhaseFinaleSimulationGames implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			windows.dispose();
			new PhaseFinalSimulationGamesFrame(worldCup, "Final Phase");
			
		   
			
		}
	}
	
	public class Teams implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			windows.dispose();
			new ListTeamsFrame("TEAMS",worldCup);
			
		   
			
		}
	}
		
}
