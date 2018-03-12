package com.pickominio.service;

import com.pickominio.model.Dice;
import com.pickominio.model.DiceSet;

import java.util.concurrent.ThreadLocalRandom;

public class Roll {
    public void diceSet(DiceSet diceSet) {
        int numberOfDice = diceSet.getNumberOfDice();
        diceSet.resetDiceSet();
        for (int number = 0; number < DiceSet.MAX_DICE_SET_SIZE; ++number) {
            int diceValue = ThreadLocalRandom.current()
                    .nextInt(Dice.MIN_DICE_VALUE, Dice.MAX_DICE_VALUE + 1);
            Dice dice = Dice.buildDice(diceValue);
            diceSet.addDice(dice);
        }
    }
}
