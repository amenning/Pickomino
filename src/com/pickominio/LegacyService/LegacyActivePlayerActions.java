package com.pickominio.LegacyService;

import com.pickominio.legacyController.LegacyGame;
import com.pickominio.legacyModel.Dice;
import com.pickominio.legacyModel.Grill;
import com.pickominio.legacyModel.PlayerWorms;

import javax.swing.*;

public class LegacyActivePlayerActions {

    private PlayerWorms currentplayer;

    private static boolean dicerollavailable = true;

    public LegacyActivePlayerActions(PlayerWorms player) {
        dicerollavailable = false;
        currentplayer = player;
    }

    public void setDiceRollAvailable(boolean setdicerollavailableboolean) {
        dicerollavailable = setdicerollavailableboolean;
    }

    public boolean getDiceRollAvailable() {
        return dicerollavailable;
    }

    public void performRollDice() {
        Dice.rollDice();
        dicerollavailable = false;
    }

    public void performPlayerBunk() {
        if (currentplayer.getPlayerWormsArrayListSize() > 0) {
            Grill.AddWormBackToGrill(currentplayer.getPlayerWormsArrayListWormValue(0));
            currentplayer.RemoveBunkWormFromPlayer();
            Dice.restartAllDice();
            dicerollavailable = false;
            Dice.setBunk(false);
            LegacyGame.setEndPlayerTurn(true);
        } else {
            Dice.restartAllDice();
            dicerollavailable = false;
            Dice.setBunk(false);
            LegacyGame.setEndPlayerTurn(true);
        }

    }

    public void performFreezeDice(int freezedicenumber) {
        //2. Move Number Grouping to FrozenDiceList
        Dice.moveDiceToFrozen(freezedicenumber);
    }


    public void performTakeWormFromGrill(int takewormnumber) {
        //Check if player can take worm
        if (Dice.getDiceSum() >= takewormnumber) {
            currentplayer.addPrizeWormToPlayer(takewormnumber);
        } else {
            JOptionPane.showMessageDialog(null, String.format("You do not have enough to take that worm, please select enter another worm to take"));
        }
    }

    public void performTakeWormFromPlayer(int prizeworm) {
        currentplayer.addPrizeWormToPlayer(prizeworm);
        dicerollavailable = false;
    }

}