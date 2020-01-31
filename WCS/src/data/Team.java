package data;

import java.util.ArrayList;
import java.util.HashMap;

public class Team {
	
	private String name;
	private ArrayList<Player>  players;
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
	private int form;
	
	public Team(String name, ArrayList<Player>  players) {
		this.name = name;
		this.players = players;
		getStats();
	}




	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}




	/**
	 * @return the players
	 */
	public ArrayList<Player> getPlayers() {
		return players;
	}




	/**
	 * @return the attack
	 */
	public int getAttack() {
		return attack;
	}




	/**
	 * @return the shot2Pts
	 */
	public int getShot2Pts() {
		return shot2Pts;
	}




	/**
	 * @return the shot3Pts
	 */
	public int getShot3Pts() {
		return shot3Pts;
	}




	/**
	 * @return the freeThrows
	 */
	public int getFreeThrows() {
		return freeThrows;
	}




	/**
	 * @return the pass
	 */
	public int getPass() {
		return pass;
	}




	/**
	 * @return the ballHandle
	 */
	public int getBallHandle() {
		return ballHandle;
	}




	/**
	 * @return the reboundOff
	 */
	public int getReboundOff() {
		return reboundOff;
	}




	/**
	 * @return the defense
	 */
	public int getDefense() {
		return defense;
	}




	/**
	 * @return the block
	 */
	public int getBlock() {
		return block;
	}




	/**
	 * @return the steal
	 */
	public int getSteal() {
		return steal;
	}




	/**
	 * @return the reboundDef
	 */
	public int getReboundDef() {
		return reboundDef;
	}




	/**
	 * @return the strenght
	 */
	public int getStrenght() {
		return strenght;
	}




	/**
	 * @return the reactionTime
	 */
	public int getReactionTime() {
		return reactionTime;
	}




	/**
	 * @return the speed
	 */
	public int getSpeed() {
		return speed;
	}




	/**
	 * @return the form
	 */
	public int getForm() {
		return form;
	}




	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}




	/**
	 * @param players the players to set
	 */
	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}




	/**
	 * @param attack the attack to set
	 */
	public void setAttack(int attack) {
		this.attack = attack;
	}




	/**
	 * @param shot2Pts the shot2Pts to set
	 */
	public void setShot2Pts(int shot2Pts) {
		this.shot2Pts = shot2Pts;
	}




	/**
	 * @param shot3Pts the shot3Pts to set
	 */
	public void setShot3Pts(int shot3Pts) {
		this.shot3Pts = shot3Pts;
	}




	/**
	 * @param freeThrows the freeThrows to set
	 */
	public void setFreeThrows(int freeThrows) {
		this.freeThrows = freeThrows;
	}




	/**
	 * @param pass the pass to set
	 */
	public void setPass(int pass) {
		this.pass = pass;
	}




	/**
	 * @param ballHandle the ballHandle to set
	 */
	public void setBallHandle(int ballHandle) {
		this.ballHandle = ballHandle;
	}




	/**
	 * @param reboundOff the reboundOff to set
	 */
	public void setReboundOff(int reboundOff) {
		this.reboundOff = reboundOff;
	}




	/**
	 * @param defense the defense to set
	 */
	public void setDefense(int defense) {
		this.defense = defense;
	}




	/**
	 * @param block the block to set
	 */
	public void setBlock(int block) {
		this.block = block;
	}




	/**
	 * @param steal the steal to set
	 */
	public void setSteal(int steal) {
		this.steal = steal;
	}




	/**
	 * @param reboundDef the reboundDef to set
	 */
	public void setReboundDef(int reboundDef) {
		this.reboundDef = reboundDef;
	}




	/**
	 * @param strenght the strenght to set
	 */
	public void setStrenght(int strenght) {
		this.strenght = strenght;
	}




	/**
	 * @param reactionTime the reactionTime to set
	 */
	public void setReactionTime(int reactionTime) {
		this.reactionTime = reactionTime;
	}




	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}




	/**
	 * @param form the form to set
	 */
	public void setForm(int form) {
		this.form = form;
	}




	@Override
	public String toString() {
		String value="Team name : "+name+"\n";
		for(Player p : players) {
			value+=p.getName()+"\n";
		}
		return value;
	}
	
	
	
	public void getStats() {
		for(Player p : players) {
			this.attack+= p.getAttack();
			this.shot2Pts+= p.getShot2Pts();
			this.shot3Pts+= p.getShot3Pts();
			this.freeThrows+= p.getFreeThrows();
			this.pass+= p.getPass();
			this.ballHandle+= p.getBallHandle();
			this.reboundOff+= p.getReboundOff();
			this.defense+= p.getDefense();
			this.block+= p.getBlock();
			this.steal+= p.getSteal();
			this.reboundDef+= p.getReboundDef();
			this.strenght+= p.getStrenght();
			this.reactionTime+= p.getReactionTime();
			this.speed+= p.getSpeed();
			this.form+= p.getForm();

		}
	}
	
	
	
	
	

}
