package Version5;
import java.util.*;

public class PlayerWorms {
	
	public ArrayList<Integer> playerwormsarraylist;
	public String playername;
	public int BunkWorm;
//	public int PlayerScore;
	
	public PlayerWorms(String name) {
		System.out.printf("Initializing new player %s\n", name);
		playername = name;
		playerwormsarraylist= new ArrayList<Integer>();
	}

	public void addPrizeWormToPlayer(int prizeworm){
		System.out.printf("Adding prizeworm %s to top of stack of worms\n", prizeworm);
		playerwormsarraylist.add(0, prizeworm);
		printPlayerWormsArrayList();
	}
	
	public void RemovePrizeWormFromPlayer(int prizeworm){
		System.out.printf("Attempting to remove prizeworm %s from player\n", prizeworm);
		if(playerwormsarraylist.size()>0 && playerwormsarraylist.get(0)==prizeworm){
			//remove prizeworm from player arraylist
			playerwormsarraylist.remove(0);
			printPlayerWormsArrayList();
		}
		else{
			//gives warning that prizeworm doesn't exist in grill arraylist
			System.out.println("Prizeworm is not on the top of this players stack of worms");
		}
	}
	
	public void RemoveBunkWormFromPlayer(){

		System.out.printf("Attempting to remove bunk worm from player\n");
		if(playerwormsarraylist.size()>0){
			//remove prizeworm from player arraylist
			BunkWorm=playerwormsarraylist.get(0);
			playerwormsarraylist.remove(0);
			Dice.bunk=false;
			
		}
		else{
			//gives warning that prizeworm doesn't exist in grill arraylist
			System.out.println("Player has no stack of worms");
			Dice.bunk=false;
		}
	}
	
	public void printPlayerWormsArrayList(){
		if(playerwormsarraylist.isEmpty())
			System.out.println("This player has no worms");
		else
			System.out.println("This is the player worm list");
			System.out.printf("%s Bottom\n", playerwormsarraylist);
	}
	
}