package com.pickominio.service;

import com.pickominio.controller.GrillWorm;
import com.pickominio.model.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class ResolveBunkTest {
    private ResolveBunk bunk;
    private FreezeDice freezeDice;
    private GrillWormSet grillWormSet;
    private OutOfGameWormSet outOfGameWormSet;
    private PlayerWormSet playerWormSet;
    private ActiveDiceSet activeDiceSet;
    private FrozenDiceSet frozenDiceSet;


    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        activeDiceSet = ActiveDiceSet.buildNewSet();
        frozenDiceSet = FrozenDiceSet.buildNewSet();
        bunk = ResolveBunk.buildWithResetDiceService(
            ResetDiceSets.build()
                .registerActiveDiceSet(activeDiceSet)
                .registerFrozenDiceSet(frozenDiceSet)
        );
        freezeDice = new FreezeDice();
        grillWormSet = GrillWormSet.build();
        playerWormSet = PlayerWormSet.build();
        outOfGameWormSet = OutOfGameWormSet.build();
    }

    @Test
    public void incorrectUseException() throws Exception {
        exception.expect(Exception.class);
        exception.expectMessage(
            "Must use from().to().withOutOfGameWormSet().resolve() method call"
        );

        bunk.resolve();
    }

    @Test
    public void resolveBunkWhenPlayerWormSetEmpty() throws Exception {
        freezeAllDice();

        callResolveBunk();

        assertGrillWormNotMovedOutOfGame();
        assertDiceSetsReset();
    }

    private void freezeAllDice() throws Exception {
        freezeDice.from(activeDiceSet).to(frozenDiceSet).value(Dice.MAX_DICE_VALUE);
    }

    private void assertGrillWormNotMovedOutOfGame() {
        assertTrue(outOfGameWormSet.isEmpty());
        assertTrue(grillWormSet.hasWorm(Worm.MAX_WORM_TILE));
    }

    @Test
    public void resolveBunkWhenPlayerWormSetHasWorm() throws Exception {
        freezeAllDice();
        Worm worm = grillWormSet.takeWorm(Worm.MIN_WORM_TILE);
        playerWormSet.addWorm(worm);

        callResolveBunk();

        assertGrillWormRemovedFromGame();
        assertDiceSetsReset();
    }

    private void assertGrillWormRemovedFromGame() {
        assertTrue(outOfGameWormSet.hasWorm(Worm.MAX_WORM_TILE));
        assertFalse(grillWormSet.hasWorm(Worm.MAX_WORM_TILE));
    }

    private void callResolveBunk() throws Exception {
        bunk.from(playerWormSet)
            .to(grillWormSet)
            .withOutOfGameWormSet(outOfGameWormSet)
            .resolve();
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