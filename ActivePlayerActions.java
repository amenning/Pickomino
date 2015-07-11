package Version3;
import java.util.*;

import javax.swing.JOptionPane;

public class ActivePlayerActions {
	public Scanner playerinput = new Scanner(System.in);
	public static boolean dicerollavailable = true;
	public int takewormnumber;
	public PlayerWorms currentplayer;
	public static boolean optionsopen = true;
	public int playeroption;
	public boolean isOnGrill = false;
	public ArrayList<Integer> ForbiddenHoldNumbers = new ArrayList<Integer>();
	public int playerinputinteger;
//	public int FrozenDiceFaceLimitCheck;
	
	public ActivePlayerActions(PlayerWorms player) {
		dicerollavailable=false;
		ForbiddenHoldNumbers.clear();
		currentplayer=player;
	}
	
	public void performRollDice(){	
		Dice.rollDice();
		dicerollavailable=false;
		ForbiddenHoldNumbers.clear();
//		if(Dice.bunk==true){
//			JOptionPane.showMessageDialog(null, "You have bunked!");
//			performPlayerBunk();
//		}
//		FrozenDiceFaceLimitCheck=0;
//		for(int x=0; x<6; x++){
//			if(Dice.FrozenDiceList.contains(x+1)){
//				FrozenDiceFaceLimitCheck++;
//			}
//		}
//		if(FrozenDiceFaceLimitCheck==5 && Dice.dicesum<Grill.grillworms.get(0)){
//			JOptionPane.showMessageDialog(null, "You cannot roll anymore and you cannot take a worm; you have bunked!");
//			performPlayerBunk();
//		}
//		else if(FrozenDiceFaceLimitCheck==5 && Dice.ActiveDiceList.contains(6)==false && Dice.ActiveDiceList.contains(6)==false){
//			JOptionPane.showMessageDialog(null, "You cannot roll anymore and you cannot take a worm; you have bunked!");
//			performPlayerBunk();
//		}
		playeroption=0;
		Gui.FreezeDiceButtonAvailable=true;
	}

	public void performPlayerBunk(){
		if(currentplayer.playerwormsarraylist.size()>0){
			currentplayer.RemoveBunkWormFromPlayer();
			Grill.AddWormBackToGrill(currentplayer.BunkWorm);
			playeroption = 0;
			ForbiddenHoldNumbers.clear();
			Dice.restartAllDice();
			dicerollavailable=false;
			Dice.bunk=false;
			Gui.EndPlayerTurn=true;
			Gui.FreezeDiceButtonAvailable=true;
		}
		else{
			//Grill.RemoveHighestWormFromGrill();
			playeroption = 0;
			ForbiddenHoldNumbers.clear();
			Dice.restartAllDice();
			dicerollavailable=false;
			Dice.bunk=false;
			Gui.EndPlayerTurn=true;
			Gui.FreezeDiceButtonAvailable=true;
		}

	}
	
	public void performFreezeDice(int playerinput){
		//2. Move Number Grouping to FrozenDiceList
		playerinputinteger=playerinput;
		optionsopen=true;
			if(ForbiddenHoldNumbers.contains(playerinputinteger)){
				System.out.println("You cannot freeze what you just unfroze");
			}
			else{
				Dice.moveDiceToFrozen(playerinputinteger);
				Gui.FreezeDiceButtonAvailable=false;
			}
		}
	
//	public void performUnfreezeDice(int playerinput){
//		//2. Move Number Grouping From FrozenDiceList to ActiveDiceList
//		Dice.printDiceList(Dice.ActiveDiceList);
//		Dice.printDiceList(Dice.FrozenDiceList);
//		Dice.moveDiceToActive(playerinput);
//		playeroption=0;
//	}

	public void performTakeWormFromGrill(int playerinput){
		optionsopen=true;
			//2.1. Select Worm From Grill
			takewormnumber=playerinput;
			//Check if player can take worm
			//Check if player dice (Active and Frozen) add up to desired total
			if(Dice.dicesum >= takewormnumber){
					currentplayer.addPrizeWormToPlayer(takewormnumber);
//						playeroption = 0;
//						dicerollavailable = false;
//						ForbiddenHoldNumbers.clear();
//						Dice.restartAllDice();
//						optionsopen=false;
//						Gui.FreezeDiceButtonAvailable=true;
			}
			else{
				JOptionPane.showMessageDialog(null, String.format("You do not have enough to take that worm, please select enter another worm to take"));
			}
	}
	
	public void performTakeWormFromPlayer(int prizeworm){
			
			currentplayer.addPrizeWormToPlayer(prizeworm);
			playeroption = 0;
			dicerollavailable = false;
			ForbiddenHoldNumbers.clear();
			Dice.restartAllDice();
			optionsopen=false;
			Gui.FreezeDiceButtonAvailable=true;
			
	}
	
}