package process;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import data.*;
import usual.*;


public class GameSimulation {

	private int duration;
	private int durationTotal;
	private Game game;
	private ArrayList<Player> playersInTeam1;
	private ArrayList<Player> playersInTeam2;
	Player playerHadBall;
	private HashMap<Integer,String> actions = new HashMap<Integer,String>();
	

	/**
	 * @return the duration
	 */
	public HashMap<Integer,String> getActions() {
		return actions;
	}


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
		
		this.playersInTeam1 = getPlayersIn(team1);
		this.playersInTeam2 = getPlayersIn(team2);
		
		int actionTime = 0;
		ArrayList<Player> teamHadBall = betweenTwo();
		ArrayList<Player> teamDontHadBall = betweenTwo();

		this.playerHadBall = teamHadBall.get(4);//Le pivot possede l'index 4 dans la liste
		
		int action;
		
		while(duration<=durationTotal) {
			action = chooseActionToDo(2,actionTime);
			
			if(action==0) { //if pass
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
				if(action == 1){  // dribble
					actionTime +=3;
					this.duration+=3;
					actions.put(duration, "Le joueur "+ playerHadBall.getName() +" dribble");
				}
			}
			else if(action == 2) {//Shot

				int mistake = MyRandom.getIntIntoMinMax(0, 9);
				if(mistake==1) { //case when a fault is done on the team who had the ball (10% chance)
					//System.out.println("Lancer franc");
					freeThrow(playerHadBall);
					teamHadBall = teamDontHadBall;
					teamDontHadBall= otherTeam(teamHadBall);	

				}
				//TODO voir quoi faire dans le cas ou recup la balle
				else {
					//System.out.println("Tir");

					shot(teamHadBall,playerHadBall);
					teamHadBall = teamDontHadBall;
					teamDontHadBall= otherTeam(teamHadBall);	
				}
				actionTime = 0;

			}
			else if(action == 3) {//change player
				int chooseTeam = MyRandom.getIntIntoMinMax(0, 1);//Choose the team which will change its players in
				if(chooseTeam==0){
					System.out.println("La team 1 change");
					changment(playersInTeam1,team1);
				}
				else{
					System.out.println("La team 2 change");
					changment(playersInTeam2,team2);

				}
			}


			if(duration==durationTotal-1) {
				if(game.getScore1()==game.getScore2()) {
					durationTotal+=300;	
				}
			}
			
			
		}
		
	}
	


	/**
	 * 
	 * @return   list of players who get the ball
	 */
	public ArrayList<Player> betweenTwo(){
		
		int chooseTeam = MyRandom.getIntIntoMinMax(0, 1);
		if(chooseTeam==0){
			this.actions.put(0, "L'équipe "+game.getTeam1()+" a gagné l'entre deux");
			return playersInTeam1;
		}
		this.actions.put(0, "L'équipe "+game.getTeam2()+" a gagné l'entre deux");

		return playersInTeam2;

	}



	/**
	 * 
	 * @param team 
	 * @return  a list of players titulars
	 */
	public ArrayList<Player> getPlayersIn(Team team){
		ArrayList<Player> playersIn = new ArrayList<Player>();
		int i = 0;
		for(Iterator<Player> it = team.getPlayers().iterator();it.hasNext()&&i>5;){
			playersIn.add(it.next());
			i++;
		}
		return playersIn;
	}


	public void shot(ArrayList<Player> teamHadBall, Player playerHadBall) {
		ArrayList<Player> teamDontHadBall = otherTeam(teamHadBall);
		int kindOfShot = MyRandom.getIntIntoMinMax(1, 100);
		Player interceptorPlayor = teamDontHadBall.get(MyRandom.getIntIntoMinMax(0, 4));

		if(kindOfShot <= 60) {//2points
			
			float succesRate = (playerHadBall.getShot2Pts() - interceptorPlayor.getBlock())* MyRandom.getFloatIntoMinMax((float)0.9,(float)1.1);  

			float lessThan = MyRandom.getFloatIntoMinMax(0, 100);
			if(succesRate>lessThan) {
				Team team;
				if(game.getTeam1().getPlayers().contains(playerHadBall)){
					team = game.getTeam1();
				}
				else{
					team = game.getTeam2();
				}
				this.game.addPoint(team, 2);
				actions.put(duration,"Le joueur " + playerHadBall.getName() + " de l'equipe "+ team.getName()+" a mis un panier à 2 points");
				//System.out.println("shot 2pts");
			}
			else {
				actions.put(duration,"Le joueur " + interceptorPlayor.getName()+" a intercepté un shot à 2 point de "+ playerHadBall.getName());

				//System.out.println("shot 2pts raté");
			}

		}
		else {//3points
			float succesRate = (playerHadBall.getShot3Pts()  - interceptorPlayor.getBlock())* MyRandom.getFloatIntoMinMax((float)0.9,(float)1.1);  

			float lessThan = MyRandom.getFloatIntoMinMax(0, 100);
			if(succesRate>lessThan) {
				Team team;
				if(game.getTeam1().getPlayers().contains(playerHadBall)){
					team = game.getTeam1();
				}
				else{
					team = game.getTeam2();
				}
				this.game.addPoint(team, 3);
				actions.put(duration,"Le joueur " + playerHadBall.getName() + " de l'equipe "+ team.getName()+" a mis un panier à 3 points");

				//System.out.println("shot 3pts");
			}
			else {
				actions.put(duration,"Le joueur " + interceptorPlayor.getName()+" a intercepté un shot à 3 point de "+ playerHadBall.getName());

				//System.out.println("shot 3pts raté");
			}
		}
		
	}

	
	/**
	 * 
	 * @param players
	 * @param team 
	 * @param duration
	 *  
	 */
	public void changment(ArrayList<Player> players, Team team){
		int positionToChange = MyRandom.getIntIntoMinMax(1, 5);
		Player playerIn =players.get(positionToChange-1);
		players.remove(positionToChange-1);
		
		if(!team.getPlayers().get(positionToChange-1).equals(playerIn)){
			players.add(team.getPlayers().get(positionToChange-1));
			actions.put(duration,"Le joueur "+players.get(positionToChange-1).getName()+" est remplacé par "+ team.getPlayers().get(positionToChange-1).getName());

		}
		else{
			players.add(team.getPlayers().get(positionToChange-1+5));
			actions.put(duration,"Le joueur "+players.get(positionToChange-1).getName()+" est remplacé par "+ team.getPlayers().get(positionToChange-1+5));
		}
	}
	


	public void freeThrow(Player playerHadBall) {
		float succesRate = playerHadBall.getFreeThrows() * MyRandom.getFloatIntoMinMax((float)0.9,(float)1.1) ;
		float lessThan = MyRandom.getFloatIntoMinMax(0, 100);
		if(succesRate>lessThan) {
			Team team;
			if(game.getTeam1().getPlayers().contains(playerHadBall)){
				team = game.getTeam1();
			}
			else{
				team = game.getTeam2();
			}
			this.game.addPoint(team, 1);
			actions.put(duration,"Le joueur " + playerHadBall.getName() + " de l'equipe "+ team.getName()+" a mis un lancer franc");
	}
	}
	
	
	/**
	 * 
	 * @param team team who is already choosed
	 * @game game 
	 * @return the other team in the game
	 */
	public ArrayList <Player> otherTeam(ArrayList <Player> team) {
		if(team.equals(playersInTeam1)) {
			return playersInTeam2;
		}
		return playersInTeam1;
	}
	

	public boolean pass(ArrayList <Player> teamHadBall) {
		
		int destinatorPlayerIndex = MyRandom.getIntIntoMinMax(0, 4);
		while (destinatorPlayerIndex==playerHadBall.getPosition() || destinatorPlayerIndex+5==playerHadBall.getPosition()) {
			destinatorPlayerIndex = MyRandom.getIntIntoMinMax(0, 4);
		}
		Player destinatorPlayer = teamHadBall.get(destinatorPlayerIndex);

		ArrayList <Player> teamDontHadBall = otherTeam(teamHadBall);
		Player interceptorPlayor = teamDontHadBall.get(MyRandom.getIntIntoMinMax(0, 4));
		
		//Define succesRate of the pass
		float succesRate = (playerHadBall.getPass() - interceptorPlayor.getSteal())* MyRandom.getFloatIntoMinMax((float)0.9,(float)1.1);  

		float lessThan = MyRandom.getFloatIntoMinMax(0, 100);
		if(succesRate>lessThan) {
			actions.put(duration, "Le joueur" + playerHadBall.getName() +" a passé la balle à "+ destinatorPlayer.getName());
			this.playerHadBall=destinatorPlayer;
			return true;
		}
		actions.put(duration, "Le joueur" + interceptorPlayor.getName() +" a pris la balle à "+ playerHadBall.getName());
		this.playerHadBall=interceptorPlayor;
		return false;
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
		int doChange = MyRandom.getIntIntoMinMax(0, 99);
		if(doChange == 40){
			return 3;
		}
		return MyRandom.getIntIntoMinMax(0, max);
		
	}
	

	/* ---------------------------------------------------------------------------------------------------------------------------------------------------*/
	
	public Team otherTeam2(Team team) {
		if(team == game.getTeam1()) {
			return game.getTeam2();
		}
		return game.getTeam1();
	}

	/**
	 * @return the team who got the ball in the between two
	 */
	public Team teamBetweenTwo(Team team1, Team team2) {
		return (Team) MyRandom.oneOrTwo((Object)team1,(Object)team2);
	}


	public void teamShot(Team teamHadBall) {
		Team teamDontHadBall = otherTeam2(teamHadBall);
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
	public void teamFreeThrow(Team teamHadBall) {
		float succesRate = teamHadBall.getFreeThrows() * teamHadBall.getForm() * MyRandom.getFloatIntoMinMax((float)0.9,(float)1.1) ;
		float lessThan = MyRandom.getFloatIntoMinMax(0, 100);
		if(succesRate>lessThan) {
			game.addPoint(teamHadBall, 1);
		}
	}
	
	public boolean teamPass(Team teamHadBall) {
		
		Team teamDontHadBall = otherTeam2(teamHadBall);
		
		//Define succesRate of the pass
		float succesRate = ((teamHadBall.getPass() * teamHadBall.getForm()) - teamDontHadBall.getSteal() * teamHadBall.getForm())* MyRandom.getFloatIntoMinMax((float)0.9,(float)1.1);  

		float lessThan = MyRandom.getFloatIntoMinMax(0, 100);
		if(succesRate>lessThan) {
			return true;
		}
		return false;
	}
		
 
}