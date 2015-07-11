package Version4;
import java.security.SecureRandom;
import java.util.*;

import javax.swing.JOptionPane;

public class Dice {
	
	public static final int MAXDICENUMBER = 8;
	public static ArrayList<Integer> ActiveDiceList = new ArrayList<Integer>();
	public static ArrayList<Integer> FrozenDiceList = new ArrayList<Integer>();
	//public static Random rand = new Random();
	private static SecureRandom rand = new SecureRandom(); 
	  //rand.setSeed(seed); 
	  //int randInt = rand.nextInt(6);
	public static int activedicesize;
	public static int dicesum;
	public static int lastchancedicesum;
	public static boolean bunk = false;
	
	public Dice() {
		ActiveDiceList.clear();
		FrozenDiceList.clear();
		dicesum = 0;
		for(int x=0; x<MAXDICENUMBER; x++){
		ActiveDiceList.add(x, rand.nextInt(6)+1);
		}
		sumDice();
	}
	
	public static void restartAllDice(){
		System.out.println("Initializing all new 8 dice");
		ActiveDiceList.clear();
		FrozenDiceList.clear();
		dicesum = 0;
		for(int x=0; x<MAXDICENUMBER; x++){
		ActiveDiceList.add(x, rand.nextInt(6)+1);
		}
		sumDice();
	}
	
	public static void rollDice(){
		int ActiveDiceNumber=ActiveDiceList.size();
		ActiveDiceList.clear();
		dicesum = 0;
		for(int x=0; x<ActiveDiceNumber; x++){
		ActiveDiceList.add(x, rand.nextInt(6)+1);
		}
		sumDice();
		checkforbunkdice();
	}
	
	public static void checkforbunkdice(){
		bunk=true;
		for(int x=0; x<ActiveDiceList.size(); x++){
			if(FrozenDiceList.contains(ActiveDiceList.get(x))){
				//ActivePlayerActions.optionsopen = false;
			}
			else{
				bunk=false;
			}
		
		}
	}

	public static void moveDiceToFrozen(int numbergrouping){
		ArrayList<Integer> TransferDiceList = new ArrayList<Integer>();
		if(ActiveDiceList.contains(numbergrouping)){
			for(int x=0; x<ActiveDiceList.size(); x++){
				if(numbergrouping==ActiveDiceList.get(x)){
					FrozenDiceList.add(ActiveDiceList.get(x));		
				}
				else{
					TransferDiceList.add(ActiveDiceList.get(x));
				} 
			}
			ActiveDiceList.clear();
			ActiveDiceList.addAll(0, TransferDiceList);
			TransferDiceList.clear();
		}
		else{
			JOptionPane.showMessageDialog(null, "Active Dice do not contain that number grouping");
		}
	}

//	public static void moveDiceToActive(int numbergrouping){
//		ArrayList<Integer> TransferDiceList = new ArrayList<Integer>();
//		if(FrozenDiceList.contains(numbergrouping)){
//			for(int x=0; x<FrozenDiceList.size(); x++){
//				if(numbergrouping==FrozenDiceList.get(x)){
//					ActiveDiceList.add(FrozenDiceList.get(x));		
//				}
//				else{
//					TransferDiceList.add(FrozenDiceList.get(x));
//				} 
//			}
//			FrozenDiceList.clear();
//			FrozenDiceList.addAll(0, TransferDiceList);
//			TransferDiceList.clear();
//			printDiceList(ActiveDiceList);
//			printDiceList(FrozenDiceList);
//		}
//		else{
//			System.out.println("Frozen dice do not contain that number grouping");
//		}
//	}
	
	public static void sumDice(){ 
		dicesum=0;
//		for(int x=0; x<ActiveDiceList.size(); x++){
//			if(ActiveDiceList.get(x)==6){
//				dicesum += 5;
//			}
//			else{
//				dicesum += ActiveDiceList.get(x);
//			}
//		}
		for(int x=0; x<FrozenDiceList.size(); x++){
			if(FrozenDiceList.get(x)==6){
				dicesum += 5;
			}
			else{
				dicesum += FrozenDiceList.get(x);
			}
		}
	}

	public static void sumDiceLastChance(){ 
		lastchancedicesum=0;
		for(int x=0; x<FrozenDiceList.size(); x++){
			if(FrozenDiceList.get(x)==6){
				lastchancedicesum += 5;
			}
			else{
				lastchancedicesum += FrozenDiceList.get(x);
			}
		}
		for(int x=0; x<ActiveDiceList.size(); x++){
			if(FrozenDiceList.contains(ActiveDiceList.get(x))==false){	
				if(ActiveDiceList.get(x)==6){
					lastchancedicesum += 5;
				}
				else{
					lastchancedicesum += ActiveDiceList.get(x);
				}
			}
		}
	}
	
//	public static void printDiceList(ArrayList<Integer> q){
//		if(q.isEmpty())
//			System.out.println("This dice list has no values");
//		else
//			System.out.println("This is the dice list values");
//			System.out.printf("%s Bottom\n", q);
//	}
	
}