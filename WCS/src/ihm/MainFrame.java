package ihm;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.Team;
import data.WorldCup;

public class MainFrame extends JFrame {
	protected JFrame windows;
	public Container contentPane;
	protected JLabel titleJLabel;
	private JButton informationTeamButton;
	private JButton lotteryButton;
	protected WorldCup worldCup;
	
	public MainFrame(String titleWindows,WorldCup worldCup) {
		super(titleWindows);
		setSize(900,600);
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
		lotteryButton=new JButton("Tirage au sort");
		
		initLayoutMain();
		initActionMain();
		
		this.worldCup=worldCup;
		
		
	}
	
	public void initLayoutMain() {
		GridLayout grid = new GridLayout(1,1);
		contentPane.setLayout(grid);
		
		JPanel panel=new JPanel();
		panel.setLayout(null);
		
		titleJLabel.setBounds(310, 30, 275, 75);
		panel.add(titleJLabel);
		
		informationTeamButton.setBounds(325, 150, 250, 75);
		panel.add(informationTeamButton);
		
		lotteryButton.setBounds(325, 250, 250, 75);
		panel.add(lotteryButton);
		
		contentPane.add(panel);
		
	}
	
	public void initLayoutListTeams() {
		GridLayout grid = new GridLayout(1,1);
		contentPane.setLayout(grid);
		
		JPanel panel=new JPanel();
		panel.setLayout(null);
		
		ArrayList<Team> teams = worldCup.getTeams();
		
		String test="";
		for(Team t :teams) {
			test+=t.getName()+"\n";
		}
		JLabel j=new JLabel(test);
		contentPane.add(j);
	
	}
	
	public void initActionMain() {
		informationTeamButton.addActionListener(new ListTeamAction());
	}
	
	public class ListTeamAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			windows.dispose();
			new ListTeamsFrame("listTeams", worldCup);
			
			
			
		}
	}
	



}
