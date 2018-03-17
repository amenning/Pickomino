package com.pickominio.service;

import com.pickominio.exception.ValueAlreadyFrozenException;
import com.pickominio.exception.ValueMissingException;
import com.pickominio.model.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.max.MaxCore;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TakeWormTest {
    private WormSet destinationWormSet;
    private WormSet originatingWormSet;
    private DiceSet frozenDiceSet;
    private DiceSet activeDiceSet;
    private FreezeDice freezeDice;
    private TakeWorm takeWorm;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        activeDiceSet = ActiveDiceSet.buildNewSet();
        frozenDiceSet = FrozenDiceSet.buildNewSet();
        takeWorm = TakeWorm.buildWithResetDiceService(
            ResetDiceSets.build()
                .registerActiveDiceSet(activeDiceSet)
                .registerFrozenDiceSet(frozenDiceSet)
        );
        freezeDice = new FreezeDice();
        originatingWormSet = GrillWormSet.build();
        destinationWormSet = PlayerWormSet.build();
    }

    @Test
    public void incorrectUseException() throws Exception {
        exception.expect(Exception.class);
        exception.expectMessage(
            "Must use wormValue().from().to().resolve() method call"
        );

        takeWorm.from(originatingWormSet)
            .to(destinationWormSet)
            .resolve();
    }

    @Test
    public void wormTransferredSuccessfully() throws Exception {
        freezeAllDice();

        takeWorm.wormValue(Worm.MAX_WORM_TILE)
            .from(originatingWormSet)
            .to(destinationWormSet)
            .resolve();

        assertFalse(originatingWormSet.hasWorm(Worm.MAX_WORM_TILE));
        assertTrue(destinationWormSet.hasWorm(Worm.MAX_WORM_TILE));
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
