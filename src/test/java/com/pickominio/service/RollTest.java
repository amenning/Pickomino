package com.pickominio.service;

import com.pickominio.model.ActiveDiceSet;
import com.pickominio.model.Dice;
import com.pickominio.model.DiceSet;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.Stream;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.*;

public class RollTest {
    private ActiveDiceSet activeDiceSet;
    private Roll roll;

    @Before
    public void setUp() throws Exception {
        activeDiceSet = ActiveDiceSet.buildNewSet();
        roll = Roll.buildWithActiveDiceSet(activeDiceSet);
    }

    @Test
    public void diceSet() {
        roll.diceSet();
        assertEquals(DiceSet.MAX_DICE_SET_SIZE, activeDiceSet.getNumberOfDice());
        int[] values = Stream.of(activeDiceSet.toString().split(" "))
                .mapToInt(value -> Integer.parseInt(value))
                .toArray();
        for (int diceValue : values) {
            assertThat(
                diceValue,
                allOf(
                    greaterThanOrEqualTo(Dice.MIN_DICE_VALUE),
                    lessThanOrEqualTo(Dice.MAX_DICE_VALUE)
                )
            );
        }
    }
}