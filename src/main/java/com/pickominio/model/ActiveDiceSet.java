package com.pickominio.model;

public class ActiveDiceSet extends DiceSet {
    private ActiveDiceSet() {
        super();
        for (int i = 0; i < this.MAX_DICE_SET_SIZE; ++i) {
            this.diceSet.get(Dice.MAX_DICE_VALUE).add(Dice.buildDice(Dice.MAX_DICE_VALUE));
        }
    }

    public static DiceSet buildNewSet() {
        return new ActiveDiceSet();
    }
}
