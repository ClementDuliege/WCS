package ihm;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import data.Game;
import data.WorldCup;
import ihm.Phase2Frame.Back;
import ihm.Phase2Frame.PhaseFinal;

public class FinalPhaseFrame extends JFrame {
	private WorldCup worldCup;
	private JFrame windows;
	private Container contentPane;
	private JPanel panel = new JPanel();
	
	public FinalPhaseFrame(WorldCup worldCup, String windowsTitle) {
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
		initLayout();
	}
	
	public void initLayout() {
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		
		JPanel pane2 =new TableauPhaseFinal(worldCup);
		pane2.setBounds(50, 50, 1200, 800);
		panel.add(pane2);
		
		
		contentPane.add(panel);
	}
	
	
	
	class TableauPhaseFinal extends JPanel{
		WorldCup worldCup;
		private ArrayList<Game> quarterFinal = new ArrayList<Game>();
		private ArrayList<Game> semiFinal = new ArrayList<Game>();
		private Game finalGame;
		private Game smallFinalGame;
		public TableauPhaseFinal(WorldCup worldCup) {
			this.worldCup=worldCup;
			quarterFinal=worldCup.getQuarterFinal();
			semiFinal=worldCup.getSemiFinal();
			finalGame=worldCup.getFinalGame();
			smallFinalGame=worldCup.getSmallFinalGame();
		}
		 public void paint(Graphics g) {  
			  
			 Graphics2D g2 = (Graphics2D)g;
			 
			 Image icone2;
			 int k=0;
			 System.out.print(quarterFinal.size());
			 for(int i = 0;i<2;i++) {
				 for(int j = 0 ;j<2;j++) {
					 Game quarterGame=quarterFinal.get(k);
					 try {
							icone2 = ImageIO.read(new File("score.jpg"));
							g2.drawImage(icone2,0 +(j*800),0+(i*400),250,43,this);
									
						}catch(IOException exc){
								exc.printStackTrace();
						}
					 g2.setFont(new Font("TimesRoman", Font.BOLD, 15)); 
					 //g2.setColor(Color.blue);
					 g2.setColor(Color.black);
					 g2.drawString(quarterGame.getTeam1().getName(), 5 +(j*800), 25+(i*400));
					 g2.drawString(quarterGame.getTeam2().getName(), 170+(j*800), 25+(i*400));
					 g2.setColor(Color.white);
					 g2.drawString(""+quarterGame.getScore1(), 83+(j*800), 25+(i*400));
					 g2.drawString(""+quarterGame.getScore2(), 150+(j*800), 25+(i*400));
					 k++;
				 }
			 }
			 
			 for(int i2= 0;i2<2;i2++) {
				 Game semiGame=semiFinal.get(i2);
				 try {
						icone2 = ImageIO.read(new File("score.jpg"));
						g2.drawImage(icone2,100 +(i2*610),200,250,43,this);
								
				}catch(IOException exc){
							exc.printStackTrace();
				}
				 g2.setFont(new Font("TimesRoman", Font.BOLD, 15)); 
				 //g2.setColor(Color.blue);
				 g2.setColor(Color.black);
				 g2.drawString(semiGame.getTeam1().getName(), 105+(i2*610), 225);
				 g2.drawString(semiGame.getTeam2().getName(), 270+(i2*610), 225);
				 g2.setColor(Color.white);
				 g2.drawString(""+semiGame.getScore1(), 183+(i2*610), 225);
				 g2.drawString(""+semiGame.getScore2(), 250+(i2*610), 225);
				 k++;
				 
			 }
			 
			 
			 try {
					icone2 = ImageIO.read(new File("score.jpg"));
					g2.drawImage(icone2,400,200,250,43,this);
							
			}catch(IOException exc){
						exc.printStackTrace();
			}
			 
			 g2.setFont(new Font("TimesRoman", Font.BOLD, 15)); 
			 //g2.setColor(Color.blue);
			 g2.setColor(Color.black);
			 g2.drawString(finalGame.getTeam1().getName(), 405, 225);
			 g2.drawString(finalGame.getTeam2().getName(), 575, 225);
			 g2.setColor(Color.white);
			 g2.drawString(""+finalGame.getScore1(), 483, 225);
			 g2.drawString(""+finalGame.getScore2(), 550, 225);
			 
			 g2.setColor(Color.black);
			 g2.drawLine(125, 43, 125, 121);
			 g2.drawLine(125, 400, 125, 322);
			 g2.drawLine(925, 43, 925, 121);
			 g2.drawLine(925, 400, 925, 322);
			 
			 g2.drawLine(125, 121, 220, 121);
			 g2.drawLine(125, 322, 220, 322);
			 g2.drawLine(925, 121, 830, 121);
			 g2.drawLine(925, 322, 830, 322);
			 
			 g2.drawLine(220, 121, 220, 201);
			 g2.drawLine(830, 121, 830, 201);
			 g2.drawLine(220, 322, 220, 242);
			 g2.drawLine(830, 322, 830, 242);
			 
			 
			 
			 
			
			
		 }
	}
}
