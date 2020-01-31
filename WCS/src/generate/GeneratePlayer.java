package generate;

import java.util.Random;

import data.Player;

public class GeneratePlayer {
	private Player playerCreated;
	private String name;
	private int attack;
	private int shot2Pts;
	private int shot3Pts;
	private int freeThrows;
	private int pass;
	private int ballHandle;
	private int reboundOff;
	private int defense;
	private int block;
	private int steal;
	private int reboundDef;
	private int strenght;
	private int reactionTime;
	private int speed;
	
	
	public GeneratePlayer() {
	
	}
	
	public Player generatePlayer(String name) {
		this.name=name;
		this.attack=generateValueAttack();
		this.shot2Pts=generateValueAttack();
		this.shot3Pts=generateValueAttack();
		this.freeThrows=generateValueAttack();
		this.pass=generateValueAttack();
		this.ballHandle=generateValueAttack();
		this.reboundOff=generateValueDefense();
		this.defense=generateValueDefense();
		this.block=generateValueDefense();
		this.steal=generateValueDefense();
		this.reboundDef=generateValueDefense();
		this.strenght=generateValueDefense();
		this.reactionTime=generateValueAttack();
		this.speed=generateValueAttack();
		
		this.playerCreated=new Player(this.name,attack,shot2Pts,shot3Pts,freeThrows,pass,ballHandle,reboundOff,defense,block,steal,reboundDef,strenght
				,reactionTime,speed);
		playerCreated.setForm(generateValueAttack());
		
		return playerCreated;
	}
	
	public int generateValueAttack() {
		Random random = new Random();
		int valueMin=90;
		int valueMax=100;
		int value = valueMin + random.nextInt(valueMax - valueMin);
		return value;
	}
	
	
	public int generateValueDefense() {
		Random random = new Random();
		int valueMin=40;
		int valueMax=100;
		int value = valueMin + random.nextInt(valueMax - valueMin);
		return value;
	}
	
	
	
	
	

}
