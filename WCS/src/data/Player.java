package data;

/**
 * 
 * @author WCS Corporation
 *
 */
public class Player {

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
	
	public Player(String name, int attack, int shot2Pts, int shot3Pts, int freeThrows, int pass, int ballHandle,
			int reboundOff, int defense, int block, int steal, int reboundDef, int strenght, int reactionTime,
			int speed) {
		this.name = name;
		this.attack = attack;
		this.shot2Pts = shot2Pts;
		this.shot3Pts = shot3Pts;
		this.freeThrows = freeThrows;
		this.pass = pass;
		this.ballHandle = ballHandle;
		this.reboundOff = reboundOff;
		this.defense = defense;
		this.block = block;
		this.steal = steal;
		this.reboundDef = reboundDef;
		this.strenght = strenght;
		this.reactionTime = reactionTime;
		this.speed = speed;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
