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
    private DiceSet diceSet;
    private Roll roll;

    @Before
    public void setUp() throws Exception {
        diceSet = ActiveDiceSet.buildNewSet();
        roll = new Roll();
    }

    @Test
    public void diceSet() {
        roll.diceSet(diceSet);
        int[] values = Stream.of(diceSet.toString().split(" "))
                .mapToInt(value -> Integer.parseInt(value))
                .toArray();
        for (int diceValue : values) {
            assertThat(
                diceValue,
                allOf(greaterThanOrEqualTo(Dice.MIN_DICE_VALUE), lessThanOrEqualTo(Dice.MAX_DICE_VALUE))
            );
        }
    }
}