package com.pickominio.service;

import com.pickominio.exception.ValueAlreadyFrozenException;
import com.pickominio.model.Dice;
import com.pickominio.model.DiceSet;

import java.util.List;

public class FreezeDice {
    private DiceSet activeDiceSet;
    private DiceSet frozenDiceSet;
    private List<Dice> diceGroup;

    public FreezeDice from(DiceSet activeDiceSet) {
        this.activeDiceSet = activeDiceSet;
        return this;
    }

    public FreezeDice to(DiceSet frozenDiceSet) {
        this.frozenDiceSet = frozenDiceSet;
        return this;
    }

    public void value(int value) throws ValueAlreadyFrozenException {
        if(!frozenDiceSet.hasValue(value)) {
            diceGroup = activeDiceSet.getAllDiceOfValue(value);
            frozenDiceSet.addDice(diceGroup);
        } else {
            throw new ValueAlreadyFrozenException();
        }
    }
}
