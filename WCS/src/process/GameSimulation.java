package process;


import data.*;
import usual.*;


public class GameSimulation {

	private int duration;
	private int durationTotal;
	private Game game;
	
	
	
	
	/**
	 * @param game
	 */
	public GameSimulation() {
		super();
		this.durationTotal=2881;
		
	}


	
	
	/**
	 * @return the duration
	 */
	public int getDuration() {
		return duration;
	}


	/**
	 * @param duration the duration to set
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}


	/**
	 * @return the game
	 */
	public Game getGame() {
		return game;
	}


	/**
	 * @param game the game to set
	 */
	public void setGame(Game game) {
		duration=0;
		durationTotal=2881;
		this.game = game;
	}

	
	
	public void play(Game game) {
		this.game=game;
		this.duration=0;
		Team team1 = game.getTeam1();
		Team team2 = game.getTeam2();
		
		
		int actionTime = 0;
		Team teamHadBall = betweenTwo(team1,team2);
		Team teamDontHadBall = otherTeam(teamHadBall);
		
		int action;
		
		while(duration<=durationTotal) {
			action = chooseActionToDo(2,actionTime);
			
			if(action==0 || action==1) { //if pass or dribble
				if(action == 0) {
					//System.out.println("Passe/dribble");
					if(!pass(teamHadBall)) {
						actionTime = 0;
						this.duration+=3;
						teamHadBall = teamDontHadBall;
						teamDontHadBall= otherTeam(teamHadBall);	
					}
					else {
						actionTime +=3;
						this.duration+=3;
					}
				}
			}
			else if(action == 2) {

				int mistake = MyRandom.getIntIntoMinMax(0, 9);
				if(mistake==1) { //case when a fault is done on the team who had the ball (10% chance)
					//System.out.println("Lancer franc");
					freeThrow(teamHadBall);
					teamHadBall = teamDontHadBall;
					teamDontHadBall= otherTeam(teamHadBall);	

				}
				else {
					//System.out.println("Tir");

					shot(teamHadBall);
					teamHadBall = teamDontHadBall;
					teamDontHadBall= otherTeam(teamHadBall);	
				}
				actionTime = 0;

			}
			
			if(duration==durationTotal-1) {
				if(game.getScore1()==game.getScore2()) {
					durationTotal+=300;
					
				}
			}
			
			
		}
		
	}
	
	
	/**
	 * @return the team who got the ball in the between two
	 */
	public Team betweenTwo(Team team1, Team team2) {
		return (Team) MyRandom.oneOrTwo((Object)team1,(Object)team2);
	}
	
	
	/**
	 * 
	 * @param team team who is already choosed
	 * @game game 
	 * @return the other team in the game
	 */
	public Team otherTeam(Team team) {
		if(team == game.getTeam1()) {
			return game.getTeam2();
		}
		return game.getTeam1();
	}
	
	
	
	/**
	 * @return what action will happen
	 */
	public int chooseActionToDo(int max,int actionTime) {
		if(actionTime<=9) {
			return MyRandom.getIntIntoMinMax(0, 1);//pass or dribble
		}
		else if(actionTime==24) {
			return 2;//shot
		}
		return MyRandom.getIntIntoMinMax(0, max);
		
	}
	
	
	
	//TODO ajouter cas ou faute, reduire le taux de reussite 
	public void shot(Team teamHadBall) {
		Team teamDontHadBall = otherTeam(teamHadBall);
		int kindOfShot = MyRandom.getIntIntoMinMax(1, 100);
		if(kindOfShot <= 60) {//2points
			
			float succesRate = ((teamHadBall.getShot2Pts() * teamHadBall.getForm()) - teamDontHadBall.getBlock() * teamHadBall.getForm())* MyRandom.getFloatIntoMinMax((float)0.9,(float)1.1);  

			float lessThan = MyRandom.getFloatIntoMinMax(0, 100);
			if(succesRate>lessThan) {
				this.game.addPoint(teamHadBall, 2);
				//System.out.println("shot 2pts");
			}
			else {
				//System.out.println("shot 2pts raté");
			}

		}
		else {//3points
			float succesRate = ((teamHadBall.getShot2Pts() * teamHadBall.getForm()) - teamDontHadBall.getBlock() * teamHadBall.getForm())* MyRandom.getFloatIntoMinMax((float)0.9,(float)1.1);  

			float lessThan = MyRandom.getFloatIntoMinMax(0, 100);
			if(succesRate>lessThan) {
				this.game.addPoint(teamHadBall, 3);
				//System.out.println("shot 3pts");
			}
			else {
				//System.out.println("shot 3pts raté");
			}
		}
		
	}
	
	
	
	//TODO 1 si il a marqué
	public void freeThrow(Team teamHadBall) {
		float succesRate = teamHadBall.getFreeThrows() * teamHadBall.getForm() * MyRandom.getFloatIntoMinMax((float)0.9,(float)1.1) ;
		float lessThan = MyRandom.getFloatIntoMinMax(0, 100);
		if(succesRate>lessThan) {
			game.addPoint(teamHadBall, 1);
		}
	}
	
	public boolean pass(Team teamHadBall) {
		
		Team teamDontHadBall = otherTeam(teamHadBall);
		
		//Define succesRate of the pass
		float succesRate = ((teamHadBall.getPass() * teamHadBall.getForm()) - teamDontHadBall.getSteal() * teamHadBall.getForm())* MyRandom.getFloatIntoMinMax((float)0.9,(float)1.1);  

		float lessThan = MyRandom.getFloatIntoMinMax(0, 100);
		if(succesRate>lessThan) {
			return true;
		}
		return false;
	}
		
	
	public static void main(String[] args) {
		
		
	}
 
}