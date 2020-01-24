package process;

import java.time.Duration;
import java.util.Random;

import data.*;


public class GameSimulation {

	private Duration duration = Duration.ofSeconds(0);
	private Game game;
	
	
	/**
	 * @return the duration
	 */
	public Duration getDuration() {
		return duration;
	}


	/**
	 * @param duration the duration to set
	 */
	public void setDuration(Duration duration) {
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


	public GameSimulation(Game game) {
		this.game = game;
	}
	
	
	public void play() {
		Team team1 = game.getTeam1();
		Team team2 = game.getTeam2();
		
		
		int quarter = 0;
		int actionTime = 0;
		Team teamHadBall = betweenTwo(team1,team2);
		Team teamDontHadBall = otherTeam(teamHadBall,game);
		
		int action;
		
		while( (duration.toMillis()/1000)!=2880 ) {
			action = chooseActionToDo(game,3);
			if(action==0 || action==1) { //if pass or dribble
				if(action==0) {
					if(teamHadBall.getPass() * teamHadBall.getForm() * MyRandom.getFloatIntoMinMax((float)0.9,(float)1.1) 
							< teamDontHadBall.getPass() * teamHadBall.getForm() * MyRandom.getFloatIntoMinMax((float)0.9,(float)1.1) ) {
						actionTime = 0;
						
					}
				}
			}
			
			
		}
		
	}
	
	
	/**
	 * @return the duration
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
	public Team otherTeam(Team team, Game game) {
		if(team == game.getTeam1()) {
			return game.getTeam2();
		}
		return game.getTeam1();
	}
	
	
	
	/**
	 * @return what action will be happened
	 */
	public int chooseActionToDo(Game game, int max) {
		return MyRandom.getIntIntoMinMax(0, max);
	}
	
	
	
	public void analyse() {
		
	}
	
	
	
	
 
}