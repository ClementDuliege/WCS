package data;

/**
 * 
 * @author WCS Corporation
 *
 */
public class Player {

	private String name;
	private int position;
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
	
	
	public Player(String name) {
		this.name = name;
	}
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
	
	

	/**
	 * @return the position
	 */
	public int getPosition() {
		return position;
	}
	/**
	 * @return the position
	 */
	public String getPositionString() {
		String positionString="";
		if(position==1) {
			positionString+="Meneur";
		}
		else if(position==2) {
			positionString+="Arrière";
		}
		else if(position==3) {
			positionString+="Aillier";
		}
		else if(position==4) {
			positionString+="Ailier Fort";
		}
		else if(position==5) {
			positionString+="Pivot";
		}
		return positionString;
	}
	/**
	 * @param position the position to set
	 */
	public void setPosition(int position) {
		this.position = position;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
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
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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


	
	
	
	
}
