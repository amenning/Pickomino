package com.pickominio.model;

import java.util.ArrayList;

public class ActiveDiceSet extends DiceSet {
    private ActiveDiceSet() {
        resetDiceSet();
    }

    @Override
    public void resetDiceSet() {
        super.resetDiceSet();
        for (int i = 0; i < this.MAX_DICE_SET_SIZE; ++i) {
            this.diceSet.get(Dice.MAX_DICE_VALUE)
                .add(Dice.buildDice(Dice.MAX_DICE_VALUE));
        }
    }

    public static ActiveDiceSet buildNewSet() {
        return new ActiveDiceSet();
    }

    public void emptyDiceSet() {
        for (int value = 1; value <= Dice.MAX_DICE_VALUE; ++value) {
            this.diceSet.put(value, new ArrayList<Dice>());
        }
    }
}
