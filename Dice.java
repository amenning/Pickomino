package Version10_inprogress;
import java.security.SecureRandom;
import java.util.*;

import javax.swing.JOptionPane;

public class Dice {
		
	private static ArrayList<Integer> ActiveDiceList = new ArrayList<Integer>();
	private static ArrayList<Integer> FrozenDiceList = new ArrayList<Integer>();
	
	private static SecureRandom rand = new SecureRandom(); 
	
	private static final int MAXDICENUMBER = 8;
	private static int activedicesize;
	private static int dicesum;
	private static int lastchancedicesum;
	
	private static boolean bunk = false;
	
	public static int getActiveDiceListValue(int activediceindex){
		return ActiveDiceList.get(activediceindex);
	}
	
	public static int getActiveDiceListSize(){
		return ActiveDiceList.size();
	}
	
	public static boolean doesActiveDiceListContainValue(int activedicevaluecheck){
		if(ActiveDiceList.contains(activedicevaluecheck)){
			return true;
		}
		else{
			return false;
		}
	}
	
	public static int getFrozenDiceListValue(int frozendiceindex){
		return FrozenDiceList.get(frozendiceindex);
	}
	
	public static int getFrozenDiceListSize(){
		return FrozenDiceList.size();
	}
	
	public static boolean doesFrozenDiceListContainValue(int frozendicevaluecheck){
		if(FrozenDiceList.contains(frozendicevaluecheck)){
			return true;
		}
		else{
			return false;
		}
	}
	
	public static void setBunk(boolean setboolean){
		bunk=setboolean;
	}
	
	public static boolean getBunk(){
		return bunk;
	}
	
	public static int getDiceSum(){
		return dicesum;
	}
	
	public static int getLastChanceDiceSum(){
		return lastchancedicesum;
	}
	
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
			if(FrozenDiceList.contains(ActiveDiceList.get(x))==false){
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

	public static void sumDice(){ 
		dicesum=0;
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
		
		for(int y=0; y<ActiveDiceList.size(); y++){
			if(FrozenDiceList.contains(ActiveDiceList.get(y))==false){	
				if(ActiveDiceList.get(y)==6){
					lastchancedicesum += 5;
				}
				else{
					lastchancedicesum += ActiveDiceList.get(y);
				}
			}
		}	
	}
		
}