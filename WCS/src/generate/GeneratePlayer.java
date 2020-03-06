package generate;

import java.util.Random;
import usual.*;
import data.Player;

public class GeneratePlayer {
	private Player playerCreated;
	
	
	public GeneratePlayer() {
	
	}
	 
	public Player generatePlayer(String name) {
		
		this.playerCreated=new Player(name);
		 
		this.playerCreated.setAttack(generateValueAttack());
		this.playerCreated.setShot2Pts(generateValueAttack());
		this.playerCreated.setShot3Pts(generateValueAttack());
		this.playerCreated.setPass(generateValueAttack());
		this.playerCreated.setBallHandle(generateValueAttack());
		this.playerCreated.setSpeed(generateValueAttack());
		this.playerCreated.setFreeThrows(generateValueAttack());
		
		this.playerCreated.setReboundOff(generateValueDefense());
		this.playerCreated.setBlock(generateValueDefense());
		this.playerCreated.setDefense(generateValueDefense());
		this.playerCreated.setSteal(MyRandom.getIntIntoMinMax(0, 10));
		this.playerCreated.setReboundDef(generateValueDefense());
		this.playerCreated.setStrenght(generateValueDefense());
		this.playerCreated.setReactionTime(generateValueDefense());		
		
				
		return playerCreated;
	}
	
	public int generateValueAttack() {
		Random random = new Random();
		int valueMin=50;
		int valueMax=100;
		int value = valueMin + random.nextInt(valueMax - valueMin);
		return value;
	}
	
	
	public int generateValueDefense() {
		Random random = new Random();
		int valueMin=0;
		int valueMax=50;
		int value = valueMin + random.nextInt(valueMax - valueMin);
		return value;
	}
	
	
	
	
	

}
