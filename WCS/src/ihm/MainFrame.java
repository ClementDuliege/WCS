package ihm;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.WorldCup;

public class MainFrame extends JFrame {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	protected JFrame windows;
	public Container contentPane;
	protected JLabel titleJLabel;
	private JButton informationTeamButton;
	private JButton simulationStartButton;
	protected WorldCup worldCup;
	
	public MainFrame(String titleWindows,WorldCup worldCup) {
		super(titleWindows);
		setSize(900,400);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setAlwaysOnTop(true);
		setVisible(true);
		
		windows=this;
		contentPane=getContentPane();
		
		this.titleJLabel=new JLabel("World Cup Simulator");
		titleJLabel.setFont(new Font("Serif", Font.BOLD,30));
		
		informationTeamButton=new JButton("Informations Teams");
		simulationStartButton=new JButton("Simulation");
		
		initLayoutMain();
		initActionMain();
		
		this.worldCup=worldCup;
		
		
	}
	
	public void initLayoutMain() {
		GridLayout grid = new GridLayout(1,1);
		contentPane.setLayout(grid);
		
		JPanel panel=new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.white);
		
		titleJLabel.setBounds(310, 30, 275, 75);
		titleJLabel.setForeground(new Color(225,82,11));
		
		panel.add(titleJLabel);
		
		simulationStartButton.setBounds(325, 150, 250, 75);
		simulationStartButton.setBackground(new Color(225,82,11));
		simulationStartButton.setForeground(Color.white);
		panel.add(simulationStartButton);
		
		contentPane.add(panel);
		
	}
	

	
	public void initActionMain() {
		informationTeamButton.addActionListener(new ListTeamAction());
		simulationStartButton.addActionListener(new SimulationStart());
	}
	
	public class ListTeamAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			windows.dispose();
			new ListTeamsFrame("listTeams", worldCup);
			
			
			
		}
	}
	
	public class SimulationStart implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			windows.dispose();
			new Phase1Frame(worldCup, "Phase1");
			
			
			
		}
	}
	



}
