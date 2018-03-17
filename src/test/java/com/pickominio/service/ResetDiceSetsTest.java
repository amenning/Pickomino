package com.pickominio.service;

import com.pickominio.model.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class ResetDiceSetsTest {
    private DiceSet frozenDiceSet;
    private DiceSet activeDiceSet;
    private FreezeDice freezeDice;
    private ResetDiceSets resetDiceSets;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        activeDiceSet = ActiveDiceSet.buildNewSet();
        frozenDiceSet = FrozenDiceSet.buildNewSet();
        resetDiceSets = ResetDiceSets.build()
            .registerActiveDiceSet(activeDiceSet)
            .registerFrozenDiceSet(frozenDiceSet);
        freezeDice = new FreezeDice();
    }

    @Test
    public void incorrectUseException() throws Exception {
        exception.expect(Exception.class);
        exception.expectMessage(
            "Must register active and frozen dice sets first"
        );

        ResetDiceSets.build().reset();
    }

    @Test
    public void diceSetsResetSuccessfully() throws Exception {
        freezeAllDice();

        resetDiceSets.reset();

        assertDiceSetsReset();
    }

    private void freezeAllDice() throws Exception {
        freezeDice.from(activeDiceSet).to(frozenDiceSet).value(Dice.MAX_DICE_VALUE);
    }

    private void assertDiceSetsReset() {
        assertTrue(frozenDiceSet.isEmpty());
        assertEquals(DiceSet.MAX_DICE_SET_SIZE, activeDiceSet.getNumberOfDice());
        assertEquals(
            DiceSet.MAX_DICE_SET_SIZE,
            activeDiceSet.getAllDiceOfValue(Dice.MAX_DICE_VALUE).size()
        );
    }
}