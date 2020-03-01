package ihm;

import java.awt.Color;
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

import data.Player;
import data.Team;
import data.WorldCup;

public class TeamFrame extends JFrame {
	protected JFrame windows;
	private WorldCup worldCup;
	private Container contentPane;
	private JButton tabButton[]= new JButton[32];
	private JLabel teamsLabel;
	private JButton backButton=new JButton("Back");
	private int indexTeam;
	
	public TeamFrame(String windowsTitle, WorldCup worldCup, int indexTeam) {
		super(windowsTitle);
		setSize(900,600);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setAlwaysOnTop(true);
		setVisible(true);
		windows=this;
		this.worldCup=worldCup;
		contentPane=getContentPane();
		contentPane.setBackground(Color.YELLOW);
		this.indexTeam=indexTeam;
		
		initLayout();
		
		
		
	}
	
	public void initLayout() {
		
		
		
		GridLayout grid = new GridLayout(1,1);
		contentPane.setLayout(grid);
		
		JPanel panel=new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		
		teamsLabel=new JLabel(writePlayerName());
		teamsLabel.setFont(new Font("Serif", Font.BOLD,10));
		teamsLabel.setBounds(300, 15, 400, 500);
		backButton.setBounds(750, 500, 70, 40);
		backButton.addActionListener(new Back());
		panel.add(backButton);
		panel.add(teamsLabel);
		
		contentPane.add(panel);
		
		
	}
	
	
	public String writePlayerName() {
		ArrayList<Team> teams=worldCup.getTeams();
		ArrayList<Player>  players = teams.get(indexTeam).getPlayers();

		String res="<html>";
		
		for(Player p : players) {
			res+="<p>"+p.getName()+"</p>";
		}
		
		res+="</html>";
		return res;
	}
	
	public class Back implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			windows.dispose();
			new ListTeamsFrame("listTeams", worldCup);
			
		   
			
		}
	}
	
	

	
	

}
