package com.pickominio.service;

import com.pickominio.exception.ValueAlreadyFrozenException;
import com.pickominio.exception.ValueMissingException;
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

    public void value(int value) throws Exception {
        validate(value);
        diceGroup = activeDiceSet.getAllDiceOfValue(value);
        frozenDiceSet.addDice(diceGroup);
        reset();
    }

    private void validate(int value) throws Exception {
        if(activeDiceSet == null || frozenDiceSet == null) {
            throw new Exception("Must use from().to().value() method call");
        }

        if (!activeDiceSet.hasValue(value)) {
            throw new ValueMissingException();
        }

        if(frozenDiceSet.hasValue(value)) {
            throw new ValueAlreadyFrozenException();
        }
    }

    private void reset() {
        activeDiceSet = null;
        frozenDiceSet = null;
        diceGroup = null;
    }
}
