package process;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.SortedMap;
import data.*;
import usual.*;

/**
 * This is used to simulate a game
 * @author WCS
 *
 */
public class GameSimulation {

	private int duration;
	private int durationTotal;
	private Game game;
	private ArrayList<Player> playersInGameTeam1;
	private ArrayList<Player> playersInGameTeam2;
	private ArrayList<Player> playersOutGameTeam1;
	private ArrayList<Player> playersOutGameTeam2;
	private ArrayList<Player> teamHadBall;
	private ArrayList<Player> teamDontHadBall;
	private HashMap<String,Integer> statsPoints;
	private HashMap<String,Integer> statsBlocks;
	private HashMap<String,Integer> statsSteals;
	
	private Player playerHadBall;
	private SortedMap<Integer,String> actions;
	

	/**
	 * @return the duration
	 */
	public SortedMap<Integer,String> getActions() {
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

	
	/**
	 * This method start the simulation of one game.
	 * @param game
	 */
	public void play(Game game) {
		this.game=game;
		this.duration=-5;
		this.durationTotal=2881;
		actions=game.getActions();
		this.actions.clear();
		
		Team team1 = game.getTeam1();
		Team team2 = game.getTeam2();
		
		this.playersInGameTeam1 = getPlayersInGame(team1);
		this.playersInGameTeam2 = getPlayersInGame(team2);
		this.playersOutGameTeam1 = getPlayersOutGame(team1);
		this.playersOutGameTeam2 = getPlayersOutGame(team2);
		this.statsBlocks=game.getStatsBlocks();
		this.statsPoints=game.getStatsPoints();
		this.statsSteals=game.getStatsSteals();
		Boolean q3,q2,q4;
		q2=false;
		q3=false;
		q4=false;
		
		
		int actionTime = 0;
		actions.put(duration, "<p style= \"margin: 10px;text-align:center;margin-left: 5px;font-size: 15px;\" color=\"#A14801\">DEBUT DU 1ER QUART TEMPS</p>");
		this.duration++;
		betweenTwo();
		this.duration++;
		

		this.playerHadBall = teamHadBall.get(4);//Le pivot possede l'index 4 dans la liste
		
		int action;
		
		this.duration++;
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
					//actions.put(duration, "<p>"+playerHadBall.getName() +" dribble</p>");
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
				this.duration+=3;

			}
			else if(action == 3) {//change player
				int chooseTeam = MyRandom.getIntIntoMinMax(0, 1);//Choose the team which will change its players in
				if(chooseTeam==0){
					changment(0);
				}
				else{
					changment(1);

				}
				this.duration+=1;
			}

			if(duration>=2160 && !q4) {
				actions.put(duration, "<p style= \"margin: 10px;text-align:center;margin-left: 5px;font-size: 15px;\" color=\"#A14801\">DEBUT DU 4EME QUART TEMPS</p>");
				this.duration++;
				q4=true;
			}
			else if(duration>=1440 && !q3) {
				actions.put(duration, "<p style= \"margin: 10px;text-align:center;margin-left: 5px;font-size: 15px;\" color=\"#A14801\">DEBUT DU 3EME QUART TEMPS</p>");
				this.duration++;
				q3=true;
			}
			else if(duration>=720 && !q2) {
				actions.put(duration, "<p style= \"margin: 10px;text-align:center;margin-left: 5px;font-size: 15px;\" color=\"#A14801\">DEBUT DU 2EME QUART TEMPS</p>");
				this.duration++;
				q2=true;
			}

			if(duration>=durationTotal) {
				if(game.getScore1()==game.getScore2()) {
					durationTotal+=300;	
				}
			}
			
			
		}
		actions.put(duration, "<p style= \"margin: 10px;text-align:center;margin-left: 5px;font-size: 15px;\" color=\"#A14801\">FIN DU MATCH</p>");
		this.duration++;
		game.setActions(actions);
		
	}
	


	/**
	 * This method define which team get the ball to start
	 * @return   list of players who get the ball
	 */
	public void betweenTwo(){
		
		int chooseTeam = MyRandom.getIntIntoMinMax(0, 1);
		if(chooseTeam==0){
			
			this.actions.put(duration, "<p style= \"margin-bottom: 10px;text-align:center;margin-left: 5px;\">L'équipe "+game.getTeam1().getName()+" a gagné l'entre deux</p>");
			teamHadBall= playersInGameTeam1;
			teamDontHadBall= playersInGameTeam2;
		}
		else {
			this.actions.put(duration, "<p style= \"margin-bottom: 10px;text-align:center;margin-left: 5px;\">L'équipe "+game.getTeam2().getName()+" a gagné l'entre deux");
			teamHadBall= playersInGameTeam2;
			teamDontHadBall= playersInGameTeam1;
		}

	}



	/**
	 * This method define players who is in game
	 * @param team 
	 * @return  a list of players titulars
	 */
	public ArrayList<Player> getPlayersInGame(Team team){
		ArrayList<Player> playersIn = new ArrayList<Player>();
		int i = 0;
		for(Iterator<Player> it = team.getPlayers().iterator();it.hasNext()&&i<5;){
			playersIn.add(it.next());
			i++;
		}
		return playersIn;
	}
	
	
	/**
	 * This method define players who is out game
	 * @param team 
	 * @return  a list of players titulars
	 */
	public ArrayList<Player> getPlayersOutGame(Team team){
		ArrayList<Player> playersOut = new ArrayList<Player>();
		for(int i =5;i<10;i++){
			playersOut.add(team.getPlayers().get(i));
			
		}
		return playersOut;
	}

	/**
	 * This method calculates the succes of one shot
	 * @param teamHadBall
	 * @param playerHadBall
	 */
	public void shot(ArrayList<Player> teamHadBall, Player playerHadBall) {
		ArrayList<Player> teamDontHadBall = otherTeam(teamHadBall);
		int kindOfShot = MyRandom.getIntIntoMinMax(1, 100);
		Player interceptorPlayor = teamDontHadBall.get(MyRandom.getIntIntoMinMax(0, 4));
		Team teamBall;
		Team teamDontBall;
		if(game.getTeam1().getPlayers().contains(playerHadBall)){
			teamBall = game.getTeam1();
			teamDontBall=game.getTeam2();
		}
		else{
			teamBall = game.getTeam2();
			teamDontBall=game.getTeam1();
		}
		if(kindOfShot <= 60) {//2points
			
			float succesRate = (playerHadBall.getShot2Pts() - interceptorPlayor.getBlock())* MyRandom.getFloatIntoMinMax((float)1.1,(float)1.3);  

			float lessThan = MyRandom.getFloatIntoMinMax(0, 100);
			if(succesRate>lessThan) {
				
				this.game.addPoint(teamBall, 2);
				actions.put(duration,"<p  style= \"margin-bottom: 10px;margin-left: 5px;\" color=\"green\"><span style=\"font-size: 12px;font-weight:bold;\">" 
						+ teamBall.getName() +"</span> ( "+game.getScore1()+"-"+game.getScore2()+" ) :"
						+ playerHadBall.getName() +" a mis un panier à  2 points" + "</p>");
				int points=statsPoints.get(playerHadBall.getName())+2;
				statsPoints.put(playerHadBall.getName(), points);
			}
			else {
				int blockOrMissed = MyRandom.getIntIntoMinMax(0, 5);
				if(blockOrMissed==0) {
					actions.put(duration,"<p style= \"margin-bottom: 10px;margin-left: 5px;\" color=\"red\"><span style=\"font-size: 12px;font-weight:bold;\">"+teamDontBall.getName()+"</span> : " + interceptorPlayor.getName()+" a contré un shot à  2 point de "+ playerHadBall.getName()+ "</p> ");
					int blocks=statsBlocks.get(interceptorPlayor.getName())+1;
					statsBlocks.put(interceptorPlayor.getName(), blocks);
				}
				else {
					actions.put(duration,"<p style= \"margin-bottom: 10px;margin-left: 5px;\" color=\"red\"><span style=\"font-size: 12px;font-weight:bold;\">"+teamBall.getName()+"</span> : " + playerHadBall.getName()+" a raté un tir à 2 points</p> ");
				}
			}

		}
		else {//3points
			float succesRate = (playerHadBall.getShot3Pts()  - interceptorPlayor.getBlock())* MyRandom.getFloatIntoMinMax((float)1.1,(float)1.3);  

			float lessThan = MyRandom.getFloatIntoMinMax(0, 100);
			if(succesRate>lessThan) {
				
				this.game.addPoint(teamBall, 3);
				actions.put(duration,"<p  style= \"margin-bottom: 10px;margin-left: 5px;\" color=\"green\"><span style=\"font-size: 12px;font-weight:bold;\">" 
						+ teamBall.getName() +"</span> ( "+game.getScore1()+"-"+game.getScore2()+" ) :"
						+ playerHadBall.getName() +" a mis un panier à  3 points" + "</p>");
				int points=statsPoints.get(playerHadBall.getName())+3;
				statsPoints.put(playerHadBall.getName(), points);
				//System.out.println("shot 3pts");
			}
			else {
				int blockOrMissed = MyRandom.getIntIntoMinMax(0, 5);
				if(blockOrMissed==0) {
					actions.put(duration,"<p style= \"margin-bottom: 10px;margin-left: 5px;\" color=\"red\"><span style=\"font-size: 12px;font-weight:bold;\">"+teamDontBall.getName()+"</span> : " + interceptorPlayor.getName()+" a contré un shot à  3 point de "+ playerHadBall.getName()+ "</p> ");
					int blocks=statsBlocks.get(interceptorPlayor.getName())+1;
					statsBlocks.put(interceptorPlayor.getName(), blocks);
				}
				else {
					actions.put(duration,"<p style= \"margin-bottom: 10px;margin-left: 5px;\" color=\"red\"><span style=\"font-size: 12px;font-weight:bold;\">"+teamBall.getName()+"</span> : " + playerHadBall.getName()+" a raté un tir à 3 points</p> ");
				}
			}
		}
		
	}

	
	/**
	 * This method create a changment of players
	 * @param players
	 * @param team 
	 * @param duration
	 *  
	 */
	public void changment(int team){
		int positionToChange = MyRandom.getIntIntoMinMax(1, 5)-1;
		if(team==0) {
			Player p = playersInGameTeam1.get(positionToChange);
			playersInGameTeam1.remove(positionToChange);
			playersInGameTeam1.add(positionToChange, playersOutGameTeam1.get(positionToChange));
			playersOutGameTeam1.remove(positionToChange);
			playersOutGameTeam1.add(positionToChange, p);
			
			actions.put(duration,"<p style= \"margin-bottom: 10px;margin-left: 5px;\" color=\"blue\"><span style=\"font-size: 12px;font-weight:bold;\">"+ game.getTeam1().getName() +"</span> : "+p.getName()+" est remplacÃ© par "+ playersInGameTeam1.get(positionToChange).getName()+ "</p>");
		}
		else {
			Player p = playersInGameTeam2.get(positionToChange);
			playersInGameTeam2.set(positionToChange, playersOutGameTeam2.get(positionToChange));
			playersOutGameTeam2.set(positionToChange, p);
			
			actions.put(duration,"<p style= \"margin-bottom: 10px;margin-left: 5px;\" color=\"blue\"><span style=\"font-size: 12px;font-weight:bold;\">"+ game.getTeam2().getName() +"</span> : "+p.getName()+" est remplacÃ© par "+ playersInGameTeam2.get(positionToChange).getName()+ "</p>");
		}
		  
		/*Player playerIn =players.get(positionToChange-1);
		players.remove(positionToChange-1);
		
		if(!team.getPlayers().get(positionToChange-1).equals(playerIn)){
			players.add(team.getPlayers().get(positionToChange-1));
			actions.put(duration,"<p color=\"blue\">"+players.get(positionToChange-1).getName()+" est remplacÃ© par "+ team.getPlayers().get(positionToChange-1).getName()+ "</p>");

		}
		else{
			players.add(team.getPlayers().get(positionToChange-1+5));
			actions.put(duration,"<p color=\"blue\">"+players.get(positionToChange-1).getName()+" est remplacÃ© par "+ team.getPlayers().get(positionToChange-1+5).getName()+ "</p>");
		}*/
	}
	

	/**
	 * This method calculates the succes of one free throw
	 * @param playerHadBall
	 */
	public void freeThrow(Player playerHadBall) {
		float succesRate = playerHadBall.getFreeThrows() * MyRandom.getFloatIntoMinMax((float)1.1,(float)1.3) ;
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
			actions.put(duration,"<p style= \"margin-bottom: 10px;margin-left: 5px;\" color=\"orange\"><span style=\"font-size: 12px;font-weight:bold;\">" +team.getName()+"</span>( "+game.getScore1()+"-"+game.getScore2()+" ) :"
					+ playerHadBall.getName() + " de l'equipe "+" a mis un lancer franc</p>");
			int points=statsPoints.get(playerHadBall.getName())+1;
			statsPoints.put(playerHadBall.getName(), points);
	}
	}
	
	
	/**
	 * This method return the other team
	 * @param team team who is already choosed
	 * @game game 
	 * @return the other team in the game
	 */
	public ArrayList <Player> otherTeam(ArrayList <Player> team) {
		if(team.equals(playersInGameTeam1)) {
			return playersInGameTeam2;
		}
		return playersInGameTeam1;
	}
	
	/**
	 * This method calculates the succes of one pass
	 * @param teamHadBall
	 * @return
	 */
	public boolean pass(ArrayList <Player> teamHadBall) {
		
		int destinatorPlayerIndex = MyRandom.getIntIntoMinMax(0, 4);
		while (destinatorPlayerIndex==playerHadBall.getPosition() || destinatorPlayerIndex+5==playerHadBall.getPosition()) {
			destinatorPlayerIndex = MyRandom.getIntIntoMinMax(0, 4);
		}
		Team teamBall;
		Team teamDontBall;
		if(game.getTeam1().getPlayers().contains(playerHadBall)){
			teamBall = game.getTeam1();
			teamDontBall=game.getTeam2();
		}
		else{
			teamBall = game.getTeam2();
			teamDontBall=game.getTeam1();
		}
		Player destinatorPlayer = teamHadBall.get(destinatorPlayerIndex);

		ArrayList <Player> teamDontHadBall = otherTeam(teamHadBall);
		Player interceptorPlayor = teamDontHadBall.get(playerHadBall.getPosition()-1);
		
		//Define succesRate of the pass
		float succesRate = (playerHadBall.getPass() - interceptorPlayor.getSteal())* MyRandom.getFloatIntoMinMax((float)1.6,(float)2.0);  

		float lessThan = MyRandom.getFloatIntoMinMax(0, 100);
		if(succesRate>lessThan) {
		//actions.put(duration, "<p>" + playerHadBall.getName() +" a passÃ© la balle Ã  "+ destinatorPlayer.getName()+ "</p>");
			this.playerHadBall=destinatorPlayer;
			return true;
		}
		
		actions.put(duration, "<p style= \"margin-bottom: 10px;margin-left: 5px;\"><span style=\"font-size: 12px;font-weight:bold;\">"+teamDontBall.getName()+"</span> : " + interceptorPlayor.getName() +" a intercepté la balle à  "+ playerHadBall.getName()+ "</p> ");
		int steals=statsSteals.get(interceptorPlayor.getName())+1;
		statsSteals.put(interceptorPlayor.getName(), steals);
		this.playerHadBall=interceptorPlayor;
		return false;
	}
	
	/**
	 * This method calculates the action (shot,pass,dribble,changment)
	 * @return what action will happen
	 */
	public int chooseActionToDo(int max,int actionTime) {
		int doChange = MyRandom.getIntIntoMinMax(0, 99);
		if(doChange < 2){
			return 3;
		}
		if(actionTime<=9) {
			return MyRandom.getIntIntoMinMax(0, 1);//pass or dribble
		}
		else if(actionTime==24) {
			return 2;//shot
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
				//System.out.println("shot 2pts ratÃ©");
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
				//System.out.println("shot 3pts ratÃ©");
			}
		}
		
	}
	
	
	
	//TODO 1 si il a marquÃ©
	/**
	 * This method calculates the succes of one shot of the team
	 * @param teamHadBall
	 */
	public void teamFreeThrow(Team teamHadBall) {
		float succesRate = teamHadBall.getFreeThrows() * teamHadBall.getForm() * MyRandom.getFloatIntoMinMax((float)0.9,(float)1.1) ;
		float lessThan = MyRandom.getFloatIntoMinMax(0, 100);
		if(succesRate>lessThan) {
			game.addPoint(teamHadBall, 1);
		}
	}
	
	
	/**
	 * This method calculates the succes of one pass of the team
	 * @param teamHadBall
	 * @return
	 */
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