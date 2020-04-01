package ihm;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import data.Game;
import data.WorldCup;

public class TableauPhaseFinal extends JPanel{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private WorldCup worldCup;
	private Game[] quarterFinal;
	private Game[] semiFinal;
	private Game finalGame;
	private Game smallFinalGame;
	private JFrame windows;
	public TableauPhaseFinal(WorldCup worldCup, JFrame windows) {
		this.worldCup=worldCup;
		quarterFinal=worldCup.getQuarterFinal();
		semiFinal=worldCup.getSemiFinal();
		finalGame=worldCup.getFinalGame();
		smallFinalGame=worldCup.getSmallFinalGame();
		this.windows=windows;
	}
	 public void paint(Graphics g) {  
		  
		 Graphics2D g2 = (Graphics2D)g;
		 Image icone2;
		 
		 g2.setStroke(new BasicStroke(10));
		 g2.setColor(new Color(225,82,11));
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
		 
		 g2.drawLine(350, 222, 400, 222);
		 g2.drawLine(650, 222, 710, 222);
		 
		 int k=0;
		 
		
		 for(int i = 0;i<2;i++) {
			 for(int j = 0 ;j<2;j++) {
				 Game quarterGame=quarterFinal[k];
				 
				 JPanel panelQuarter=new ScorePanel(quarterGame);
				 panelQuarter.setBounds(0 +(j*800),0+(i*400),250,43);
				 addActionToPanelScore(panelQuarter,quarterGame);
				 add(panelQuarter);
				 try {
						icone2 = ImageIO.read(new File("score.jpg")); // "score.jpg"
						g2.drawImage(icone2,0 +(j*800),0+(i*400),250,43,this);
								
					}catch(IOException exc){
							exc.printStackTrace();
					}
				 g2.setFont(new Font(Font.DIALOG, Font.BOLD, 13)); 
				
				 if(quarterGame.getScore1()<quarterGame.getScore2()) {
					 g2.setColor(new Color(210,20,0));
				 }
				 else {
					 g2.setColor(new Color(30,130,60));
				 }
				// g2.drawString(""+quarterGame.getScore1(), 78, 25);
				 g2.drawString(""+quarterGame.getScore1(), 83+(j*800), 25+(i*400));
				 if(quarterGame.getScore1()>quarterGame.getScore2()) {
					 g2.setColor(new Color(210,20,0));
				 }
				 else {
					 g2.setColor(new Color(30,130,60));
				 }
				 g2.drawString(""+quarterGame.getScore2(), 150+(j*800), 25+(i*400));
				 k++;
			 }
		 }
		 
		 for(int i2= 0;i2<2;i2++) {
			 Game semiGame=semiFinal[i2];
			 JPanel panelSemi=new ScorePanel(semiGame);
			 panelSemi.setBounds(100 +(i2*610),200,250,43);
			 addActionToPanelScore(panelSemi,semiGame);
			 add(panelSemi);
			 try {
					icone2 =  ImageIO.read(new File("score.jpg")); // "score.jpg"
					g2.drawImage(icone2,100 +(i2*610),200,250,43,this);
							
			}catch(IOException exc){
						exc.printStackTrace();
			}
			 g2.setFont(new Font("TimesRoman", Font.BOLD, 15)); 
			 if(semiGame.getScore1()<semiGame.getScore2()) {
				 g2.setColor(new Color(210,20,0));
			 }
			 else {
				 g2.setColor(new Color(30,130,60));
			 }
			// g2.drawString(""+quarterGame.getScore1(), 78, 25);
			 g2.drawString(""+semiGame.getScore1(), 183+(i2*610), 225);
			 if(semiGame.getScore1()>semiGame.getScore2()) {
				 g2.setColor(new Color(210,20,0));
			 }
			 else {
				 g2.setColor(new Color(30,130,60));
			 }
			 g2.drawString(""+semiGame.getScore2(), 250+(i2*610), 225);
			// g2.drawString(""+semiGame.getScore1(), 183+(i2*610), 225);
			// g2.drawString(""+semiGame.getScore2(), 250+(i2*610), 225);
			 k++;
			 
		 }
		 
		 
		 try {
				icone2 =  ImageIO.read(new File("score.jpg")); // "score.jpg"
				g2.drawImage(icone2,400,200,250,43,this);
						
		}catch(IOException exc){
					exc.printStackTrace();
		}
		 
		 JPanel panelFinal=new ScorePanel(finalGame);
		 panelFinal.setBounds(400,200,250,43);
		 addActionToPanelScore(panelFinal,finalGame);
		 add(panelFinal);
		 
		 g2.setFont(new Font("TimesRoman", Font.BOLD, 15)); 
		 
		 if(finalGame.getScore1()<finalGame.getScore2()) {
			 g2.setColor(new Color(210,20,0));
		 }
		 else {
			 g2.setColor(new Color(30,130,60));
		 }
		// g2.drawString(""+quarterGame.getScore1(), 78, 25);
		 g2.drawString(""+finalGame.getScore1(), 483, 225);
		 if(finalGame.getScore1()>finalGame.getScore2()) {
			 g2.setColor(new Color(210,20,0));
		 }
		 else {
			 g2.setColor(new Color(30,130,60));
		 }
		 g2.drawString(""+finalGame.getScore2(), 550, 225);
		 
		// g2.drawString(""+finalGame.getScore1(), 483, 225);
		// g2.drawString(""+finalGame.getScore2(), 550, 225);
		 
		 
		 
		 try {
				icone2 =  ImageIO.read(new File("score.jpg")); // "score.jpg"
				g2.drawImage(icone2,400,300,250,43,this);
						
		}catch(IOException exc){
					exc.printStackTrace();
		}
		 
		 JPanel panelSmallFinal=new ScorePanel(smallFinalGame);
		 panelSmallFinal.setBounds(400,300,250,43);
		 addActionToPanelScore(panelSmallFinal,smallFinalGame);
		 add(panelSmallFinal);
		 
		 g2.setFont(new Font(Font.DIALOG, Font.BOLD, 13));
		 
		 if(smallFinalGame.getScore1()<smallFinalGame.getScore2()) {
			 g2.setColor(new Color(210,20,0));
		 }
		 else {
			 g2.setColor(new Color(30,130,60));
		 }
		// g2.drawString(""+quarterGame.getScore1(), 78, 25);
		 g2.drawString(""+smallFinalGame.getScore1(), 483, 325);
		 if(smallFinalGame.getScore1()>smallFinalGame.getScore2()) {
			 g2.setColor(new Color(210,20,0));
		 }
		 else {
			 g2.setColor(new Color(30,130,60));
		 }
		 g2.drawString(""+smallFinalGame.getScore2(), 550, 325);
		 
		 
		 //g2.draw
		 
		try {
				icone2 = ImageIO.read(new File("trophee.png")); // "score.jpg"
				g2.drawImage(icone2,360,350,320,320,this);
						
		}catch(IOException exc){
					exc.printStackTrace();
		}
		 g2.setColor(Color.black);
		

		 
		
	 }
	 
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
	
	 
	
}