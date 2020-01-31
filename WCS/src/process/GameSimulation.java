package process;

import java.time.Duration;

import java.util.Random;

import data.*;
import usual.*;


public class GameSimulation {

	private int duration;
	private Game game;
	
	
	
	
	/**
	 * @param game
	 */
	public GameSimulation(Game game) {
		super();
		this.game = game;
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
		this.game = game;
	}

	
	
	public void play() {
		Team team1 = game.getTeam1();
		Team team2 = game.getTeam2();
		
		
		int quarter = 0;
		int actionTime = 0;
		Team teamHadBall = betweenTwo(team1,team2);
		Team teamDontHadBall = otherTeam(teamHadBall);
		
		int action;
		
		while(duration!=2880) {
			action = chooseActionToDo(2,actionTime);
			if(action==0 || action==1) { //if pass or dribble
				if(action == 0) {
					System.out.println("Passe/dribble");
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
					freeThrow(teamHadBall);
					teamHadBall = teamDontHadBall;
					teamDontHadBall= otherTeam(teamHadBall);	

				}
				else {
					shot(teamHadBall);
					teamHadBall = teamDontHadBall;
					teamDontHadBall= otherTeam(teamHadBall);	
				}
				actionTime = 0;

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
			if(teamHadBall.getShot2Pts() * teamHadBall.getForm() * MyRandom.getFloatIntoMinMax((float)0.9,(float)1.1) 
					>= teamDontHadBall.getBlock()*2 * teamHadBall.getForm() * MyRandom.getFloatIntoMinMax((float)0.9,(float)1.1) ) {
				this.game.addPoint(teamHadBall, 2);
				System.out.println("shot 2pts");

			}
			else {
				System.out.println("shot 2pts raté");
			}
		}
		else {//3points
			if(teamHadBall.getShot3Pts() * teamHadBall.getForm() * MyRandom.getFloatIntoMinMax((float)0.9,(float)1.1) 
					>= teamDontHadBall.getBlock() * teamHadBall.getForm() * MyRandom.getFloatIntoMinMax((float)0.9,(float)1.1) ) {
				this.game.addPoint(teamHadBall, 3);
				System.out.println("shot 3pts");

			}
			else {
				System.out.println("shot 3pts raté");

			}
		}
		
	}
	
	
	
	//TODO 1 si il a marquÃ©
	public void freeThrow(Team teamHadBall) {
		float succesRate = teamHadBall.getFreeThrows() * teamHadBall.getForm() * MyRandom.getFloatIntoMinMax((float)0.9,(float)1.1) ;
		float lessThan = MyRandom.getFloatIntoMinMax(0, 100);
		if(succesRate<lessThan) {
			game.addPoint(teamHadBall, 1);
		}
	}
	
	public boolean pass(Team teamHadBall) {
		Team teamDontHadBall = otherTeam(teamHadBall);
		if(teamHadBall.getPass() * teamHadBall.getForm() * MyRandom.getFloatIntoMinMax((float)0.9,(float)1.1) 
				>= teamDontHadBall.getPass() * teamHadBall.getForm() * MyRandom.getFloatIntoMinMax((float)0.9,(float)1.1) ) {
			return true;
		}
		return false;
	}
	

	
	
	public void analyse() {
		
	}
	
	
	public static void main(String[] args) {
		
		
	}
 
}