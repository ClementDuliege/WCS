package Data;

import java.util.HashMap;

public class Team {
	
	private String name;
	private HashMap<String,Player> players;
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
	
	
	public Team(String name, HashMap<String, Player> players) {
		this.name = name;
		this.players = players;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public HashMap<String, Player> getPlayers() {
		return players;
	}


	public void setPlayers(HashMap<String, Player> players) {
		this.players = players;
	}


	public int getAttack() {
		return attack;
	}


	public void setAttack(int attack) {
		this.attack = attack;
	}


	public int getShot2Pts() {
		return shot2Pts;
	}


	public void setShot2Pts(int shot2Pts) {
		this.shot2Pts = shot2Pts;
	}


	public int getShot3Pts() {
		return shot3Pts;
	}


	public void setShot3Pts(int shot3Pts) {
		this.shot3Pts = shot3Pts;
	}


	public int getFreeThrows() {
		return freeThrows;
	}


	public void setFreeThrows(int freeThrows) {
		this.freeThrows = freeThrows;
	}


	public int getPass() {
		return pass;
	}


	public void setPass(int pass) {
		this.pass = pass;
	}


	public int getBallHandle() {
		return ballHandle;
	}


	public void setBallHandle(int ballHandle) {
		this.ballHandle = ballHandle;
	}


	public int getReboundOff() {
		return reboundOff;
	}


	public void setReboundOff(int reboundOff) {
		this.reboundOff = reboundOff;
	}


	public int getDefense() {
		return defense;
	}


	public void setDefense(int defense) {
		this.defense = defense;
	}


	public int getBlock() {
		return block;
	}


	public void setBlock(int block) {
		this.block = block;
	}


	public int getSteal() {
		return steal;
	}


	public void setSteal(int steal) {
		this.steal = steal;
	}


	public int getReboundDef() {
		return reboundDef;
	}


	public void setReboundDef(int reboundDef) {
		this.reboundDef = reboundDef;
	}


	public int getStrenght() {
		return strenght;
	}


	public void setStrenght(int strenght) {
		this.strenght = strenght;
	}


	public int getReactionTime() {
		return reactionTime;
	}


	public void setReactionTime(int reactionTime) {
		this.reactionTime = reactionTime;
	}


	public int getSpeed() {
		return speed;
	}


	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	
	
	
	

}
