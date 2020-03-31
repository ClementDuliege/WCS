package ihm;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
 
import data.Game;

public class ScorePanel extends JPanel{
	/**
	 *  
	 */
	private static final long serialVersionUID = 1L;
	private Game ga;
	public ScorePanel(Game g) {
		
		this.ga=g;
		
	}
	 public void paint(Graphics g) {  
		  
		 Graphics2D g2 = (Graphics2D)g;
		 try {
			Image icone2 =  ImageIO.read(new File("score.jpg")); // "score.jpg"
			g2.drawImage(icone2,0,0,250,43,this);
					
		}catch(IOException exc){
				exc.printStackTrace();
		}
		 g2.setFont(new Font("TimesRoman", Font.BOLD, 15)); 
		 //g2.setColor(Color.blue);
		 g2.setColor(Color.black);
		// g2.drawString(ga.getTeam1().getName(), 5, 25);
		// g2.drawString(ga.getTeam2().getName(), 170, 25);
		 g2.setColor(Color.white);
		 if(ga.getScore1()<ga.getScore2()) {
			 g2.setColor(new Color(210,20,0));
		 }
		 else {
			 g2.setColor(new Color(30,130,60));
		 }
		 g2.drawString(""+ga.getScore1(), 78, 25);
		 if(ga.getScore1()>ga.getScore2()) {
			 g2.setColor(new Color(210,20,0));
		 }
		 else {
			 g2.setColor(new Color(30,130,60));
		 }
		 g2.drawString(""+ga.getScore2(), 150, 25);
		 
	 }
}