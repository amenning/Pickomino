package Version9_stable;
import java.util.*;

public class PlayerWorms {
	
	private ArrayList<Integer> playerwormsarraylist;
	private String playername;
	
	public PlayerWorms(String name) {
		System.out.printf("Initializing new player %s\n", name);
		playername = name;
		playerwormsarraylist= new ArrayList<Integer>();
	}
	
	public String getPlayerName(){
		return playername;
	}
	
	public int getPlayerWormsArrayListSize(){
		return playerwormsarraylist.size();
	}
	
	public int getPlayerWormsArrayListWormValue(int wormindex){
		return playerwormsarraylist.get(wormindex);
	}

	public void addPrizeWormToPlayer(int prizeworm){

		playerwormsarraylist.add(0, prizeworm);
	}
	
	public void RemovePrizeWormFromPlayer(int prizeworm){

		if(playerwormsarraylist.size()>0 && playerwormsarraylist.get(0)==prizeworm){
			playerwormsarraylist.remove(0);
		}

	}
	
	public void RemoveBunkWormFromPlayer(){
		
		if(playerwormsarraylist.size()>0){
			playerwormsarraylist.remove(0);			
		}
		
		Dice.setBunk(false);
	}
	
	public void RemoveStolenWormFromPlayer(){
		if(playerwormsarraylist.size()>0){
			playerwormsarraylist.remove(0);
		}
	}
	
}