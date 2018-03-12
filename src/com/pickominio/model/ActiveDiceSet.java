package com.pickominio.model;

public class ActiveDiceSet extends DiceSet {
    private ActiveDiceSet() {
        super();
        for (int i = 0; i < this.MAX_DICE_SET_SIZE; ++i) {
            this.diceSet.get(6).add(Dice.buildDice(6));
        }
    }

    public static DiceSet buildNewSet() {
        return new ActiveDiceSet();
    }
}
