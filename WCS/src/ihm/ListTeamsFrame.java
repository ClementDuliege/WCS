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
import ihm.TeamFrame.Back;

public class ListTeamsFrame extends JFrame {
	protected JFrame windows;
	private WorldCup worldCup;
	private Container contentPane;
	private JButton tabButton[]= new JButton[32];
	private JLabel teamsLabel;
	private JButton backButton=new JButton("Back");
	public ListTeamsFrame(String windowsTitle, WorldCup worldCup) {
		super(windowsTitle);
		setSize(900,600);
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
		
		teamsLabel= new JLabel("Equipes");
		teamsLabel.setFont(new Font("Serif", Font.BOLD,30));
		teamsLabel.setBounds(300, 15, 275, 75);
		panel.add(teamsLabel);
		
		backButton.setBounds(750, 500, 70, 40);
		backButton.addActionListener(new Back());
		panel.add(backButton);
		
		ArrayList<Team> teams=worldCup.getTeams();
		int x = 70;
		int y= 100;
		int k= 0;
		for(int i =0 ; i<4 ; i++) {
			for(int j=0; j<8; j++) {
				tabButton[k] = new JButton(teams.get(k).getName());
				tabButton[k].setBounds(x, y, 180, 30);
				tabButton[k].setActionCommand(Integer.toString(k));
				tabButton[k].addActionListener(new ListTeamAction());
				panel.add(tabButton[k]);
				
				y+=50;
				k++;
			}
			y=100;
			x+=200;
		}
		contentPane.add(panel);
		
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
