package Version3;
import java.util.*;
import javax.swing.JOptionPane;

public class Grill {
	
	public static ArrayList<Integer> grillworms= new ArrayList<Integer>();
	public static boolean endofgame = false;
	private final int HighestWorm = 36;
	private final int LowestWorm = 21;
	
	public Grill() {
		for(int x=LowestWorm; x<=HighestWorm; x++) {
		grillworms.add(x);
		}
	}

	public static boolean CheckIfPrizeWormIsOnGrill(int prizeworm){
		if(grillworms.contains(prizeworm)){
			if(Dice.dicesum>=prizeworm){
			RemovePrizeWormFromGrill(prizeworm);
			}
			return true;
		}
		else{
			JOptionPane.showMessageDialog(null, String.format("That worm is not on the grill"));
			return false;
		}
	}
	
	private static void RemovePrizeWormFromGrill(int prizeworm){
			grillworms.remove(grillworms.indexOf(prizeworm));		
	}

	public static void AddWormBackToGrill(int lostworm){
			//Check if lostworm belongs at front of grill arraylist
		if(lostworm<grillworms.get(0)){
			grillworms.add(0,lostworm);
			RemoveHighestWormFromGrill();
		}
		//Check if lostworm belongs at end of grill arraylist
		else if(lostworm>grillworms.get(grillworms.size()-1)){
			grillworms.add(grillworms.size(), lostworm);
		}
		//Otherwise find place in middle of grill arraylist to add lost worm
		else {
			for(int x=0; x<grillworms.size(); x++){
				if(lostworm<grillworms.get(x) && lostworm>grillworms.get(x-1)){
					grillworms.add(x, lostworm);
				}
			}
			RemoveHighestWormFromGrill();
		}
	}

	public static void RemoveHighestWormFromGrill(){
		grillworms.remove(grillworms.size()-1);
	}
	
	public static void checkEndOfGameCondition(){
		if(grillworms.isEmpty()){
			endofgame=true;
		}
	}	
}