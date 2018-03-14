package com.pickominio.service;

import com.pickominio.exception.ValueAlreadyFrozenException;
import com.pickominio.exception.ValueMissingException;
import com.pickominio.model.ActiveDiceSet;
import com.pickominio.model.Dice;
import com.pickominio.model.DiceSet;
import com.pickominio.model.FrozenDiceSet;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class FreezeDiceTest {
    private FreezeDice freezeDice;
    private DiceSet activeDiceSet;
    private DiceSet frozenDiceSet;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        freezeDice = new FreezeDice();
        activeDiceSet = ActiveDiceSet.buildNewSet();
        frozenDiceSet = FrozenDiceSet.buildNewSet();
    }

    @Test
    public void incorrectUseException() throws Exception {
        exception.expect(Exception.class);
        exception.expectMessage("Must use from().to().value() method call");

        freezeDice.from(activeDiceSet).value(6);
    }

    @Test
    public void diceTransferedSuccessfully() throws Exception {
        freezeDice.from(activeDiceSet)
            .to(frozenDiceSet)
            .value(Dice.MAX_DICE_VALUE);
        assertFalse(activeDiceSet.hasValue(Dice.MAX_DICE_VALUE));
        assertTrue(frozenDiceSet.hasValue(Dice.MAX_DICE_VALUE));
    }

    @Test
    public void valueAlreadyFrozen() throws Exception {
        exception.expect(ValueAlreadyFrozenException.class);
        Dice dice = Dice.buildDice(Dice.MAX_DICE_VALUE);
        frozenDiceSet.addDice(dice);

        freezeDice.from(activeDiceSet)
            .to(frozenDiceSet)
            .value(Dice.MAX_DICE_VALUE);
    }

    @Test
    public void valueNotInActiveDiceSet() throws Exception {
        exception.expect(ValueMissingException.class);

        freezeDice.from(activeDiceSet)
                .to(frozenDiceSet)
                .value(Dice.MIN_DICE_VALUE);
    }
}
