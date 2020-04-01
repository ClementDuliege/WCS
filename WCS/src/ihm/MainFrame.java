package ihm;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.sun.prism.Image;

import data.WorldCup;

/**
 * This class is the main windows of the World Cup Simulator
 * @author WCS
 *
 */
public class MainFrame extends JFrame {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	protected JFrame windows;
	public Container contentPane;
	protected JLabel titleJLabel;
	private JButton simulationStartButton;  
	protected WorldCup worldCup;
	private JPanel panel;
	private Font freshman;
	
	
	public MainFrame(String titleWindows,WorldCup worldCup) {
		super(titleWindows);
		setSize(900,480);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setAlwaysOnTop(true);
		setVisible(true);
		
		this.windows=this;
		this.contentPane=getContentPane();
		this.titleJLabel=new JLabel("World Cup Simulator",SwingConstants.CENTER);
		this.titleJLabel.setFont(new Font("Serif", Font.BOLD,30));
		this.simulationStartButton=new JButton("Simulation de la compétition");
		this.panel=new JPanel();
		this.worldCup=worldCup;
		//Init the Font
		font();
		//Init the layout
		initLayoutMain(); 
		
	}
	
	/**
	 * This method is used to implement the extern police
	 */
	public void font() {
		try {
			freshman = Font.createFont(Font.TRUETYPE_FONT, new File("Freshman.ttf")).deriveFont(50f);
			
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * This method is used to initialise the layout and place the menu, etc...
	 */
	public void initLayoutMain() {
		GridLayout grid = new GridLayout(1,1);
		contentPane.setLayout(grid);
		
		panel.setLayout(null);
		panel.setBackground(Color.white);
		
		titleJLabel.setFont(freshman);
		titleJLabel.setBounds(112, 30, 675, 75);
		titleJLabel.setForeground(new Color(225,82,11));
		
		simulationStartButton.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		simulationStartButton.setBounds(325, 190, 250, 85);
		simulationStartButton.setBackground(new Color(225,82,11));
		simulationStartButton.setForeground(Color.white);
		
		panel.add(simulationStartButton);
		panel.add(titleJLabel);
		
		contentPane.add(panel);
		
		initActionMain();
		
	}
	
	

	/**
	 * This method is used to initialise actions to the buttons
	 */
	public void initActionMain() {
		simulationStartButton.addActionListener(new SimulationStart());
	}
	
	
	
	/**
	 * ActionListener action of the start button
	 *
	 */
	public class SimulationStart implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			windows.dispose();
			new Phase1Frame(worldCup, "Phase1");
		}
	}
	



}
