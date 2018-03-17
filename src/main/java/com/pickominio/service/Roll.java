package com.pickominio.service;

import com.pickominio.model.ActiveDiceSet;
import com.pickominio.model.Dice;
import com.pickominio.model.DiceSet;

import java.util.concurrent.ThreadLocalRandom;

public class Roll {
    private final ActiveDiceSet activeDiceSet;

    private Roll(ActiveDiceSet activeDiceSet) {
        this.activeDiceSet = activeDiceSet;
    }

    public static Roll buildWithActiveDiceSet(ActiveDiceSet activeDiceSet) {
        return new Roll(activeDiceSet);
    }

    public void diceSet() {
        int numberOfDice = this.activeDiceSet.getNumberOfDice();
        this.activeDiceSet.emptyDiceSet();
        for (int number = 0; number < numberOfDice; ++number) {
            int diceValue = ThreadLocalRandom.current()
                    .nextInt(Dice.MIN_DICE_VALUE, Dice.MAX_DICE_VALUE + 1);
            Dice dice = Dice.buildDice(diceValue);
            this.activeDiceSet.addDice(dice);
        }
    }
}
